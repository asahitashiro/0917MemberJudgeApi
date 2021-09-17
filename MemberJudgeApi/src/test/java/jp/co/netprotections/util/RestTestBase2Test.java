package jp.co.netprotections.util;

import java.io.IOException;


public class RestTestBase2Test {
	
	public static void main(String[] args) throws IOException {
		
			String document = RestTestBase2.readForObject("test001.json");		
			System.out.print(document);
		
	}	
}
