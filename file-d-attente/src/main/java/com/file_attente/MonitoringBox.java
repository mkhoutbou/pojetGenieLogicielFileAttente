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
    public void start() {
        System.out.println("Bienvenu, Ici s'affiche les numero Appele");
        try {
            serverSocket = new ServerSocket(PORT);
            // ici on demare une nouveau thread a chaque nouvelle connection
            while (true) new ClientHandler(serverSocket.accept()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() throws IOException {
        serverSocket.close();
    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {

            try {

                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String ticketChecking = in.readLine();

                if (ticketChecking.equals("ticket")) {

                    out.println("Bienvenu , vous etes bien connecte ");

                    while (!"exit".equals(in.readLine())) {

                        queuTop++;
                        out.println("ticket numero T_" + queuTop);

                    }

                } else {

                    String checkerNumber = in.readLine();
                    out.println("Bienvenu , vous etes bien connecte : caisse numero G_" + checkerNumber);

                    while (!"exit".equals(in.readLine())) {

                        if (queuTop <= queuHead)
                            out.println("IL n'y a personne en attente");
                        else {
                            queuHead++;
                            out.println("Numero T_" + queuHead);
                            System.out.println("Numero T_" + queuHead + " est appele au guichet Numero G_" + checkerNumber);
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