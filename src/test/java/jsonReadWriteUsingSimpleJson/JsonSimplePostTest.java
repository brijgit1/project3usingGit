package jsonReadWriteUsingSimpleJson;

import org.testng.annotations.Test;

import postPackagePOJOs.HeadersHostsPojo;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonSimplePostTest {
	HeadersHostsPojo headersHostsPojo =new HeadersHostsPojo("application/json", "test");
	
	@Test
	public void createUserUsingJsonFilePayload() throws IOException, ParseException {
		JSONParser jp=new JSONParser();
		Reader reader =new FileReader(System.getProperty("user.dir")+"/resources/createUserPayload.json");
		Object obj=jp.parse(reader);
		JSONObject jo=(JSONObject)obj;
		System.out.println(jo.get("name"));
		System.out.println(jo.get("job"));
		System.out.println(jo.toString());
		//given().header("content-type", "application/json").body(body)
	}
	
	@Test
	public void createUserUsingFilePayload() throws IOException, ParseException {
		JSONParser jp=new JSONParser();
		Reader reader =new FileReader(System.getProperty("user.dir")+"/resources/createUserPayload.json");
		Object obj=jp.parse(reader);
		JSONObject jo=(JSONObject)obj;
		//System.out.println(jo.get("name"));
		//System.out.println(jo.get("job"));
		//System.out.println(jo.toString());
		given().header("content-type", headersHostsPojo.getContentType())
		.body(jo.toString()).when().post(headersHostsPojo.host+headersHostsPojo.createUserPath)
		.then().assertThat().statusCode(201).and().log().all();
		
	}
	
	@Test
	public void writeIntojsonFile() throws IOException {
		JSONObject jo=new JSONObject();
		jo.put("name", "BrijBhanSingh1");
		jo.put("job", "TEstLead1");
		//jo.put("role", "Programmer");
		
		FileWriter fw=new FileWriter(System.getProperty("user.dir")+"/resources/file1.json");
		fw.write(jo.toString());
		fw.flush();
		fw.close();
		
		
	}

}
