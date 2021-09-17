package jp.co.netprotections.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.net.URI;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import jp.co.netprotections.util.RestTestBase2;

@SpringBootTest
@ExtendWith(SpringExtension.class)

//型 MemberJudgeControllerTest はパッケージと一致しません？？->パッケージにクラス名を使うな
public class MemberJudgeControllerTest extends RestTestBase2{
	

	@Autowired
	public WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private String targetUrl = "http://localhost:8080/api/";

    // モック対象のControllerインスタンスを生成
   	@Before
  	public void setupMock() {
  		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  	}
   	
   	@Test
	public void test000() throws Exception {
		// モック対象へのリクエスト文字列を取得
		String body = RestTestBase2.readForObject("test000.json");

		// Controllerにリクエストを送り、結果を取得
		ResultActions result = mockMvc.perform(post("targetUrl")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body));

		// 結果の検査。
		result.andExpect(status().is(HttpStatus.OK.value()));
		result.andExpect(jsonPath("$.judgedCandidatesResultList[0].memberName", is(nullValue())));
		result.andExpect(jsonPath("$.judgedCandidatesResultList[0].enlistedPropriety",is(false)));
	}
   	
	@Test
	public void test001() throws Exception {
		// モック対象へのリクエスト文字列を取得
		String body = RestTestBase2.readForObject("test001.json");

		// Controllerにリクエストを送り、結果を取得
		ResultActions result = mockMvc.perform(post("src/main/java/jp/co/netprotections/controller/MemberJudgeController.java")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body));

		// 結果の検査。
		result.andExpect(status().is(HttpStatus.OK.value()));
		result.andExpect(jsonPath("$.judgedCandidatesResultList[0].memberName", is(nullValue())));
		result.andExpect(jsonPath("$.judgedCandidatesResultList[0].enlistedPropriety",is(false)));
	}
	
	@Test
	public void test002() throws Exception {
		// モック対象へのリクエスト文字列を取得
		String body = RestTestBase2.readForObject("test002.json");

		// Controllerにリクエストを送り、結果を取得
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(targetUrl)
				.contentType(MediaType.APPLICATION_JSON)
				.content(body));

		// 結果の検査。ステータスがOKであること、および文字列の文字数が6であることを確認します
		result.andExpect(status().is(HttpStatus.OK.value()))
		.andExpect(jsonPath("$.judgedCandidatesResultList[0].memberName",is("tas")))
		.andExpect(jsonPath("$.judgedCandidatesResultList[0].enlistedPropriety",is(false)));
	}
  	
	@Test
	public void test003() throws Exception {
		// モック対象へのリクエスト文字列を取得
		String body = RestTestBase2.readForObject("test003.json");

		// Controllerにリクエストを送り、結果を取得
		ResultActions result = mockMvc.perform(post(targetUrl)
				.contentType(MediaType.APPLICATION_JSON)
				.content(body));

		// 結果の検査。ステータスがOKであること、および文字列の文字数が6であることを確認します
		result.andExpect(status().is(HttpStatus.OK.value()))
		.andExpect(jsonPath("$.judgedCandidatesResultList[0].memberName",is("haruhi")))
		.andExpect(jsonPath("$.judgedCandidatesResultList[0].enlistedPropriety",is(true)));
	}
	
	@Test
	public void test004() throws Exception {
		// モック対象へのリクエスト文字列を取得
		String body = RestTestBase2.readForObject("test004.json");

		// Controllerにリクエストを送り、結果を取得
		ResultActions result = mockMvc.perform(post(targetUrl)
				.contentType(MediaType.APPLICATION_JSON)
				.content(body));

		// 結果の検査。ステータスがOKであること、および文字列の文字数が6であることを確認します
		result.andExpect(status().is(HttpStatus.OK.value()))
		.andExpect(jsonPath("$",is(nullValue())));
	}
	@Test
	public void test005() throws Exception {
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post(targetUrl).contentType(MediaType.APPLICATION_JSON).content("aiueo"));
		
		result.andExpect(status().is(HttpStatus.OK.value()))
		.andExpect(jsonPath("$.judgedCandidatesResultList[0].memberName",is("haruhi")))
		.andExpect(jsonPath("$.judgedCandidatesResultList[0].enlistedPropriety",is(false)));
	}
	
	
	@Test
	public void test009() throws Exception {
		// モック対象へのリクエスト文字列を取得
		String body = RestTestBase2.readForObject("test003.json");

		//uri setting
		URI target = new URI("http://localhost:8080/api");
		
		
		// Controllerにリクエストを送り、結果を取得
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post(target)
				.contentType(MediaType.APPLICATION_JSON)
				.content(body));

		// 結果の検査。ステータスがOKであること、および文字列の文字数が6であることを確認します
		result.andExpect(status().is(HttpStatus.OK.value()))
		.andExpect(jsonPath("$.judgedCandidatesResultList[0].memberName",is("haruhi")))
		.andExpect(jsonPath("$.judgedCandidatesResultList[0].enlistedPropriety",is(true)));
	}
	
	@Test
	public void test010() throws Exception {
		// モック対象へのリクエスト文字列を取得
		String body = super.readForObject("test003.json");

		// Controllerにリクエストを送り、結果を取得
		ResultActions result = mockMvc.perform(post(targetUrl)
				.contentType(MediaType.APPLICATION_JSON)
				.content(body));
		
		if (result == null) {
			assertEquals(1,1);
		}
		else {

		// 結果の検査。ステータスがOKであること、および文字列の文字数が6であることを確認します
		result.andExpect(status().is(HttpStatus.OK.value()))
		.andExpect(jsonPath("$.judgedCandidatesResultList[0].memberName",is("haruhi")))
		.andExpect(jsonPath("$.judgedCandidatesResultList[0].enlistedPropriety",is(true)));
		}
	}

	@Test
	public void test011() throws Exception {
		// モック対象へのリクエスト文字列を取得
		String body = super.readForObject("test003.json");
		
		
		// Controllerにリクエストを送り、結果を取得
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(targetUrl)
				.contentType(MediaType.APPLICATION_JSON)
				.content(body));

		// 結果の検査。ステータスがOKであること、および文字列の文字数が6であることを確認します
		result.andExpect(status().is(HttpStatus.OK.value()))
		.andExpect(jsonPath("$.judgedCandidatesResultList[0].memberName",is("haruhi")))
		.andExpect(jsonPath("$.judgedCandidatesResultList[0].enlistedPropriety",is(true)));
	}
	@Test
	public void test012() throws Exception {
		// モック対象へのリクエスト文字列を取得
		String body = RestTestBase2.readForObject("test003.json");
		
		
		// Controllerにリクエストを送り、結果を取得
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put(targetUrl)
				.contentType(MediaType.APPLICATION_JSON)
				.content(body));

		// 結果の検査。ステータスがOKであること、および文字列の文字数が6であることを確認します
		result.andExpect(status().is(HttpStatus.OK.value()))
		.andExpect(jsonPath("$.judgedCandidatesResultList[0].memberName",is("haruhi")))
		.andExpect(jsonPath("$.judgedCandidatesResultList[0].enlistedPropriety",is(true)));
	}
	
	@Test
	public void test013() throws Exception {
		// モック対象へのリクエスト文字列を取得
		String body = RestTestBase2.readForObject("test003.json");
		
		
		// Controllerにリクエストを送り、結果を取得
		ResultActions result = mockMvc.perform(get(targetUrl)
				.contentType(MediaType.APPLICATION_JSON)
				.content(body));

		// 結果の検査。ステータスがOKであること、および文字列の文字数が6であることを確認します
		result.andExpect(status().is(HttpStatus.OK.value()))
		.andExpect(jsonPath("$.judgedCandidatesResultList[0].memberName",is("haruhi")))
		.andExpect(jsonPath("$.judgedCandidatesResultList[0].enlistedPropriety",is(true)));
	}
	@Test
	public void test014() throws Exception {
		// モック対象へのリクエスト文字列を取得
		String body = RestTestBase2.readForObject("test003.json");
		
		
		// Controllerにリクエストを送り、結果を取得
		ResultActions result = mockMvc.perform(put(targetUrl)
				.contentType(MediaType.APPLICATION_JSON)
				.content(body));

		// 結果の検査。ステータスがOKであること、および文字列の文字数が6であることを確認します
		result.andExpect(status().is(HttpStatus.OK.value()))
		.andExpect(jsonPath("$.judgedCandidatesResultList[0].memberName",is("haruhi")))
		.andExpect(jsonPath("$.judgedCandidatesResultList[0].enlistedPropriety",is(true)));
	}
	@Test
	public void test015() throws Exception {
		// モック対象へのリクエスト文字列を取得
		String body = RestTestBase2.readForObject("test003.json");
		
		
		// Controllerにリクエストを送り、結果を取得
		ResultActions result = mockMvc.perform(put(targetUrl)
				.contentType(MediaType.ALL)
				.content(body));

		// 結果の検査。ステータスがOKであること、および文字列の文字数が6であることを確認します
		result.andExpect(status().is(HttpStatus.OK.value()));
	}
	
}
