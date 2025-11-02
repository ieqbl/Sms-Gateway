package gateway.template;


import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.DirectoryCodeResolver;

import java.nio.file.Path;

import static gateway.template.TemplateConfig.TemplatesPath;
import static gateway.template.TemplateConfig.classDirectory;


public final class TemplateEngineProvider
{
    public static TemplateEngine engine;

    private TemplateEngineProvider()
    {}

    public static TemplateEngine getEngine()
    {
        if (engine == null)
        {
            synchronized (TemplateEngineProvider.class)
            {
                if (engine == null)
                {
                    Path TemplatesPath = Path.of("gateway","src","gateway","template","templates");
                    var resolver = new DirectoryCodeResolver(TemplatesPath);
                    engine = TemplateEngine.create(resolver,classDirectory(),ContentType.Html);
                }
            }
        }
        return engine;
    }
}
