package com.cos.naverrealtime.domain;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Document(collection = "naver_realtime")
@Data
public class News {
	@Id
	private String _id;
	private String company;
	private String title;
	private String createdAt;
}
