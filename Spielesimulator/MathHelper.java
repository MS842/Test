/**
 * Beschreiben Sie hier die Klasse MathHelper.
 * 
 * @author Max & Tilo
 * @version 0.2
 */
public class MathHelper //static aufgrund von BlueJ nicht möglich!?
{
    public static int gridNrY(int i, int seitenlaenge){
        return (int)i/seitenlaenge;//Bestimmen der Zeile durch Abschneiden der Nachkomastelle bei der Division
    }

    public static int gridNrX(double i, double seitenlaenge){
        int iI= (int) i;
        int iSeitenlaenge = (int) seitenlaenge;
        double ergebnis = i- ((double) gridNrY(iI, iSeitenlaenge)*seitenlaenge);
        int iErgebnis = (int) ergebnis;
        return iErgebnis;
    }

    public static int tileNum(int xIN, int yIN, int widthIN){
        return xIN + yIN * widthIN;
    }

    public static int MenueGridNrY(int i, int seitenlaenge){
        int NrY = gridNrY(i, seitenlaenge);
        if(NrY==0){
            return 0;
        }
        return NrY+1; //Überspringen von Zeile 1 da hier nur die Überschrift sein sol
    }
}
