package com.file_attente;
// ici on a appliquer le design patter factory. BoxFactory.getBox retourne un box selon le context (type de box)
public class BoxFactory {
    public static final String CHECKING = "Checking";
    public static final String MONITORING = "Monitoring";
    public static final String TICKET = "Ticking";

    public static Box getBox(String boxType) {

        Box box = null;

        switch (boxType) {

            case CHECKING: // caissier
                box = new CheckingBox();
                break;
            case MONITORING: // moniteur
                box = new MonitoringBox();
                break;
            case TICKET: // ticket
                box = new TicketBox();
                break;
            default: break;

        }

        return box;
        
    }
}
