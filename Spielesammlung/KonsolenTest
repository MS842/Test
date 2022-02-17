/**
 * Beschreiben Sie hier die Klasse KonsolenTest.
 *
 * @author Max
 * @version 0.4
 */
import java.util.Scanner;

public class KonsolenTest
{
	private Scanner scanner = new Scanner(System.in);
	private TikTakToeGameLogic game;

	private boolean turn = false;
	private boolean criticalError = false;

	private String startText = "Hallo";
	private String boardText = "Current board:";
	private String waitForOpponentText = "Player2 enter your coordinates: x,y";
	private String yourTurnText = "Player1 enter your coordinates: x,y";
	private String drawText = "DRAW";
	private String looseText = "Player2 WINS";
	private String winText = "Player1 WINS";

	public void start(){
    	game = new TikTakToeGameLogic(3,3,3,true, null);

    	System.out.println(startText);
   	 
    	output();

    	while(true){
        	String s = scanner.next();
        	if(s.equals("q") || criticalError) {
            	game = null;
            	break;
        	}else{
            	gameCycle(s);
        	}
    	}

	}

	private void gameCycle(String sIN){
    	interpred(sIN);
    	output();
	}

	private void interpred(String sIN){
    	int x = 0,y = 0;
    	boolean inputRight = false;

    	for(int i = 0; i < sIN.length(); i++){
        	if(sIN.charAt(i) == ','){
            	x = Integer.parseInt(sIN.substring(0,i));
            	y = Integer.parseInt(sIN.substring(i+1,sIN.length()));
            	inputRight = true;
        	}
    	}
    	if(inputRight){
        	if(!game.tryToPlacePice(x,y,getTurn())) error("Placement", false);
    	}else{
        	error("Input", false);
    	}
	}

	private void output(){
    	System.out.println(boardText);
    	//outputs the board
    	int[][] board = game.getTiles();

    	for(int y = 0; y < board[0].length; y++){
        	String out = "";
        	for(int x = 0; x < board.length; x++){
            	out += board[x][y];
        	}

        	System.out.println(out);
    	}

    	//outputs Gamestate
    	TikTakToeGameLogic.GameState gameState = game.getGameState();
    	String gameStateOutput = "";
    	if(gameState == TikTakToeGameLogic.GameState.WaitForOpponent){
        	gameStateOutput = waitForOpponentText;
    	}
    	else if(gameState == TikTakToeGameLogic.GameState.YourTurn){
        	gameStateOutput = yourTurnText;
    	}
    	else if(gameState == TikTakToeGameLogic.GameState.Draw){
        	gameStateOutput = drawText;
    	}
    	else if(gameState == TikTakToeGameLogic.GameState.Loose){
        	gameStateOutput = looseText;
    	}
    	else if(gameState == TikTakToeGameLogic.GameState.Win){
        	gameStateOutput = winText;
    	}
   	 
    	System.out.println(gameStateOutput);
	}

	private boolean getTurn(){
    	TikTakToeGameLogic.GameState gameState = game.getGameState();
    	if(gameState == TikTakToeGameLogic.GameState.WaitForOpponent){
        	return false;
    	}
    	else if(gameState == TikTakToeGameLogic.GameState.YourTurn){
        	return true;
    	}
    	error("asked for turn after gameend", true);
    	return false;
	}

	private void error(String sIN, boolean criticalErrorIN){
    	criticalError = criticalErrorIN;
    	System.out.println("Error: " + sIN);
	}
}


