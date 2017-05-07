// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient;

public enum StatusCode
{
    BROWSE_AGENT_WRONG_STATE(-66), 
    BROWSE_CW_WRONG_STATE(-68), 
    BROWSE_IQ_WRONG_STATE(-67), 
    CONFIG_DOWNLOAD_FAILED(-12), 
    CONFIG_REFRESH_FAILED(-7), 
    DRM_FAILURE_CDM(-100), 
    DRM_FAILURE_GOOGLE_CDM_PROVISIONG_DENIED(-101), 
    FALCOR_RESPONSE_PARSE_ERROR(-80), 
    FORCED_TESTING_ERROR(-8), 
    HTTP_SSL_DATE_TIME_ERROR(-121), 
    HTTP_SSL_ERROR(-120), 
    HTTP_SSL_NO_VALID_CERT(-122), 
    INIT_SERVICE_TIMEOUT(-9), 
    INTERNAL_ERROR(-2), 
    MAP_ERROR(-65), 
    MISSING_ID_IN_CACHE(-63), 
    NETWORK_ERROR(-3), 
    NON_RECOMMENDED_APP_VERSION(1), 
    NOT_IMPLEMENTED(-10), 
    NO_CONNECTIVITY(-11), 
    NRD_ERROR(-4), 
    NRD_INVALID_SW_VERSION(-20), 
    NRD_LOGIN_ACTIONID_1(-200), 
    NRD_LOGIN_ACTIONID_10(-209), 
    NRD_LOGIN_ACTIONID_11(-210), 
    NRD_LOGIN_ACTIONID_12(-211), 
    NRD_LOGIN_ACTIONID_2(-201), 
    NRD_LOGIN_ACTIONID_3(-202), 
    NRD_LOGIN_ACTIONID_4(-203), 
    NRD_LOGIN_ACTIONID_5(-204), 
    NRD_LOGIN_ACTIONID_6(-205), 
    NRD_LOGIN_ACTIONID_7(-206), 
    NRD_LOGIN_ACTIONID_8(-207), 
    NRD_LOGIN_ACTIONID_9(-208), 
    NRD_LOGIN_ONGOING(-40), 
    NRD_REGISTRATION_EXISTS(-41), 
    NRD_REGISTRATION_INVALID_CREDENTIALS(-42), 
    OBSOLETE_APP_VERSION(-5), 
    OK(0), 
    RESPONSE_PARSE_ERROR(-300), 
    SERVER_ERROR(-62), 
    SERVER_ERROR_MAP_CACHE_MISS(-64), 
    SET_FAILED(-6), 
    UNKNOWN(-1), 
    USER_FB_CONNECT_FAILURE(-56), 
    USER_FB_CONNECT_ID_ALREADY_IN_USE(-52), 
    USER_FB_CONNECT_INVALID_CREDENTIALS(-51), 
    USER_FB_CONNECT_MISSING_PARAMS(-50), 
    USER_FB_CONNECT_RETRY_AFTER_FB_SMS(-53), 
    USER_FB_TRANSIENT_DO_NOT_RETRY(-54), 
    USER_FB_TRANSIENT_RETRY(-55), 
    USER_NOT_AUTHORIZED(-61), 
    WRONG_PATH(-60);
    
    private int mValue;
    
    private StatusCode(final int mValue) {
        this.mValue = mValue;
    }
    
    public static StatusCode getStatusCodeByValue(final int n) {
        final StatusCode[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final StatusCode statusCode = values[i];
            if (statusCode != null && statusCode.getValue() == n) {
                return statusCode;
            }
        }
        return null;
    }
    
    public static boolean isSucess(final int n) {
        return n >= 0;
    }
    
    public int getValue() {
        return this.mValue;
    }
    
    public boolean isError() {
        return this.mValue < 0;
    }
    
    public boolean isSucess() {
        return !this.isError();
    }
    
    @Override
    public String toString() {
        return "StatusCode: [ code: " + this.mValue + ", name: " + this.name() + "]";
    }
}
