package trans;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.lang.Object;
import java.lang.Class;
import java.lang.reflect.Field;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.lang.reflect.Method;
import java.net.Socket;
import java.lang.reflect.*;
import javax.swing.JTable;
import java.util.Arrays;
import java.util.Vector;
import java.awt.BorderLayout ;
import java.awt.GridLayout;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;

import listener.*;
import trans.Client;

public class InterfaceClient extends JFrame{
    //attribut
    int idMipoitra=0;
    Client c;
    ThreadTransfererFenetre threadTransfererFenetre;
    EnvoyerAction envoyerAction;

    
    







    //constructor
    public InterfaceClient(int idMipoitra,Client c,ThreadTransfererFenetre threadTransfererFenetre,EnvoyerAction envoyerAction)
    {
        setIdMipoitra(idMipoitra);
        setThreadTransfererFenetre(threadTransfererFenetre);
        setC(c);
        setEnvoyerAction(envoyerAction);
        if(getIdMipoitra()==0)
        {
            JPanel panel=new JPanel();
            panel.setSize(500,500);
            panel.setLayout(new BorderLayout());
            JButton importer= new JButton("importer un fichier");
            importer.addActionListener(new BoutonListener(this));
            panel.add(importer, BorderLayout.CENTER);
            this.add(panel);
            this.setSize(500,500);
            this.setTitle("Transfer de fichier");
            this.setResizable(false);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.pack();
            this.setVisible(true);
            this.setLocationRelativeTo(null);
        }
        else if(getIdMipoitra()==1)
        {
            String chemin=getChemin();
            String[] cut=chemin.split("\\\\",-2);

            System.out.println(cut[cut.length-1]);

            try {
                DataOutputStream dout=new DataOutputStream(getC().getS().getOutputStream());  
                getEnvoyerAction().setIndication(1);
                // ObjectOutputStream out=new ObjectOutputStream(getC().getS().getOutputStream());
                // out.writeObject(2);
                dout.writeUTF("envoyer");
                dout.writeUTF(cut[cut.length-1]);
                getC().sendFileServeurPrincipal(chemin);
                // getC().getS().getOutputStream().flush();
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            // getThreadTransfererFenetre().setOuvrir(1);
        }
        else if(getIdMipoitra()==3)
        {
            getEnvoyerAction().setIndication(1);
            getEnvoyerAction().setIndicationStop(0);
            getThreadTransfererFenetre().setOuvrir(1);
        }
    }

    public InterfaceClient(int idMipoitra)
    {
        setIdMipoitra(idMipoitra);
        
        if(getIdMipoitra()==0)
        {
            JPanel panel=new JPanel();
            panel.setSize(500,500);
            panel.setLayout(new BorderLayout());
            JButton importer= new JButton("importer un fichier");
            importer.addActionListener(new BoutonListener(this));
            panel.add(importer, BorderLayout.CENTER);
            this.add(panel);
        }
        else if(getIdMipoitra()==1)
        {
            String chemin=getChemin();
            String[] cut=chemin.split("\\\\",-2);

            System.out.println(cut[cut.length-1]);

            // DataOutputStream dout=new DataOutputStream(getC().getS().getOutputStream());  
            // dout.writeUTF("envoyer");
            // dout.writeUTF(cut[cut.length-1]);
            // getC().sendFileServeurPrincipal(chemin);
        }
        
        this.setSize(500,500);
		this.setTitle("Transfer de fichier");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
    }







    //getters and setters
    public int getIdMipoitra() {
        return idMipoitra;
    }
    public void setIdMipoitra(int idMipoitra) {
        this.idMipoitra = idMipoitra;
    }
    
    public Client getC() {
        return c;
    }
    public void setC(Client c) {
        this.c = c;
    }

    public ThreadTransfererFenetre getThreadTransfererFenetre() {
        return threadTransfererFenetre;
    }

    public void setThreadTransfererFenetre(ThreadTransfererFenetre threadTransfererFenetre) {
        this.threadTransfererFenetre = threadTransfererFenetre;
    }

    public EnvoyerAction getEnvoyerAction() {
        return envoyerAction;
    }

    public void setEnvoyerAction(EnvoyerAction envoyerAction) {
        this.envoyerAction = envoyerAction;
    }








    //fonction
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
            System.out.println(file.getAbsolutePath());
            result=file.getAbsolutePath();
        }
        return result;
    }
}
