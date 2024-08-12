import java.awt.event.ItemEvent;
import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();

        list.insertAtBeginning(7);
        list.insertAtBeginning(6);
        list.insertAtBeginning(5);
        list.insertAtBeginning(4);

        list.display();
        System.out.println();

        list.insertAtPosition(3, 10);
        list.display();
        System.out.println();

        list.deleteAtPosition(3);
        list.display();
        System.out.println();

        //you can either use enhanced for loop to print the elements from the list
        for(int iterator : list){
            System.out.print(iterator + " ");
        }

        //or use the iterator class itself to print the elements from the list
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
}
    Node head;
    class Node{
        T data;
        Node next;
        Node(T value){
            data = value;
            next = null;
        }
    }
    LinkedList(){
        head = null;
    }
    public void insertAtBeginning(T value){
        Node newNode = new Node(value);
        if(head == null) {
           head = newNode;
        }else{
        newNode.next = head;
        head = newNode;
        }
    }
    public void display(){
        Node currentNode = head;
        while(currentNode != null){
            System.out.print(currentNode.data + " ");
            currentNode = currentNode.next;
        }
    }
    public void insertAtPosition(int position, T value){
        if(position == 0){
            insertAtBeginning(value);
            return;
        }
        Node newNode = new Node(value);
        Node currentNode = head;
        for(int iterator = 1; iterator < position; iterator++){
            currentNode = currentNode.next;
            if(currentNode == null){
                throw new IllegalArgumentException("Invalid Argument");
            }
        }
        newNode.next = currentNode.next;
        currentNode.next = newNode;
    }
    public void deleteAtPosition(int position){
        //handle edge case -> what if the list is empty?
        if(head == null){
            throw new IndexOutOfBoundsException("Deletion attempted on empty list.");
        }
        //handle edge case -> what if you are expected to delete the first node (no need to run the loop right?)
        if(position == 0){
            deleteAtBeginning();
            return; //make sure you doesn't want to run the rest of the code even after this particular part of deletion has been done.
        }
        Node currentNode = head;  //you need a previous node, current node, so that you can skip the current node by changing the reference of the previous node to current + 1;
        Node previousNode = null;
        for(int iterator = 1; iterator <= position; iterator++){
            if (currentNode.next == null) {
                throw new IndexOutOfBoundsException("Position is out of bounds");
            }
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
        previousNode.next = currentNode.next; //you are just making the list to skip the current node i.e, position to be deleted;
    }
    public void deleteAtBeginning(){
        if(head == null){
            throw new IndexOutOfBoundsException("Deletion attempted on empty list.");  //What if the function is called when there are no one in the list.
        }
        head = head.next;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            //implemented anonymous class instead of writing a separate one
            Node currentNode = head;

            // Check if there is a next node in the list
            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            // Return the data of the current node and move to the next node
            @Override
            public T next() {
                T value = currentNode.data;
                currentNode = currentNode.next;
                return value;
            }
        };
    }
}
