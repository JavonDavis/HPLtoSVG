package hpl.comp;

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
public interface CompilerResult {

    /**
     *
     * @return a String representing this result that is suitable for
     * writing into the object code format for this result.
     */
    public String gen();

}
