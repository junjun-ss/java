package main_exam.json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class GsonParsingExample {
    public static void main(String[] args) {
        String personJson = "{\"name\":\"jun\",\"age\":30,\"city\":\"Seoul\"}";
        String peopleJson = "[{\"name\":\"kim\",\"age\":20,\"city\":\"Busan\"},{\"name\":\"lee\",\"age\":25,\"city\":\"Incheon\"}]";
        String nestedJson = "{\"result\":{\"name\":\"park\",\"age\":28,\"city\":\"Daegu\"}}";

        Person person = jsonStringToPerson(personJson);
        List<Person> people = jsonArrayToPeople(peopleJson);
        Person nestedPerson = nestedJsonToPerson(nestedJson);

        System.out.println(person);
        System.out.println(people);
        System.out.println(nestedPerson);
    }

    public static Person jsonStringToPerson(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, Person.class);
    }

    public static List<Person> jsonArrayToPeople(String jsonArrayString) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Person>>() {
        }.getType();
        return gson.fromJson(jsonArrayString, listType);
    }

    public static Person nestedJsonToPerson(String jsonString) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject root = parser.parse(jsonString).getAsJsonObject();
        JsonObject result = root.getAsJsonObject("result");
        return gson.fromJson(result, Person.class);
    }
}
