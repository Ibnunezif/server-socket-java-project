import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp {
    static DataInputStream in;
    static DataOutputStream out;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8000);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());

            while (true) {
                System.out.print("you: ");
                String str = scanner.nextLine();
                out.writeUTF(str);
                out.flush(); 

                String clientMassege=in.readUTF();
                System.out.println ("user :"+clientMassege);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}