import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DinnerConstructor dinnerConstructor = new DinnerConstructor(); // Создаём объект конструктора
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1 - Добавить блюдо");
            System.out.println("2 - Сгенерировать комбинации");
            System.out.println("3 - Выход");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Введите тип блюда (первое, второе, напиток):");
                    String type = scanner.nextLine();
                    System.out.println("Введите название блюда:");
                    String name = scanner.nextLine();
                    dinnerConstructor.addDish(type, name);
                    break;

                case 2:
                    System.out.println("Введите количество комбинаций:");
                    int count = scanner.nextInt();
                    dinnerConstructor.generateCombinations(count);
                    break;

                case 3:
                    System.out.println("Выход из программы.");
                    return;

                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
}

