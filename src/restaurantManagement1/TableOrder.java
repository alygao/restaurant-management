package restaurantManagement1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TableOrder implements Serializable {
	private Table table;
	private TableOrder self = this;
	private List<TableOrderItem> orderItems = new ArrayList<>();
	private Waiter waiter;
	
	public List<TableOrderItem>  getOrderItems(){
		return orderItems;
	}

	public void setOrderItems(List<TableOrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public TableOrder getSelf(){
		return self;
	}
}
