import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.web.WebView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class JeeNeetApp extends Application {

    private int competencyScore = 0;
    private VBox contentArea;
    private Label scoreLabel;

    @Override
    public void start(Stage primaryStage) {
        // Main Window Setup
        BorderPane mainLayout = new BorderPane();
        mainLayout.setStyle("-fx-background-color: #ffffff;"); // Light Mode

        // --- SIDEBAR ---
        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #f8fafc; -fx-border-color: #e2e8f0; -fx-border-width: 0 1 0 0;");
        sidebar.setPrefWidth(220);

        Label title = new Label("JEE/NEET\nCONQUEROR");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        title.setTextFill(Color.web("#2563eb"));

        Button btnPreface = createMenuButton("📖 Preface");
        Button btnStudy = createMenuButton("📚 Study Material");
        Button btnQuest = createMenuButton("🎯 Challenger Quest");
        Button btnPerformance = createMenuButton("📈 Performance");
        
        // Sponsor Link
        Hyperlink sponsorLink = new Hyperlink("Sponsor: NCERT Official");
        sponsorLink.setOnAction(e -> getHostServices().showDocument("https://ncert.nic.in/"));

        sidebar.getChildren().addAll(title, new Separator(), btnPreface, btnStudy, btnQuest, btnPerformance, new Spacer(), sponsorLink);

        // --- CONTENT AREA ---
        contentArea = new VBox(20);
        contentArea.setPadding(new Insets(30));
        showPreface(); // Default view

        mainLayout.setLeft(sidebar);
        mainLayout.setCenter(contentArea);

        // --- BUTTON ACTIONS ---
        btnPreface.setOnAction(e -> showPreface());
        btnStudy.setOnAction(e -> showStudyMaterial());
        btnQuest.setOnAction(e -> showChallengerQuest());
        btnPerformance.setOnAction(e -> showPerformance());

        Scene scene = new Scene(mainLayout, 1000, 700);
        primaryStage.setTitle("Azure AI: JEE & NEET Aspirant Suite");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showPreface() {
        contentArea.getChildren().clear();
        Label head = new Label("Preface & Guide");
        head.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        
        TextFlow flow = new TextFlow(new Label("This application is designed for 2026 Aspirants.\n\n" +
            "1. Instructions: Use the Study Material tab to access chapters.\n" +
            "2. Challenger: Complete the quest to update your performance card.\n" +
            "3. Motivation: Links inside study material provide seminar videos."));
        
        contentArea.getChildren().addAll(head, flow);
    }

    private void showStudyMaterial() {
        contentArea.getChildren().clear();
        Label head = new Label("Essential Chapters (PDF Links)");
        head.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Button physicsPdf = new Button("Download Physics Ch-1");
        physicsPdf.setOnAction(e -> getHostServices().showDocument("https://ncert.nic.in/textbook/pdf/keph101.pdf"));
        
        Button seminarVideo = new Button("🎥 Watch AI Motivation Seminar");
        seminarVideo.setStyle("-fx-background-color: #000; -fx-text-fill: white;");
        seminarVideo.setOnAction(e -> getHostServices().showDocument("https://www.youtube.com/watch?v=2Lp8tU1F7f4"));

        contentArea.getChildren().addAll(head, physicsPdf, seminarVideo);
    }

    private void showChallengerQuest() {
        contentArea.getChildren().clear();
        Label head = new Label("🎯 Challenger Quest (Competency Based)");
        Label question = new Label("Q: If the velocity of light (c), Planck's constant (h) and G are taken as \nfundamental units, then the dimensions of mass will be?");
        
        TextField ansInput = new TextField();
        ansInput.setPromptText("Enter Answer or Dimension Formula");
        
        Button submit = new Button("Check Competency");
        submit.setOnAction(e -> {
            competencyScore += 20;
            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Answer recorded! Performance card updated.");
            a.show();
        });

        contentArea.getChildren().addAll(head, question, ansInput, submit);
    }

    private void showPerformance() {
        contentArea.getChildren().clear();
        Label head = new Label("Your Performance Card");
        head.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        
        Label scoreDisplay = new Label("Competency Level: " + competencyScore + "%");
        ProgressBar bar = new ProgressBar(competencyScore / 100.0);
        bar.setPrefWidth(300);

        contentArea.getChildren().addAll(head, scoreDisplay, bar);
    }

    private Button createMenuButton(String text) {
        Button b = new Button(text);
        b.setMaxWidth(Double.MAX_VALUE);
        b.setStyle("-fx-alignment: CENTER_LEFT; -fx-background-color: transparent; -fx-cursor: hand;");
        return b;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

// Helper class for sidebar spacing
class Spacer extends Region {
    public Spacer() {
        VBox.setVgrow(this, Priority.ALWAYS);
    }
}
