package com;


//Model
import model.User;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


//SET PATH ..............................................
@Path("/User")
public class User_Service {
	
	// Object
	User userObj = new User();
	 
		// Read
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)

		public String readUser() {

			return userObj.readUser();
		}
		
		
		// Insert
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertUser(

				@FormParam("user_Name") String user_Name, 
				@FormParam("User_Type") String User_Type,
				@FormParam("Email") String Email,
				@FormParam("Password") String Password) {

			String output = userObj.insertUser(user_Name, User_Type, Email, Password);
			return output;
		}
		
		// Update
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateUser(String UserData) {

			// Convert the input string to a JSON object
			JsonObject userObject = new JsonParser().parse(UserData).getAsJsonObject();

			// Read the values from the JSON object
			String user_ID = userObject.get("user_ID").getAsString();
			String user_Name = userObject.get("user_Name").getAsString();
			String User_Type = userObject.get("User_Type").getAsString();
			String Email = userObject.get("Email").getAsString();
			String Password = userObject.get("Password").getAsString();

			String output = userObj.updateUser(user_ID, user_Name, User_Type, Email, Password);

			return output;
		}

		
		// Delete
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteUser(String UserData) {

			// Convert the input string to an XML document
			Document doc = Jsoup.parse(UserData, "", Parser.xmlParser());

			// Read the value from the element <ProductID>
			String user_ID = doc.select("user_ID").text();

			String output = userObj.deleteUser(user_ID);
			return output;
		}
		

}
