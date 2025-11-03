package gg.jte.generated.ondemand;
import gateway.database.Message;
import java.util.List;
@SuppressWarnings("unchecked")
public final class JteindexGenerated {
	public static final String JTE_NAME = "index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,2,20,20,20,22,22,29,29,31,31,31,32,32,32,33,33,33,35,35,37,37,40,40,40,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, List<Message> messages) {
		jteOutput.writeContent("\r\n<!DOCTYPE html>\r\n<html>\r\n<head>\r\n    <meta charset=\"UTF-8\">\r\n    <title>Messages</title>\r\n    <style>\r\n        body { font-family: Arial, sans-serif; margin: 40px; }\r\n        table { border-collapse: collapse; width: 100%; }\r\n        th, td { border: 1px solid #ccc; padding: 8px; text-align: left; }\r\n        th { background-color: #f2f2f2; }\r\n        tr:hover { background-color: #f9f9f9; }\r\n    </style>\r\n</head>\r\n<body>\r\n<h2>Messages</h2>\r\n\r\n");
		if (messages.isEmpty()) {
			jteOutput.writeContent("\r\n    <p>No messages found.</p>\r\n");
		} else {
			jteOutput.writeContent("\r\n    <table>\r\n        <tr>\r\n            <th>ID</th>\r\n            <th>Phone</th>\r\n            <th>Content</th>\r\n        </tr>\r\n        ");
			for (var message : messages) {
				jteOutput.writeContent("\r\n            <tr>\r\n                <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(message.id);
				jteOutput.writeContent("</td>\r\n                <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(message.phone);
				jteOutput.writeContent("</td>\r\n                <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(message.content);
				jteOutput.writeContent("</td>\r\n            </tr>\r\n        ");
			}
			jteOutput.writeContent("\r\n    </table>\r\n");
		}
		jteOutput.writeContent("\r\n</body>\r\n</html>\r\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		List<Message> messages = (List<Message>)params.get("messages");
		render(jteOutput, jteHtmlInterceptor, messages);
	}
}
