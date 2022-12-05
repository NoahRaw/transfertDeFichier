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








    //---------- cosnstructor -----------//
    public BoutonListener()
    {
        
    }

    public BoutonListener(InterfaceClient interfaceClient) {
        this.interfaceClient = interfaceClient;
    }










    //---------- getters and setters -----------//
    public InterfaceClient getInterfaceClient() {
        return interfaceClient;
    }
    public void setInterfaceClient(InterfaceClient interfaceClient) {
        this.interfaceClient = interfaceClient;
    }   











    //---------- action -----------//
    public void actionPerformed(ActionEvent e)
    {
        getInterfaceClient().setVisible(false);
        InterfaceClient i=new InterfaceClient(1,getInterfaceClient().getC(),getInterfaceClient().getThreadTransfererFenetre(),getInterfaceClient().getEnvoyerAction());
        // InterfaceClient i=new InterfaceClient(3,getInterfaceClient().getC(),getInterfaceClient().getThreadTransfererFenetre(),getInterfaceClient().getEnvoyerAction());
        // InterfaceClient i=new InterfaceClient(1);
    }
}