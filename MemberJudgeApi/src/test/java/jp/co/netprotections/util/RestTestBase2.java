package jp.co.netprotections.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RestTestBase2 {
	
	public static String readForObject(String jsonFileName) throws IOException{
		File file = new File("src/test/resources/jp/co/netprotections/controller/"+jsonFileName);
		
		StringBuilder sb = new StringBuilder();
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String text;
			while ((text = br.readLine()) != null) {
				sb.append(text);
			}
			
			return sb.toString();
		}
	}
	
	public static void main(String[] args) {
		File file = new File("src/test/resources/jp/co/netprotections/controller/test002.json");
				
				StringBuilder sb = new StringBuilder();
				
				try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				
					String text;
					while ((text = br.readLine()) != null) {
						sb.append(text);
					}
					br.close();
					
					String document = sb.toString();
			        
			     System.out.print(document);
			     }catch (FileNotFoundException e) {
						// TODO 自動生成された catch ブロック
						System.out.print("FileNotFoundException");
					} catch (IOException e) {
						// TODO 自動生成された catch ブロック
						System.out.print("IOException");
					} finally {
						System.out.print("mystery");
					}
				
		        
			}
	
	
}
