package com.efub.blogsession.domain.account.repository;

import com.efub.blogsession.domain.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
	Boolean existsByEmail(String email);

	//이메일로 계정 조회
	Optional<Account> findByEmail(String email);

}
