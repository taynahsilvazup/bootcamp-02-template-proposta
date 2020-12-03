package br.zup.bootcamp.nossoCartao.Proposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    Proposta findByDocumento(String documento);
}
