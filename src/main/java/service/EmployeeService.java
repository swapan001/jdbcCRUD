package service;


import model.Employee;
import repository.EmployeeRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
    service layer is used to keep main business logic
    taking the inputs
    handling the exceptions
    It will act as abstract layer between repository and the end user
    Logics like Processing the ResultSet etc..
* */
public class EmployeeService {

    private final EmployeeRepository er=new EmployeeRepository();
    private final Scanner scanner=new Scanner(System.in);
    public  void save(){
        Employee employee=new Employee();
        try {
        System.out.print("Enter employee id:");
        int id= scanner.nextInt();
        employee.setEmpid(id);
        //employee.setEmpid(sc.nextInt());
            scanner.nextLine();
        System.out.print("Enter employee name:");
        String name= scanner.nextLine();
        employee.setEname(name);
        System.out.print("Enter employee salary:");
        double salary= scanner.nextDouble();
        employee.setEsal(salary);


            er.save(employee);
            System.out.println("Successfully Saved...");
        } catch (SQLException | InputMismatchException e) {
            if(e instanceof SQLException){
                System.out.println("Error while save the data cause of duplicate ID , DB error!!!!");
            }
            else{
                System.out.println("Error  for invalid input!!!!");
            }
        }
    }

    public void findAll(){
        try {
            ResultSet rs= er.findAll();
            System.out.println("---------All Employee Details------------");
            while (rs.next()){
                System.out.print("EMP_ID : "+rs.getInt(1)+" ENAME : "+rs.getString(2)+" ESAL : "+rs.getDouble(3));
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public  Employee findById(){
        System.out.print("Enter the employee id :");
        try {
            ResultSet rs=er.findById( scanner.nextInt());
            if(rs.next()){
                return
                        new Employee
                                (rs.getInt(1),
                                        rs.getString(2),
                                        rs.getDouble(3)
                                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("No data found for the  passed id ");
        return  null;
    }
    public void updateNameById(){

        Employee employee=findById();
        if(employee!=null){
            System.out.println("The current name is: "+employee.getEname());
            System.out.print("Enter the  updated name for update : ");
            try {
                scanner.nextLine();
                er.updateNameById(scanner.nextLine(),employee.getEmpid());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void deleteById(){
        Employee employee=findById();
        if(employee != null){
            try {
                er.deleteById(employee.getEmpid());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void updateSalById(){
        Employee employee=findById();
        if(employee!=null){
            System.out.println("The current name is: "+employee.getEname()+" and Salary is: "+employee.getEsal());
            System.out.print("Enter the  updated salary for update : ");
            try {
                er.updateSalById(scanner.nextDouble(),employee.getEmpid());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
