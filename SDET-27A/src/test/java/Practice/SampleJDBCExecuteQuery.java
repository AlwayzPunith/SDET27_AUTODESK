package Practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class SampleJDBCExecuteQuery {

	public static void main(String[] args) throws SQLException {
		/*Step1: Register the Driver*/
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);//Register the Driver
		
		/*Step2: get Connection with the database- provide db name(go to google search mysql jdbc url  then select jdbs:mysql:----------until 3306/then our table name)*/
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb", "root", "root");
		
		/*Step3: issue Create Statement*/
		Statement state = con.createStatement();
		
		/*Step4: Execute a query--- Provide Table name */
		ResultSet result = state.executeQuery("select * from employeeinfo");
		while(result.next())
		{
			System.out.println(result.getString(2)+" "+result.getString(3));
		}
		
		/*Step5:Close the DataBase*/
		con.close();
		
	}

}
