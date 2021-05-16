package model;

import java.sql.*;

public class User {
	
	// DB Connection
		public Connection connect() {
			Connection con = null;

			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/ass_paf", "root", "");

				// For testing
				System.out.print("DB Successfully connected");
			}

			catch (Exception e) {
				e.printStackTrace();
				System.out.print("DB not connected");
			}

			return con;
		}


		// Insert
		public String insertUser(String user_Name, String User_Type, String Email, String Password) {

			String output = "";

			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database";
				}

				// create a prepared statement
				String query = " insert into user(`user_ID`,`user_Name`,`User_Type`,`Email`,`Password`)"
						+ " values (?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, user_Name);
				preparedStmt.setString(3, User_Type);
				preparedStmt.setString(4, Email);
				preparedStmt.setString(5, Password);
				
				

				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Insertion successful";

			} catch (Exception e) {
				output = "Insertion Unsuccess";
				System.err.println(e.getMessage());
			}
			return output;
		}

		// Read
		public String readUser() {

			String output = "";

			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for reading.";
				}

				// Prepare the html table to be displayed
				output = "<table border=\"1\"><tr><th>user_Name</th>" + "<th>User_Type</th><th>Email</th>"
						+ "<th>Password</th></tr>";

				String query = "select * from user";

				Statement stmt = con.createStatement();

				ResultSet rs = stmt.executeQuery(query);

				// iterate through the rows in the result set
				while (rs.next()) {

					String user_ID  = Integer.toString(rs.getInt("user_ID"));
					String user_Name = rs.getString("user_Name");
					String User_Type = rs.getString("User_Type");
					String Email = rs.getString("Email");
					String Password = rs.getString("Password");
					

					// Add into the html table
					output += "<tr><td>" + user_Name + "</td>";
					output += "<td>" + User_Type + "</td>";
					output +=  "<td>" + Email + "</td>";
					output += "<td>" + Password + "</td>";
					
				}

				con.close();

				// Complete the html table
				output += "</table>";

			} catch (Exception e) {
				output = "Error while reading.";
				System.err.println(e.getMessage());
			}
			return output;
		}

		// Update
		public String updateUser(String user_ID, String user_Name, String User_Type, String Email, String Password) {

			String output = "";

			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for updating.";
				}

				// create a prepared statement
				String query = "UPDATE user SET user_Name=?,User_Type=?,Email=?,Password=? WHERE user_ID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values
				preparedStmt.setString(1, user_Name);
				preparedStmt.setString(2, User_Type);
			    preparedStmt.setString(3, Email);
			    preparedStmt.setString(4, Password);
				preparedStmt.setInt(5, Integer.parseInt(user_ID));

				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Successfully Updated";

			} catch (Exception e) {
				output = "Updating unsuccesful .";
				System.err.println(e.getMessage());
			}
			return output;
		}

		// Delete
		public String deleteUser(String user_ID) {
			String output = "";

			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}

				// create a prepared statement
				String query = "delete from user where user_ID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values
				preparedStmt.setInt(1, Integer.parseInt(user_ID));

				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Deleted successfully";

			} catch (Exception e) {
				output = "Error while deleting the Product Details.";
				System.err.println(e.getMessage());
			}
			return output;
		}



}
