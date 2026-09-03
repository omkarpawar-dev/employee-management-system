package service;
import model.Employee;

import java.util.ArrayList;

public class EmployeeService {
    private ArrayList<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        employees.add(employee);
        System.out.println("Employee added successfully");

        //this execution when we create object of this class
        //EmployeeService service = new EmployeeService();
        //service.addEmployee(
        //       new Employee(101, "Omkar", 21, "omkar@gmail.com", "IT", 50000)
        //);
        //ArrayList<Employee>:This particular ArrayList is meant to contain Employee objects.
    }

    public void viewEmployees() {
        if(employees.isEmpty()){
            System.out.println("No employees found");
        }
        else {
            for (Employee emp : employees) { //bascially we say for every obj of class Employee from employees arrayList do the following
                emp.display();
                System.out.println("************************************");
            }
        }
    }
    public  void searchEmployee(int employeeId){
        boolean found=false;
        for(Employee emp: employees){
            if(emp.getEmployeeId()==employeeId){
                found=true;
                System.out.println("Employee Found!!");
                emp.display();
            }

            }
        if (!found){
            System.out.println("Employee not found.");
        }

    }

    public void updateEmployee(int employeeId,String name, int age,String email,String department, double salary){
        boolean found=false;
        for(Employee emp:employees) {
            if (emp.getEmployeeId() == employeeId) {
                found = true;
                emp.setName(name);
                emp.setAge(age);
                emp.setDepartment(department);
                emp.setEmail(email);
                emp.setSalary(salary);
                System.out.println("Employee updated successfully.");
                break;
            }
        }
        if(!found){
            System.out.println("Employee not found.");
        }

    }
    public void deleteEmployee(int employeeId){
        int index=-1;
        boolean found=false;
        for(Employee emp:employees){
            index++;
            if(emp.getEmployeeId()==employeeId){
                found=true;
                break;
            }
        }
        if(found){
            employees.remove(index);
            System.out.println("Employee removed successfully.");
        }
        else {
            System.out.println("Employee not found.");
        }
    }
}