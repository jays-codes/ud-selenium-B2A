package jayslabs.test.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDataReader {

	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		
		String fstr = System.getProperty("user.dir") + "\\src\\test\\java\\jayslabs\\test\\data\\PurchaseOrder.json";
		
		String jsonstr = FileUtils.readFileToString(new File(fstr), StandardCharsets.UTF_8);
		
		//using jackson databind to convert json str to HashMap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data 
			= mapper.readValue(
					jsonstr, 
					new TypeReference<List<HashMap<String, String>>>(){
			
		});
		
		return data;
	}

}
