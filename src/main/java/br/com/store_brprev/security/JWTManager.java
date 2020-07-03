package br.com.store_brprev.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.store_brprev.domain.dto.UserDTO;
import br.com.store_brprev.domain.exceptions.InvalidTokenException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTManager implements IJWTService {
	
	@Value("${key_screte:refnr437843854344043t5}")
	private String keySecret;
	
	@Value("${time_duration:800}")
	private Long timeDurationToken;
	
	private final String CLAIM_PERMISSION = "ROLE";

	@Override
	public String createToke(UserDTO user) {
		Date now = new Date();
		Date tokenExpirationDateTime = new Date(now.getTime() + timeDurationToken);
		return Jwts.builder()
					.setSubject(user.getLogin())
					.setIssuedAt(now)
					.setExpiration(tokenExpirationDateTime)
					.claim(CLAIM_PERMISSION, user.getPermission())
					.signWith(SignatureAlgorithm.HS256, keySecret)
					.compact();
	}

	@Override
	public String getLoginFromToken(String token) {		
		return Jwts.parser()
					.setSigningKey(keySecret)
					.parseClaimsJws(token)
					.getBody()
					.getSubject();
	}

	@Override
	public boolean validToken(String token) {
		try {
			Jwts.parser()
				.setSigningKey(keySecret)
				.parseClaimsJws(token);
			return true;
		} catch (ExpiredJwtException ex) {
			throw new InvalidTokenException("Your is expired");
		} catch(Exception ex) {
			throw new InvalidTokenException("Your is broken");
		}
	}
	
}
