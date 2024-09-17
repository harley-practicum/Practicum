import java.util.*;

public class DinnerConstructor {
    private final Map<String, List<String>> dishesByType = new HashMap<>();
    private final Random random = new Random();

    // Метод для добавления нового блюда
    public void addDish(String type, String name) {
        dishesByType.putIfAbsent(type, new ArrayList<>());
        dishesByType.get(type).add(name);
    }

    // Метод для проверки существования типа блюда
    public boolean checkType(String type) {
        return dishesByType.containsKey(type);
    }

    // Метод для генерации комбинаций блюд
    public String generateCombinations(int numCombinations, List<String> types) {
        List<List<String>> combinations = new ArrayList<>();
        for (int i = 0; i < numCombinations; i++) {
            List<String> combination = new ArrayList<>();
            for (String type : types) {
                List<String> dishes = dishesByType.get(type);
                if (dishes != null && !dishes.isEmpty()) {
                    int randomIndex = getRandomIndex(dishes.size());
                    String randomDish = dishes.get(randomIndex);
                    combination.add(randomDish);
                }
            }
            combinations.add(combination);
        }
        return formatCombinations(combinations);
    }

    // Метод для получения случайного индекса
    public int getRandomIndex(int size) {
        return random.nextInt(size);
    }

    // Метод для форматирования комбинаций в строку
    private String formatCombinations(List<List<String>> combinations) {
        String result = "";
        for (int i = 0; i < combinations.size(); i++) {
            result += "Комбинация " + (i + 1) + ":\n";
            for (String dish : combinations.get(i)) {
                result += dish + "\n";
            }
            result += "\n"; // Пустая строка для разделения комбинаций
        }
        return result;
    }

    // Метод для обработки ввода и генерации комбинаций
    public String handleGenerateCombinations(int numCombinations, List<String> types) {
        if (types.isEmpty()) {
            return "Не введены типы блюд.";
        }
        for (String type : types) {
            if (!checkType(type)) {
                return "Тип блюда \"" + type + "\" не существует.";
            }
        }
        return generateCombinations(numCombinations, types);
    }
}