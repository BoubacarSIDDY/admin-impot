package com.isi.adminimpot.repositories;

import com.isi.adminimpot.entities.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IPaiementRepository extends JpaRepository<Paiement, Long> {
    @Query("SELECT SUM(p.montantPaiement) FROM Paiement p WHERE p.declaration.id = :declarationId")
    Double getTotalPaiementsForDeclaration(@Param("declarationId") Long declarationId);
}
