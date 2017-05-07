// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.social;

import com.netflix.mediaclient.service.logging.social.model.SocialImpressionSessionEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.IClientLogging;

public class SocialImpressionSession extends BaseSocialSession
{
    public static final String NAME = "impression";
    private String mStoryId;
    private int mTrackId;
    private IClientLogging.ModalView mView;
    
    public SocialImpressionSession(final IClientLogging.ModalView mView, final String mStoryId, final int mTrackId) {
        this.mView = mView;
        this.mStoryId = mStoryId;
        this.mTrackId = mTrackId;
    }
    
    public SocialImpressionSessionEndedEvent createEndEvent(final boolean b, final Error error) {
        final SocialImpressionSessionEndedEvent socialImpressionSessionEndedEvent = new SocialImpressionSessionEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mStoryId, this.mTrackId, b, error);
        socialImpressionSessionEndedEvent.setCategory(this.getCategory());
        socialImpressionSessionEndedEvent.setSessionId(this.mId);
        return socialImpressionSessionEndedEvent;
    }
    
    @Override
    public String getName() {
        return "impression";
    }
}
