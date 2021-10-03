package com.file_attente;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

// C'est le moniteur , le serveur centrall . il affiche la file et les appel des caisse
public class MonitoringBox implements Box {

    public static final String IP = "127.0.0.1";
    public static final int PORT = 6666;

    private ServerSocket serverSocket;
    private static int queuTop = 0; // la tete de la queue
    private static int queuHead = 0; // la position courrente des appels

    @Override
    public void start() { // tout boxe doit avoir cette methode

        try {

            serverSocket = new ServerSocket(PORT); // ce constructeur de ServerSocket permet l'application d'ecouter sous un port ..

            System.out.println("");
            System.out.println(".##############################################################.");
            System.out.println("|##############################################################|");
            System.out.println("|##############|                                |##############|");
            System.out.println("|##############|  Gestionnaire File d'attente   |##############|");
            System.out.println("|##############|           (Moniteur)           |##############|");
            System.out.println("|##############################################################|");
            System.out.println("*##############################################################*");
            System.out.println("");
            System.out.println("");
            System.out.println("      Developpé par Mouhamed Khoutbou Thiam(77 430 90 04)       ");
            System.out.println("----------------------------------------------------------------");
            System.out.println("");
            System.out.println("           L'application est bien lancée au PORT 6666           ");
            System.out.println("");
            System.out.println("");

            // ici on demare une nouveau thread a chaque nouvelle connection
            while (true) new ClientHandler(serverSocket.accept()).start();

        } catch (IOException e) { // en cas d'erreur

            System.out.println("");
            System.out.println("");
            System.out.println("Le Moniteur est deja lancée dans cette machine ou que le PORT 6666 est occupé !!!");

        }
    }

    @Override
    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            
        }  
    }

    // cette sous classe permet de creer de nouvelle threads pour donner la possibité de multiple connexion
    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out; // permet d'envoier un message
        private BufferedReader in; // permet de recevoir un message

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        // c'est cette methode qui sera executéme par le thread 
        public void run() {

            try {

                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String ticketChecking = in.readLine();

                if (ticketChecking.equals("ticket")) { // execution pour recuperation de ticket

                    out.println("Bienvenu , vous etes bien connecte ");

                    while (!"exit".equals(in.readLine())) { // un message "exit" permet d'arreter le thread

                        queuTop++;
                        out.println("|| ticket : << " + queuTop + " >> ||");

                    }

                } else { // execution pour l'appele du suivant

                    String checkerNumber = in.readLine();
                    out.println("Bienvenu , vous etes bien connecte : Guichet numero " + checkerNumber);

                    while (!"exit".equals(in.readLine())) { // un message "exit" permet d'arreter le thread

                        if (queuTop <= queuHead) // verification s'il quel qu'un en attente
                            out.println("IL n'y a personne en attente");
                        else {
                            queuHead++;
                            out.println("|| Numero : << " + queuHead + " >> ||");
                            System.out.println("");
                            System.out.println("      || Numero : << " + queuHead + " >> ||     *     || Guichet : << " + checkerNumber + " >> ||");
                            System.out.println("");
                            System.out.println("  ----------------------------------------------------------------");

                        }

                    }

                }

                out.println("Bye Bye"); 
                in.close();
                out.close();
                clientSocket.close();

            } catch (IOException e) {

            }

        }
    }

}