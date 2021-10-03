package com.file_attente;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

// cette classe est la classe mere , le point d'entrer de l'application
public class QueueMenager {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        System.out.println("");
        System.out.print("\ndonner le module : ");
        String choise = scanner.nextLine();

        String[] possibleChoices = {"moniteur","ticket", "guichet"};

        while (!Arrays.asList(possibleChoices).contains(choise)) {

            System.out.println("");
            System.out.println("    Liste des modules acceptés  :");
            System.out.println("    guichet : pour demarer un nouveau guichet");
            System.out.println("    ticket : pour demarer le boxe Ticket");
            System.out.println("    moniteur : pour demarer le moniteur");
            System.out.println("");
            System.out.print("donner le module : ");
            choise = scanner.nextLine();

        }

        switch (choise) {

            case "moniteur":
                choise = BoxFactory.MONITORING;
                break;
            case "ticket":
                choise = BoxFactory.TICKET;
                break;
            case "guichet":
                choise = BoxFactory.CHECKING;
                break;
            default:
                return;

        }

        BoxFactory.getBox(choise).start();  // ici on cree le Boxe selon le context (le choix) par le biau de la Fabrique de Box

        try {
            System.out.println("");
            System.out.println("La fenetre va se fermée dans 10s ");
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
           
        }


    }

}
