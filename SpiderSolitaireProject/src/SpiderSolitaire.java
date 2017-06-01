import javax.swing.*;

/**
 **===============================
 ** Created by Kaś on 19.01.2017.
 **===============================
 */
public class SpiderSolitaire {
    public static void main(String[] args) {
        JFrame frame = new JFrame ("Spider Solitaire");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GUI giu = new GUI();
        frame.setContentPane(giu.createGUI(frame));

        frame.setSize(940,700);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
