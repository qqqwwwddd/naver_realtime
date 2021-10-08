package com.cos.naverrealtime.batch;

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
	 
	@Scheduled(fixedDelay=1000*60*1)
	public void naverCrawAndSave() {
	
		List<News> newsList = naverCraw.newsCollect();
		
		try {
	         System.out.println(newsList);
	         System.out.println("=======================");
	         newsRepository.saveAll(newsList);
	         System.out.println(newsList);
	      } catch (Exception e) {
	         System.out.println("db저장 오류");
	      }
	      
	      System.out.println("실행됨");
	      
	   }
	}