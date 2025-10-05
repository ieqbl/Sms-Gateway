package gateway.template;

import gg.jte.TemplateEngine;
import gg.jte.ContentType;
import gg.jte.output.StringOutput;
import gg.jte.resolve.DirectoryCodeResolver;

public class TemplateRenderer
{

    private final TemplateEngine engine;

    public TemplateRenderer()
    {
        var resolver = new DirectoryCodeResolver(TemplateConfig.TemplatesPath());
        this.engine = TemplateEngine.create(resolver, TemplateConfig.classDirectory(), ContentType.Html);
    }

    public String render(String templateName, Object model)
    {
        var output = new StringOutput();
        engine.render(templateName, model, output);
        return output.toString();
    }
}
