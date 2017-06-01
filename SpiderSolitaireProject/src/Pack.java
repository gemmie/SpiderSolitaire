import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;
import static java.lang.Math.random;

/**
 **===============================
 ** Created by Kaś on 19.01.2017.
 **===============================
 */
//a class to keep the card we're going to deal in the game
public class Pack extends Stack {
    protected Image[][] img;

    public void setupCardImage (Image[][] img){
        this.img = img;
    }

    public void shuffleCards(){
        ArrayList vec = new ArrayList();
        int newsize = size();
        int random;

        for( int card = 0; card < newsize; card++){
            vec.add(removeCard(0));
        }

        for( int i = 0; i < newsize; i++){
            random = 1 + (int)(vec.size() * random());
            random--;

            addCard((Card)vec.remove(random));
        }
    } //we shuffle cards by adding them to new arraylist
    //and then randomly putting them back in stack

    public void setupPack (int DiffLevel){
            for (int i = 0; i < 2 ; i++){
                for (int rank = 0; rank < 13; rank++) {
                    for (int suit = 0; suit < 4; suit++) {
                        Card current = null;
                        if(DiffLevel == 0)
                            current = new Card(0,rank,img[0][rank]);

                        else if (DiffLevel == 1) {
                            if (suit < 2)
                                current = new Card(0, rank, img[0][rank]);

                            else
                               current = new Card(2, rank, img[2][rank]);

                        }
                        else if (DiffLevel == 2)
                            current = new Card(suit, rank, img[suit][rank]);
                        addCard(current);

                    }
                }

            }
        shuffleCards();
    } //here we choose what cards to display
    //so we make a loop for the number of decks (one deck = 52 cards) we need 104 cards
    //another one for rank - to we need 13 from ace to king
    //and the last one for suit, and all depends on the level of difficulty the player chooses

}
