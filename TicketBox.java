import java.io.IOException;
import java.util.Scanner;

// la classe qui gere la generation des tickets
public class TicketBox extends Client implements Box {

    @Override
    public void start() {

        try {

            startConnection(MonitoringBox.IP, MonitoringBox.PORT); // connection avec le serveur (moniteur)
            String argument = "continue";
            Scanner scanner = new Scanner(System.in);
            String response = sendMessage("ticket"); // ici on dit au moniteur qu'on se connecte en tant que ticket generateur
            System.out.println(response);

            while (!argument.equals("exit")) { // on sort de cette quand l'utilisateur donne exit comme entree

                System.out.print("Taper sur \"Entrer\" pour Nouveau Ticket >>> ");
                argument = scanner.nextLine();
                response = sendMessage(argument);
                System.out.println(response); // la reponse di moniteur ici c'est le numero du ticket

            }

            scanner.close();
            stopConnection(); // on stop la connection quand on sort de la boucle

        }  catch (IOException e) {

            System.out.println("Verifier que le moniteur est demarer et ensuite ressayer");
            
        }
        
    }
    
}
