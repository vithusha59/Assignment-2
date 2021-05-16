package com;

//Model
import model.Payment;

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
@Path("/Payment")
public class Payment_Service {
	
	// Object
	Payment paymentObj = new Payment();

		// Read
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)

		public String readPayment() {

			return paymentObj.readPayments();
		}
		
		// Insert
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertPayment(

				@FormParam("Transaction_Code") String Transaction_Code,
				@FormParam("Depositor") String Depositor,
				@FormParam("Account_No") String Account_No,
				@FormParam("Bank") String Bank,
				@FormParam("Amount") String Amount,
				@FormParam("CVV") String CVV) {

			String output = paymentObj.insertPayments(Transaction_Code, Depositor, Account_No, Bank, Amount,CVV );
			return output;
		}

		// Update
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updatePayment(String paymentData) {

			// Convert the input string to a JSON object
			JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();

			// Read the values from the JSON object
			String Payment_ID = paymentObject.get("Payment_ID").getAsString();
			String Transaction_Code = paymentObject.get("Transaction_Code").getAsString();
			String Depositor = paymentObject.get("Depositor").getAsString();
			String Account_No = paymentObject.get("Account_No").getAsString();
			String Bank = paymentObject.get("Bank").getAsString();
			String Amount = paymentObject.get("Amount").getAsString();
			String CVV = paymentObject.get("CVV").getAsString();

			String output = paymentObj.updatePayments(Payment_ID, Transaction_Code, Depositor, Account_No, Bank, Amount,CVV);

			return output;
		}

		
		// Delete
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deletePayment(String paymentData) {

			// Convert the input string to an XML document
			Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

			// Read the value from the element <ProductID>
			String Payment_ID = doc.select("Payment_ID").text();

			String output = paymentObj.deletePayment(Payment_ID);
			return output;
		}
		
}
