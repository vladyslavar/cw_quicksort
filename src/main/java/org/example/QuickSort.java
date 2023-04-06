package org.example;
import java.util.Arrays;
public class QuickSort {
    public long executeTime;
    public int[] array;
    public QuickSort(int size)
    {
        array = new int[size];
        for(int i = 0; i < size; i++){
            array[i] = (int)(Math.random() * 100);
        }
        long start = System.nanoTime();
        quickSort(array, 0, size - 1);
        long end = System.nanoTime();
        executeTime = (end - start) / 1000;
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
    private int partition(int[] array, int left, int right, int pivot){
        int pivot_value = array[pivot];
        swap(array, pivot, right);
        int store_index = left;
        for(int i = left; i < right; i++){
            if(array[i] < pivot_value){
                swap(array, i, store_index);
                store_index++;
            }
        }
        swap(array, store_index, right);
        return store_index;
    }
    private void swap(int[] array, int index1, int index2){
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
