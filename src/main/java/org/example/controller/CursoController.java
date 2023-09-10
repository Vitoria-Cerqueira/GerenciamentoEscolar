package org.example.controller;

import org.example.model.ProfessorModel;
import org.example.repository.CursoRepository;

public class CursoController {
    CursoRepository cursoRepository = new CursoRepository();
    ProfessorModel professorModel = new ProfessorModel();

    public boolean consultarCursosPorUmProfessor(String nomeProfessor){
        professorModel.setNome(nomeProfessor);
        return cursoRepository.consultarCursosPorUmProfessor(professorModel.getNome());
    }
    public boolean consultarCursosSemAlunosMatriculados(){
        return cursoRepository.consultarCursosSemAlunosMatriculados();
    }
}
