package com.retail.ecom.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.ecom.model.RefreshToken;
import com.retail.ecom.model.User;

public interface RefreshTokenRepository  extends JpaRepository<RefreshToken, Integer>{
	Optional<RefreshToken>findByToken(String refreshToken);

	boolean existsByTokenAndIsBlocked(String rt, boolean b);

	List<RefreshToken> findAllByExpirationBefore(LocalDateTime now);
}
