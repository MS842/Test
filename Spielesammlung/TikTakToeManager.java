/**
 * Beschreiben Sie hier die Klasse TikTakToeManager.
 *
 * @author Max und Tilo
 * @version 0.3
 */
public class TikTakToeManager
{
	private TikTakToeGameLogic game;
	private int width;
    
	private boolean input=true;
	private TikTakToeStage tikTakToeStage;
	private boolean criticalError;
	public TikTakToeManager(TikTakToeStage tikTakToeStageIN){
    	tikTakToeStage=tikTakToeStageIN;
	}
	public void startNewGame(int seitenlaengeIN){
    	if(input){
        	width = seitenlaengeIN;
       	 
        	int lengthToWin;
        	if (seitenlaengeIN ==3){
            	lengthToWin=3;
        	}
        	else {
            	lengthToWin=4;
        	}
        	game = new TikTakToeGameLogic(seitenlaengeIN, seitenlaengeIN,lengthToWin, true);
    	}
	}
}