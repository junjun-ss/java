package exam._02_json_gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class GsonCastingExample {
    private static final String PERSON_JSON_FILE = "src/exam/_02_json_gson/person.json";
    private static final Gson GSON = new Gson();

    public static JsonObject stringToJson(String jsonString) {
        JsonParser jsonParser = new JsonParser();
        return jsonParser.parse(jsonString).getAsJsonObject();
    }

    public static String jsonToString(JsonObject json) {
        return GSON.toJson(json);
    }

    public static Person stringToPojo(String jsonString) {
        return GSON.fromJson(jsonString, Person.class);
    }

    public static Person readJsonFile() {
        return readJsonFile(PERSON_JSON_FILE, Person.class);
    }

    public static <T> T readJsonFile(String filePath, Class<T> type) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return GSON.fromJson(reader, type);
        } catch (IOException e) {
            throw new IllegalStateException("JSON 파일 읽기 실패: " + filePath, e);
        }
    }

    public static Person extractInTwoDepth(String jsonString) {
        JsonObject jsonObject = GSON.fromJson(jsonString, JsonObject.class);

        JsonObject testObject = jsonObject.getAsJsonObject("test");
        return GSON.fromJson(testObject, Person.class);
    }
    
    public static List<Person> stringToPojoJsonList(String jsonString) {
        JsonObject jsonObject = GSON.fromJson(jsonString, JsonObject.class);

        JsonArray testArray = jsonObject.getAsJsonArray("test");
        List<Person> personList = new ArrayList<>();
        
        for (JsonElement element : testArray) {
            JsonObject testObject = element.getAsJsonObject();
            Person person = GSON.fromJson(testObject, Person.class);

            personList.add(person);
        }
        
        return personList;
    }
    
    public static List<Person> stringToPojoJsonListByType(String jsonString) {
        Type listType = new TypeToken<List<Person>>() {}.getType();
        return GSON.fromJson(jsonString, listType);
    }
    
    public static List<Person> stringToPojoJsonArray(String jsonString) {
        return stringToPojoJsonListByType(jsonString);
    }
    
    public static void main(String[] args) {
        String jsonString = "{\"name\":\"John\",\"age\":30}";
        String twoDepthJsonString = "{\"test\": {\"name\":\"John\",\"age\":30}}";
        String jsonListString = "{\"test\" : [{\"name\":\"jiun\", \"age\":27}, {\"name\":\"haeun\", \"age\":28}] }";
        String jsonArrayString = "[{\"name\":\"jiun\", \"age\":27}, {\"name\":\"haeun\", \"age\":28}]";
        
        JsonObject json = stringToJson(jsonString);
        System.out.println("String to JSON:");
        System.out.println(json);

        String str = jsonToString(json);
        System.out.println("JSON to String:");
        System.out.println(str);
        
        Person person = extractInTwoDepth(twoDepthJsonString);
        System.out.println(person.getName());
        
        List<Person> personList = stringToPojoJsonListByType(jsonArrayString);
        System.out.println(personList.get(0).getAge());

        personList = stringToPojoJsonArray(jsonArrayString);
        System.out.println(personList.get(0).getAge());
        

        personList = stringToPojoJsonList(jsonListString);
        System.out.println(personList.get(0).getAge());
        
        person = stringToPojo(jsonString);
        System.out.println("String to POJO:");
        System.out.println(person.getName());

        Person filePerson = readJsonFile();
        if (filePerson != null) {
            System.out.println("JSON file to POJO:");
            System.out.println(filePerson.getName() + " / " + filePerson.getAge());
        }
    }
}
