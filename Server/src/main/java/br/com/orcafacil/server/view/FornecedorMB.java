package br.com.orcafacil.server.view;

import br.com.orcafacil.server.domain.Fornecedor;
import br.com.orcafacil.server.repository.FornecedorRepository;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import java.util.List;
import javax.inject.Inject;

@ViewController
public class FornecedorMB {
    
    @Inject
    private FornecedorRepository fornecedorRepository;
    
    private Fornecedor fornecedor;

    public Fornecedor getFornecedor() {
        if(fornecedor == null)
            fornecedor = new Fornecedor();
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<Fornecedor> getList(){
        return fornecedorRepository.getAll();
    }
    
    @Transactional
    public String salvar(){
        fornecedorRepository.add(fornecedor);
        return "fornecedor_list.jsf?faces-redirect=true";
    }
}
