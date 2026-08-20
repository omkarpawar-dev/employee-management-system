package model;

public class Employee {

        private int employeeId;
        private String name;
        private int age;
        private String email;
        private String department;
        private double salary;

        public Employee(int employeeId, String name, int age,String email,String department, double salary){
            this.employeeId=employeeId;
            this.name=name;
            this.age=age;
            this.email=email;
            this.department=department;
            this.salary=salary;
        }

        public void setEmployeeId(int employeeId){
            this.employeeId=employeeId;
        }

        public void setName(String name){
            this.name=name;
        }

        public void setAge(int age){
            this.age=age;
        }

        public void setEmail(String email){
            this.email=email;
        }

        public void setDepartment(String department){
            this.department=department;
        }
        public void setSalary(double salary){
            this.salary=salary;
        }

        public int getEmployeeId(){
            return employeeId;
        }

        public String getName(){
            return name;
        }

        public int getAge(){
            return age;
        }

        public String getEmail(){
            return email;
        }

        public String getDepartment(){
            return department;
        }

        public double getSalary(){
            return salary;
        }

        public void display(){
            System.out.println("Employee Id: "+employeeId);
            System.out.println("Name: "+name);
            System.out.println("Age: "+age);
            System.out.println("Email: "+email);
            System.out.println("Department: "+department);
            System.out.println("Salary: "+salary);
        }
}


