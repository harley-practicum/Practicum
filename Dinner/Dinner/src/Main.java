import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DinnerConstructor dinnerConstructor = new DinnerConstructor();

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Добавить блюдо");
            System.out.println("2. Сгенерировать комбинации");
            System.out.println("3. Выход");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addDish();
                    break;
                case "2":
                    generateCombinations();
                    break;
                case "3":
                    System.out.println("Программа завершена.");
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    // Метод для добавления нового блюда
    private static void addDish() {
        System.out.println("Введите тип блюда:");
        String type = scanner.nextLine();
        System.out.println("Введите название блюда:");
        String name = scanner.nextLine();
        dinnerConstructor.addDish(type, name);
        System.out.println("Блюдо добавлено.");
    }

    // Метод для генерации комбинаций
    private static void generateCombinations() {
        System.out.println("Введите количество комбинаций:");
        int numCombinations = Integer.parseInt(scanner.nextLine());
        List<String> types = new ArrayList<>();
        System.out.println("Введите типы блюд. Для завершения нажмите Enter на пустой строке.");
        while (true) {
            String type = scanner.nextLine();
            if (type.isEmpty()) break;
            if (!dinnerConstructor.checkType(type)) {
                System.out.println("Тип блюда не существует, попробуйте снова.");
            } else {
                types.add(type);  // Повторяющиеся типы разрешены
            }
        }
        List<List<String>> combinations = dinnerConstructor.generateCombinations(types, numCombinations);

        // Вывод каждой комбинации в столбик
        for (int i = 0; i < combinations.size(); i++) {
            System.out.println("Комбинация " + (i + 1) + ":");
            for (String dish : combinations.get(i)) {
                System.out.println(dish);
            }
            System.out.println(); // Пустая строка для разделения комбинаций
        }
    }
}


