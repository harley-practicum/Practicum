public class ConsoleApp {

    public static void main(String[] args) {
        String symbol = "*";

        int width = 10;
        int height = 5;
        boolean needBorder = false;
        String borderSymbol = "S";

        if (args.length > 0) {
            for (int i = 0; i < args.length; i += 1) {
                String token = args[i];

                if (token.equals("--width")) {
                    width = Integer.parseInt(args[i + 1]);
                } else if (token.equals("--height")) {
                    height = Integer.parseInt(args[i + 1]);
                } else if (token.equals("--border")) {
                    needBorder = true;
                }
            }
        }

        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= width; j++) {
                if ( needBorder
                        && ( i == 1 || j == 1 || i == height || j == width)) {
                    System.out.print(borderSymbol);
                } else {
                    System.out.print(symbol);
                }
            }
            System.out.println();
        }
    }
}
