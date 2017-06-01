/**
 **===============================
 ** Created by Kaś on 19.01.2017.
 **===============================
 */
//class for cards being dragged and getting instruction to paint them
public class dragStack extends Stack {
    private int dragStartCard = 0, oColumn = 0;
    private Instr previousPaintInstr = new Instr(0,0);
    //private int mouseX =0, mouseY = 0;


    public void setDragStartCard(int dragStartCard) {
        this.dragStartCard = dragStartCard;
    }

    public void setoColumn(int oColumn) { //the column we drag from
        this.oColumn = oColumn;
    }

    public int getDragStartCard() {
        return dragStartCard;
    }

    public int getoColumn() { //the column we drag from
        return oColumn;
    }

    public void setMousePosition (int mouseX, int mouseY){
        //this.mouseX = mouseX;
        //this.mouseY = mouseY;

        this.startX = (mouseX -30);
        this.startY = (mouseY - 30);
    }

    public void setStartInstr(Instr previousPaintInstr) {
        this.previousPaintInstr = previousPaintInstr;
    }

    public Instr getPaintInstr() { //gets instructions to paint dragged stack
        int stackHeight = getStackHeight(0);
        int previousX = previousPaintInstr.getStartX();
        int previousY = previousPaintInstr.getStartY();

        previousPaintInstr.setRect(startX, startY, 71, stackHeight); //we set it to startX, startY, width as
                                                                            //width of the card pictures, height of stack

        int clipX, clipY, clipWidth, clipHeight;
        if(previousX >= startX){
            clipX = startX;
            clipWidth = clipX + (previousX - clipX) + 71;
        }
        else{
            clipX = previousX;
            clipWidth = clipX + (startX - previousX) +71;
        }
        if (previousY >= startY){
            clipY = startY;
            clipHeight = clipY + (previousY - clipY) + stackHeight;
        }
        else {
            clipY = previousY;
            clipHeight = clipY + (startY - previousY) + stackHeight;
        }
        return new Instr(clipX, clipY, clipWidth, clipHeight);
    }





}
