import java.awt.*;

/**
 * Created by Kaś on 19.01.2017.
 */
//class to represent the menu button
public class Menu {

    private final Font font = new Font("Arial", Font.BOLD, 13);
    private final Color colourHeader = new Color (255, 255, 255);
    private final Color colourBorder = new Color (255, 255, 255);

    private final int menuHeight = 26, menuLen = 970;
    private Item[] items;
    private boolean isMenuVisible = false, hideMenu = false;
    private Instr menuPaintInstr = new Instr(0,0,0,0);
    private int menuAction = -1, mouseOverPreviousItem = -1;

    public Menu (String[] itemsS, boolean[] ifSeparated){
        items = new Item[itemsS.length];
        items[0] = new Item (0, menuHeight, itemsS[0], ifSeparated[0]); //we create first object here so that we can
        int height = items[0].getHeight(); // get height here and then

        for (int i = 1; i < itemsS.length; i++){ //use it to know the positions of following objects
            items[i] = new Item(0, (menuHeight + (i * height)), itemsS[i], ifSeparated[i]);
        }
    } //this constructor gets array of strings and array of logical value
    //which decide if items in menu will be separated with a line or not
    //the number of strings in the array decides how many obects Item will be created

    public boolean isVisible(){
        if(hideMenu) {
            hideMenu = false;
            return true;
        }

        return isMenuVisible;
    }

    public int mouseOverItem (int x, int y, boolean canBreakEarly){
        int index = -1;
        for ( int i =0; i < items.length; i++){
            if(items[i].isMouseOver(x,y)){
                index = i;
                if(canBreakEarly) break;
            }
        }
        return index;
    }

    public void changeMenuVisibility(){
        if (isMenuVisible){
            hideMenu = true;
            isMenuVisible = false;
        }
        else {
            isMenuVisible = true;
        }
    }

    public boolean menuClicked (int x, int y){
        menuAction = -1;
        if ((x >0 && x < 50) && y >0 && y <30){ //we decide where mouse clicked will affect the menu button
            changeMenuVisibility();
        }
        else if (isMenuVisible){
            menuAction = mouseOverItem(x,y, true); //we check which menu action was chosen
            if (menuAction == -1){
                changeMenuVisibility();
            }
            else{
                items[menuAction].removeMouseOver();
                isMenuVisible = false;
            }
        }
        else return false;
        menuPaintInstr.setRect(0, 0, 210, 330); //an instruction to paint a rectangle
        return true;
    }

    public Instr getMenuPaintInstr() {
        return menuPaintInstr;
    }

    public int getMenuAction() {
        return menuAction;
    }

    public boolean checkMouseOver(int x, int y){
        boolean requireRepaint = false;
        if(isMenuVisible){
            int mouseCurrent = mouseOverItem(x, y, false);
            if( mouseOverPreviousItem != mouseCurrent){
                menuPaintInstr.setRect(0, items[0].getStartY(),210 , items[0].getHeight() * items.length);
                mouseOverPreviousItem = mouseCurrent;
                requireRepaint = true;
            }
        }
        return requireRepaint;
    }

    public void drawMenu (Graphics graphics){
        drawMenuHeader(graphics);
        if(isMenuVisible) drawMenuItems(graphics);
    }
    private void drawMenuItems (Graphics graphics){
        for (int i= 0; i < items.length; i++){
            items[i].drawMenuItem(graphics);
        }
    }

    public void drawMenuHeader (Graphics graphics){
        graphics.setColor(colourHeader);
        graphics.fillRect(0, 0, menuLen, menuHeight -1);
        graphics.setColor(new Color(0,0,0));
        graphics.setFont(font);
        graphics.drawString("Menu", 7, 17);
        graphics.setColor(colourBorder);
        graphics.drawRect(0,0,menuLen, menuHeight  -1);
    }
}
