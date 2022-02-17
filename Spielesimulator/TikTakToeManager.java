
/**
 * Beschreiben Sie hier die Klasse TikTakToeManager.
 * 
 * @author Max und Tilo
 * @version 0.3
 */
public abstract class TikTakToeManager
{
    protected TikTakToeGameLogic game;
    protected int width;
    
    protected boolean gameInput = true;
    protected TikTakToeStage tikTakToeStage;
    protected boolean criticalError;
    
    public TikTakToeManager(TikTakToeStage tikTakToeStageIN){
        tikTakToeStage=tikTakToeStageIN;
    }
    
    public abstract void startNewGame(int seitenlaengeIN);

    public abstract void inputButton(int xIN, int yIN, TikTakToeStage tikTakToeStageIN);

    protected boolean getTurn(){ //del
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

    protected void error(String sIN, boolean criticalErrorIN){
        criticalError = criticalErrorIN;
        tikTakToeStage.tikTakToeError("Error: " + sIN);
        //System.out.println("Error: " + sIN);
    }  
       

}
