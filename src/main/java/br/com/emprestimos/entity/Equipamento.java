package br.com.emprestimos.entity;
import br.com.emprestimos.enums.TipoEquipamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Entity public class Equipamento {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @NotBlank @Column(unique=true,nullable=false) private String patrimonio;
    @NotBlank private String nome;
    @NotNull @Enumerated(EnumType.STRING) private TipoEquipamento tipo;
    private boolean disponivel=true;
    public Long getId() {
        return id;
    }
    public String getPatrimonio() {
        return patrimonio;
    }
    public void setPatrimonio(String v) {
        patrimonio=v;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String v) {
        nome=v;
    }
    public TipoEquipamento getTipo() {
        return tipo;
    }
    public void setTipo(TipoEquipamento v) {
        tipo=v;
    }
    public boolean isDisponivel() {
        return disponivel;
    }
    public void setDisponivel(boolean v) {
        disponivel=v;
    }
}
