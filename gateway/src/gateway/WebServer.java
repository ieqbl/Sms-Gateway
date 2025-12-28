package gateway;

import static spark.Spark.*;
import gateway.database.Database;
import gateway.database.Message;
import gateway.template.TemplateEngineProvider;
import gg.jte.output.StringOutput;
import spark.TemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class WebServer
{
    public static void start()
    {
        port(8080);
        get("/", (request, response) ->
        {
            DbServices db = new DbServices();
            db.connect();

            Database database = new Database(db.getConnection());
            List<Message> messages = database.getMessages();

            Map<String, Object> model = new HashMap<>();
            model.put("messages",messages);

            var output = new StringOutput();
            var engine = TemplateEngineProvider.getEngine();
            engine.render("index.jte", model, output);

            db.close();
            response.type("text/html");
            return output.toString();
        });
    }
}
