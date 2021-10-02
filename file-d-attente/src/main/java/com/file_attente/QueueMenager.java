package com.file_attente;
import java.io.IOException;
import java.net.UnknownHostException;
public class QueueMenager {
    public static void main(String[] args) throws UnknownHostException, IOException {

        String choise = args[0];

        switch (choise) {

            case "moniteur": 
                choise = BoxFactory.MONITORING;
                break;
            case "ticket" : 
                choise = BoxFactory.TICKET;
                break;
            case "caisse" : 
                choise = BoxFactory.CHECKING;
                break;
            default : break;

        }

        BoxFactory.getBox(choise).start();
        
    }



}
