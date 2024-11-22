package boundaryfill;

import java.awt.Color;

public class Quadrado {
    int lado; 
    Color cor; 

    public Quadrado(Color cor) {
        this.lado = 30; 
        this.cor = cor;
    }

    
    public int getLado() {
        return lado;
    }

    public Color getCor() {
        return cor;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }
}
