package me.kimgiuk.springbootdeveloper;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRespository extends JpaRepository<Member, Long> {
	Optional<Member> findByName(String name);

	//@Query("SELECT m FROM Member m WHERE m.name = :name")
	// 어노테이션으로 복잡한 쿼리 사용 가능
}
