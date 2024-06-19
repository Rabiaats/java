import java.util.Arrays;

public class MyList<T> {
    private T[] list;
    private final int capacity = 10;

    public MyList(int capacity){
        this.list = (T[]) new Object[capacity];
    }
    public MyList(){
        this.list = (T[]) new Object[this.capacity];
    }
    public int size(){
        int count = 0;
        for (T i : this.list){
            if (i != null){
                count++;
            }
        }
        return count;
    }
    public int getCapacity(){
        return list.length;
    }
    public void add(T data) {
        int i = size();
        if (i == getCapacity()){
            T[] tmp = (T[]) new Object[this.list.length * 2];
            for (int t = 0; t < this.list.length; t++){
                tmp[t] = this.list[t];
            }
            this.list = tmp;
        }
        this.list[i] = data;
    }
    public T get(int index){
        if (index < this.list.length){
            return this.list[index];
        }else {
            return null;
        }
    }
    public T remove(int index){
        if (index < this.list.length) {
            if (index == this.list.length - 1) {
                this.list[index] = null;
            } else {
                for (int i = index; i < this.list.length - 2; i++) {
                    this.list[i] = this.list[i + 1];
                }
            }

            return this.list[index];
        }
        return null;
    }
    public T set(int index, T data){
        if (index < this.list.length){
            this.list[index] = data;
            return data;
        }
        return null;
    }

    public String toString(){
        String str = "[";
        for (T i : this.list){
            if (i == this.list[this.list.length - 1] && i != null){
                str += i + "]";
            }else if (i != null){
                str += i + ", ";
            }
        }
        if (str.charAt(str.length() - 1) != ']'){
            str += "]";
        }
        return str;
    }
    public int indexOf(T data){
        for (int i = 0; i < size(); i++){
            if (this.list[i] == data){
                return i;
            }
        }
        return (-1);
    }
    public int lastIndexOf(T data){
        for (int i = (size() - 1); i >=0; i--){
            if (this.list[i] == data){
                return i;
            }
        }
        return (-1);
    }
    public boolean isEmpty(){
        if (size() != 0) return true;
        return false;
    }
    public T[] toArray(){
        return this.list;
    }
    public void clear(){
        this.list = (T[]) new Object[this.capacity];
    }
    public MyList<T> subList(int start, int finish){
        MyList<T> tmpList = new MyList<>();
        for (int i = start; i < finish; i++){
            tmpList.add(this.list[i]);
        }
        return tmpList;
    }
    public boolean contains(T data){
        for (T i : this.list){
            if(i == data)
                return true;
        }
        return false;
    }
}
