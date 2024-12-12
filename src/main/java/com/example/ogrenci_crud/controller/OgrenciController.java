package com.example.ogrenci_crud.controller;

import com.example.ogrenci_crud.model.Ogrenci;
import com.example.ogrenci_crud.service.OgrenciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class OgrenciController {

    @Autowired
    private OgrenciService ogrenciService;

    // HTML - Ana sayfa yönlendirmesi (GET)
    @GetMapping("/")
    public String homePage() {
        return "index"; // index.html sayfasına yönlendirecek
    }

    // API - Tüm öğrencileri listeleme (GET)
    @GetMapping("/api/ogrenciler")
    public List<Ogrenci> getAllOgrenciler() {
        return ogrenciService.getAllOgrenciler();
    }

    // API - Öğrenci ID ile getirme (GET)
    @GetMapping("/api/ogrenciler/{id}")
    public ResponseEntity<Ogrenci> getOgrenciById(@PathVariable Long id) {
        Optional<Ogrenci> ogrenci = ogrenciService.getOgrenciById(id);
        return ogrenci.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // API - Yeni öğrenci ekleme (POST)
    @PostMapping("/api/ogrenciler")
    public ResponseEntity<Ogrenci> createOgrenci(@RequestBody Ogrenci ogrenci) {
        Ogrenci savedOgrenci = ogrenciService.saveOgrenci(ogrenci);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOgrenci);
    }

    // API - Öğrenci güncelleme (PUT)
    @PutMapping("/api/ogrenciler/{id}")
    public ResponseEntity<Ogrenci> updateOgrenci(@PathVariable Long id, @RequestBody Ogrenci ogrenci) {
        ogrenci.setId(id);  // setId'yi Long olarak çağırın
        Ogrenci updatedOgrenci = ogrenciService.updateOgrenci(ogrenci);
        return ResponseEntity.ok(updatedOgrenci);
    }

    @DeleteMapping("/api/ogrenciler/{id}")
    public ResponseEntity<Void> deleteOgrenci(@PathVariable Long id) {
        ogrenciService.deleteOgrenciById(id);  // deleteOgrenciById'yi Long olarak çağırın
        return ResponseEntity.noContent().build();
    }

    // HTML - Öğrencileri listeleme (GET)
    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("ogrenciler", ogrenciService.getAllOgrenciler());
        return "students"; // students.html sayfasına yönlendirecek
    }

    // HTML - Yeni öğrenci formu (GET)
    @GetMapping("/students/new")
    public String createStudentForm(Model model) {
        model.addAttribute("ogrenci", new Ogrenci());
        return "create_student"; // create_student.html sayfasına yönlendirecek
    }

    // HTML - Yeni öğrenci ekleme (POST)
    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("ogrenci") Ogrenci ogrenci) {
        ogrenciService.saveOgrenci(ogrenci);
        return "redirect:/students"; // öğrenciler listesine geri yönlendirecek
    }

    // HTML - Öğrenci düzenleme formunu gösterme (GET)
    @GetMapping("/students/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Ogrenci> ogrenci = ogrenciService.getOgrenciById(id);
        if (ogrenci.isPresent()) {
            model.addAttribute("ogrenci", ogrenci.get());
            return "edit_student"; // edit_student.html sayfasına yönlendirme
        } else {
            return "redirect:/students"; // Öğrenci bulunamazsa liste sayfasına yönlendirme
        }
    }

    // HTML - Öğrenci bilgilerini güncelleme (POST)
    @PostMapping("/students/{id}/update")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("ogrenci") Ogrenci ogrenci) {
        ogrenci.setId(id); // ID'yi ayarlayın
        ogrenciService.updateOgrenci(ogrenci); // Güncelleme işlemi
        return "redirect:/students"; // Öğrenci listesi sayfasına yönlendirme
    }

    // HTML - Öğrenci silme (GET)
    @GetMapping("/students/{id}/delete")
    public String deleteStudent(@PathVariable Long id) {
        ogrenciService.deleteOgrenciById(id);
        return "redirect:/students"; // öğrenciler listesine geri yönlendirecek
    }
}
