package marketplace.data;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDataReader {
	
	public static <T> List<T> readJsonFile(String filePath, Class<T> classData) throws StreamReadException, DatabindException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		
		return objectMapper.readValue(new File(filePath), objectMapper.getTypeFactory().constructCollectionType(List.class, classData));
	}

}
