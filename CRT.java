import java.util.Scanner;

public class CRT {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of congruences: ");
        int numCongruences = scanner.nextInt();

        int[] a = new int[numCongruences];
        int[] n = new int[numCongruences];

        for (int i = 0; i < numCongruences; i++) {
            System.out.print("Enter a_" + (i + 1) + ": ");
            a[i] = scanner.nextInt();
            System.out.print("Enter n_" + (i + 1) + ": ");
            n[i] = scanner.nextInt();
        }

        int x = chineseRemainderTheorem(a, n);
        System.out.println("The solution is x = " + x);
    }

    // Function to calculate the greatest common divisor (GCD) of two numbers
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    // Function to calculate the modular multiplicative inverse using the extended Euclidean algorithm
    public static int modInverse(int a, int m) {
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return 1;
    }

    // Function to apply the Chinese Remainder Theorem
    public static int chineseRemainderTheorem(int[] a, int[] n) {
        int N = 1;
        for (int i = 0; i < n.length; i++) {
            N *= n[i];
        }

        int result = 0;

        for (int i = 0; i < a.length; i++) {
            int ni = N / n[i];
            int xi = modInverse(ni, n[i]);
            result += a[i] * ni * xi;
        }

        return result % N;
    }
}

