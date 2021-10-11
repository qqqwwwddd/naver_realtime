package com.cos.naverrealtime.batch;

import java.text.ParseException;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cos.naverrealtime.domain.News;
import com.cos.naverrealtime.domain.NewsRepository;
import com.cos.naverrealtime.util.NaverCraw;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class NewsBatch {

	private final NewsRepository newsRepository;
	private final NaverCraw naverCraw; // DI
	 
	@Scheduled(cron = "0 0 1 * * ?")
	public void naverCrawAndSave() {
	
		List<News> newsList ;
		try {
			newsList = naverCraw.newsCollect();
			newsRepository.saveAll(newsList);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("실행");
	}
}