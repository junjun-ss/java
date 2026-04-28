package exam._90_ai_model_stub;

import java.util.HashMap;
import java.util.Map;

public class Predictor {
    private final Map<String, String> rules = new HashMap<>();

    public Predictor() {
        rules.put("error", "장애");
        rules.put("fail", "장애");
        rules.put("success", "정상");
        rules.put("ok", "정상");
    }

    public static void main(String[] args) {
        Predictor predictor = new Predictor();
        System.out.println(predictor.predict("api success"));
        System.out.println(predictor.score("api success"));
    }

    public String predict(String input) {
        String lower = input.toLowerCase();
        for (Map.Entry<String, String> entry : rules.entrySet()) {
            if (lower.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return "미분류";
    }

    public int score(String input) {
        String lower = input.toLowerCase();
        int score = 0;
        for (String keyword : rules.keySet()) {
            if (lower.contains(keyword)) {
                score++;
            }
        }
        return score;
    }
}
