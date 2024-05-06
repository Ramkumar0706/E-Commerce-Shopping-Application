package com.retail.ecom.service.impl;

import java.net.CookieManager;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.retail.ecom.cache.CacheStore;
import com.retail.ecom.enums.UserRole;
import com.retail.ecom.exception.InvalidCreaditionalException;
import com.retail.ecom.exception.InvalidEmailException;
import com.retail.ecom.exception.InvalidUserRoleSpecfiedException;
import com.retail.ecom.exception.OTPExpiredException;
import com.retail.ecom.exception.OTPInvalidException;
import com.retail.ecom.exception.RegistrationSessionExpiredException;
import com.retail.ecom.exception.UserAlreadyExistsByEmailException;
import com.retail.ecom.jwt.JwtService;
import com.retail.ecom.mail_service.MailService;
import com.retail.ecom.model.AccessToken;
import com.retail.ecom.model.Customer;
import com.retail.ecom.model.RefreshToken;
import com.retail.ecom.model.Seller;
import com.retail.ecom.model.User;
import com.retail.ecom.repository.AccessTokenRepository;
import com.retail.ecom.repository.RefreshTokenRepository;
import com.retail.ecom.repository.UserRepostiory;
import com.retail.ecom.requestdto.AuthRequest;
import com.retail.ecom.requestdto.OTPRequest;
import com.retail.ecom.requestdto.UserRequest;
import com.retail.ecom.responsedto.AuthResponse;
import com.retail.ecom.responsedto.UserResponse;
import com.retail.ecom.service.UserService;
import com.retail.ecom.utility.MessageModel;
import com.retail.ecom.utility.ResponseStructure;
import com.retail.ecom.utility.SimpleResponseStructure;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;

@Service
public class UserServiceImpl  implements UserService {

	private CacheStore<String> otpCache;
	private CacheStore<User> userCache;
	private SimpleResponseStructure simpleResponseStructure;
	private UserRepostiory userRepository;
	private ResponseStructure<UserResponse> userResponseStructure;
	private MailService mailService;
	private AuthenticationManager  authenticationManager;
	private JwtService jwtService;
	@Value("${myapp.jwt.refresh.expiration}")
	private long refreshExpiration;
	@Value("${myapp.jwt.access.expiration}")
	private long accessExpiration;



	private RefreshTokenRepository refreshTokenRepo;
	private AccessTokenRepository accessTokenRepo;

	private PasswordEncoder pass;

	private ResponseStructure<AuthResponse> authResponse;
	private CookieManager cookieManager;



	//learn the simple message yourself





	@Override
	public ResponseEntity<SimpleResponseStructure> UserRegisteration(UserRequest userRequest) {
		if(userRepository.existsByEmail(userRequest.getEmail()))
			throw new UserAlreadyExistsByEmailException("The User Email is Already Present");

		User user=mapToChildEntity(userRequest);
		String otp=generateOTP();
		otpCache.add(user.getEmail(), otp);
		userCache.add(user.getEmail(), user);


		try {
			sendMail(user,otp);
		} catch (MessagingException e) {
			throw new InvalidEmailException(" the email is not valid");
		}


		return ResponseEntity.ok(simpleResponseStructure.setStatusCode(HttpStatus.ACCEPTED.value())
				.setMessage("Verify OTP sent through mail to complete the registration"
				+" "+ otp+" OTP expire in 2 minutes"));



	}







	public UserServiceImpl(CacheStore<String> otpCache, CacheStore<User> userCache,
			SimpleResponseStructure simpleResponseStructure, UserRepostiory userRepository,
			ResponseStructure<UserResponse> userResponseStructure, MailService mailService,
			AuthenticationManager authenticationManager, JwtService jwtService, RefreshTokenRepository refreshTokenRepo,
			AccessTokenRepository accessTokenRepo, PasswordEncoder pass, ResponseStructure<AuthResponse> authResponse) {
		super();
		this.otpCache = otpCache;
		this.userCache = userCache;
		this.simpleResponseStructure = simpleResponseStructure;
		this.userRepository = userRepository;
		this.userResponseStructure = userResponseStructure;
		this.mailService = mailService;
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
		this.refreshTokenRepo = refreshTokenRepo;
		this.accessTokenRepo = accessTokenRepo;
		this.pass = pass;
		this.authResponse = authResponse;
	}







	private void sendMail(User user, String otp) throws MessagingException {
		MessageModel model=MessageModel.builder().to(user.getEmail()).subject("Verify your OTP").text("<p> Hi,<br>"
				+"Thanks for your intrest to E-Com,"
				+"please verify your mail id using OTP given below.</p><br>"
				+"<hi>"+otp+"</h1>"
				+"<br>"
				+"<p> Please ignore if its not you</p>"
				+"<br>"
				+"with the best regards"
				+"<h3>thanks,you E-com</h3>"

				).build();

		mailService.sendEmailMessage(model);
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> verifiedOTP(OTPRequest otpRequest) {

		if(otpCache.get(otpRequest.getEmail())==null) throw new OTPExpiredException("the otp time is expired");
		if(!otpCache.get(otpRequest.getEmail()).equals(otpRequest.getOtp())) throw new OTPInvalidException("OTP is Invalided");

		User user=userCache.get(otpRequest.getEmail());
		System.out.println(user.getPassword());
		user.setPassword(pass.encode(user.getPassword()));
		if(user==null) throw new RegistrationSessionExpiredException("the user time out to registered");
		user.setEmailVerified(true);
		System.out.println(otpRequest.getEmail());
		System.out.println(user.getPassword());
		userRepository.save(user);
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(userResponseStructure.setStatuscode(HttpStatus.CREATED.
				value()) .setData(mapToUserResponse(user))
				.setMessage("User Object is created Successfully"));
	}

	private String generateOTP() {
		return String.valueOf(new Random().nextInt(100000,999999));
	}	

	@Override
	public ResponseEntity<ResponseStructure<AuthResponse>> userLogin(AuthRequest authRequest) {
		System.out.println(authRequest.getUsername());
		String username = authRequest.getUsername().split("@gmail.com")[0];
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, authRequest.getPassword()));
		if(!authentication.isAuthenticated())throw new 	InvalidCreaditionalException("user is not authenticated");

		SecurityContextHolder.getContext().setAuthentication(authentication);

		HttpHeaders headers=new HttpHeaders();
		return userRepository.findByUsername(username).map(user->{
			generateAccessToken(user,headers);
			generateRefresfToken(user,headers);
			return ResponseEntity.ok().headers(headers).body(new ResponseStructure<AuthResponse>().setStatuscode(HttpStatus.CREATED.value())
					.setData(mapToAuthResponse(user)).setMessage("user login successfully"));
		}).get();
	}
	
	@Override
	public ResponseEntity<SimpleResponseStructure> userLogout(String refreshToken, String accessToken) {
		if(refreshToken==null&&accessToken==null)throw new UsernameNotFoundException("user is not login");
		HttpHeaders header=new HttpHeaders();
		header.add(HttpHeaders.SET_COOKIE, validateCookie("at"));
		header.add(HttpHeaders.SET_COOKIE, validateCookie("rt"));
		blockAccessToken(accessToken);
		blockRefreshToken(refreshToken);

		return ResponseEntity.ok().headers(header).body(simpleResponseStructure.setStatusCode(HttpStatus.OK.value()).setMessage("user Logout successfull"));
	}
	
	public ResponseEntity<ResponseStructure<AuthResponse>> refreshLogin(String accesToken, String refreshToken) {
		System.out.println(refreshToken);
		if(refreshToken==null) {
			throw new IllegalArgumentException("User is not Logged In");
		}
		if(accesToken!=null)
		{
			accessTokenRepo.findByToken(refreshToken).ifPresent(token->
			{
				token.setBlocked(true);
				accessTokenRepo.save(token);
			});
		}
		Date date=jwtService.getIssueDate(refreshToken);
		String username=jwtService.getUsername(refreshToken);
		System.out.println(username+"        rt");
		System.out.println("USERNAME : "+username);
		HttpHeaders headers=new HttpHeaders();

		return userRepository.findByUsername(username).map(user->{
			if(date.before(new Date()))
				generateAccessToken(user, headers);
			else 
				headers.add(HttpHeaders.SET_COOKIE,configureCookie("rt", refreshToken,refreshExpiration));
			generateAccessToken(user, headers);
			return ResponseEntity.ok().headers(headers).body(new ResponseStructure<AuthResponse>()
					.setStatuscode(HttpStatus.OK.value()).setMessage("Token is Refresh seccusfully")
					.setData(mapToAuthResponse(user))
					);
		}).get();
	}
	private void blockRefreshToken(String refreshToken) {
		refreshTokenRepo.findByToken(refreshToken).ifPresent((rt)->{
			rt.setBlocked(true);
			refreshTokenRepo.save(rt);
		}
				);
	}

	AuthResponse mapToAuthResponse(User user) {

		return AuthResponse.builder()
				.username(user.getUsername())
				.userId(user.getUserId())
				.isAuthenticated(true)
				.accessExpiration(accessExpiration)
				.refreshExpiration(refreshExpiration)
				.role(user.getUserRole()).build();
	}
	private void generateRefresfToken(User user, HttpHeaders headers) {
		String token = jwtService.generateRefreshToken(user.getUsername(),user.getUserRole().name());
		headers.add(HttpHeaders.SET_COOKIE,configureCookie("rt",token,refreshExpiration));
		RefreshToken refreshToken=new RefreshToken();
		refreshToken.setToken(token);
		refreshToken.setBlocked(false);
		
		refreshToken.setExpiration(mapToLocalDateAndTime(refreshExpiration));
		refreshToken.setUser(user);
		refreshTokenRepo.save(refreshToken);
	}
	private void generateAccessToken(User user, HttpHeaders headers) {
		String token = jwtService.generateAccessToken(user.getUsername(),user.getUserRole().name());
		headers.add(HttpHeaders.SET_COOKIE,configureCookie("at",token,accessExpiration));

		AccessToken accessToken=new AccessToken();
		accessToken.setToken(token);
		accessToken.setBlocked(false);
		
		
		accessToken.setExpiration(mapToLocalDateAndTime(accessExpiration));
		System.out.println(accessExpiration);
		accessToken.setUser(user);
		accessTokenRepo.save(accessToken);
	}

	private String configureCookie(String name, String value, long maxAge) {
		return ResponseCookie.from(name,value)
				.domain("localhost")
				.path("/")
				.httpOnly(true)
				.secure(false)
				.maxAge(Duration.ofMillis(maxAge))
				.sameSite("Lax")
				.build().toString();

	}

	public <T extends User>T  mapToChildEntity(UserRequest userRequest) {
		UserRole role=userRequest.getUserRole();
		User user=null;
		switch(role) {
		case SELLER->user=new Seller();
		case CUSTOMER->user=new Customer();
		default -> throw new InvalidUserRoleSpecfiedException("UserRole is not valid");
		}
		user.setDisplayName(userRequest.getName());
		user.setPassword(userRequest.getPassword());
		user.setEmail(userRequest.getEmail());
		user.setUsername(userRequest.getEmail().split("@gmail.com")[0]);
		user.setEmailVerified(true);
		user.setUserRole(userRequest.getUserRole());

		return (T)user;
	}

	public UserResponse mapToUserResponse(User user) {

		return UserResponse.builder().displayName(user.getDisplayName())
				.userId(user.getUserId())
				.username(user.getUsername())
				.email(user.getEmail())
				.userRole(user.getUserRole())
				.isEmailVerified(user.isEmailVerified())
				.isDeleted(user.isDeleted())
				.build();
	}


	private void blockAccessToken(String accessToken) {
		accessTokenRepo.findByToken(accessToken).ifPresent((at)->{
			at.setBlocked(true);
			accessTokenRepo.save(at);
			
		});
	}


	public String validateCookie(String name) {
		return ResponseCookie.from(name,"")
				.domain("localhost")
				.path("/")
				.httpOnly(true)
				.secure(false)
				.maxAge(0)
				.sameSite("Lax")
				.build().toString();
	}
	private LocalDateTime mapToLocalDateAndTime(long millisecond) {
		//Instant instant=Instant.ofEpochMilli(millisecond);
	//	LocalDateTime localDateTime=LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		LocalDateTime l=LocalDateTime.now();
		Instant currentDate =l.atZone(ZoneId.systemDefault()).toInstant();
		Instant plusMillis = currentDate.plusMillis(millisecond);
		LocalDateTime localDateTime =LocalDateTime.ofInstant(plusMillis, ZoneId.systemDefault());
		return  localDateTime;
		
	}







	



}
