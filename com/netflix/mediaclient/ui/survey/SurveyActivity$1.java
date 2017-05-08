// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.survey;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import android.content.Context;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import android.app.Activity;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.model.survey.Survey;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

final class SurveyActivity$1 extends SimpleManagerCallback
{
    final /* synthetic */ NetflixActivity val$activity;
    
    SurveyActivity$1(final NetflixActivity val$activity) {
        this.val$activity = val$activity;
    }
    
    @Override
    public void onSurveyFetched(final Survey survey, final Status status) {
        if (status.isSucces()) {
            if (survey != null && !survey.isEmpty()) {
                SurveyActivity.showSurvey(this.val$activity, survey);
            }
            else {
                UserActionLogUtils.reportSurveyEnded((Context)this.val$activity, IClientLogging$CompletionReason.success, null, "");
                if (Log.isLoggable()) {
                    Log.i("SurveyActivity", "No Survey available!");
                }
            }
        }
        else {
            UserActionLogUtils.reportSurveyEnded((Context)this.val$activity, IClientLogging$CompletionReason.failed, null, "");
            if (Log.isLoggable()) {
                Log.i("SurveyActivity", "Survey failed!");
            }
        }
    }
}
