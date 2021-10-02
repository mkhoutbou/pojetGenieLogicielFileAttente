package com.file_attente;

import java.io.IOException;
import java.util.Scanner;
// c'est cette classe qui va gerer Guichet
// elle herite les cappacités de connexion de la classe Guests
// elle implemente l'Interface Box 
public class CheckingBox extends Guest implements Box {

    @Override
    public void start() { // c'est cette mettode qui demare le guichet

        try {

            startConnection(MonitoringBox.IP, MonitoringBox.PORT); // la connexion

            System.out.println("");
            System.out.println(".##############################################################.");
            System.out.println("|##############################################################|");
            System.out.println("|##############|                                |##############|");
            System.out.println("|##############|  Gestionnaire File d'attente   |##############|");
            System.out.println("|##############|            (Guiché)            |##############|");
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
            sendMessageWithoutStack("checker"); // envoie du message d'indentification
            System.out.print("      Donner le numero de Guichet : ");
            argument = scanner.nextLine();
            String response = sendMessage(argument);
            System.out.println("");
            System.out.println("         ====> " + response);

            while (!argument.equals("exit")) {

                System.out.println("");
                System.out.print("      Taper sur \"Entrer\" pour appeler le suivant >>> ");
                argument = scanner.nextLine();
                response = sendMessage(argument);
                System.out.println("");
                System.out.println("         ====> " + response);

            }

            scanner.close();
            stopConnection();

        } catch (IOException e) { // en cas d'erreur

            System.out.println("");
            System.out.println("Verifier que le moniteur est demarer et ensuite ressayer !!!");
            System.out.println("");

        }
    }

}
