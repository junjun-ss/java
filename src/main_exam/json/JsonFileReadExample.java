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
    private static final Gson GSON = new Gson();

    public static void main(String[] args) {
        Person person = readPerson(PERSON_FILE);
        List<Person> people = readPeople(PEOPLE_FILE);

        System.out.println(person);
        System.out.println(people);
    }

    public static Person readPerson(String filePath) {
        return readJsonFile(filePath, Person.class);
    }

    public static List<Person> readPeople(String filePath) {
        Type listType = new TypeToken<List<Person>>() {
        }.getType();
        return readJsonFile(filePath, listType);
    }

    public static <T> T readJsonFile(String filePath, Class<T> type) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return GSON.fromJson(reader, type);
        } catch (IOException e) {
            throw new IllegalStateException("JSON 파일 읽기 실패: " + filePath, e);
        }
    }

    public static <T> T readJsonFile(String filePath, Type type) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return GSON.fromJson(reader, type);
        } catch (IOException e) {
            throw new IllegalStateException("JSON 파일 읽기 실패: " + filePath, e);
        }
    }
}
