public class Jucator {
    public String nume;
    public int varsta;
    public String pozitie;
    public int numarGoluri;

    public Jucator(String nume, int varsta, String pozitie) {
        this.nume = nume;
        this.varsta = varsta;
        this.pozitie = pozitie;
        this.numarGoluri = 0;
    }

    public void adaugaGol() {
        this.numarGoluri++;
    }

    public String getNume() {
        return nume;
    }

    public int getNumarGoluri() {
        return numarGoluri;
    }

    @Override
    public String toString() {
        return nume + " (" + pozitie + "), " + numarGoluri + " goluri";
    }
}




