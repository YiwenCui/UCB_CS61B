public class LinkedListDeque<T> {
    public class Node{
        T item;
        Node prev;
        Node next;

        public Node(T item, Node prev, Node next){
            this.item = item;
            this.prev = prev;
            this.next = next;           
        }
    }

    Node sentinel;
    int size;

    public LinkedListDeque(){
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public void addFirst(T item){
        sentinel.next = new Node(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size++;
    }

    public void addLast(T item){
        sentinel.prev = new Node(item, sentinel.prev, sentinel);
        sentinel.next.next = sentinel.prev;
        size++;
    }

    public boolean isEmpty(){
        return (size == 0);
    }

    public int size(){
        return size;
    }

    public T get(int index){
        if (size == 0){
            return null;
        }

        Node p = sentinel;
        int i = 0;
        while (i <= index){
            p = p.next;
            i++;
        }
        return p.item;
    }

    public void printDeque(){
        Node p = sentinel;
        for (int i = 0; i < size; i++){
            p = p.next;
            System.out.print(p.item + " ");
        }
        System.out.println();
    }

    public T removeFirst(){
        T item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;

        return item;
    }

    public T removeLast(){
        T item = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;

        return item;
    }        
}
