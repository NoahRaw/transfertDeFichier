package trans;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class Fenetre extends JFileChooser
{
    //constructor
    public Fenetre()
    {
        // getChemin();
    }

    public String getChemin()
    {
        String result=new String();
        JFileChooser choose = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        
        // Ouvrez le fichier
        int res = choose.showOpenDialog(null);
        
        // Enregistrez le fichier
        // int res = choose.showSaveDialog(null);
        if (res == JFileChooser.APPROVE_OPTION) {
            File file = choose.getSelectedFile();
            // System.out.println(file.getAbsolutePath());
        }
        return result;
    }
}