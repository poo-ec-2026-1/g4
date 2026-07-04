package br.com.g4.orcamentos.repository;

import br.com.g4.orcamentos.database.Database;
import br.com.g4.orcamentos.domain.Cliente;
import br.com.g4.orcamentos.domain.Orcamento;
import br.com.g4.orcamentos.domain.Produto;
import br.com.g4.orcamentos.domain.StatusOrcamento;
import br.com.g4.orcamentos.domain.Vendedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrcamentoDAO {
    private ClienteDAO clienteDAO = new ClienteDAO();
    private VendedorDAO vendedorDAO = new VendedorDAO();
    private ProdutoDAO produtoDAO = new ProdutoDAO();

    public Orcamento salvar(Orcamento orcamento) {
        String sql = "INSERT INTO orcamentos (cliente_id, vendedor_id, status, criado_em) VALUES (?, ?, ?, ?)";
        
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
             
            ps.setInt(1, orcamento.getCliente().getId());
            ps.setInt(2, orcamento.getVendedor().getId());
            ps.setString(3, orcamento.getStatus().name());
            ps.setString(4, orcamento.getCriadoEm().toString());
            ps.executeUpdate();
            
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    orcamento.setId(rs.getInt(1));
                }
            }
            
            for (Produto produto : orcamento.getProdutos()) {
                vincularProduto(c, orcamento.getId(), produto.getId());
            }
            
            return orcamento;
            
        } catch (Exception ex) {
            throw new IllegalStateException("Nao foi possivel salvar orcamento: " + ex.getMessage(), ex);
        }
    }

    public List<Orcamento> listar() {
        List<Orcamento> orcamentos = new ArrayList<Orcamento>();
        
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM orcamentos ORDER BY id DESC");
             ResultSet rs = ps.executeQuery()) {
             
            while (rs.next()) {
                Orcamento orcamento = mapear(rs);
                orcamento.setProdutos(listarProdutos(c, orcamento.getId()));
                orcamentos.add(orcamento);
            }
            
        } catch (Exception ex) {
            throw new IllegalStateException("Nao foi possivel listar orcamentos: " + ex.getMessage(), ex);
        }
        
        return orcamentos;
    }

    public void atualizarStatus(int id, StatusOrcamento status) {
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement("UPDATE orcamentos SET status = ? WHERE id = ?")) {
             
            ps.setString(1, status.name());
            ps.setInt(2, id);
            ps.executeUpdate();
            
        } catch (Exception ex) {
            throw new IllegalStateException("Nao foi possivel atualizar status: " + ex.getMessage(), ex);
        }
    }

    public void excluir(int id) {
        try (Connection c = Database.getConnection();
             PreparedStatement limpar = c.prepareStatement("DELETE FROM orcamento_produtos WHERE orcamento_id = ?");
             PreparedStatement ps = c.prepareStatement("DELETE FROM orcamentos WHERE id = ?")) {
             
            limpar.setInt(1, id);
            limpar.executeUpdate();
            
            ps.setInt(1, id);
            ps.executeUpdate();
            
        } catch (Exception ex) {
            throw new IllegalStateException("Nao foi possivel excluir orcamento: " + ex.getMessage(), ex);
        }
    }

    private void vincularProduto(Connection c, int orcamentoId, int produtoId) throws Exception {
        try (PreparedStatement ps = c.prepareStatement("INSERT OR IGNORE INTO orcamento_produtos (orcamento_id, produto_id) VALUES (?, ?)")) {
            ps.setInt(1, orcamentoId);
            ps.setInt(2, produtoId);
            ps.executeUpdate();
        }
    }

    private List<Produto> listarProdutos(Connection c, int orcamentoId) throws Exception {
        List<Produto> produtos = new ArrayList<Produto>();
        
        try (PreparedStatement ps = c.prepareStatement("SELECT produto_id FROM orcamento_produtos WHERE orcamento_id = ?")) {
            ps.setInt(1, orcamentoId);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Produto produto = produtoDAO.buscarPorId(rs.getInt("produto_id"));
                    if (produto != null) {
                        produtos.add(produto);
                    }
                }
            }
        }
        
        return produtos;
    }

    private Orcamento mapear(ResultSet rs) throws Exception {
        Cliente cliente = clienteDAO.buscarPorId(rs.getInt("cliente_id"));
        Vendedor vendedor = vendedorDAO.buscarPorId(rs.getInt("vendedor_id"));
        return new Orcamento(
                rs.getInt("id"),
                cliente,
                vendedor,
                StatusOrcamento.valueOf(rs.getString("status")),
                LocalDateTime.parse(rs.getString("criado_em")));
    }
}
