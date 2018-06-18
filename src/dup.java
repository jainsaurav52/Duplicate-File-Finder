/**
 *
 * @Saurav Jain
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class dup {
    
    public static void find(Map<String, List<String>> lists, File dir) {
        for (File f : dir.listFiles()) {
            if (f.isDirectory()) {
                find(lists, f);
            } else {
                    String hash = f.getName()+f.length();
                    //System.out.println(hash);
                    List<String> list = lists.get(hash);
                    if (list == null) {
                    	
                    	// Create a linked list and add duplicate entries
                        list = new LinkedList<String>();
                        lists.put(hash, list);
                    }
                    list.add(f.getAbsolutePath());
                }
            }
        }

     public static void main(String[] arg) throws IOException {
        File dir = new File("C:\\Users\\Administrator\\Desktop\\dup");// location of directory that to be checked
        if (!dir.isDirectory()) {
            System.out.println("Supplied directory does not exist.");
            return;
        }
        Map<String, List<String>> lists = new HashMap<String, List<String>>();
        dup.find(lists, dir);
        for (List<String> list : lists.values()) {
            if (list.size() > 1) {
                System.out.println("\n");
                int i=0;
                for (i=1;i<list.size();i++) {
                    String file=list.get(i);
                    System.out.println(file);
                    Files.deleteIfExists(Paths.get(file));
                }
            }
        }
        System.out.println("\n");
    }
}
