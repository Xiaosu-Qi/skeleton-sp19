public class LinkedListDeque<T> {

    public class DLList {
        public DLList prev;
        public T item;
        public DLList next;

        public DLList(DLList m, T i, DLList n) {
            prev = m;
            item = i;
            next = n;
        }
    }

    public DLList sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new DLList(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public LinkedListDeque(T i) {
        DLList Node = new DLList(sentinel, i, sentinel);
        sentinel = new DLList(null, null, null);
        sentinel.prev = Node;
        sentinel.next = Node;
        size = 1;
    }

    public void addFirst(T item) {
        DLList Node = new DLList(sentinel, item, sentinel.next);
        sentinel.next.prev = Node;
        sentinel.next = Node;
        size += 1;
    }

    public void addLast(T item) {
        DLList Node = new DLList(null, item, sentinel);
        Node.prev = sentinel;
        sentinel.prev = Node;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        DLList l = sentinel;
        while (l.next != sentinel) {
            System.out.println(l.next.item + " ");
        }
        System.out.println("\n");
    }

    /**Removes and returns the item at the front of the deque. 
     * If no such item exists, returns null. */
    public T removeFirst() {
        DLList first = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        if (size > 0) {
            size -= 1;
        }
        return first.item;
    }

    /**Removes and returns the item at the back of the deque. 
     * If no such item exists, returns null. */
    public T removeLast() {
        DLList last = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        if (size > 0) {
            size -= 1;
        }
        return last.item;
    }

    public T get(int index) {
        DLList pointer = sentinel.next;
        while ((pointer.item != null) & (index > 0)) {
            index -= 1;
            pointer = pointer.next;
        }
        return pointer.item;
    }
    /**Creating a deep copy means that you create an entirely new LinkedListDeque, with the exact same items as other. 
    * However, they should be different objects, i.e. if you change other, the new LinkedListDeque you created should not change as well. */
    public LinkedListDeque(LinkedListDeque other) {
        DLList sentinel = new DLList(null, null, null);
        DLList pointer = other.sentinel.next;
        while (pointer.item != null) {
            this.addLast(pointer.item);
            pointer = pointer.next;
        }
    }
    /**Same as get, but uses recursion. */
    public T getRecursive(int index) {
        DLList pointer = this.sentinel;
        
        
        
    }


    public static void main(String[] args) {
        LinkedListDeque<Integer> L = new LinkedListDeque<Integer>(1);
        L.addFirst(2);
        System.out.println(L.sentinel.next.item);
        System.out.println(L.get(0));
        System.out.println(L.removeFirst());
    }


    
}