// Strategy Interface
interface SortStrategy {
    void sort(int[] array);
}

// Concrete Strategy 1: Bubble Sort
class BubbleSort implements SortStrategy {
    public void sort(int[] array) {
        int n = array.length;
        System.out.println("Using Bubble Sort:");
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}

// Concrete Strategy 2: Quick Sort
class QuickSort implements SortStrategy {
    public void sort(int[] array) {
        System.out.println("Using Quick Sort:");
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high], i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
            }
        }
        int temp = arr[i + 1]; arr[i + 1] = arr[high]; arr[high] = temp;
        return i + 1;
    }
}

// Concrete Strategy 3: Merge Sort
class MergeSort implements SortStrategy {
    public void sort(int[] array) {
        System.out.println("Using Merge Sort:");
        mergeSort(array, 0, array.length - 1);
    }

    private void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private void merge(int[] arr, int l, int m, int r) {
        int[] left = java.util.Arrays.copyOfRange(arr, l, m + 1);
        int[] right = java.util.Arrays.copyOfRange(arr, m + 1, r + 1);

        int i = 0, j = 0, k = l;
        while (i < left.length && j < right.length)
            arr[k++] = (left[i] <= right[j]) ? left[i++] : right[j++];

        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }
}

// Context Class
class Sorter {
    private SortStrategy strategy;

    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public void sort(int[] array) {
        if (strategy == null) {
            throw new IllegalStateException("Strategy not set");
        }
        strategy.sort(array);
        System.out.println("Sorted array: " + java.util.Arrays.toString(array));
    }
}

// Main/Test Class
public class StrategyPatternSortingDemo {
    public static void main(String[] args) {
        int[] array1 = {5, 3, 8, 4, 2};
        int[] array2 = {9, 1, 6, 3, 7};
        int[] array3 = {10, 2, 5, 3, 8};

        Sorter sorter = new Sorter();

        sorter.setStrategy(new BubbleSort());
        sorter.sort(array1);

        sorter.setStrategy(new QuickSort());
        sorter.sort(array2);

        sorter.setStrategy(new MergeSort());
        sorter.sort(array3);
    }
}
