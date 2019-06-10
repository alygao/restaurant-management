package restaurantManagement1;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Project - RestaurantManagement Queue.java A File to make the queue
 * 
 * @author Zaid Omer
 * @version June 14, 2019
 */

class Queue<T> implements Serializable {
	protected QueueNode<T> head;

	/**
	 * adds node to the queue
	 * 
	 * @param nodeToAdd the node to add to the queue, of type QueueNode
	 */
	public void enqueue(QueueNode<T> nodeToAdd) {
		if (head.getItem() == null) {
			head = nodeToAdd;
		} else {
			QueueNode<T> tempNode = head;
			while (tempNode.getNext() != null) {
				tempNode = tempNode.getNext();
			}
			tempNode.setNext(nodeToAdd);
		}
	}

	/**
	 * adds the item passed through to the queue (as a node)
	 * 
	 * @param item , the item to add to the queue, of type T
	 */
	public void enqueue(T item) {
		QueueNode<T> nodeToAdd = new QueueNode<T>(item);
		if (head == null) {
			head = nodeToAdd;
		} else {
			QueueNode<T> tempNode = head;
			while (tempNode.getNext() != null) {
				tempNode = tempNode.getNext();
			}
			tempNode.setNext(nodeToAdd);
		}
	}

	/**
	 * removes node from the queue
	 * 
	 * @return T the item in the node being removed
	 */
	public T dequeue() {
		QueueNode<T> previousRoot = head;
		head = head.getNext();
		return previousRoot.getItem();
	}

	/**
	 * finds the size of the queue
	 * 
	 * @return int the size calculated
	 */
	public int size() {
		int count = 1;
		QueueNode<T> tempNode = head;
		if (head == null) {
			return 0;
		} else {
			while (tempNode.getNext() != null) {
				count++;
				tempNode = tempNode.getNext();
			}
			return count;
		}
	}

	public boolean isEmpty() {
		if (head == null) {
			return true;
		} else {
			return false;
		}
	}

	public Iterator iterator() {
		return new QueueIterator();
	}

	class QueueIterator implements Iterator<T> {

		private QueueNode<T> currentNode = head;

		@Override
		public boolean hasNext() {
			return currentNode != null;
		}

		@Override
		public T next() {
			T item = null;
			if (currentNode != null) {
				item = currentNode.getItem();
				currentNode = currentNode.getNext();
			}
			return item;
		}

	}

}