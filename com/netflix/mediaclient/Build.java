// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient;

public interface Build
{
    public static final boolean debug = false;
    public static final boolean enableTestServer = false;
    
    public interface Config
    {
        public interface Logging
        {
            public static final String CRITTER_VERSION_NAME = "3.9.0";
            public static final boolean ENABLE_CRITTERCISM = true;
        }
        
        public interface Push
        {
            public static final String SENDER_ID = "484286080282";
        }
        
        public interface WebClient
        {
            public static final String SELECTED_WEBCLIENT = "volley";
        }
    }
    
    public interface EndPoints
    {
        public static final String ANDROID_CONFIG_ENDPOINT_FULL = "/android/samurai/config";
        public static final String ANDROID_ENDPOINT_FULL = "/android/3.9/api";
        public static final String BOOTLOADER_URL = "https://www.netflix.com/applanding/android";
        public static final String CLIENT_LOGGING_ENDPOINT = "ichnaea.netflix.com";
        public static final String CLIENT_LOGGING_PATH = "/log";
        public static final String CMS_BEACON_ENDPOINT_URL = "ichnaea.netflix.com";
        public static final String CUSTOMER_EVENTS_BEACON_ENDPOINT_URL = "customerevents.netflix.com";
        public static final String PRESENTATION_TRACKING_ENDPOINT = "presentationtracking.netflix.com";
        public static final String PRESENTATION_TRACKING_PATH = "/users/presentationtracking";
        public static final String WEBAPI_ENDPOINT_URL = "api-global.netflix.com";
        public static final String WEBCLIENT_ENDPOINT = "api-global.netflix.com";
    }
    
    public interface Versions
    {
        public static final String MDXJS_VERSION_VALUE = "1.1.6-android";
        public static final String MDXLIB_VERSION_VALUE = "2013.3";
        public static final String NRDAPP_VERSION_VALUE = "2013.2";
        public static final String NRDLIB_VERSION_VALUE = "2013.2";
        public static final String NRD_SDK_VERSION_VALUE = "4.1";
    }
}
