import java.util.Random;

public class Possion {
    public static int getPossion(double lamda) {
        Random random = new Random();
        return  Possion.getPossionVariable(lamda);
    }

    private static int getPossionVariable(double lamda) {
        int x = 0;
        double y = Math.random(), cdf = getPossionProbability(x, lamda);
        while (cdf < y) {
            x++;
            cdf += getPossionProbability(x, lamda);
        }
        return x;
    }

    private static double getPossionProbability(int k, double lamda) {
        double c = Math.exp(-lamda), sum = 1;
        for (int i = 1; i <= k; i++) {
            sum *= lamda / i;
        }
        return sum * c;
    }
}
