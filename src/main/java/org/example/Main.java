package org.example;

public class Main {
    public static void main(String[] args) {
        int size;

        /*
        testing(1000);
        testing(10000);
        testing(100000);
        testing(250000);
        testing(500000);
        testing(750000);*/

        testingBottom(100000, 1);
        testingBottom(100000, 100);
        testingBottom(100000, 1000);
        testingBottom(100000, 5000);
        testingBottom(100000, 10000);
        testingBottom(100000, 50000);
        testingBottom(100000, 100000);

    }
    private static void testing(int size)
    {
        System.out.println("For array size " + size + ":");
        long wholeTime = 0;
        long parallelTime = 0;
        for(int i = 0; i < 100; i++){
            QuickSort qs = new QuickSort(size);
            wholeTime += qs.executeTime;
            ParallelQuickSort pqs = new ParallelQuickSort(size, size / 1000);
            parallelTime += pqs.executeTime;
        }
        System.out.println("Average time consist: " + wholeTime / 100 + "ms");
        System.out.println("Average time parallel: " + parallelTime / 100 + "ms");
    }
    private static void testingBottom(int size, int bottom)
    {
        long parallelTime = 0;
        for(int i = 0; i < 100; i++){
            ParallelQuickSort pqs = new ParallelQuickSort(size, bottom);
            parallelTime += pqs.executeTime;
        }
        System.out.println("Average time with bottom " + bottom + ": " + parallelTime / 100 + "ms");
    }
}