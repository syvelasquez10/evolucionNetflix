// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.ums;

import android.view.View;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.ViewFlipper;
import com.netflix.mediaclient.service.webclient.model.leafs.EogAlert;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class EndOfGrandfatheringActivity extends NetflixActivity
{
    private static final String EXTRA_CURRENT_PAGE_INDEX = "com.netflix.mediaclient.ui.ums.eog.pageIndex";
    private static final String EXTRA_CURRENT_PLAN_INDEX = "com.netflix.mediaclient.ui.ums.eog.planIndex";
    private static final String TAG = "eog";
    public static final boolean TEST_BLOCKING = false;
    public static final int TEST_CELL_ID = 5;
    public static final boolean TEST_USING_DUMMY_DATA = false;
    private int mCurrentPageIndex;
    private int mCurrentPlanIndex;
    private EogAlert mEogAlert;
    private ViewFlipper mFlipper;
    private EogLandingPage mLandingPage;
    private EogPlanPage mPlanPage;
    private ServiceManager mServiceManager;
    
    private void addViewsToFlipper() {
        this.getApplicationContext();
        final LayoutInflater layoutInflater = (LayoutInflater)this.getSystemService("layout_inflater");
        this.mFlipper.addView(layoutInflater.inflate(this.getLandingPageLayoutToUse(), (ViewGroup)null));
        this.mFlipper.addView(layoutInflater.inflate(this.getPlanPageLayoutToUse(), (ViewGroup)null));
    }
    
    public static Intent createStartIntent(final NetflixActivity netflixActivity, final boolean b) {
        final Intent addFlags = new Intent((Context)netflixActivity, (Class)getEndOfGrandfatheringActivityClass()).addFlags(268435456);
        if (b) {
            Log.d("eog", "adding clear_task flag");
            addFlags.addFlags(32768);
        }
        return addFlags;
    }
    
    private int getCurrentPlanIndexOfUser() {
        final EogAlert eogAlert = this.getEogAlert();
        if (eogAlert != null && !StringUtils.isEmpty(eogAlert.currentPlanId) && !eogAlert.currentPlanId.equals(eogAlert.sdPlanPlanId)) {
            if (eogAlert.currentPlanId.equals(eogAlert.hdPlanPlanId)) {
                return 1;
            }
            if (eogAlert.currentPlanId.equals(eogAlert.uhdPlanPlanId)) {
                return 2;
            }
        }
        return 0;
    }
    
    private static Class<?> getEndOfGrandfatheringActivityClass() {
        return EndOfGrandfatheringActivity.class;
    }
    
    private int getLandingPageLayoutToUse() {
        if (EogUtils.shouldUseLayoutWithImages(this.getEogAlert())) {
            if (DeviceUtils.isTabletByContext(this.getApplicationContext())) {
                if (!DeviceUtils.isPortrait(this.getApplicationContext()) || !EogUtils.isSmallSizeTablet(this.getApplicationContext())) {
                    Log.d("eog", "use tablet landing page");
                    return 2130903118;
                }
                Log.d("eog", "returning phone layout for cell5 tablet bcoz size too small");
            }
            return 2130903114;
        }
        if (DeviceUtils.isTabletByContext(this.getApplicationContext())) {
            return 2130903117;
        }
        return 2130903113;
    }
    
    private int getPlanPageLayoutToUse() {
        if (!DeviceUtils.isTabletByContext(this.getApplicationContext())) {
            return 2130903116;
        }
        if (DeviceUtils.isPortrait(this.getApplicationContext()) && EogUtils.isSmallSizeTablet(this.getApplicationContext())) {
            return 2130903120;
        }
        Log.d("eog", "use tablet plan page");
        return 2130903119;
    }
    
    private void init(final ServiceManager mServiceManager, final Status status) {
        this.mServiceManager = mServiceManager;
        this.mEogAlert = this.mServiceManager.getEndOfGrandfatheringAlert();
        this.initUi();
    }
    
    private void initUi() {
        int mCurrentPlanIndex;
        if (this.mCurrentPlanIndex != -1) {
            mCurrentPlanIndex = this.mCurrentPlanIndex;
        }
        else {
            mCurrentPlanIndex = this.getCurrentPlanIndexOfUser();
        }
        this.mCurrentPlanIndex = mCurrentPlanIndex;
        this.setContentView(2130903110);
        this.mFlipper = (ViewFlipper)this.findViewById(2131689782);
        this.addViewsToFlipper();
        this.mLandingPage = new EogLandingPage(this);
        this.mPlanPage = new EogPlanPage(this);
        this.mLandingPage.initUi();
        this.mPlanPage.initUi();
        if (EogUtils.shouldGoToPlanPage(this.mCurrentPageIndex, this.getEogAlert())) {
            this.goToPlanPage();
        }
        else {
            this.reportUiViewChanged(IClientLogging$ModalView.eogPrompt);
        }
        if (!shouldBlockUser(this.getEogAlert().isBlocking)) {
            this.markEogAlertAsDirty();
        }
    }
    
    private void markEogAlertAsDirty() {
        this.getEogAlert().isDirty = true;
        Log.d("eog", "eogAlert marked dirty; can't use same data again");
    }
    
    public static boolean shouldBlockUser(final boolean b) {
        return b;
    }
    
    protected void backPressed() {
        if (!this.handleBackPressed()) {
            Log.d("eog", "finish");
            this.finish();
        }
    }
    
    public boolean canProceed() {
        final boolean b = this.getServiceManager() != null && this.getEogAlert() != null;
        if (!b) {
            Log.e("eog", "fields are null - can't proceed");
        }
        return b;
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new EndOfGrandfatheringActivity$1(this);
    }
    
    public int getCurrentPlanIndex() {
        return this.mCurrentPlanIndex;
    }
    
    public EogAlert getEogAlert() {
        if (this.mEogAlert != null) {
            return this.mEogAlert;
        }
        if (this.mServiceManager != null) {
            this.mEogAlert = this.mServiceManager.getEndOfGrandfatheringAlert();
        }
        return this.mEogAlert;
    }
    
    @Override
    public IClientLogging$ModalView getUiScreen() {
        return IClientLogging$ModalView.eogPrompt;
    }
    
    public void goToLandingPage() {
        this.reportUiViewChanged(IClientLogging$ModalView.eogPrompt);
        this.mFlipper.showPrevious();
    }
    
    public void goToPlanPage() {
        this.reportUiViewChanged(IClientLogging$ModalView.eogPlanSelector);
        this.mFlipper.showNext();
    }
    
    @Override
    protected boolean handleBackPressed() {
        Log.d("eog", "handleBackPressed");
        if (EogUtils.shouldGoToLandingPage(this.mFlipper.getDisplayedChild(), this.getEogAlert())) {
            this.goToLandingPage();
            return true;
        }
        if (shouldBlockUser(this.getEogAlert().isBlocking)) {
            Log.d("eog", "finishing");
            this.finish();
            return true;
        }
        this.recordUmsImpression();
        return super.handleBackPressed();
    }
    
    @Override
    public boolean isLoadingData() {
        return false;
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.mCurrentPageIndex = bundle.getInt("com.netflix.mediaclient.ui.ums.eog.pageIndex");
            this.mCurrentPlanIndex = bundle.getInt("com.netflix.mediaclient.ui.ums.eog.planIndex");
            return;
        }
        this.mCurrentPageIndex = 0;
        this.mCurrentPlanIndex = -1;
    }
    
    @Override
    protected void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Log.d("eog", "Saving eog state...");
        int displayedChild;
        if (this.mFlipper != null) {
            displayedChild = this.mFlipper.getDisplayedChild();
        }
        else {
            displayedChild = 0;
        }
        bundle.putInt("com.netflix.mediaclient.ui.ums.eog.pageIndex", displayedChild);
        bundle.putInt("com.netflix.mediaclient.ui.ums.eog.planIndex", this.getCurrentPlanIndex());
    }
    
    public void performAction(final View view) {
        Log.d("eog", String.format("performAction currentChildIndex: %d", this.mFlipper.getDisplayedChild()));
        if (this.mLandingPage.performAction(view)) {
            Log.d("eog", "Handled by landing page");
            return;
        }
        if (this.mPlanPage.performAction(view)) {
            Log.d("eog", "Handled by plan page");
            return;
        }
        Log.w("eog", "Handled by nobody!");
    }
    
    public void performPlanSelection(final View view) {
        Log.d("eog", String.format("performPlanSelection currentChildIndex: %d", this.mFlipper.getDisplayedChild()));
        if (this.mPlanPage.performPlanSelection(view)) {
            Log.d("eog", "Handled by plan page");
            return;
        }
        Log.w("eog", "Handled by nobody!");
    }
    
    @Override
    public void performUpAction() {
        if (!shouldBlockUser(this.getEogAlert().isBlocking)) {
            this.recordUmsImpression();
            super.performUpAction();
            return;
        }
        if (EogUtils.shouldGoToLandingPage(this.mFlipper.getDisplayedChild(), this.getEogAlert())) {
            this.goToLandingPage();
            return;
        }
        Log.d("eog", "finishing activity");
        this.finish();
    }
    
    public void recordPlanSelection() {
        String s = null;
        final EogAlert eogAlert = this.getEogAlert();
        if (this.getServiceManager() == null || eogAlert == null) {
            Log.d("eog", "serviceMgr or eogAlert are null");
        }
        else {
            String s2 = null;
            switch (this.mCurrentPlanIndex) {
                default: {
                    if (Log.isLoggable()) {
                        Log.d("eog", String.format("unkown planId :%d - skipping recording", null));
                    }
                    s2 = null;
                    break;
                }
                case 0: {
                    s2 = eogAlert.sdPlanPlanId;
                    s = eogAlert.sdPlanPlanTier;
                    break;
                }
                case 1: {
                    s2 = eogAlert.hdPlanPlanId;
                    s = eogAlert.hdPlanPlanTier;
                    break;
                }
                case 2: {
                    s2 = eogAlert.uhdPlanPlanId;
                    s = eogAlert.uhdPlanPlanTier;
                    break;
                }
            }
            if (StringUtils.isNotEmpty(s2) && StringUtils.isNotEmpty(s)) {
                if (Log.isLoggable()) {
                    Log.d("eog", String.format("record planSelection planId:%s, priceTier:%s", s2, s));
                }
                this.markEogAlertAsDirty();
                this.getServiceManager().recordPlanSelection(s2, s);
            }
        }
    }
    
    public void recordUmsImpression() {
        if (this.getServiceManager() == null || this.getEogAlert() == null) {
            Log.d("eog", "serviceMgr or eogAlert are null");
            return;
        }
        if (Log.isLoggable()) {
            Log.d("eog", String.format("recordEndOfGrandfatheringImpression msgName:%s, impressionType:%s, curPageIndex:%d", this.getEogAlert().messageName, this.getEogAlert().skipBtnImpressionType, this.mCurrentPageIndex));
        }
        this.getServiceManager().recordEndOfGrandfatheringImpression(this.getEogAlert().messageName, this.getEogAlert().skipBtnImpressionType);
    }
    
    public void setCurrentPlanIndex(final int mCurrentPlanIndex) {
        this.mCurrentPlanIndex = mCurrentPlanIndex;
    }
}
