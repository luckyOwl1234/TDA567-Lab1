import java.util.*;

public class Set {
    private ArrayList<Integer> a;

    public Set() {
        a = new ArrayList<Integer>();
    }

    public int[] toArray() {
        int[] ia = new int[a.size()];
        for (int i = 0; i < ia.length; i++) {
            ia[i] = a.get(i);
        }
        return ia;
    }

    public void insert(int x) {
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) > x) {
                a.add(i, x);
                break;
            } else {
                if (a.get(i) == x) {
                    break;
                }
            }
            if(i == a.size() - 1 && a.get(i) != x){ //Egen if
                a.add(x);
            }
        }
        if(a.size() == 0) { //Egen if
            a.add(x);
        }
    }

    public boolean member(int x) {
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) > x) {
                System.out.println("branch 1");
                return false;
            } else {
                if (a.get(i) == x) {
                    System.out.println("branch 2");
                    return true;
                }
                System.out.println("branch 3");
            }
        }
        System.out.println("branch 4");
        return false;
    }

    public void section(Set s) {
        for (int i = 0, j = 0; i < a.size() && j < s.a.size();) {
            if (a.get(i).equals(s.a.get(j))) {
                a.remove(i);
                //i++; //Bug uppstog då man tog bort ett värde i a och samtidigt ökade på i då
                j++;
            } else {
                if (a.get(i) < s.a.get(j)) {
                    i++;
                } else {
                    j++;
                }
            }
        }
    }

    public boolean containsArithTriple() {
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < i; j++) {   // <= made the if statement only look at the first element which makes it true
                                            //everytime
                if (member(2 * a.get(i) - a.get(j))) return true;
            }
        }
        return false;
    }
}
