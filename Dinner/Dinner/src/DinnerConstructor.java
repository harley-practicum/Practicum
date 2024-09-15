import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DinnerConstructor {
    private Map<String, List<String>> dishesByType;
    private Random random;

    public DinnerConstructor(Random random) {
        this.random = random;
        this.dishesByType = new HashMap<>();
        dishesByType.put("первое", new ArrayList<>());
        dishesByType.put("второе", new ArrayList<>());
        dishesByType.put("напиток", new ArrayList<>());
    }

    public void addDish(String type, String name) {
        String lowerCaseType = type.toLowerCase();
        if (dishesByType.containsKey(lowerCaseType)) {
            dishesByType.get(lowerCaseType).add(name);
        } else {
            System.out.println("Неизвестный тип блюда. Игнорирую.");
        }
    }

    public void generateCombinations(int count, List<String> types) {
        if (types.isEmpty()) {
            System.out.println("Необходимо ввести хотя бы один тип блюда.");
            return;
        }

        List<String> availableDishes = new ArrayList<>();
        for (String type : types) {
            List<String> dishes = dishesByType.get(type.toLowerCase());
            if (dishes != null) {
                availableDishes.addAll(dishes);
            } else {
                System.out.println("Неизвестный тип блюда: " + type);
            }
        }

        if (availableDishes.isEmpty()) {
            System.out.println("Нет доступных блюд для указанных типов.");
            return;
        }

        for (int i = 0; i < count; i++) {
            String combination = "";
            for (String type : types) {
                combination += getRandomDish(type) + ", ";
            }
            if (combination.length() > 0) {
                combination = combination.substring(0, combination.length() - 2); // Удаляем последнюю запятую и пробел
            }
            System.out.println("Комбинация " + (i + 1) + ": " + combination);
        }
    }

    private String getRandomDish(String type) {
        List<String> dishes = dishesByType.get(type.toLowerCase());
        if (dishes != null && !dishes.isEmpty()) {
            return dishes.get(random.nextInt(dishes.size()));
        } else {
            return "Нет доступных блюд";
        }
    }
}













