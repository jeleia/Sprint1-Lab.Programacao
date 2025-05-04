import java.util.Scanner;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {

        DecimalFormat df = new DecimalFormat("00.00");
        Scanner sc = new Scanner(System.in);

        //Declaração de variáveis
        int distancia = 0, x = 1, y = 10;
        double bateria = 0, bateriaMinima = 25;
        String comando;

        //Declaração da pista
        boolean[][] pista = new boolean[12][24];

        // Pista no eixo X
        for (int j = 1; j <= 8; j++) {
            for (int i = 9; i <= 11; i++) {
                pista[i][j] = true;
            }
        }

        for (int j = 6; j <= 14; j++) {
            for (int i = 6; i <= 8; i++) {
                pista[i][j] = true;
            }
        }

        for (int j = 12; j <= 23; j++) {
            for (int i = 3; i <= 5; i++) {
                pista[i][j] = true;
            }
        }

        // Pista no eixo Y
        for (int i = 6; i <= 8; i++) {
            for (int j = 6; j <= 14; j++) {
                pista[i][j] = true;
            }
        }

        for (int i = 3; i <= 5; i++) {
            for (int j = 12; j <= 23; j++) {
                pista[i][j] = true;
            }
        }

        //Instruções pro usuário
        System.out.println("O robô se move em um plano cartesiano, onde a direção inicial e para onde ele está virado é leste!");
        System.out.println("Os comandos aceitáveis são: FRENTE, TRAS, DIREITA e ESQUERDA!");
        System.out.println("As distâncias aceitáveis são em formato numérico inteiro!");
        System.out.println("O robô está com 50% de bateria e precisa de no mínimo 25% para concluir o trajeto. Cada passo consome 1,12% de bateria.");

        //Looping
        while (true) {

            //Instrução para inserir o comando
            System.out.println("Insira o comando (ou digite FIM para encerrar):");
            comando = sc.nextLine().toUpperCase();

            // Comando "fim" finaliza o código
            if (comando.equals("FIM")) {
                break;
            }

            //Validação da string
            if (!comando.equals("DIREITA") && !comando.equals("ESQUERDA") &&
                    !comando.equals("FRENTE") && !comando.equals("TRAS")) {
                System.out.println("Comando inválido! Use: FRENTE, TRAS, DIREITA ou ESQUERDA.");
                continue;
            }

            //Instrução para inserir a distância e validação do int
            System.out.println("Insira a distância:");
            while (true) {
                if (sc.hasNextInt()) {
                    distancia = sc.nextInt();
                    if (distancia >= 0) {
                        sc.nextLine();
                        break;
                    } else {
                        System.out.println("Comando inválido! Digite um número positivo:");
                        sc.nextLine();
                    }
                } else {
                    System.out.println("Comando inválido! Digite um número inteiro:");
                    sc.nextLine();
                }
            }

            // Movimento para DIREITA e calculo da bateria por passo
            if (comando.equals("DIREITA")) {
                for (int i = 0; i < distancia; i++) {
                    if (y - 1 >= 0 && pista[y - 1][x]) {
                        y--;
                    } else {
                        System.out.println("O robô parou antes de sair da pista!");
                        break;
                    }
                }
                bateria += distancia * 1.12;
            }

            // Movimento para ESQUERDA e calculo da bateria por passo
            if (comando.equals("ESQUERDA")) {
                for (int i = 0; i < distancia; i++) {
                    if (y + 1 < pista.length && pista[y + 1][x]) {
                        y++;
                    } else {
                        System.out.println("O robô parou antes de sair da pista!");
                        break;
                    }
                }
                bateria += distancia * 1.12;
            }

            // Movimento para FRENTE e calculo da bateria por passo
            if (comando.equals("FRENTE")) {
                for (int i = 0; i < distancia; i++) {
                    if (x + 1 < pista[0].length && pista[y][x + 1]) {
                        x++;
                    } else {
                        System.out.println("O robô parou antes de sair da pista!");
                        break;
                    }
                }
                bateria += distancia * 1.12;
            }

            // Movimento para TRAS e calculo da bateria por passo
            if (comando.equals("TRAS")) {
                for (int i = 0; i < distancia; i++) {
                    if (x - 1 >= 0 && pista[y][x - 1]) {
                        x--;
                    } else {
                        System.out.println("O robô parou antes de sair da pista!");
                        break;
                    }
                }
                bateria += distancia * 1.12;
            }

            //Limite da bateria atingido
            if (bateria >= 50) {
                System.out.println("\nBateria insuficiente. Carregue o robô!\n");
                break;
            }

            //Atualização da posição e bateria
            System.out.println("Posição atual: (" + x + ", " + y + ")");
            System.out.println("Bateria usada: " + df.format(bateria) + "%");

            //Mensagem pra linha de chegada
            if (x == 23 && y == 4) {
                System.out.println("Parabéns! Você chegou ao final do trajeto!");
                break;
            }
        }

        //Mensagem para a finalização do trajeto
        System.out.println("\nPosição final do robô: (" + x + "," + y + ")");
        System.out.println("Bateria usada no trajeto: " + df.format(bateria) + "%.\n");
        System.out.println("\nPrograma encerrado.");
        sc.close();
    }
}
