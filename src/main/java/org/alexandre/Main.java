package org.alexandre;

import org.alexandre.dao.ClientDAO;
import org.alexandre.forms.Client;
import org.alexandre.helper.CryptographyManager;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Inicializa um scanner para capturar entradas do usuário
        Scanner scanner = new Scanner(System.in);
        boolean _lockenable = true;

        try {
            while (_lockenable) {
                System.out.println("Selecione uma opção: ");
                System.out.println("\t1 - Consultar");
                System.out.println("\t2 - Cadastrar");
                System.out.println("\t0 - Finalizar  ");

                // Captura a escolha do usuário
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Qual o ID do Cliente que deseja consultar: ");
                        Long id = scanner.nextLong();
                        if (new ClientDAO().existsData(id)) {
                            Client client = new ClientDAO().getData(id);
                            Client clientDecryptography = new Client.Builder()
                                    .setId(client.getId())
                                    .setName(new CryptographyManager()
                                            .getDecryptedString(String.valueOf(client.getId()), client.getName()))
                                    .setCardToken(new CryptographyManager()
                                            .getDecryptedString(String.valueOf(client.getId()), client.getCardToken()))
                                    .setValue(client.getValue())
                                    .build();

                            System.out.println();
                            System.out.print("Nome: " + clientDecryptography.getName());
                            System.out.print("\nCard Token: " + clientDecryptography.getCardToken());
                            System.out.println(" Valor: " + clientDecryptography.getValue());

                            System.out.println("Selecione uma opção: ");
                            System.out.println("\t1 - Editar");
                            System.out.println("\t2 - Deletar");
                            System.out.println("\t0 - Voltar");

                            choice = scanner.nextInt();

                            switch (choice) {
                                case 1:
                                    Client clientUpdate = new Client();
                                    System.out.println("Novo nome: ");
                                    clientUpdate.setName(scanner.next());
                                    System.out.println("Novo Card Token: ");
                                    clientUpdate.setCardToken(scanner.next());
                                    System.out.println("Novo valor: ");
                                    clientUpdate.setValue(scanner.nextLong());
                                    clientUpdate.setId(id);

                                    Client clientUpdateCryptography = new Client.Builder()
                                            .setId(id)
                                            .setName(new CryptographyManager()
                                                    .getEncryptedString(String.valueOf(id), clientUpdate.getName()))
                                            .setCardToken(new CryptographyManager()
                                                    .getEncryptedString(String.valueOf(id), clientUpdate.getCardToken()))
                                            .setValue(clientUpdate.getValue())
                                            .build();

                                    //Salva no banco de dados usando ClientDAO
                                    new ClientDAO().updateData(clientUpdateCryptography);
                                    System.out.println("Cliente atualizado com sucesso!");
                                    break;
                                case 2:
                                    new ClientDAO().deleteData(id);
                                    System.out.println("Cliente deletado com sucesso!");
                                    break;
                                case 0:
                                    break;
                                default:
                                    System.out.println("Opção inválida!");
                                    break;
                            }

                        } else {
                            System.out.println("ID: " + id + " não encontrado!");
                        }
                        break;
                    case 2:
                        Client client = new Client();
                        System.out.println("Nome: ");
                        client.setName(scanner.next());
                        System.out.println("CardToken: ");
                        client.setCardToken(scanner.next());
                        System.out.println("Valor: ");
                        client.setValue(scanner.nextLong());

                        Long randomID = 0L;
                        boolean randomize = true;
                        while (randomize) {
                            randomID = (long) Math.floor((Math.random() * 999) + 1);
                            if (!new ClientDAO().existsData(randomID)) {
                                randomize = false;
                            }
                        }
                        client.setId(randomID);

                        Client clientCryptography = new Client.Builder()
                                .setId(randomID)
                                .setName(new CryptographyManager()
                                        .getEncryptedString(String.valueOf(randomID), client.getName()))
                                .setCardToken(new CryptographyManager()
                                        .getEncryptedString(String.valueOf(randomID), client.getCardToken()))
                                .setValue(client.getValue())
                                .build();

                        //Salva no banco de dados usando ClientDAO
                        new ClientDAO().insertData(clientCryptography);
                        System.out.println("Cliente cadastrado com sucesso!");
                        System.out.println("O ID desse cliente é: " + clientCryptography.getId());
                        break;
                    case 0:
                        _lockenable = false;
                        break;
                    default:
                        System.out.println("Selecione uma opção válida!");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Erro: (Main)001\n" + e);
        } finally {
            //Garante que o Scanner será corretamente fechado
            scanner.close();
        }
    }
}