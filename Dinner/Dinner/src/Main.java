import java.util.*;
//это harley.andrey оперативка подвела и ссылка на репозиторий: https://github.com/harley-andrey/practicum
// надеюсь все правильно сделал и не прийдется переделывать, а идти дальше
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DinnerConstructor dinnerConstructor = new DinnerConstructor();

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Добавить блюдо");
            System.out.println("2. Сгенерировать комбинации");
            System.out.println("3. Выход");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    handleAddDish();
                    break;
                case "2":
                    handleGenerateCombinations();
                    break;
                case "3":
                    System.out.println("Программа завершена.");
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }
    // Метод для обработки добавления нового блюда
    private static void handleAddDish() {
        System.out.println("Введите тип блюда:");
        String type = scanner.nextLine().trim().toLowerCase();
        System.out.println("Введите название блюда:");
        String name = scanner.nextLine().trim();
        dinnerConstructor.addDish(type, name);
        System.out.println("Блюдо добавлено.");
    }
    // Метод для обработки генерации комбинаций
    private static void handleGenerateCombinations() {
        System.out.println("Введите количество комбинаций:");
        int numCombinations = Integer.parseInt(scanner.nextLine().trim());
        List<String> types = new ArrayList<>();
        System.out.println("Введите типы блюд. Для завершения нажмите Enter на пустой строке.");
        while (true) {
            String type = scanner.nextLine().trim().toLowerCase();
            if (type.isEmpty()) break;
            types.add(type);
        }
        String result = dinnerConstructor.handleGenerateCombinations(numCombinations, types);
        System.out.println(result);
    }
}