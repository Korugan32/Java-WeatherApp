import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.IOException;

public class NewJFrame extends javax.swing.JFrame {

    public NewJFrame() throws ParseException {
        initComponents();
        setResizable(false);
    }

    private void initComponents() {

        textInput = new javax.swing.JTextField();
        lblShowStatus = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        lblShowCityCelcius = new javax.swing.JLabel();
        lblShowCityStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnSearch.setText("Ara");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnSearchActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        textInput.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N

        lblShowCityCelcius.setFont(new java.awt.Font("Arial Black", 1, 48)); // NOI18N

        lblShowCityStatus.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(textInput, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblShowStatus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblShowCityCelcius, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblShowCityStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(208, 208, 208))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(textInput))
                                .addGap(26, 26, 26)
                                .addComponent(lblShowStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblShowCityCelcius, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblShowCityStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(98, Short.MAX_VALUE))
        );

        pack();
    }

    public static void main(String args[]) {


        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public void run(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new NewJFrame().setVisible(true);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }


    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) throws IOException, ParseException {
        String text = textInput.getText();
        String buyukHarf = text.toLowerCase();
        String s = buyukHarf.substring(0, 1).toUpperCase() + buyukHarf.substring(1).toLowerCase();
        ApiConnection apiConnection = new ApiConnection();
        apiConnection.ConnectionUrl(s);
        lblShowCityCelcius.setText(apiConnection.getTemp() + "°C");
        lblShowCityStatus.setText(apiConnection.getStatus());
        switch (apiConnection.getStatus()) {
            case "Partly cloudy":
            case "Cloudy":
                ImageIcon icon = new ImageIcon("C:\\javademos\\weatherApp\\src\\assets\\cloudy.png");
                lblShowStatus.setIcon(icon);
                break;
            case "Light snow":
            case "Snow":
                icon = new ImageIcon("C:\\javademos\\weatherApp\\src\\assets\\snow.png");
                lblShowStatus.setIcon(icon);
                break;
            case "Sunny":
            case "Clear":
                icon = new ImageIcon("C:\\javademos\\weatherApp\\src\\assets\\clear.png");
                lblShowStatus.setIcon(icon);
                break;
            case "Light rain":
            case "Moderate rain":
            case "Heavy rain":
                icon = new ImageIcon("C:\\javademos\\weatherApp\\src\\assets\\rain.png");
                lblShowStatus.setIcon(icon);
                break;
        }
    }

    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel lblShowCityCelcius;
    private javax.swing.JLabel lblShowCityStatus;
    private javax.swing.JLabel lblShowStatus;
    private javax.swing.JTextField textInput;

}

