package Exercise;

/*
 Port 번호: 10014

클라이언트가 연결되면,

클라이언트가 전송한 문자열을 읽어서, 화면에 출력한다.

연결을 끊는다.

여러 클라이언트 연결에 대해서, 위 작업을 계속 반복한다.

싱글 쓰레드로 구현한다
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Exam4Server {
    public static void main(String[] args) {
        final int PORT = 10014, BACKLOG = 300;
        try (ServerSocket serverSocket = new ServerSocket(PORT, BACKLOG)) {
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    new EchoTask(socket).run();
                } catch (IOException ex) {
                    System.err.println("Socket Accept Error: " + ex);
                }
            }
        } catch (IOException ex) {
            System.err.println("ServerSocket Error: " + ex);
        }
    }

    static class EchoTask implements Runnable {
        Socket socket;

        public EchoTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (Socket autoClose = socket) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(autoClose.getInputStream(), "UTF-8"));
                String s = reader.readLine();
                System.out.println(s);
            } catch (Exception ex) {
                System.err.println("EchoTask Error: " + ex);
            }
        }
    }
}
