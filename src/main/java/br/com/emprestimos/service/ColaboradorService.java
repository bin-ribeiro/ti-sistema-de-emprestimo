package br.com.emprestimos.service;
import br.com.emprestimos.dto.*;
import br.com.emprestimos.entity.*;
import br.com.emprestimos.exception.*;
import br.com.emprestimos.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.*;
@Service public class ColaboradorService {
    private final ColaboradorRepository repo;
    private final RestClient rest=RestClient.create("https://viacep.com.br");
    public ColaboradorService(ColaboradorRepository r) {
        repo=r;
    }
    public List<Colaborador> listar() {
        return repo.findAll();
    }
    public Colaborador buscar(Long id) {
        return repo.findById(id).orElseThrow(()->new ApiException(HttpStatus.NOT_FOUND,"Colaborador não encontrado"));
    }
    public Colaborador criar(Colaborador c) {
        if(repo.existsByEmail(c.getEmail()))throw new ApiException(HttpStatus.CONFLICT,"E-mail já cadastrado");
        ViaCepResponse v;
        try {
            v=rest.get().uri("/ws/{cep}/json/",c.getCep()).retrieve().body(ViaCepResponse.class);
        }
        catch(Exception e) {
            throw new ApiException(HttpStatus.BAD_REQUEST,"CEP não encontrado");
        }
        if(v==null||Boolean.TRUE.equals(v.erro()))throw new ApiException(HttpStatus.BAD_REQUEST,"CEP não encontrado");
        c.setLogradouro(v.logradouro());
        c.setBairro(v.bairro());
        c.setCidade(v.localidade());
        c.setUf(v.uf());
        return repo.save(c);
    }
}
