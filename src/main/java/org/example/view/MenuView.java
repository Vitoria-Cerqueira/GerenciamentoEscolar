package org.example.view;

import org.example.controller.AlunoController;
import org.example.controller.CursoController;

import java.util.Scanner;

public class MenuView {
    AlunoController alunoController;
    CursoController cursoController;
    Scanner scanner;

    public MenuView() {
        alunoController = new AlunoController();
        cursoController = new CursoController();
        scanner = new Scanner(System.in);
    }

    public void menuPrincipal() {
        int resp = 0;
        do {
            System.out.println("\t\t\t\tVocê deseja: \n------------------------------------------------------");
            System.out.println("\t[1] Consultar alunos matriculados em um curso específico \n\t[2] Consultar cursos ministrados por um professor " + "\n\t[3] Consultar alunos que não estão matriculados em nenhum curso  \n\t[4] Consultar cursos sem alunos matriculados " + "\n\t[5] Consultar alunos matriculados em mais de um curso \n\t[0] SAIR");
            resp = scanner.nextInt();
            switch (resp) {
                case 1:
                    System.out.print("Informe o nome do curso: ");
                    scanner.nextLine();
                    String nome_curso = scanner.nextLine();
                    System.out.println("Curso: " + nome_curso);
                    System.out.println("Aluno(s) matriculado(s):");
                    alunoController.consultarAlunoMatriculado(nome_curso);
                    break;
                case 2:
                    System.out.print("Informe o nome do professor: ");
                    scanner.nextLine();
                    String nome_professor = scanner.nextLine();
                    System.out.println("Professor(a): " + nome_professor);
                    System.out.println("Curso(s):");
                    cursoController.consultarCursosPorUmProfessor(nome_professor);
                    break;
                case 3:
                    alunoController.consultarAlunosSemMatricula();
                    break;
                case 4:
                    cursoController.consultarCursosSemAlunosMatriculados();
                    break;
                case 5:
                    alunoController.consultarAlunoMatriculadoMaisDeUmCurso();
                    break;
                case 0:
                    System.out.println("Encerrando Sistema...");
                    System.exit(0);
                default:
                    System.err.println("Opção Inválida! Tente novamente... \n");
            }
        } while (resp != 0);
    }
}
