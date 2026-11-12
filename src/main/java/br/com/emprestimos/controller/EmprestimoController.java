package br.com.emprestimos.controller;
import br.com.emprestimos.dto.*;
import br.com.emprestimos.entity.*;
import br.com.emprestimos.service.*;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/emprestimos") public class EmprestimoController {
    private final EmprestimoService s;
    public EmprestimoController(EmprestimoService s) {
        this.s=s;
    }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)public Emprestimo criar(@Valid @RequestBody EmprestimoRequest r) {
        return s.criar(r);
    }
    @PutMapping("/{id}/devolucao")public Emprestimo devolver(@PathVariable Long id) {
        return s.devolver(id);
    }
    @GetMapping public List<Emprestimo> listar(@RequestParam(required=false)Boolean atrasados) {
        return s.listar(atrasados);
    }
}
