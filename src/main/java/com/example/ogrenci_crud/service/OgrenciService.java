package com.example.ogrenci_crud.service;

import com.example.ogrenci_crud.model.Ogrenci;

import java.util.List;
import java.util.Optional;

public interface OgrenciService {
    List<Ogrenci> getAllOgrenciler();
    Optional<Ogrenci> getOgrenciById(Long id);
    Ogrenci saveOgrenci(Ogrenci ogrenci);
   public Ogrenci updateOgrenci(Ogrenci ogrenci);
    void deleteOgrenciById(Long id);
}
