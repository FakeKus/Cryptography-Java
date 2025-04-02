package org.alexandre.connections;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    private static final String USERNAME = "";                                    //Usuário do Banco de Dados
    private static final String PASSWORD = "";                                    //Senha do Banco de Dados
    private static final String DATABASE_URL = "jdbc:sqlite:"
            + System.getProperty("user.dir").replace("\\", "/")
            + "/src/main/resources/database/";                                    //URL para o Banco de Dados

    public static Connection connectDB() {

        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            System.out.println("Erro ao conectar com o banco de dados: 001(ConnectionFactory)\n" + e);

            return null;
        }
    }
}