import java.sql.*; // import

public class DatabaseConnection {
	public static void main(String[] args) {
		Connection cn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver"); // loadint the driver
			String url = "jdbc:mysql://localhost:3306/bca_third_2020";
			String un = "root";
			String pw = "";
			cn = DriverManager.getConnection(url, un, pw); // creating connection
			System.out.println("DB Connected!");
			insert(cn);
//			System.out.println("Reading record before update");
//			read(cn);
//			System.out.println("Updating...");
//			update(cn);
//			System.out.println("Reading record after update");
//			read(cn);
			
			
			System.out.println("Reading record before delete");
			read(cn);
			delete(cn);
			System.out.println("Reading record after delete");
			read(cn);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static void insert(Connection cn) throws SQLException {
		Statement stat = cn.createStatement(); // statment create

		String query = "insert into student (roll_no, name, course) values (3, 'Sajan', 'BIT')";
		stat.executeUpdate(query); // execute query

		System.out.println("Record inserted!");
	}
	private static void read(Connection cn) throws SQLException {
		Statement stat = cn.createStatement(); // statment create

		String query = "select * from student";
		System.out.println("Reading records...");
		ResultSet rs = stat.executeQuery(query); // execute query
		while(rs.next()) {
			System.out.print("Roll NO: "+rs.getString("roll_no"));
			System.out.print(" Name: "+rs.getString("name"));
			System.out.print(" Course: "+rs.getString("course"));
			System.out.println("\n");
		}
		
	}
	
	private static void update(Connection cn) throws SQLException {
		Statement stat = cn.createStatement(); // statment create

		String query = "update student set course = 'BIT' where name = 'Anita'";
		stat.executeUpdate(query); // execute query

		System.out.println("Record updated!");
	}
	
	private static void delete(Connection cn) throws SQLException {
		Statement stat = cn.createStatement(); // statment create

		String query = "delete from student where roll_no = 1";
		stat.executeUpdate(query); // execute query

		System.out.println("Record deleted!");
	}
}
