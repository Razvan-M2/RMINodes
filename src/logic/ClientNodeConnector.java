/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;


import logic.RMICommandExecutor;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Razvan
 */
public class ClientNodeConnector extends UnicastRemoteObject implements RMIClientNodeConnector {

    public ClientNodeConnector() throws RemoteException {
    }

    @Override
    public String forwardCommand(String command) throws RemoteException {
        RMICommandExecutor c = null;
        try {
            String rmiName = "rmi://localhost:9000/fileExplorer";

            System.out.println("Connecting to: " + rmiName);
            c = (RMICommandExecutor) Naming.lookup(rmiName);
        } catch (NotBoundException | MalformedURLException ex) {
            Logger.getLogger(ClientNodeConnector.class.getName()).log(Level.SEVERE, null, ex);
        }

        return c.execute(command);
    }

}
