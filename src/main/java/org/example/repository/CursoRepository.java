package org.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.connection.ConnectionDB.connection;

public class CursoRepository {
    private Connection conn = connection();
    private PreparedStatement preparedStatement;

    public CursoRepository(){}
    public CursoRepository(Connection conn) {
        this.conn = conn;
    }

    public boolean consultarCursosPorUmProfessor(String nomeProfessor){
        try {
            String sql = "SELECT Curso.nome_curso FROM Curso " +
                    "LEFT JOIN Professor " + "ON Professor.id_professor=Curso.id_curso " +
                    "WHERE Professor.nome_professor = ? ";

            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,nomeProfessor);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Nomes dos cursos ministrados por " + nomeProfessor);
            while (resultSet.next()){
                System.out.println(resultSet.getString("nome_curso"));
            }
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }
    public boolean consultarCursosSemAlunosMatriculados(){
        try{
            String sql = "SELECT Curso.nome_curso FROM Curso " +
                    "Full JOIN Matricula " + "ON Curso.id_curso=Matricula.id_matricula " +
                    "WHERE Matricula.aluno_id IS NULL ";
            preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Nomes dos cursos sem alunos matriculados ");
            while (resultSet.next()){
                System.out.println(resultSet.getString("nome_curso"));
            }
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
