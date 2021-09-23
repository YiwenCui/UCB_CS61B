public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst = 0;
    private int nextLast = 1;
    private int capacity;
    private int RESIZE_FACTOR = 2;
    private double USAGE_RATIO = 0.25;
    private int CAPACITY_BASE = 16;

    public ArrayDeque(int capacity){
        items = (T[]) new Object[capacity];
        this.size = 0;
        this.capacity = capacity;
    }

    public ArrayDeque() {

        /*items = (T[]) new Object[8];
        this.size = 0;
        this.capacity = 8;
        same to to following code*/       
        this(8);
    }

    public ArrayDeque(ArrayDeque other){
        this(other.size()*2);
        for (int i = 0; i<other.size(); i++){
            addLast((T) other.get(i));
        }
    }
    
    
    public boolean isFull(){
       return this.size == this.capacity;
    }
    public boolean isEmpty(){
        return this.size == 0;
    }

    public int size(){
        return this.size;
    }

    public void addLast(T item){
        if (isFull()){

        }

        else{
            items[nextLast]= item;
            size++;
            nextLast = indexAdd(nextLast,1);
        }
    }

    public void addFirst(T item){
        if (isFull()){

        }
        else{
            items[nextFirst] =item;
            size++;
            nextFirst = indexAdd(nextFirst,-1);
        }
    }

    public T removeFirst(){
        if (isEmpty()){
            return null;
        }
        else{
            nextFirst = indexAdd(nextFirst, 1);
            T first = items[nextFirst];
            items[nextFirst] = null;
            size--;
            return first;
        }       
    }
    public T removeLast(){
        if (isEmpty()){
            return null;
        }
        else{
            nextLast = indexAdd(nextLast, -1);
            T last = items[nextLast];
            items[nextLast] = null;
            size--;
            return last;
        }
    }

    private int indexAdd(int index, int num){
        index+=num;
        if (index<0){
            index+=capacity;
        }

        if (index>=capacity){
            index -= capacity;
        }
        return index;
    }

    public T get(int index){
        if (index>size){
            return null;
        }
        else{
            return items[indexAdd(nextFirst,1+index)];
        }
    }

    private void resizeItems(int newCapacity){
        T[] newItems = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newItems[i] = items[indexAdd(nextFirst, 1 + i)];
        }
        items = newItems;
        nextFirst = newCapacity - 1;
        nextLast = size;       
    }

    private void shrinkArray() {
        resizeItems(capacity / 2);
        capacity /= 2;
    }

    private void expandArray(int factor) {
        resizeItems(capacity * factor);
        capacity *= factor;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }
    

}
