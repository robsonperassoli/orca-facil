package br.com.orcafacil.server.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@DiscriminatorValue("F")
public class Fornecedor extends Entidade {

    @OneToMany(mappedBy="fornecedor")
    private List<PrecoProduto> precosProduto = new ArrayList<PrecoProduto>();

    public Fornecedor() {
    }
    
}