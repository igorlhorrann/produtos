package Sistema_Supermercado.DAO;

import Sistema_Supermercado.Conexao;
import Sistema_Supermercado.Estoque;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EstoqueDAO {
    Conexao conexao = new Conexao();

    public boolean add(Estoque estoque) {
        ResultSet rs = null;

        try {
            conexao.conectar();

            String sql = "SELECT quantidade FROM estoque WHERE id_produto = ?";

            PreparedStatement stmt = conexao.getConnection().prepareStatement(sql);
            stmt.setInt(1, estoque.getIdProduto());

            rs = stmt.executeQuery();

            if (rs.next()) {
                int quantidadeExistente = rs.getInt("quantidade");
                int novaQuantidade = quantidadeExistente + estoque.getQuantidade();

                //atualizar a quantidade no estoque
                String updateSql = "UPDATE estoque SET quantidade = ? WHERE id_produto = ?";
                PreparedStatement updateStmt = conexao.getConnection().prepareStatement(updateSql);
                updateStmt.setInt(1, novaQuantidade);
                updateStmt.setInt(2, estoque.getIdProduto());


                int rowsAffected = updateStmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Quantidade do produto no estoque atualizada com sucesso!");
                    return true;
                } else {
                    System.out.println("Erro ao atualizar o estoque do produto.");
                    return false;
                }
            } else {
                // Produto não existe
                String insertSql = "INSERT INTO estoque (id_produto, quantidade, tipo_quantidade) VALUES (?, ?, ?)";
                PreparedStatement insertStmt = conexao.getConnection().prepareStatement(insertSql);
                insertStmt.setInt(1, estoque.getIdProduto());
                insertStmt.setInt(2, estoque.getQuantidade());
                insertStmt.setString(3, estoque.getTipoQuantidade());


                int rowsAffected = insertStmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Produto adicionado ao estoque com sucesso!");
                    return true;
                } else {
                    System.out.println("Erro ao adicionar produto ao estoque.");
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public boolean update(Estoque estoque) {
        try {
            conexao.conectar();

            String sql = "UPDATE estoque SET quantidade = ?, tipo_quantidade = ? WHERE id_produto = ?";


            PreparedStatement stmt = conexao.getConnection().prepareStatement(sql);
            stmt.setInt(1, estoque.getQuantidade());
            stmt.setString(2, estoque.getTipoQuantidade());
            stmt.setInt(3, estoque.getIdProduto());

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            conexao.close();
        }
    }

    public void listAll() {
        ResultSet rs = null;
        try {
            conexao.conectar();

            String sql = "SELECT p.nome_produto, e.quantidade, e.tipo_quantidade " +
                    "FROM estoque e " +
                    "JOIN produtos p ON e.id_produto = p.id_produto";

            PreparedStatement stmt = conexao.getConnection().prepareStatement(sql);

            rs = stmt.executeQuery();

            System.out.println("Nome do produto | Quantidade | Tipo de quantidade");
            while (rs.next()) {
                String nomeProduto = rs.getString("nome_produto");
                int quantidade = rs.getInt("quantidade");
                String tipoQuantidade = rs.getString("tipo_quantidade");

                System.out.println(nomeProduto + " | " + quantidade + " | " + tipoQuantidade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void listByProduto(int idProduto) {
        ResultSet rs = null;
        try {
            conexao.conectar();

            String sql = "SELECT p.nome_produto, e.quantidade, e.tipo_quantidade " +
                    "FROM estoque e " +
                    "JOIN produtos p ON e.id_produto = p.id_produto " +
                    "WHERE e.id_produto = ?";

            PreparedStatement stmt = conexao.getConnection().prepareStatement(sql);
            stmt.setInt(1, idProduto);

            rs = stmt.executeQuery();

            if (rs.next()) {
                String nomeProduto = rs.getString("nome_produto");
                int quantidade = rs.getInt("quantidade");
                String tipoQuantidade = rs.getString("tipo_quantidade");

                System.out.println("Nome do produto | Quantidade | Tipo de quantidade");
                System.out.println(nomeProduto + " | " + quantidade + " | " + tipoQuantidade);
            } else {
                System.out.println("Não há estoque registrado para o produto com ID: " + idProduto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public boolean remove(int idProduto) {
        try {
            conexao.conectar();

            // SQL para remover produto do estoque
            String sql = "DELETE FROM estoque WHERE id_produto = ?";

            PreparedStatement stmt = conexao.getConnection().prepareStatement(sql);
            stmt.setInt(1, idProduto);

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            conexao.close();
        }
    }

}