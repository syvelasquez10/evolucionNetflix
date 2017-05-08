// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.ums;

import com.netflix.mediaclient.ui.home.HomeActivity;
import android.view.View;
import android.text.method.LinkMovementMethod;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.service.webclient.model.leafs.EogAlert;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import android.content.Context;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import android.support.v4.content.ContextCompat;
import android.widget.LinearLayout;
import android.widget.CheckBox;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

public class EogPlanPage
{
    private static final String TAG = "eog_plan_page";
    private TextView mAccount;
    private Drawable mActive_plan_drawable;
    private TextView mContinue;
    private TextView mDisclaimer;
    private CheckBox mHdCheckBox;
    private TextView mHdName;
    private LinearLayout mHdPlanHeader;
    private TextView mHdValue;
    private Drawable mInactive_plan_drawable;
    private EndOfGrandfatheringActivity mOwner;
    private CheckBox mSdCheckBox;
    private TextView mSdName;
    private LinearLayout mSdPlanHeader;
    private TextView mSdValue;
    private TextView mSelect;
    private TextView mTitle;
    private CheckBox mUhdCheckBox;
    private TextView mUhdName;
    private LinearLayout mUhdPlanHeader;
    private TextView mUhdValue;
    
    EogPlanPage(final EndOfGrandfatheringActivity mOwner) {
        this.mOwner = mOwner;
        this.mTitle = (TextView)this.mOwner.findViewById(2131689799);
        this.mSelect = (TextView)this.mOwner.findViewById(2131689800);
        this.mSdName = (TextView)this.mOwner.findViewById(2131689803);
        this.mSdValue = (TextView)this.mOwner.findViewById(2131689804);
        this.mHdName = (TextView)this.mOwner.findViewById(2131689807);
        this.mHdValue = (TextView)this.mOwner.findViewById(2131689808);
        this.mUhdName = (TextView)this.mOwner.findViewById(2131689811);
        this.mUhdValue = (TextView)this.mOwner.findViewById(2131689812);
        this.mAccount = (TextView)this.mOwner.findViewById(2131689813);
        this.mContinue = (TextView)this.mOwner.findViewById(2131689815);
        this.mDisclaimer = (TextView)this.mOwner.findViewById(2131689816);
        this.mSdCheckBox = (CheckBox)this.mOwner.findViewById(2131689802);
        this.mHdCheckBox = (CheckBox)this.mOwner.findViewById(2131689806);
        this.mUhdCheckBox = (CheckBox)this.mOwner.findViewById(2131689810);
        this.mSdPlanHeader = (LinearLayout)this.mOwner.findViewById(2131689801);
        this.mHdPlanHeader = (LinearLayout)this.mOwner.findViewById(2131689805);
        this.mUhdPlanHeader = (LinearLayout)this.mOwner.findViewById(2131689809);
        this.mInactive_plan_drawable = ContextCompat.getDrawable(this.mOwner.getApplicationContext(), 2130837657);
        this.mActive_plan_drawable = ContextCompat.getDrawable(this.mOwner.getApplicationContext(), 2130837656);
    }
    
    private void logPlanChangeEventByUser(final int n) {
        final EogAlert eogAlert = this.mOwner.getEogAlert();
        if (eogAlert == null) {
            Log.e("eog_plan_page", "skip cl logging for plan change - eogAlert null");
        }
        else {
            String s = null;
            switch (n) {
                default: {
                    s = null;
                    break;
                }
                case 0: {
                    s = eogAlert.sdPlanPlanId;
                    break;
                }
                case 1: {
                    s = eogAlert.hdPlanPlanId;
                    break;
                }
                case 2: {
                    s = eogAlert.uhdPlanPlanId;
                    break;
                }
            }
            if (!StringUtils.isEmpty(s)) {
                if (Log.isLoggable()) {
                    Log.d("eog_plan_page", String.format("cl logging planChange planIndex:%d, planId:%s", n, s));
                }
                UserActionLogUtils.reportChangeValueActionStarted((Context)this.mOwner, null, IClientLogging$ModalView.eogPlanSelector);
                UserActionLogUtils.reportChangeValueActionEnded((Context)this.mOwner, IClientLogging$CompletionReason.success, null, s);
                return;
            }
            if (Log.isLoggable()) {
                Log.e("eog_plan_page", String.format("skip cl logging - bad plandIndex:%d", n));
            }
        }
    }
    
    private void setPlanSelectionUi(final int currentPlanIndex) {
        this.setPlanSelectionUiToDefault();
        switch (currentPlanIndex) {
            default: {
                if (Log.isLoggable()) {
                    Log.e("eog_plan_page", "No plan is selected. planIndex:" + currentPlanIndex);
                }
                this.mHdCheckBox.setChecked(true);
                break;
            }
            case 0: {
                this.mSdCheckBox.setChecked(true);
                this.mSdPlanHeader.setBackground(this.mActive_plan_drawable);
                break;
            }
            case 1: {
                this.mHdCheckBox.setChecked(true);
                this.mHdPlanHeader.setBackground(this.mActive_plan_drawable);
                break;
            }
            case 2: {
                this.mUhdCheckBox.setChecked(true);
                this.mUhdPlanHeader.setBackground(this.mActive_plan_drawable);
                break;
            }
        }
        this.mOwner.setCurrentPlanIndex(currentPlanIndex);
    }
    
    private void setPlanSelectionUiToDefault() {
        this.mSdCheckBox.setChecked(false);
        this.mHdCheckBox.setChecked(false);
        this.mUhdCheckBox.setChecked(false);
        this.mSdPlanHeader.setBackground(this.mInactive_plan_drawable);
        this.mHdPlanHeader.setBackground(this.mInactive_plan_drawable);
        this.mUhdPlanHeader.setBackground(this.mInactive_plan_drawable);
    }
    
    public void initUi() {
        if (!this.mOwner.canProceed()) {
            return;
        }
        final EogAlert eogAlert = this.mOwner.getEogAlert();
        this.mTitle.setText((CharSequence)eogAlert.title);
        final TextView mSelect = this.mSelect;
        String text;
        if (EogUtils.isPlanOnlyCell(eogAlert)) {
            text = eogAlert.body;
        }
        else {
            text = eogAlert.selectPlanText;
        }
        mSelect.setText((CharSequence)text);
        this.mSdName.setText((CharSequence)eogAlert.sdPlanText);
        this.mSdValue.setText((CharSequence)eogAlert.sdPlanPrice);
        this.mHdName.setText((CharSequence)eogAlert.hdPlanText);
        this.mHdValue.setText((CharSequence)eogAlert.hdPlanPrice);
        this.mUhdName.setText((CharSequence)eogAlert.uhdPlanText);
        this.mUhdValue.setText((CharSequence)eogAlert.uhdPlanPrice);
        this.setPlanSelectionUi(this.mOwner.getCurrentPlanIndex());
        this.mAccount.setText((CharSequence)EogLandingPage.buildAccountString(this.mOwner, this.mOwner.getEogAlert()));
        this.mAccount.setMovementMethod(LinkMovementMethod.getInstance());
        this.mContinue.setText((CharSequence)eogAlert.continueBtnText);
        this.mDisclaimer.setText((CharSequence)eogAlert.disclaimerText);
    }
    
    public boolean performAction(final View view) {
        if (view == null) {
            Log.e("eog_plan_page", "EogPlanPage:: null view? This should never happen!");
            return true;
        }
        switch (view.getId()) {
            default: {
                if (Log.isLoggable()) {
                    Log.w("eog_plan_page", "Uknown view, unable to handle: " + view.getId());
                }
                return false;
            }
            case 2131689814: {
                this.mOwner.recordPlanSelection();
                if (EndOfGrandfatheringActivity.shouldBlockUser(this.mOwner.getEogAlert().isBlocking)) {
                    this.mOwner.startActivity(HomeActivity.createStartIntent(this.mOwner));
                }
                this.mOwner.finish();
                return true;
            }
        }
    }
    
    public boolean performPlanSelection(final View view) {
        if (view == null) {
            Log.e("eog_plan_page", "EogPlanPage:: null view? This should never happen!");
            return true;
        }
        switch (view.getId()) {
            default: {
                if (Log.isLoggable()) {
                    Log.w("eog_plan_page", "Uknown view, unable to handle: " + view.getId());
                }
                return false;
            }
            case 2131689801:
            case 2131689802: {
                Log.d("eog_plan_page", "Clicked on SD plan");
                this.setPlanSelectionUi(0);
                this.logPlanChangeEventByUser(0);
                return true;
            }
            case 2131689805:
            case 2131689806: {
                Log.d("eog_plan_page", "Clicked on HD plan");
                this.setPlanSelectionUi(1);
                this.logPlanChangeEventByUser(1);
                return true;
            }
            case 2131689809:
            case 2131689810: {
                Log.d("eog_plan_page", "Clicked on UHD plan");
                this.setPlanSelectionUi(2);
                this.logPlanChangeEventByUser(2);
                return true;
            }
        }
    }
}
