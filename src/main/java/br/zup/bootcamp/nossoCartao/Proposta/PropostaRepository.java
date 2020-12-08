package br.zup.bootcamp.nossoCartao.Proposta;

import br.zup.bootcamp.nossoCartao.Proposta.Enum.StatusPropostaEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, UUID> {

    Proposta findByDocumento(String documento);

    List<Proposta> findByStatusAndCartaoIsNull(StatusPropostaEnum status);
}
