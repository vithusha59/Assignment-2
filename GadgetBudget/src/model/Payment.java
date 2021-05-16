package model;

import java.sql.*;

public class Payment {

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
	public String insertPayments(String Transaction_Code , String Depositor, String Account_No, String Bank, String Amount, String CVV) {

		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " insert into payment(`Payment_ID`,`Transaction_Code`,`Depositor`,`Account_No`,`Bank`,`Amount`,`CVV`)"
					+ " values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, Transaction_Code);
			preparedStmt.setString(3, Depositor);
			preparedStmt.setInt(4, Integer.parseInt(Account_No));
			preparedStmt.setString(5, Bank);
			preparedStmt.setDouble(6, Double.parseDouble(Amount));
			preparedStmt.setInt(7, Integer.parseInt(CVV));
			
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
	public String readPayments() {

		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Transaction_Code</th>" + "<th>Depositor</th><th>Account_No</th>"
					+ "<th>Bank</th>" + "<th>Amount</th><th>CVV</th></tr>";

			String query = "select * from payment";

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {

				String Payment_ID  = Integer.toString(rs.getInt("Payment_ID"));
				String Transaction_Code = rs.getString("Transaction_Code");
				String Depositor = rs.getString("Depositor");
				String Account_No = Integer.toString(rs.getInt("Account_No"));
				String Bank = rs.getString("Bank");
				String Amount = Double.toString(rs.getDouble("Amount"));
				String CVV = Integer.toString(rs.getInt("CVV"));
				

				// Add into the html table
				output += "<tr><td>" + Transaction_Code + "</td>";
				output += "<td>" + Depositor + "</td>";
				output += "<td>" + Account_No + "</td>";
				output += "<td>" + Bank + "</td>";
				output += "<td>" +"Rs. "+ Amount + "</td>";
				output += "<td>" + CVV + "</td>";
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
	public String updatePayments(String Payment_ID, String Transaction_Code , String Depositor, String Account_No, String Bank, String Amount, String CVV) {

		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE payment SET Transaction_Code=?,Depositor=?,Account_No=?,Bank=?,Amount=?,CVV=? WHERE Payment_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, Transaction_Code);
			preparedStmt.setString(2, Depositor);
			preparedStmt.setInt(3, Integer.parseInt(Account_No));
			preparedStmt.setString(4, Bank);
			preparedStmt.setDouble(5, Double.parseDouble(Amount));
			preparedStmt.setInt(6, Integer.parseInt(CVV));
			preparedStmt.setInt(7, Integer.parseInt(Payment_ID));

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
	public String deletePayment(String Payment_ID) {
		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from payment where Payment_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(Payment_ID));

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
