package br.com.orcafacil.server.domain;

import br.com.orcafacil.server.repository.PontuacaoRepository;
import br.gov.frameworkdemoiselle.util.Beans;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("C")
public class Cliente extends Entidade {

    @Transient
    private PontuacaoRepository pontuacaoRepository = Beans.getReference(PontuacaoRepository.class);
    
    public Cliente() {
    }
    
    public void pontuar(Fornecedor fornecedor, TipoPontuacao tipoPontuacao, String justificativa) {
        Pontuacao pontuacao = new Pontuacao(justificativa, tipoPontuacao, fornecedor, this);
        pontuacaoRepository.add(pontuacao);
    }
}