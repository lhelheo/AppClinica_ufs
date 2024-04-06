package com.app.clinica.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.clinica.dto.EnfermeiroRecordDTO;
import com.app.clinica.models.EnfermeiroModel;
import com.app.clinica.models.UsuarioModel;
import com.app.clinica.repositories.EnfermeiroRepository;
import com.app.clinica.repositories.UsuarioRepository;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class EnfermeiroController {
    @Autowired
    EnfermeiroRepository enfermeiroRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/enfermeiros")
    public ResponseEntity<List<EnfermeiroModel>> buscarEnfermeiros() {
        return ResponseEntity.status(HttpStatus.OK).body(enfermeiroRepository.findAll());
    }

    @GetMapping("/enfermeiros/{id}")
    public ResponseEntity<Object> buscarEnfermeiroPorId(@PathVariable(value = "id") UUID id) {
        Optional<EnfermeiroModel> enfermeiro0 = enfermeiroRepository.findById(id);
        if (enfermeiro0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("enfermeiro não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(enfermeiro0.get());
    }

    @PostMapping("/enfermeiros")
    public ResponseEntity<EnfermeiroModel> saveEnfermeiro(@RequestBody @Valid EnfermeiroRecordDTO enfermeiroRecordDTO) {
        var enfermeiroModel = new EnfermeiroModel();
        BeanUtils.copyProperties(enfermeiroRecordDTO, enfermeiroModel);
        var usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(enfermeiroRecordDTO, usuarioModel);
        UsuarioModel user = usuarioRepository.save(usuarioModel);
        enfermeiroModel.setUsuario(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(enfermeiroRepository.save(enfermeiroModel));
    }

    @PutMapping("enfermeiro/{id}")
    public ResponseEntity<Object> atualizaEnfermeiro(@PathVariable(value = "id") UUID id,
            @RequestBody @Valid EnfermeiroRecordDTO enfermeiroRecordDTO) {
        Optional<EnfermeiroModel> enfermeiro = enfermeiroRepository.findById(id);
        if (enfermeiro.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Enfermeiro não encontrado!");
        }
        var enfermeiroModel = enfermeiro.get();

        BeanUtils.copyProperties(enfermeiroRecordDTO, enfermeiroModel, "id");
        UsuarioModel usuario0 = enfermeiroModel.getUsuario();
        BeanUtils.copyProperties(enfermeiroRecordDTO, usuario0, "cpf");
        usuarioRepository.save(usuario0);

        return ResponseEntity.status(HttpStatus.OK).body(enfermeiroRepository.save(enfermeiroModel));
    }

}
