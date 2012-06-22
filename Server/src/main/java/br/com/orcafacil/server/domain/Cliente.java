package br.com.orcafacil.server.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("C")
public class Cliente extends Entidade {

    public Cliente() {
    }
}