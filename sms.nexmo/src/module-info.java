module sms.nexmo
{
    requires sms.api;
    provides api.SmsDriver with Ndrivers.Nexmodriver;
}