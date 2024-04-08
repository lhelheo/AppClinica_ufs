package br.com.javaexample.testejava.repositories;

import br.com.javaexample.testejava.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, String> {

}
