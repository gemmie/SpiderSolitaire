import java.awt.*;

/**
 **===============================
 ** Created by Kaś on 19.01.2017.
 **===============================
 */
//represents cards shown in the right down corner to use
//when you run out of moves
public class Deck extends Stack {

    private int endX = 840, startY = 550; //we set their position
    private int deckCardsShown = 0;       //how many cards to show
    private Image cardBack = null;



    public void update (){
        if (size() == 0) deckCardsShown = 0;
        else deckCardsShown = size() /10;
    } //updating how many cards to show -- the number of cards in stack/10
    //so at first we get 5
    //as we use two decks and we layout 4*6 + 6*5 card that gives us
    //24+30 so 54 cards two decks are 104 so it gives us 50 cards left
    //we deal 10 cards at once - one for every column


    public void paintDeck (Graphics grpOffScreen, PaintLayout mainApp){
        for (int i = 0; i < deckCardsShown; i++){
            mainApp.drawCard(grpOffScreen, cardBack, endX - (20*i), startY);
        }

    }//painting, we can't do it here so we put it in paint&layout

    public void setCardBack(Image cardBack) {
        this.cardBack = cardBack;
    }
    //that's one pretty obvious

    public boolean clickedDeck (int x, int y){

        if( deckCardsShown == 0) return false;
        int cardX = endX - (20*(deckCardsShown -1));

        if( x >= cardX && x <= (cardX +96) && y >= startY && y <= (startY+ 71))
            return true;
        return false;
    }
    //checking if deck was clicked so we can deal some cards

    public boolean successfulDeckDeal (Column[] column){
        for (int i = 0; i<10; i++){
            if ( column[i].size() == 0) return false;
        } //we can't deal if one of colums is empty
        Card card;

       // for( int i = 0; i < 10; i++){
         //   card = column[i].getLastCard();
          //  if (card.isIsFaceDown()) return false;
        //} //if one of cards is facedown we can't deal,
        //but now that I think about it, it probably
        //doesn't make sense - cards are always face up
        for (int i = 0; i<10; i++){
            column[i].addCard(removeCard((size()-1)));
        }
        //so we add one card form the deck to every column
        deckCardsShown --;//less decks to show
        return true; //all went well
    }

}

