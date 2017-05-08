// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.model.survey.Survey;

class UserAgent$FetchSurveyTask$1 extends SimpleUserAgentWebCallback
{
    final /* synthetic */ UserAgent$FetchSurveyTask this$1;
    
    UserAgent$FetchSurveyTask$1(final UserAgent$FetchSurveyTask this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onSurveyFetched(final Survey survey, final Status status) {
        this.this$1.this$0.getMainHandler().post((Runnable)new UserAgent$FetchSurveyTask$1$1(this, survey, status));
    }
}
