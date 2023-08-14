package com.isi.adminimpot.dao;

import com.isi.adminimpot.entities.Declarant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeclarantDao extends JpaRepository<Declarant, Long> {
}
