package main;

import entity.Ansatt;
import dao.AnsattDAO;
import jakarta.persistence.EntityManager;
import util.JPAUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        AnsattDAO dao = new AnsattDAO();

        // TEST 1
        Ansatt a = dao.finnAnsattMedId(1);
        System.out.println("En ansatt:");
        System.out.println(a);

        // TEST 2
        System.out.println("\nAlle ansatte:");
        List<Ansatt> liste = dao.finnAlle();
        for (Ansatt ans : liste) {
            System.out.println(ans);
        }

        // TEST 3
        dao.oppdaterLonn(1, 70000);

        System.out.println("\nOppdatert lønn!");
    }

}