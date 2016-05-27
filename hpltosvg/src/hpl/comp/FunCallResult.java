package hpl.comp;


import java.lang.StringBuilder;

public class FunCallResult extends Declaration
{
    private StringBuilder builder = new StringBuilder();
    public FunCallResult()
    {
    }

    public void addContent(CompilerResult result)
    {
        builder = new StringBuilder("<g>\n"+result.gen()+"</g>\n");
    }

    public void setId(String id)
    {
        int start = builder.indexOf("<g");

        int end = builder.indexOf(">");
        builder.replace(start,end, "<g id=\""+id+"\"");
    }

    public String gen()
    {
        //String result = "<image id=\""+mId+"\" x=\"0\" y=\"0\" width=\"600\" height=\"600\" transform=\"scale(0.00167, 0.00167)\" xlink:href=\""+file_name+"\" preserveAspectRatio=\"none\" />\n";
        return builder.toString();
    }
}