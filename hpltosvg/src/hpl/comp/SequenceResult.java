package hpl.comp;


import java.lang.Override;

public class SequenceResult implements CompilerResult
{
    private StringBuilder builder = new StringBuilder();
    public SequenceResult()
    {

    }

    public void addResult(CompilerResult result)
    {
        if(result!=null)
            builder.append(result.gen());
    }

    @Override
    public String gen()
    {
        return builder.toString();
    }
}