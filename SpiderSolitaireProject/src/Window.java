import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 **===============================
 ** Created by Kaś on 19.01.2017.
 **===============================
 */
//we steer with mouse
public class Window extends PaintLayout implements MouseListener, MouseMotionListener {
    private final int refreshRate = 5;
    private int refreshCount = 0;
    private boolean updateDrag = false;


    public Window (){
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && !hasWon){
            int x = e.getX();
            int y = e.getY();

            if(menu.menuClicked(x, y))
                performMenuAction();
            else if (deck.clickedDeck(x, y)) {
                if (deck.successfulDeckDeal( columns )) {
                    testColumnDeck();
                    repaint();
                }
            }
        }
    }

    public int columnMouseIsOver (int x, int y){
        if(x >= columnGap && x <= ((71 + columnGap)*10) && y >= 50){
            for (int i =0; i < 10; i++){
                if( columns[i].isMouseOverColumn(x, y))
                    return i;
            }
        }
        return -1;
    }

    public void testWin (){
        boolean wonGame = true;
        for ( int column = 0; column <10; column ++){
            if(columns[column].size() != 0){
                wonGame = false;
                break;
            }
        }
        if( deck.size() != 0)
            wonGame = false;
        if(wonGame)
            hasWon = true;
    }

    public void testColumnDeck(){
        boolean shouldTestWin = false;
        for (int i =0; i < 10; i ++){
            if(columns[i].checkFullDeck()){
                wonCards.incrementCardsShown();
                shouldTestWin = true;
                current.reset();
            }
        }
        if (shouldTestWin)
            testWin();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && !hasWon){
            int x = e.getX();
            int y = e.getY();
            int column = columnMouseIsOver(x,y);

            if(column!= -1){
                int dragCard = columns[column].mouseOverCard(y);
                if(columns[column].canBeDragged(dragCard)){
                    drag.setoColumn(column);
                    drag.setDragStartCard(dragCard);
                    updateDrag = true;
                }
            }

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isDragging = false;
        updateDrag = false;

        if (drag.size() != 0){
            int x = e.getX();
            int y = e.getY();
            boolean badMove = true;
            int column = columnMouseIsOver(x,y);

            if(column != -1){
                if(columns[column].canBeDropped(drag.getCard(0))){
                    columns[column].addStack(drag.removeStack(0));
                    badMove = false;
                    testColumnDeck();



                    if(columns[drag.getoColumn()].size() > 0) {
                        Card card = columns[drag.getoColumn()].getLastCard();
                        if (card.isIsFaceDown()) card.setIsFaceDown(false);
                    }

                }
            }
            if (badMove)
                columns[drag.getoColumn()].addStack(drag.removeStack(0));
            repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
        mouseReleased(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if(updateDrag){
            int column = drag.getoColumn();
            int dragCard = drag.getDragStartCard();

            isDragging = true;
            updateDrag = false;
            refreshCount = 0;

            drag.setStartInstr(columns[column].getDragStartInstr(dragCard));
            drag.addStack(columns[column].removeStack(dragCard));
            drag.setMousePosition(x, y);
            repaint();
        }
        if (isDragging){
            if (refreshCount > refreshRate){
                refreshCount = 0;
                drag.setMousePosition(x, y);
                repaint();
            }
            else
                refreshCount++;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (menu.checkMouseOver(e.getX(), e.getY()))
            repaint();
    }



}
