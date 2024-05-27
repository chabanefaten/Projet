package javafxLoginrecCrudForm;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class FxmlDocumentController implements Initializable {

    @FXML
    private BorderPane Login_form;

    @FXML
    private BorderPane signup_form;

    @FXML
    private Button su_CreateAccountbtn;

    @FXML
    private Button su_loginAccountbtn;

    @FXML
    private Button su_loginbtn;

    @FXML
    private PasswordField su_password;

    @FXML
    private Button su_signupbtn;

    @FXML
    private TextField su_username;

    @FXML
    private PasswordField su_password_signup;

    @FXML
    private TextField su_username_signup;

    private boolean showLogin = true;

    public void toggleForm() {
        showLogin = !showLogin;
        Login_form.setVisible(showLogin);
        signup_form.setVisible(!showLogin);
    }

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public Connection connectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/produitcrud", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void loginAccount() {
        String Sql = "SELECT username, password FROM admin WHERE username = ? AND password = ?";

        connect = connectDB();
        try {
            Alert alert;
            if (su_username.getText().isEmpty() || su_password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                prepare = connect.prepareStatement(Sql);
                prepare.setString(1, su_username.getText());
                prepare.setString(2, su_password.getText());

                result = prepare.executeQuery();

                if (result.next()) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login");
                    alert.showAndWait();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect username/password");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registerAccount() {
        String Sql = "INSERT INTO admin (username, password) VALUES (?, ?)";

        connect = connectDB();
        try {
            Alert alert;
            if (su_username_signup.getText().isEmpty() || su_password_signup.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                prepare = connect.prepareStatement(Sql);
                prepare.setString(1, su_username_signup.getText());
                prepare.setString(2, su_password_signup.getText());

                int result = prepare.executeUpdate();
                if (result > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Registration successful!");
                    alert.showAndWait();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Registration failed.");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization if needed
    }
}
