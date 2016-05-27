package hpl.sys;

import java.io.*;
import hpl.lang.*;
import hpl.comp.*;

/**
 * The <code>HPLComp</code> class is a generic HPL compiler class.  It
 * takes the class name of the compiler to use and the filename of the
 * HPL source on the command line.  It parses the HPL file and uses an
 * instance of the supplied compiler class to visit the syntax tree
 * produced from the parser.  It then causes the compiler to write out
 * its output to a file name with a stem derived from the input
 * filename.  The actual output filename is determined by the compiler
 * object used.
 *
 * @author <a href="mailto:newts@uwimona.edu.jm">Daniel Coore</a>
 * @version 1.0
 */
public class HPLComp {

    static Class compClass;
    static HPLCompiler comp;

    /**
     * Setup a global environment, execute any programs supplied on
     * the command line, and if desired execute the Read Eval Print
     * Loop.
     *
     * @param args a <code>String[]</code> value
     */
    public static void main(String[] args) {
	if (args.length != 2)
	    usage();
	else {
	    String filename = args[1];
	    try {
		compClass = Class.forName(args[0]);
	    } catch (ClassNotFoundException cnfe) {
		System.err.println("Could not find Compiler class " + args[0]);
	    }

	    // make an instance of the compiler
	    try {
		comp = (HPLCompiler)compClass.newInstance();
	    } catch (IllegalAccessException iae) {
		System.err.println("Constructor for " + compClass +
				   " is inaccessible");
	    } catch (InstantiationException ie) {
		System.err.println("The compiler class " + compClass +
				   " cannot be instantiated!");
	    } catch (ClassCastException cce) {
		System.err.println("The compiler class " + compClass +
				   " does not implement the HPLCompiler " +
				   "interface");
	    }

	    PIRProgram commands = null;
	    CompilerResult result = null;

	    // now read the file
	    try {
		commands = parse(new FileInputStream(filename));
	    } catch (FileNotFoundException fnfe) {
		System.out.println("Could not find file " + filename);
	    }

	    /* convert the AST to the target language, writing it to a
	       file with the same stem as the input file. */
	    // pick out the stem (everything up to the rightmost dot)
	    result = comp.translate(commands);
	    String filenameStem = filename.substring(0, filename.lastIndexOf("."));
	    String outFilename = filenameStem + comp.getTargetExtension();
	    File outFile = new File(outFilename);
	    if (outFile.exists())
		outFile.delete();
	    try {
		if (outFile.createNewFile())
		    try {
			FileWriter fw = new FileWriter(outFile);
			fw.write(result.gen());
			fw.close();
		    } catch (IOException ioe) {
			System.err.println("Failed to write to object file " +
					   outFilename);
		    }
		else {
		    System.err.println("Could not create object file " + outFilename);
		}
	    } catch (IOException ioe) {
		System.err.println("Failed to create object file " + outFilename);
	    }
	}
    }

    /**
     * Create a parser to read an HPL program from the supplied input
     * stream and return the abstract syntax tree representing it.
     *
     * @param is the input stream containing an HPL program
     * @return the <code>PIRProgram</code> instance that results from
     * parsing the HPL program.
     */
    public static PIRProgram parse(InputStream is) {

	HPLParser parser;
	PIRProgram commands = null;

	try {
	    parser = new HPLParser(is);
	    commands = (PIRProgram) parser.parse().value;
	} catch (Exception e) {
	    System.out.println("Syntax Error: " + e.getMessage());
	}

	return commands;
    }

    /**
     * Print instructions for using this program and exit.
     *
     */
    public static void usage() {
	System.err.println("Usage: java HPLComp <compiler class> <HPL source>");
	System.err.println("<compiler class> is the compiler you wish to invoke");
	System.err.println("<HPL source> is the name of a file containing" +
			   " an HPL program");
	System.exit(1);
    }
}
