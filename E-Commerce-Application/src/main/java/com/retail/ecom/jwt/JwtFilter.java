package com.retail.ecom.jwt;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.retail.ecom.exception.UserTokenBlockedException;
import com.retail.ecom.model.AccessToken;
import com.retail.ecom.repository.AccessTokenRepository;
import com.retail.ecom.repository.RefreshTokenRepository;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
	private AccessTokenRepository accessTokenRepository;
	private RefreshTokenRepository refreshTokenRepository;
	private JwtService jwtService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String at=null;
		String rt=null;
		Cookie[] cookies = request.getCookies();
		if(cookies!=null) {
		for(Cookie c:request.getCookies()) {
			if(c.getName().equals("at")) at=c.getValue();
			if(c.getName().equals("rt")) rt=c.getValue();
		}
		}
		if(at!=null &&rt!=null) {

			if(!accessTokenRepository.existsByTokenAndIsBlocked(at,true)&& refreshTokenRepository.existsByTokenAndIsBlocked(rt,false)){
				
				try {
					
				String username = jwtService.getUsername(at);
				String userRole=jwtService.getUserRole(at);
				
				if(username!=null&& userRole!=null&&SecurityContextHolder.getContext().getAuthentication()==null) {
					
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				              username, null,Collections.singleton(new SimpleGrantedAuthority(userRole)));
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}}
				
				catch (JwtException e) {
					System.out.println("exp");			
					}
			}
		
			else
				throw new UserTokenBlockedException("User token is blocked state");
		}
		filterChain.doFilter(request, response);
	}

}
