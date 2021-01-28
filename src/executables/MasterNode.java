/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package executables;

import logic.RMIClientNodeConnector;
import logic.ClientNodeConnector;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Razvan
 */
public class MasterNode {

    public static void main(String[] args) throws NotBoundException, IOException {

        try {

            Registry registry;
            registry = LocateRegistry.createRegistry(6000);
            RMIClientNodeConnector commandExecutor = new ClientNodeConnector();
            Naming.rebind("rmi://localhost:6000/master", commandExecutor);
        } catch (Exception e) {
            System.out.println("Exception encountered: " + e);
            e.printStackTrace();
        }

    }
}
