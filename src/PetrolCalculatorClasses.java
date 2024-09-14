import java.util.Scanner;

public class PetrolCalculatorClasses {
    public void calculate() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество действий => ");
        int iterationsCount = scanner.nextInt();
        for (int counter = 0; counter < iterationsCount; counter++) {
            System.out.print("Введите тип топлива: 1 - АИ-92, 2 - АИ-95 => ");
            int petrolType = scanner.nextInt();
            System.out.print("Введите количество литров => ");
            double razmer = scanner.nextDouble();
            if (petrolType == 1) {
                double price = new Ai92Calculator().calculate(razmer);
                System.out.println("Стоимость " + razmer + " литров АИ-92 = " + price + "₽");
            } else if (petrolType == 2) {
                double price = new Ai95Calculator().calculate(razmer);
                System.out.println("Стоимость " + razmer + " литров АИ-95 = " + price + "₽");
            }
        }
    }

    public static void main(String[] args) {
		System.out.println("Введённые аргументы:" + args);
        new PetrolCalculatorClasses().calculate();
    }
}
