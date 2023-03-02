package maven.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        try (Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/exercicio2", "ti2cc", "ti@cc")) {
            DAO dao = new DAO(conexao);
            Scanner sc = new Scanner(System.in);
            int opcao;
            do {
                System.out.println("1 - Listar");
                System.out.println("2 - Inserir");
                System.out.println("3 - Excluir");
                System.out.println("4 - Atualizar");
                System.out.println("5 - Sair");
                System.out.print("Digite uma opção: ");
                opcao = sc.nextInt();
                sc.nextLine();
                switch (opcao) {
                    case 1:
                        List<x> listaX = dao.listar();
                        for (x x : listaX) {
                            System.out.println("ID: " + x.getId());
                            System.out.println("Nome: " + x.getNome());
                            System.out.println("------------------------------");
                        }
                        break;
                    case 2:
                        System.out.print("Digite o ID: ");
                        int idInserir = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Digite o nome: ");
                        String nomeInserir = sc.nextLine();
                        x xInserir = new x(idInserir, nomeInserir);
                        dao.inserir(xInserir);
                        break;
                    case 3:
                        System.out.print("Digite o ID: ");
                        int idExcluir = sc.nextInt();
                        sc.nextLine();
                        dao.excluir(idExcluir);
                        break;
                    case 4:
                        System.out.print("Digite o ID: ");
                        int idAtualizar = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Digite o nome: ");
                        String nomeAtualizar = sc.nextLine();
                        x xAtualizar = new x(idAtualizar, nomeAtualizar);
                        dao.atualizar(xAtualizar);
                        break;
                    case 5:
                        System.out.println("Encerrando...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } while (opcao != 5);
        } catch (SQLException e) {
            System.out.println("Erro de conexão com o banco de dados: " + e.getMessage());
        }
    }
}