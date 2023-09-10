import org.example.repository.AlunoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AlunoRepositoryTest {
    private AlunoRepository alunoRepository;
    private Connection connect = mock(Connection.class);
    private PreparedStatement statement = mock(PreparedStatement.class);
    private ResultSet result = mock(ResultSet.class);

    @BeforeEach
    void setUp() {
        try {
            when(connect.prepareStatement(anyString())).thenReturn(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        alunoRepository = new AlunoRepository(connect);
    }

    @Test
    void testConsultaDeAlunosMatriculadosEmUmCursoEspecifico() throws SQLException {
        when(statement.executeQuery()).thenReturn(result);
        when(result.next()).thenReturn(true).thenReturn(false);
        when(result.getString("nome_luno")).thenReturn("Vitoria Cerqueira");
        Assertions.assertTrue(alunoRepository.consultarAlunoMatriculado("Curso Kotlin"));
    }

    @Test
    void testConsultaDeAlunosQueNaoEstaoMatriculadosEmNenhumCurso() throws SQLException {
        when(statement.executeQuery()).thenReturn(result);
        when(result.next()).thenReturn(true).thenReturn(false);
        when(result.getString("nome_luno")).thenReturn("Milena Alcantara");
        Assertions.assertTrue(alunoRepository.consultarAlunosSemMatricula());
    }

    @Test
    void testConsultaDeAlunosMatriculadosEmMaisDeUmCurso() throws SQLException {
        when(statement.executeQuery()).thenReturn(result);
        when(result.next()).thenReturn(true).thenReturn(false);
        when(result.getString("nome_luno")).thenReturn("Vitoria Cerqueira");
        Assertions.assertTrue(alunoRepository.consultarAlunoMatriculadoMaisDeUmCurso());
    }
}

