package model;

import java.sql.*;

public class Fund {
	
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
	public String insertFund(String FundingOrgName, String InterestingProjArea, String FundingAmount, String Email) {

		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " insert into fund(`Funder_ID`,`FundingOrgName`,`InterestingProjArea`,`FundingAmount`,`Email`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, FundingOrgName);
			preparedStmt.setString(3, InterestingProjArea);
			preparedStmt.setDouble(4, Double.parseDouble(FundingAmount));
			preparedStmt.setString(5, Email);
			
			

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
	public String readFund() {

		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Funding_OrganizationName</th>" + "<th>Interesting_ProjectArea</th><th>Funding_Amount</th>"
					+ "<th>Email</th></tr>";

			String query = "select * from fund";

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {

				String Funder_ID = Integer.toString(rs.getInt("Funder_ID"));
				String FundingOrgName = rs.getString("FundingOrgName");
				String InterestingProjArea = rs.getString("InterestingProjArea");
				String FundingAmount = Double.toString(rs.getDouble("FundingAmount"));
				String Email = rs.getString("Email");
				

				// Add into the html table
				output += "<tr><td>" + FundingOrgName + "</td>";
				output += "<td>" + InterestingProjArea + "</td>";
				output +=  "<td>" +"Rs. "+ FundingAmount + "</td>";
				output += "<td>" + Email + "</td>";
				
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
	public String updateFund(String Funder_ID, String FundingOrgName, String InterestingProjArea, String FundingAmount, String Email) {

		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE fund SET FundingOrgName=?,InterestingProjArea=?,FundingAmount=?,Email=? WHERE Funder_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, FundingOrgName);
			preparedStmt.setString(2, InterestingProjArea);
		    preparedStmt.setDouble(3, Double.parseDouble(FundingAmount));
			preparedStmt.setString(4, Email);
			preparedStmt.setInt(5, Integer.parseInt(Funder_ID));

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
	public String deleteFund(String Funder_ID) {
		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from fund where Funder_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(Funder_ID));

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
