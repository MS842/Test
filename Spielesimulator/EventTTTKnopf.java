import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.Event;
public class EventTTTKnopf implements EventHandler <ActionEvent>
{
    private int i;
    private int seitenlaenge;
    private TikTakToeStage tikTakToeStage;
    
    public EventTTTKnopf(int pI, int pSeitenlaenge, TikTakToeStage tikTakToeStageIN)
    {
        i=pI; 
        seitenlaenge=pSeitenlaenge;
        tikTakToeStage=tikTakToeStageIN;
    }
    @Override
    public void handle(ActionEvent event){
       tikTakToeStage.buttonPressed(MathHelper.gridNrX(i, seitenlaenge), MathHelper.gridNrY(i, seitenlaenge));
    } 
}

