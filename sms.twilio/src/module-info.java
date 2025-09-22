import api.SmsDriver;
import Tdrivers.Twiliodriver;

module sms.twilio
{
    requires sms.api;
    provides SmsDriver with Twiliodriver;
}