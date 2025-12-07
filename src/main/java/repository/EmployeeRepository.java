package repository;

import model.Employee;

import java.sql.*;
import java.util.List;

// repository - This type of class is used to write db related logics

public class EmployeeRepository {
    static String url = "jdbc:postgresql://localhost:5432/company?user=postgres&password=root";
    static Connection connection;
    {
        try{
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //add data
    public void save(Employee employee) throws SQLException {
        String query = "insert into employee values(?,?,?) ";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1,employee.getEmpid());
            ps.setString(2, employee.getEname());
            ps.setDouble(3, employee.getEsal());
            ps.executeUpdate();

    }

    //fetch all data from database
    public ResultSet findAll()throws SQLException{
        String sql="select * from employee";
            Statement statement=connection.createStatement();
            return statement.executeQuery(sql);
    }

    //update name in BD by using Id
    public int  updateNameById(String eName,int id)throws SQLException{
        String sql="update employee set ename=? where empid=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,eName);
            preparedStatement.setInt(2,id);
           return  preparedStatement.executeUpdate();
    }

    //update salary by ID n db
    public int updateSalById(double esal,int id)throws SQLException{
        String sql="update employee set esal=? where empid=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setDouble(1,esal);
            preparedStatement.setInt(2,id);
            return  preparedStatement.executeUpdate();


    }

    //find all data from DB

    public ResultSet findById(int id) throws SQLException {
        String sql="select * from employee where empid=?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
       return  preparedStatement.executeQuery();

    }

    //delete data by id


    public int deleteById(int id)throws SQLException{
        String sql="delete from employee where empid=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,id);
            return  preparedStatement.executeUpdate();

    }



}