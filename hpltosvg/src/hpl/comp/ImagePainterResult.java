package hpl.comp;

import java.lang.String;

/**
 * The <code>CompilerResult</code> class represents any intermediate
 * level data that a compiler might produce.  It is one step away from
 * the final object code that is written to files.  Any class that has
 * a method to produce a useful string format could be an
 * implementation of this interface.
 *
 * @author <a href="mailto:daniel.coore@uwimona.edu.jm">Daniel Coore</a>
 * @version 1.0
 */
public class ImagePainterResult extends Declaration {

    private String filename,mId="";

    public ImagePainterResult(String file) {
        filename = file;
    }

    public void setId(String id)
    {
        this.mId = id;
    }
    /**
     * @return a String representing this result that is suitable for
     * writing into the object code format for this result.
     */
    public String gen()
    {
        String result = "<image id=\""+mId+"\" x=\"0\" y=\"0\" width=\"600\" height=\"600\" transform=\"scale(0.00167, 0.00167)\" xlink:href=\""+filename+"\" preserveAspectRatio=\"none\" />\n";
        return result;
    }



}
