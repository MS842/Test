
/**
 * Beschreiben Sie hier die Klasse TikTakToeManagerHoste.
 * 
 * @author Max
 * @version 0.1
 */
public class TikTakToeManagerHoste extends TikTakToeManager
{
    
    public TikTakToeManagerHoste(TikTakToeStage tikTakToeStageIN){
        super(tikTakToeStageIN);
    }
    
    public void startNewGame(int seitenlaengeIN){
        if(gameInput){
            width = seitenlaengeIN;
            
            int lengthToWin;
            if (seitenlaengeIN ==3){
                lengthToWin=3;
            }
            else {
                lengthToWin=4;
            }
            game = new TikTakToeGameLogic(seitenlaengeIN, seitenlaengeIN,lengthToWin, true, tikTakToeStage);
            //TiktokProtokol.newTikTakToeGameLogic(seitenlaengeIN, seitenlaengeIN,lengthToWin, false, tikTakToeStage);
        }
    }

    public void inputButton(int xIN, int yIN, TikTakToeStage tikTakToeStageIN){
        if(gameInput && getTurn()) {
            if(game.tryToPlacePice(xIN, yIN, getTurn())){
                //TiktokProtokol.tryToPlacePice(xIN, yIN, getTurn());
                int[][] board = game.getTiles();
                tikTakToeStageIN.updateTile(MathHelper.tileNum(xIN, yIN, width), board[xIN][yIN]);
            }
        }
    }


    
       

}
