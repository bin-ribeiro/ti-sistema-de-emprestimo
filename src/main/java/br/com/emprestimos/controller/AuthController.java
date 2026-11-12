package br.com.emprestimos.controller;
import br.com.emprestimos.dto.*;
import br.com.emprestimos.exception.ApiException;
import br.com.emprestimos.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController @RequestMapping("/auth") public class AuthController {
    private final JwtService jwt;
    public AuthController(JwtService j) {
        jwt=j;
    }
    @PostMapping("/login")public Map<String,String> login(@RequestBody LoginRequest r) {
        String role;
        if("admin".equals(r.usuario())&&"admin123".equals(r.senha()))role="ADMIN";
        else if("colaborador".equals(r.usuario())&&"colab123".equals(r.senha()))role="COLABORADOR";
        else throw new ApiException(HttpStatus.UNAUTHORIZED,"Usuário ou senha inválidos");
        return Map.of("token",jwt.gerar(r.usuario(),role),"perfil",role);
    }
}
