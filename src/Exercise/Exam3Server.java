package Exercise;

/*
 Port 번호: 10013

클라이언트가 연결되면,

자신의 이름 문자열을 클라이언트에게 전송하고,

연결을 끊는다.

여러 클라이언트 연결에 대해서, 위 작업을 계속 반복한다.

싱글 쓰레드로 구현한다.
 */
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;


public class Exam3Server {

	public static void main(String[] args) {

		final int PORT = 10013;

		try(ServerSocket s = new ServerSocket(PORT)){
			while(true) {
				try(Socket soc = s.accept()){
					Writer out = new OutputStreamWriter(soc.getOutputStream(), "UTF-8");
					out.write("Jeon Hyeon O");
					out.flush();
				}catch(IOException ex) {}
			}
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
}
