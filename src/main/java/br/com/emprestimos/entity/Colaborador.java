package br.com.emprestimos.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Entity public class Colaborador {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @NotBlank private String nome;
    @Email @NotBlank @Column(unique=true) private String email;
    @Pattern(regexp="\\d{8}") private String cep;
    @NotBlank private String numero;
    private String logradouro,bairro,cidade,uf;
    public Long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String v) {
        nome=v;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String v) {
        email=v;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String v) {
        cep=v;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String v) {
        numero=v;
    }
    public String getLogradouro() {
        return logradouro;
    }
    public void setLogradouro(String v) {
        logradouro=v;
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String v) {
        bairro=v;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String v) {
        cidade=v;
    }
    public String getUf() {
        return uf;
    }
    public void setUf(String v) {
        uf=v;
    }
}
