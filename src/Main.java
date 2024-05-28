import org.json.simple.parser.ParseException;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Swing uygulamasını başlat
        SwingUtilities.invokeLater(Main::run);
    }

    private static void run() {
        try {
            // Ana ekranı görünür yap
            new AppScreen().setVisible(true);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
