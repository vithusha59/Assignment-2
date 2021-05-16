package com;

//Model
import model.Fund;


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
@Path("/Fund")
public class Fund_Service {
	
	// Object
	Fund fundObj = new Fund();
	
	// Read
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)

	public String readFund() {

		return fundObj.readFund();
	}
	
	// Insert
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertFund(

			@FormParam("FundingOrgName") String FundingOrgName, 
			@FormParam("InterestingProjArea") String InterestingProjArea,
			@FormParam("FundingAmount") String FundingAmount,
			@FormParam("Email") String Email) {

		String output = fundObj.insertFund(FundingOrgName, InterestingProjArea, FundingAmount, Email);
		return output;
	}
	
	// Update
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateFund(String FundData) {

		// Convert the input string to a JSON object
		JsonObject fundObject = new JsonParser().parse(FundData).getAsJsonObject();

		// Read the values from the JSON object
		String Funder_ID = fundObject.get("Funder_ID").getAsString();
		String FundingOrgName = fundObject.get("FundingOrgName").getAsString();
		String InterestingProjArea = fundObject.get("InterestingProjArea").getAsString();
		String FundingAmount = fundObject.get("FundingAmount").getAsString();
		String Email = fundObject.get("Email").getAsString();
	

		String output = fundObj.updateFund(Funder_ID, FundingOrgName, InterestingProjArea, FundingAmount, Email);

		return output;
	}
	
	// Delete
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFund(String FundData) {

		// Convert the input string to an XML document
		Document doc = Jsoup.parse(FundData, "", Parser.xmlParser());

		// Read the value from the element <ProductID>
		String Funder_ID = doc.select("Funder_ID").text();

		String output = fundObj.deleteFund(Funder_ID);
		return output;
	}


	
	

}
