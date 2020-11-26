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

public class GetTestsTestNG_DPInDiffClass {
	
@Test(dataProvider = "getData",dataProviderClass = DataProviderClass.class)
public void getListOfUsersQueryParam(String pageNumber, String pageNumber2) {
	RestAssured.baseURI="https://reqres.in";
	RestAssured.basePath="/api/users";
	RequestSpecification reqSpec=RestAssured.given();
	reqSpec.queryParam("page", pageNumber);
	String respInString=reqSpec.get().body().asString();
	System.out.println(respInString);
}
}
