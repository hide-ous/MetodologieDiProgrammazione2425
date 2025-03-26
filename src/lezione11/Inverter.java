package lezione11;

import java.util.Arrays;

public class Inverter {

    public static void main(String[] args) {
        double[] a = {1.0, 2.0, 3.0, 4.0};
        int n = a.length;

        for (int i = 0; i < n/2; i++) {
            double temp = a[n-i-1];
            a[n-i-1] = a[i];
            a[i] = temp;
        }
        System.out.println(Arrays.toString(a));
    }
}
