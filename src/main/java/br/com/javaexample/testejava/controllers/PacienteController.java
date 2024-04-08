package br.com.javaexample.testejava.controllers;

import br.com.javaexample.testejava.models.Paciente;
import br.com.javaexample.testejava.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<Paciente> cadastrarPaciente(@RequestBody Paciente paciente) {
        Paciente novoPaciente = pacienteService.cadastrarPaciente(paciente);
        return new ResponseEntity<>(novoPaciente, HttpStatus.CREATED);
    }
}
