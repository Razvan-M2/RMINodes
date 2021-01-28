package executables;

import logic.RMIClientNodeConnector;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.NotBoundException;

/**
 *
 * @author Razvan
 */
public class Client {

    public static void main(String[] args) throws IOException, NotBoundException {

        String rmiName = "rmi://localhost:6000/master";

        System.out.println("Connecting to: " + rmiName);
        RMIClientNodeConnector c = (RMIClientNodeConnector) Naming.lookup(rmiName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("\n(Linux interface input):");
            String userInput = reader.readLine();
            System.out.println("\n"+c.forwardCommand(userInput)+"\n");
        }

    }

}
