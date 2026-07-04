package br.com.g4.orcamentos.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String DB_DIR = "database";
    private static final String URL = "jdbc:sqlite:" + DB_DIR + "/orcamentos.db";

    private Database() {
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            throw new SQLException("Driver SQLite JDBC nao encontrado em versao-final/lib.", ex);
        }
        return DriverManager.getConnection(URL);
    }

    public static void inicializar() {
        File dir = new File(DB_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        executarSchema();
    }

    private static void executarSchema() {
        File schema = new File(DB_DIR, "schema.sql");
        if (!schema.exists()) {
            throw new IllegalStateException("Arquivo database/schema.sql nao encontrado.");
        }
        StringBuilder sql = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(schema));
            String linha;
            while ((linha = reader.readLine()) != null) {
                sql.append(linha).append('\n');
            }
            reader.close();
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String[] comandos = sql.toString().split(";");
            for (String comando : comandos) {
                if (!comando.trim().isEmpty()) {
                    statement.execute(comando);
                }
            }
            statement.close();
            connection.close();
        } catch (Exception ex) {
            throw new IllegalStateException("Falha ao inicializar banco de dados: " + ex.getMessage(), ex);
        }
    }
}
