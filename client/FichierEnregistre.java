package trans;

import javax.swing.*;
import java.io.*;
 
public class FichierEnregistre extends JFrame 
{
    private JList<String> langages;
  
    public FichierEnregistre() 
    {
        //créer le modèle et ajouter des éléments
        DefaultListModel<String> model = new DefaultListModel<>();
        try
        {
            // Le fichier d'entrée
            File file = new File("liste.txt");    
            // Créer l'objet File Reader
            FileReader fr = new FileReader(file);  
            // Créer l'objet BufferedReader        
            BufferedReader br = new BufferedReader(fr);  
            StringBuffer sb = new StringBuffer();    
            String line;
            while((line = br.readLine()) != null)
            {
                model.addElement(line);
            }
            fr.close();    
            System.out.println("Contenu du fichier: ");
            System.out.println(sb.toString());  
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
 
        //créer la liste des langages
        langages = new JList<>(model);
        add(langages);
         
        this.setTitle("Exemple de JList");  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
        this.setSize(200,200);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }       
}