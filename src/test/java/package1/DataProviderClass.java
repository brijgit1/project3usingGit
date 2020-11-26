package package1;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
	
	@DataProvider
	public static Object[][] getData(){
		Object[][] obj=new Object[3][2];
		obj[0][0]="2";
		obj[0][1]="0";
		obj[1][0]="2";
		obj[1][1]="0";
		obj[2][0]="2";
		obj[2][1]="0";
		return obj;
	}


}
