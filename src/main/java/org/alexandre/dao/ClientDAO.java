package org.alexandre.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.alexandre.connections.ConnectionFactory;
import org.alexandre.forms.Client;

public class ClientDAO {

    //Iniciando variáveis
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private final List<Client> listClients = new ArrayList<>();

    //Declaração das consultas SQL
    private final String insert         = "INSERT INTO clients (id, name, cardToken, value) VALUES (?, ?, ?, ?)";
    private final String select         = "SELECT * FROM clients";
    private final String selectWhere    = "SELECT * FROM clients WHERE id = ?";
    private final String update         = "UPDATE clients SET name = ?, cardToken = ?, value = ? WHERE id = ?";
    private final String delete         = "DELETE FROM clients";
    private final String deleteWhere    = "DELETE FROM clients WHERE id = ?";

    public ClientDAO() {
        //Inicializa a conexão com o banco de dados
        connection = null;
        preparedStatement = null;
        resultSet = null;

        connection = ConnectionFactory.connectDB();
    }

    public void insertData(Client client) {
        try {
            //Prepara a consulta de inserção
            preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setLong(1, client.getId());
            preparedStatement.setString(2, client.getName());
            preparedStatement.setString(3, client.getCardToken());
            preparedStatement.setLong(4, client.getValue());

            //Executa a inserção
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro ao inserir dados: (ClientDAO)001\n" + e);
        } finally {
            //Fecha a conexão e os recursos
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                System.out.println("Erro ao fechar conexão: (ClientDAO)002\n" + e);
            }
        }
    }

    public List<Client> getData() {
        try {
            //Prepara a consulta de seleção
            preparedStatement = connection.prepareStatement(select);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //Cria um objeto Subject a partir dos dados do ResultSet
                Client client = new Client.Builder()
                        .setId(resultSet.getLong("id"))
                        .setName(resultSet.getString("name"))
                        .setCardToken(resultSet.getString("cardToken"))
                        .setValue(resultSet.getLong("value"))
                        .build();

                listClients.add(client);
            }

            return listClients;
        } catch (Exception e) {
            System.out.println("Erro ao buscar dados: (ClientDAO)003\n" + e);
        } finally {
            //Fecha a conexão e os recursos
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                System.out.println("Erro ao fechar conexão: (ClientDAO)004\n" + e);
            }
        }

        return null;
    }

    public Client getData(Long id) {
        try {
            //Prepara a consulta de seleção com filtro
            preparedStatement = connection.prepareStatement(selectWhere);
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                //Cria um objeto Subject a partir dos dados do ResultSet
                return new Client.Builder()
                        .setId(resultSet.getLong("id"))
                        .setName(resultSet.getString("name"))
                        .setCardToken(resultSet.getString("cardToken"))
                        .setValue(resultSet.getLong("value"))
                        .build();
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar dados: (ClientDAO)005\n" + e);
        } finally {
            //Fecha a conexão e os recursos
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                System.out.println("Erro ao fechar conexão: (ClientDAO)006\n" + e);
            }
        }

        return null;
    }

    public void updateData(Client client) {
        try {
            //Prepara a consulta de atualização
            preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getCardToken());
            preparedStatement.setLong(3, client.getValue());
            preparedStatement.setLong(4, client.getId());

            //Executa a atualização
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro ao atualizar dados: (ClientDAO)007\n" + e);
        } finally {
            //Fecha a conexão e os recursos
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                System.out.println("Erro ao fechar conexão: (ClientDAO)008\n" + e);
            }
        }
    }

    public void deleteData() {
        try {
            //Prepara a consulta de exclusão
            preparedStatement = connection.prepareStatement(delete);

            //Executa a exclusão
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro ao deletar dados: (ClientDAO)009\n" + e);
        } finally {
            //Fecha a conexão e os recursos
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                System.out.println("Erro ao fechar conexão: (ClientDAO)010\n" + e);
            }
        }
    }

    public void deleteData(Long id) {
        try {
            //Prepara a consulta de exclusão com filtro
            preparedStatement = connection.prepareStatement(deleteWhere);
            preparedStatement.setLong(1, id);

            //Executa a exclusão
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro ao deletar dados: (ClientDAO)011\n" + e);
        } finally {
            //Fecha a conexão e os recursos
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                System.out.println("Erro ao fechar conexão: (ClientDAO)012\n" + e);
            }
        }
    }

    public boolean existsData(Long id) {
        try {
            //Prepara a consulta de verificação de existência
            preparedStatement = connection.prepareStatement(selectWhere);
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar dados: (ClientDAO)012\n" + e);
        } finally {
            //Fecha a conexão e os recursos
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                System.out.println("Erro ao fechar conexão: (ClientDAO)014\n" + e);
            }
        }

        return false;
    }
}