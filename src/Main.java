import service.EmployeeService;
import model.Employee;
import java.util.Scanner;

public class Main   {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        EmployeeService service=new EmployeeService();
        while(true){
            System.out.println("=============== Employee Management System ===============");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employee");
            System.out.println("3. Search Employee");
            System.out.println("4. Update Employee");
            System.out.println("5. Delete Employee");
            System.out.println("6. Exit");
            System.out.println("Enter your choice: ");
            int choice=sc.nextInt();

            switch (choice){
                //Using the {} for cases to limit the variable scope
                case 1:{
                    System.out.print("Enter Employee ID: ");
                    int employeeId = sc.nextInt();
                    sc.nextLine(); // Clears the Enter left by nextInt()

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();

                    System.out.print("Enter Email: ");
                    String email = sc.next();
                    sc.nextLine(); // Clears the Enter left by next()

                    System.out.print("Enter Department: ");
                    String department = sc.nextLine();

                    System.out.print("Enter Salary: ");
                    double salary = sc.nextDouble();

                    service.addEmployee(
                            new Employee(employeeId, name, age, email, department, salary)
                    );

                    break;}

                case 2:
                    service.viewEmployees();
                    break;

                case 3:
                    System.out.print("Enter the ID of Employee you want to search: ");
                    int empId=sc.nextInt();
                    service.searchEmployee(empId);
                    break;

                case 4:{
                    System.out.print("Enter Employee Id:  ");
                    int employeeId = sc.nextInt();
                    sc.nextLine(); // Clears the Enter left by nextInt()
                    System.out.print("Enter Employee Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Employee Age: ");
                    int age = sc.nextInt();
                    System.out.print("Enter Employee Email: ");
                    String email = sc.next();
                    System.out.print("Enter Employee Department: ");
                    sc.nextLine();
                    String department = sc.nextLine();
                    System.out.print("Enter Employee Salary: ");
                    double salary = sc.nextDouble();
                    service.updateEmployee(employeeId, name, age, email, department, salary);
                    break;
                }

                case 5:{
                    System.out.print("Enter the Id of Employee you want to delete: ");
                    int employeeId=sc.nextInt();
                    service.deleteEmployee(employeeId);
                    break;
                }

                case 6:
                    System.out.print("Exiting....");
                    return;

                default:
                    System.out.println("Enter a valid choice");
            }
        }
    }
}
