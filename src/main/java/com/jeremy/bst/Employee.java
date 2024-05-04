package com.jeremy.bst;

/**
 * Employee has three fields
 * @employeeId
 * @name
 * @department
 */
public class Employee implements Comparable<Employee> {
    public int employeeId;
    private String name;
    private String department;

    public Employee(int employeeId, String name, String department) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
    }

    // getEmployeeId
    public int getEmployeeId() {
        return employeeId;
    }

    // getName
    public String getName() {
        return name;
    }

    // getDepartment
    public String getDepartment() {
        return department;
    }

    @Override
    public int compareTo(Employee other) {
        return Integer.compare(this.employeeId, other.employeeId);
    }

    // toString
    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
