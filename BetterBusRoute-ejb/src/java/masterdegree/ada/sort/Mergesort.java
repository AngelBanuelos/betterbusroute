/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.sort;

/**
 *
 * @author Angel.Sahagun
 */
public class Mergesort {

    private long sortingTime = 0l;
    private int swapCount = 0;
    private int comparationCount = 0;
    private boolean printExecutionTime = false;

    public int[] sort(int[] array) {
        int[] workspace = new int[array.length];
        long startTime = System.currentTimeMillis();
        recMergeSort(workspace, 0, array.length - 1, array);
        long endime = System.currentTimeMillis();
        sortingTime = endime - startTime;
        if (printExecutionTime) {
            System.out.println("Merge Sorting time: " + sortingTime + " miliseconds.");
        }
        return array;
    }

    private int[] recMergeSort(int[] workspace, int min, int max, int[] array) {
        if (min == max) {
            comparationCount++;
            return array;
        } else {
            int middle = (min + max) / 2;
            recMergeSort(workspace, min, middle, array);
            recMergeSort(workspace, middle + 1, max, array);
            return mergeArrays(workspace, min, middle + 1, max, array);
        }
    }

    private int[] mergeArrays(int[] workspace, int min, int middle, int max, int[] array) {
        int j = 0;
        int low = min;
        int mid = middle - 1;
        int n = max - low + 1;
        comparationCount++;
        while (min <= mid && middle <= max) {
            comparationCount++;
            if (array[min] < array[middle]) {
                workspace[j++] = array[min++];
                swapCount++;
            } else {
                workspace[j++] = array[middle++];
                swapCount++;
            }
        }
        comparationCount++;
        while (min <= mid) {
            comparationCount++;
            workspace[j++] = array[min++];
            swapCount++;
        }

        comparationCount++;
        while (middle <= max) {
            comparationCount++;
            workspace[j++] = array[middle++];
            swapCount++;
        }
        for (int i = 0; i < n; i++) {
            array[low + i] = workspace[i];
        }
        return array;
    }

    public long getSortingTime() {
        return sortingTime;
    }

    public int getSwapCount() {
        return swapCount;
    }

    public int getComparationCount() {
        return comparationCount;
    }

    public void setPrintExecutionTime(boolean printExecutionTime) {
        this.printExecutionTime = printExecutionTime;
    }

    public String sort(String array) {
        char[] charV = array.toCharArray();
        int[] arrayInt = new int[array.toCharArray().length];
        for (int i = 0; i < charV.length; i++) {
            arrayInt[i] = charV[i];
        }
        sort(arrayInt);
        array = "";
        for (int i = 0; i < arrayInt.length; i++) {
            array += (char) arrayInt[i];
        }
        return array;
    }

    public Sortable[] sort(Sortable[] array) {
        Sortable[] workspace = new Sortable[array.length];
        long startTime = System.currentTimeMillis();
        recMergeSort(workspace, 0, array.length - 1, array);
        long endime = System.currentTimeMillis();
        sortingTime = endime - startTime;
        if (printExecutionTime) {
            System.out.println("Merge Sorting time: " + sortingTime + " miliseconds.");
        }
        return array;
    }

    private Sortable[] recMergeSort(Sortable[] workspace, int min, int max, Sortable[] array) {
        if (min == max) {
            comparationCount++;
            return array;
        } else {
            int middle = (min + max) / 2;
            recMergeSort(workspace, min, middle, array);
            recMergeSort(workspace, middle + 1, max, array);
            return mergeArrays(workspace, min, middle + 1, max, array);
        }
    }

    private Sortable[] mergeArrays(Sortable[] workspace, int min, int middle, int max, Sortable[] array) {
        int j = 0;
        int low = min;
        int mid = middle - 1;
        int n = max - low + 1;
        comparationCount++;
        while (min <= mid && middle <= max) {
            comparationCount++;
            if (array[min].getValue() < array[middle].getValue()) {
                workspace[j++] = array[min++];
                swapCount++;
            } else {
                workspace[j++] = array[middle++];
                swapCount++;
            }
        }
        comparationCount++;
        while (min <= mid) {
            comparationCount++;
            workspace[j++] = array[min++];
            swapCount++;
        }

        comparationCount++;
        while (middle <= max) {
            comparationCount++;
            workspace[j++] = array[middle++];
            swapCount++;
        }
        for (int i = 0; i < n; i++) {
            array[low + i] = workspace[i];
        }
        return array;
    }

}
