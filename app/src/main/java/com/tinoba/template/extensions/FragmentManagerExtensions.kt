package com.tinoba.template.extensions

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.tinoba.template.R

fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) = beginTransaction().func().commit()

fun FragmentManager.inTransactionAndAddToBackStack(func: FragmentTransaction.() -> FragmentTransaction) = beginTransaction().func().addToBackStack(null).commit()

fun FragmentManager.inTransactionAnimateAndAddToBackStack(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction()
            .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
            .func()
            .addToBackStack(null)
            .commit()
}

fun FragmentManager.removeFragmentIfItExists(fragmentTag: String) {
    val fragment = findFragmentByTag(fragmentTag)
    fragment?.let {
        beginTransaction()
                .remove(fragment)
                .commit()
    }
}

fun FragmentManager.clearBackStackAndInTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    beginTransaction()
            .func()
            .commit()
}