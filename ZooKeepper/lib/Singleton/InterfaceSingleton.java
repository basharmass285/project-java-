package ZooKeepper.lib.Singleton;

import javax.swing.*;

public class InterfaceSingleton {
    private static JFrame instance;

    public static JFrame getWindow() {
        if (instance == null) {
            instance = createWindow();
        }
        return instance;
    }

    private static JFrame createWindow() {
        JFrame frame = new JFrame("Singleton Swing Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        return frame;
    }
}