// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.util.MdxUtils$MdxTargetSelectionDialogInterface;
import com.netflix.mediaclient.ui.mdx.MdxTargetSelectionDialog;
import com.netflix.mediaclient.ui.launch.RelaunchActivity;
import com.netflix.mediaclient.ui.launch.LaunchActivity;
import com.netflix.mediaclient.util.WebApiUtils$VideoIds;
import com.netflix.mediaclient.ui.player.MDXControllerActivity;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.service.mdx.MdxAgent;
import android.text.TextUtils;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import android.app.FragmentTransaction;
import android.app.Fragment;
import com.netflix.mediaclient.ui.signup.SignupActivity;
import com.netflix.mediaclient.ui.home.HomeActivity;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.ui.verifyplay.PlayVerifierVault;
import com.netflix.mediaclient.util.DeviceUtils;
import java.util.Iterator;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.ui.common.DebugMenuItems;
import android.view.Menu;
import com.netflix.mediaclient.util.AndroidUtils;
import android.content.IntentFilter;
import com.netflix.mediaclient.util.ViewUtils;
import android.os.Bundle;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import android.app.Activity;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.ui.login.LogoutActivity;
import com.netflix.mediaclient.servicemgr.IMdxSharedState;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.ui.details.AbsEpisodeView$EpisodeRowListener;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$EntryPoint;
import android.app.DialogFragment;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import java.io.Serializable;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;
import android.app.AlertDialog;
import android.app.AlertDialog$Builder;
import android.view.MotionEvent;
import com.netflix.mediaclient.util.MdxUtils;
import android.view.KeyEvent;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.ui.kubrick_kids.KubrickKidsActionBar;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.view.View;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.android.widget.UpdateDialog;
import com.netflix.mediaclient.android.widget.UpdateDialog$Builder;
import com.netflix.mediaclient.service.error.ErrorDescriptor;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.android.widget.AlertDialogFactory;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import com.netflix.mediaclient.ui.mdx.MdxReceiver;
import android.view.View$OnClickListener;
import android.view.ViewGroup$LayoutParams;
import android.support.design.widget.CoordinatorLayout$LayoutParams;
import android.view.ViewGroup;
import android.content.Context;
import android.view.LayoutInflater;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.voip.ContactUsActivity;
import android.content.Intent;
import java.util.HashSet;
import android.app.Dialog;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.sothree.slidinguppanel.SlidingUpPanelLayout$PanelSlideListener;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag;
import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;
import android.view.MenuItem;
import android.support.design.widget.CoordinatorLayout;
import java.util.concurrent.atomic.AtomicLong;
import android.support.design.widget.FloatingActionButton;
import java.util.concurrent.atomic.AtomicBoolean;
import android.os.Handler;
import android.content.BroadcastReceiver;
import java.util.Set;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.ui.verifyplay.PlayVerifier$PlayVerifyCallback;
import com.netflix.mediaclient.ui.mdx.ShowMessageDialogFrag$MessageResponseProvider;
import com.netflix.mediaclient.ui.details.AbsEpisodeView$EpisodeRowListenerProvider;
import com.netflix.mediaclient.android.app.LoadingStatus;
import android.support.v7.app.AppCompatActivity;

@SuppressLint({ "DefaultLocale" })
public abstract class NetflixActivity extends AppCompatActivity implements LoadingStatus, AbsEpisodeView$EpisodeRowListenerProvider, ShowMessageDialogFrag$MessageResponseProvider, PlayVerifier$PlayVerifyCallback
{
    private static final long ACTION_BAR_VISIBILITY_CHECK_DELAY_MS = 1000L;
    public static final String ACTION_CS_CALL_ENDED = "com.netflix.mediaclient.ui.cs.ACTION_CALL_ENDED";
    public static final String ACTION_CS_CALL_STARTED = "com.netflix.mediaclient.ui.cs.ACTION_CALL_STARTED";
    public static final String ACTION_DISPLAY_ERROR = "com.netflix.mediaclient.ui.error.ACTION_DISPLAY_ERROR";
    private static final String ACTION_FINISH_ALL_ACTIVITIES = "com.netflix.mediaclient.ui.login.ACTION_FINISH_ALL_ACTIVITIES";
    public static final long EXPAND_MINI_PLAYER_DELAY_MS = 400L;
    public static final String EXTRA_ENTRY = "entry";
    public static final String EXTRA_FROM = "from";
    public static final String EXTRA_PARAM_MESSAGE_ID = "message_id";
    public static final String EXTRA_PARAM_STATUS = "status";
    public static final String EXTRA_PARAM_URL = "url";
    private static final String EXTRA_SHOULD_EXPAND_MINI_PLAYER = "mini_player_expanded";
    public static final String EXTRA_SOURCE = "source";
    public static final String FRAG_DIALOG_TAG = "frag_dialog";
    private static final String INSTANCE_STATE_SAVED_TAG = "NetflixActivity_instanceState";
    private static final boolean PRINT_LOADING_STATUS = false;
    private static final String TAG = "NetflixActivity";
    private int actionBarHeight;
    private final Set<BroadcastReceiver> autoUnregisterLocalReceivers;
    private final Set<BroadcastReceiver> autoUnregisterReceivers;
    private final BroadcastReceiver autokillReceiver;
    private final BroadcastReceiver expandMdxMiniPlayerReceiver;
    protected Handler handler;
    protected AtomicBoolean instanceStateSaved;
    private boolean isDestroyed;
    private boolean isVisible;
    private final BroadcastReceiver localBroadcastReceiver;
    private FloatingActionButton mBackToCustomerSupportCallFAB;
    private boolean mConnectingToTarget;
    protected AtomicLong mDialogCount;
    private NetflixActivity$DismissingDialogConfig mDismissingDialogConfiguration;
    private String mErrorDialogId;
    private CoordinatorLayout mFabAnchor;
    protected MenuItem mHelpMenuItem;
    protected boolean mIsTablet;
    protected LoadingStatus$LoadingStatusCallback mLoadingStatusCallback;
    private MdxMiniPlayerFrag mdxFrag;
    private NetflixActionBar netflixActionBar;
    private final SlidingUpPanelLayout$PanelSlideListener panelSlideListener;
    private final Runnable printLoadingStatusRunnable;
    private ServiceManager serviceManager;
    private boolean shouldExpandMiniPlayer;
    private SlidingUpPanelLayout slidingPanel;
    private final Runnable updateActionBarVisibilityRunnable;
    private final BroadcastReceiver userAgentUpdateReceiver;
    protected Dialog visibleDialog;
    protected Object visibleDialogLock;
    
    public NetflixActivity() {
        this.autoUnregisterReceivers = new HashSet<BroadcastReceiver>();
        this.autoUnregisterLocalReceivers = new HashSet<BroadcastReceiver>();
        this.mDismissingDialogConfiguration = NetflixActivity$DismissingDialogConfig.dismissOnStop;
        this.instanceStateSaved = new AtomicBoolean(false);
        this.visibleDialogLock = new Object();
        this.mDialogCount = new AtomicLong(1L);
        this.mIsTablet = false;
        this.mConnectingToTarget = false;
        this.autokillReceiver = new NetflixActivity$3(this);
        this.expandMdxMiniPlayerReceiver = new NetflixActivity$4(this);
        this.printLoadingStatusRunnable = new NetflixActivity$5(this);
        this.userAgentUpdateReceiver = new NetflixActivity$7(this);
        this.panelSlideListener = new NetflixActivity$8(this);
        this.updateActionBarVisibilityRunnable = new NetflixActivity$9(this);
        this.localBroadcastReceiver = new NetflixActivity$13(this);
    }
    
    private void addFab() {
        while (true) {
            Label_0071: {
                synchronized (this) {
                    if (!(this instanceof ContactUsActivity)) {
                        if (this.mBackToCustomerSupportCallFAB == null) {
                            break Label_0071;
                        }
                        if (Log.isLoggable()) {
                            Log.d("NetflixActivity", "FAB existed, make it visible " + this.getLocalClassName());
                        }
                        this.mBackToCustomerSupportCallFAB.show();
                    }
                    return;
                }
            }
            if (Log.isLoggable()) {
                Log.d("SRDJAN", "Add FAB to " + this.getLocalClassName());
            }
            this.mFabAnchor = (CoordinatorLayout)this.findViewById(2131624154);
            if (this.mFabAnchor == null) {
                if (Log.isLoggable()) {
                    Log.w("NetflixActivity", "FAB anchor is NULL for " + this.getLocalClassName());
                }
            }
            else {
                if (!this.isCustomerSupportCallInProgress()) {
                    Log.w("NetflixActivity", "Customer support call is NOT in progress for " + this.getLocalClassName());
                    return;
                }
                LayoutInflater.from((Context)this).inflate(2130903081, (ViewGroup)this.mFabAnchor, true);
                final FloatingActionButton mBackToCustomerSupportCallFAB = (FloatingActionButton)this.mFabAnchor.findViewById(2131624148);
                if (mBackToCustomerSupportCallFAB == null) {
                    Log.e("NetflixActivity", "Fab is not found in root layout! This should NOT happen!");
                    return;
                }
                final CoordinatorLayout$LayoutParams layoutParams = (CoordinatorLayout$LayoutParams)mBackToCustomerSupportCallFAB.getLayoutParams();
                layoutParams.setAnchorId(2131624154);
                mBackToCustomerSupportCallFAB.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
                mBackToCustomerSupportCallFAB.setOnClickListener((View$OnClickListener)new NetflixActivity$2(this));
                mBackToCustomerSupportCallFAB.show();
                this.mBackToCustomerSupportCallFAB = mBackToCustomerSupportCallFAB;
            }
        }
    }
    
    private void addMdxReceiver() {
        if (!this.showMdxInMenu()) {
            Log.d("NetflixActivity", "Activity does not required MDX, skipping add of MDX receiver.");
            return;
        }
        Log.d("NetflixActivity", "Listen to updated from MDX service, add");
        final MdxReceiver mdxReceiver = new MdxReceiver(this);
        this.registerReceiverWithAutoUnregister(mdxReceiver, mdxReceiver.getFilter());
        Log.d("NetflixActivity", "Listen to updated from MDX service, added");
    }
    
    private void addUserAgentUpdateReceiver() {
        this.registerReceiverLocallyWithAutoUnregister(this.userAgentUpdateReceiver, UserAgentBroadcastIntents.getNotificationIntentFilter());
    }
    
    private void collapseSlidingPanel() {
        boolean collapsePane = false;
        if (this.slidingPanel != null) {
            collapsePane = collapsePane;
            if (this.slidingPanel.isExpanded()) {
                Log.v("NetflixActivity", "Collapsing sliding panel...");
                collapsePane = this.slidingPanel.collapsePane();
            }
        }
        if (!collapsePane && this.mdxFrag != null) {
            this.mdxFrag.onPanelCollapsed();
        }
    }
    
    private void displayErrorDialogIfExist() {
        final ServiceManager serviceManager = this.getServiceManager();
        if (serviceManager != null) {
            final ErrorDescriptor currentError = serviceManager.getErrorHandler().getCurrentError();
            if (currentError != null) {
                if (currentError.getData() != null) {
                    Log.d("NetflixActivity", "Display error dialog");
                    AlertDialogFactory.createDialog((Context)this, this.handler, currentError.getData(), new NetflixActivity$14(this, serviceManager, currentError));
                    this.mErrorDialogId = this.reportUiModelessViewSessionStart(IClientLogging$ModalView.errorDialog);
                    synchronized (this.visibleDialogLock) {
                        if (this.destroyed()) {
                            return;
                        }
                    }
                    if (this.visibleDialog != null) {
                        this.visibleDialog.dismiss();
                    }
                    final UpdateDialog$Builder updateDialog$Builder;
                    final UpdateDialog create = updateDialog$Builder.create();
                    ((Dialog)create).show();
                    AlertDialogFactory.activateLinkIfExist((Dialog)create);
                    this.visibleDialog = (Dialog)create;
                    // monitorexit(o)
                    return;
                }
                Log.e("NetflixActivity", "Unable to display an error dialog, data not found!");
            }
        }
    }
    
    public static void finishAllActivities(final Context context) {
        context.sendBroadcast(new Intent("com.netflix.mediaclient.ui.login.ACTION_FINISH_ALL_ACTIVITIES"));
    }
    
    public static ImageLoader getImageLoader(final Context context) {
        return ((NetflixActivity)context).serviceManager.getImageLoader();
    }
    
    private void handleCustomerSupportCallEnded() {
        if (Log.isLoggable()) {
            Log.d("NetflixActivity", "Customer support call ended, remove FAB " + this.hashCode());
        }
        this.removeFab();
    }
    
    private void handleDisplayToken(final Intent intent) {
    }
    
    private void handleErrorDialog() {
        if (this.isVisible) {
            Log.d("NetflixActivity", "Display error dialog");
            this.displayErrorDialogIfExist();
            return;
        }
        Log.d("NetflixActivity", "Not visible, can not display error dialog");
    }
    
    private boolean isCustomerSupportCallInProgress() {
        return this.serviceManager != null && this.serviceManager.getVoip() != null && this.serviceManager.getVoip().isCallInProgress() && !(this instanceof ContactUsActivity);
    }
    
    private void onFromBackground() {
        this.showMDXPostPlayOnResume();
        this.serviceManager.uiComingFromBackground();
    }
    
    private void postActionBarUpdate() {
        this.handler.removeCallbacks(this.updateActionBarVisibilityRunnable);
        this.handler.postDelayed(this.updateActionBarVisibilityRunnable, 1000L);
    }
    
    private void removeFab() {
        synchronized (this) {
            if (this.mFabAnchor != null && this.mBackToCustomerSupportCallFAB != null) {
                Log.d("NetflixActivity", "Hiding FAB...");
                this.mBackToCustomerSupportCallFAB.hide();
            }
        }
    }
    
    private void setAssistBlocked(final View view, final boolean b) {
        try {
            View.class.getMethod("setAssistBlocked", Boolean.TYPE).invoke(view, b);
        }
        catch (Exception ex) {
            Log.e("NetflixActivity", "Couldn't execute setAssistBlocked method. Got exception: " + ex);
        }
    }
    
    private void setInstanceStateSaved(final boolean b) {
        if (Log.isLoggable()) {
            Log.v("NetflixActivity_instanceState", this.getClass().getSimpleName() + " instanceStateSaved: " + b);
        }
        synchronized (this.instanceStateSaved) {
            this.instanceStateSaved.set(b);
        }
    }
    
    private boolean shouldDismissVisibleDialog() {
        if (this.mErrorDialogId == null) {
            final boolean b = this.visibleDialog != null;
            if (this.mDismissingDialogConfiguration == null) {
                this.mDismissingDialogConfiguration = NetflixActivity$DismissingDialogConfig.dismissOnStop;
                return b;
            }
            switch (NetflixActivity$15.$SwitchMap$com$netflix$mediaclient$android$activity$NetflixActivity$DismissingDialogConfig[this.mDismissingDialogConfiguration.ordinal()]) {
                case 2: {
                    break;
                }
                default: {
                    return false;
                }
                case 1: {
                    return b;
                }
                case 3: {
                    this.mDismissingDialogConfiguration = NetflixActivity$DismissingDialogConfig.dismissOnStop;
                    return false;
                }
            }
        }
        return false;
    }
    
    private void updateHelpInMenuStatus() {
        if (this.mHelpMenuItem != null) {
            this.mHelpMenuItem.setVisible(this.getServiceManager().getVoip().isEnabled());
        }
    }
    
    protected boolean canApplyBrowseExperience() {
        return false;
    }
    
    protected NetflixActionBar createActionBar() {
        if (this.canApplyBrowseExperience() && BrowseExperience.isKubrickKids()) {
            return new KubrickKidsActionBar(this, this.hasUpAction());
        }
        return new NetflixActionBar(this, this.hasUpAction());
    }
    
    protected ManagerStatusListener createManagerStatusListener() {
        return null;
    }
    
    public boolean destroyed() {
        return this.isDestroyed;
    }
    
    public boolean dispatchKeyEvent(final KeyEvent keyEvent) {
        this.getNetflixApplication().getUserInput().updateUserInteraction();
        return (!MdxUtils.isMediaSessionAvailable() && this.mdxFrag != null && this.mdxFrag.dispatchKeyEvent(keyEvent)) || super.dispatchKeyEvent(keyEvent);
    }
    
    public boolean dispatchTouchEvent(final MotionEvent motionEvent) {
        this.getNetflixApplication().getUserInput().updateUserInteraction();
        return super.dispatchTouchEvent(motionEvent);
    }
    
    public boolean dispatchTrackballEvent(final MotionEvent motionEvent) {
        this.getNetflixApplication().getUserInput().updateUserInteraction();
        return super.dispatchTrackballEvent(motionEvent);
    }
    
    public Dialog displayDialog(final AlertDialog$Builder alertDialog$Builder) {
        if (alertDialog$Builder == null) {
            return null;
        }
        synchronized (this.visibleDialogLock) {
            final AlertDialog create = alertDialog$Builder.create();
            this.displayDialog((Dialog)create);
            return (Dialog)create;
        }
    }
    
    public Dialog displayDialog(final UpdateDialog$Builder updateDialog$Builder) {
        if (updateDialog$Builder == null || this.destroyed()) {
            return null;
        }
        synchronized (this.visibleDialogLock) {
            final UpdateDialog create = updateDialog$Builder.create();
            this.displayDialog((Dialog)create);
            return (Dialog)create;
        }
    }
    
    public void displayDialog(final Dialog dialog) {
        if (dialog == null || this.destroyed()) {
            return;
        }
        synchronized (this.visibleDialogLock) {
            if (this.destroyed()) {
                return;
            }
        }
        if (this.mErrorDialogId != null) {
            Log.w("NetflixActivity", "Error dualog is displayed, do not remove it!");
            // monitorexit(o)
            return;
        }
        if (this.visibleDialog != null) {
            this.visibleDialog.dismiss();
        }
        final Dialog visibleDialog;
        visibleDialog.show();
        this.visibleDialog = visibleDialog;
    }
    // monitorexit(o)
    
    protected void displayErrorDialog(final String s, final int n, final boolean b) {
        final String format = String.format("%s ( %d )", s, n);
        NetflixActivity$12 netflixActivity$12;
        if (b) {
            netflixActivity$12 = new NetflixActivity$12(this);
        }
        else {
            netflixActivity$12 = null;
        }
        this.displayServiceAgentDialog(format, netflixActivity$12, true);
    }
    
    protected void displayServiceAgentDialog(final String s, Runnable visibleDialogLock, final boolean b) {
        final UpdateDialog$Builder dialog = AlertDialogFactory.createDialog((Context)this, this.handler, new AlertDialogFactory$AlertDialogDescriptor(null, s, this.getString(2131165568), visibleDialogLock));
        if (this.destroyed()) {
            return;
        }
        visibleDialogLock = (Runnable)this.visibleDialogLock;
        // monitorenter(visibleDialogLock)
        Label_0095: {
            if (!b) {
                break Label_0095;
            }
        Label_0144_Outer:
            while (true) {
                try {
                    if (Log.isLoggable()) {
                        Log.d("NetflixActivity", "displayServiceAgentDialog " + s + " isCritical");
                    }
                    this.displayDialog(dialog);
                    return;
                    // iftrue(Label_0144:, !Log.isLoggable())
                    // iftrue(Label_0159:, this.getVisibleDialog() == null || this.getVisibleDialog().isShowing())
                    while (true) {
                        Block_10: {
                            while (true) {
                                break Block_10;
                                continue Label_0144_Outer;
                            }
                            this.displayDialog(dialog);
                            return;
                        }
                        Log.d("NetflixActivity", "displayServiceAgentDialog " + s);
                        continue;
                    }
                }
                finally {
                }
                // monitorexit(visibleDialogLock)
                final String s2;
                Label_0159: {
                    if (this.getVisibleDialog() == null) {
                        if (Log.isLoggable()) {
                            Log.d("NetflixActivity", "displayServiceAgentDialog, no dialog  " + s2);
                        }
                        this.displayDialog(dialog);
                        return;
                    }
                }
                if (Log.isLoggable()) {
                    Log.e("NetflixActivity", "displayServiceAgentDialog, Dialog visible, skipping  " + s2);
                }
            }
        }
    }
    
    protected void expandMiniPlayerIfVisible() {
        if (this.isVisible) {
            if (Log.isLoggable()) {
                Log.v("NetflixActivity", "Activity is visible, checking for MDX mini player to see if it can be expanded...");
                final StringBuilder append = new StringBuilder().append("MDX frag showing: ");
                Serializable value;
                if (this.mdxFrag == null) {
                    value = "null";
                }
                else {
                    value = this.mdxFrag.isShowing();
                }
                Log.v("NetflixActivity", append.append(value).toString());
            }
            if (this.mdxFrag != null && this.mdxFrag.isShowing() && this.slidingPanel != null) {
                Log.v("NetflixActivity", "Expanding mini player");
                final boolean expandPane = this.slidingPanel.expandPane();
                if (Log.isLoggable()) {
                    String s;
                    if (expandPane) {
                        s = "Panel is expanding";
                    }
                    else {
                        s = "Panel is NOT expanding";
                    }
                    Log.v("NetflixActivity", s);
                }
            }
        }
    }
    
    public void finish() {
        if (Log.isLoggable()) {
            Log.d("NetflixActivity", this.getClass().getSimpleName() + ": finish has been called");
        }
        super.finish();
    }
    
    public int getActionBarHeight() {
        return this.actionBarHeight;
    }
    
    public int getActionBarParentViewId() {
        return 16908290;
    }
    
    protected ApplicationPerformanceMetricsLogging getApmSafely() {
        final ServiceManager serviceManager = this.getServiceManager();
        if (serviceManager != null) {
            final IClientLogging clientLogging = serviceManager.getClientLogging();
            if (clientLogging != null) {
                return clientLogging.getApplicationPerformanceMetricsLogging();
            }
        }
        return null;
    }
    
    public DataContext getDataContext() {
        return null;
    }
    
    public DialogFragment getDialogFragment() {
        return (DialogFragment)this.getFragmentManager().findFragmentByTag("frag_dialog");
    }
    
    public NetflixActivity$DismissingDialogConfig getDismissingDialogConfiguration() {
        return this.mDismissingDialogConfiguration;
    }
    
    protected CustomerServiceLogging$EntryPoint getEntryPoint() {
        return null;
    }
    
    @Override
    public AbsEpisodeView$EpisodeRowListener getEpisodeRowListener() {
        return this.mdxFrag;
    }
    
    public Handler getHandler() {
        return this.handler;
    }
    
    public MdxMiniPlayerFrag getMdxMiniPlayerFrag() {
        return this.mdxFrag;
    }
    
    public NetflixActionBar getNetflixActionBar() {
        return this.netflixActionBar;
    }
    
    public NetflixApplication getNetflixApplication() {
        return (NetflixApplication)this.getApplication();
    }
    
    public ServiceManager getServiceManager() {
        return this.serviceManager;
    }
    
    protected IMdxSharedState getSharedState() {
        return this.serviceManager.getMdx().getSharedState();
    }
    
    public abstract IClientLogging$ModalView getUiScreen();
    
    public Dialog getVisibleDialog() {
        return this.visibleDialog;
    }
    
    protected void handleAccountDeactivated() {
        if (this.isVisible && !(this instanceof LogoutActivity)) {
            this.startActivity(LogoutActivity.create((Context)this));
        }
        this.finish();
    }
    
    protected boolean handleBackPressed() {
        return false;
    }
    
    protected void handleFalkorAgentErrors(final Status status) {
        if (StatusCode.INVALID_COUNRTY.equals(status.getStatusCode())) {
            Log.d("NetflixActivity", "User accessing Netflix in a not supported country. Show alert and kill self");
            this.displayErrorDialog(this.getString(2131165363), status.getStatusCode().getValue(), true);
        }
        else if (StatusCode.INSUFFICIENT_CONTENT.equals(status.getStatusCode())) {
            Log.d("NetflixActivity", "Insufficient content for this profile - cant show lolomo. Show alert and go to profile selection");
            this.displayServiceAgentDialog(this.getString(2131165401), new NetflixActivity$10(this), false);
        }
    }
    
    protected void handleInvalidCurrentProfile() {
        finishAllActivities((Context)this);
        this.startActivity(ProfileSelectionActivity.createStartIntent((Context)this));
    }
    
    protected void handleNetworkErrorDialog() {
        this.finish();
    }
    
    protected void handleProfileActivated() {
        this.finish();
    }
    
    protected void handleProfileReadyToSelect() {
    }
    
    protected void handleProfileSelectionResult(final int n, final String s) {
    }
    
    protected void handleProfilesListUpdated() {
    }
    
    protected String handleUserAgentErrors(final Status status) {
        return this.handleUserAgentErrors(status, true);
    }
    
    protected String handleUserAgentErrors(final Status status, final boolean b) {
        String message = status.getMessage();
        if (message == null) {
            message = "";
        }
        switch (NetflixActivity$15.$SwitchMap$com$netflix$mediaclient$StatusCode[status.getStatusCode().ordinal()]) {
            default: {
                final String string = this.getString(2131165664);
                this.displayErrorDialog(this.getString(2131165664), status.getStatusCode().getValue(), b);
                return string;
            }
            case 1: {
                String format = message;
                if (message.isEmpty()) {
                    format = String.format("%s ( %d )", this.getString(2131165657), status.getStatusCode().getValue());
                }
                this.displayServiceAgentDialog(format, null, false);
                return format;
            }
            case 2:
            case 3: {
                final String format2 = String.format("%s ( %d )", this.getString(2131165395), status.getStatusCode().getValue());
                this.displayServiceAgentDialog(format2, new NetflixActivity$11(this), true);
                return format2;
            }
            case 4: {
                ServiceErrorsHandler.handleManagerResponse(this, CommonStatus.OBSOLETE_APP_VERSION);
                return "";
            }
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12: {
                final String format3 = String.format("%s ( %d )", this.getString(2131165657), status.getStatusCode().getValue());
                this.displayServiceAgentDialog(format3, null, false);
                return format3;
            }
            case 13:
            case 14:
            case 15: {
                final String string2 = this.getString(2131165658);
                this.displayErrorDialog(string2, status.getStatusCode().getValue(), b);
                return string2;
            }
        }
    }
    
    protected boolean hasUpAction() {
        return true;
    }
    
    protected void initToolbar() {
        this.netflixActionBar = this.createActionBar();
    }
    
    boolean isComingFromBackground(final boolean b) {
        final boolean wasInBackground = this.getNetflixApplication().wasInBackground(b);
        if (Log.isLoggable()) {
            Log.d("NetflixActivity", "isComingFromBackground(), wasinBackground: " + wasInBackground);
        }
        return wasInBackground;
    }
    
    public boolean isConnectingToTarget() {
        return this.mConnectingToTarget;
    }
    
    public boolean isDialogFragmentVisible() {
        return this.getDialogFragment() != null;
    }
    
    public boolean isInstanceStateSaved() {
        synchronized (this.instanceStateSaved) {
            return this.instanceStateSaved.get();
        }
    }
    
    public boolean isPanelExpanded() {
        return this.slidingPanel != null && this.slidingPanel.isExpanded();
    }
    
    public boolean isTablet() {
        return this.mIsTablet;
    }
    
    public void notifyMdxEndOfPlayback() {
        Log.v("NetflixActivity", "MDX end of playback");
        this.collapseSlidingPanel();
        this.postActionBarUpdate();
    }
    
    public void notifyMdxMiniPlayerHidden() {
        Log.v("NetflixActivity", "MDX frag hidden");
        this.collapseSlidingPanel();
        this.postActionBarUpdate();
    }
    
    public void notifyMdxMiniPlayerShown() {
        Log.v("NetflixActivity", "MDX frag shown");
        this.postActionBarUpdate();
    }
    
    public void notifyMdxShowDetailsRequest() {
        this.handler.postDelayed((Runnable)new NetflixActivity$6(this), 250L);
    }
    
    @Override
    public final void onBackPressed() {
        if (Log.isLoggable()) {
            Log.v("NetflixActivity", this.getClass().getSimpleName() + ": back button pressed");
        }
        if (this.slidingPanel != null && this.mdxFrag != null && this.slidingPanel.isExpanded() && this.mdxFrag.isVisible()) {
            if (!this.isDialogFragmentVisible()) {
                Log.v("NetflixActivity", "MDX mini player sliding panel was expanded, collapsing...");
                this.slidingPanel.collapsePane();
                return;
            }
            Log.v("NetflixActivity", "DialogFragment is visible, closing...");
            this.removeDialogFrag();
        }
        else if (!this.handleBackPressed()) {
            UIViewLogUtils.reportUIViewCommand((Context)this, UIViewLogging$UIViewCommandName.backButton, this.getUiScreen(), this.getDataContext());
            super.onBackPressed();
        }
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        final boolean b = false;
        super.onCreate(bundle);
        this.setInstanceStateSaved(false);
        this.actionBarHeight = ViewUtils.getDefaultActionBarHeight((Context)this);
        if (Log.isLoggable()) {
            Log.v("NetflixActivity", "Creating activity: " + this.getClass().getSimpleName() + ", hash: " + this.hashCode());
        }
        if (this.shouldShowKidsBackground()) {
            this.getWindow().setBackgroundDrawableResource(2131558596);
        }
        boolean shouldExpandMiniPlayer = b;
        if (bundle != null) {
            shouldExpandMiniPlayer = b;
            if (bundle.getBoolean("mini_player_expanded", false)) {
                shouldExpandMiniPlayer = true;
            }
        }
        this.shouldExpandMiniPlayer = shouldExpandMiniPlayer;
        if (Log.isLoggable()) {
            Log.v("NetflixActivity", "Should expand mini player: " + this.shouldExpandMiniPlayer);
        }
        this.registerFinishReceiverWithAutoUnregister("com.netflix.mediaclient.ui.login.ACTION_FINISH_ALL_ACTIVITIES");
        this.registerReceiverWithAutoUnregister(this.expandMdxMiniPlayerReceiver, "com.netflix.mediaclient.service.ACTION_EXPAND_MDX_MINI_PLAYER");
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.netflix.mediaclient.ui.error.ACTION_DISPLAY_ERROR");
        intentFilter.addAction("com.netflix.mediaclient.ui.cs.ACTION_CALL_ENDED");
        this.registerReceiverLocallyWithAutoUnregister(this.localBroadcastReceiver, intentFilter);
        this.setupServicemanager();
        this.handler = new Handler();
        if (AndroidUtils.getAndroidVersion() >= 23) {
            this.setAssistBlocked(this.findViewById(16908290), true);
        }
    }
    
    protected void onCreateOptionsMenu(final Menu menu, final Menu menu2) {
        if (menu2 != null) {
            new DebugMenuItems("NetflixActivity", this).addItems(this, menu2);
        }
        if (this.showHelpInMenu()) {
            (this.mHelpMenuItem = menu.add((CharSequence)this.getString(2131165531))).setShowAsAction(1);
            final Intent startIntent = ContactUsActivity.createStartIntent((Context)this);
            final IClientLogging$ModalView uiScreen = this.getUiScreen();
            if (uiScreen != null) {
                startIntent.putExtra("source", uiScreen.name());
            }
            if (this.getEntryPoint() != null) {
                startIntent.putExtra("entry", this.getEntryPoint().name());
            }
            this.mHelpMenuItem.setIntent(startIntent);
            if (this.getServiceManager() != null && this.getServiceManager().getVoip() != null) {
                this.mHelpMenuItem.setVisible(this.getServiceManager().getVoip().isEnabled());
            }
        }
    }
    
    public final boolean onCreateOptionsMenu(final Menu menu) {
        Log.v("NetflixActivity", "onCreateOptionsMenu");
        this.onCreateOptionsMenu(menu, null);
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    protected void onDestroy() {
        ((NetflixApplication)this.getApplication()).releaseCurrentActivity(this);
        if (Log.isLoggable()) {
            Log.v("NetflixActivity", "Destroying activity: " + this.getClass().getSimpleName() + ", hash: " + this.hashCode());
        }
        this.isDestroyed = true;
        final Iterator<BroadcastReceiver> iterator = this.autoUnregisterReceivers.iterator();
        while (iterator.hasNext()) {
            this.unregisterReceiver((BroadcastReceiver)iterator.next());
        }
        final Iterator<BroadcastReceiver> iterator2 = this.autoUnregisterLocalReceivers.iterator();
        while (iterator2.hasNext()) {
            LocalBroadcastManager.getInstance((Context)this).unregisterReceiver(iterator2.next());
        }
        if (this.serviceManager != null) {
            this.serviceManager.release();
        }
        super.onDestroy();
    }
    
    @Override
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        return (n == 82 && DeviceUtils.DEVICE_WITH_MENU_BUTTON_BUG) || super.onKeyDown(n, keyEvent);
    }
    
    public boolean onKeyUp(final int n, final KeyEvent keyEvent) {
        if (n == 82 && DeviceUtils.DEVICE_WITH_MENU_BUTTON_BUG) {
            this.openOptionsMenu();
            return true;
        }
        return super.onKeyUp(n, keyEvent);
    }
    
    public void onLoaded(final Status status) {
        if (this.mLoadingStatusCallback != null) {
            this.mLoadingStatusCallback.onDataLoaded(status);
        }
    }
    
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        if (Log.isLoggable()) {
            Log.v("NetflixActivity", "onMenuItemSelected: " + menuItem.getItemId());
        }
        return (menuItem != null && this.netflixActionBar != null && this.netflixActionBar.handleHomeButtonSelected(menuItem)) || super.onOptionsItemSelected(menuItem);
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        final NetflixApplication netflixApplication = (NetflixApplication)this.getApplication();
        NetflixApplication.activityPaused();
        netflixApplication.releaseCurrentActivity(this);
        this.isVisible = false;
        this.handler.removeCallbacks(this.printLoadingStatusRunnable);
        netflixApplication.startActivityTransitionTimer();
    }
    
    @Override
    public void onPlayVerified(final boolean b, final PlayVerifierVault playVerifierVault) {
        throw new IllegalStateException(String.format("onPlayVerified vault: %s", playVerifierVault));
    }
    
    @Override
    protected void onPostCreate(final Bundle bundle) {
        super.onPostCreate(bundle);
        if (Log.isLoggable()) {
            Log.v("NetflixActivity", this.getClass().getSimpleName() + ": onPostCreate");
        }
    }
    
    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (this.mdxFrag != null) {
            this.mdxFrag.onResumeFragments();
        }
    }
    
    @Override
    public void onResponse(final String s) {
        if (Log.isLoggable()) {
            Log.d("NetflixActivity", "onResponse: User selected: " + s);
        }
        if (this.mdxFrag != null) {
            this.mdxFrag.sendDialogResponse(s);
        }
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        final NetflixApplication netflixApplication = (NetflixApplication)this.getApplication();
        NetflixApplication.activityResumed();
        if (this.isComingFromBackground(false)) {
            this.onFromBackground();
        }
        this.setInstanceStateSaved(false);
        netflixApplication.setCurrentActivity(this);
        this.isVisible = true;
        this.handler.post(this.printLoadingStatusRunnable);
        netflixApplication.stopActivityTransitionTimer();
        this.addFab();
    }
    
    @Override
    protected void onSaveInstanceState(final Bundle bundle) {
        boolean b = true;
        this.setInstanceStateSaved(true);
        super.onSaveInstanceState(bundle);
        if (this.mdxFrag == null || !this.mdxFrag.isShowing() || this.slidingPanel == null || !this.slidingPanel.isExpanded()) {
            b = false;
        }
        bundle.putBoolean("mini_player_expanded", b);
    }
    
    protected void onSlidingPanelCollapsed(final View view) {
    }
    
    protected void onSlidingPanelExpanded(final View view) {
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        UserActionLogUtils.reportNavigationActionStarted((Context)this, null, this.getUiScreen());
        this.mdxFrag = (MdxMiniPlayerFrag)this.getFragmentManager().findFragmentById(2131624299);
        this.slidingPanel = (SlidingUpPanelLayout)this.findViewById(2131624175);
        if (this.slidingPanel != null) {
            this.slidingPanel.setDragView(this.mdxFrag.getSlidingPanelDragView());
            this.slidingPanel.setPanelHeight(this.getResources().getDimensionPixelSize(2131296292));
            this.slidingPanel.setShadowDrawable(this.getResources().getDrawable(2130837909));
            this.slidingPanel.setPanelSlideListener(this.panelSlideListener);
            if (this.shouldApplyPaddingToSlidingPanel()) {
                final View child = this.slidingPanel.getChildAt(0);
                child.setPadding(child.getPaddingLeft(), this.actionBarHeight, child.getPaddingRight(), child.getPaddingBottom());
            }
        }
    }
    
    @Override
    protected void onStop() {
        if (this.shouldReportNavigationActionEndedOnStop()) {
            UserActionLogUtils.reportNavigationActionEnded((Context)this, this.getUiScreen(), IClientLogging$CompletionReason.success, null);
        }
        Object o = this.visibleDialogLock;
        synchronized (o) {
            Label_0047: {
                if (!this.shouldDismissVisibleDialog()) {
                    break Label_0047;
                }
                try {
                    this.visibleDialog.dismiss();
                    this.visibleDialog = null;
                    // monitorexit(o)
                    o = this.mErrorDialogId;
                    if (o != null) {
                        Log.d("NetflixActivity", "Report end of error dialog ended");
                        this.reportUiModelessViewSessionEnded(IClientLogging$ModalView.errorDialog, (String)o);
                        this.mErrorDialogId = null;
                    }
                    super.onStop();
                }
                catch (Throwable t) {
                    Log.e("NetflixActivity", "Failed to dismiss dialog!", t);
                }
            }
        }
    }
    
    public void performUpAction() {
        UIViewLogUtils.reportUIViewCommand((Context)this, UIViewLogging$UIViewCommandName.upButton, this.getUiScreen(), this.getDataContext());
        if (this.getServiceManager() != null && this.getServiceManager().isUserLoggedIn()) {
            this.startActivity(HomeActivity.createShowIntent(this));
            return;
        }
        this.startActivity(SignupActivity.createShowIntent((Context)this));
    }
    
    protected void registerFinishReceiverWithAutoUnregister(final String s) {
        this.registerReceiverWithAutoUnregister(this.autokillReceiver, s);
    }
    
    public void registerReceiverLocallyWithAutoUnregister(final BroadcastReceiver broadcastReceiver, final IntentFilter intentFilter) {
        LocalBroadcastManager.getInstance((Context)this).registerReceiver(broadcastReceiver, intentFilter);
        this.autoUnregisterLocalReceivers.add(broadcastReceiver);
    }
    
    public void registerReceiverLocallyWithAutoUnregister(final BroadcastReceiver broadcastReceiver, final String s) {
        this.registerReceiverLocallyWithAutoUnregister(broadcastReceiver, new IntentFilter(s));
    }
    
    public void registerReceiverWithAutoUnregister(final BroadcastReceiver broadcastReceiver, final IntentFilter intentFilter) {
        super.registerReceiver(broadcastReceiver, intentFilter);
        this.autoUnregisterReceivers.add(broadcastReceiver);
    }
    
    public void registerReceiverWithAutoUnregister(final BroadcastReceiver broadcastReceiver, final String s) {
        this.registerReceiverWithAutoUnregister(broadcastReceiver, new IntentFilter(s));
    }
    
    public void removeDialogFrag() {
        final FragmentTransaction beginTransaction = this.getFragmentManager().beginTransaction();
        final DialogFragment dialogFragment = this.getDialogFragment();
        if (dialogFragment != null) {
            if (dialogFragment instanceof DialogFragment) {
                dialogFragment.dismiss();
            }
            beginTransaction.remove((Fragment)dialogFragment);
        }
        beginTransaction.commitAllowingStateLoss();
    }
    
    public void removeVisibleDialog() {
        synchronized (this.visibleDialogLock) {
            if (this.visibleDialog != null) {
                this.visibleDialog.dismiss();
                this.visibleDialog = null;
            }
        }
    }
    
    public void reportUiModelessViewSessionEnded(final IClientLogging$ModalView clientLogging$ModalView, final String s) {
        final ApplicationPerformanceMetricsLogging apmSafely = this.getApmSafely();
        if (apmSafely != null) {
            final boolean portrait = DeviceUtils.isPortrait((Context)this);
            if (Log.isLoggable()) {
                Log.d("NetflixActivity", "Report UI modeless view session ended for  " + clientLogging$ModalView + " in portrait: " + portrait + ". Dialog id: " + s);
            }
            apmSafely.endUiModelessViewSession(s);
        }
    }
    
    public String reportUiModelessViewSessionStart(final IClientLogging$ModalView clientLogging$ModalView) {
        final ApplicationPerformanceMetricsLogging apmSafely = this.getApmSafely();
        if (apmSafely != null && clientLogging$ModalView != null) {
            final boolean portrait = DeviceUtils.isPortrait((Context)this);
            final String value = String.valueOf(this.mDialogCount.getAndIncrement());
            if (Log.isLoggable()) {
                Log.d("NetflixActivity", "Report UI modeless view session started for  " + clientLogging$ModalView + " in portrait: " + portrait + ". Dialog id: " + value);
            }
            apmSafely.startUiModelessViewSession(portrait, clientLogging$ModalView, value);
            return value;
        }
        return null;
    }
    
    protected void reportUiViewChanged(final IClientLogging$ModalView clientLogging$ModalView) {
        final ApplicationPerformanceMetricsLogging apmSafely = this.getApmSafely();
        if (apmSafely != null && clientLogging$ModalView != null) {
            final boolean portrait = DeviceUtils.isPortrait((Context)this);
            if (Log.isLoggable()) {
                Log.d("NetflixActivity", "Report UI view change for  " + clientLogging$ModalView + " in portrait: " + portrait);
            }
            apmSafely.uiViewChanged(portrait, clientLogging$ModalView);
        }
    }
    
    public void runInUiThread(final Runnable runnable) {
        if (runnable == null || this.destroyed()) {
            return;
        }
        this.runOnUiThread(runnable);
    }
    
    public void setConnectingToTarget(final boolean mConnectingToTarget) {
        this.mConnectingToTarget = mConnectingToTarget;
    }
    
    @Override
    public void setContentView(final int contentView) {
        super.setContentView(contentView);
        if (this.shouldAttachToolbar()) {
            this.initToolbar();
        }
    }
    
    @Override
    public void setContentView(final View contentView) {
        super.setContentView(contentView);
        if (this.shouldAttachToolbar()) {
            this.initToolbar();
        }
    }
    
    public void setDismissingDialogConfiguration(final NetflixActivity$DismissingDialogConfig mDismissingDialogConfiguration) {
        this.mDismissingDialogConfiguration = mDismissingDialogConfiguration;
    }
    
    @Override
    public void setLoadingStatusCallback(final LoadingStatus$LoadingStatusCallback mLoadingStatusCallback) {
        if (!this.isLoadingData() && mLoadingStatusCallback != null) {
            mLoadingStatusCallback.onDataLoaded(CommonStatus.OK);
            return;
        }
        this.mLoadingStatusCallback = mLoadingStatusCallback;
    }
    
    protected void setupServicemanager() {
        this.serviceManager = new ServiceManager(this, new NetflixActivity$DefaultManagerStatusListener(this, this.createManagerStatusListener(), this.isComingFromBackground(true)));
    }
    
    public boolean shouldAddMdxToMenu() {
        if (!this.showMdxInMenu()) {
            Log.d("NetflixActivity", "Activity does not required MDX.");
            return false;
        }
        if (this.serviceManager == null || !this.serviceManager.isReady() || this.serviceManager.getMdx() == null) {
            Log.w("NetflixActivity", "Service manager or mdx are null or service manager is not ready.");
            return false;
        }
        if (!this.serviceManager.isUserLoggedIn()) {
            Log.d("NetflixActivity", "User is not logged in, not adding MDX icon");
            return false;
        }
        return true;
    }
    
    public boolean shouldApplyPaddingToSlidingPanel() {
        return true;
    }
    
    protected boolean shouldAttachToolbar() {
        return true;
    }
    
    protected boolean shouldFinishOnManagerError() {
        return true;
    }
    
    protected boolean shouldReportNavigationActionEndedOnStop() {
        return true;
    }
    
    protected boolean shouldShowKidsBackground() {
        return this.canApplyBrowseExperience() && BrowseExperience.isKubrickKids();
    }
    
    public boolean showAboutInMenu() {
        return this.canApplyBrowseExperience() && !BrowseExperience.isKubrickKids();
    }
    
    public boolean showAccountInMenu() {
        return this.canApplyBrowseExperience() && !BrowseExperience.isKubrickKids();
    }
    
    protected boolean showCallInProgressFloatingActionButton() {
        return true;
    }
    
    public boolean showContactUsInSlidingMenu() {
        return this.canApplyBrowseExperience() && !BrowseExperience.isKubrickKids();
    }
    
    public void showDialog(final DialogFragment dialogFragment) {
        if (dialogFragment == null || this.isDestroyed || this.isFinishing()) {
            return;
        }
        synchronized (this.instanceStateSaved) {
            if (this.instanceStateSaved.get()) {
                Log.d("NetflixActivity", "Instance state has been saved - skipping showing dialog");
                return;
            }
        }
        while (true) {
            try {
                final FragmentTransaction beginTransaction = this.getFragmentManager().beginTransaction();
                final DialogFragment dialogFragment2 = this.getDialogFragment();
                if (dialogFragment2 != null) {
                    if (dialogFragment2 instanceof DialogFragment) {
                        Log.v("NetflixActivity", "Dismissing previous dialog");
                        dialogFragment2.dismiss();
                    }
                    Log.v("NetflixActivity", "Removing previous dialog");
                    beginTransaction.remove((Fragment)dialogFragment2);
                }
                beginTransaction.addToBackStack((String)null);
                Log.v("NetflixActivity", "Showing dialog");
                final DialogFragment dialogFragment3;
                dialogFragment3.show(beginTransaction, "frag_dialog");
            }
            // monitorexit(atomicBoolean)
            catch (Throwable t) {
                final String string = "Failed to show dialog, " + t;
                Log.e("NetflixActivity", string);
                ErrorLoggingManager.logHandledException(string);
                continue;
            }
            break;
        }
    }
    
    public void showFetchErrorsToast() {
    }
    
    protected boolean showHelpInMenu() {
        return false;
    }
    
    protected void showMDXPostPlayOnResume() {
        while (true) {
            Label_0085: {
                if (!ServiceManagerUtils.isMdxAgentAvailable(this.serviceManager)) {
                    break Label_0085;
                }
                final IMdxSharedState sharedState = this.getSharedState();
                if (sharedState == null || TextUtils.isEmpty((CharSequence)sharedState.getPostplayState())) {
                    break Label_0085;
                }
                final WebApiUtils$VideoIds videoIdsPostplay = ((MdxAgent)this.serviceManager.getMdx()).getVideoIdsPostplay();
                if (videoIdsPostplay == null || videoIdsPostplay.episodeId <= 0) {
                    break Label_0085;
                }
                MDXControllerActivity.showMDXController(this, String.valueOf(videoIdsPostplay.episodeId), videoIdsPostplay.isEpisode, PlayContext.DFLT_MDX_CONTEXT);
                final int n = 1;
                if (n == 0) {
                    MDXControllerActivity.finishMDXController((Context)this);
                }
                return;
            }
            final int n = 0;
            continue;
        }
    }
    
    protected boolean showMdxInMenu() {
        return true;
    }
    
    public boolean showSettingsInMenu() {
        return this.canApplyBrowseExperience() && !BrowseExperience.isKubrickKids();
    }
    
    public boolean showSignOutInMenu() {
        return this.canApplyBrowseExperience() && !BrowseExperience.isKubrickKids();
    }
    
    protected void startLaunchActivityIfVisible() {
        if (this.isVisible && !(this instanceof LaunchActivity)) {
            Log.i("NetflixActivity", "Activity is visible, starting launch activity");
            this.startActivity(RelaunchActivity.createStartIntent(this, "startLaunchActivityIfVisible()").addFlags(131072));
            return;
        }
        Log.v("NetflixActivity", "Activity is not visible, skipping launch of new activity");
    }
    
    public void updateTargetSelectionDialog() {
        if (this.visibleDialog != null && this.visibleDialog.isShowing() && this.visibleDialog instanceof MdxTargetSelectionDialog) {
            this.displayDialog((Dialog)MdxUtils.createMdxTargetSelectionDialog(this, this.mdxFrag));
        }
    }
    
    public void updateVisibleDialog(final Dialog visibleDialog) {
        if (visibleDialog == null) {
            return;
        }
        synchronized (this.visibleDialogLock) {
            if (this.visibleDialog != null) {
                this.visibleDialog.dismiss();
            }
            this.visibleDialog = visibleDialog;
        }
    }
}
