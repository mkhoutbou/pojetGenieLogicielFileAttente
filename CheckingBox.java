import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class CheckingBox extends Client implements Box {

    @Override
    public void start() {

        try {

            startConnection(MonitoringBox.IP, MonitoringBox.PORT);
            String argument = "continue";
            Scanner scanner = new Scanner(System.in);
            sendMessageWithoutStack("checker");
            System.out.print("Donner le numero du Caisse : ");
            argument = scanner.nextLine();
            String response = sendMessage(argument);
            System.out.println(response);

            while (!argument.equals("exit")) {

                System.out.print("Taper sur \"Entrer\" pour Suivant ou taper \"exit\" pour quiter >>> ");
                argument = scanner.nextLine();
                response = sendMessage(argument);
                System.out.println(response);

            }

            scanner.close();
            stopConnection();

        }  catch (IOException e) {

            System.out.println("Verifier que le moniteur est demarer et ensuite ressayer");
            
        }
    }

}
