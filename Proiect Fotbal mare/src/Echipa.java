import java.util.*;

public class Echipa {
    public String nume;
    public String antrenor;
    public List<Jucator> jucatori;

    public Echipa(String nume, String antrenor) {
        this.nume = nume;
        this.antrenor = antrenor;
        this.jucatori = new ArrayList<>();
    }

    public void adaugaJucator(Jucator jucator) {
        jucatori.add(jucator);
    }

    public void afiseazaJucatori() {
        if (jucatori.isEmpty()) {
            System.out.println("Nu exista jucatori in aceasta echipa.");
        } else {
            for (Jucator jucator : jucatori) {
                System.out.println(jucator);
            }
        }
    }
}


