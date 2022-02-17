
/**
 * Beschreiben Sie hier die Klasse TikTakToeManagerLocal.
 * 
 * @author Max und Tilo
 * @version 0.1
 */
public class TikTakToeManagerLocal extends TikTakToeManager
{
    
    public TikTakToeManagerLocal(TikTakToeStage tikTakToeStageIN){
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
        }
    }

    public void inputButton(int xIN, int yIN, TikTakToeStage tikTakToeStageIN){
        if(gameInput) {
            if(game.tryToPlacePice(xIN, yIN, getTurn())){
                int[][] board = game.getTiles();
                tikTakToeStageIN.updateTile(MathHelper.tileNum(xIN, yIN, width), board[xIN][yIN]);
            }
        }
    }


    
       

}
