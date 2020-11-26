package package1;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class GetTestsTestNGDP_JsonSchemaValidation {
	
@Test(dataProvider = "getData")
public void getListOfUsersQueryParam(String pageNumber, String pageNumber2) {
	RestAssured.baseURI="https://reqres.in";
	RestAssured.basePath="/api/users";
	RequestSpecification reqSpec=RestAssured.given();
	reqSpec.queryParam("page", pageNumber);
	reqSpec.get().then().assertThat().body(matchesJsonSchemaInClasspath("jsonSchema.json"));
	System.out.println("Json Schema validation of the response is Passed");
	
}

@DataProvider
public Object[][] getData(){
	Object[][] obj=new Object[1][2];
	obj[0][0]="2";
	obj[0][1]="0";
	return obj;
}






}
