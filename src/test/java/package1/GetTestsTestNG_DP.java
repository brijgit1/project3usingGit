package package1;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetTestsTestNG_DP {
	
//@Test(dataProvider = "getData")
public void getListOfUsersQueryParam(String pageNumber, String pageNumber2) {
	RestAssured.baseURI="https://reqres.in";
	RestAssured.basePath="/api/users";
	RequestSpecification reqSpec=RestAssured.given();
	reqSpec.queryParam("page", pageNumber);
	String respInString=reqSpec.get().body().asString();
	System.out.println(respInString);
}

@Test(dataProvider = "getData")
public void getListOfUsersParseJsonResponse(String pageNumber, String pageNumber2) {
	RestAssured.defaultParser=Parser.JSON;
	RestAssured.baseURI="https://reqres.in";
	RestAssured.basePath="/api/users";
	RequestSpecification reqSpec=RestAssured.given();
	reqSpec.queryParam("page", pageNumber);
	
	List<String> items=reqSpec.get().then().contentType(ContentType.JSON).extract().response().jsonPath().getList("$");
	System.out.println(items.size());
}

@DataProvider
public Object[][] getData(){
	Object[][] obj=new Object[3][2];
	obj[0][0]="2";
	obj[0][1]="0";
	return obj;
}






}
