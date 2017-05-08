// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.model.survey.Survey;
import com.netflix.mediaclient.android.app.Status;

class UserAgent$FetchSurveyTask$1$1 implements Runnable
{
    final /* synthetic */ UserAgent$FetchSurveyTask$1 this$2;
    final /* synthetic */ Status val$res;
    final /* synthetic */ Survey val$survey;
    
    UserAgent$FetchSurveyTask$1$1(final UserAgent$FetchSurveyTask$1 this$2, final Survey val$survey, final Status val$res) {
        this.this$2 = this$2;
        this.val$survey = val$survey;
        this.val$res = val$res;
    }
    
    @Override
    public void run() {
        this.this$2.this$1.getCallback().onSurveyFetched(this.val$survey, this.val$res);
    }
}
