package Sistema_Supermercado.DAO;

import Sistema_Supermercado.Conexao;
import Sistema_Supermercado.Produto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProdutosDAO {

    public int create(Produto produto) throws Exception {
        try {
            Conexao conexao = new Conexao();
            conexao.conectar();

            String sql = "INSERT INTO produtos (nome_produto, descricao_produto) VALUES ('" + produto.getNome_produto() + "', '" + produto.getDescricao_produto() + "')";
            conexao.executar(sql);
            int id = conexao.getResultId();
            conexao.close();
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean update(Produto produto) throws Exception {
        try {
            Conexao conexao = new Conexao();
            conexao.conectar();

            String sql = "UPDATE produtos SET nome_produto = '" + produto.getNome_produto() + "', descricao_produto = '" + produto.getDescricao_produto() + "' WHERE id_produto = " + produto.getId_produto();

            conexao.executar(sql);

            int id = conexao.getResultId();
            conexao.close();

            return id > -1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Produto get(int idProduto) throws Exception {
        Produto produto = null;

        try {
            Conexao conexao = new Conexao();
            conexao.conectar();

            String sql = "SELECT id_produto, nome_produto, descricao_produto FROM produtos WHERE id_produto = ?";

            // Usando pra evitar SQL Injection
            PreparedStatement stmt = conexao.getConnection().prepareStatement(sql);
            stmt.setInt(1, idProduto);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Cria um novo objeto Produto e preenche com os dados recuperados
                produto = new Produto();
                produto.setId_produto(rs.getInt("id_produto"));
                produto.setNome_produto(rs.getString("nome_produto"));
                produto.setDescricao_produto(rs.getString("descricao_produto"));
            }

            rs.close();
            stmt.close();
            conexao.close();

            return produto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void listAll() {
        ResultSet rs = null;
        Statement stmt = null;
        Conexao conexao = new Conexao();
        try {
            conexao.conectar();

            stmt = conexao.getConnection().createStatement();

            // Consulta para buscar todos os produtos
            String sql = "SELECT id_produto, nome_produto, descricao_produto FROM produtos ORDER BY id_produto";

            rs = stmt.executeQuery(sql);

            //se o ResultSet contém dados
            if (rs != null) {
                int count = 1;
                while (rs.next()) {
                    int idProduto = rs.getInt("id_produto");
                    String nomeProduto = rs.getString("nome_produto");
                    String descricaoProduto = rs.getString("descricao_produto");

                    System.out.println(count + " - " + nomeProduto + " | " + descricaoProduto);
                    count++;
                }
            } else {
                System.out.println("Nenhum produto encontrado.");
            }
        } catch (SQLException e) {
            // Em caso de erro
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // Fecha o ResultSet
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean delete(int idProduto) {
        PreparedStatement stmt = null;
        Conexao conexao = new Conexao();
        try {
            conexao.conectar();

            //para deletar produto com o ID
            String sql = "DELETE FROM produtos WHERE id_produto = ?";

            stmt = conexao.getConnection().prepareStatement(sql);
            stmt.setInt(1, idProduto);

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean check(int idProduto) throws Exception {
        try {
            Conexao conexao = new Conexao();
            conexao.conectar();

            // para verificar se existe um produto com o id_produto
            String sql = "SELECT COUNT(*) FROM produtos WHERE id_produto = ?";

            PreparedStatement stmt = conexao.getConnection().prepareStatement(sql);
            stmt.setInt(1, idProduto);

            ResultSet rs = stmt.executeQuery();

            // Verifica se o resultado contém algum valor
            boolean exists = false;
            if (rs.next()) {
                exists = rs.getInt(1) > 0;
            }

            rs.close();
            stmt.close();
            conexao.close();

            return exists;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
