package br.zup.bootcamp.nossoCartao.Cartao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, UUID> {
}
