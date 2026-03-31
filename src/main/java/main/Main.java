package main;

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
import java.math.BigDecimal;
import java.time.LocalDate;

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
            skrivMeny();
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

                case "6":
                    finnAvdelingMedId(sc, avdelingDAO);
                    break;

                case "7":
                    skrivAlleAvdelinger(avdelingDAO);
                    break;

                case "8":
                    finnSjefIAvdeling(sc, avdelingDAO);
                    break;

                case "9":
                    skrivAnsatteIAvdeling(sc, avdelingDAO);
                    break;

                case "10":
                    byttAvdelingForAnsatt(sc, ansattDAO);
                    break;

                case "11":
                    finnProsjektMedId(sc, prosjektDAO);
                    break;

                case "12":
                    skrivAlleProsjekter(prosjektDAO);
                    break;

                case "13":
                    tellDeltakelser(sc, prosjektDAO);
                    break;

                case "14":
                    skrivAlleDeltakelser(deltakelseDAO);
                    break;

                case "15":
                    totalTimer(sc, deltakelseDAO);
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

    private static void skrivMeny() {
        System.out.println("==== MENY ====");
        System.out.println("1. Finn ansatt med id");
        System.out.println("2. Finn ansatt med brukernavn");
        System.out.println("3. Skriv ut alle ansatte");
        System.out.println("4. Oppdater stilling og/eller lønn for ansatt");
        System.out.println("5. Legg inn ny ansatt");
        System.out.println("6. Finn avdeling med id");
        System.out.println("7. Skriv ut alle avdelinger");
        System.out.println("8. Finn sjef i avdeling");
        System.out.println("9. Skriv ut ansatte i avdeling");
        System.out.println("10. Bytt avdeling for ansatt");
        System.out.println("11. Finn prosjekt med id");
        System.out.println("12. Skriv ut alle prosjekter");
        System.out.println("13. Tell deltakelser i prosjekt");
        System.out.println("14. Skriv ut alle deltakelser");
        System.out.println("15. Finn totalt timetall i prosjekt");
        System.out.println("0. Avslutt");
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

    private static void skrivAlleAnsatte(AnsattDAO ansattDAO) {
        List<Ansatt> ansatte = ansattDAO.finnAlle();

        if (ansatte.isEmpty()) {
            System.out.println("Ingen ansatte funnet.");
        } else {
            System.out.println("Alle ansatte:");
            ansatte.forEach(System.out::println);
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

    private static void totalTimer(Scanner sc, DeltakelseDAO deltakelseDAO) {
        System.out.print("Skriv inn prosjekt-id: ");
        int id = Integer.parseInt(sc.nextLine());

        Long total = deltakelseDAO.totalTimer(id);
        System.out.println("Totalt timetall i prosjekt " + id + ": " + total);
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
}