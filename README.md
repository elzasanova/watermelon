import java.util.Scanner;

class Rose {

    private final int x;
    private final int y;

    public Rose(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double distance() {
        return Math.sqrt(x * x + y * y);
    }
}

class PointAnalize {
    public static Rose webb(Rose[] mains) {
        Rose webb = mains[0];
        double maxDistance = webb.distance();
        for (int i = 0; i < mains.length; i++) {
            double distances = mains[i].distance();
            if (distances > maxDistance) {
                webb = mains[i];
            }
        }
        return webb;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Rose[] mains = new Rose[n];
        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            mains[i] = new Rose(x, y);
        }
        Rose webb = PointAnalize.webb(mains);
        System.out.print(webb.getX() + " ");
        System.out.println(webb.getY());
    }
