package Sistema_Supermercado;

import Sistema_Supermercado.DAO.EstoqueDAO;
import Sistema_Supermercado.DAO.ProdutosDAO;

import java.util.Scanner;

public class View {
    private String context = "initial";
    private boolean running = true;
    Scanner scanner = new Scanner(System.in);

    public void init() throws Exception {
        System.out.println("Bem-vindo ao Sistema de Supermercado!\n");
        view();
    }

    public void view() throws Exception {
        while (this.running) {
            System.out.println("\nO que deseja fazer?\n");

            switch (this.context) {
                case "initial" -> {
                    System.out.println("1 - Gerenciar produtos");
                    System.out.println("2 - Gerenciar estoque");
                    System.out.println("3 - Sair\n");

                    String userOption = this.scanner.nextLine();

                    if (userOption.equals("3")) {
                        this.running = false;
                    } else {
                        switch (userOption) {
                            case "1" -> {
                                context = "produtos";
                            }
                            case "2" -> {
                                context = "estoque";
                            }
                            default -> {
                                System.out.println("Opção inválida!");
                            }
                        }
                    }
                }
                case "produtos" -> {
                    produtoView();
                }
                case "estoque" -> {
                    estoqueView();
                }
                default -> {
                    System.out.println("Opção inválida!");
                }
            }
        }
    }

    private void produtoView() throws Exception {
        System.out.println("1 - Adicionar produto");
        System.out.println("2 - Alterar produto");
        System.out.println("3 - Listar produtos");
        System.out.println("4 - Remover produto");
        System.out.println("5 - Voltar");
        System.out.println("6 - Sair\n");

        String userOption = this.scanner.nextLine();
        ProdutosDAO produtosDAO = new ProdutosDAO();

         switch (userOption) {
             case "1" -> {
                 Produto produto = new Produto();

                 System.out.println("\nDigite o nome do produto:");
                 produto.setNome_produto(this.scanner.nextLine());

                 System.out.println("\nDigite a descrição do produto:");
                 produto.setDescricao_produto(this.scanner.nextLine());

                 int id = produtosDAO.create(produto);
                 System.out.println("\nProduto " + produto.getNome_produto() + " criado com ID " + id + " com sucesso!");
             }
             case "2" -> {
                 Produto produto = new Produto();

                 System.out.println("\nDigite o ID do produto que deseja alterar:");
                 produto.setId_produto(Integer.parseInt(this.scanner.nextLine()));

                 while (!produtosDAO.check(produto.getId_produto())) {
                     System.out.println("\nNão existe produto com o ID informado!");
                     System.out.println("\nDigite o ID do produto que deseja alterar:");
                     produto.setId_produto(Integer.parseInt(this.scanner.nextLine()));
                 }

                 produto = produtosDAO.get(produto.getId_produto());

                 System.out.println("\nO que deseja alterar?\n");
                 System.out.println("1 - Nome: " + produto.getNome_produto());
                 System.out.println("2 - Descrição: " + produto.getDescricao_produto());

                 userOption = this.scanner.nextLine();

                 switch (userOption) {
                     case "1" -> {
                         System.out.println("\nDigite o novo nome do produto:");
                         produto.setNome_produto(this.scanner.nextLine());
                     }
                     case "2" -> {
                         System.out.println("\nDigite a nova descrição do produto:");
                         produto.setDescricao_produto(this.scanner.nextLine());
                     }
                 }

                 boolean updated = produtosDAO.update(produto);

                 if (updated) {
                     System.out.println("Produto alterado com sucesso!");
                 } else {
                     System.out.println("Erro ao alterar produto!");
                 }
             }
             case "3" -> {
                 produtosDAO.listAll();
             }
             case "4" -> {
                 System.out.println("\nDigite o ID do produto que deseja remover:");
                 int idProduto = Integer.parseInt(this.scanner.nextLine());

                 if (produtosDAO.check(idProduto)) {
                     boolean deleted = produtosDAO.delete(idProduto);

                     if (deleted) {
                         System.out.println("Produto removido com sucesso.");
                     } else {
                         System.out.println("Erro ao remover o produto.");
                     }
                 } else {
                     System.out.println("Não existe produto com o ID informado.");
                 }
             }
             case "5" -> {
                 this.context = "initial";
             }
             case "6" -> {
                 this.running = false;
             }
         }
    }

    private void estoqueView() throws Exception {
        System.out.println("1 - Adicionar produto no estoque");
        System.out.println("2 - Alterar produto no estoque");
        System.out.println("3 - Listar estoque");
        System.out.println("4 - Listar estoque de um produto");
        System.out.println("5 - Remover produto do estoque");
        System.out.println("6 - Voltar");
        System.out.println("7 - Sair\n");

        String userOption = this.scanner.nextLine();
        EstoqueDAO estoqueDAO = new EstoqueDAO();

        switch (userOption) {
            case "1" -> {
                Estoque estoque = new Estoque();

                System.out.println("\nDigite o ID do produto:");
                estoque.setIdProduto(Integer.parseInt(this.scanner.nextLine()));

                System.out.println("\nDigite a quantidade do produto:");
                estoque.setQuantidade(Integer.parseInt(this.scanner.nextLine()));

                System.out.println("\nDigite o tipo de quantidade (unidade, caixa, etc.):");
                estoque.setTipoQuantidade(this.scanner.nextLine());

                boolean added = estoqueDAO.add(estoque);
                if (added) {
                    System.out.println("Produto adicionado ao estoque com sucesso!");
                } else {
                    System.out.println("Erro ao adicionar produto ao estoque!");
                }
            }
            case "2" -> {
                Estoque estoque = new Estoque();

                System.out.println("\nDigite o ID do produto que deseja alterar:");
                estoque.setIdProduto(Integer.parseInt(this.scanner.nextLine()));

                System.out.println("\nDigite a nova quantidade:");
                estoque.setQuantidade(Integer.parseInt(this.scanner.nextLine()));

                System.out.println("\nDigite o novo tipo de quantidade:");
                estoque.setTipoQuantidade(this.scanner.nextLine());

                boolean updated = estoqueDAO.update(estoque);
                if (updated) {
                    System.out.println("Produto no estoque alterado com sucesso!");
                } else {
                    System.out.println("Erro ao alterar produto no estoque!");
                }
            }
            case "3" -> {
                estoqueDAO.listAll();
            }
            case "4" -> {
                System.out.println("\nDigite o ID do produto para listar o estoque:");
                int idProduto = Integer.parseInt(this.scanner.nextLine());
                estoqueDAO.listByProduto(idProduto);
            }
            case "5" -> {
                System.out.println("\nDigite o ID do produto para remover do estoque:");
                int idProduto = Integer.parseInt(this.scanner.nextLine());
                boolean removed = estoqueDAO.remove(idProduto);
                if (removed) {
                    System.out.println("Produto removido do estoque com sucesso!");
                } else {
                    System.out.println("Erro ao remover produto do estoque!");
                }
            }
            case "6" -> {
                this.context = "initial";
            }
            case "7" -> {
                this.running = false;
            }
        }
    }

}
