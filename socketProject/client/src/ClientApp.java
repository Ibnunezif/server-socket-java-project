import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

class ReadMessage extends Thread {
    Socket socket;
    public ReadMessage (Socket socket){
        this.socket=socket;
    }
        DataInputStream in;
        public void run (){
            try {
                in = new DataInputStream(socket.getInputStream());
                while (true) {
                    String clientMassege=in.readUTF();
                    System.out.println ("user :"+clientMassege);
                }

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
}

class WriteMessage extends Thread {
    Socket socket;
    public WriteMessage  (Socket socket){
        this.socket=socket;
    }
     static DataOutputStream out;
     static Scanner scanner = new Scanner(System.in);
 
     public void run() {
         try {
             
             out = new DataOutputStream(socket.getOutputStream());
 
             while (true) {
                 String str = scanner.nextLine();
                 out.writeUTF(str);
                 out.flush(); 
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
}

public class ClientApp {
   public static void main (String [] args) throws Exception {
    Socket socket = new Socket("localhost", 8000);
    ReadMessage rm= new ReadMessage(socket);
    WriteMessage wm=new WriteMessage(socket);
    rm.start();
    wm.start();
   }
}
