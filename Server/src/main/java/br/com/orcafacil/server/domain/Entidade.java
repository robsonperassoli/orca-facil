package br.com.orcafacil.server.domain;

import br.com.orcafacil.server.util.UniqId;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="ENTIDADE")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="ENT_TIPO", discriminatorType=DiscriminatorType.STRING, length = 1)
@DiscriminatorValue("E")
@SequenceGenerator(name="entidade_gen", allocationSize=1)
public class Entidade implements Serializable {

    @Id
    @Column(name="ENT_ID")
    @GeneratedValue(generator="entidade_gen", strategy=GenerationType.SEQUENCE)
    private Long id;
    
    @Column(name="ENT_NOME")
    private String nome;
    
    @Column(name="ENT_EMAIL")
    private String email;
    
    @Column(name="ENT_FONE")
    private String fone;
    
    @Column(name="ENT_BAIRRO")
    private String bairro;
    
    @ManyToOne
    @JoinColumn(name="MUN_ID")
    private Municipio municipio;
    
    @Column(name="ENT_NUMERO")
    private String numero;
    
    @Column(name="ENT_RUA")
    private String rua;
    
    @Column(name="ENT_SENHA")
    private String senha;
    
    @Column(name="ENT_APIKEY")
    private String apiKey;
    
    @Column(name="ENT_CEP")
    private String cep;
    
    @Column(name="ENT_TIPO", insertable=false, updatable=false)
    private String tipo;

    public Entidade() {
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public Long getId() {
        return id;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getApiKey() {
        return apiKey;
    }
    
    public void generateApiKey(){
        apiKey = UniqId.getInstance().getUniqIDHashString();
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    
}