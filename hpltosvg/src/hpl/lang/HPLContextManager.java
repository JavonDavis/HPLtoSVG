package hpl.lang;

import hpl.sys.*;
import hpl.values.*;
import java.util.*;
import hpl.comp.*;

/**
 *
 * @author Javon Davis <javon.davis@mymona.uwi.edu>
 * Created on 7-Nov-2015
 */
public class HPLContextManager implements HPLContext {

	protected HPLEnvironment<HPLFunction> funcEnviron;
	protected HPLEnvironment<Double> numericalEnviron;
	protected HPLEnvironment<CompilerResult> resultEnviron;
	protected HPLEnvironment<Painter> painterEnviron;
	protected PainterFrame mFrame;

	public HPLContextManager(PainterFrame frame, HPLEnvironment<HPLFunction> fE,
	 HPLEnvironment<Double> nE, HPLEnvironment<Painter> pE, HPLEnvironment<CompilerResult> cE)
	{
		mFrame = frame;
		funcEnviron = fE;
		numericalEnviron = nE;
		painterEnviron = pE;
		resultEnviron = cE;
	}

	public HPLContext composeFrame(PainterFrame f)
	{
		PainterFrame frame = new PainterFrame(f.getO(),f.getU(),f.getV());
		return new HPLContextManager(frame, funcEnviron, numericalEnviron, painterEnviron,resultEnviron);
	}

	public HPLContext extendF(ArrayList<String> fParams, ArrayList<HPLFunction> args)
	{
		HPLEnvironment<HPLFunction> fBindings = new HPLEnvironment(funcEnviron, fParams, args);
		return new HPLContextManager(mFrame, fBindings, numericalEnviron, painterEnviron,resultEnviron);
	}

	public HPLContext extendN(ArrayList<String> nParams, ArrayList<Double> vals)
	{
		HPLEnvironment<Double> nBindings = new HPLEnvironment(numericalEnviron, nParams, vals);
		return new HPLContextManager(mFrame, funcEnviron, nBindings, painterEnviron,resultEnviron);
	}

	public HPLContext extendP(ArrayList<String> pParams, ArrayList<Painter> args)
	{
		HPLEnvironment<Painter> pBindings = new HPLEnvironment(painterEnviron, pParams, args);
		return new HPLContextManager(mFrame, funcEnviron, numericalEnviron, pBindings,resultEnviron);
	}

	public HPLContext extendC(ArrayList<String> cParams, ArrayList<CompilerResult> args)
	{
		HPLEnvironment<CompilerResult> cBindings = new HPLEnvironment(resultEnviron, cParams, args);
		return new HPLContextManager(mFrame, funcEnviron, numericalEnviron, painterEnviron,cBindings);
	}

	public PainterFrame getFrame()
	{
		return mFrame;
	}

	public HPLFunction getF(String name) throws HPLException
	{
		return funcEnviron.get(name);
	}

	public Double getN(String name) throws HPLException
	{
		return numericalEnviron.get(name);
	}

	public Painter getP(String name) throws HPLException
	{
		return painterEnviron.get(name);
	}

	public CompilerResult getC(String name) throws HPLException
	{
		return resultEnviron.get(name);
	}

	public void putF(String name, HPLFunction p)
	{
		funcEnviron.put(name,p);
	}

	public void putN(String name, Double n)
	{
		numericalEnviron.put(name, n);
	}

	public void putC(String name, CompilerResult result)
	{
		resultEnviron.put(name, result);
	}

	public HPLEnvironment<Double> getNumEnv()
	{
		return numericalEnviron;
	}

	public void putP(String name, Painter p)
	{
		painterEnviron.put(name,p);
	}
}