package br.com.g4.orcamentos.repository;

import br.com.g4.orcamentos.database.Database;
import br.com.g4.orcamentos.domain.Cortina;
import br.com.g4.orcamentos.domain.Produto;
import br.com.g4.orcamentos.domain.Toldo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public Produto salvar(Produto produto) {
        String sql = "INSERT INTO produtos (categoria, largura, altura, material, tipo, cor, tecido, preco_m2) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
             
            ps.setString(1, produto.getCategoria());
            ps.setDouble(2, produto.getLargura());
            ps.setDouble(3, produto.getAltura());
            ps.setString(4, produto.getMaterial());
            ps.setString(5, produto.getTipo());
            ps.setString(6, produto.getCor());
            ps.setString(7, produto.getTecido());
            ps.setDouble(8, produto.getPrecoM2());
            ps.executeUpdate();
            
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    produto.setId(rs.getInt(1));
                }
            }
            
            return produto;
            
        } catch (Exception ex) {
            throw new IllegalStateException("Nao foi possivel salvar produto: " + ex.getMessage(), ex);
        }
    }

    public List<Produto> listar() {
        List<Produto> produtos = new ArrayList<Produto>();
        
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM produtos ORDER BY id DESC");
             ResultSet rs = ps.executeQuery()) {
             
            while (rs.next()) {
                produtos.add(mapear(rs));
            }
            
        } catch (Exception ex) {
            throw new IllegalStateException("Nao foi possivel listar produtos: " + ex.getMessage(), ex);
        }
        
        return produtos;
    }

    public Produto buscarPorId(int id) {
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM produtos WHERE id = ?")) {
             
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
            
        } catch (Exception ex) {
            throw new IllegalStateException("Nao foi possivel buscar produto: " + ex.getMessage(), ex);
        }
    }

    public void excluir(int id) {
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM produtos WHERE id = ?")) {
             
            ps.setInt(1, id);
            ps.executeUpdate();
            
        } catch (Exception ex) {
            throw new IllegalStateException("Nao foi possivel excluir produto: " + ex.getMessage(), ex);
        }
    }

    private Produto mapear(ResultSet rs) throws Exception {
        Produto produto;
        if ("TOLDO".equals(rs.getString("categoria"))) {
            produto = new Toldo(
                    rs.getDouble("largura"),
                    rs.getDouble("altura"),
                    rs.getString("material"),
                    rs.getString("tipo"),
                    rs.getString("cor"));
        } else {
            produto = new Cortina(
                    rs.getDouble("largura"),
                    rs.getDouble("altura"),
                    rs.getString("tecido"));
        }
        produto.setId(rs.getInt("id"));
        return produto;
    }
}
