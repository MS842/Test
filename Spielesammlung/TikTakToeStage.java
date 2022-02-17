/**
 * Beschreiben Sie hier die Klasse TikTakToeStage.
 *
 * @author Tilo
 * @version 0.2
 */
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.event.*;
import java.io.FileInputStream;
import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.text.*;
/*import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.PopupWindow;
import javafx.stage.Popup;
import javafx.stage.Modality;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import java.io.FileInputStream;
import java.io.File;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;   
import javafx.scene.layout.GridPane;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.stage.Screen;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
 */

public class TikTakToeStage
{
	private int seitenlaenge;
	int knopfgroesse;
	private Main main;
	Button [] buttons;
	Group playGroup;
	GridPane playGrid;
	Scene mainScene;
	private Button errorBox = new Button(" Keinen Fehler entdeckt.");

	TikTakToeManager gameManager= new TikTakToeManager(this);
	public TikTakToeStage(Main mainIN){
    	main=mainIN;
	}

	public void init() throws Exception
	{

    	// Hier die Instanzvariablen initialisieren

	}

	public void start(Stage primaryStage) throws Exception
	{

	}

	public void getStage(){
    	/*stage.initModality(Modality.WINDOW_MODAL); //Fenster das verbietet andere Fenster zu benutzen
    	stage.setResizable(true);
    	stage.initStyle(StageStyle.DECORATED);
    	stage.setWidth(main.getScreenWidth());
    	stage.setHeight(main.getScreenHeight());
     	*/
    	playGroup = new Group();
    	playGrid = new GridPane();
    	mainScene =  new Scene (playGroup);

    	Button backToMenue = new Button("Zurück zum Menue");
    	backToMenue.setMinWidth(main.getScreenWidth()/6);
    	backToMenue.setMinHeight(15);
    	backToMenue.setOnAction(new EventHandler<ActionEvent>() {
            	@Override
            	public void handle (ActionEvent event){
                	main.backToMenueScene();
            	}
        	});   

    	Button endGame = new Button("Programm beenden");
    	endGame.setMinWidth(main.getScreenWidth()/6);
    	endGame.setMinHeight(15);
    	endGame.setOnAction(new EventHandler<ActionEvent>() {
            	@Override
            	public void handle (ActionEvent event){
                	main.mainStage.close();
            	}
        	});    

    	//Button errorBox = new Button(" Keinen Fehler entdeckt.");
    	errorBox.setMinWidth(main.getScreenWidth()/3*2);
    	errorBox.setMinHeight(15);
    	HBox menueBar = new HBox(backToMenue, endGame, errorBox);
    	playGroup.getChildren().add(menueBar);       	 

    	ChoiceBox chooseSize = new ChoiceBox();
    	chooseSize.getItems().add("Wählen Sie die Seitenlaenge aus");
    	chooseSize.setMinSize(main.getScreenWidth()/3,main.getScreenHeight()/3);
    	chooseSize.setMaxSize(main.getScreenWidth()/3,main.getScreenHeight()/3);   	 
    	chooseSize.relocate(main.getScreenWidth()*2/6, main.getScreenHeight()*2/6);
    	for(int i=0; i<97; i++){
        	chooseSize.getItems().add((i+3)+" x "+(i+3)+"  Kästchen");
    	}
    	chooseSize.getSelectionModel().select(0);	//Nullte Option als Titel setzen
    	chooseSize.setOnAction(new EventHandler<ActionEvent>() {
            	@Override
            	public void handle (ActionEvent event){
                	seitenlaenge=chooseSize.getSelectionModel().getSelectedIndex()+2;
                	knopfgroesse=(int)(main.getScreenHeight()-200)/seitenlaenge;
                	gameManager.startNewGame((seitenlaenge));

                	buttons = new Button[seitenlaenge*seitenlaenge];
                	for(int i =0; i<buttons.length; i++){
                    	buttons [i] = new Button();
                    	buttons [i].setPrefSize(knopfgroesse, knopfgroesse);
                    	buttons [i].setMinSize(knopfgroesse, knopfgroesse);
                    	buttons [i].setMaxSize(knopfgroesse, knopfgroesse);
                    	buttons[i].setOnAction(new EventTTTKnopf(i, seitenlaenge, main.tikTakToeStage));
                    	playGrid.setConstraints(buttons[i], MathHelper.gridNrX(i, seitenlaenge), MathHelper.gridNrY(i, seitenlaenge));
                    	playGrid.getChildren().add(buttons[i]);
                	}
                	playGrid.setAlignment(Pos.CENTER);
                	playGrid.relocate(main.getScreenWidth()/2-(seitenlaenge*knopfgroesse)/2, main.getScreenHeight()/2-(seitenlaenge*knopfgroesse)/2);  	 
                	playGroup.getChildren().add(playGrid);              	 
                	main.mainStage.setScene(mainScene);
            	}
        	});

    	Scene choiceScene = new Scene(chooseSize);
    	main.mainStage.setScene(choiceScene);
	}

	public void updateTile(int buttonNumIN, int playerNumIN){
    	if(playerNumIN==1){
        	Text circle= new Text("○");
        	//buttons[buttonNumIN]= new Button("Gedrückt");
        	buttons[buttonNumIN].setShape(circle);   	 
    	}
    	else if(playerNumIN==2){
        	Text cross = new Text("X");
        	//cross[buttonNumIN].setFont(new Font("Comic Sans MS", tikTakToeStage.getKnopfgroesse()/1.5));
        	buttons[buttonNumIN].setShape(cross);    	 
    	}
    	//p.setConstraints(buttons[buttonNumIN], MathHelper.gridNrX(buttonNumIN, seitenlaenge), MathHelper.gridNrY(buttonNumIN, seitenlaenge));
    	//p.getChildren().add(buttons[buttonNumIN]);
	}

	public void buttonPressed(int x, int y){
    	gameManager.inputButton(x,y,this);
	}

	public void tikTakToeError(String error){
    	errorBox.setText(error);
	}

	public void noteWin(){
    	Label note = new Label("Glückwunsch: Du hast gewonnen! Du wusstest doch eh schon, dass du unschlagbar bist.");
    	note.relocate(100, 60);
    	playGroup.getChildren().add(note);
	}

	public void noteLoose(){
    	Label note = new Label("Du hast verloren. Schade, aber vielleicht klappts ja beim nächsten mal...");
    	note.relocate(100, 60);
    	playGroup.getChildren().add(note);
	}    

}


