package listener;

import java.util.Vector;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.reflect.Method;
import java.lang.reflect.*;
import java.lang.Object;
import java.lang.Class;
import java.io.*;
import trans.*;
import javax.swing.text.AbstractDocument.Content;

public class BoutonListener implements ActionListener
{
    //---------- attribut -----------//
    InterfaceClient interfaceClient;
    int idBouton=0;
    String aRecuperer;
    FichierEnregistre fichierEnregistre;








    

    

    //---------- cosnstructor -----------//
    public BoutonListener()
    {
        
    }

    public BoutonListener(InterfaceClient interfaceClient) {
        this.interfaceClient = interfaceClient;
    }

    public BoutonListener(InterfaceClient interfaceClient2, int i,FichierEnregistre fichierEnregistre) {
        this.interfaceClient = interfaceClient2;
        setFichierEnregistre(fichierEnregistre);
        setIdBouton(i);
    }

    public BoutonListener(InterfaceClient interfaceClient2, int i) 
    {
        setInterfaceClient(interfaceClient2);
        setIdBouton(i);
    }










    

    

    //---------- getters and setters -----------//
    public String getaRecuperer() {
        return aRecuperer;
    }

    public void setaRecuperer(String aRecuperer) {
        this.aRecuperer = aRecuperer;
    }
    
    public InterfaceClient getInterfaceClient() {
        return interfaceClient;
    }
    public void setInterfaceClient(InterfaceClient interfaceClient) {
        this.interfaceClient = interfaceClient;
    }
    
    public int getIdBouton() {
        return idBouton;
    }
    public void setIdBouton(int idBouton) {
        this.idBouton = idBouton;
    }

    public FichierEnregistre getFichierEnregistre() {
        return fichierEnregistre;
    }
    public void setFichierEnregistre(FichierEnregistre idBouton) {
        this.fichierEnregistre = idBouton;
    }










    //---------- action -----------//
    public void actionPerformed(ActionEvent e)
    {
        if(getIdBouton()==1)
        {
            getInterfaceClient().setVisible(false);
            InterfaceClient i=new InterfaceClient(getFichierEnregistre().getLangages().getSelectedValue(),getInterfaceClient().getC());    
        }
        else if(getIdBouton()==2)
        {
            getInterfaceClient().setVisible(false);
            InterfaceClient i=new InterfaceClient(2,getInterfaceClient().getC());    
        }
        else
        {
            getInterfaceClient().setVisible(false);
            InterfaceClient i=new InterfaceClient(1,getInterfaceClient().getC());
            // InterfaceClient i=new InterfaceClient(3,getInterfaceClient().getC(),getInterfaceClient().getThreadTransfererFenetre(),getInterfaceClient().getEnvoyerAction());
            // InterfaceClient i=new InterfaceClient(1);
        }
    }
}