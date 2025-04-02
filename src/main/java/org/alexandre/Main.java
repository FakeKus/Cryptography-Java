package org.alexandre;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean _lockenable = true;

        while (_lockenable) {
            System.out.println("Selecione uma opção: ");
            System.out.println("\t1 - Consultar");
            System.out.println("\t2 - Cadrastrar");
            System.out.println("\t3 - Editar");
            System.out.println("\t4 - Deletar");
            System.out.println("\t0 - Finalizar  " + System.getProperty("user.dir"));

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Consultar");
                    break;
                case 2:
                    System.out.println("Cadastrar");
                    break;
                case 3:
                    System.out.println("Editar");
                    break;
                case 4:
                    System.out.println("Delete");
                    break;
                case 0:
                    _lockenable = false;
                    break;
            }
        }
    }
}