module gateway {
    requires sms.api;
    requires java.sql;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.web;

    uses api.SmsDriver;

    opens com.example.gateway to spring.core, spring.beans, spring.context;
}
