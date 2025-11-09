module gateway
{
    requires sms.api;
    requires java.sql;
    requires gg.jte;
    requires gg.jte.runtime;
    requires static spark.core;
    requires gg.jte.extension.api;
    exports gateway.database;
    exports gateway.template;
    uses api.SmsDriver;

}