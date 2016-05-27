/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hpl.sys;

import hpl.values.HPLFunction;
import hpl.values.Painter;
import java.util.ArrayList;
import hpl.comp.*;

/**
 *
 * @author Daniel Coore <daniel.coore@uwimona.edu.jm>
 * Created on 31-Oct-2015
 */
public interface HPLContext {

    /**
     * Resolve a frame relative to the current frame (coordinate system), and
     * make the result be the new current frame in a newly created context.
     * @param f The frame to be derived from the current frame.
     * @return a newly created context that has identical components as this one,
     * except that its current frame is the effective frame denoted by the given
     * one.
     */
    public HPLContext composeFrame(PainterFrame f);

    /**
     * Create a new context in which the function environment is extended with
     * new bindings.
     * @param fParams The names to be bound in the new function frame.
     * @param args The corresponding values for the names
     * @return A newly created context containing the new function environment,
     * but leaving all the other components of the context unchanged.
     */
    public HPLContext extendF(ArrayList<String> fParams, ArrayList<HPLFunction> args);

    /**
     * Create a new context in which the numerical environment is extended with
     * new bindings.
     * @param nParams The names to be bound in the new numerical environment
     * frame.
     * @param vals The corresponding values for the names
     * @return A newly created context containing the new numerical environment,
     * but leaving all the other components of the context unchanged.
     */
    public HPLContext extendN(ArrayList<String> nParams, ArrayList<Double> vals);

    /**
     * Create a new context in which the painter environment is extended with
     * new bindings.
     * @param pParams The names to be bound in the new painter environment frame.
     * @param args The corresponding values for the names
     * @return A newly created context containing the new painter environment,
     * but leaving all the other components of the context unchanged.
     */
    public HPLContext extendP(ArrayList<String> pParams, ArrayList<Painter> args);

    /**
     * Create a new context in which the CompilerResult is extended with new bindings.
     * @param cParams The names to be bounded in the new CompilerResult frame
     * @param args tthe corresponding values for the names
     * @return a newly created context containing the CompilerResult environment, but
     * leaving all the other components of the context unchanged.
     */
    public HPLContext extendC(ArrayList<String> cParams, ArrayList<CompilerResult> args);

    /**
     * Lookup a reference to a HPL function.
     * @param name The identifier of the HPL function
     * @return The HPL function associated with the given name in this context
     * @throws HPLException if the name is not bound to a painter in this
     * context
     */
    public HPLFunction getF(String name) throws HPLException;

    /**
     *
     * @return The (resultant) frame associated with this context.
     */
    public PainterFrame getFrame();

    /**
     * Lookup a reference to a number
     * @param name The identifier of the Double
     * @return The number associated with the given name in this context
     * @throws HPLException if the name is not bound to a number in this
     * context
     */
    public Double getN(String name) throws HPLException;

    /**
     *
     * @return The numerical environment associated with this context.
     */
    public HPLEnvironment<Double> getNumEnv();

    /**
     * Lookup a reference to a painter
     * @param name The identifier of the painter
     * @return The painter associated with the given name in this context
     * @throws HPLException if the name is not bound to a painter in this
     * context
     */
    public Painter getP(String name) throws HPLException;

    /**
     * Lookup a reference to a CompilerResult
     * @param name the identifier of the CompilerResult
     * @return The CompilerResult assoiciated with the given name in this context
     * @throws HPLException if the name is not bound to a CompilerResult in this context
     */
    public CompilerResult getC(String name) throws HPLException;

    /**
     * Store a binding for the given name to the given HPL function.
     * @param name The identifier of the binding
     * @param p The HPL function to be associated with the name
     */
    public void putF(String name, HPLFunction p);

    /**
     * Store a binding for the given name to the given number.
     * @param name The identifier of the binding
     * @param n The numerical value to be associated with the name
     */
    public void putN(String name, Double n);

    /**
     * Store a binding for the given name to the given painter.
     * @param name The identifier of the binding
     * @param p The painter value to be associated with the name
     */
    public void putP(String name, Painter painter);

    /**
     *
     * @param name the identifier of the binding
     * @param result the Compilerresult value to be associated with the name
     */
    public void putC(String name, CompilerResult result);
}
