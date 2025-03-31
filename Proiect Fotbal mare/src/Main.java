import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Echipa> echipe = FileUtil.incarcaDate();
        Statistici statistici = new Statistici();

        while (true) {
            System.out.println("1. Adauga echipa");
            System.out.println("2. Adauga jucator");
            System.out.println("3. Inregistreaza meci");
            System.out.println("4. Vezi statistici");
            System.out.println("5. Iesire");
            System.out.println("6. Vezi jucatori din echipe");

            int optiune = scanner.nextInt();
            scanner.nextLine();

            switch (optiune) {
                case 1:

                    System.out.println("Introduceti numele echipei:");
                    String numeEchipa = scanner.nextLine();
                    System.out.println("Introduceti numele antrenorului:");
                    String antrenorEchipa = scanner.nextLine();
                    Echipa echipaNoua = new Echipa(numeEchipa, antrenorEchipa);
                    echipe.add(echipaNoua);
                    System.out.println("Echipa " + numeEchipa + " a fost adaugata!");
                    break;

                case 2:

                    if (echipe.isEmpty()) {
                        System.out.println("Nu exista echipe. Adaugati mai intai o echipa!");
                        break;
                    }

                    System.out.println("Selectati echipa pentru a adauga jucator:");
                    for (int i = 0; i < echipe.size(); i++) {
                        System.out.println((i + 1) + ". " + echipe.get(i).nume);
                    }
                    int indexEchipa = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (indexEchipa >= 0 && indexEchipa < echipe.size()) {
                        System.out.println("Introduceti numele jucatorului:");
                        String numeJucator = scanner.nextLine();
                        System.out.println("Introduceti varsta jucatorului:");
                        int varsta = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Introduceti pozitia jucatorului:");
                        String pozitie = scanner.nextLine();

                        Jucator jucator = new Jucator(numeJucator, varsta, pozitie);
                        echipe.get(indexEchipa).adaugaJucator(jucator);
                        System.out.println("Jucatorul " + numeJucator + " a fost adaugat echipei " + echipe.get(indexEchipa).nume);
                    } else {
                        System.out.println("Echipa invalida!");
                    }
                    break;



                case 3:

                    if (echipe.size() < 2) {
                        System.out.println("Trebuie sa aveti cel putin 2 echipe pentru a inregistra un meci.");
                        break;
                    }

                    System.out.println("Selectati prima echipa:");
                    for (int i = 0; i < echipe.size(); i++) {
                        System.out.println((i + 1) + ". " + echipe.get(i).nume);
                    }
                    int indexEchipa1 = scanner.nextInt() - 1;
                    System.out.println("Selectati a doua echipa:");
                    for (int i = 0; i < echipe.size(); i++) {
                        System.out.println((i + 1) + ". " + echipe.get(i).nume);
                    }
                    int indexEchipa2 = scanner.nextInt() - 1;
                    scanner.nextLine();

                    System.out.println("Introduceti scorul echipei 1:");
                    int scor1 = scanner.nextInt();
                    System.out.println("Introduceti scorul echipei 2:");
                    int scor2 = scanner.nextInt();
                    scanner.nextLine();


                    Meci meci = new Meci(echipe.get(indexEchipa1), echipe.get(indexEchipa2), scor1, scor2, new java.util.Date());


                    System.out.println("Introduceti golurile pentru echipa " + echipe.get(indexEchipa1).nume);
                    for (Jucator jucator : echipe.get(indexEchipa1).jucatori) {
                        System.out.println("Cate goluri a marcat " + jucator.nume + "?");
                        int goluri = scanner.nextInt();
                        scanner.nextLine();
                        for (int i = 0; i < goluri; i++) {
                            jucator.adaugaGol();
                        }
                    }

                    System.out.println("Introduceti golurile pentru echipa " + echipe.get(indexEchipa2).nume);
                    for (Jucator jucator : echipe.get(indexEchipa2).jucatori) {
                        System.out.println("Cate goluri a marcat " + jucator.nume + "?");
                        int goluri = scanner.nextInt();
                        scanner.nextLine();
                        for (int i = 0; i < goluri; i++) {
                            jucator.adaugaGol();
                        }
                    }


                    statistici.actualizeazaClasament(echipe.get(indexEchipa1), scor1 > scor2 ? 3 : scor1 == scor2 ? 1 : 0);
                    statistici.actualizeazaClasament(echipe.get(indexEchipa2), scor2 > scor1 ? 3 : scor1 == scor2 ? 1 : 0);


                    System.out.println("Meciul a fost inregistrat: " + meci);
                    break;


                case 4:

                    System.out.println("Clasament echipe:");
                    statistici.afiseazaClasament();
                    System.out.println("Statistici jucatori:");
                    statistici.afiseazaStatisticiJucatori();
                    statistici.afiseazaMeciuriCastigate();
                    break;

                case 5:

                    FileUtil.salveazaDate(echipe);
                    statistici.inchideExecutor();
                    System.out.println("Datele au fost salvate. La revedere!");
                    return;

                case 6:

                    if (echipe.isEmpty()) {
                        System.out.println("Nu exista echipe inregistrate.");
                    } else {
                        for (Echipa echipa : echipe) {
                            System.out.println("Echipa: " + echipa.nume);
                            echipa.afiseazaJucatori();
                        }
                    }
                    break;

                default:
                    System.out.println("Optiune invalida!");
            }
        }
    }
}


