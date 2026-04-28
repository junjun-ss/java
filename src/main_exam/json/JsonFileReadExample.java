package main_exam.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JsonFileReadExample {
    private static final String PERSON_FILE = "src/main_exam/json/person.json";
    private static final String PEOPLE_FILE = "src/main_exam/json/people.json";

    public static void main(String[] args) {
        Person person = readPerson(PERSON_FILE);
        List<Person> people = readPeople(PEOPLE_FILE);

        System.out.println(person);
        System.out.println(people);
    }

    public static Person readPerson(String filePath) {
        Gson gson = new Gson();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return gson.fromJson(reader, Person.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Person> readPeople(String filePath) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Person>>() {
        }.getType();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
