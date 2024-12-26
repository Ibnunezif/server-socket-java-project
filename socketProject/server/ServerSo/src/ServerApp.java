import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class ReadMessage extends Thread {
    Socket socket;
    public ReadMessage(Socket socket) {
        this.socket = socket;
    }
    DataInputStream in;
    public void run() {
        try {
            in = new DataInputStream(socket.getInputStream());
            while (true) {
                String clientMessage = in.readUTF();
                System.out.println("user: " + clientMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class WriteMessage extends Thread {
    Socket socket;
    public WriteMessage(Socket socket) {
        this.socket = socket;
    }
    static DataOutputStream out;
    static Scanner scanner = new Scanner(System.in);

    public void run() {
        try {
            out = new DataOutputStream(socket.getOutputStream());
            while (true) {
                // System.out.print("you: ");
                String str = scanner.nextLine();
                out.writeUTF(str);
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class ServerApp {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        Socket socket = serverSocket.accept();

        ReadMessage rm = new ReadMessage(socket);
        WriteMessage wm = new WriteMessage(socket);
        rm.start();
        wm.start();
    }
}
