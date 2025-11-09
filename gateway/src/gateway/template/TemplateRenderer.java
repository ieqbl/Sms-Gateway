package gateway.template;

import gg.jte.output.StringOutput;
import java.util.Map;

public class TemplateRenderer
{

    public static String render(String templateName, Map<String, Object> params)
    {
        var engine = TemplateEngineProvider.getEngine();
        var output = new StringOutput();
        engine.render(templateName, params, output);
        return output.toString();
    }

}
