package postPackage;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import postPackagePOJOs.CreateUsers;
import postPackagePOJOs.CreateUsersPojo;
import postPackagePOJOs.HeadersHostsPojo;


public class PostHeadersTest {
	HeadersHostsPojo hp=new HeadersHostsPojo("application/json", "test123");
	CreateUsers cu=new CreateUsers("Brij","TL1");
	Gson gson=new Gson();
	
	@Test
	public void createUserGson() {
		//String createUsersPayload=gson.toJson(createUsers);
		given().header("content-type", hp.getContentType()).body(gson.toJson(cu)).when().post(hp.host+hp.createUserPath).then().body("name", Matchers.equalTo("Brij")).and().log().all();
	}
	
	



}
