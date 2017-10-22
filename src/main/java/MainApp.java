import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        // read input
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int d_ij = s.nextInt();

                // use or store the distance d_ij in some way
            }
        }

        // compute things
        // ???

        // write output
        for (int k = 0; k < n; k++) {
            int p1 = 1; // first point
            int p2 = 1; // second point
            int d = 0;  // distance
            System.out.println(p1 + " " + p2 + " " + d);
        }
    }
}
