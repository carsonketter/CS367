package dbUtil;
/**
 * This class provides some basic methods for accessing a MySQL DB.
 * It uses Java JDBC and MySQL JDBC driver, mysql-connector-java-5.1.18-bin.jar
 * to open an modify the DB.
 * 
 */

// You need to import the java.sql package to use JDBC methods and classes
import java.sql.*;

/**
 * @author Dr. Blaha
 * 
 * 
 */
public class Utilities {

	private Connection conn = null; // Connection object

	/**
	 * The default constructor will load and register the MySQL driver
	 */
	public Utilities() {
		super();
		// Load the MySQL JDBC driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Class.forName("com.mysql.jdbc.Driver").newInstance();

		} catch (ClassNotFoundException e) {
			System.out.println("Unable to load driver.");
		}
	}

	/**
	 * @return the conn
	 */
	public Connection getConn() {
		return conn;
	}

	/**
	 * Open a MySQL DB connection where user name and password are predefined
	 * (hardwired) within the method.
	 */
	public void openDB() {

		// Connect to the database
		String url = "jdbc:mysql://mal.cs.plu.edu:3306/company367_2017";
		String username = "";
		String password = "";

		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error connecting to database: " + e.toString());
		}

	}// openDB

	/**
	 * Close the connection to the DB
	 */
	public void closeDB() {
		try {
			conn.close();
			conn = null;
		} catch (SQLException e) {
			System.err.println("Failed to close database connection: " + e);
		}
	}// closeDB

	/**
	 * This method creates an SQL statement to list fname, lname, salary of all
	 * employees that work in the department with dname='Research'
	 * 
	 * @return ResultSet that contains three columns lname, fname, salary of all
	 *         employees that work in the research department
	 */
	public ResultSet getNameSalary() {
		ResultSet rset = null;
		String sql = null;

		try {
			// create a Statement and an SQL string for the statement
			Statement stmt = conn.createStatement();
			sql = "SELECT lname, fname, salary FROM employee, department " + "WHERE dno=dnumber and dname='Research' "
					+ "ORDER BY lname, fname";
			rset = stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("createStatement " + e.getMessage() + sql);
		}

		return rset;
	}// getNameSalary

	/**
	 * This method creates an SQL statement to list fname, lname, and department
	 * number of all employees that have a last name that starts with the String
	 * target
	 * 
	 * @param target the string used to match beginning of employee's last name
	 * @return ResultSet that contains lname, fname, and department number of
	 *         all employees that have a first name that starts with target.
	 */
	public ResultSet matchLastName(String target) {
		ResultSet rset = null;
		String sql = null;

		try {
			// create a Statement and an SQL string for the statement
			Statement stmt = conn.createStatement();
			sql = "SELECT dno, lname, fname FROM employee " + "WHERE lname like '" + target + "%' " + "ORDER BY dno";
			rset = stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("createStatement " + e.getMessage() + sql);
		}

		return rset;

	}// matchLastName

	/**
	 * This method creates an SQL statement to list fname, lname, and department
	 * number of all employees that work in the department with number dno
	 * 
	 * @param dno the department number
	 * @return ResultSet that contains lname, fname, and department number of
	 *         all employees that work in the department number dno
	 */
	// EXAMPLE OF USING A PreparedStatement AND SETTING Parameters
	public ResultSet employeeByDNO(int dno) {
		ResultSet rset = null;
		String sql = null;

		try {
			// create a Statement and an SQL string for the statement
			sql = "SELECT dno, lname, fname FROM employee " + "WHERE dno = ? " + "ORDER BY dno";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.clearParameters();
			pstmt.setInt(1, dno); // set the 1 parameter

			rset = pstmt.executeQuery();
		} catch (SQLException e) {
			System.out.println("createStatement " + e.getMessage() + sql);
		}

		return rset;
	}// matchLastName2

	/**  
	 * 1 Write and Test
	 * Overload the open method that opens a MySQL DB with the user name 
	 * and password given as input.
	 * 
	 * @param username is a String that is the DB account username
	 * @param password is a String that is the password the account
	 */

	
	/**
	 * 2 Write and Test 
	 * Write a method that returns lname, fname, project number and hours of all 
	 * employees that work on a project controlled by department, deptNum. Here 
	 * deptNum is given as input from the client
	 * 
	 * @param deptNum is the controlling department number
	 * @return ResultSet with lname, fname, project number and hours of all
	 *         employees that work on a project controlled by department dno
	 */

	/**
	 * 3 Write and Test
	 * Write a method that returns for each project the number of employees 
	 * that work on the project, the total number of hours they have all worked 
	 * on the project, and the average number of hours each employee has worked 
	 * on the project.
	 * 
	 * @return ResultSet that has for each project the number of employees that
	 *         work on the project, the total number of hours they have all
	 *         worked on the project, and the average number of hours each
	 *         employee has worked on project
	 */

	/**
	 * 4 Write and Test
	 * Write a method that returns fname, lname, salary, and dno for each employee 
	 * that works on a project with the employee specified by input values empFname, empLname
	 * 
	 * @param empFname is the first name of the employee
	 * @param empLname is the last name of the employee
	 * @return ResultSet that has fname, lname, salary, and dno for each
	 *         employee that works on a project with the employee empFname,
	 *         empLname
	 */

	/**
	 * 5 Write and Test
	 * Retrieve the names of employees who do not work on any project and their 
	 * salary Names must be in the format "lname, fname" i.e., the last name and 
	 * first name must be concatenated.
	 * 
	 * @return ResultSet that has employee name and salary of all employees that
	 *         do not work on any project.
	 */

	/**
	 * 6 Write and Test   ==> YOU MUST USE A PreparedStatement <==
	 * This method will use a PreparedStatement and the
	 * information in data to update the works_on table. Each row of the 2-dim
	 * array, data, contains the 3 attributes for one tuple in the works_on
	 * table. The 2-dim array is a nx3 array and the column format is 
	 * (essn, pno, hours) The method returns the number of tuples successfully
	 * inserted.
	 * 
	 * @param data is a nx3 table of Strings where each row has the format 
	 *        (essn, pno, hours)
	 * @return number of tuples successfully inserted into works_on
	 */

}// Utilities class