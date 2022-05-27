package IBM_LP.B31batch;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class day1 {
	
	@Test(enabled=false)
	public void testcase1()
	{
		//System.out.println("hello world");
		Response resp = RestAssured.get("http://localhost:3000/ibmexample");
		System.out.println(resp.prettyPrint());
		System.out.println(resp.statusCode());
		System.out.println(resp.headers());
		//System.out.println(resp.get);
	}
	
	@Test(enabled=false)
	public void testcase2()
	{
		
		RestAssured.baseURI="http://localhost:3000";
		
		given()
			.get("/ibmexample").
		then()
		    .statusCode(200)
		    .log().all();
		
		given()
		.delete("/ibmexample/1").
	    then()
	    .statusCode(200)
	    .log().all();
	}
	
	
	
	@Test
	public void testcase3()
	{
		
		RestAssured.baseURI="http://localhost:3000";
		
		given()
			.get("/ibmexample").
		then()
		    .statusCode(200)
		    .log().all();
		System.out.println("git changes");
		
	}

}
