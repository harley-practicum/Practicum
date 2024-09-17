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

    // Метод для генерации случайного индекса от 0 до размера коллекции - 1
    private int getRandomIndex(int size) {
        return random.nextInt(size);  // Генерация числа от 0 до size - 1
    }

    // Метод для генерации комбинаций
    public List<List<String>> generateCombinations(List<String> types, int numCombinations) {
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
        return combinations;
    }
}















