package so;

public class P3 {

    private int[] array;

    public P3(int[] array){
        this.array = array;
        Thread tq = new Thread(){
            @Override
            public void run() {
                long time = System.nanoTime();
                quickSort(array, 0, array.length-1);
                time = System.nanoTime() - time;
                System.out.println("time quick sort in nano seconds:" + time);
            }
        };
        Thread tm =new Thread(){
          public void run(){
            long time = System.nanoTime();
            mergeSort(array,0, array.length);
            time = System.nanoTime() - time;
            System.out.println("time merge sort in nano seconds:" + time);
          }
        };
        tq.start();
        tm.start();
    }

    private void quickSort(int[] array, int start, int end){
        if(start < end){
            int position = split(array, start, end);
            quickSort(array, start, position - 1);
            quickSort(array, position + 1, end);
        }
    }
    private int split(int[] array, int start, int end) {
        int pivo = array[start];
        int s = start + 1, e = end;
        while (s <= e) {
            if (array[s] <= pivo)
                s++;
            else if (pivo < array[e])
                e--;
            else {
                int troca = array[s];
                array[s] = array[e];
                array[e] = troca;
                s++;
                e--;
            }
        }
        array[start] = array[e];
        array[e] = pivo;
        return e;
    }

    private void mergeSort(int[] array,int start, int end){
        if(start < end - 1){
            int middle = (start + end)/2;
            mergeSort(array, start, middle);
            mergeSort(array, middle, end);
            merge(array, start, end, middle);
        }
    }

    private void merge(int[] array, int start, int end, int middle) {
        int[] cache = new int[end - start];
        int s = start;
        int m = middle;
        int pos = 0;
        while(s < middle && m< end){
            if(array[s] <= array[m]){
                cache[pos] = array[s];
                s+=1;
            }else{
                cache[pos] = array[m];
                m+=1;
            }
            pos+=1;
        }

        while(s < middle){
            cache[pos] = array[s];
            pos+=1;
            s+=1;
        }

        while (m < end){
            cache[pos] = array[m];
            pos+=1;
            m+=1;
        }

        for(pos = 0, s=start; s< end; s++,pos++){
            array[s] = cache[pos];
        }
    }
}
