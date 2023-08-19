package com.isi.adminimpot.repositories;

import com.isi.adminimpot.entities.Declaration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDeclarationRepository extends JpaRepository<Declaration, Long> {
}
