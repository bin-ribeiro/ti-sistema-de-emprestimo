package br.com.emprestimos.security;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;
@Service public class JwtService {
    private final SecretKey key=Keys.hmacShaKeyFor("chave-secreta-muito-segura-com-32-caracteres-emprestimos".getBytes(StandardCharsets.UTF_8));
    public String gerar(String usuario,String role) {
        return Jwts.builder().subject(usuario).claim("role",role).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis()+8*60*60*1000)).signWith(key).compact();
    }
    public Claims ler(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }
}
