import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL; // URL sınıfını içe aktar
import org.json.simple.parser.ParseException;

public class AppScreen extends JFrame {
    private JTextField cityField;
    private JLabel cityLabel;
    private JLabel temperatureLabel;
    private JLabel precipitationLabel;
    private JLabel humidityLabel;
    private JLabel windLabel;
    private JLabel weatherIconLabel;

    // Uygulama ekranı oluşturucu
    public AppScreen() throws ParseException {
        setTitle("Hava Durumu Uygulaması");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Uygulama ikonu ayarla
        String iconPath = "C:\\Users\\tahab\\IdeaProjects\\Java-WeatherApp\\src\\icon\\app_icon.png";
        setIconImage(new ImageIcon(iconPath).getImage());

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setLocationRelativeTo(null); // Pencereyi ortala
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // Şehir etiketi
        cityLabel = new JLabel("Şehir:");
        cityLabel.setBounds(10, 20, 140, 25);
        panel.add(cityLabel);

        // Sıcaklık etiketi
        temperatureLabel = new JLabel("Sıcaklık:");
        temperatureLabel.setBounds(10, 50, 300, 25);
        panel.add(temperatureLabel);

        // Yağış etiketi
        precipitationLabel = new JLabel("Yağış:");
        precipitationLabel.setBounds(10, 80, 300, 25);
        panel.add(precipitationLabel);

        // Nem etiketi
        humidityLabel = new JLabel("Nem:");
        humidityLabel.setBounds(10, 110, 300, 25);
        panel.add(humidityLabel);

        // Rüzgar etiketi
        windLabel = new JLabel("Rüzgar:");
        windLabel.setBounds(10, 140, 300, 25);
        panel.add(windLabel);

        // Şehir metin alanı
        cityField = new JTextField(20);
        cityField.setBounds(150, 20, 165, 25);
        panel.add(cityField);

        // Enter tuşuna basıldığında hava durumunu getir
        cityField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String city = cityField.getText();
                    getWeather(city);
                }
            }
        });

        // Hava durumu getir butonu
        JButton getWeatherButton = new JButton("Hava Durumu Getir");
        getWeatherButton.setBounds(100, 170, 165, 25);
        panel.add(getWeatherButton);

        // Hava durumu ikonu etiketi
        weatherIconLabel = new JLabel();
        weatherIconLabel.setBounds(150, 200, 180, 100); // Y koordini butonun altına gelecek şekilde ayarlandı
        panel.add(weatherIconLabel);

        // Butona tıklandığında hava durumunu getir
        getWeatherButton.addActionListener(e -> {
            String city = cityField.getText();
            getWeather(city);
        });
    }

    // Hava durumu bilgisini API'den getir
    private void getWeather(String city) {
        ApiConnection apiConnection = new ApiConnection();
        try {
            apiConnection.connect(city);
            cityLabel.setText("Şehir: " + city);
            temperatureLabel.setText("Sıcaklık: " + apiConnection.getTemp() + " °C");
            precipitationLabel.setText("Yağış: " + apiConnection.getPrecipitation() + " mm");
            humidityLabel.setText("Nem: " + apiConnection.getHumidity() + " %");
            windLabel.setText("Rüzgar: " + apiConnection.getWindSpeed() + " kph " + apiConnection.getWindDirection());

            // İkon URL'sini al ve etikete ayarla
            String iconUrl = apiConnection.getIconUrl();
            if (iconUrl != null) {
                ImageIcon icon = new ImageIcon(new URL(iconUrl));
                Image img = icon.getImage();
                Image scaledImg = img.getScaledInstance(128, 128, Image.SCALE_SMOOTH); // İkonu yeniden boyutlandırma
                weatherIconLabel.setIcon(new ImageIcon(scaledImg));
            } else {
                weatherIconLabel.setIcon(null);
            }
        } catch (IOException | ParseException e) {
            JOptionPane.showMessageDialog(null, "Hava durumu verileri alınamadı.");
        }
    }
}
