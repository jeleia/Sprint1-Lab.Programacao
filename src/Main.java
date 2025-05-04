import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Declaração de variáveis
        Scanner sc = new Scanner(System.in);
        int distancia=0, bateria = 0, x = 1, y = 10, bateriaMinima=0;
        String comando;

        //Declaração da pista
        boolean[][] pista = new boolean[12][24];

        // Pista no eixo X
        for (int j = 1; j <= 8; j++) {
            pista[11][j] = true;
            pista[10][j] = true;
            pista[9][j] = true;
        }

        for (int j = 6; j <= 14; j++) {
            pista[8][j] = true;
            pista[7][j] = true;
            pista[6][j] = true;
        }

        for (int j = 12; j <= 23; j++) {
            pista[5][j] = true;
            pista[4][j] = true;
            pista[3][j] = true;
        }

        // Pista no eixo Y
        for (int i = 6; i <= 8; i++) {
            pista[i][6] = true;
            pista[i][7] = true;
            pista[i][8] = true;
            pista[i][9] = true;
            pista[i][10] = true;
            pista[i][11] = true;
            pista[i][12] = true;
            pista[i][13] = true;
            pista[i][14] = true;
        }

        for (int i = 3; i <= 5; i++) {
            pista[i][12] = true;
            pista[i][13] = true;
            pista[i][14] = true;
            pista[i][15] = true;
            pista[i][16] = true;
            pista[i][17] = true;
            pista[i][18] = true;
            pista[i][19] = true;
            pista[i][20] = true;
            pista[i][21] = true;
            pista[i][22] = true;
            pista[i][23] = true;
        }

        //Instruções para o usuário
        System.out.println("O robô se move em um plano cartesiano, onde a direção inicial e para onde ele está virado é leste!");
        System.out.println("Os comandos aceitáveis são: FRENTE, TRAS, DIREITA e ESQUERDA!");
        System.out.println("As distâncias aceitáveis são em formato numérico inteiro!");


        //Inserir a bateria
        System.out.println("Digite a bateria do robô:");

        //Inserir e validar a bateria
        while (true) {
            if (sc.hasNextInt()) {
                bateriaMinima = sc.nextInt();
                if (bateriaMinima > 0) {
                    sc.nextLine();
                    break;
                } else {
                    System.out.println("A bateria do robô deve ser maior que zero. Tente novamente:");
                    sc.nextLine();
                }
            } else {
                System.out.println("Comando inválido! Digite um número inteiro:");
                sc.nextLine();
            }
        }

        //Loop
        while (true) {

            //Calculo da bateria
            if (bateriaMinima < 28) {
                System.out.println("\nBateria insuficiente. Carregue o robô!\n");
                break;
            }

            //Inserir o comando
            System.out.println("Insira o comando (ou digite FIM para encerrar):");
            comando = sc.nextLine().toUpperCase();

            //Fim do comando
            if (comando.equals("FIM")) {
                break;
            }

            //Validação do comando
            if (!comando.equals("DIREITA") && !comando.equals("ESQUERDA") && !comando.equals("FRENTE") && !comando.equals("TRAS")) {
                System.out.println("Comando inválido! Use: FRENTE, TRAS, DIREITA ou ESQUERDA.");
                continue;
            }

            //Inserir e validar a distância
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

            //Calculo da bateria
            if (comando.equals("FRENTE") || comando.equals("TRAS") || comando.equals("DIREITA") || comando.equals("ESQUERDA")) {
                bateria += distancia;
            }

            // Movimento pra direita
            if (comando.equals("DIREITA")) {
                y -= distancia;

                // Validação do movimento na pista
                if (y < 0 || y >= pista.length || x < 0 || x >= pista[0].length || !pista[y][x]) {
                    y += distancia;
                    System.out.println("O robô sairá da pista com esse comando! Tente novamente!");

                    //Validação da bateria
                    if (bateria >= bateriaMinima){
                        System.out.println("\nBateria insuficiente. Carregue o robô!\n");
                        break;
                    }

                    continue;
                }

            }

            // Movimento pra esquerda
            if (comando.equals("ESQUERDA")) {
                y += distancia;

                // Validação do movimento na pista
                if (y < 0 || y >= pista.length || x < 0 || x >= pista[0].length || !pista[y][x]) {
                    y -= distancia;
                    System.out.println("O robô sairá da pista com esse comando! Tente novamente!");

                    //Validação da bateria
                    if (bateria >= bateriaMinima){
                        System.out.println("\nBateria insuficiente. Carregue o robô!\n");
                        break;
                    }

                    continue;
                }
            }

            // Movimento pra frente
            if (comando.equals("FRENTE")) {
                x += distancia;

                // Validação do movimento na pista
                if (x < 0 || x >= pista[0].length || y < 0 || y >= pista.length || !pista[y][x]) {
                    x -= distancia;
                    System.out.println("O robô sairá da pista com esse comando! Tente novamente!");

                    //Validação da bateria
                    if (bateria >= bateriaMinima){
                        System.out.println("\nBateria insuficiente. Carregue o robô!\n");
                        break;
                    }

                    continue;
                }
            }

            // Movimento pra trás
            if (comando.equals("TRAS")) {
                x -= distancia;

                // Validação do movimento na pista
                if (x < 0 || x >= pista[0].length || y < 0 || y >= pista.length || !pista[y][x]) {
                    x += distancia;
                    System.out.println("O robô sairá da pista com esse comando! Tente novamente!");

                    //Validação da bateria
                    if (bateria >= bateriaMinima){
                        System.out.println("\nBateria insuficiente. Carregue o robô!\n");
                        break;
                    }

                    continue;
                }
            }

            //Validação da bateria
            if (bateria >= bateriaMinima){
                System.out.println("\nBateria insuficiente. Carregue o robô!\n");
                break;
            }

            //Atualização da posição do robô e da bateria consumida até então
            System.out.println("Posição atual: (" + x + ", " + y +")");
            System.out.println("Bateria usada: " + bateria + " unidades.");

            //Mensagem para a linha de chegada
            if(x==23 && y==4){
                System.out.println("Parabéns! Você chegou ao final do trajeto!");
                break;
            }

        }
        //Posição final do robô e bateria consumida no trajeto
        System.out.println("\nPosição final do robô: (" + x + "," + y + ")");
        System.out.println("Bateria usada no trajeto: " + bateria + " unidades.\n");
        System.out.println("\nPrograma encerrado.");
        sc.close();
    }
}