package JSoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Example3 {
	public static void main(String[] args) throws IOException {
		String list_url = "https://www.skhu.ac.kr/skhu/1038/subview.do";
		Document document = Jsoup.connect(list_url).get();
		Elements tr_list = document.select("table.board-table.horizon1 tbody tr");
		for (Element tr : tr_list) {
			Element a = tr.selectFirst("td.td-subject a");
			String article_url = a.attr("href");
			String title = a.text();
			System.out.println(article_url + " " + title);

			Document doc2 = Jsoup.connect("https://www.skhu.ac.kr/" + article_url).get();
			// 게시글 본문 페이지를 웹서버로부터 받아와서, 웹 페이지의 내용을 담은 Document 객체를 생성한다.

			Element div = doc2.selectFirst("div.view-con");
			// 게시글 본문 내용 div 태그를 찾는다.

			String content = div.text();
			// 게시글 본문 내용 꺼내기

			System.out.println(content + "\n\n");
		}
	}
}