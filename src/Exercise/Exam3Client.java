package Exercise;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Exam3Client {
	public static void main(String[] args) throws UnknownHostException, IOException{
		final String HOST = "localhost";
		final int PORT = 10013;

		try(Socket s = new Socket(HOST,PORT)){
			InputStream in = s.getInputStream();
			StringBuilder result = new StringBuilder();

			while(true) {
				int c = in.read();
				if(c ==-1) break;
				result.append((char)c);

			}

			System.out.println(result.toString());
		}
	}
}
