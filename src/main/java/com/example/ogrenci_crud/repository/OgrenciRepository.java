package com.example.ogrenci_crud.repository;

import com.example.ogrenci_crud.model.Ogrenci;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OgrenciRepository extends JpaRepository<Ogrenci, Long> {
    // JpaRepository, CRUD işlemleri için gerekli metodları sağlar
}
