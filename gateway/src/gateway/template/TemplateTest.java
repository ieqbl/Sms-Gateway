package gateway.template;

import gg.jte.TemplateEngine;
import gg.jte.output.StringOutput;
import gg.jte.resolve.DirectoryCodeResolver;
import java.nio.file.Path;
import java.util.Map;

public class TemplateTest {
    public static void main(String[] args) {
        var resolver = new DirectoryCodeResolver(Path.of("gateway/src/gateway/template/templates"));
        var engine = TemplateEngine.create(resolver, Path.of("target/jte-classes"), gg.jte.ContentType.Plain);

        var output = new StringOutput();
        engine.render("index.jte", Map.of("name", "Eghbali"), output);
        System.out.println(output.toString());
    }
}
