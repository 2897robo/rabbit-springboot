package me.kimgiuk.springbootdeveloper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
	@Autowired
	MemberRespository memberRespository;

	public List<Member> getAllMembers() {
		return memberRespository.findAll();
	}
}
