public class Ai95Calculator {
    double price = 46.35;
    double tax = 0.14;
    double fullPrice = price * (1 + tax);

    public double calculate(double razmer) {
        return razmer * fullPrice;
    }
}
