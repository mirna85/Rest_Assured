package testCases;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Put_Or_Update_Product {
	
	@Test
	public void update_A_Product() {
		
		String payloadPath = "src/test/java/data/updatePayLoad_1932.json";
		
		Response response =
				given()
					.log().all() //detailed data from response 
					.baseUri("https://techfios.com/api-prod/api/product")
					.header("Content-Type","application/json;")
					.body(new File(payloadPath))
				.when()
					.log().all()
					.put("update.php")
				.then()
					.log().all()
					.extract().response();
				
		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);
		Assert.assertEquals(statusCode, 200);
		
		long actualResponseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
		System.out.println("Actual Response Time: " + actualResponseTime);
		
		if(actualResponseTime <= 5000) {
			System.out.println("Response time is within range.");
		}else {
			System.out.println("Response time is out of range.");
		}
		
//		response.getBody().prettyPrint();
		
		String responseBody = response.getBody().asString();
		System.out.println("Response Body:" + responseBody);
		
		JsonPath js = new JsonPath(responseBody);
		
		String successMessage = js.getString("message");
		System.out.println("Success Message: " + successMessage);
		Assert.assertEquals(successMessage, "Product was updated.");
		
	}

}
