package JSoup;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Example1 {
	public static void main(String[] args) throws IOException {

		String list_url = "https://www.skhu.ac.kr/skhu/1038/subview.do";
		Document document = Jsoup.connect(list_url).get();
		// 공지 게시글 목록 페이지를 웹 서버로부터 받아와서,
		// 웹 페이지의 내용을 담은 Document 객체를 생성한다.

		Elements tr_list = document.select("table.board-table.horizon1 tbody tr");
		//공지 게시글 목록 웹페이지 document 객체의 내용에서
		// "table,borad-table.horizon1 tbody tr" CSS selector에 해당하는 태그를 찾아서
		//그 태그들 목록을 리턴한다.

		for (Element tr : tr_list) {
			System.out.println(tr.text());
		}
		//tr_list 목록에 들어있는 tr 태그 각각에 대해서 반복한다.
	}
}