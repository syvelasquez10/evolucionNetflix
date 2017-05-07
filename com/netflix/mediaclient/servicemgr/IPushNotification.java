// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import android.content.Intent;

public interface IPushNotification
{
    public static final String CATEGORY_NFPUSH = "com.netflix.mediaclient.intent.category.PUSH";
    public static final String EXTRA_CP_UID = "cp_uid";
    public static final String EXTRA_DEVICECATEGORY = "device_cat";
    public static final String EXTRA_ESN = "esn";
    public static final String EXTRA_MSG = "msg";
    public static final String EXTRA_NETFLIXID = "nid";
    public static final String EXTRA_REGISTRATIONID = "reg_id";
    public static final String EXTRA_SECURENETFLIXID = "sid";
    public static final String EXTRA_UID = "uid";
    public static final String GCM_ON_MESSAGE = "com.netflix.mediaclient.intent.action.PUSH_NOTIFICATION_GCM_ONMESSAGE";
    public static final String GCM_ON_REGISTERED = "com.netflix.mediaclient.intent.action.PUSH_NOTIFICATION_GCM_ONREGISTERED";
    public static final String GCM_ON_UNREGISTERED = "com.netflix.mediaclient.intent.action.PUSH_NOTIFICATION_GCM_ONUNREGISTERED";
    public static final String NOTIFICATION_CANCELED = "com.netflix.mediaclient.intent.action.NOTIFICATION_CANCELED";
    public static final String ONLOGIN = "com.netflix.mediaclient.intent.action.PUSH_ONLOGIN";
    public static final String ONLOGOUT = "com.netflix.mediaclient.intent.action.PUSH_ONLOGOUT";
    public static final String PUSH_OPTIN = "com.netflix.mediaclient.intent.action.PUSH_NOTIFICATION_OPTIN";
    public static final String PUSH_OPTOUT = "com.netflix.mediaclient.intent.action.PUSH_NOTIFICATION_OPTOUT";
    
    boolean isRegistered();
    
    boolean isSupported();
    
    void onMessage(final Intent p0);
    
    void setRegistrationIdFromRegistrationServer(final String p0);
    
    void unregistrationFromFromRegistrationServer(final String p0);
    
    boolean wasNotificationOptInDisplayed();
}
