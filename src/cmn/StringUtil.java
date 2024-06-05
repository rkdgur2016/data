package cmn;

import java.util.UUID;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class StringUtil {
	
	public static String nvl(String value, String defaultValue) {
		if(null == value || value.trim().isEmpty()) {
			return defaultValue;
		}
		return value;
	}
	
	public static String getUUID() {
		UUID uuidTemp = UUID.randomUUID();
		
		return uuidTemp.toString().replaceAll("-","");
	}
}
