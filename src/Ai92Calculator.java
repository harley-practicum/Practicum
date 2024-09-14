public class Ai92Calculator {
    double price = 42.74;
    double tax = 0.13;
    double fullPrice = price * (1 + tax);

    public double calculate(double razmer) {
        return razmer * fullPrice;
    }
}
