import java.util.HashMap;
import java.util.Map;

public class A {
	public static void main(String[] args) {
		String a = "F:\\workspace2020\\spring-test\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp4\\wtpwebapps\\_24_boardWeb\\WEB-INF\\classes\\sql-map-config.xml=org.apache.ibatis.builder.xml.XMLConfigBuilder@4a8aa563, F:\\workspace2020\\spring-test\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp4\\wtpwebapps\\_24_boardWeb\\WEB-INF\\classes\\mappings\\board2-mapping.xml=org.apache.ibatis.builder.xml.XMLMapperBuilder@64bcdb9d, F:\\workspace2020\\spring-test\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp4\\wtpwebapps\\_24_boardWeb\\WEB-INF\\classes\\mappings\\board-mapping.xml=org.apache.ibatis.builder.xml.XMLMapperBuilder@6a7b397a, F:\\workspace2020\\spring-test\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp4\\wtpwebapps\\_24_boardWeb\\WEB-INF\\classes\\com\\springbook\\mybatis\\mapper\\MyMapper.xml=org.apache.ibatis.builder.xml.XMLMapperBuilder@6e44b0ed";
		System.out.println(a.replaceAll("\\\\", "/"));
		
        String urlPath = "";
        if (urlPath.startsWith("/")) {
        	urlPath = urlPath.substring(1);
        }
        Map<String, Object> configurationMap = new HashMap<>();
        configurationMap.put("A", 1);
        configurationMap.put("B", 1);
        configurationMap.put("C", 1);
        for (String key : configurationMap.keySet()) {
        	System.out.println(1);
        	if ("B".equals(key)) {
        		break;
        	}
		}
//        if (configurationMap.containsKey(urlPath)) {
//            
//        }
        try {
        	throw new Exception("KKK");
		} catch (Exception e) {
//			e.printStackTrace();
			StackTraceElement[] stackTraceArr = e.getStackTrace();
			for (StackTraceElement stackTraceElement : stackTraceArr) {
				System.out.println(stackTraceElement);
			}
		}
        
	}
}
