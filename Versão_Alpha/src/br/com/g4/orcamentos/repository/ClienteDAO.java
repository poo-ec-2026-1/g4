package br.com.g4.orcamentos.repository;

import br.com.g4.orcamentos.database.Database;
import br.com.g4.orcamentos.domain.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    public Cliente salvar(Cliente cliente) {
        String sql = "INSERT INTO clientes (nome, documento, telefone, email, endereco) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection c = Database.getConnection();
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getDocumento());
            ps.setString(3, cliente.getTelefone());
            ps.setString(4, cliente.getEmail());
            ps.setString(5, cliente.getEndereco());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                cliente.setId(rs.getInt(1));
            }
            rs.close();
            ps.close();
            c.close();
            return cliente;
        } catch (Exception ex) {
            throw new IllegalStateException("Nao foi possivel salvar cliente: " + ex.getMessage(), ex);
        }
    }

    public List<Cliente> listar() {
        List<Cliente> clientes = new ArrayList<Cliente>();
        try {
            Connection c = Database.getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM clientes ORDER BY nome");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                clientes.add(mapear(rs));
            }
            rs.close();
            ps.close();
            c.close();
            return clientes;
        } catch (Exception ex) {
            throw new IllegalStateException("Nao foi possivel listar clientes: " + ex.getMessage(), ex);
        }
    }

    public Cliente buscarPorId(int id) {
        try {
            Connection c = Database.getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM clientes WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Cliente cliente = rs.next() ? mapear(rs) : null;
            rs.close();
            ps.close();
            c.close();
            return cliente;
        } catch (Exception ex) {
            throw new IllegalStateException("Nao foi possivel buscar cliente: " + ex.getMessage(), ex);
        }
    }

    public boolean existeDocumento(String documento) {
        try {
            Connection c = Database.getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) FROM clientes WHERE documento = ?");
            ps.setString(1, Cliente.limparDocumento(documento));
            ResultSet rs = ps.executeQuery();
            boolean existe = rs.next() && rs.getInt(1) > 0;
            rs.close();
            ps.close();
            c.close();
            return existe;
        } catch (Exception ex) {
            throw new IllegalStateException("Nao foi possivel validar documento: " + ex.getMessage(), ex);
        }
    }

    public void excluir(int id) {
        try {
            Connection c = Database.getConnection();
            PreparedStatement ps = c.prepareStatement("DELETE FROM clientes WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            c.close();
        } catch (Exception ex) {
            throw new IllegalStateException("Nao foi possivel excluir cliente: " + ex.getMessage(), ex);
        }
    }

    private Cliente mapear(ResultSet rs) throws Exception {
        return new Cliente(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("documento"),
                rs.getString("telefone"),
                rs.getString("email"),
                rs.getString("endereco"));
    }
}
