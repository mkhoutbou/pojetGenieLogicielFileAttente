package com.file_attente;

import java.io.IOException;
import java.net.UnknownHostException;

// cette classe est la classe mere , le point d'entrer de l'application
public class QueueMenager {
    public static void main(String[] args) throws UnknownHostException, IOException {

        if (args.length == 0) { // verification si l'untilisateur a donner d'arguments ou pas 

            System.out.println("\n\nVous avez donné aucun argument\n");
            System.out.println("    Les argument accepté sont  :");
            System.out.println("    guichet : pour demarer un nouveau guichet");
            System.out.println("    ticket : pour demarer le boxe Ticket");
            System.out.println("    moniteur : pour demarer le moniteur");
        
            return ; // on quite en lui laissant un message

        }

        String choise = args[0]; // l'argument 0 est le choix de lanchement de l'application

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
                System.out.println("");
                System.out.println(choise + " n'est pas reconnu");
                System.out.println("    Les argument accepté sont  :");
                System.out.println("    guichet : pour demarer un nouveau guichet");
                System.out.println("    ticket : pour demarer le boxe Ticket");
                System.out.println("    moniteur : pour demarer le moniteur");
                return;

        }

        BoxFactory.getBox(choise).start();  // ici on cree le Boxe selon le context (le choix) par le biau de la Fabrique de Box

    }

}
