// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.survey;

import com.netflix.mediaclient.util.log.ApmLogUtils;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import com.netflix.mediaclient.Log;
import android.support.v4.app.Fragment;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.os.Parcelable;
import android.content.Intent;
import com.netflix.model.survey.Survey;
import android.app.Activity;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import android.content.Context;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.model.survey.SurveyQuestion;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class SurveyActivity extends NetflixActivity implements SurveyListener
{
    public static final String EXTRA_SURVEY = "extra_survey";
    public static final String TAG = "SurveyActivity";
    private static boolean hasCompleted;
    SurveyQuestion firstQuestion;
    
    public static void makeSurveyRequestAndShow(final NetflixActivity netflixActivity) {
        UserActionLogUtils.reportSurveyStarted((Context)netflixActivity, null, netflixActivity.getUiScreen());
        netflixActivity.getServiceManager().fetchSurvey(new SurveyActivity$1(netflixActivity));
    }
    
    public static boolean shouldShowSurvey(final Context context, final ServiceManager serviceManager) {
        return PersistentConfig.getBrandLoveSurveyTestCell(context) == ABTestConfig$Cell.CELL_ONE && serviceManager != null && serviceManager.getCurrentProfile() != null && !serviceManager.getCurrentProfile().isKidsProfile() && !SurveyActivity.hasCompleted;
    }
    
    public static void showSurvey(final Activity activity, final Survey survey) {
        final Intent intent = new Intent((Context)activity, (Class)SurveyActivity.class);
        intent.putExtra("extra_survey", (Parcelable)survey);
        activity.startActivity(intent);
    }
    
    @Override
    public IClientLogging$ModalView getUiScreen() {
        return IClientLogging$ModalView.survey;
    }
    
    @Override
    protected boolean handleBackPressed() {
        this.onSkipped();
        return true;
    }
    
    @Override
    public boolean isLoadingData() {
        return false;
    }
    
    @Override
    public void onCompleted(final int n) {
        if (!SurveyActivity.hasCompleted) {
            SurveyActivity.hasCompleted = true;
            UserActionLogUtils.reportSurveyEnded((Context)this, IClientLogging$CompletionReason.success, null, this.firstQuestion.getType());
            UserActionLogUtils.reportSurveyQuestionEnded((Context)this, IClientLogging$CompletionReason.success, null, this.firstQuestion.getId(), String.valueOf(n));
            final ThanksFragment thanksFragment = new ThanksFragment();
            final FragmentTransaction beginTransaction = this.getSupportFragmentManager().beginTransaction();
            beginTransaction.setCustomAnimations(17432576, 17432577);
            beginTransaction.replace(2131755158, thanksFragment);
            beginTransaction.commit();
            if (Log.isLoggable()) {
                Log.i("SurveyActivity", "Survey completed! " + this.firstQuestion);
            }
        }
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130903071);
        ApmLogUtils.reportUiModalViewChanged((Context)this, this.getUiScreen());
        final Survey survey = (Survey)this.getIntent().getParcelableExtra("extra_survey");
        if (survey == null || survey.getQuestionTotal() == 0) {
            this.finish();
            return;
        }
        this.firstQuestion = survey.getFirstQuestion();
        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().hide();
        }
        UserActionLogUtils.reportSurveyQuestionStarted((Context)this, null, this.getUiScreen());
        final SurveyFragment instance = SurveyFragment.getInstance(survey);
        final FragmentTransaction beginTransaction = this.getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(2131755158, instance);
        beginTransaction.commit();
    }
    
    @Override
    protected void onDestroy() {
        this.onSurveyMessageRead();
        super.onDestroy();
    }
    
    @Override
    public void onSkipped() {
        if (!SurveyActivity.hasCompleted) {
            SurveyActivity.hasCompleted = true;
            UserActionLogUtils.reportSurveyEnded((Context)this, IClientLogging$CompletionReason.canceled, null, this.firstQuestion.getType());
            UserActionLogUtils.reportSurveyQuestionEnded((Context)this, IClientLogging$CompletionReason.canceled, null, this.firstQuestion.getId(), "0");
            this.finish();
            if (Log.isLoggable()) {
                Log.i("SurveyActivity", "Survey cancelled! " + this.firstQuestion);
            }
        }
    }
    
    public void onSurveyMessageRead() {
        this.getServiceManager().markSurveysAsRead();
    }
}
