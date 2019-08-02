package com.tinoba.template.util

import okhttp3.CipherSuite
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import java.security.KeyManagementException
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

class OkHttpClientBuilderUtilsImpl : OkHttpClientBuilderUtils {

    private val x509TrustManager: X509TrustManager?
        get() {
            try {
                val factory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
                factory.init(null as KeyStore?)
                val trustManagers = factory.trustManagers
                return trustManagers[0] as X509TrustManager
            } catch (exception: NoSuchAlgorithmException) {
                exception.printStackTrace()
            } catch (exception: KeyStoreException) {
                exception.printStackTrace()
            }

            return null
        }

    private val tlsConnectionSpec: ConnectionSpec
        get() = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS).tlsVersions(TlsVersion.TLS_1_2)
                .cipherSuites(CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA)
                .build()

    override fun setupTls(builder: OkHttpClient.Builder) {
        val x509TrustManager = x509TrustManager
        if (x509TrustManager != null) {
            setupTlsInternal(builder, x509TrustManager)
        }
    }

    private fun setupTlsInternal(builder: OkHttpClient.Builder, x509TrustManager: X509TrustManager?) {
        try {
            builder.sslSocketFactory(TLSSocketFactory(), x509TrustManager!!)
            builder.connectionSpecs(listOf(tlsConnectionSpec))
        } catch (exception: KeyManagementException) {
            exception.printStackTrace()
        } catch (exception: NoSuchAlgorithmException) {
            exception.printStackTrace()
        }

    }
}
