package br.zup.bootcamp.nossoCartao.Carteira;

import br.zup.bootcamp.nossoCartao.Cartao.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, UUID> {

    Optional<Carteira> findByCarteiraAndCartao(String carteira, Cartao cartao);
}
