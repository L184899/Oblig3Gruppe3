package main;

import entity.Ansatt;
import dao.AnsattDAO;

import entity.Avdeling;
import dao.AvdelingDAO;

import entity.Prosjekt;
import dao.ProsjektDAO;

import entity.Deltakelse;
import dao.DeltakelseDAO;

import jakarta.persistence.EntityManager;
import util.JPAUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();

        List<?> result =
                em.createNativeQuery("SELECT * FROM ansatt").getResultList();

        System.out.println(result.size());

        AnsattDAO dao = new AnsattDAO();
        AvdelingDAO avdelingDAO = new AvdelingDAO();
        ProsjektDAO prosjektDAO = new ProsjektDAO();
        DeltakelseDAO deltakelseDAO = new DeltakelseDAO();

        // ansatt test
        Ansatt a = dao.finnAnsattMedId(1);
        System.out.println("En ansatt:");
        System.out.println(a);

        System.out.println("\nAlle ansatte:");
        List<Ansatt> liste = dao.finnAlle();
        for (Ansatt ans : liste) {
            System.out.println(ans);
        }

        // avdeling test
        System.out.println("\nAlle avdelinger:");
        List<Avdeling> avdelinger = avdelingDAO.finnAlle();
        for (Avdeling avd : avdelinger) {
            System.out.println(avd);
        }

        System.out.println("\nSjef i avdeling 1:");
        System.out.println(avdelingDAO.finnSjef(1));

        // prosjekt test
        System.out.println("\nAlle prosjekter:");
        List<Prosjekt> prosjekter = prosjektDAO.finnAlle();
        for (Prosjekt p : prosjekter) {
            System.out.println(p);
        }

        System.out.println("\nAntall deltakere i prosjekt 1:");
        System.out.println(prosjektDAO.tellDeltakelser(1));

        // deltakelser test
        System.out.println("\nAlle deltakelser:");
        List<Deltakelse> deltakelser = deltakelseDAO.finnAlle();
        for (Deltakelse d : deltakelser) {
            System.out.println(d);
        }

        System.out.println("\nTotale timer i prosjekt 1:");
        System.out.println(deltakelseDAO.totalTimer(1));








    }


}