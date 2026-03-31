package main;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import dao.AnsattDAO;
import dao.AvdelingDAO;
import dao.DeltakelseDAO;
import dao.ProsjektDAO;
import entity.Ansatt;
import entity.Avdeling;
import entity.Deltakelse;
import entity.Prosjekt;
import jakarta.persistence.EntityManager;
import util.JPAUtil;

public class Main {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();

        AnsattDAO ansattDAO = new AnsattDAO();
        AvdelingDAO avdelingDAO = new AvdelingDAO();
        ProsjektDAO prosjektDAO = new ProsjektDAO();
        DeltakelseDAO deltakelseDAO = new DeltakelseDAO();

        Scanner sc = new Scanner(System.in);
        boolean ferdig = false;

        while (!ferdig) {
            skrivHovedmeny();
            System.out.print("Velg et alternativ: ");
            String valg = sc.nextLine();

            switch (valg) {
                case "1":
                    kjørAnsattMeny(sc, ansattDAO);
                    break;

                case "2":
                    kjørAvdelingMeny(sc, ansattDAO, avdelingDAO);
                    break;

                case "3":
                    kjørProsjektMeny(sc, prosjektDAO, deltakelseDAO);
                    break;

                case "0":
                    ferdig = true;
                    System.out.println("Avslutter programmet.");
                    break;

                default:
                    System.out.println("Ugyldig valg. Prøv igjen.");
            }

            System.out.println();
        }

        sc.close();
        em.close();
        JPAUtil.close();
    }

    private static void skrivHovedmeny() {
        System.out.println("KONTROLLPANEL");
        System.out.println("1. Ansatt-meny");
        System.out.println("2. Avdeling-meny");
        System.out.println("3. Prosjekt-meny");
        System.out.println("0. Avslutt");
    }

    private static void kjørAnsattMeny(Scanner sc, AnsattDAO ansattDAO) {
        boolean tilbake = false;

        while (!tilbake) {
            skrivAnsattMeny();
            System.out.print("Velg et alternativ: ");
            String valg = sc.nextLine();

            switch (valg) {
                case "1":
                    finnAnsattMedId(sc, ansattDAO);
                    break;

                case "2":
                    finnAnsattMedBrukernavn(sc, ansattDAO);
                    break;

                case "3":
                    skrivAlleAnsatte(ansattDAO);
                    break;

                case "4":
                    oppdaterAnsatt(sc, ansattDAO);
                    break;

                case "5":
                    leggTilAnsatt(sc, ansattDAO);
                    break;

                case "0":
                    tilbake = true;
                    break;

                default:
                    System.out.println("Ugyldig valg. Prøv igjen.");
            }

            System.out.println();
        }
    }

    private static void skrivAnsattMeny() {
        System.out.println("ANSATT-MENY");
        System.out.println("1. Finn ansatt med id");
        System.out.println("2. Finn ansatt med brukernavn");
        System.out.println("3. Skriv ut alle ansatte");
        System.out.println("4. Oppdater stilling og/eller lønn");
        System.out.println("5. Legg inn ny ansatt");
        System.out.println("0. Tilbake");
    }

    private static void kjørAvdelingMeny(Scanner sc, AnsattDAO ansattDAO, AvdelingDAO avdelingDAO) {
        boolean tilbake = false;

        while (!tilbake) {
            skrivAvdelingMeny();
            System.out.print("Velg et alternativ: ");
            String valg = sc.nextLine();

            switch (valg) {
                case "1":
                    finnAvdelingMedId(sc, avdelingDAO);
                    break;

                case "2":
                    skrivAlleAvdelinger(avdelingDAO);
                    break;

                case "3":
                    finnSjefIAvdeling(sc, avdelingDAO);
                    break;

                case "4":
                    skrivAnsatteIAvdeling(sc, avdelingDAO);
                    break;

                case "5":
                    byttAvdelingForAnsatt(sc, ansattDAO);
                    break;

                case "6":
                    leggTilAvdeling(sc, avdelingDAO);
                    break;

                case "0":
                    tilbake = true;
                    break;

                default:
                    System.out.println("Ugyldig valg. Prøv igjen.");
            }

            System.out.println();
        }
    }

    private static void skrivAvdelingMeny() {
        System.out.println("AVDELING-MENY");
        System.out.println("1. Finn avdeling med id");
        System.out.println("2. Skriv ut alle avdelinger");
        System.out.println("3. Finn sjef i avdeling");
        System.out.println("4. Skriv ut ansatte i avdeling");
        System.out.println("5. Bytt avdeling for ansatt");
        System.out.println("6. Legg inn ny avdeling");
        System.out.println("0. Tilbake");
    }

    private static void kjørProsjektMeny(Scanner sc, ProsjektDAO prosjektDAO, DeltakelseDAO deltakelseDAO) {
        boolean tilbake = false;

        while (!tilbake) {
            skrivProsjektMeny();
            System.out.print("Velg et alternativ: ");
            String valg = sc.nextLine();

            switch (valg) {
                case "1":
                    finnProsjektMedId(sc, prosjektDAO);
                    break;

                case "2":
                    skrivAlleProsjekter(prosjektDAO);
                    break;

                case "3":
                    leggTilProsjekt(sc, prosjektDAO);
                    break;

                case "4":
                    oppdaterProsjekt(sc, prosjektDAO);
                    break;

                case "5":
                    registrerProsjektdeltakelse(sc, deltakelseDAO);
                    break;

                case "6":
                    forTimerPaProsjekt(sc, deltakelseDAO);
                    break;

                case "7":
                    skrivProsjektInfo(sc, prosjektDAO, deltakelseDAO);
                    break;

                case "8":
                    tellDeltakelser(sc, prosjektDAO);
                    break;

                case "9":
                    skrivAlleDeltakelser(deltakelseDAO);
                    break;

                case "10":
                    totalTimer(sc, deltakelseDAO);
                    break;

                case "0":
                    tilbake = true;
                    break;

                default:
                    System.out.println("Ugyldig valg. Prøv igjen.");
            }

            System.out.println();
        }
    }

    private static void skrivProsjektMeny() {
        System.out.println("PROSJEKT-MENY");
        System.out.println("1. Finn prosjekt med id");
        System.out.println("2. Skriv ut alle prosjekter");
        System.out.println("3. Legg inn nytt prosjekt");
        System.out.println("4. Endre prosjekt");
        System.out.println("5. Registrer prosjektdeltagelse");
        System.out.println("6. Før timer på prosjekt");
        System.out.println("7. Skriv ut prosjektinfo");
        System.out.println("8. Tell deltakelser i prosjekt");
        System.out.println("9. Skriv ut alle deltakelser");
        System.out.println("10. Finn totalt timetall i prosjekt");
        System.out.println("0. Tilbake");
    }

    private static void finnAnsattMedId(Scanner sc, AnsattDAO ansattDAO) {
        System.out.print("Skriv inn ansatt-id: ");
        int id = Integer.parseInt(sc.nextLine());

        Ansatt ansatt = ansattDAO.finnAnsattMedId(id);

        if (ansatt != null) {
            System.out.println(ansatt);
        } else {
            System.out.println("Fant ingen ansatt med id " + id);
        }
    }

    private static void finnAnsattMedBrukernavn(Scanner sc, AnsattDAO ansattDAO) {
        System.out.print("Skriv inn brukernavn: ");
        String brukernavn = sc.nextLine();

        Ansatt ansatt = ansattDAO.finnAnsattMedBrukernavn(brukernavn);

        if (ansatt != null) {
            System.out.println(ansatt);
        } else {
            System.out.println("Fant ingen ansatt med brukernavn " + brukernavn);
        }
    }

    private static void skrivAlleAnsatte(AnsattDAO ansattDAO) {
        List<Ansatt> ansatte = ansattDAO.finnAlle();

        if (ansatte.isEmpty()) {
            System.out.println("Ingen ansatte funnet.");
        } else {
            System.out.println("Alle ansatte:");
            ansatte.forEach(System.out::println);
        }
    }

    private static void oppdaterAnsatt(Scanner sc, AnsattDAO ansattDAO) {
        System.out.print("Skriv inn ansatt-id: ");
        int id = Integer.parseInt(sc.nextLine());

        Ansatt gammel = ansattDAO.finnAnsattMedId(id);

        if (gammel == null) {
            System.out.println("Fant ingen ansatt med id " + id);
            return;
        }

        System.out.println("Nåværende info:");
        System.out.println(gammel);

        System.out.print("Ny stilling (trykk Enter for å beholde gammel): ");
        String nyStilling = sc.nextLine();

        System.out.print("Ny månedslønn (trykk Enter for å beholde gammel): ");
        String lonnInput = sc.nextLine();

        BigDecimal nyLonn = null;
        if (!lonnInput.isBlank()) {
            nyLonn = new BigDecimal(lonnInput);
        }

        boolean ok = ansattDAO.oppdaterStillingOgLonn(id, nyStilling, nyLonn);

        if (ok) {
            System.out.println("Ansatt ble oppdatert.");
            System.out.println(ansattDAO.finnAnsattMedId(id));
        } else {
            System.out.println("Kunne ikke oppdatere ansatt.");
        }
    }

    private static void leggTilAnsatt(Scanner sc, AnsattDAO ansattDAO) {
        System.out.print("Brukernavn: ");
        String brukernavn = sc.nextLine();

        System.out.print("Fornavn: ");
        String fornavn = sc.nextLine();

        System.out.print("Etternavn: ");
        String etternavn = sc.nextLine();

        System.out.print("Ansettelsesdato (yyyy-mm-dd): ");
        LocalDate ansettelsesdato = LocalDate.parse(sc.nextLine());

        System.out.print("Stilling: ");
        String stilling = sc.nextLine();

        System.out.print("Månedslønn: ");
        BigDecimal maanedslonn = new BigDecimal(sc.nextLine());

        System.out.print("Avdeling-id: ");
        int avdelingId = Integer.parseInt(sc.nextLine());

        boolean ok = ansattDAO.leggTilAnsatt(
                brukernavn, fornavn, etternavn,
                ansettelsesdato, stilling, maanedslonn, avdelingId
        );

        if (ok) {
            System.out.println("Ny ansatt ble lagt til.");
        } else {
            System.out.println("Kunne ikke legge til ansatt. Sjekk brukernavn og avdeling-id.");
        }
    }

    private static void finnAvdelingMedId(Scanner sc, AvdelingDAO avdelingDAO) {
        System.out.print("Skriv inn avdeling-id: ");
        int id = Integer.parseInt(sc.nextLine());

        Avdeling avdeling = avdelingDAO.finnAvdelingMedId(id);

        if (avdeling != null) {
            System.out.println(avdeling);
        } else {
            System.out.println("Fant ingen avdeling med id " + id);
        }
    }

    private static void skrivAlleAvdelinger(AvdelingDAO avdelingDAO) {
        List<Avdeling> avdelinger = avdelingDAO.finnAlle();

        if (avdelinger.isEmpty()) {
            System.out.println("Ingen avdelinger funnet.");
        } else {
            System.out.println("Alle avdelinger:");
            avdelinger.forEach(System.out::println);
        }
    }

    private static void finnSjefIAvdeling(Scanner sc, AvdelingDAO avdelingDAO) {
        System.out.print("Skriv inn avdeling-id: ");
        int id = Integer.parseInt(sc.nextLine());

        String sjef = avdelingDAO.finnSjef(id);

        if (sjef != null) {
            System.out.println("Sjef i avdeling " + id + ": " + sjef);
        } else {
            System.out.println("Fant ingen sjef eller avdeling med id " + id);
        }
    }

    private static void skrivAnsatteIAvdeling(Scanner sc, AvdelingDAO avdelingDAO) {
        System.out.print("Skriv inn avdeling-id: ");
        int id = Integer.parseInt(sc.nextLine());

        Avdeling avdeling = avdelingDAO.finnAvdelingMedId(id);

        if (avdeling == null) {
            System.out.println("Fant ingen avdeling med id " + id);
            return;
        }

        List<Ansatt> ansatte = avdelingDAO.finnAnsatteIAvdeling(id);

        if (ansatte.isEmpty()) {
            System.out.println("Ingen ansatte funnet i avdeling " + avdeling.getNavn());
            return;
        }

        System.out.println("Ansatte i avdeling " + avdeling.getNavn() + ":");

        for (Ansatt ansatt : ansatte) {
            if (avdeling.getSjef() != null &&
                    ansatt.getAnsattId() == avdeling.getSjef().getAnsattId()) {
                System.out.println(ansatt + " (SJEF)");
            } else {
                System.out.println(ansatt);
            }
        }
    }

    private static void byttAvdelingForAnsatt(Scanner sc, AnsattDAO ansattDAO) {
        System.out.print("Skriv inn ansatt-id: ");
        int ansattId = Integer.parseInt(sc.nextLine());

        Ansatt ansatt = ansattDAO.finnAnsattMedId(ansattId);
        if (ansatt == null) {
            System.out.println("Fant ingen ansatt med id " + ansattId);
            return;
        }

        System.out.println("Valgt ansatt:");
        System.out.println(ansatt);

        System.out.print("Skriv inn ny avdeling-id: ");
        int nyAvdelingId = Integer.parseInt(sc.nextLine());

        boolean ok = ansattDAO.byttAvdeling(ansattId, nyAvdelingId);

        if (ok) {
            System.out.println("Ansatt ble flyttet til ny avdeling.");
            System.out.println(ansattDAO.finnAnsattMedId(ansattId));
        } else {
            System.out.println("Kunne ikke bytte avdeling.");
            System.out.println("Årsak kan være at ansatt er sjef, eller at avdeling ikke finnes.");
        }
    }

    private static void leggTilAvdeling(Scanner sc, AvdelingDAO avdelingDAO) {
        System.out.print("Navn på ny avdeling: ");
        String navn = sc.nextLine();

        System.out.print("Ansatt-id for sjef: ");
        int sjefAnsattId = Integer.parseInt(sc.nextLine());

        boolean ok = avdelingDAO.leggTilAvdeling(navn, sjefAnsattId);

        if (ok) {
            System.out.println("Ny avdeling ble lagt til.");
        } else {
            System.out.println("Kunne ikke legge til avdeling.");
            System.out.println("Årsak kan være ugyldig ansatt-id eller at ansatt allerede er sjef.");
        }
    }

    private static void finnProsjektMedId(Scanner sc, ProsjektDAO prosjektDAO) {
        System.out.print("Skriv inn prosjekt-id: ");
        int id = Integer.parseInt(sc.nextLine());

        Prosjekt prosjekt = prosjektDAO.finnProsjektMedId(id);

        if (prosjekt != null) {
            System.out.println(prosjekt);
        } else {
            System.out.println("Fant ikke prosjekt med id " + id);
        }
    }

    private static void skrivAlleProsjekter(ProsjektDAO prosjektDAO) {
        List<Prosjekt> prosjekter = prosjektDAO.finnAlle();

        if (prosjekter.isEmpty()) {
            System.out.println("Ingen prosjekter funnet.");
        } else {
            System.out.println("Alle prosjekter:");
            prosjekter.forEach(System.out::println);
        }
    }

    private static void leggTilProsjekt(Scanner sc, ProsjektDAO prosjektDAO) {
        System.out.print("Navn på prosjekt: ");
        String navn = sc.nextLine();

        System.out.print("Beskrivelse: ");
        String beskrivelse = sc.nextLine();

        boolean ok = prosjektDAO.leggTilProsjekt(navn, beskrivelse);

        if (ok) {
            System.out.println("Nytt prosjekt ble lagt til.");
        } else {
            System.out.println("Kunne ikke legge til prosjekt.");
        }
    }

    private static void registrerProsjektdeltakelse(Scanner sc, DeltakelseDAO deltakelseDAO) {
        System.out.print("Ansatt-id: ");
        int ansattId = Integer.parseInt(sc.nextLine());

        System.out.print("Prosjekt-id: ");
        int prosjektId = Integer.parseInt(sc.nextLine());

        System.out.print("Rolle: ");
        String rolle = sc.nextLine();

        System.out.print("Timer: ");
        int timer = Integer.parseInt(sc.nextLine());

        boolean ok = deltakelseDAO.registrerDeltakelse(ansattId, prosjektId, rolle, timer);

        if (ok) {
            System.out.println("Prosjektdeltagelse ble registrert.");
        } else {
            System.out.println("Kunne ikke registrere prosjektdeltagelse.");
            System.out.println("Årsak kan være ugyldig ansatt/prosjekt, eller at deltakelsen allerede finnes.");
        }
    }

    private static void forTimerPaProsjekt(Scanner sc, DeltakelseDAO deltakelseDAO) {
        System.out.print("Ansatt-id: ");
        int ansattId = Integer.parseInt(sc.nextLine());

        System.out.print("Prosjekt-id: ");
        int prosjektId = Integer.parseInt(sc.nextLine());

        System.out.print("Antall nye timer: ");
        int flereTimer = Integer.parseInt(sc.nextLine());

        boolean ok = deltakelseDAO.forTimer(ansattId, prosjektId, flereTimer);

        if (ok) {
            System.out.println("Timer ble ført.");
        } else {
            System.out.println("Kunne ikke føre timer.");
            System.out.println("Årsak kan være at prosjektdeltagelsen ikke finnes.");
        }
    }

    private static void skrivProsjektInfo(Scanner sc, ProsjektDAO prosjektDAO, DeltakelseDAO deltakelseDAO) {
        System.out.print("Prosjekt-id: ");
        int prosjektId = Integer.parseInt(sc.nextLine());

        Prosjekt prosjekt = prosjektDAO.finnProsjektMedId(prosjektId);

        if (prosjekt == null) {
            System.out.println("Fant ikke prosjekt med id " + prosjektId);
            return;
        }

        List<Deltakelse> deltakelser = deltakelseDAO.finnDeltakelserForProsjekt(prosjektId);
        Long total = deltakelseDAO.totalTimer(prosjektId);

        System.out.println("----- PROSJEKTINFO -----");
        System.out.println("Prosjekt-id: " + prosjekt.getProsjekt_id());
        System.out.println("Navn: " + prosjekt.getNavn());
        System.out.println("Beskrivelse: " + prosjekt.getBeskrivelse());
        System.out.println();

        if (deltakelser.isEmpty()) {
            System.out.println("Ingen deltagere registrert.");
        } else {
            System.out.println("Deltagere:");
            for (Deltakelse d : deltakelser) {
                System.out.println(
                        d.getAnsatt().getFornavn() + " " +
                                d.getAnsatt().getEtternavn() +
                                " | Rolle: " + d.getRolle() +
                                " | Timer: " + d.getTimer()
                );
            }
        }

        System.out.println();
        System.out.println("Totalt timetall: " + (total != null ? total : 0));
    }

    private static void tellDeltakelser(Scanner sc, ProsjektDAO prosjektDAO) {
        System.out.print("Skriv inn prosjekt-id: ");
        int id = Integer.parseInt(sc.nextLine());

        Long antall = prosjektDAO.tellDeltakelser(id);
        System.out.println("Antall deltakelser i prosjekt " + id + ": " + antall);
    }

    private static void skrivAlleDeltakelser(DeltakelseDAO deltakelseDAO) {
        List<Deltakelse> deltakelser = deltakelseDAO.finnAlle();

        if (deltakelser.isEmpty()) {
            System.out.println("Ingen deltakelser funnet.");
        } else {
            System.out.println("Alle deltakelser:");
            deltakelser.forEach(System.out::println);
        }
    }

    private static void totalTimer(Scanner sc, DeltakelseDAO deltakelseDAO) {
        System.out.print("Skriv inn prosjekt-id: ");
        int id = Integer.parseInt(sc.nextLine());

        Long total = deltakelseDAO.totalTimer(id);
        System.out.println("Totalt timetall i prosjekt " + id + ": " + (total != null ? total : 0));
    }

    private static void oppdaterProsjekt(Scanner sc, ProsjektDAO prosjektDAO) {
        System.out.print("Prosjekt-id: ");
        int prosjektId = Integer.parseInt(sc.nextLine());

        Prosjekt prosjekt = prosjektDAO.finnProsjektMedId(prosjektId);

        if (prosjekt == null) {
            System.out.println("Fant ikke prosjekt med id " + prosjektId);
            return;
        }

        System.out.println("Nåværende prosjekt:");
        System.out.println(prosjekt);

        System.out.print("Nytt navn (trykk Enter for å beholde gammelt): ");
        String nyttNavn = sc.nextLine();

        System.out.print("Ny beskrivelse (trykk Enter for å beholde gammel): ");
        String nyBeskrivelse = sc.nextLine();

        boolean ok = prosjektDAO.oppdaterProsjekt(prosjektId, nyttNavn, nyBeskrivelse);

        if (ok) {
            System.out.println("Prosjekt ble oppdatert.");
            System.out.println(prosjektDAO.finnProsjektMedId(prosjektId));
        } else {
            System.out.println("Kunne ikke oppdatere prosjekt.");
        }
    }
}