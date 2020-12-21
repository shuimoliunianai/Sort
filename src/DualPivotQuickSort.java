import java.util.Arrays;

public class DualPivotQuickSort {

    public static void sort(int[] a) {
        sort(a, 0, a.length);
    }

    public static void sort(int[] a, int fromIndex, int toIndex) {
        rangeCheck(a.length, fromIndex, toIndex);
        dualPivotQuicksort(a, fromIndex, toIndex - 1);
        //result(a);
    }

    public static void result(int[] a){
        System.out.println(Arrays.toString(a));
    }

    private static void rangeCheck(int length, int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
        }
        if (fromIndex < 0) {
            throw new ArrayIndexOutOfBoundsException(fromIndex);
        }
        if (toIndex > length) {
            throw new ArrayIndexOutOfBoundsException(toIndex);
        }
    }

    private static void swap(int[] data ,int a,int b){
        int x = 0;
        if(data[a] > data[b]){
            x = data[a];
            data[a] = data[b];
            data[b] = x;
        }
    }

    private static void dualPivotQuicksort(int[] data, int left, int right) {
        int length = right - left;
        int x;
        if (length < TINY_SIZE) { // insertion sort on tiny array
            for (int i = left + 1; i <= right; i++) {
                for (int j = i; j > left && data[j] < data[j - 1]; j--) {
                    x = data[j - 1];
                    data[j - 1] = data[j];
                    data[j] = x;
                }
            }
            return;
        }
// median indexes




// pivots: [ < pivot1 | pivot1 <= && <= pivot2 | > pivot2 ]
        int pivot1 = data[left];
        int pivot2 = data[right];
        boolean diffPivots = pivot1 != pivot2;
// center part pointers
        int less = left + 1;
        int great = right - 1;
// sorting
        if (diffPivots) {
            for (int k = less; k <= great; k++) {
                x = data[k];
                if (x < pivot1) {
                    data[k] = data[less];
                    data[less++] = x;
                } else if (x > pivot2) {
                    while (data[great] > pivot2 && k < great) {
                        great--;
                    }
                    data[k] = data[great];
                    data[great--] = x;
                    x = data[k];
                    if (x < pivot1) {
                        data[k] = data[less];
                        data[less++] = x;
                    }
                }
            }
        } else {
            for (int k = less; k <= great; k++) {
                x = data[k];
                if (x == pivot1) {
                    continue;
                }
                if (x < pivot1) {
                    data[k] = data[less];
                    data[less++] = x;
                } else {
                    while (data[great] > pivot2 && k < great) {
                        great--;
                    }
                    data[k] = data[great];
                    data[great--] = x;
                    x = data[k];
                    if (x < pivot1) {
                        data[k] = data[less];
                        data[less++] = x;
                    }
                }
            }
        }
// swap
        data[left] = data[less - 1];
        data[less - 1] = pivot1;
        data[right] = data[great + 1];
        data[great + 1] = pivot2;
// left and right parts
        dualPivotQuicksort(data, left, less - 2);
        dualPivotQuicksort(data, great + 2, right);
// equal elements
        if (great - less > length - DIST_SIZE && diffPivots) {
            for (int k = less; k <= great; k++) {
                x = data[k];
                if (x == pivot1) {
                    data[k] = data[less];
                    data[less++] = x;
                } else if (x == pivot2) {
                    data[k] = data[great];
                    data[great--] = x;
                    x = data[k];
                    if (x == pivot1) {
                        data[k] = data[less];
                        data[less++] = x;
                    }
                }
            }
        }
// center part
        if (diffPivots) {
            dualPivotQuicksort(data, less, great);
        }
    }

    private static final int DIST_SIZE = 13;
    private static final int TINY_SIZE = 17;
}
