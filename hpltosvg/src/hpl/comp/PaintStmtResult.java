package hpl.comp;

import java.awt.geom.Point2D;
import java.lang.Override;
import java.lang.StringBuilder;
import hpl.sys.*;

public class PaintStmtResult extends Initialiser
{
    private StringBuilder builder = new StringBuilder();
    public PaintStmtResult()
    {

    }

    public void buildPaintStmt(CompilerResult result, PainterFrame frame)
    {
        Point2D location = frame.getO();
        Vector2D fU = frame.getU();
        Vector2D fV = frame.getV();

        String transform = "transform=\"matrix("+fU.getX()+", "+fU.getY()+", "+fV.getX()+", "+fV.getY()+", "+location.getX()+", "+location.getY()+")\"";

        builder.append("<g "+transform+">\n");
        builder.append(result.gen());
        builder.append("</g>\n");
    }

    @Override
    public String gen()
    {
        return builder.toString();
    }
}