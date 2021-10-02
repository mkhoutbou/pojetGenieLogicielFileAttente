package com.file_attente;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

// cette classe permet d'etablire une connexion a une autre application a travers les sockets
public class Guest {
    private Socket clientSocket;
    private PrintWriter out; // permet d'envoier un message
    private BufferedReader in; // permet de lire un message

    // la connexion a une applicatin qui ecoute au port ...
    public void startConnection(String ip, int port) throws UnknownHostException, IOException {
        clientSocket = new Socket(ip, port);// c'est ce constructeur de Socket qui va etablir la connexion  
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    // envoi d'un message 
    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        String resp = in.readLine();
        return resp;
    }


    public void sendMessageWithoutStack(String msg){
        out.println(msg);
    }

    // arreter la connexion
    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}
