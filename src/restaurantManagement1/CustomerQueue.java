package restaurantManagement1;

public class CustomerQueue<T extends Customer> extends Queue<T> {

	/**
	 * removes node from the queue
	 * 
	 * @return T the item in the node being removed
	 */
	public T dequeue(int numPeople) {
		QueueNode<T> tempNode = head;
		QueueNode<T> previousNode = null;
		System.out.println(numPeople);
		while (tempNode != null) {
			if (tempNode.getItem().getNumPeople() <= numPeople) {
				if (previousNode == null) {
					head = tempNode.getNext();
				} else {
					previousNode.setNext(tempNode.getNext());
				}
				return tempNode.getItem();
			}

			previousNode = tempNode;
			tempNode = tempNode.getNext();
		}
		return null;
	}

	public void dequeue(Customer customer) {
		QueueNode<T> tempNode = head;
		QueueNode<T> previousNode = null;
		while (tempNode != null) {
			if (tempNode.getItem() == customer) {
				if (previousNode == null) {
					head = tempNode.getNext();
				} else {
					previousNode.setNext(tempNode.getNext());
				}
				return;
			}

			previousNode = tempNode;
			tempNode = tempNode.getNext();
		}
	}
}
