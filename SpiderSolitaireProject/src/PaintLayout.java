import java.awt.*;

/**
 **===============================
 ** Created by Kaś on 19.01.2017.
 **===============================
 */
public class PaintLayout extends Canvas {
    int columnGap = 20;
    private Color backgroundColour = new Color(12, 143, 52);
    protected Menu menu;
    protected  Column[] columns = new Column[10];
    protected dragStack drag = new dragStack();
    protected Deck deck = new Deck();
    private Pack pack = new Pack();

    protected boolean isDragging = false, hasWon = false;
    private int newDiffLevel = 0;
    protected int cardBackImgInUse = 0;
    protected Instr current , dragPaint;
    protected Image[] cardBack;
    protected WonCards wonCards = new WonCards();

    public PaintLayout(){
        setupMenuSystem();
        setupColumnSystem();
    }

    private void setupMenuSystem() {
        String[] strMenuItemText = {"Deal", "Easy", "Medium", "Hard", "Feather",
                "Leaves", "Clouds"};
        boolean[] boolMenuItemSep = {true, false, false, true,false, false, false};

        menu = new Menu(strMenuItemText, boolMenuItemSep);

    } //here we setup what's in the menu

    private void setupColumnSystem(){
        for (int i=0; i<10; i++){
            columns[i] = new Column(columnGap + ((71+columnGap)*i),50);
        }
    } //we're making columns

    public void performMenuAction(){
        int menuAction = menu.getMenuAction();
        if( menuAction == 0 ) newGame(newDiffLevel);
        else if (menuAction > 0 && menuAction <= 3){
            newDiffLevel = menuAction -1;
            newGame(newDiffLevel);
        }
        else if( menuAction >= 4)
            setCardBackImage( menuAction -4);
        repaint();
    }//which menu action was clicked;

    public void newGame(int DiffLevel){
        current.reset();
        hasWon = false;
        pack.clear();
        wonCards.resetCardsShown();
        pack.setupPack(DiffLevel);
        layoutCards();
    } //we start a new game - reset everything

    public void setCardBackImage(int index){
        cardBackImgInUse = index;
        for ( int i =0; i<10; i++)
            columns[i].setCardBack(cardBack[cardBackImgInUse]);
        deck.setCardBack(cardBack[cardBackImgInUse]);
    } //set card back to cards on columns and in deck


    public void setupCard (Image[][] Cards, Image[] cardBack){
        this.cardBack = cardBack;
        pack.setupCardImage(Cards);
        setCardBackImage(cardBackImgInUse);
        this.current = new Instr(getSize().width, getSize().height);
        this.dragPaint = new Instr(getSize().width, getSize().height);
        wonCards.setAce(Cards[0][0]);

        newGame(newDiffLevel);
        repaint();
    } //we put objects and images together

    public void layoutCards(){
        int rowHeight = 0, count = 0;
        Card curCard;
        for(int i =0; i<10; i++){
            columns[i].clear();
            if (i < 4)
                rowHeight = 6;
            else
                rowHeight = 5;
            for (int row = 0; row < rowHeight; row++){
                curCard = pack.getCard(count);
                if( row == (rowHeight -1))
                    curCard.setIsFaceDown(false);
                else
                    curCard.setIsFaceDown(true);
                columns[i].addCard(curCard);
                count++;
            }
        }
        deck.clear();
        for ( int card = count; card < 104; card++){
            curCard = pack.getCard(card);
            curCard.setIsFaceDown(false);
            deck.addCard(curCard);
        }
        deck.update();
        current.reset();
    } //laying out cards - 6 in first four columns, then 5 in the next six columns
    //the rest goes to the deck
    @Override
    public void update (Graphics g){
        paint(g);
        if(hasWon) drawCongrats(g);
    }

    public void clip (Instr clipInstr, Graphics graphics, Graphics g){
        int startX = clipInstr.getStartX();
        int startY = clipInstr.getStartY();
        int width = clipInstr.getWidth();
        int height = clipInstr.getHeight();

        graphics.clipRect(startX, startY, width, height);
        g.clipRect(startX, startY, width, height);

        graphics.setColor(backgroundColour);
        graphics.clearRect(startX, startY, width, height);
    } //we clip the instruction - where objects are to be shown

    @Override
    public void paint (Graphics g){
        Image imgOffScreen = createImage(getSize().width, getSize().height);
        Graphics graphics = imgOffScreen.getGraphics();
        graphics.setClip(0,0,getSize().width, getSize().height);
        if(isDragging)
            clip(drag.getPaintInstr(),graphics, g);
        else if(menu.isVisible())
            clip(menu.getMenuPaintInstr(),graphics, g);
        else
            clip(current, graphics, g);

        for (int i = 0; i < 10; i ++)
            columns[i].paintStack(graphics, this);
        deck.paintDeck(graphics, this);
        wonCards.paintWonCards(graphics, this);

        if(isDragging)
            drag.paintStack(graphics, this);

        menu.drawMenu(graphics);
        g.drawImage(imgOffScreen, 0, 0, this);
        current.reset();

    }

    public void drawCard (Graphics graphics, Image imgCard, int startX, int startY){
        graphics.drawImage(imgCard, startX, startY, this);
        graphics.setColor(new Color(3, 3, 3)); //dark green
        graphics.drawRect(startX, startY, 71, 96);
    }

    public void drawCongrats(Graphics g){
        g.setColor(new Color(0,0,0));
        g.setFont(new Font("Arial", Font.BOLD,50 ));
        g.drawString("Congratulations! \n You won!", 110, 400);
    }
}
