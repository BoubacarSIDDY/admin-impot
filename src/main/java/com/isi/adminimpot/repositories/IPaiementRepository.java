package com.isi.adminimpot.repositories;

import com.isi.adminimpot.entities.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaiementRepository extends JpaRepository<Paiement, Long> {
}
