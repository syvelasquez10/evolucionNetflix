// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm;

import com.netflix.mediaclient.service.logging.apm.model.UIStartupSessionEndedEvent;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$UiStartupTrigger;
import com.netflix.mediaclient.service.logging.apm.model.Display;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.apm.model.DeepLink;
import com.netflix.mediaclient.service.logging.apm.model.UIBrowseStartupSessionCustomData;
import java.util.Map;

public final class UIStartupSession extends BaseApmSession
{
    public static final String NAME = "uiStartup";
    private Map<String, Integer> mActiveABTests;
    private UIBrowseStartupSessionCustomData mCustomData;
    private DeepLink mDeepLink;
    private IClientLogging$ModalView mDestination;
    private Display mDisplay;
    private String mSearchTerm;
    private int mTrackId;
    private ApplicationPerformanceMetricsLogging$UiStartupTrigger mTrigger;
    
    public UIStartupSession(final ApplicationPerformanceMetricsLogging$UiStartupTrigger applicationPerformanceMetricsLogging$UiStartupTrigger, final IClientLogging$ModalView clientLogging$ModalView, final int mTrackId, final String mSearchTerm, final Map<String, Integer> mActiveABTests, final Display display, final DeepLink deepLink, final UIBrowseStartupSessionCustomData uiBrowseStartupSessionCustomData) {
        this(applicationPerformanceMetricsLogging$UiStartupTrigger, clientLogging$ModalView, display, deepLink, uiBrowseStartupSessionCustomData);
        this.mTrackId = mTrackId;
        this.mSearchTerm = mSearchTerm;
        this.mActiveABTests = mActiveABTests;
    }
    
    public UIStartupSession(final ApplicationPerformanceMetricsLogging$UiStartupTrigger mTrigger, final IClientLogging$ModalView mDestination, final Display mDisplay, final DeepLink mDeepLink, final UIBrowseStartupSessionCustomData mCustomData) {
        this.mTrigger = mTrigger;
        this.mDestination = mDestination;
        this.mDisplay = mDisplay;
        this.mDeepLink = mDeepLink;
        this.mCustomData = mCustomData;
    }
    
    public UIStartupSessionEndedEvent createEndedEvent(final boolean b, final UIError error, final PlayerType playerType) {
        final UIStartupSessionEndedEvent uiStartupSessionEndedEvent = new UIStartupSessionEndedEvent(System.currentTimeMillis() - this.mStarted, this.mTrigger, this.mDestination, b, playerType, this.mDeepLink, this.mCustomData);
        uiStartupSessionEndedEvent.setCategory(this.getCategory());
        uiStartupSessionEndedEvent.setTrackId("" + this.mTrackId);
        uiStartupSessionEndedEvent.setActiveABTests(this.mActiveABTests);
        uiStartupSessionEndedEvent.setSearchTerm(this.mSearchTerm);
        uiStartupSessionEndedEvent.setError(error);
        uiStartupSessionEndedEvent.setDisplay(this.mDisplay);
        return uiStartupSessionEndedEvent;
    }
    
    public Map<String, Integer> getActiveABTests() {
        return this.mActiveABTests;
    }
    
    public IClientLogging$ModalView getDestination() {
        return this.mDestination;
    }
    
    @Override
    public String getName() {
        return "uiStartup";
    }
    
    public String getSearchTerm() {
        return this.mSearchTerm;
    }
    
    public int getTrackId() {
        return this.mTrackId;
    }
    
    public ApplicationPerformanceMetricsLogging$UiStartupTrigger getTrigger() {
        return this.mTrigger;
    }
    
    public void setActiveABTests(final Map<String, Integer> mActiveABTests) {
        this.mActiveABTests = mActiveABTests;
    }
    
    public void setDestination(final IClientLogging$ModalView mDestination) {
        this.mDestination = mDestination;
    }
    
    public void setSearchTerm(final String mSearchTerm) {
        this.mSearchTerm = mSearchTerm;
    }
    
    public void setTrackId(final int mTrackId) {
        this.mTrackId = mTrackId;
    }
    
    public void setTrigger(final ApplicationPerformanceMetricsLogging$UiStartupTrigger mTrigger) {
        this.mTrigger = mTrigger;
    }
}
