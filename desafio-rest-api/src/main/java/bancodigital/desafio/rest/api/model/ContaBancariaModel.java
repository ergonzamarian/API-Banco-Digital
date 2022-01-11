package bancodigital.desafio.rest.api.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.cfg.annotations.reflection.internal.XMLContext;
import org.hibernate.mapping.Value;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "contaBancaria")
public class ContaBancariaModel implements Serializable {
    private static final long serialVersionUTD=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Integer id;

    @Column(nullable = false, length = 50)
    public String nome;

    @Column(nullable = false, length = 14)
    public String cpf;

    @Column(nullable = false, length = 50)
    public final Float saldo = 0f;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Float getSaldo() {
        return saldo;
    }
}
