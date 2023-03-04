import javax.swing.*;

public class MainGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("FIO");
        frame.setSize(600, 300);

        frame.add(new FIO().getPanel());


        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
