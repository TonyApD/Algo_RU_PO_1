import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        // read input
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        System.out.println("n: " + n);
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int d_ij = s.nextInt();
                matrix[i][j] = d_ij;
                // use or store the distance d_ij in some way
            }
        }

        //filter the matrix by removing double paths
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                matrix[i][j] = (int) Double.MAX_VALUE;
            }
        }

        // compute things
        // ???

        // write output
        for (int k = 0; k < n; k++) {
            int p1 = 1; // first point
            int p2 = 2; // second point
            int d = matrix[p1][p2];  // distance
            System.out.println(p1 + " " + p2 + " " + d);
        }
    }
}
