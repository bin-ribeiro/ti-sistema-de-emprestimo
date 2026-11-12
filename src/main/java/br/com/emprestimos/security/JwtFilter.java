package br.com.emprestimos.security;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;
@Component public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwt;
    public JwtFilter(JwtService j) {
        jwt=j;
    }
    protected void doFilterInternal(HttpServletRequest req,HttpServletResponse res,FilterChain chain)throws ServletException,IOException {
        String h=req.getHeader("Authorization");
        if(h!=null&&h.startsWith("Bearer "))try {
            Claims c=jwt.ler(h.substring(7));
            String role=c.get("role",String.class);
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(c.getSubject(),null,List.of(new SimpleGrantedAuthority("ROLE_"+role))));
        }
        catch(Exception ignored) {
        }
        chain.doFilter(req,res);
    }
}
