package base;

import java.util.Date;

public interface FilePaths {
	
	String PROPERTY_FILE="./src/test/resources/commondata.properties";
	String EXECL_FILE = "";
	String EXTENT_REPORT_PATH="./reports/ExtentReport"+getSystemDate()+".html";
	
	static String getSystemDate() {
		return new Date().toString().replace(" ", "_").replace(":", "_");
	}

}
