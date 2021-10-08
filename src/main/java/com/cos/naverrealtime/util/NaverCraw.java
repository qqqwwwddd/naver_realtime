package com.cos.naverrealtime.util;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cos.naverrealtime.domain.News;

@Component
public class NaverCraw {

	int aidNum = 1;

	public List<News> newsCollect() {
		RestTemplate rt = new RestTemplate();
		List<News> newsList = new ArrayList<>();
		
		for (int i = 1; i < 6; i++) {
			String aid = String.format("%010d", aidNum);
			String url = "https://news.naver.com/main/read.naver?mode=LSD&mid=shm&sid1=103&oid=437&aid=" + aid;
			String html = rt.getForObject(url, String.class);
 
			Document doc = Jsoup.parse(html);
			
			Element companyElement = doc.selectFirst(".content a img");
			Element titleElement = doc.selectFirst("#articleTitle");
			Element createdAtElement = doc.selectFirst(".t11");
			String company = companyElement.attr("alt");
			String title = titleElement.text();
			String createdAt = createdAtElement.text();

			System.out.println(company);
			// System.out.println(time);

			News news = News.builder()
					.company(company)
					.title(title)
					.createdAt(createdAt)
					.build();

			newsList.add(news);

			aidNum++;
		} 
		return newsList;
	}
}