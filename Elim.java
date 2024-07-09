public class Elim {
    public static void gElim(){
        double[][] arr = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 4, -3, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 3, -2, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 1, -1, 0, 0, 0},
                {1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 0}
        };

        double[] sol = {364, 4, 16, 36, 64, 100, 79, 61, 0, 0, 0, -42};
        gaussElim(arr, sol);

        System.out.println("The solution matrix");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }


        System.out.println("**********************************");
        System.out.println("The COMPLETE solutions:");
        for (int i = 0; i < 12; i++) {
            System.out.println("x" + (i + 1) + " = " + Math.round(arr[i][12]));
        }
    }
    public static void gaussElim(double[][] a, double[] b) {
        int n = a.length;

        // Append b to A as the last column
        for (int i = 0; i < n; i++) {
            a[i][n] = b[i];
        }

        betterForwardElimination(a, n);
        backSubstitution(a, n);
        normalization(a, n);
    }

    private static void normalization(double[][] a, int n) {
        for (int i = 0; i < n; i++) {
            double divisor = a[i][i];
            for (int j = 0; j < n + 1; j++) {
                a[i][j] /= divisor;
            }
        }
    }

    private static void backSubstitution(double[][] a, int n) {
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                double ratio = a[i][j] / a[j][j];
                for (int k = j; k < n + 1; k++) {
                    a[i][k] -= a[j][k] * ratio;
                }
            }
        }
    }

    private static void betterForwardElimination(double[][] a, int n) {
        for (int i = 0; i < n - 1; i++) {
            int pivotRow = i;
            for (int j = i + 1; j < n; j++) {
                // Find the row with the largest pivot element
                if (Math.abs(a[j][i]) > Math.abs(a[pivotRow][i])) {
                    pivotRow = j;
                }
            }

            // Swap rows (pivotRow and i)
            double[] temp = a[i];
            a[i] = a[pivotRow];
            a[pivotRow] = temp;

            for (int j = i + 1; j < n; j++) {
                double ratio = a[j][i] / a[i][i];
                for (int k = i; k < n + 1; k++) {
                    a[j][k] -= a[i][k] * ratio;
                }
            }
        }
    }
}
