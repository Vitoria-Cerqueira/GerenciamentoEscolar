package org.example.controller;

import org.example.model.CursoModel;
import org.example.repository.AlunoRepository;

public class AlunoController {
    AlunoRepository alunoRepository = new AlunoRepository();
    CursoModel cursoModel = new CursoModel();


    public boolean consultarAlunoMatriculado(String nomeCurso){
        cursoModel.setNomeCurso(nomeCurso);
        return alunoRepository.consultarAlunoMatriculado(cursoModel.getNomeCurso());
    }

    public boolean consultarAlunosSemMatricula(){
        return alunoRepository.consultarAlunosSemMatricula();
    }
    public boolean consultarAlunoMatriculadoMaisDeUmCurso(){
        return alunoRepository.consultarAlunoMatriculadoMaisDeUmCurso();
    }
}
