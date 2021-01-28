package logic;


/**
 *
 * @author Razvan
 */
public interface FileExplorer {


    String readFileContent(String name);
    
    String listAllFiles();
    
    String deleteFile(String name);
    
    String createFile(String name);
    
    String fileWrite(String name,String message);
    
    String fileCreateWrite(String name,String message);
}
