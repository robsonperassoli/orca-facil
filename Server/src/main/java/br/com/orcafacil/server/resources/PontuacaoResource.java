package br.com.orcafacil.server.resources;

import br.com.orcafacil.integration.api.IPontuacaoResource;
import br.com.orcafacil.integration.api.to.Pontuacao;
import br.com.orcafacil.server.domain.Cliente;
import br.com.orcafacil.server.domain.Fornecedor;
import br.com.orcafacil.server.domain.TipoPontuacao;
import br.com.orcafacil.server.repository.ClienteRepository;
import br.com.orcafacil.server.repository.FornecedorRepository;
import br.gov.frameworkdemoiselle.util.Beans;

public class PontuacaoResource implements IPontuacaoResource {
    
    private ClienteRepository clienteRepository = Beans.getReference(ClienteRepository.class);
    private FornecedorRepository fornecedorRepository = Beans.getReference(FornecedorRepository.class);

    @Override
    public void pontuar(String apiKey, Pontuacao pontuacao) {
        Cliente cliente = clienteRepository.getByApiKey(apiKey);
        Fornecedor fornecedor = fornecedorRepository.get(pontuacao.getFornecedor().getId()); 
        cliente.pontuar(fornecedor, getTipoPontuacao(pontuacao.getTipoPontuacao()), pontuacao.getJustificativa());
    }

    private TipoPontuacao getTipoPontuacao(Integer tipoPontuacao) {
        switch(tipoPontuacao){
            case 0:
                return TipoPontuacao.positiva;
            case 1:
                return TipoPontuacao.negativa;
        }
        return null;
    }
    
    
    
}
