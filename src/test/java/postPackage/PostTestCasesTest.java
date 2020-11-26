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


public class PostTestCasesTest {
	CreateUsers createUsers =new CreateUsers("BBS1", "TL1");
	CreateUsers createUsers1 =new CreateUsers("BBS1", "TL1","Pune");
	CreateUsers createUsers2 =new CreateUsers();
	Gson gson=new Gson();
	
	@Test
	public void createUserGson() {
		//String createUsersPayload=gson.toJson(createUsers);
		//Serilization: POJO to json string
		given().header("content-type", "application/json").body(gson.toJson(createUsers)).when().post("https://reqres.in/api/users").then().body("name", Matchers.equalTo("BBS1")).and().log().all();
	}
	
	@Test
	public void createUserJsonObject() {
		//Directly using JsonObject from Gson and creating json string to pass in body
		JsonObject jo=new JsonObject();
		jo.addProperty("name", "BBS2");
		jo.addProperty("job", "TL2");
		given().header("content-type", "application/json").body(jo.toString()).when().post("https://reqres.in/api/users").then().body("name", Matchers.equalTo("BBS2")).and().log().all();
	}
	
	@Test
	public void createUserJackson() {
		ObjectMapper om=new ObjectMapper();
		String payload="";
		try {
			payload = om.writeValueAsString(createUsers);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		given().header("content-type", "application/json").body(payload).when().post("https://reqres.in/api/users").then().body("name", Matchers.equalTo("BBS1"));
	}
	
	@Test
	public void createUserHM_Gson() {
		Map<String, String> map=new HashMap<String, String>();
		map.put("name", "BBS2");
		map.put("job", "TL2");
		
		Gson gson =new Gson();
		String payload=gson.toJson(map);
		given().header("content-type", "application/json").body(payload).when().post("https://reqres.in/api/users").then().body("name", Matchers.equalTo("BBS2")).and().log().all();
	}
	
	@Test
	public void createUserHM_GsonBuilder() {
		Map<String, String> map=new HashMap<String, String>();
		map.put("name", "BBS2");
		map.put("job", "TL2");
		GsonBuilder gsonBuilder=new GsonBuilder();
		Gson gson=gsonBuilder.create();
		Gson gsonPrettyr=gsonBuilder.setPrettyPrinting().create();
		//String payload=gson.toJson(map);
		//given().header("content-type", "application/json").body(gsonPrettyr.toJson(map)).when().post("https://reqres.in/api/users").then().body("name", Matchers.equalTo("BBS2")).and().log().all();
		//System.out.println("****************************************");
		System.out.println(gsonPrettyr.toJson(map));
		System.out.println(gson.toJson(map));
		//System.out.println(gson);
	}
	
	@Test
	public void createUsersNullFields() {
		Gson gson=new Gson();
		String javaObjInJsonForm=gson.toJson(createUsers1);
		System.out.println(javaObjInJsonForm);
	}
	
	/**
	 * Gson or GsonBuilder does not seriliaze fields having null values, but using GsonBuilder obj.serializeNulls method such fields can be serialize
	 */
	@Test
	public void createUsersNullFieldsGsonBuilder() {
		CreateUsersPojo createUsersPojo =new CreateUsersPojo();
		GsonBuilder gsonBuilder =new GsonBuilder();
		GsonBuilder gb=gsonBuilder.serializeNulls();
		//Gson gson=new Gson();
		Gson gson=gb.create();
		String javaObjInJsonForm=gson.toJson(createUsersPojo);
		System.out.println(javaObjInJsonForm);
	}
	
	/**
	 * Exclude fields withought Expose Annotations
	 */
	@Test
	public void createUsersExposeTest() {
		CreateUsersPojo createUsersPojo =new CreateUsersPojo();
		GsonBuilder gsonBuilder =new GsonBuilder();
		gsonBuilder.excludeFieldsWithoutExposeAnnotation();
		Gson gson=gsonBuilder.create();

		String javaObjInJsonForm=gson.toJson(createUsersPojo);
		System.out.println(javaObjInJsonForm);
	}
	
	/**
	 * //skip class from serilization if CreateUsersPojo class objects being used
	 */
	@Test
	public void createUsersExclusionStrategy() {
		CreateUsersPojo createUsersPojo =new CreateUsersPojo();
		CreateUsers createUsers =new CreateUsers("ABC","ABCjob");

		ExclusionStrategy es=new ExclusionStrategy() {

			@Override
			public boolean shouldSkipField(FieldAttributes f) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean shouldSkipClass(Class<?> clazz) {
				// TODO Auto-generated method stub
				//return clazz.equals(CreateUsersPojo.class);
				return clazz.equals(CreateUsers.class);
			}
			
		};
		GsonBuilder gsonBuilder =new GsonBuilder();
		gsonBuilder.setExclusionStrategies(es);
		Gson gson=gsonBuilder.create();
		
		String javaObjInJsonForm=gson.toJson(createUsersPojo);//skip class from serilization if CreateUsersPojo class objects being used
		//String javaObjInJsonForm=gson.toJson(createUsers);
		System.out.println(javaObjInJsonForm);
	}
	
	/**
	 * //skip fields from 
	 */
	@Test
	public void createUsersExclusionStrategyFields() {
		//CreateUsersPojo createUsersPojo =new CreateUsersPojo();
		CreateUsers createUsers =new CreateUsers("ABC","ABCjob");

		ExclusionStrategy es=new ExclusionStrategy() {

			@Override
			public boolean shouldSkipField(FieldAttributes f) {
				// TODO Auto-generated method stub
				return ((f.getDeclaringClass()==CreateUsers.class) && (f.getName().equals("ABC111")));
			}

			@Override
			public boolean shouldSkipClass(Class<?> clazz) {
				// TODO Auto-generated method stub
				return clazz.equals(CreateUsersPojo.class);
			}
			
		};
		GsonBuilder gsonBuilder =new GsonBuilder();
		gsonBuilder.setExclusionStrategies(es);
		Gson gson=gsonBuilder.create();
		
		String javaObjInJsonForm=gson.toJson(createUsers);//skip class from serilization if CreateUsersPojo class objects being used
		//String javaObjInJsonForm=gson.toJson(createUsers);
		System.out.println(javaObjInJsonForm);
	}




}
