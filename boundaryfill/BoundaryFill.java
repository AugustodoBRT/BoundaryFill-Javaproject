package boundaryfill;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import javax.swing.Timer;

public class BoundaryFill extends Canvas {

    private int[][] matriz;
    private int tamanhoCelula = 30; // Tamanho de cada célula
    private int linhas = 15; // Número de linhas
    private int colunas = 15; // Número de colunas
    private Queue<Point> filaPreenchimento; // Fila para animar o preenchimento
    private int corPreenchimento = 2; // Cor do preenchimento (amarelo)

    public BoundaryFill() {
        filaPreenchimento = new LinkedList<>();
        gerarMatriz();

        // Adiciona o evento de clique do mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int linha = e.getY() / tamanhoCelula;
                int coluna = e.getX() / tamanhoCelula;

                if (linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas && matriz[linha][coluna] == 0) {
                    filaPreenchimento.clear();
                    filaPreenchimento.add(new Point(linha, coluna));
                    iniciarPreenchimento();
                }
            }
        });
    }

    // Gera a matriz com células brancas e pretas
    private void gerarMatriz() {
        matriz = new int[linhas][colunas];
        Random random = new Random();
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                matriz[i][j] = random.nextInt(3) == 0 ? 1 : 0; 
            }
        }
    }

    // Boundary fill
    private void iniciarPreenchimento() {
        Timer temporizador = new Timer(80, e -> {
            if (filaPreenchimento.isEmpty()) {
                ((Timer) e.getSource()).stop();
                return;
            }

            Point ponto = filaPreenchimento.poll();
            int linha = ponto.x;
            int coluna = ponto.y;

            if (linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas && matriz[linha][coluna] == 0) {
                matriz[linha][coluna] = corPreenchimento;
                repaint();

             
                filaPreenchimento.add(new Point(linha + 1, coluna));
                filaPreenchimento.add(new Point(linha - 1, coluna));
                filaPreenchimento.add(new Point(linha, coluna + 1));
                filaPreenchimento.add(new Point(linha, coluna - 1));
            }
        });
        temporizador.start();
    }

    // Desenha a matriz na tela
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (matriz[i][j] == 0) {
                    g.setColor(Color.WHITE); // Célula branca
                } else if (matriz[i][j] == 1) {
                    g.setColor(Color.BLACK); // Célula preta
                } else if (matriz[i][j] == 2) {
                    g.setColor(Color.YELLOW); // Célula preenchida
                }
                g.fillRect(j * tamanhoCelula + 25, i * tamanhoCelula + 25, tamanhoCelula, tamanhoCelula);
                g.setColor(Color.GRAY);
                g.drawRect(j * tamanhoCelula + 25, i * tamanhoCelula + 25, tamanhoCelula, tamanhoCelula);
            }
        }
    }
}
