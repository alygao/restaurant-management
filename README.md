Restaurant Planner

Features/Functions

Basics (Restaurant Layout, Reservations, Orders, Billing & Payment)

**Reservation Management**
- A calendar to organize reservations for different dates (set a limit to how far they can reserve // maybe a month)
    - Reserved table is held for 3 hours
    - 50% of tables can do reservation (done by confiugration for Tables)
    - Set a limit to number of tables reserved for (Table’s attributes)
    - Compared to system time to decide whether or not other customers can sit there (Table’s placeSeat() method)

**Restaurant Management**
- A room (Restaurant class) overview to organize the restaurant layout (UI). Each table is given a table #, corresponding with the actual table numbers in the restaurant
- Pop-up window which appears when empty table is right-clicked, to write name of person reserving, time, party size
    - On the day of the reservation, the table layout should already have a table assigned to the name that is being reserved under) - UI
    - Reservations are also based on time too
- Another pop-up which only appears on the current day, which when a reserved table is clicked, you can get the person’s name and number of people

**Restaurant Management**
- A menu list (in Queue - FIFO) and place order and passed to chef
- Waiter needs to deliver the meal
- When a customer comes in, waiter will use system to see if there’s space. Once they take a table, it becomes “occupied.”
    - Set other boolean values to see if they have “ordered,” “in progress/paid/print receipt,” and “left”
    - Save order to “historical Orders”
    - Generate bill once all meals have been delivered
- Keep a waiting list (maybe allot a certain amount of time for people)
    - Once the boolean values change, we subtract minutes from the waiting time ??
- Billing and Payment → Split or group bills and transfer items, Divide items between customers
    - Manage tips (distribution for the team) ← percent, dollar amount, or no tip
- Assign waiters for certain tables (should be a limit to how many they can manage)
    - This can be tied to number of employees scheduled if there are too many or too little waiters
        - We will need timeframes (Lunch, Dinner)
        - Same with chefs
- Waiters/managers have to login to edit things

**Additional Stuff**
- Money management (see revenue and expenses)
- Display a graph of the most popular times people come to the restaurant
- Employee scheduling
- Display daily/weekly reports
- Display most popular orders
- Analysis can then be downloaded as excel
- Ordering new materials (kitchen supplies/food) // inventory management
- The ability to manage multiple restaurants???
- https://peachworks.com/ ← check this out for other functions
- Daily log of tasks
- Take-out / delivery options
- Student discounts for Wloo kids 😂

**Classes (Objects)**
- Table (table #, number of seats, can be reserved, current order, customer name, bill, status)
- Employees ABSTRACT CLASS (name, pay)
- Waiter (number of customers serving, tips)
- Password
- Manager
- Password
- Chef (number of dishes, role, list of orders)
- Customer (name)
- Reservation (time, name)
- Menu (ArrayList/Linked List of dishes)
- Waiting List (ArrayList/Linked List of customers)
- Menu Items /Dishes (prices, maybe time to make)
- Room Panel/restaurant (array of tables)
- findTableForReservartion/walk-in
- Place Seat ← for both types
- Orders
- Historical Orders list (waiter, order items, (sub)total, tax,  tip, table number, date and time, customer name, payment method)









