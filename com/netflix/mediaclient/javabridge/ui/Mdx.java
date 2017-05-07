// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui;

import org.json.JSONArray;
import java.util.Map;

public interface Mdx
{
    public static final String BOTH = "BOTH";
    public static final String COMPLETE = "COMPLETE";
    public static final String CONTROLLER = "CONTROLLER";
    public static final String MDX_ERR_NETWORK = "3";
    public static final String MDX_ERR_NOT_PAIRED = "12";
    public static final String MDX_ERR_NO_CTICKET = "13";
    public static final String MDX_ERR_PAIRING_NO_MATCH = "10";
    public static final String MDX_ERR_PORT = "2";
    public static final String MDX_ERR_SESSION_DECRYPTION_FAILED = "6";
    public static final String MDX_ERR_SESSION_INVALID_HMAC = "5";
    public static final String MDX_ERR_SESSION_INVALID_SID = "11";
    public static final String MDX_ERR_SSDP = "1";
    public static final String MDX_ERR_VERSION = "4";
    public static final int MDX_LAUNCHED = 1;
    public static final int MDX_NOTLAUNCHED = 0;
    public static final String MDX_PAIRING_ALREADY_PAIRED = "31";
    public static final String MDX_PAIRING_CONTROLLER_CTICKET_CORRUPTED = "22";
    public static final String MDX_PAIRING_CONTROLLER_CTICKET_EXPIRED = "21";
    public static final String MDX_PAIRING_CONTROLLER_HMAC_FAILURE = "20";
    public static final String MDX_PAIRING_INVALID_CONTROLLER_REQUEST = "10";
    public static final String MDX_PAIRING_NETWORK_ERROR = "99";
    public static final String MDX_PAIRING_NOT_PAIRING = "30";
    public static final String MDX_PAIRING_NO_ERROR = "0";
    public static final String MDX_PAIRING_SERVER_ERROR = "11";
    public static final String MDX_PAIRING_SERVER_NOT_REACHABLE = "13";
    public static final String MDX_PAIRING_TARGET_ERROR = "12";
    public static final String MDX_PAIRING_UNKNOWN_VERSION = "1";
    public static final String MDX_PAIRING_USER_MISMATCH = "USER_MISMATCH";
    public static final String MDX_PAIRING_USER_PAIRING_FAILED = "PAIRING_FAILED";
    public static final String MDX_REGISTRATION_ALREADY_REGISTERED = "41";
    public static final int MDX_REGISTRATION_DEFAULT_PIN = 2;
    public static final int MDX_REGISTRATION_DISABLED = 0;
    public static final int MDX_REGISTRATION_ENABLED = 1;
    public static final String MDX_REGISTRATION_NOT_REGISTERING = "40";
    public static final String MDX_REGISTRATION_PAIRING_IN_PROGRESS = "42";
    public static final String MDX_REGISTRATION_PIN_MISMATCH = "43";
    public static final String MDX_SESSION_DECRYPTION_FAILED = "6";
    public static final String MDX_SESSION_INVALID_HMAC = "5";
    public static final String MDX_SESSION_INVALID_NONCE = "4";
    public static final String MDX_SESSION_INVALID_SID = "11";
    public static final String MDX_SESSION_NETWORK_ERROR = "10";
    public static final String MDX_SESSION_UNKNOWN_RECEIVER = "3";
    public static final String MDX_SESSION_UNKNOWN_RECEIVER_USERID = "9";
    public static final String MDX_SESSION_UNKNOWN_SENDER = "2";
    public static final String MDX_SESSION_UNKNOWN_SENDER_USERID = "8";
    public static final String MDX_SESSION_UNKNOWN_VERSION = "1";
    public static final String NAME = "mdx";
    public static final String PATH = "nrdp.mdx";
    public static final String TARGET = "TARGET";
    
    void addEventListener(final String p0, final EventListener p1);
    
    void clearDeviceMap();
    
    void configure(final Map<String, String> p0);
    
    void exit();
    
    void init(final Map<String, String> p0, final boolean p1, final JSONArray p2);
    
    void removeEventListener(final String p0, final EventListener p1);
    
    public enum Events
    {
        mdx_discovery_devicefound("devicefound"), 
        mdx_discovery_devicelost("devicelost"), 
        mdx_discovery_remoteDeviceReady("remoteDeviceReady"), 
        mdx_init("init"), 
        mdx_initerror("initerror"), 
        mdx_mdxstate("mdxstate"), 
        mdx_pair_pairingdeleted("pairingdeleted"), 
        mdx_pair_pairingresponse("pairingresponse"), 
        mdx_pair_regpairresponse("regpairresponse"), 
        mdx_session_message("message"), 
        mdx_session_messagedelivered("messagedelivered"), 
        mdx_session_messagingerror("messagingerror"), 
        mdx_session_sessionended("sessionended"), 
        mdx_session_startSessionResponse("startSessionResponse");
        
        protected String name;
        
        private Events(final String name) {
            this.name = name;
        }
        
        public final String getName() {
            return this.name;
        }
    }
}
