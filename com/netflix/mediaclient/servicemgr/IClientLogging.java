// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.service.logging.client.model.SessionKey;
import java.util.List;
import com.netflix.mediaclient.javabridge.ui.LogArguments;

public interface IClientLogging
{
    public static final String APM_LOGGING_REQUEST_DELIVERY_STATUS = "com.netflix.mediaclient.intent.action.APM_LOGGING_REQUEST_DELIVERY_STATUS";
    public static final String CATEGORY_NFLOGGING = "com.netflix.mediaclient.intent.category.LOGGING";
    public static final String EXTRA_DELIVERED = "delivered";
    public static final String EXTRA_FLUSH = "flush";
    public static final String PAUSE_EVENTS_DELIVERY = "com.netflix.mediaclient.intent.action.LOG_PAUSE_EVENTS_DELIVERY";
    public static final String PRESENTATION_TRACKING_LOGGING_REQUEST_DELIVERY_STATUS = "com.netflix.mediaclient.intent.action.PRESENTATION_TRACKING_LOGGING_REQUEST_DELIVERY_STATUS";
    public static final String RESUME_EVENTS_DELIVERY = "com.netflix.mediaclient.intent.action.LOG_RESUME_EVENTS_DELIVERY";
    
    void NrdpLog(final LogArguments p0);
    
    void flushLoggingEvents();
    
    List<SessionKey> getActiveLoggingSessions();
    
    AdvertiserIdLogging getAdvertiserIdLogging();
    
    ApplicationPerformanceMetricsLogging getApplicationPerformanceMetricsLogging();
    
    BreadcrumbLogging getBreadcrumbLogging();
    
    CmpEventLogging getCmpEventLogging();
    
    CustomerEventLogging getCustomerEventLogging();
    
    ErrorLogging getErrorLogging();
    
    long getNextSequence();
    
    PresentationTracking getPresentationTracking();
    
    void onPlayEnd();
    
    void onProfileSwitch();
    
    void onUserLogout();
    
    void pauseDelivery();
    
    void resumeDelivery(final boolean p0);
    
    void setDataContext(final DataContext p0);
}
