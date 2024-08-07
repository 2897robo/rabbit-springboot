package me.kimgiuk.springbootdeveloper;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
class MemberRespositoryTest {
	@Autowired
	MemberRespository memberRepository;

	@Sql("/insert-member.sql")
	@Test
	void getAllMembers() {
		// when
		List<Member> members = memberRepository.findAll();

		// then
		assertThat(members.size()).isEqualTo(3);
	}

	@Sql("/insert-member.sql")
	@Test
	void getMemberByName() {
		// when
		Member members = memberRepository.findByName("C").get();

		// then
		assertThat(members.getId()).isEqualTo(3);
	}

	@Test
	void saveMember() {
		// given
		Member member = new Member(1L, "A");

		// when
		memberRepository.save(member);

		// then
		assertThat(memberRepository.findById(1L).get().getName()).isEqualTo("A");
	}

	@Test
	void saveMembers() {
		List<Member> members = List.of(new Member(2L, "B"), new Member(3L, "C"));
		memberRepository.saveAll(members);
		assertThat(memberRepository.findAll().size()).isEqualTo(2);
	}

	@Sql("/insert-member.sql")
	@Test
	void deleteMemberById() {
		memberRepository.deleteById(2L);
		assertThat(memberRepository.findById(2L).isEmpty()).isTrue();
	}

	@AfterEach
	void cleanUp() {
		memberRepository.deleteAll();
	}

	@Sql("/insert-member.sql")
	@Test
	void update() {
		Member member = memberRepository.findById(2L).get();
		member.changeName("DB");
		assertThat(memberRepository.findById(2L).get().getName()).isEqualTo("DB");
	}
	// @DataJpaTest 에 @Transactional 이 포함되어 있어서 테스트 메소드 실행 후 롤백되어 테스트 데이터가 영향을 주지 않음
}