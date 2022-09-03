import java.io.*;


public class MyTest {
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        // Math.random()   [0,1)
        // Math.random() * N  [0,N)
        // (int)(Math.random() * N)  [0, N-1]
        int[] arr = new int[(int) (maxSize)];
        for (int i = 0; i < arr.length; i++) {
            // [-? , +?]
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static void main(String[] args) throws Exception {
        int size = 1000;
        OutputStream os = new FileOutputStream("D://test.txt");
        for(int i=0; i<size; i++){
            int[] arr = generateRandomArray(size, 20);
            for(int x = 0; x < size ; x++) {

                os.write( arr[x]);   // writes the bytes
            }
            byte[] bytes = {'\n'};
            os.write(bytes);
            os.close();







        }

    }
}
