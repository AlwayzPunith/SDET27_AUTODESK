package Practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class SampleJDBCExecuteUpdate {

	public static void main(String[] args) throws Throwable 
	{
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb", "root", "root");
		
		Statement state = con.createStatement();
		
		int result = state.executeUpdate("insert into employeeinfo values(2,'Rock','Miami');");
		
		if(result==1)
		{
			System.out.println("Data Added Succesfully");
		}
		
		else
		{
			System.out.println("Failed to add");
		}
		
		con.close();
	}

}
