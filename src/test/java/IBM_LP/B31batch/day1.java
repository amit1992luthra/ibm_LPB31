package IBM_LP.B31batch;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.io.IOException;

import io.restassured.response.Response;
import junit.framework.Assert;

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
	
	@Test(enabled = false)
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
	
	
	@Test(enabled=false)
	public void example1(ITestContext val)
	{
		
		RestAssured.baseURI="http://localhost:3000";
		
		JSONObject obj = new JSONObject();
		obj.put("firstname", "amit");
		obj.put("lastname", "xyz");
		obj.put("place", "delhi");
		
		Response res = given()
			.contentType(ContentType.JSON)
			.body(obj.toJSONString()).
		when()
			.post("/ibmexample").
		then()
		    .statusCode(201)
		    .log().all().extract().response();
		
		
		String id = res.jsonPath().getString("id");
		String fname = res.jsonPath().getString("firstname");
		
		Assert.assertEquals("xyz", fname);
		
		
		System.out.println(id);
		System.out.println(fname);
		
		
		val.setAttribute("keyname", id);
		val.setAttribute("fname", fname);
		
	}
	
	@Test(enabled=false,dependsOnMethods="example1")
	public void example2(ITestContext val1)
	{
		RestAssured.baseURI="http://localhost:3000";
		String id = val1.getAttribute("keyname").toString();
		given()
			.get("/ibmexample/"+id).
		then()
		    .statusCode(200)
		    .log().all();
		System.out.println(val1.getAttribute("fname").toString());
		
	}
	
	@Test(enabled=false)
	public void queryparam()
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		
		given()
			.queryParam("username", "amit")
			.queryParam("password", "123456")
			.log().all().
		when().
			get("/user/login").
		then()
		    .statusCode(200)
		    .log().all();
	}
	
	@DataProvider(name="data")
	public Object[][]testdata1()
	{
		Object[][] obj = new Object[2][3];
		obj[0][0]="amit";
		obj[0][1]="luthra";
		obj[0][2]="delhi";
		obj[1][0]="ankit";
		obj[1][1]="mishra";
		//obj[1][2]="chennai";
		
		return obj;
		
	}
	
	@DataProvider(name="testdata")
	public Object[][]testdata2() throws IOException
	{
		Object[][] obj  = testdata.testdata();
		
		return obj;
		
	}
	
	
	@Test(enabled=true,dataProvider="testdata")
	public void dataproviderexample1(String fname, String lname,String place)
	{
		
		RestAssured.baseURI="http://localhost:3000";
		
		JSONObject obj = new JSONObject();
		obj.put("firstname", fname);
		obj.put("lastname", lname);
		obj.put("place", place);
		
		 given()
			.contentType(ContentType.JSON)
			.body(obj.toJSONString()).
		when()
			.post("/ibmexample").
		then()
		    .statusCode(201)
		    .log().all();
	}
	
	

}
