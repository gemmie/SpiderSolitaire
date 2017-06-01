import javax.swing.*;
import java.awt.*;

/**
 **===============================
 ** Created by Kaś on 19.01.2017.
 **===============================
 */
public class GUI {
    private GetImages createImage = new GetImages();
    private Window mainWindow;
    private MediaTracker mt;
    private String[] strCardBack = {"Feather", "Leaves", "Clouds"};


    public Container createGUI(JFrame mainApp) {

        JPanel panRoot = new JPanel( new BorderLayout() );

        mainWindow = new Window();
        mainWindow.setSize( new Dimension( 940, 700 ) );
        mainWindow.setBackground( new Color( 12, 143, 52 ) );

        mt = new MediaTracker( mainApp );
        importPictures();

        panRoot.add( mainWindow, BorderLayout.CENTER );

        return panRoot;

    }

    private void importPictures() {

        String colour = "";
        Image[][] imgCards = new Image[4][13];
        Image[] imgCardBack = new Image[3]; //Array to store all the card back images

        for (int suit = 0; suit < 4; suit++) { //loop 4 times (for each suit)

            switch (suit) { //inspect current suit number
                case 0:
                    colour = "club";
                    break;
                case 1:
                    colour = "spade";
                    break;
                case 2:
                    colour = "heart";
                    break;
                case 3:
                    colour = "diamond";
                    break;
            }

            for (int rank = 0; rank < 13; rank++) { //loop 13 times (for ace - king)
                imgCards[suit][rank] = createImage.getImage( this, "images/" + colour + (rank + 1) + ".gif", 10000 );
                mt.addImage( imgCards[suit][rank], 0 );
            }

        }

        for (int card = 0; card < 3; card++) { //loop the number of card back images being supplied
            imgCardBack[card] = createImage.getImage( this, "images/cardBack" + strCardBack[card] + ".gif", 20000 );
            mt.addImage( imgCardBack[card], 0 );
        }

        try {
            mt.waitForID( 0 );
        } catch (InterruptedException e) {
        }

        mainWindow.setupCard( imgCards, imgCardBack );
        mainWindow.newGame( 0 );

    }
}
