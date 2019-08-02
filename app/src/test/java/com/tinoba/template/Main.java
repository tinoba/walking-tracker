package com.tinoba.template;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    int[] A;
    List<Set<Integer>> lista;

    @Test
    public void test() {

        int[] T = {9, 1, 4, 9, 0, 4, 8, 9, 0, 1};
        int A[] = {3, 2, 1, 6, 5};
        int B[] = {4, 2, 1, 3, 3};
        System.out.println(solution(A, B));
    }

    public int solution(int[] A, int X) {

        int xfromleft = 0;

        int xfromright = 0;

        for (int i = 0; i < A.length; i++)

        {
            if (A[i] == X) {
                xfromleft++;
            }
        }

        for (int i = A.length - 1; i >= 0; i--) {

            if (A[i] == X)

            {
                xfromleft--; //decrease it ...now this many X are present to the left of i
            }

            if (xfromleft == A.length - 1 - i - xfromright) {
                return i;
            }

            //you found the index ...you can return or continue to serach such

            //positions

            if (A[i] == X) {
                xfromright++;
            }

            System.out.println(Integer.toString(X, 2));
        }
        return 0;
    }

    public int[] solution(int[] T) {
        A = new int[T.length - 1];

        lista = new ArrayList<>(T.length);
        for (int i = 0; i < T.length; i++) {
            lista.add(new HashSet<>());
        }

        int capital = 0;
        for (int i = 0; i < T.length; i++) {
            if (T[i] == i) {
                capital = i;
                continue;
            }
            lista.get(T[i]).add(i);
        }
        lista.get(capital).remove(capital);

        for (int i = 0; i < A.length; i++) {
            A[i] = 0;
        }

        int counter = 1;

        recursion(lista.get(capital), 0);

//        for (int i = 0; i < T.length; i++) {
//
//            int found = i;
//            int j = 0;
//
//            do {
//                if (T[found] != capital) {
//                    found = T[found];
//                    j++;
//                } else {
//                    break;
//                }
//            } while (found != capital);
//            A[j]++;
//        }
//
//        A[0] = 1;*/

        for (int i = 0; i < T.length - 1; i++) {
            System.out.println(A[i]);
        }

        return A;
    }

    public void recursion(Set<Integer> set, int counter) {
        set.forEach(it -> {
            A[counter]++;
            recursion(lista.get(it), counter + 1);
        });
    }

    public int solution(int[] A, int[] B) {

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < A.length; i++) {
            if (A[i] == B[i]) {
                set.add(A[i]);
            }
        }

        int min = 1;

        while (set.contains(min)) {
            min++;
        }

        return min;
    }
}
