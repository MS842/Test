/**
 * Beschreiben Sie hier die Klasse main.
 * 
 * @author Tilo 
 * @version 1.0
 */
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.text.*;
import javafx.scene.shape.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.scene.image.*;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import java.io.FileInputStream;
import java.io.File;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.scene.paint.*;
public class Main extends Application //erbt von JavaFx Klasse aus Bibliothek
{
    // Ggf. hier Instanzvariablen deklarieren
    public int seitenlaenge;
    public int publicTmpI;
    int serverChoiceInt=0;
    TikTakToeStage tikTakToeStage = new TikTakToeStage (this);
    Stage tikTakToeShow = new Stage();
    Stage mainStage = new Stage();
    Stage question = new Stage();
    Group menueGroup = new Group();
    Scene menueScene = new Scene(menueGroup);
    Group enterPortGroup= new Group();
    Scene enterPortScene = new Scene(enterPortGroup);
    Group serverChoiceGroup = new Group();
    Scene serverChoiceScene = new Scene(serverChoiceGroup);

    Label [] cross = new Label [seitenlaenge*seitenlaenge];
    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

    ServerMain host;
    ClientMain client;
    /**
     * Init-Methode für die JavaFX-Applikation. 
     * In dieser Methode werden Konstruktor-typische Initialisierungen vorgenommen. 
     * Sie wird einmalig nach dem Launch der JavaFX-Applikation und nicht im JavaFX-Thread ausgeführt. 
     * Deshalb DARF NICHT in dieser Methode die GUI erzeugt werden. 
     */
    @Override
    public void init() throws Exception 
    {

        // Hier die Instanzvariablen initialisieren

    }

    /**
     * Start-Methode für die JavaFX-Applikation. 
     * In dieser Methode wird insbesondere die grafische Benutzeroberfläche erzeugt. 
     * Sie wird einmalig nach der Init()-Methode und im JavaFX-Thread ausgeführt.
     * Parameter Stage ist grafische Oberfläche in der die Applikaton ausgeführt wird.
     * Eine Stage (engl. Bühne) kann mehrere Szenen darstellen
     * Die Stage wird automatisch von JavaFx erzeugt
     **/
    @Override //Überschreiben der geerbten Methode von Application
    public void start(Stage primaryStage) throws Exception 
    {
        //Gestaltung des Hauptmenüs
        Text title = new Text(getScreenWidth()/2, getScreenHeight()/2, "Spielesammlungs Simulator"); //Objekt Text bei Koordinaten erzeugen
        title.setFont(new Font("Comic Sans MS", getScreenHeight()/22)); //Schriftart ändern

        mainStage.initStyle(StageStyle.DECORATED); //Definiert den Stil des Fensters 
        mainStage.setResizable(true);
        mainStage.setMaximized(true);
        mainStage.initModality(Modality.WINDOW_MODAL); //Fenster das verbietet andere Fenster zu benutzen
        mainStage.getIcons().add(new Image(Main.class.getResourceAsStream("pic/icon_main.png"))); 

        GridPane menueGrid = new GridPane();

        //Gestaltung des Buttons für Tictactoe
        FileInputStream input = new FileInputStream("pic/menueButton_tiktaktoe.png");
        Image image = new Image(input, getScreenWidth()/8, getScreenHeight()/9*5, true, true);
        ImageView imageView = new ImageView(image); 

        //Erzeugung des Knopfes zur Auswahl von Tictactoe
        Button buttontic = new Button("", imageView);

        menueGrid.setConstraints(title, 1, 1);
        menueGrid.getChildren().add(title);
        buttontic.setMinSize(getScreenWidth()/3,getScreenHeight()/3);
        Button GameButtons[]= new Button[6];
        for(int i = 0; i<GameButtons.length; i++){
            if(i==0){
                GameButtons[0]= buttontic;
            }
            else{
                GameButtons[i] = new Button("Platzhalter Nr."+i);
                GameButtons[i].setMinSize(getScreenWidth()/3,getScreenHeight()/3);
            }
            menueGrid.setConstraints(GameButtons[i], MathHelper.gridNrX(i, 3), MathHelper.MenueGridNrY(i, 3));
            menueGrid.getChildren().add(GameButtons[i]);
        }
        for(int i=0; i<3; i++){
            menueGrid.getColumnConstraints().add(new ColumnConstraints(getScreenWidth()/3));
        }
        for(int i=0; i<3; i++){
            menueGrid.getRowConstraints().add(new RowConstraints(getScreenHeight()/3));
        }        
        menueGrid.setAlignment(Pos.CENTER);

        //Erzeugung des Tictactoe Interfaces dass bei Knopfdruck angezeigt wird

        buttontic.setOnAction(value ->  {
                tikTakToeStage.getStage();
            }
        );

        //Erzeugung der Hauptmenüszene
        menueGroup.getChildren().add(menueGrid);
        //menueGroup.getChildren().add(buttontic);

        mainStage.setScene(menueScene);

        //Erzeugung des Knopfes zum akzeptieren der AGB
        Button termsButton = new Button("Ich bin mit den Nutzungsbedingungen einverstanden und älter als 12.");
        termsButton.setAlignment(Pos.CENTER);
        termsButton.setMinSize(getScreenWidth()/3,getScreenHeight()/3);
        //termsButton.relocate(getScreenWidth()*2/6, getScreenHeight()*2/6);
        termsButton.setMaxSize(getScreenWidth()/3,getScreenHeight()/3);
        termsButton.setOnAction(value -> {
                question.setScene(serverChoiceScene); // Wenn Knopf geklickt wird Popup mit Bedinungen geschlossen
            });
        Scene termsScene = new Scene(termsButton);

        //Erzeugung des Knopfes zum Auswhählen des Hosts/Ports?
        ChoiceBox serverChoice = new ChoiceBox();
        serverChoice.getItems().add("Wählen Sie die Art der Verbindung aus");
        serverChoice.getItems().add("1: Ein lokales Multiplayer Spiel starten");
        serverChoice.getItems().add("2: Ein online Multiplayer Spiel hosten");
        serverChoice.getItems().add("3: Einem online Multiplayer Spiel beitreten");
        serverChoice.setMinSize(getScreenWidth()/3,getScreenHeight()/3);
        serverChoice.setMaxSize(getScreenWidth()/3,getScreenHeight()/3);        
        //serverChoice.relocate(getScreenWidth()*2/6, getScreenHeight()*2/6);
        serverChoice.getSelectionModel().select(0);    //Nullte Option als Titel setzen

        serverChoice.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle (ActionEvent event){

                    serverChoiceInt =serverChoice.getSelectionModel().getSelectedIndex();
                    if(serverChoiceInt==1){
                        //Anweisung für lokales Spiel:
                        question.close();
                        mainStage.show();
                    }else if(serverChoiceInt==2||serverChoiceInt==3){
                        //Anweisung für online Host || Anweisung für online beitreten
                        question.setScene(enterPortScene);
                    }else{
                        System.out.println("Error: Keine Verbindungsart ausgewählt.");
                        question.close();
                    }                    
                }
            });
        serverChoiceGroup.getChildren().add(serverChoice);

        
        TextField PortInput = new TextField();
        PortInput.setOnKeyPressed(new EventHandler<KeyEvent>() {

                @Override
                public void handle(KeyEvent event) {
                    if(event.getCode().equals(KeyCode.ENTER)) {
                        question.close();
                        int intPortInput;
                        try {
                            intPortInput = Integer.parseInt(PortInput.getText());
                            if(serverChoiceInt==2){
                                host = new ServerMain(intPortInput);
                                question.close();
                                mainStage.show(); 
                            }
                            else if(serverChoiceInt==3){
                                client = new ClientMain(intPortInput);
                                question.close();
                                mainStage.show(); 
                            }
                        }
                        catch (NumberFormatException e) {
                            System.out.println("Error: Keinen gültigen Port ausgewählt.");
                            question.close();
                        }
                    }
                }
            });
        Button enterPortLabel = new Button("Portnummer eingeben: Nur ganzzahlige Werte erlaubt", PortInput);
        enterPortLabel.setMinSize(getScreenWidth()/3,getScreenHeight()/3);
        enterPortLabel.setMaxSize(getScreenWidth()/3,getScreenHeight()/3);
        enterPortLabel.setAlignment(Pos.CENTER);
        enterPortGroup.getChildren().add(enterPortLabel);

        
        question.initOwner(primaryStage); //Gehört dem Hauptfenster
        question.initModality(Modality.WINDOW_MODAL); //Fenster das verbietet andere Fenster zu benutzen
        question.initStyle(StageStyle.TRANSPARENT); //Transparentes Fenster
        question.centerOnScreen();
        question.setWidth(getScreenWidth()/3);
        question.setHeight(getScreenHeight()/3);
        question.getIcons().add(new Image(Main.class.getResourceAsStream("pic/icon_attention.png")));

        mainStage.setTitle("Spielebibliothek"); //Titel des Fensters bestimmen
        mainStage.setWidth(getScreenWidth());
        mainStage.setHeight(getScreenHeight());

        question.setScene(termsScene);
        question.show();        
    }

    /**
     * Main Methode
     * Intiieren von JavaFx, optional
     * Darf nur einmal aufgrufen werden
     **/
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }    

    /**
     * Stop-Methode für die JavaFX-Applikation. 
     * In dieser Methode können "Aufräumarbeiten" vorgenommen werden. 
     * Sie wird einmalig beim Beenden der Applikation und im JavaFX-Thread ausgeführt. 
     */
    public void stop() throws Exception 
    {
    } 

    /*public void updateTikTakToeShow(){
    tikTakToeShow.close();
    tikTakToeShow.show();
    }
    public void closeTikTakToeShow(){
    tikTakToeShow.close();
    }
    public void showTikTakToeShow(){
    tikTakToeShow.show();
    }    */
    public void backToMenueScene(){
        mainStage.setScene(menueScene);
    }

    public double getScreenHeight(){
        return primaryScreenBounds.getHeight();
    }

    public double getScreenWidth(){
        return primaryScreenBounds.getWidth();
    }    
}
/*
for(int i =0; i<buttons.length; i++){
if(i<buttons.length/2){
circle [i]=new Circle();
circle [i].setRadius(knopfgroesse/3);
buttons [i] = new Button("",circle[i]);
}   
else{
cross[i]=new Label("X");
cross[i].setFont(new Font("Comic Sans MS", knopfgroesse/1.5));
buttons [i] = new Button("",cross[i]);
}
buttons [i].setPrefSize(knopfgroesse, knopfgroesse);
buttons [i].setMinSize(knopfgroesse, knopfgroesse);
buttons [i].setMaxSize(knopfgroesse, knopfgroesse);
buttons [i].setAlignment(Pos.CENTER);
buttons[i].setOnAction(new EventTTTKnopf(i, seitenlaenge, main));
p.setConstraints(buttons[i], MathHelper.gridNrX(i, seitenlaenge), MathHelper.gridNrY(i, seitenlaenge));
p.getChildren().add(buttons[i]);          
}
 */
