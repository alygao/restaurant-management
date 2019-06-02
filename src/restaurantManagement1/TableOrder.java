package restaurantManagement1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TableOrder implements Serializable {
	private Table table;
	private List<TableOrderItem> orderItems = new ArrayList<>();
	private Waiter waiter;
	
	
	

}
