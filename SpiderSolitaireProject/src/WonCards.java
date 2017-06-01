import java.awt.*;

/**
 * Created by Kaś on 24.01.2017.
 */
//this class paints aces that represent full deck was correctly put together
public class WonCards extends Stack {
    private int startX = 20, startY = 550;
    private int cardsShown = 0;
    private Image ace = null;


    public void setAce (Image ace){
        this.ace = ace;
    }
    public void resetCardsShown(){
        this.cardsShown = 0;
    }

    public void paintWonCards (Graphics g, PaintLayout app){
        for (int i = 0; i < cardsShown ; i++) {
            app.drawCard(g, ace, startX + (20*i), startY);
        }
    }
    public void incrementCardsShown(){
        cardsShown ++;
    }

}
