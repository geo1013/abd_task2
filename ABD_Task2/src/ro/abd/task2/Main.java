/**
 * 
 */
package ro.abd.task2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Calendar;

import oracle.jdbc.pool.OracleDataSource;

/**
 * <p>
 * Adauga numere random in conexiunea SQL oracle
 * </p>
 * 
 * <p>
 * Voi presupune ca mi-ai creat tabelele si userii necesari
 * </p>
 * 
 * @author laura
 * 
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Calendar cal = Calendar.getInstance();

		try {
			// Create an OracleDataSource instance and set properties
			OracleDataSource ods = new OracleDataSource();
			ods.setUser("abd_task2");
			ods.setPassword("oracle");
			ods.setURL("jdbc:oracle:thin:@localhost:1521:orcl");

			// Connect to the database
			Connection conn = ods.getConnection();

			// Prepare to insert new names in the EMPLOYEES table
			PreparedStatement pstmt = conn
					.prepareStatement("insert into MYLOG " + "(ID, TIMP) "
							+ "values (mylog_seq.nextval, ?)");

			// Add Timestamp
			pstmt.setTimestamp(1, new Timestamp(cal.getTime().getTime()));

			// Do the insertion, check number of rows updated
			pstmt.execute();
			System.out.println(pstmt.getUpdateCount() + " row updated");

			// Close the statement
			pstmt.close();

			// Close the connecion
			conn.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
