import java.awt.*;

/**
 * Created by Kaś on 19.01.2017.
 */

//Class representing a card, with colour and rand as ints
//Also having an image to show in the applet
//Checks if card is face up or down

public class Card {
    private int rank = 0, colour = 0;
    private boolean isFaceDown = true;
    private Image img = null;


    public Card (int colour,int rank, Image img){
        this.rank = rank;
        this.colour = colour;
        this.img = img;
    }

    public int getRank() {
        return rank;
    }

    public int getColour() {
        return colour;
    }

    public boolean isIsFaceDown() {
        return isFaceDown;
    }

    public Image getImg() {
        return img;
    }

    public void setIsFaceDown(boolean isFaceDown) {
        this.isFaceDown = isFaceDown;
    }

}
