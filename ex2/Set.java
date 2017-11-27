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
                return false;
            } else {
                if (a.get(i) == x) {
                    return true;
                }
            }
        }
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
        if (!(0 < a.size())) {
            System.out.println("Branch 4");
        }


        for (int i = 0; i < a.size(); i++) {

            if (!(0 < i)) {
                System.out.println("Branch 3");
            }

            for (int j = 0; j < i; j++) {   // <= made the if statement only look at the first element which makes it true
                                            //everytime
                if (member(2 * a.get(i) - a.get(j))) {
                    System.out.println("Branch 1");
                    return true;
                }
                System.out.println("Branch 2");
            }
        }
        System.out.println("Branch 3");

        return false;
    }
}
