package com.retail.ecom.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.ecom.model.AccessToken;

public interface AccessTokenRepository  extends JpaRepository<AccessToken, Integer>{

	Optional<AccessToken> findByToken(String accessToken);

	boolean existsByTokenAndIsBlocked(String at, boolean b);

	List<AccessToken> findAllByExpirationBefore(LocalDateTime now);

}
