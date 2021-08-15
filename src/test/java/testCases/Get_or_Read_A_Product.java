package testCases;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Get_or_Read_A_Product {
	
	@Test
	public void read_A_Products() {
	
		Response response =
				given()
					.baseUri("https://techfios.com/api-prod/api/product")
					.header("Content-Type","application/json; charset=UTF-8")
					.queryParam("id", "1923")
				.when()
					.get("/read_one.php")
				.then()
					.extract().response();
				
		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);
		Assert.assertEquals(statusCode, 200);
		
		long actualResponseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
		System.out.println("Actual Response Time: " + actualResponseTime);
		
		if(actualResponseTime <= 2000) {
			System.out.println("Response time is within range.");
		}else {
			System.out.println("Response time is out of range.");
		}
		
		response.getBody().prettyPrint();
		String responseBody = response.getBody().asString();
		System.out.println("Response Body:" + responseBody);
		
		//Specific details of particular product:
		
//		JsonPath js = new JsonPath(responseBody);
//		String productName = js.getString("name");
//		System.out.println("Product Name: " + productName);
//		Assert.assertEquals(productName, "iPhone 13.0");
//		
//		String price = js.getString("price");
//		System.out.println("Price: " + price);
//		Assert.assertEquals(price, "199");
//		
//		String description = js.getString("description");
//		System.out.println("Description: " + description);
//		Assert.assertEquals(description, "The super phone");
		
	}

}
