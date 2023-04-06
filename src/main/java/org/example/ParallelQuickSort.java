package org.example;

import java.util.Arrays;import java.lang.reflect.Array;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
public class ParallelQuickSort {
    public int[] array;
    private int numThreads;
    private int bottom;
    public long executeTime;
    public ParallelQuickSort(int size, int bottom){
        array = new int[size];
        for(int i = 0; i < size; i++){
            array[i] = (int)(Math.random() * 100);
        }
        this.bottom = bottom;
        numThreads = Runtime.getRuntime().availableProcessors();
        ForkJoinPool pool = new ForkJoinPool(numThreads);
        QuickSortTask task = new QuickSortTask(array, 0, array.length - 1, bottom);
        long start = System.nanoTime();
        pool.invoke(task);
        long end = System.nanoTime();
        executeTime = (end - start) / 1000;
    }
}
class QuickSortTask extends RecursiveAction{
    private int[] array;
    private int left;
    private int right;
    private int bottom;
    public QuickSortTask(int[] array, int left, int right, int bottom){
        this.array = array;
        this.left = left;
        this.right = right;
        this.bottom = bottom;
    }
    @Override
    protected void compute() {
        if(right - left < bottom){
            quickSort(array, left, right);
        }
        else{
            int pivot = choosePivot(array, left, right);
            pivot = partition(array, left, right, pivot);
            QuickSortTask leftTask = new QuickSortTask(array, left, pivot, bottom);
            QuickSortTask rightTask = new QuickSortTask(array, pivot + 1, right, bottom);
            leftTask.fork();
            rightTask.compute();
            leftTask.join();
        }
    }
    private void quickSort(int[] array, int left, int right){
        if(left < right){
            int pivot = choosePivot(array, left, right);
            pivot = partition(array, left, right, pivot);
            quickSort(array, left, pivot);
            quickSort(array, pivot + 1, right);
        }
    }
    private int choosePivot(int[] array, int left_index, int right_index){
        int left_value = array[left_index];
        int right_value = array[right_index];
        int middle = (left_index + right_index) / 2;
        int middle_value = array[middle];

        if(left_value <= middle_value && middle_value <= right_value){
            return middle;
        }
        else if(middle_value <= left_value && left_value <= right_value){
            return left_index;
        }
        else{
            return right_index;
        }
    }
    private int partition(int[] array, int left_index, int right_index, int pivot_index){
        int pivot_value = array[pivot_index];
        swap(array, pivot_index, right_index);
        int store_index = left_index;
        for(int i = left_index; i < right_index; i++){
            if(array[i] < pivot_value){
                swap(array, i, store_index);
                store_index++;
            }
        }
        swap(array, store_index, right_index);
        return store_index;
    }
    private void swap(int[] array, int index1, int index2){
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
