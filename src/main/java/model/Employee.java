package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private int empid;
    private String ename;
    private double esal;


    /*
    * public void setEmpId(int empid){
    *   if(empid>0){
    *       this.empid=empid;
    *   }
    * }
    * */
}
