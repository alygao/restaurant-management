package restaurantManagement1;

public abstract class Employee {
    private String name;
    private double pay;
    private String userID;
    private String password;

    Employee(){
        
    }
    
    Employee(String name, double pay, String userID, String password){
        this.name = name;
        this.pay = pay;
        this.userID = userID;
        this.password = password;
    }
}
