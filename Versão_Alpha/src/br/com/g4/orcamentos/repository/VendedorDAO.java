package br.com.g4.orcamentos.repository;

import br.com.g4.orcamentos.database.Database;
import br.com.g4.orcamentos.domain.Vendedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VendedorDAO {
    public Vendedor salvar(Vendedor vendedor) {
        String sql = "INSERT INTO vendedores (nome, telefone, email, percentual_comissao) VALUES (?, ?, ?, ?)";
        try {
            Connection c = Database.getConnection();
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, vendedor.getNome());
            ps.setString(2, vendedor.getTelefone());
            ps.setString(3, vendedor.getEmail());
            ps.setDouble(4, vendedor.getPercentualComissao());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                vendedor.setId(rs.getInt(1));
            }
            rs.close();
            ps.close();
            c.close();
            return vendedor;
        } catch (Exception ex) {
            throw new IllegalStateException("Nao foi possivel salvar vendedor: " + ex.getMessage(), ex);
        }
    }

    public List<Vendedor> listar() {
        List<Vendedor> vendedores = new ArrayList<Vendedor>();
        try {
            Connection c = Database.getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM vendedores ORDER BY nome");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vendedores.add(mapear(rs));
            }
            rs.close();
            ps.close();
            c.close();
            return vendedores;
        } catch (Exception ex) {
            throw new IllegalStateException("Nao foi possivel listar vendedores: " + ex.getMessage(), ex);
        }
    }

    public Vendedor buscarPorId(int id) {
        try {
            Connection c = Database.getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM vendedores WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Vendedor vendedor = rs.next() ? mapear(rs) : null;
            rs.close();
            ps.close();
            c.close();
            return vendedor;
        } catch (Exception ex) {
            throw new IllegalStateException("Nao foi possivel buscar vendedor: " + ex.getMessage(), ex);
        }
    }

    public void excluir(int id) {
        try {
            Connection c = Database.getConnection();
            PreparedStatement ps = c.prepareStatement("DELETE FROM vendedores WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            c.close();
        } catch (Exception ex) {
            throw new IllegalStateException("Nao foi possivel excluir vendedor: " + ex.getMessage(), ex);
        }
    }

    private Vendedor mapear(ResultSet rs) throws Exception {
        return new Vendedor(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("telefone"),
                rs.getString("email"),
                rs.getDouble("percentual_comissao"));
    }
}
