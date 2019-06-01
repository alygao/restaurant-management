/**
 * Project - RestaurantManagement
 * DoublyLinkedList.java
 * A File to make the Doubly Linked List
 * @author Zaid Omer
 * @version June 14, 2019
 */

package restaurantManagement1;

public class DoublyLinkedList<T> {
    private DoublyLinkedListNode<T> head;

    /**
     * add item to doubly linked list
     * @param item , the item to add, of type T
     */
    public void add(T item){
        DoublyLinkedListNode<T> nodeToAdd = new DoublyLinkedListNode<T>(item);
        if(head == null){
            head = nodeToAdd;
        }else{
            DoublyLinkedListNode<T> tempNode = head;
            while(tempNode.getNext() != null){
                tempNode = tempNode.getNext();
            }
            nodeToAdd.setPrevious(tempNode);
            tempNode.setNext(nodeToAdd);
        }
    }

    /**
     * to remove item from doubly linked list
     * @param itemToRemove , the item to remove
     */
    public void remove(T itemToRemove){
        DoublyLinkedListNode<T> tempNode = head;
        boolean removed = false;
        if(head.getItem().equals(itemToRemove)){
            head = head.getNext();
            removed = true;
        }else{
            while(tempNode.getNext()!=null){
                tempNode = tempNode.getNext();
                if(tempNode.getItem().equals(itemToRemove)) {
                    //if it's the last item in the list
                    if (tempNode.getNext() == null) {
                        tempNode.setPrevious(null);
                    } else {
                        tempNode.setPrevious(tempNode.getNext());
                    }
                    removed = true;
                }
            }
        }
        if(!removed){
            System.out.println("Item not found, therefore not removed");
        }
    }

    /**
     * To calculate the size of the doubly linked list
     * @return int size , the size of the list
     */
    public int size() {
        DoublyLinkedListNode<T> tempNode = head;
        int size = 1;
        if(head == null){
            return 0;
        }else {
            while (tempNode.getNext() != null) {
                tempNode = tempNode.getNext();
                size++;
            }
            return size;
        }
    }

    /**
     * To see if an item is in the doubly liked list
     * @param item , the item to look for, of type T
     */
    public boolean contains(T item){
        boolean ifContains = false;
        if(head == null){
            return false;
        }else if(head.getItem().equals(item)){
            return true;
        }else{
            DoublyLinkedListNode<T> tempNode = head;
            while(tempNode.getNext() != null){
                if(tempNode.getItem().equals(item)){
                    ifContains = true;
                }
                tempNode = tempNode.getNext();
            }
            return ifContains;
        }
    }

    /**
     * A method to clear the doubly liked list
     */
    public void clear(){
        head = null;
    }

    /**
     * To find the index of an item in the doubly linked list
     * @param item , the item to find the index of
     * @return int the index number
     */
    public int indexOf(T item) {
        int index = 0;
        int indexToReturn = 0;
        boolean itemFound = false;
        DoublyLinkedListNode<T> tempNode = head;
        while(tempNode.getNext()!=null){
            if(tempNode.getItem().equals(item)){
                itemFound = true;
                indexToReturn = index;
            }
            tempNode = tempNode.getNext();
            index++;
        }
        if(itemFound){
            return indexToReturn;
        }else {
            return -1;
        }
    }

}
