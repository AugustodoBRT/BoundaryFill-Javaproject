package boundaryfill;

import javax.swing.JFrame;

public class Base extends JFrame {

    public static void main(String[] args) {
        Base base = new Base();
    }

    public Base() {
        initGui();
    }

    private void initGui() {
        this.setTitle("BoundaryFill");
        setSize(600, 600); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BoundaryFill boundaryFill = new BoundaryFill();
        this.add(boundaryFill);

        setVisible(true);
    }
}
