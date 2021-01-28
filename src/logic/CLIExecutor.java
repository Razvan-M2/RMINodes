/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Razvan
 */
public final class CLIExecutor extends UnicastRemoteObject implements RMICommandExecutor {

    //  REGEX in order to find possible combinations
    private static final Pattern REGEX = Pattern.compile("^(.+?)([ ](.*))?$");

    //  FileExplorer to interact and edit directly the root folder (content folder)
    private final FileExplorer editor;

    public CLIExecutor(FileExplorer fileExplorer) throws RemoteException {
        this.editor = fileExplorer;
    }

    @Override
    public String execute(String command) throws RemoteException {
        Matcher match = REGEX.matcher(command);

        if (match.matches()) {
            String cmd = match.group(1);
            System.err.println("Received command " + cmd);

            switch (cmd) {
                case "content":
                    return editor.readFileContent(match.group(3));
                case "list":
                    return editor.listAllFiles();
                case "delete":
                    return editor.deleteFile(match.group(3));
                case "create":
                    return editor.createFile(match.group(3));
                case "write":
                    return processAppend(match.group(3));
                case "cwrite":
                    return processAppendAndC(match.group(3));
                default:
                    System.err.println("The command doesn't exist!");
                    return "The command does not exist!";
            }
        }

        return "Invalid input!";

    }

    private String processAppend(String appendParams) {
        Matcher matcher = REGEX.matcher(appendParams);
        if (matcher.matches()) {
            if (matcher.group(3) == null) {
                return "The command can't be executed";
            }
            return editor.fileWrite(matcher.group(1), matcher.group(3));
        }
        return "The command can't be executed";
    }

    private String processAppendAndC(String appendParams) {
        Matcher matcher = REGEX.matcher(appendParams);
        if (matcher.matches()) {
            return editor.fileCreateWrite(matcher.group(1), matcher.group(3));
        }
        return "The command can't be executed";
    }

}
