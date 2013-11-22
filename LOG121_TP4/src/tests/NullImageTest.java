package tests;

import ets.ImageConcrete;
import ets.NullImage;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: charleslevesque
 * Date: 2013-11-21
 * Time: 19:40
 * To change this template use File | Settings | File Templates.
 */
public class NullImageTest {
    @Test
    public void testCreateImage() throws Exception {
        NullImage image = NullImage.createImage();

        assert image.getTheImage() == null;
    }
}
