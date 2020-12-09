package br.zup.bootcamp.nossoCartao.Bloqueio;

import br.zup.bootcamp.nossoCartao.Cartao.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BloqueioRepository extends JpaRepository<Bloqueio, UUID> {

    Optional<Bloqueio> findByCartao(Cartao cartao);
}
