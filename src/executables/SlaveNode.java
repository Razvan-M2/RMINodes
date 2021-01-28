package executables;

import logic.CLIExecutor;
import logic.RMICommandExecutor;
import logic.FileExplorerImpl;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author Razvan
 */
public class SlaveNode {

    public static void main(String args[]) throws RemoteException, MalformedURLException {
        LocateRegistry.createRegistry(9000);
        RMICommandExecutor commandExecutor = new CLIExecutor(new FileExplorerImpl());
        Naming.rebind("rmi://localhost:9000/fileExplorer", commandExecutor);
    }
}
