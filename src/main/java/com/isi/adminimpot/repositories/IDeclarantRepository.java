package com.isi.adminimpot.repositories;

import com.isi.adminimpot.entities.Declarant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDeclarantRepository extends JpaRepository<Declarant, Long> {
}
