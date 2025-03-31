import java.io.*;
import java.util.*;

public class FileUtil {

    public static void salveazaDate(List<Echipa> echipe) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("echipe.txt"))) {
            for (Echipa echipa : echipe) {
                writer.write("Echipa: " + echipa.nume + "\n");
                writer.write("Antrenor: " + echipa.antrenor + "\n");
                for (Jucator jucator : echipa.jucatori) {
                    writer.write(jucator.nume + "," + jucator.varsta + "," + jucator.pozitie + "," + jucator.numarGoluri + "\n");
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Eroare la salvarea datelor: " + e.getMessage());
        }
    }

    public static List<Echipa> incarcaDate() {
        List<Echipa> echipe = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("echipe.txt"))) {
            String linie;
            Echipa echipaCurenta = null;
            while ((linie = reader.readLine()) != null) {
                if (linie.startsWith("Echipa:")) {
                    if (echipaCurenta != null) {
                        echipe.add(echipaCurenta);
                    }
                    String numeEchipa = linie.substring(8).trim();
                    String antrenor = reader.readLine().substring(10).trim();
                    echipaCurenta = new Echipa(numeEchipa, antrenor);
                } else if (linie.trim().length() > 0) {
                    String[] detaliiJucator = linie.split(",");
                    String numeJucator = detaliiJucator[0].trim();
                    int varsta = Integer.parseInt(detaliiJucator[1].trim());
                    String pozitie = detaliiJucator[2].trim();
                    int numarGoluri = Integer.parseInt(detaliiJucator[3].trim());
                    echipaCurenta.adaugaJucator(new Jucator(numeJucator, varsta, pozitie));
                    echipaCurenta.jucatori.get(echipaCurenta.jucatori.size() - 1).numarGoluri = numarGoluri;
                }
            }
            if (echipaCurenta != null) {
                echipe.add(echipaCurenta);
            }
        } catch (IOException e) {
            System.out.println("Eroare la incarcarea datelor: " + e.getMessage());
        }
        return echipe;
    }
}


