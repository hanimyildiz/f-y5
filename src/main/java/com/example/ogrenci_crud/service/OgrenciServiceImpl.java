package com.example.ogrenci_crud.service;

import com.example.ogrenci_crud.model.Ogrenci;
import com.example.ogrenci_crud.repository.OgrenciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OgrenciServiceImpl implements OgrenciService {

    private final OgrenciRepository ogrenciRepository;

    @Autowired
    public OgrenciServiceImpl(OgrenciRepository ogrenciRepository) {
        this.ogrenciRepository = ogrenciRepository;
    }

    @Override
    public List<Ogrenci> getAllOgrenciler() {
        return ogrenciRepository.findAll();
    }

    @Override
    public Optional<Ogrenci> getOgrenciById(Long id) {
        return ogrenciRepository.findById(id);
    }

    @Override
    public Ogrenci saveOgrenci(Ogrenci ogrenci) {
        return ogrenciRepository.save(ogrenci);
    }

    @Override
    public Ogrenci updateOgrenci(Ogrenci ogrenci) {
        return ogrenciRepository.save(ogrenci);
    }

    @Override
    public void deleteOgrenciById(Long id) {
        ogrenciRepository.deleteById(id);
    }
}
