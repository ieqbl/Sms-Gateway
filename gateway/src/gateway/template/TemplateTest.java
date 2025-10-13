package gateway.template;

public class TemplateTest
{
    public static void main(String[] args)
    {
        TemplateRenderer rend = new TemplateRenderer();

        var model = new Object()
        {
            public final String user = "Sir";
        };


        String output = rend.render("index.jte",model);

        System.out.println(output);
    }

}
