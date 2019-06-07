package restaurantManagement1;

import java.io.Serializable;
import java.util.AbstractList;

/**
 * Project - RestaurantManagement
 * DoublyLinkedList.java
 * A File to make the Doubly Linked List
 * @author Zaid Omer
 * @version June 14, 2019
 */

public class DoublyLinkedList<T> extends AbstractList<T> implements Serializable {
	
    private DoublyLinkedListNode<T> head;
    
	@Override
    /**
     * add item to doubly linked list
     * @param item , the item to add, of type T
     */
	public boolean add(T item) {
        DoublyLinkedListNode<T> nodeToAdd = new DoublyLinkedListNode<T>(item);
        boolean added = false;
        if(head == null){
            head = nodeToAdd;
            added = true;
        }else{
            DoublyLinkedListNode<T> tempNode = head;
            while(tempNode.getNext() != null){
                tempNode = tempNode.getNext();
            }
            nodeToAdd.setPrevious(tempNode);
            tempNode.setNext(nodeToAdd);
            added = true;
        }
        return added;
	}
    
	@Override
	public boolean remove(Object item) {
		return removeItem ((T)item);
	}
	
	@Override
	public T remove(int index) {
		T item = get(index);
		this.remove( item );
		return item;
	}

    /**
     * to remove item from doubly linked list
     * @param itemToRemove , the item to remove
     */
    public boolean removeItem(T itemToRemove){
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
        return removed;
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
    
	@Override
	public boolean contains(Object item) {
		return containsHelper((T)item);
	}

    /**
     * To see if an item is in the doubly liked list
     * @param item , the item to look for, of type T
     */
    public boolean containsHelper(T item){
        boolean contains = false;
        if(head == null){
            return false;
        }else if(head.getItem().equals(item)){
            return true;
        }else{
            DoublyLinkedListNode<T> tempNode = head;
            while(tempNode.getNext() != null){
                if(tempNode.getItem().equals(item)){
                    contains = true;
                }
                tempNode = tempNode.getNext();
            }
            return contains;
        }
    }

    /**
     * A method to clear the doubly liked list
     */
    public void clear(){
        head = null;
    }
    
	@Override
	public int indexOf(Object item) {
		return findIndexOf ((T)item);
	}

    /**
     * To find the index of an item in the doubly linked list
     * @param item , the item to find the index of
     * @return int the index number
     */
    public int findIndexOf(T item) {
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


	@Override
	public T get(int index) {
		int sizeCounter = 0;
		DoublyLinkedListNode<T> tempNode = head;
		if(head == null){
            return null;
        }
		while(tempNode!=null && sizeCounter <= index){
			if (sizeCounter == index) {
        		return tempNode.getItem();
        	}else {
        		tempNode = tempNode.getNext();
        		sizeCounter++;
        	}
        }
		return null;		
	}

}
