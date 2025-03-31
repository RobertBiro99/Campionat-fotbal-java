import java.util.Date;

public class Meci {
    public Echipa echipa1;
    public Echipa echipa2;
    public int scor1;
    public int scor2;
    public Date data;

    public Meci(Echipa echipa1, Echipa echipa2, int scor1, int scor2, Date data) {
        this.echipa1 = echipa1;
        this.echipa2 = echipa2;
        this.scor1 = scor1;
        this.scor2 = scor2;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Meci: " + echipa1.nume + " (" + scor1 + ") vs " + echipa2.nume + " (" + scor2 + ") la data " + data;
    }
}



