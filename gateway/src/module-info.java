module gateway {
    requires sms.api;
    requires java.sql;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.web;

    uses api.SmsDriver;

    opens gateway to spring.core, spring.beans, spring.context;
    opens gateway.model to spring.core, spring.beans, spring.context;
    opens gateway.repository to spring.core, spring.beans, spring.context;

}
