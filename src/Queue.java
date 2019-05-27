/**
 * Project - RestaurantManagement
 * Queue.java
 * A File to make the queue
 * @author Zaid Omer
 * @version June 14, 2019
 */

class Queue<T>{
    private QueueNode<T> root;

    /**
     * Queue constructor
     */
    Queue(){
        root = new QueueNode<T>(null);
    }

    /**
     * adds node to the queue
     * @param nodeToAdd the node to add to the queue, of type QueueNode
     */
    public void enqueue(QueueNode<T> nodeToAdd){
        if(root.getItem() == null){
            root = nodeToAdd;
        }else{
            QueueNode<T> tempNode = root;
            while(tempNode.getNext() != null){
                tempNode = tempNode.getNext();
            }
            tempNode.setNext(nodeToAdd);
        }
    }

    /**
     * adds the item passed through to the queue (as a node)
     * @param item , the item to add to the queue, of type T
     */
    public void enqueue(T item){
        QueueNode<T> nodeToAdd = new QueueNode<T>(item);
        if(root.getItem() == null){
            root = nodeToAdd;
        }else{
            QueueNode<T> tempNode = root;
            while(tempNode.getNext() != null){
                tempNode = tempNode.getNext();
            }
            tempNode.setNext(nodeToAdd);
        }
    }

    /**
     * removes node from the queue
     * @return T the item in the node being removed
     */
    public T dequeue(){
        QueueNode<T> previousRoot = root;
        root = root.getNext();
        return previousRoot.getItem();
    }

    /**
     * finds the size of the queue
     * @return int the size calculated
     */
    public int size(){
        int count = 1;
        QueueNode<T> tempNode = root;
        if(root.getItem() == null){
            return 0;
        }else{
            while(tempNode.getNext() != null){
                count++;
                tempNode = tempNode.getNext();
            }
            return count;
        }       
    }

    
}