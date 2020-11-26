package postPackage;
import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import postPackagePOJOs.CreateUsers;

public class GsonTests {
	//CreateUsers createUsers=new CreateUsers("Brij", "TL");
	Gson gson=new Gson();
	
	@Test
	public void createUserGsonPojo() {
		CreateUsers createUsers=new CreateUsers("Brij", "TL");
		//Gson gson=new Gson();
		given().header("content-type","application/json").body(gson.toJson(createUsers)).when().post("https://reqres.in/api/users").then().log().all();
	}
	
	@Test
	public void createUserGsonHashMap() {
		HashMap<String, String> hm=new HashMap<>();
		hm.put("name", "BrijBhan");
		hm.put("job", "TL2");
		given().header("content-type","application/json").body(gson.toJson(hm)).when().post("https://reqres.in/api/users").then().log().all();

	}
	
	@Test
	public void createUserExcludeClassSr() {
		ExclusionStrategy es=new ExclusionStrategy() {

			@Override
			public boolean shouldSkipField(FieldAttributes f) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean shouldSkipClass(Class<?> clazz) {
				// TODO Auto-generated method stub
				return clazz.equals(CreateUsers.class);
			}
			
		};
		CreateUsers createUsers1=new CreateUsers("Brij", "TL");
		//Gson gson=new Gson();
		GsonBuilder gb=new GsonBuilder();
		gb.setExclusionStrategies(es);
		Gson gbObject=gb.create();
		given().header("content-type","application/json").body(gbObject.toJson(createUsers1)).when().post("https://reqres.in/api/users").then().log().all();
	
	}

}
