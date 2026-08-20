package service;
import model.Employee;

import java.util.ArrayList;

public class EmployeeService {
    private ArrayList<Employee> employees=new ArrayList<>();

    public void addEmployee(Employee employee){
        employees.add(employee);
        System.out.println("Employee added successfully");
    }
}
