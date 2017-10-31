package validation;

import Kruskal.Edge;

import java.util.ArrayList;
import java.util.List;

public class EdgeListValidator {

    private static ArrayList<Edge> expectedCopy = new ArrayList<>();
    private static ArrayList<Edge> actualCopy = new ArrayList<>();

    public static boolean validate(List<Edge> expected, List<Edge> actual) {
        expectedCopy.addAll(expected);
        actualCopy.addAll(actual);
        for (Edge e : expected) {
            int e1 = e.getVertex1();
            int e2 = e.getVertex2();
            int eweight = e.getWeight();
            for (Edge a : actual) {
                int a1 = a.getVertex1();
                int a2 = a.getVertex2();
                int aweight = a.getWeight();
                if ((e1 == a1 && e2 == a2 && eweight == aweight) || (e1 == a2 && e2 == a1 && eweight == aweight)) {
                    //System.out.println("Match at " + e1 + " " + e2 + " " + eweight);
                    expectedCopy.remove(e);
                    actualCopy.remove(a);
                }
            }
        }
        if (!expectedCopy.isEmpty() || !actualCopy.isEmpty()) {
            Edge ex = null;
            Edge ac = null;
            for (Edge e : expectedCopy) {
                ex = e;
                System.out.println("No match found for " + e.getVertex1() + " " + e.getVertex2() + " " + e.getWeight());
            }
            for (Edge e : actualCopy) {
                ac = e;
                System.out.println("Edge " + e.getVertex1() + " " + e.getVertex2() + " " + e.getWeight() + " was not expected");
            }
            if (ex != null && ac != null && ex.getWeight() == ac.getWeight()) {
                System.out.println("Warning: No actual match for " + ac.getVertex1() + " - " + ac.getVertex2() + ", but could be another solution since both edges have the same weight!");
            }
        }
        return expectedCopy.isEmpty() && actualCopy.isEmpty();
    }
}
