package postPackage;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import postPackagePOJOs.CreateUsers;

public class JacksonTest {
	
	/**
	 * POJO to JSON
	 */
	@Test
	public void PojoToJsonUsingJackson() throws JsonGenerationException, JsonMappingException, IOException {
		CreateUsers createUsers =new CreateUsers("BBsingh5", "TestLead5");
		ObjectMapper objectMapper=new ObjectMapper();
		File file=new File("./src/test/resources/file2.json");
		objectMapper.writeValue(file, createUsers);
		given().header("content-type","application/json").body(file).that().post("https://reqres.in/api/users").then().log().all();
	}
	
	/**
	 * JSON to POJO using Jackson
	 */
	@Test
	public void JsontoPOJOUsingJackson() throws JsonGenerationException, JsonMappingException, IOException {
		//CreateUsers createUsers =new CreateUsers("BBsingh5", "TestLead5");
		ObjectMapper objectMapper=new ObjectMapper();
		File file=new File("./src/test/resources/file2.json");
		CreateUsers createUsers=objectMapper.readValue(file, CreateUsers.class); //accepts file, or jsonstr or URL form
		System.out.println(createUsers);
		given().header("content-type","application/json").body(createUsers).that().post("https://reqres.in/api/users").then().log().all();
	}
	
	@Test
	public void JsontoPOJOUsingJacksonUsingURL() throws JsonGenerationException, JsonMappingException, IOException {
		//CreateUsers createUsers =new CreateUsers("BBsingh5", "TestLead5");
		ObjectMapper objectMapper=new ObjectMapper();
		URL file=new URL("file:src/test/resources/file2.json");
		System.out.println(file);
		CreateUsers createUsers=objectMapper.readValue(file, CreateUsers.class); //accepts file, or jsonstr or URL form
		//System.out.println(createUsers);
		given().header("content-type","application/json").body(createUsers).that().post("https://reqres.in/api/users").then().log().all();
	}

}
