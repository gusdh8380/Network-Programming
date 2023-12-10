package Exercise;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


//객체를 주고 받는 서버와 클라 구
public class Exam5Server {
    public static void main(String[] args) {
        final int PORT = 10015;
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    new GuguTask(socket).run();
                } catch (IOException ex) {
                    System.err.println("Socket Accept Error: " + ex);
                }
            }
        } catch (IOException ex) {
            System.err.println("ServerSocket Error: " + ex);
        }
    }

    static class GuguTask implements Runnable {
        Socket socket;

        public GuguTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (Socket autoClose = socket) {
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                String a = "전현오";
                DTO d = new DTO(a);
                out.writeObject(d);
                out.flush();
            } catch (IOException ex) {
                System.err.println("GuguTask Error: " + ex);
            }
        }
    }
}
