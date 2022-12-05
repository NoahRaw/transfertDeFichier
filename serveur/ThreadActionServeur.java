package trans;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ThreadActionServeur extends Thread
{
    //attribut
    int indicationAction;
    Serveur serveur;

    
    
    




    //constructor
    public ThreadActionServeur(int indicationAction,Serveur serveur)
    {
        setIndicationAction(indicationAction);
        setServeur(serveur);
    }






    //acion
    public void run()
    {
        while(getIndicationAction()==0){
        try
        {
        ObjectInputStream in=new ObjectInputStream(getServeur().getSock().getInputStream());
        // System.out.println(in.available());
        setIndicationAction((int)in.readObject());
        // System.out.println(getIndicationAction());
        if(getIndicationAction()==1)
            // this.interrupt();
        {
            // System.out.println("Miarahaba anle tafiditra o");
            DataInputStream dis=new DataInputStream(serveur.getSock().getInputStream());  
            String todo=(String)dis.readUTF();
            String fileName=(String)dis.readUTF();
            System.out.println("commande="+todo);
            System.out.println("fileName="+fileName);

            if(todo.equalsIgnoreCase("envoyer"))
            {
                
                    // DataOutputStream dout=new DataOutputStream(getServeur().getS1().getOutputStream());  
                    // dout.writeUTF(todo);
                    // dout.writeUTF(fileName);
                    // DataOutputStream dout1=new DataOutputStream(getServeur().getS1().getOutputStream());  
                    // dout1.writeUTF(todo);
                    // dout1.writeUTF(fileName);
                    // DataOutputStream dout2=new DataOutputStream(getServeur().getS1().getOutputStream());  
                    // dout2.writeUTF(todo);
                    // dout2.writeUTF(fileName);
                    // serveur.sendFileServeurSecondaire(fileName);
                    serveur.sendFileServeurSecondaire2(fileName);
                    setIndicationAction(0);
            }
        }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    }


    
    
    
    
    
    //getters and seters
    public int getIndicationAction() {
        return indicationAction;
    }

    public void setIndicationAction(int indicationAction) {
        this.indicationAction = indicationAction;
    }
    
    public Serveur getServeur() {
        return serveur;
    }
    public void setServeur(Serveur serveur) {
        this.serveur = serveur;
    }
}
