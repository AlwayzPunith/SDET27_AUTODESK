package Practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DataisPresentOrNot1 {

	public static void main(String[] args) throws Throwable
	{
		boolean flag=false;
		Driver	driver=new Driver();
		DriverManager.registerDriver(driver);
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb", "root", "root");
		
		Statement state = con.createStatement();
		
		 state.executeUpdate("insert into employeeinfo values(3,''shivu,'NY');");
		 
		 ResultSet result = state.executeQuery("select * from employeeinfo;");
		
		while(result.next())
		{
			if(result.getString(2).contains("shivu"))
			{
				flag=true;
				System.out.println("It Contains Given Data");
				break;
			}
			
		}
		if(flag==false)
		{
			System.out.println("It not Contains Given Data");
		}

	}

}
