/**
 * Beschreiben Sie hier die Klasse TikTakToeGameLogic.
 *
 * @author Max & Ben
 * @version 1.1
 */
public class TikTakToeGameLogic
{
	public enum GameState {
    	WaitForOpponent,
    	YourTurn,
    	Loose,
    	Win,
    	Draw
	}

	private int[][] tiles;
	private int width;
	private int height;
	private int lengthToWin;
	private GameState gameState;
private TikTakToeStage tikTakToeStage;

	private int placedTileNum = 0;

	public TikTakToeGameLogic(int widthIN, int heightIN,int lengthToWinIN, boolean startingPlayerIN, TikTakToeStage tikTakToeStageIN){
    	width = widthIN;
    	height = heightIN;
    	lengthToWin = lengthToWinIN;
tikTakToeStage= tikTakToeStageIN;
    	if (startingPlayerIN){
        	gameState = GameState.YourTurn;
    	} else {
        	gameState = GameState.WaitForOpponent;
    	}

    	tiles = new int[width][height];
	}

	public boolean tryToPlacePice(int xIN, int yIN, boolean ownIN){
    	if ((0 <= xIN && xIN < width && 0 <= yIN && yIN < height) && (gameState == GameState.YourTurn && ownIN) || (gameState == GameState.WaitForOpponent && !ownIN)) {
        	if(tiles[xIN][yIN] == 0){
            	if(ownIN) tiles[xIN][yIN] = 1;
            	else tiles[xIN][yIN] = 2;

            	if(checkForWin(xIN, yIN)){
                	if (ownIN){
                    	gameState = GameState.Win;
tikTakToeStage.noteWin();
                	} else {
                    	gameState = GameState.Loose;
tikTakToeStage.noteLoose();
                	}
            	}
            	if(checkForDraw()){
                	gameState = GameState.Draw;
            	}
        	} else {return false;}
        	if (gameState == GameState.YourTurn){
            	gameState = GameState.WaitForOpponent;
        	} else if(gameState == GameState.WaitForOpponent){
            	gameState = GameState.YourTurn;
        	}

        	return true;
    	} else {return false;}
	}

	private boolean checkForWin(int xIN, int yIN){
    	if(tiles[xIN][yIN] != 0){
        	if (lengthToWin <= addRay(xIN+1,yIN,1,0,tiles[xIN][yIN])+1+addRay(xIN-1,yIN,-1,0,tiles[xIN][yIN])){
            	return true;
        	} else if (lengthToWin <= addRay(xIN+1,yIN+1,1,1,tiles[xIN][yIN])+1+addRay(xIN-1,yIN-1,-1,-1,tiles[xIN][yIN])){
            	return true;
        	} else if (lengthToWin <= addRay(xIN,yIN+1,0,1,tiles[xIN][yIN])+1+addRay(xIN,yIN-1,0,-1,tiles[xIN][yIN])){
            	return true;
        	}else if (lengthToWin <= addRay(xIN-1,yIN+1,-1,1,tiles[xIN][yIN])+1+addRay(xIN+1,yIN-1,1,-1,tiles[xIN][yIN])){
            	return true;
        	}
    	}
    	return false;
	}

	private int addRay(int xIN, int yIN,int deltaXIN, int deltaYIN, int typeIN){
    	if(tiles.length > xIN && -1 < xIN && tiles[0].length > yIN && -1 < yIN && tiles[xIN][yIN] == typeIN){
        	return 1+addRay(xIN+deltaXIN, yIN+deltaYIN, deltaXIN, deltaYIN, typeIN);
    	} else {return 0;}
	}

	private boolean checkForDraw(){
    	placedTileNum++;
    	if (placedTileNum >= (width * height)){
        	return true;
    	} else {return false;}
	}

	public int[][] getTiles(){
    	return tiles;
	}

	public GameState getGameState(){
    	return gameState;
	}

}


