import java.awt.*;

/**
 **===============================
 ** Created by Kaś on 19.01.2017.
 **===============================
 */
//our class to represent the ten columns that
//we will then paint in the applet
public class Column extends Stack {

    private Image cardBack = null;


    public Column (int startX, int startY){
        this.startX = startX;
        this.startY = startY;
    }

    public int getStartX (){
        return startX;
    }

    public void setCardBack (Image cardBack){
        this.cardBack = cardBack;
    }

    public boolean isMouseOverColumn (int x, int y){
        int columnH = startY + 96;
        if (size() !=0) columnH = getHeight() + 30;

        if (( x >= startX && x <= (startX + 71)) && (y >= startY && y <= columnH))
            return true;
        return false;
    } //we check if mouse is over column

    public int mouseOverCard (int y){
        int gap = startY;
        Card current;

        for (int i = 0; i < size(); i++){
            current = getCard(i);
            if ( i == (size() -1)){
                if( y >= gap && y <= (gap + 96)) return i;
            }
            else if ( current.isIsFaceDown()){
                if ( y >= gap && y <= (gap + faceDownGap)) return i;
                else gap += faceDownGap;
            }
            else{
                if( y >= gap && y <= (gap + faceUpGap)) return i;
                else gap += faceUpGap;
            }
        }
        return -1;
    } //we check over which card is the mouse, starting from the one
    //on the very bottom, so we can calculate to what value
    //we should compare the y of the mouse position to



    public Instr getDragStartInstr (int dragCard){
        return new Instr(startX, getHeight(dragCard -1), 71, getStackHeight(dragCard));
    }

    public boolean canBeDragged (int startCard){
        if(startCard != -1){
            Card first = getCard(startCard);
            if (first.isIsFaceDown()) return false;
            for (int i = (startCard + 1); i < size(); i++){
                if(!ruleCanBeDragged(getCard(i-1), getCard(i))) return false;
            }
            return true;
        }
        return false;
    }
    //here we check if the card can be dragged
    //we get the specified index and of course
    //if card is face down it can't be dragged
    //and it has to fulfill the rule beneath

    public boolean ruleCanBeDragged (Card previous, Card current){
        if(current.getRank() == (previous.getRank() -1)) return true;
        return false;
    } //this rules specifies that for dragging two cards
     //we can do that only if their ranks are adequate

    public boolean canBeDropped (Card dragCard){
        if( ruleCanBeDropped(dragCard.getRank(), getLastCardRank())) return true;
        return false;
    }
    //checking if card can be dropped

    public boolean ruleCanBeDropped ( int dragCardrank, int columnCardrank){
        if(columnCardrank == -1) return true;
        if (dragCardrank == (columnCardrank - 1 )) return true;
        return false;
    }
    //can be dropped either when the column is empty
    //or the rank of card dragged equals column card's rank - 1
    public Image getFaceDownImg (){
        return cardBack;
    }

    public boolean checkFullDeck(){
        if (size() < 13) return false;
        Card previous = getLastCard();
        if(previous.getRank() != 0) return false;

        int cardIndex = size() - 2;
        Card current;

        for ( int i = 0; i < 12; i++){
            current = getCard(cardIndex);
            if ((current.getColour() != previous.getColour()) || (current.getRank() != (previous.getRank()+1)))
                return false;
            cardIndex--;
            previous = current;
        }
        removeStack(size()-13);

        if(size() > 0) {
            Card card = getLastCard();
            if (card.isIsFaceDown())
                card.setIsFaceDown(false);
        }
        return true;
    }
    //we check if full deck was put together
    //if yes we remove it from the column


    public void drawExtra (Graphics grpOffScreen){
        if (size() == 0){
            //grpOffScreen.setColor(holder);
            //grpOffScreen.fillRect(startX, startY, 71, 96);
            grpOffScreen.setColor(new Color(0,0,0));
            grpOffScreen.drawRect(startX, startY, 71, 96);
        }
    }
    //here we draw the rectangle that's left when all the cards
    //in a column are gone




}
