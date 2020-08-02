import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Profile profile = new Profile("Trainer", 500);
        Stock s = new Stock();
        s.prettyPrintHistory();

        JFrame frame = new JFrame("Bust a Bit");
        frame.setSize(Display.WIDTH, Display.HEIGHT);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Display display = new Display(frame);
        Game game = new Game();
        Control control = new Control(display, game);
        display.setControl(control);

        frame.add(display);
        frame.setVisible(true);
    }
}
