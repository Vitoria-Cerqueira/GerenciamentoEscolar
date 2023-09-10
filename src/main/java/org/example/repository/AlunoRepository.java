package org.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.connection.ConnectionDB.connection;

public class AlunoRepository {
    private Connection conn = connection();
    private PreparedStatement preparedStatement;
    public AlunoRepository(Connection conn){
        this.conn = conn;
    }
    public AlunoRepository(){}

    public boolean consultarAlunoMatriculado(String nomeCurso){
        try {
            String sql =  "SELECT Alunos.nome_luno FROM Alunos " +
                    "INNER JOIN Matricula ON Alunos.id_aluno=Matricula.aluno_id " +
                    "INNER JOIN Curso ON Matricula.curso_id=Curso.id_curso " +
                    "WHERE Curso.nome_curso = ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,nomeCurso);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Nome dos alunos matriculados " + nomeCurso);
            while (resultSet.next()){
                System.out.println(resultSet.getString("nome_luno"));
            }
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean consultarAlunosSemMatricula(){
        try{
            String sql = "SELECT Alunos.nome_luno FROM Matricula " +
                    "RIGHT JOIN Alunos " + "ON Alunos.id_aluno=Matricula.aluno_id " +
                    "WHERE Matricula.curso_id IS NULL;";
            preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Nome dos alunos não matriculados ");
            while (resultSet.next()){
                System.out.println(resultSet.getString("nome_luno"));
            }
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean consultarAlunoMatriculadoMaisDeUmCurso(){
        try {
            String sql = "SELECT Alunos.nome_luno FROM Alunos " +
                    "INNER JOIN Matricula M1 ON Alunos.id_aluno = M1.aluno_id " +
                    "INNER JOIN Matricula M2 ON Alunos.id_aluno = M2.aluno_id " +
                    "WHERE M1.curso_id <> M2.curso_id;";
            preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Nome dos alunos matriculados em mais de um curso ");
            while (resultSet.next()){
                System.out.println(resultSet.getString("nome_luno"));
            }
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
