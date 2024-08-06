package me.kimgiuk.springbootdeveloper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest	// @SpringBootApplication 이 있는 클래스를 찾고 그 클래스에 포함되어 있는 빈을 찾은 다음 테스트용 애플리케이션 컨텍스트 생성
@AutoConfigureMockMvc // 애플리케이션을 서버에 배포하지 않고도 테스트용 MVC 환경 생성. 요청 및 전송, 응답 기능 제공
class TestControllerTest {
	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private MemberRespository memberRepository;

	@BeforeEach
	public void mockMvcSetUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@AfterEach
	public void cleanUp() {
		memberRepository.deleteAll();
	}

	@DisplayName("getAllMembers : 아티클 조회에 성공")
	@Test
	public void getAllMembers() throws Exception {
		// given
		final String url = "/test";
		Member savedMember = memberRepository.save(new Member(1L, "홍길동"));

		// when
		final ResultActions result = mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON));

		// then
		result
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].id").value(savedMember.getId()))
			.andExpect(jsonPath("$[0].name").value(savedMember.getName()));
	}
}