package com.retail.ecom.utility;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.retail.ecom.model.AccessToken;
import com.retail.ecom.model.RefreshToken;
import com.retail.ecom.repository.AccessTokenRepository;
import com.retail.ecom.repository.RefreshTokenRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ScheduledJobs {
	private RefreshTokenRepository refreshTokenRepository;
	private AccessTokenRepository accessTokenRepository;
	
	
	 @Scheduled(fixedDelay = 60 * 60 * 1000l)
	    public void deleteAllExpiredAccessTokens() {
	        List<AccessToken> ats = accessTokenRepository.findAllByExpirationBefore(LocalDateTime.now());
	        if(ats!=null)
	            if(!ats.isEmpty()) accessTokenRepository.deleteAll(ats);
	    }

	    @Scheduled(fixedDelay = 60 * 60 * 1000)
	    public void deleteAllExpiredRefreshTokens() {
	        List<RefreshToken> rts =  refreshTokenRepository.findAllByExpirationBefore(LocalDateTime.now());
	        if(rts!=null)
	            if(!rts.isEmpty()) refreshTokenRepository.deleteAll(rts);
	    }

}
