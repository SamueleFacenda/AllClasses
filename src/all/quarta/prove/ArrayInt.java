package all.quarta.prove;

public class ArrayInt {
    private int[] arr;
    private int dimL;
    public ArrayInt(int len){
        arr=new int[len];
        dimL=0;
    }
    public ArrayInt(int[] arr){
        dimL=arr.length;
        this.arr=new int[dimL];
        for(int i=0;i<dimL;i++)
            this.arr[i]=arr[i];
    }
    public int size(){
        return dimL;
    }
    public boolean isEmpty(){
        return dimL==0;
    }
    public void add(int v){
        if(dimL==arr.length){
            int[] second=new int[dimL+dimL/10];
            for(int i=0;i<dimL;i++)
                second[i]=arr[i];
            arr=second;
        }
        arr[dimL]=v;
        dimL++;
    }
    public void clear(){
        for(int i=0;i<dimL;i++)
            arr[i]=0;
        dimL=0;
    }
    public int get(int i){
        return arr[i];
    }
    public int indexOf(int num){
        int i=0;
        while(arr[i]!=num&&i<dimL)
            i++;
        if(i==dimL)
            return -1;
        else
            return i;
    }
    public void remove(int e){
        shift(e);
        dimL--;
    }
    private void shift(int index){
        for(int i=index;i<dimL;i++)
            arr[i]=arr[i+1];
    }
    @Override
    public String toString(){
        String out="";
        for(int num:arr)
            out+=num+" ";
        return out;
    }
    public int max(){
        int max=0;
        for(int i=0;i<dimL;i++){
            if(arr[i]>max)
                max=arr[i];
        }
        return max;
    }
    public int min(){
        int min=arr[0];
        for(int i=0;i<dimL;i++){
            if(arr[i]<min)
                min=arr[i];
        }
        return min;
    }
    public int[] bubbleSort(){
        int[] ordi=new int[arr.length];
        for(int i=0;i<arr.length;i++)
            ordi[i]=arr[i];
        int ext;
        for(int i=0;i<arr.length;i++){
            for(int e=0;e<arr.length-1;e++){
                if(ordi[e]>ordi[e+1]){
                    ext=ordi[e];
                    ordi[e]=ordi[e+1];
                    ordi[e+1]=ext;
                }
            }
        }
        return ordi;
    }
    public int[] selectionSort(){
        int[] ordi=new int[arr.length];
        for(int i=0;i<arr.length;i++)
            ordi[i]=arr[i];
        int ext, num=0, min;
        for(int i=0;i<arr.length-1;i++){
            min=num;
            for(int e=num;e<arr.length;e++){
                min=(ordi[e]<ordi[min])?e:min;
            }
            ext=ordi[num];
            ordi[num]=ordi[min];
            ordi[min]=ext;
            num++;
        }
        return ordi;
    }
    public void extend(ArrayInt in){
        int[] second=new int[dimL+in.dimL];
        for(int i=0;i<dimL;i++)
            second[i]=arr[i];
        for(int i=dimL;i<dimL+in.dimL;i++)
            second[i]=in.arr[i];
    }
    public ArrayInt unisci(ArrayInt in){
        int[] second=new int[dimL+in.dimL];
        for(int i=0;i<dimL;i++)
            second[i]=arr[i];
        for(int i=dimL;i<dimL+in.dimL;i++)
            second[i]=in.arr[i];
        ArrayInt out=new ArrayInt(second.length);
        out.arr=second;
        return out;
    }
    public void insert(int index,int num){
        arr[index]=num;
    }
    public int dimF(){return arr.length;}
    public int[] toArray(){return arr;}
    public void resize(){
        int[] second=new int[dimL];
        for(int i=0;i<dimL;i++)
            second[i]=arr[i];
        arr=second;
    }
}
