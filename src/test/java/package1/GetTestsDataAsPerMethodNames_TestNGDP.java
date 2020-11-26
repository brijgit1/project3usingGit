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
import io.restassured.specification.RequestSpecification;
import java.lang.reflect.Method;
public class GetTestsDataAsPerMethodNames_TestNGDP {
	
@Test(dataProvider = "getData")
public void getListOfUsersQueryParam(String pageNumber, String pageNumber2) {
	RestAssured.baseURI="https://reqres.in";
	RestAssured.basePath="/api/users";
	RequestSpecification reqSpec=RestAssured.given();
	reqSpec.queryParam("page", pageNumber);
	String respInString=reqSpec.get().body().asString();
	System.out.println(respInString);
}

@Test(dataProvider = "getData")
public void getListOfUsersQueryParam1(String pageNumber, String pageNumber2) {
	RestAssured.baseURI="https://reqres.in";
	RestAssured.basePath="/api/users";
	RequestSpecification reqSpec=RestAssured.given();
	reqSpec.queryParam("page", pageNumber);
	String respInString=reqSpec.get().body().asString();
	System.out.println(respInString);
}

@DataProvider
public Object[][] getData(Method m){
	if(m.getName().equalsIgnoreCase("getListOfUsersQueryParam")) {
		Object[][] obj=new Object[2][2];
		obj[0][0]="2";
		obj[0][1]="0";
		obj[1][0]="2";
		obj[1][1]="0";
		return obj;
	}else {
		Object[][] obj1=new Object[2][2];
	obj1[0][0]="1";
	obj1[0][1]="0";
	obj1[1][0]="1";
	obj1[1][1]="0";
	return obj1;
	}
	
}

}
