
package Sistema_Supermercado;

import java.sql.*;

public class Conexao {

    public static final String SERVIDOR = "jdbc:mysql://localhost:3306/supermercado";
    public static final String USUARIO = "root";
    public static final String SENHA = "123456";

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private long resultId = 0;

    // Conecta ao banco de dados
    public void conectar() throws Exception {
        try {
            // Carrega o driver MySQL, cada banco de dados tem seu próprio driver
//            Class.forName("com.mysql.cj.jdbc.Driver");

            // Configura a conexão com o banco de dados
            this.connection = DriverManager.getConnection(SERVIDOR, USUARIO, SENHA);;

            // As instruções permitem emitir consultas SQL ao banco de dados
            this.statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection () {
        return this.connection;
    }

    // Fecha a conexão
    public void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executar(String sql) {
        try {
            String[] generatedColumns = {"ID"};

            this.connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(sql, generatedColumns);
            preparedStatement.executeUpdate();
            this.resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                this.resultId = resultSet.getLong(1);
            }

            this.connection.commit();
        } catch (SQLException e) {
            try {
                this.connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();
        }
    }

    public ResultSet consultar(String sql) {
        try {
            this.resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return this.resultSet;
    }

    public int getResultId() {
        return Integer.parseInt(String.valueOf(this.resultId));
    }
}