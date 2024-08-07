package me.kimgiuk.springbootdeveloper;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)	// 기본 생성자를 protected로 설정
@AllArgsConstructor
@Getter
@Entity
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	public void changeName(String name) {
		this.name = name;
	}
	// @Transactional 메소드에서 호출되면 JPA 는 변경감지 기능을 통해 엔티티의 필드값이 변경될 때 그 변경사항을 DB에 자동으로 반영
	// 엔티티가 영속 상태일때 필드값 변경 후 트랜잭션 커밋 시 JPA는 변경사항을 데이터베이스에 자동으로 적용
}
