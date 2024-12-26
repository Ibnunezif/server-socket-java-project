import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerApp {
    static DataInputStream in;
    static DataOutputStream out;
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("Server is listening on port 8000...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            while (true) {
                String receivedMessage = in.readUTF();
                System.out.println("user: " + receivedMessage);

                System.out.print("you: ");
                String str = scanner.nextLine();
                out.writeUTF(str);
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}