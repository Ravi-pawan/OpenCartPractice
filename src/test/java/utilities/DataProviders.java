package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		String path=("C:\\Users\\Ravi Yadav\\eclipse-workspace1\\OpenCartPractice\\testData\\opencartLoginData.xlsx");
		ExcelUtility xlutil=new ExcelUtility(path);
		int rows=xlutil.getRowCount("Sheet1");
		int cols=xlutil.getCellCount("Sheet1", 1);
		String loginData[][]=new String[rows][cols];
		for(int r=1;r<=rows;r++)
		{
			for(int c=0;c<cols;c++)
			{
				loginData[r-1][c]=xlutil.getCellData("sheet1", r, c);
			}
		}
		return loginData;
		
		//dataprovider 2
	
	}
}
