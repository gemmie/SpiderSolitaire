import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.IOException;

/**
 **===============================
 ** Created by Kaś on 19.01.2017.
 **===============================
 */
//importing images from a folder
public class GetImages {
    private String strErrorMsg = "";

    public Image getImage (Object parentClass, String path, int fileSize) {

        byte buff[] = createImageArray(parentClass, path, fileSize);
        return Toolkit.getDefaultToolkit().createImage(buff);

    }




    //public String getErrorMsg ()
    //{
    //    return strErrorMsg;
    //}

    private byte[] createImageArray (Object parentClass, String path, int fileSize) {

        int count = 0;

        BufferedInputStream imgStream = new BufferedInputStream(parentClass.getClass().getResourceAsStream(path));

        if (imgStream != null) {//If file exists

            byte buff[] = new byte[fileSize]; //Create the array of bytes

            try {
                count = imgStream.read(buff);
            }
            catch (IOException e) {
                strErrorMsg = "Error reading from file: " + path;
            }

            try {
                imgStream.close(); //Closes the stream
            }
            catch (IOException e) {
                strErrorMsg = "Error closing file: " + path;
            }

            if (count <= 0) {
                strErrorMsg = "Error, empty file: " + path;
                return null;
            }
            return buff;
        }
        else {
            strErrorMsg = "Could Not Find File: " + path;
            return null;
        }

    }

}
