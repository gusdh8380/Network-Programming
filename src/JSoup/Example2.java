package JSoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Example2 {
	public static void main(String[] args) throws IOException {
		String list_url = "https://www.skhu.ac.kr/skhu/1038/subview.do";


		Document document = Jsoup.connect(list_url).get();


		Elements tr_list = document.select("table.board-table.horizon1 tbody tr");
		//태그 목록을 리턴할 때는 select 메소드를 사용하여서 document 문서 전체에서 찾는다.

		for (Element tr : tr_list) {
			Element a = tr.selectFirst("td.td-subject a");
			String article_url = a.attr("href");//a 태그의 href 속성 값을 리턴한다. 이 값은 게시글 내용 URL 이다.
			String title = a.text();
			System.out.println(article_url + " " + title);
		}
	}
}