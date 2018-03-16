/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.search;

/**
 *
 * @author angel_banuelos
 */
public class BinarySearch {

    public BinarySearch() {
    }

    public int search(int[] array, int value) {
        return searchR(array, 0, array.length / 2, array.length, value);
    }

    private int searchR(int[] array, int start, int half, int end, int value) {
        if (value > array[end - 1] || value < array[start]) {
            return -1;
        }
        if (array[half] == value) {
            return half;
        }
        if (value < array[half]) {
            end = half;
            half /= 2;
            return searchR(array, start, half, end, value);
        } else {
            start = half;
            half += (end - start) / 2;
            return searchR(array, start, half, end, value);
        }
    }

    /**
     * Metodo para metodos que implementan Serching interface.
     *
     * @param array Arreglo en donde buscara el elemento
     * @param start Variable para la particion del arrglo inicial
     * @param half La mitad el arreglo
     * @param end el final del arreglo.
     * @param value Valor a buscar
     * @return regresa el indice del elemento a buscar.
     */
    private int searchR(Searching[] array, int start, int half, int end, int value) {
        if (value > array[end - 1].getValue() || value < array[start].getValue()) {
            return -1;
        }
        if (array[half].getValue() == value) {
            return half;
        }
        if (value < array[half].getValue()) {
            end = half;
            half /= 2;
            return searchR(array, start, half, end, value);
        } else {
            start = half;
            half += (end - start) / 2;
            return searchR(array, start, half, end, value);
        }
    }

    public int search(Searching[] array, int value) {
        return searchR(array, 0, array.length / 2, array.length, value);
    }

}
