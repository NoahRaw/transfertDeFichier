//cette classe permet de dire au serveur principal qu'il peut envoyer ou recuperer un fichier
package trans;

import java.io.ObjectOutputStream;

import trans.*;

public class EnvoyerAction extends Thread
{
    //attribut
    int indication=0;    //1 si le serveur sinon 0
    Client client;
    int indicationStop=1;    //arreter d'envoyer au serveur quand il fait une action en cours









    //constructor
    public EnvoyerAction(int indication,Client client)
    {
        setIndication(indication);
        setClient(client);
    }









    //getters and setters
    public int getIndication() {
        return indication;
    }
    public void setIndication(int indication) {
        this.indication = indication;
    }

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    public int getIndicationStop() {
        return indicationStop;
    }
    public void setIndicationStop(int indicationStop) {
        this.indicationStop = indicationStop;
    }






    //action-----------------
    public void run()
    {
        while(true)
        {
            if(getIndicationStop()==1)
            {
                try{
                    ObjectOutputStream out=new ObjectOutputStream(getClient().getS().getOutputStream());
                    if(getIndication()==0)
                    {
                        out.writeObject(0);
                    }
                    else if(getIndication()==1 && getIndicationStop()==1)
                    {
                        out.writeObject(1);
                        setIndicationStop(0);
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}