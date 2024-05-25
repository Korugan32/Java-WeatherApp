import org.json.simple.parser.ParseException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class NewJFrame extends JFrame {

    private JTextField textInput;
    private JLabel lblShowStatus;
    private JButton btnSearch;
    private JLabel lblShowCityCelcius;
    private JLabel lblShowCityStatus;

    public NewJFrame() throws ParseException {
        initComponents();
        setResizable(false);
    }

    private void initComponents() {

        textInput = new JTextField();
        lblShowStatus = new JLabel();
        btnSearch = new JButton();
        lblShowCityCelcius = new JLabel();
        lblShowCityStatus = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        btnSearch.setText("Ara");
        btnSearch.addActionListener(evt -> {
            try {
                btnSearchActionPerformed();
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        });

        textInput.setFont(new Font("Arial", Font.BOLD, 36));
        lblShowCityCelcius.setFont(new Font("Arial Black", Font.BOLD, 48));
        lblShowCityStatus.setFont(new Font("Segoe UI Black", Font.PLAIN, 36));

        createLayout();

        pack();
    }

    private void createLayout() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(textInput, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblShowStatus, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblShowCityCelcius, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblShowCityStatus, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(208, 208, 208))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnSearch, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(textInput))
                                .addGap(26, 26, 26)
                                .addComponent(lblShowStatus, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblShowCityCelcius, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblShowCityStatus, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(98, Short.MAX_VALUE))
        );
    }

    private void btnSearchActionPerformed() throws IOException, ParseException {
        String text = textInput.getText().toLowerCase();
        String capitalizedText = text.substring(0, 1).toUpperCase() + text.substring(1);
        ApiConnection apiConnection = new ApiConnection();
        apiConnection.connect(capitalizedText);
        lblShowCityCelcius.setText(apiConnection.getTemp() + "°C");
        lblShowCityStatus.setText(apiConnection.getStatus());
        updateWeatherIcon(apiConnection.getStatus());
    }

    private void updateWeatherIcon(String status) {
        String iconPath = "";
        switch (status) {
            case "Partly cloudy":
            case "Cloudy":
                iconPath = "C:\\javademos\\weatherApp\\src\\assets\\cloudy.png";
                break;
            case "Light snow":
            case "Snow":
                iconPath = "C:\\javademos\\weatherApp\\src\\assets\\snow.png";
                break;
            case "Sunny":
            case "Clear":
                iconPath = "C:\\javademos\\weatherApp\\src\\assets\\clear.png";
                break;
            case "Light rain":
            case "Moderate rain":
            case "Heavy rain":
                iconPath = "C:\\javademos\\weatherApp\\src\\assets\\rain.png";
                break;
        }
        lblShowStatus.setIcon(new ImageIcon(iconPath));
    }
}
