package so;

public class Main {
    public static void main(String[] args) {
        int[] array = new int[1000];
        for(int x = 0; x< array.length; x++){
            array[x] = (int) (Math.random() * array.length);
        }
        P3 p3 = new P3(array);
    }
}
