package com.file_attente;
import java.io.IOException;
import java.util.Scanner;

// la classe qui gere la generation des tickets
public class TicketBox extends Guest implements Box {

    @Override
    public void start() {

        try {

            startConnection(MonitoringBox.IP, MonitoringBox.PORT); // connection avec le serveur (moniteur)
            
            System.out.println("");
            System.out.println(".##############################################################.");
            System.out.println("|##############################################################|");
            System.out.println("|##############|                                |##############|");
            System.out.println("|##############|  Gestionnaire File d'attente   |##############|");
            System.out.println("|##############|            (Ticket)            |##############|");
            System.out.println("|##############################################################|");
            System.out.println("*##############################################################*");
            System.out.println("");
            System.out.println("");
            System.out.println("      Developpé par Mouhamed Khoutbou Thiam(77 430 90 04)       ");
            System.out.println("----------------------------------------------------------------");
            System.out.println("");
            System.out.println("                 L'application est bien lancée                  ");
            System.out.println("               Connexion etablie avec le Moniteur               ");
            System.out.println("");
            System.out.println("");

            String argument = "continue";
            Scanner scanner = new Scanner(System.in);
            String response = sendMessage("ticket"); // ici on dit au moniteur qu'on se connecte en tant que ticket generateur
            System.out.println("");
            System.out.println("         ====> " + response);

            while (!argument.equals("exit")) { // on sort de cette quand l'utilisateur donne exit comme entree
                
                System.out.println("");
                System.out.print("      Taper sur \"Entrer\" pour Nouveau Ticket >>> ");
                argument = scanner.nextLine();
                response = sendMessage(argument);
                System.out.println("");
                System.out.println("         ====> " + response); // la reponse di moniteur ici c'est le numero du ticket

            }

            scanner.close();
            stopConnection(); // on stop la connection quand on sort de la boucle

        }  catch (IOException e) { // en cas d'erreur

            System.out.println("");
            System.out.println("Verifier que le moniteur est demarer et ensuite ressayer !!!");
            System.out.println("");

        }
        
    }

    @Override
    public void stop() {
        
    }
    
}
