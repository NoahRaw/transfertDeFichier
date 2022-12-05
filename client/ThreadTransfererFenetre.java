//classe pour la fenetre d'envoie et recuperation d'un fichier
package trans;

import trans.*;

public class ThreadTransfererFenetre extends Thread
{
    //attribut
    EnvoyerAction envoyerAction;
    int ouvrir;     //indique si on doit ouvrir un autre fenetre ou pas
    Client client;


    
    
    
    
    //constructor
    public ThreadTransfererFenetre(EnvoyerAction envoyerAction,int ouvrir,Client client)
    {
        setEnvoyerAction(envoyerAction);
        setOuvrir(ouvrir);
        setClient(client);
    }



    
    
    

    //getters and setters
    public EnvoyerAction getEnvoyerAction() {
        return envoyerAction;
    }
    public void setEnvoyerAction(EnvoyerAction envoyerAction) {
        this.envoyerAction = envoyerAction;
    }

    public int getOuvrir() {
        return ouvrir;
    }
    public void setOuvrir(int ouvrir) {
        this.ouvrir = ouvrir;
    }

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }







    //action-----------------
    public void run()
    {
        while(true)
        {
            if(getOuvrir()==1)
            {
                setOuvrir(0);
                getEnvoyerAction().setIndication(0);
                // getEnvoyerAction().setIndicationStop(1);
                InterfaceClient interfaceClient=new InterfaceClient(0,client,this,envoyerAction);
            }
        }
    }
}
