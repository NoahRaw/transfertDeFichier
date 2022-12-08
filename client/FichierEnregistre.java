package trans;

import javax.swing.*;
import java.io.*;
 
public class FichierEnregistre extends JPanel 
{
    //attribut
    JList<String> langages;
  







    //constructor
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
        setLangages(new JList<>(model));
        add(langages);
         
        // this.setTitle("Exemple de JList");  
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
        this.setSize(500,500);
        // this.setLocationRelativeTo(null);
        // this.setVisible(true);
    }
    
    








    //getters and setters
    public JList<String> getLangages() {
        return langages;
    }

    public void setLangages(JList<String> langages) {
        this.langages = langages;
    }
}