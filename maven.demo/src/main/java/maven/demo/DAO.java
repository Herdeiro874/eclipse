package maven.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    private Connection conexao;

    public DAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void inserir(x x) throws SQLException {
        String sql = "INSERT INTO x (id, nome) VALUES (?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, x.getId());
            stmt.setString(2, x.getNome());
            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM x WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void atualizar(x x) throws SQLException {
        String sql = "UPDATE x SET nome = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, x.getNome());
            stmt.setInt(2, x.getId());
            stmt.executeUpdate();
        }
    }

    public List<x> listar() throws SQLException {
        List<x> listaX = new ArrayList<>();
        String sql = "SELECT id, nome FROM x";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                x x = new x(id, nome);
                listaX.add(x);
            }
        }
        return listaX;
    }
}