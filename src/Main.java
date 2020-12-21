import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();
        int[] data = new int[100000];
        int j=0;
        for(int i=99999;i>0;i--){
            data[++j] = i;
        }
        for(int i=1;i<data.length;i++){
            data[i] = random.nextInt(100000);
        }
        long startTime=System.currentTimeMillis();
        //QuickSort.sort(data);
        //DualPivotQuickSort.sort(data);
        QuantileDualPivotQuickSort.sort(data);
        long endTime = System.currentTimeMillis();
        System.out.println("排序运行时间： "+(endTime-startTime)+"ms");

    }
}
