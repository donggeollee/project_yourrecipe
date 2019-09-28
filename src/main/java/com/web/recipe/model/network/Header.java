package com.web.recipe.model.network;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Header<T> {

	
	private LocalDateTime transactionTime;
	
	private String resultCode;
	
	private String description;
	 
	private T data;
	
	public static <T> Header<T> OK(){
		return (Header<T>) Header.builder()
				.transactionTime(LocalDateTime.now())
				.resultCode("OK")
				.description("OK")
				.build();
	}
	
	public static <T> Header<T> OK(T data){
		return (Header<T>) Header.builder()
				.transactionTime(LocalDateTime.now())
				.resultCode("OK")
				.description("OK")
				.data(data)
				.build();
	}
	
	public static <T> Header<T> ERROR(String description){
		return (Header<T>) Header.builder()
				.transactionTime(LocalDateTime.now())
				.resultCode("ERROR")
				.description(description)
				.build();
	}
	
}
