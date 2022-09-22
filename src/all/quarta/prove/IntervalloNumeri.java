package all.quarta.prove;

public class IntervalloNumeri {
    private ArrayInt arr;
    public IntervalloNumeri(int dim){
        arr=new ArrayInt(dim);
    }
    public IntervalloNumeri(int[] in){
        arr=new ArrayInt(in);
    }
    public IntervalloNumeri(ArrayInt arr){
        this.arr=arr;
    }
    public void add(int num){
        arr.add(num);
    }
    public void remove(int index){
        arr.remove(index);
    }
    public void sort(){
        arr=new ArrayInt(arr.bubbleSort());
    }
    public IntervalloNumeri unisciNOD(IntervalloNumeri in){
        IntervalloNumeri out = new IntervalloNumeri(arr.toArray());
        out.arr.extend(in.arr);
        return out;
    }
    public IntervalloNumeri unisciNOND(IntervalloNumeri in){
        ArrayInt out=new ArrayInt(in.arr.size()+arr.size());
        for(int i=0;i<out.size();i++){
            if(out.indexOf(out.get(i))!=i)
                out.remove(i);
        }
        return new IntervalloNumeri(out);
    }
    public IntervalloNumeri unisciOD(IntervalloNumeri in){
        ArrayInt out=new ArrayInt(in.arr.size()+arr.size());
        int indexIn=0,indexThis=0;
        for(int i=0;i<out.dimF();i++){
            if(arr.size()>indexThis&&(in.arr.size()==indexIn||arr.get(indexThis)<in.arr.get(indexIn))){
                out.add(arr.get(indexThis));
                indexThis++;
            }else{
                out.add(in.arr.get(indexIn));
                indexIn++;
            }
        }
        return new IntervalloNumeri(out);
    }
    public IntervalloNumeri unisciOND(IntervalloNumeri in){
        ArrayInt out=new ArrayInt(in.arr.size()+arr.size());
        int indexIn=0,indexThis=0,i=0;
        while(indexIn<in.arr.size()||indexThis<arr.size()){
            if(arr.size()>indexThis&&(in.arr.size()==indexIn||arr.get(indexThis)<in.arr.get(indexIn))){
                out.add(arr.get(indexThis));
                indexThis++;
            }else{
                if(in.arr.get(indexIn)<arr.get(indexThis)){
                    out.add(in.arr.get(indexIn));
                    indexIn++;
                }else{
                    out.add(arr.get(indexThis));
                    indexThis++;
                    indexIn++;
                }
            }
            i++;
        }
        return new IntervalloNumeri(out);
    }
    public IntervalloNumeri getCopy(){
        return new IntervalloNumeri(arr.toArray());
    }
}

