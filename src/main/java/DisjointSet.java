

/**
 * Disjoint set class, using union by rank and path compression.
 * Elements in the set are numbered starting at 0.
 */
public class DisjointSet {
    private int[] s;        //the set field

    /**
     *
     * @return
     */
    public int[] getSet() {        //mostly debugging method to print array
        return s;
    }

    /**
     * Construct the disjoint sets object.
     *
     * @param numElements the initial number of disjoint sets.
     */
    public DisjointSet(int numElements) {        //constructor creates singleton sets
        s = new int[numElements];
        for (int i = 0; i < s.length; i++)        //initialize to -1 so the trees have nothing in them
            s[i] = -1;
    }

    /**
     * Union two disjoint sets using the height heuristic.
     * For simplicity, we assume root1 and root2 are distinct and represent set names.
     *
     * @param root1 the root of set 1.
     * @param root2 the root of set 2.
     */
    public void union(int root1, int root2) {
        if (s[root2] < s[root1])  // root2 is deeper
            s[root1] = root2;        // Make root2 new root
        else {
            if (s[root1] == s[root2])
                s[root1]--;          // Update height if same
            s[root2] = root1;        // Make root1 new root
        }
    }

    /**
     * Perform a find with path compression.
     * Error checks omitted again for simplicity.
     *
     * @param x the element being searched for.
     * @return the set containing x.
     */
    public int find(int x) {
        if (s[x] < 0)    //if tree has no elements, then it is its own root
            return x;
        else
            return s[x] = find(s[x]);
    }
}
