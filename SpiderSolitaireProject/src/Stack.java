import java.awt.*;
import java.util.ArrayList;

/**
 **===============================
 ** Created by Kaś on 19.01.2017.
 **===============================
 */

//Stack represents a larger number of cards as an arraylist
public class Stack {
    private ArrayList stack = new ArrayList();
    protected final int faceDownGap = 10, faceUpGap = 20; //gaps between cards
    private int height = 0; //height of the stack
    private int previousStackSize = 0;
    protected int startX = 0, startY = 0;





    public int size() {
        return stack.size();
    } //how many elements are there in the stack
    public int getHeight() { //how high is the stack

        if (size() == 0) return 0;
        return height;
    }

    private void amendStackHeight (Card card) { //changing the height of a stack
        int gap;
        int size = size();

        if( card.isIsFaceDown() )
            gap = faceDownGap;
        else
            gap = faceUpGap;

        if( size > previousStackSize ) {//if a card has been added

            if ( size == 1) height = 96;
            else height += gap;
        }
        else { //if a card has been removed

            if( size == 0 ) height = 0;
            else height -= gap;
        }

        previousStackSize = size;

    }

    protected void addCard (Card card){ //adding a card to the stack and changing its height
        stack.add(card);
        amendStackHeight(card);
    }

    protected Card removeCard (int index){ //removing card from stack
        Card card = (Card) stack.remove(index);
        amendStackHeight(card);

        return card; //returns the removed card

    }

    public void addStack (ArrayList vec){   //adding to a stack
        int addHeight = vec.size();
        Card card;

        for (int i=0; i < addHeight; i ++){
            card =(Card) vec.remove(0);
            addCard(card);
        }

    }

    public Card getCard (int index){
        return (Card)stack.get(index);
    } //returns a card of a specified index in arraylist


    public Card getLastCard (){

        return (Card)stack.get(size()-1);
    } //


    public int getLastCardRank(){
        if(size() >0) {
            Card card = (Card)stack.get(size()-1);
            return card.getRank();
        }
        return -1;
    } //gets last cards rank and if there are no more cards we get -1
    //it's a separate method so that when one of the columns is empty
    //we can drag everything in there, as rule can be dropped
    //depends on cards ranks

    public ArrayList removeStack (int index){
        ArrayList vec = new ArrayList();
        while ( index < stack.size()){
            vec.add(removeCard(index));
        }

        return vec;
    }
    //removes everything from the index specified


    public void clear (){
        stack.clear();
        previousStackSize = 0;
    } //clears the whole stack

    public int getHeight (int cardInd){
        return countStackHeight (startY, 0, cardInd, false);
    } //gives us the height from the first card in stack to the one of specified index given as parameter

    public int getStackHeight (int fromCard){
        return countStackHeight (0, fromCard, size(), true);
    } //gives us the height from the card of index given to the last card in stack

    public int countStackHeight (int gap, int startCard, int endCard, boolean fullLastCard){
        Card card;
        for( int i = startCard; i < endCard; i++){
            if ( i == (endCard - 1) && fullLastCard ){
                gap +=96;
            }
            else{
                card = getCard(i);
                if (card.isIsFaceDown())gap += faceDownGap;
                else gap += faceUpGap;

            }
        }
        return gap;
    }//counts height of the stack on applet adding different gap values
    //depending on whether the card is face up or down

    protected void paintStack(Graphics grpOffScreen, PaintLayout mainApp){
        drawExtra (grpOffScreen);
        Card card;
        int gap = startY;

        for (int i =0; i < size(); i++){
            card = getCard(i);

            if(card.isIsFaceDown()){
                mainApp.drawCard(grpOffScreen, getFaceDownImg(), startX, gap);
                gap += faceDownGap;
            }
            else{
                mainApp.drawCard(grpOffScreen, card.getImg(), startX, gap);
                gap += faceUpGap;
            }
        }
    }
    //painting stack

    public void drawExtra (Graphics grpOffScreen){}
    public Image getFaceDownImg() {
        return null;
    }
}
