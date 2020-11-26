package package1;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ParseJsonResponse {
	
@Test
public void getUsersJsonResponseParse() {
	RestAssured.defaultParser=Parser.JSON;
	RestAssured.baseURI="https://reqres.in";
	RestAssured.basePath="/api/users";
	RequestSpecification reqSpec=RestAssured.given();
	reqSpec.queryParam("page", 2);
	//reqSpec.get().then().log().all();
	//System.out.println(resp.asString());
	Response respObj=reqSpec.get().then().contentType(ContentType.JSON).extract().response();
	List<String> respInList=respObj.jsonPath().get("data");//get data array
	String respInStr=respObj.jsonPath().getString("data"); //get data array
	System.out.println(respInList);
	System.out.println(respInStr);
	
	//System.out.println(respObj.jsonPath().get("data[0]"));//get 1st element of data array
	List<Object> elem=respObj.jsonPath().getList(" ");
	System.out.println(elem.get(0));
}

}
