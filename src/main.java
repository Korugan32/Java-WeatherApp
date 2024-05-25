import org.json.simple.parser.ParseException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new NewJFrame().setVisible(true);
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        });
    }
}
