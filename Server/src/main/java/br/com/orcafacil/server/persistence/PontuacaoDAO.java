package br.com.orcafacil.server.persistence;

import br.com.orcafacil.server.domain.Pontuacao;
import br.com.orcafacil.server.repository.PontuacaoRepository;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;

public class PontuacaoDAO extends JPACrud<Pontuacao, Integer> implements PontuacaoRepository {

    @Transactional
    @Override
    public void add(Pontuacao pontuacao) {
        insert(pontuacao);
    }

}
