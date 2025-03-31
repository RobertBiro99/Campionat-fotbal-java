import java.util.*;
import java.util.concurrent.*;

public class Statistici {
        public Map<String, Integer> clasamentEchipe;
        public Map<String, Integer> statisticiJucatori;
        public Map<String, Integer> meciuriCastigate;

        private ExecutorService executor;

        public Statistici() {
                clasamentEchipe = new HashMap<>();
                statisticiJucatori = new HashMap<>();
                meciuriCastigate = new HashMap<>();
                executor = Executors.newFixedThreadPool(4);
        }

        public void actualizeazaClasament(Echipa echipa, int puncte) {
                executor.submit(() -> {
                        clasamentEchipe.put(echipa.nume, clasamentEchipe.getOrDefault(echipa.nume, 0) + puncte);
                });
        }

        public void actualizeazaStatistici(Jucator jucator) {
                executor.submit(() -> {
                        statisticiJucatori.put(jucator.nume, statisticiJucatori.getOrDefault(jucator.nume, 0) + jucator.numarGoluri);
                });
        }

        public void actualizeazaMeciuriCastigate(Echipa echipa, boolean castigat) {
                executor.submit(() -> {
                        if (castigat) {
                                meciuriCastigate.put(echipa.nume, meciuriCastigate.getOrDefault(echipa.nume, 0) + 1);
                        }
                });
        }

        public void afiseazaClasament() {
                System.out.println("Clasament echipe:");
                clasamentEchipe.forEach((echipa, puncte) -> System.out.println(echipa + ": " + puncte + " puncte"));
        }

        public void afiseazaStatisticiJucatori() {
                System.out.println("Statistici jucatori:");
                statisticiJucatori.forEach((jucator, goluri) -> System.out.println(jucator + ": " + goluri + " goluri"));
        }

        public void afiseazaMeciuriCastigate() {
                System.out.println("Meciuri castigate:");
                meciuriCastigate.forEach((echipa, meciuri) -> System.out.println(echipa + ": " + meciuri + " meciuri castigate"));
        }

        public void inchideExecutor() {
                executor.shutdown();
                try {
                        if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                                executor.shutdownNow();
                        }
                } catch (InterruptedException e) {
                        executor.shutdownNow();
                }
        }
}


