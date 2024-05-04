package com.jeremy.bst;

public class EmployeeBST extends BinarySearchTree<Employee> {
    public static void main(String[] args) {
        EmployeeBST employeeBST = new EmployeeBST();

        // Insert employees
        employeeBST.insert(new Employee(1237, "Jeremy", "HR"));
        employeeBST.insert(new Employee(1236, "Stephen", "Development"));
        employeeBST.insert(new Employee(1235, "Joe", "Sales"));
        employeeBST.insert(new Employee(1234, "Ryan", "Finance"));

        // Print employee records
        System.out.println("Employee Records:");
        employeeBST.printEmployeeRecords();

        // Search for an employee
        Employee searchEmployee = new Employee(1236, "", "");
            if (employeeBST.contains(searchEmployee)) {
            System.out.println("\nEmployee with ID 1236 found.");
        } else {
            System.out.println("\nEmployee with ID 1236 not found.");
        }


        // Delete an employee
        employeeBST.remove(searchEmployee);
        System.out.println("\nEmployee Records after deletion:");
        employeeBST.printEmployeeRecords();
    }
}
