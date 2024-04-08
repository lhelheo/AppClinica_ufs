package br.com.javaexample.testejava.services;

import br.com.javaexample.testejava.models.Paciente;
import br.com.javaexample.testejava.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente cadastrarPaciente(Paciente paciente) {
        // Aqui pode-se adicionar lógica de negócios antes de salvar o paciente
        return pacienteRepository.save(paciente);
    }
}
