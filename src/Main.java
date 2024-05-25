import org.json.simple.parser.ParseException;
import javax.swing.*;

public class Main {
    public static void main(String[] args){
            SwingUtilities.invokeLater(Main::run);
    }
    private static void run() {
        try {
            new NewJFrame().setVisible(true);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
