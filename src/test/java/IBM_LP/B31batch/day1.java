package IBM_LP.B31batch;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
	
	
	
	@Test(enabled = false)
	public void postandputexample1()
	{
		
		RestAssured.baseURI="http://localhost:3000";
		
		String reqbody = "{\"firstname\":\"ankit\",\"lastname\":\"abc\",\"place\":\"chennai\"}";
		String modbody = "{\"firstname\":\"amit\",\"lastname\":\"abc\",\"place\":\"chennai\"}";
		
		given()
			.header("content-type","application/json")
			.body(reqbody).
		when()
			.post("/ibmexample").
		then()
			.statusCode(201)
			.log().all();
		
		given()
		.header("content-type","application/json")
		.body(modbody).
	when()
		.put("/ibmexample/3").
	then()
		.statusCode(200)
		.log().all();
			
		
	}
	
	@Test(enabled = false)
	public void postandputexample2()
	{
		
		RestAssured.baseURI="http://localhost:3000";
		
		JSONObject obj = new JSONObject();
		obj.put("id", "sri");
		obj.put("lastname", "xyz");
		obj.put("place", "goa");
		
		//System.out.println(obj.toJSONString());
		
		
		
		
		given()
			.header("content-type","application/json")
			.body(obj.toJSONString()).
		when()
			.post("/ibmexample").
		then()
			.statusCode(201)
			.log().all();
	
			
		
	}
	
	@Test
	public void jsonobjexample()
	{

		RestAssured.baseURI="http://localhost:3000";
		
		JSONObject obj = new JSONObject();
		obj.put("id",0);
		obj.put("name", "abc");
		obj.put("status", "available");
		
		JSONObject catobj = new JSONObject();
		catobj.put("id", 0);
		catobj.put("name", "doggie");
		
		JSONObject tagobj = new JSONObject();
		tagobj.put("id", 0);
		tagobj.put("name", "doggie");
		
		obj.put("category", catobj);
		
		JSONArray arrayobj = new JSONArray();
		arrayobj.add("fish");
		arrayobj.add("dog");
		arrayobj.add("cat");
		
		
		JSONArray tagarrayobj = new JSONArray();
		tagarrayobj.add(tagobj);
		
		obj.put("photourls", arrayobj);
		
		obj.put("tags", tagarrayobj);
		
		
		
		
		System.out.println(obj.toJSONString());
		
	}

}
