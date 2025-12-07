import model.Employee;
import repository.EmployeeRepository;
import service.EmployeeService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class App {

static EmployeeService employeeService=new EmployeeService();
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        System.out.println("\n----------------User Welcome to employee management------------- \n");

        boolean flag=true;

        do{
            System.out.println("\n\t press 1 for Insert New Data\n\t Press 2 for find all employee \n\t Press 3 for update employee name\n\t press 4 for update employee salary by id \n\t press 5 for find one employee by id\n\t Press 6 for delete employee data\n\t Press 0 for exit");
            System.out.print("\n\tPlease enter the option: ");
            int choice=scanner.nextInt();
            switch (choice){
                case 1: employeeService.save();
                    break;
                case 2: employeeService.findAll();
                break;
                case 3: employeeService.updateNameById();
                break;
                case 4: employeeService.updateSalById();

                break;
                case 5: Employee e =employeeService.findById();
                    System.out.println(e);

                    break;
                case 6: employeeService.deleteById();
                    System.out.println("Successfully deleted------ ):\n");
                break;
                case 0: flag=false;
                break;
                default:
                    System.out.println("Please enter valid choice!!!!!!");
            }
        }while (flag);

    }
}
