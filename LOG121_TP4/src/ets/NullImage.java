package ets;

import java.awt.*;

/**
 * Patron : NullObject
 *
 *          Historique des modifications
 ***************************************************
 * @author
 * 2013-11-
 */
public class NullImage  implements IImage {
    private Image theImage;

    public Image getTheImage() {
        return theImage;
    }

    private NullImage(){
        theImage = null;
    }

    public static NullImage createImage() {
        return new NullImage();
    }
}
