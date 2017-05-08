// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.util.MdxUtils$MdxTargetSelectionDialogInterface;
import com.netflix.mediaclient.ui.mdx.MdxTargetSelectionDialog;
import com.netflix.mediaclient.ui.launch.RelaunchActivity;
import android.app.FragmentManager;
import android.widget.PopupMenu$OnDismissListener;
import com.netflix.mediaclient.util.WebApiUtils$VideoIds;
import com.netflix.mediaclient.ui.player.MDXControllerActivity;
import com.netflix.mediaclient.service.mdx.MdxAgent;
import android.text.TextUtils;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import android.widget.Toast;
import com.netflix.mediaclient.service.webclient.model.leafs.UmaAlert;
import android.app.FragmentTransaction;
import android.app.Fragment;
import com.netflix.mediaclient.ui.home.HomeActivity;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import com.netflix.mediaclient.ui.verifyplay.PlayVerifierVault;
import java.util.Iterator;
import com.netflix.mediaclient.android.widget.advisor.Advisor;
import com.netflix.mediaclient.ui.common.DebugMenuItems;
import android.view.Menu;
import com.netflix.mediaclient.android.debug.DebugOverlay;
import android.content.IntentFilter;
import android.os.Bundle;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.crittercism.app.Crittercism;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import com.netflix.mediaclient.util.Coppola1Utils;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import android.content.res.Resources;
import com.netflix.mediaclient.ui.mdx.MiniPlayerControlsFrag;
import com.netflix.mediaclient.ui.signup.SignupActivity;
import com.netflix.mediaclient.ui.launch.LaunchActivity;
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
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog$Builder;
import android.view.MotionEvent;
import com.netflix.mediaclient.util.MdxUtils;
import android.view.KeyEvent;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.ui.barker_kids.BarkerKidsActionBar;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentListener;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.android.widget.UpdateDialog;
import com.netflix.mediaclient.android.widget.UpdateDialog$Builder;
import com.netflix.mediaclient.service.error.ErrorDescriptor;
import com.netflix.mediaclient.servicemgr.IErrorHandler;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.android.widget.AlertDialogFactory;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
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
import com.netflix.mediaclient.ui.offline.TutorialHelper;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.squareup.seismic.ShakeDetector;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.sothree.slidinguppanel.SlidingUpPanelLayout$PanelSlideListener;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.ui.mdx.IMiniPlayerFrag;
import android.widget.PopupMenu;
import java.util.LinkedList;
import android.widget.RelativeLayout;
import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;
import android.view.MenuItem;
import android.support.design.widget.CoordinatorLayout;
import java.util.concurrent.atomic.AtomicLong;
import android.app.Application$ActivityLifecycleCallbacks;
import android.support.design.widget.FloatingActionButton;
import java.util.concurrent.atomic.AtomicBoolean;
import android.os.Handler;
import android.content.BroadcastReceiver;
import java.util.Set;
import com.netflix.mediaclient.ui.offline.ActivityPageOfflineAgentListener;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.ui.verifyplay.PinAndAgeVerifier$PinAndAgeVerifyCallback;
import com.netflix.mediaclient.ui.mdx.ShowMessageDialogFrag$MessageResponseProvider;
import com.netflix.mediaclient.ui.details.AbsEpisodeView$EpisodeRowListenerProvider;
import com.netflix.mediaclient.android.app.LoadingStatus;
import android.support.v7.app.AppCompatActivity;

@SuppressLint({ "DefaultLocale" })
public abstract class NetflixActivity extends AppCompatActivity implements LoadingStatus, AbsEpisodeView$EpisodeRowListenerProvider, ShowMessageDialogFrag$MessageResponseProvider, PinAndAgeVerifier$PinAndAgeVerifyCallback
{
    private static final long ACTION_BAR_VISIBILITY_CHECK_DELAY_MS = 1000L;
    public static final String ACTION_CS_CALL_ENDED = "com.netflix.mediaclient.ui.cs.ACTION_CALL_ENDED";
    public static final String ACTION_CS_CALL_STARTED = "com.netflix.mediaclient.ui.cs.ACTION_CALL_STARTED";
    public static final String ACTION_DISPLAY_ERROR = "com.netflix.mediaclient.ui.error.ACTION_DISPLAY_ERROR";
    private static final String ACTION_FINISH_ALL_ACTIVITIES = "com.netflix.mediaclient.ui.login.ACTION_FINISH_ALL_ACTIVITIES";
    public static int DL_REQUEST_CODE = 0;
    public static final long EXPAND_MINI_PLAYER_DELAY_MS = 400L;
    public static final String EXTRA_DL_PLAYABLE_ID = "playableId";
    public static final String EXTRA_DL_VIDEO_TYPE = "videoTYpe";
    public static final String EXTRA_ENABLE_TRANSITION_ANIMATION = "com.netflix.mediaclient._TRANSITION_ANIMATION";
    public static final String EXTRA_ENTRY = "entry";
    public static final String EXTRA_FROM = "from";
    private static final String EXTRA_IS_MDX_CONNECTING = "mdx_connecting";
    protected static final String EXTRA_MDX_EXPAND_MINIPLAYER = "expandMinPlayer";
    public static final String EXTRA_PARAM_MESSAGE_ID = "message_id";
    public static final String EXTRA_PARAM_STATUS = "status";
    public static final String EXTRA_PARAM_URL = "url";
    private static final String EXTRA_SHOULD_EXPAND_MINI_PLAYER = "mini_player_expanded";
    public static final String EXTRA_SOURCE = "source";
    public static final String FRAG_DIALOG_TAG = "frag_dialog";
    private static final String INSTANCE_STATE_SAVED_TAG = "NetflixActivity_instanceState";
    public static final int PERMISSIONS_WRITE_EXTERNAL_STORAGE = 1;
    private static final boolean PRINT_LOADING_STATUS = false;
    private static final String TAG = "NetflixActivity";
    private static boolean hasShownEndpointLatch;
    private static boolean isTutorialOn;
    private int actionBarHeight;
    ActivityPageOfflineAgentListener activityPageOfflineAgentListener;
    private final Set<BroadcastReceiver> autoUnregisterLocalReceivers;
    private final Set<BroadcastReceiver> autoUnregisterReceivers;
    private final BroadcastReceiver autokillReceiver;
    private final BroadcastReceiver closeMdxMiniPlayerReceiver;
    private final BroadcastReceiver expandMdxMiniPlayerReceiver;
    protected Handler handler;
    protected final AtomicBoolean instanceStateSaved;
    private boolean isVisible;
    private final BroadcastReceiver localBroadcastReceiver;
    private FloatingActionButton mBackToCustomerSupportCallFAB;
    protected Application$ActivityLifecycleCallbacks mCallback;
    private boolean mConnectingToTarget;
    protected AtomicLong mDialogCount;
    private NetflixActivity$DismissingDialogConfig mDismissingDialogConfiguration;
    private String mErrorDialogId;
    private CoordinatorLayout mFabAnchor;
    protected MenuItem mHelpMenuItem;
    protected boolean mIsTablet;
    protected LoadingStatus$LoadingStatusCallback mLoadingStatusCallback;
    private RelativeLayout mNoNetworkOverlay;
    private final LinkedList<NetflixActivity$ServiceManagerRunnable> mPendingServiceManagerRunnable;
    private final LinkedList<PopupMenu> mShownPopupMenus;
    private IMiniPlayerFrag mdxFrag;
    private NetflixActionBar netflixActionBar;
    private final SlidingUpPanelLayout$PanelSlideListener panelSlideListener;
    private final Runnable printLoadingStatusRunnable;
    private ServiceManager serviceManager;
    private ShakeDetector shakeDetector;
    private boolean shouldExpandMiniPlayer;
    protected SlidingUpPanelLayout slidingPanel;
    private TutorialHelper tutorialHelper;
    private final Runnable updateActionBarVisibilityRunnable;
    private final BroadcastReceiver userAgentUpdateReceiver;
    protected Dialog visibleDialog;
    protected final Object visibleDialogLock;
    
    static {
        NetflixActivity.isTutorialOn = true;
        NetflixActivity.hasShownEndpointLatch = false;
        NetflixActivity.DL_REQUEST_CODE = 3;
    }
    
    public NetflixActivity() {
        this.autoUnregisterReceivers = new HashSet<BroadcastReceiver>();
        this.autoUnregisterLocalReceivers = new HashSet<BroadcastReceiver>();
        this.mDismissingDialogConfiguration = NetflixActivity$DismissingDialogConfig.dismissOnStop;
        this.instanceStateSaved = new AtomicBoolean(false);
        this.visibleDialogLock = new Object();
        this.mDialogCount = new AtomicLong(1L);
        this.mIsTablet = false;
        this.mConnectingToTarget = false;
        this.tutorialHelper = TutorialHelper.EMPTY;
        this.mShownPopupMenus = new LinkedList<PopupMenu>();
        this.mPendingServiceManagerRunnable = new LinkedList<NetflixActivity$ServiceManagerRunnable>();
        this.autokillReceiver = new NetflixActivity$5(this);
        this.expandMdxMiniPlayerReceiver = new NetflixActivity$6(this);
        this.closeMdxMiniPlayerReceiver = new NetflixActivity$7(this);
        this.printLoadingStatusRunnable = new NetflixActivity$8(this);
        this.userAgentUpdateReceiver = new NetflixActivity$12(this);
        this.panelSlideListener = (SlidingUpPanelLayout$PanelSlideListener)new NetflixActivity$13(this);
        this.updateActionBarVisibilityRunnable = new NetflixActivity$14(this);
        this.localBroadcastReceiver = new NetflixActivity$18(this);
    }
    
    private void addFab() {
        while (true) {
            Label_0080: {
                synchronized (this) {
                    if (!(this instanceof ContactUsActivity)) {
                        final boolean customerSupportCallInProgress = this.isCustomerSupportCallInProgress();
                        if (this.mBackToCustomerSupportCallFAB == null || !customerSupportCallInProgress) {
                            break Label_0080;
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
                Log.d("NetflixActivity", "Add FAB to " + this.getLocalClassName());
            }
            this.mFabAnchor = (CoordinatorLayout)this.findViewById(2131755339);
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
                LayoutInflater.from((Context)this).inflate(2130903098, (ViewGroup)this.mFabAnchor, true);
                final FloatingActionButton mBackToCustomerSupportCallFAB = (FloatingActionButton)this.mFabAnchor.findViewById(2131755321);
                if (mBackToCustomerSupportCallFAB == null) {
                    Log.e("NetflixActivity", "Fab is not found in root layout! This should NOT happen!");
                    return;
                }
                final CoordinatorLayout$LayoutParams layoutParams = (CoordinatorLayout$LayoutParams)mBackToCustomerSupportCallFAB.getLayoutParams();
                layoutParams.gravity = 80;
                mBackToCustomerSupportCallFAB.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
                mBackToCustomerSupportCallFAB.setOnClickListener((View$OnClickListener)new NetflixActivity$4(this));
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
        this.registerReceiverWithAutoUnregister((BroadcastReceiver)mdxReceiver, mdxReceiver.getFilter());
        Log.d("NetflixActivity", "Listen to updated from MDX service, added");
    }
    
    private void addNoNetworkOverlay() {
        if (this.showNoNetworkOverlayIfNeeded()) {
            Log.d("NetflixActivity", "Add No network overlay to %s ", this.getLocalClassName());
            if (this.mNoNetworkOverlay != null) {
                ViewUtils.setVisibleOrInvisible((View)this.mNoNetworkOverlay, true);
                return;
            }
            this.mFabAnchor = (CoordinatorLayout)this.findViewById(2131755339);
            if (this.mFabAnchor == null) {
                if (Log.isLoggable()) {
                    Log.w("NetflixActivity", "FAB anchor is NULL for " + this.getLocalClassName());
                }
            }
            else {
                LayoutInflater.from((Context)this).inflate(2130903224, (ViewGroup)this.mFabAnchor, true);
                this.mNoNetworkOverlay = (RelativeLayout)this.mFabAnchor.findViewById(2131755606);
                if (this.mNoNetworkOverlay == null) {
                    Log.e("NetflixActivity", "No network overlay is not found in root layout! This should NOT happen!");
                    return;
                }
                final CoordinatorLayout$LayoutParams layoutParams = (CoordinatorLayout$LayoutParams)this.mNoNetworkOverlay.getLayoutParams();
                layoutParams.gravity = 80;
                this.mNoNetworkOverlay.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
                this.mNoNetworkOverlay.setOnClickListener((View$OnClickListener)new NetflixActivity$3(this));
            }
        }
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
            final IErrorHandler errorHandler = serviceManager.getErrorHandler();
            if (errorHandler != null) {
                final ErrorDescriptor currentError = errorHandler.getCurrentError();
                if (currentError != null) {
                    if (currentError.getData() != null) {
                        Log.d("NetflixActivity", "Display error dialog");
                        AlertDialogFactory.createDialog((Context)this, this.handler, currentError.getData(), new NetflixActivity$19(this, serviceManager, currentError));
                        this.mErrorDialogId = this.reportUiModelessViewSessionStart(IClientLogging$ModalView.errorDialog);
                        synchronized (this.visibleDialogLock) {
                            if (AndroidUtils.isActivityFinishedOrDestroyed((Context)this)) {
                                return;
                            }
                        }
                        if (this.visibleDialog != null) {
                            this.visibleDialog.dismiss();
                        }
                        final UpdateDialog$Builder updateDialog$Builder;
                        final UpdateDialog create = updateDialog$Builder.create();
                        create.show();
                        AlertDialogFactory.activateLinkIfExist(create);
                        this.visibleDialog = create;
                        // monitorexit(o)
                        return;
                    }
                    Log.e("NetflixActivity", "Unable to display an error dialog, data not found!");
                }
            }
        }
    }
    
    private void displayNoNetworkOverlay() {
        synchronized (this) {
            if (ConnectivityUtils.isConnectedOrConnecting((Context)this)) {
                this.removeNoNetworkOverlay();
            }
            else {
                this.addNoNetworkOverlay();
            }
        }
    }
    
    public static void finishAllActivities(final Context context) {
        context.sendBroadcast(new Intent("com.netflix.mediaclient.ui.login.ACTION_FINISH_ALL_ACTIVITIES"));
    }
    
    public static ImageLoader getImageLoader(final Context context) {
        return ((NetflixActivity)context).serviceManager.getImageLoader();
    }
    
    public static OfflineAgentInterface getOfflineAgentOrNull(final NetflixActivity netflixActivity) {
        final ServiceManager serviceManager = ServiceManager.getServiceManager(netflixActivity);
        if (serviceManager != null) {
            return serviceManager.getOfflineAgent();
        }
        return null;
    }
    
    private int getSlidingRightInTransition() {
        if (DeviceUtils.isTabletByContext((Context)this)) {
            return 2130968593;
        }
        return 2130968590;
    }
    
    private int getSlidingRightOutTransition() {
        if (DeviceUtils.isTabletByContext((Context)this)) {
            return 2130968594;
        }
        return 2130968591;
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
    
    private boolean hasMiniPlayerFrag() {
        return this.mdxFrag != null && this.mdxFrag.getSlidingPanelDragView() != null;
    }
    
    private boolean isCustomerSupportCallInProgress() {
        return this.serviceManager != null && this.serviceManager.getVoip() != null && this.serviceManager.getVoip().isCallInProgress() && !(this instanceof ContactUsActivity);
    }
    
    public static boolean isTutorialOn() {
        return NetflixActivity.isTutorialOn;
    }
    
    private void notifyMdxEndOfPostPlay() {
        Log.v("NetflixActivity", "MDX end of postplay");
        if (this.slidingPanel != null) {
            this.slidingPanel.hidePane();
        }
        this.hideMiniPlayer();
        if (this.getNetflixActionBar() != null) {
            this.handler.post((Runnable)new NetflixActivity$9(this));
        }
        LocalBroadcastManager.getInstance((Context)this).sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.MINI_PLAYER_POST_HIDE"));
    }
    
    private void onFromBackground() {
        this.showMDXPostPlayOnResume();
        this.serviceManager.uiComingFromBackground();
    }
    
    private void overridePendingTransitionAnimation(final int n, final int n2) {
        if (AnimationUtils.isTransitionAnimationSupported() && this.allowTransitionAnimation()) {
            if (Log.isLoggable()) {
                Log.d("NetflixActivity", "invoking overridePendingTransition animation on " + this.getClass().getSimpleName());
            }
            this.overridePendingTransition(n, n2);
        }
    }
    
    private void postActionBarUpdate() {
        this.handler.removeCallbacks(this.updateActionBarVisibilityRunnable);
        this.handler.postDelayed(this.updateActionBarVisibilityRunnable, 1000L);
    }
    
    private void removeDownloadButtonListener() {
        final ServiceManager serviceManager = this.getServiceManager();
        if (serviceManager != null && serviceManager.isReady() && this.activityPageOfflineAgentListener != null) {
            serviceManager.getOfflineAgent().removeOfflineAgentListener((OfflineAgentListener)this.activityPageOfflineAgentListener);
        }
    }
    
    private void removeFab() {
        synchronized (this) {
            if (this.mFabAnchor == null || this.mBackToCustomerSupportCallFAB == null) {
                Log.w("NetflixActivity", "Unable to remove FAB!");
            }
            else {
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
    
    public static void setTutorialOn(final boolean b) {
    }
    
    private void setupDownloadButtonListener() {
        if (this.requiresDownloadButtonListener()) {
            final ServiceManager serviceManager = this.getServiceManager();
            if (serviceManager != null && serviceManager.isReady() && serviceManager.isOfflineFeatureAvailable()) {
                this.removeDownloadButtonListener();
                if (this.activityPageOfflineAgentListener != null) {
                    this.activityPageOfflineAgentListener.updateSnackbar(false);
                }
                this.activityPageOfflineAgentListener = new ActivityPageOfflineAgentListener((ViewGroup)this.findViewById(16908290), true);
                serviceManager.getOfflineAgent().addOfflineAgentListener((OfflineAgentListener)this.activityPageOfflineAgentListener);
                this.activityPageOfflineAgentListener.updateSnackbar(false);
            }
        }
    }
    
    private boolean shouldDismissVisibleDialog() {
        if (this.mErrorDialogId == null) {
            final boolean b = this.visibleDialog != null;
            if (this.mDismissingDialogConfiguration == null) {
                this.mDismissingDialogConfiguration = NetflixActivity$DismissingDialogConfig.dismissOnStop;
                return b;
            }
            switch (NetflixActivity$20.$SwitchMap$com$netflix$mediaclient$android$activity$NetflixActivity$DismissingDialogConfig[this.mDismissingDialogConfiguration.ordinal()]) {
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
    
    private void showEndpointPathDebugToast(final ServiceManager serviceManager) {
        if (NetflixActivity.hasShownEndpointLatch) {
            return;
        }
        NetflixActivity.hasShownEndpointLatch = true;
    }
    
    private void updateHelpInMenuStatus() {
        if (this.mHelpMenuItem != null) {
            this.mHelpMenuItem.setVisible(this.getServiceManager().getVoip().isEnabled());
        }
    }
    
    public void addDebugOverlay() {
    }
    
    protected boolean allowTransitionAnimation() {
        return this.getIntent() == null || this.getIntent().getBooleanExtra("com.netflix.mediaclient._TRANSITION_ANIMATION", true);
    }
    
    protected boolean canApplyBrowseExperience() {
        return false;
    }
    
    public boolean canShowSnackBar() {
        return false;
    }
    
    public void closeAllPopupMenus() {
        while (!this.mShownPopupMenus.isEmpty()) {
            this.mShownPopupMenus.pop().dismiss();
        }
    }
    
    protected NetflixActionBar createActionBar() {
        if (this.canApplyBrowseExperience() && BrowseExperience.showKidsExperience()) {
            return new BarkerKidsActionBar(this, this.hasUpAction());
        }
        return new NetflixActionBar(this, this.hasUpAction());
    }
    
    protected ManagerStatusListener createManagerStatusListener() {
        return null;
    }
    
    @Override
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
            this.displayDialog(create);
            return create;
        }
    }
    
    public Dialog displayDialog(final UpdateDialog$Builder updateDialog$Builder) {
        if (updateDialog$Builder == null || AndroidUtils.isActivityFinishedOrDestroyed((Context)this)) {
            return null;
        }
        synchronized (this.visibleDialogLock) {
            final UpdateDialog create = updateDialog$Builder.create();
            this.displayDialog(create);
            return create;
        }
    }
    
    public void displayDialog(final Dialog dialog) {
        if (dialog == null || AndroidUtils.isActivityFinishedOrDestroyed((Context)this)) {
            return;
        }
        synchronized (this.visibleDialogLock) {
            if (AndroidUtils.isActivityFinishedOrDestroyed((Context)this)) {
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
        NetflixActivity$17 netflixActivity$17;
        if (b) {
            netflixActivity$17 = new NetflixActivity$17(this);
        }
        else {
            netflixActivity$17 = null;
        }
        this.displayServiceAgentDialog(format, netflixActivity$17, true);
    }
    
    public void displayServiceAgentDialog(final String s, Runnable visibleDialogLock, final boolean b) {
        final UpdateDialog$Builder dialog = AlertDialogFactory.createDialog((Context)this, this.handler, new AlertDialogFactory$AlertDialogDescriptor(null, s, this.getString(2131296724), visibleDialogLock));
        if (AndroidUtils.isActivityFinishedOrDestroyed((Context)this)) {
            return;
        }
        visibleDialogLock = (Runnable)this.visibleDialogLock;
        // monitorenter(visibleDialogLock)
        Label_0095: {
            if (!b) {
                break Label_0095;
            }
            while (true) {
                try {
                    if (Log.isLoggable()) {
                        Log.d("NetflixActivity", "displayServiceAgentDialog " + s + " isCritical");
                    }
                    this.displayDialog(dialog);
                    return;
                    // iftrue(Label_0159:, this.getVisibleDialog() == null || this.getVisibleDialog().isShowing())
                    // iftrue(Label_0144:, !Log.isLoggable())
                    while (true) {
                        Block_10: {
                            break Block_10;
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
        if (this.mdxFrag != null && this.slidingPanel != null && this.isVisible) {
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
            this.notifyMdxMiniPlayerShown(true);
        }
    }
    
    public void finish() {
        if (Log.isLoggable()) {
            Log.d("NetflixActivity", this.getClass().getSimpleName() + ": finish has been called");
        }
        super.finish();
        this.overridePendingTransitionAnimation(2130968587, this.getSlidingRightOutTransition());
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
        return (AbsEpisodeView$EpisodeRowListener)this.mdxFrag;
    }
    
    public Handler getHandler() {
        return this.handler;
    }
    
    public IMiniPlayerFrag getMdxMiniPlayerFrag() {
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
    
    public TutorialHelper getTutorialHelper() {
        return this.tutorialHelper;
    }
    
    public abstract IClientLogging$ModalView getUiScreen();
    
    public Dialog getVisibleDialog() {
        return this.visibleDialog;
    }
    
    protected void handleAccountDeactivated() {
        if (this.isVisible && !(this instanceof LogoutActivity)) {
            this.startActivity(LogoutActivity.create((Context)this));
        }
        if (this instanceof LogoutActivity) {
            Log.w("NetflixActivity", "Account deactivated, leave to LogoutActivity to complete transition...");
            return;
        }
        this.finish();
    }
    
    protected void handleActionOnNoNetworkOverlay() {
        this.recreate();
    }
    
    protected boolean handleBackPressed() {
        return false;
    }
    
    protected void handleFalkorAgentErrors(final Status status) {
        if (StatusCode.INVALID_COUNRTY.equals(status.getStatusCode())) {
            Log.d("NetflixActivity", "User accessing Netflix in a not supported country. Show alert and kill self");
            this.displayErrorDialog(this.getString(2131296455), status.getStatusCode().getValue(), true);
        }
        else if (StatusCode.INSUFFICIENT_CONTENT.equals(status.getStatusCode())) {
            Log.d("NetflixActivity", "Insufficient content for this profile - cant show lolomo. Show alert and go to profile selection");
            this.displayServiceAgentDialog(this.getString(2131296526), new NetflixActivity$15(this), false);
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
    }
    
    protected void handleProfileReadyToSelect() {
    }
    
    protected void handleProfileSelectionResult(final int n, final String s) {
    }
    
    protected void handleProfilesListUpdated() {
    }
    
    public String handleUserAgentErrors(final Status status) {
        return this.handleUserAgentErrors(status, true);
    }
    
    protected String handleUserAgentErrors(final Status status, final boolean b) {
        String message = status.getMessage();
        if (message == null) {
            message = "";
        }
        switch (NetflixActivity$20.$SwitchMap$com$netflix$mediaclient$StatusCode[status.getStatusCode().ordinal()]) {
            default: {
                final String string = this.getString(2131296855);
                this.displayErrorDialog(this.getString(2131296855), status.getStatusCode().getValue(), b);
                return string;
            }
            case 1: {
                String format = message;
                if (message.isEmpty()) {
                    format = String.format("%s ( %d )", this.getString(2131296845), status.getStatusCode().getValue());
                }
                this.displayServiceAgentDialog(format, null, false);
                return format;
            }
            case 2:
            case 3: {
                final String format2 = String.format("%s ( %d )", this.getString(2131296520), status.getStatusCode().getValue());
                this.displayServiceAgentDialog(format2, new NetflixActivity$16(this), true);
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
                final String format3 = String.format("%s ( %d )", this.getString(2131296845), status.getStatusCode().getValue());
                this.displayServiceAgentDialog(format3, null, false);
                return format3;
            }
            case 13:
            case 14:
            case 15: {
                final String string2 = this.getString(2131296846);
                this.displayErrorDialog(string2, status.getStatusCode().getValue(), b);
                return string2;
            }
            case 16: {
                Log.d("NetflixActivity", "going to signup activity");
                if (LaunchActivity.isSignUpEnabled(this.getServiceManager())) {
                    this.startActivity(SignupActivity.createShowWithNewCookiesIntent((Context)this));
                    this.finish();
                    return "";
                }
                return "";
            }
            case 17: {
                final String string3 = this.getString(2131296703);
                this.displayErrorDialog(this.getString(2131296703), status.getStatusCode().getValue(), b);
                return string3;
            }
        }
    }
    
    protected boolean hasUpAction() {
        return true;
    }
    
    protected void hideMiniPlayer() {
        if (this.slidingPanel != null) {
            this.slidingPanel.setPanelHeight(0);
        }
        final View viewById = this.findViewById(2131755523);
        if (viewById != null) {
            viewById.setVisibility(4);
            viewById.requestLayout();
            if (this.mdxFrag instanceof MiniPlayerControlsFrag) {
                ((MiniPlayerControlsFrag)this.mdxFrag).hideRDP();
            }
        }
    }
    
    protected void initSlidingPanel() {
        this.slidingPanel = (SlidingUpPanelLayout)this.findViewById(2131755311);
        if (this.slidingPanel != null) {
            if (!this.hasMiniPlayerFrag()) {
                this.slidingPanel.setPanelHeight(0);
                return;
            }
            this.slidingPanel.setDragView(this.mdxFrag.getSlidingPanelDragView());
            final SlidingUpPanelLayout slidingPanel = this.slidingPanel;
            final Resources resources = this.getResources();
            int n;
            if (!BrowseExperience.shouldShowMemento((Context)this)) {
                n = 2131427369;
            }
            else {
                n = 2131427816;
            }
            slidingPanel.setPanelHeight(resources.getDimensionPixelSize(n));
            this.slidingPanel.setPanelSlideListener(this.panelSlideListener);
            if (this.shouldApplyPaddingToSlidingPanel()) {
                final View child = this.slidingPanel.getChildAt(0);
                child.setPadding(child.getPaddingLeft(), this.actionBarHeight, child.getPaddingRight(), child.getPaddingBottom());
            }
        }
    }
    
    protected void initToolbar() {
        this.netflixActionBar = this.createActionBar();
    }
    
    boolean isComingFromBackground() {
        final boolean wasInBackground = this.getNetflixApplication().wasInBackground();
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
    
    public boolean isMdxPlayerShowing() {
        return this.mdxFrag != null && this.mdxFrag.isShowing() && this.mdxFrag.isVisible();
    }
    
    public boolean isPanelExpanded() {
        return this.slidingPanel != null && this.slidingPanel.isExpanded();
    }
    
    public boolean isTablet() {
        return this.mIsTablet;
    }
    
    protected void lockScreenOrientation() {
        if (PersistentConfig.getPhoneOrientation((Context)this).ordinal() == ABTestConfig$Cell.CELL_TWO.ordinal()) {
            this.setRequestedOrientation(1);
        }
        else {
            if (Coppola1Utils.isCoppolaExperience((Context)this) && Coppola1Utils.shouldLockActivitiesToPortrait((Activity)this) && !(this instanceof DetailsActivity)) {
                this.setRequestedOrientation(1);
                return;
            }
            if (BrowseExperience.shouldForcePortraitInMemento((Context)this)) {
                this.setRequestedOrientation(1);
            }
        }
    }
    
    public void notifyMdxEndOfPlayback() {
        Log.v("NetflixActivity", "MDX end of playback");
        if (this.slidingPanel != null) {
            this.slidingPanel.hidePane();
        }
        this.hideMiniPlayer();
        if (!PersistentConfig.inAnyMementoTest((Context)this)) {
            this.postActionBarUpdate();
        }
    }
    
    public void notifyMdxMiniPlayerHidden() {
        Log.v("NetflixActivity", "MDX frag hidden");
        String s;
        if (!PersistentConfig.inAnyMementoTest((Context)this)) {
            s = "user_flow_mdx_legacy";
        }
        else if (PersistentConfig.inMementoTest((Context)this)) {
            s = String.format("user_flow_mdx_memento_%d", PersistentConfig.getMemento(NetflixApplication.getContext()).getCellId());
        }
        else {
            s = String.format("user_flow_mdx_memento2_%d", PersistentConfig.getMemento2(NetflixApplication.getContext()).getCellId());
        }
        Crittercism.endUserflow(s);
        this.collapseSlidingPanel();
        this.hideMiniPlayer();
        if (!PersistentConfig.inAnyMementoTest((Context)this)) {
            this.postActionBarUpdate();
        }
    }
    
    public void notifyMdxMiniPlayerShown(final boolean b) {
        Log.v("NetflixActivity", "MDX frag shown");
        String s;
        if (!PersistentConfig.inAnyMementoTest((Context)this)) {
            s = "user_flow_mdx_legacy";
        }
        else if (PersistentConfig.inMementoTest((Context)this)) {
            s = String.format("user_flow_mdx_memento_%d", PersistentConfig.getMemento(NetflixApplication.getContext()).getCellId());
        }
        else {
            s = String.format("user_flow_mdx_memento2_%d", PersistentConfig.getMemento2(NetflixApplication.getContext()).getCellId());
        }
        Crittercism.beginUserflow(s);
        if (this.slidingPanel != null) {
            this.showMiniPlayer();
            if (b) {
                this.slidingPanel.expandPane();
            }
        }
        if (!PersistentConfig.inAnyMementoTest((Context)this)) {
            this.postActionBarUpdate();
        }
    }
    
    public void notifyMdxShowDetailsRequest() {
        this.handler.postDelayed((Runnable)new NetflixActivity$10(this), 250L);
    }
    
    @Override
    protected void onActivityResult(final int n, final int n2, final Intent intent) {
        if (n == NetflixActivity.DL_REQUEST_CODE && n2 == -1 && intent != null) {
            getOfflineAgentOrNull(this).requestOfflineViewing(intent.getStringExtra("playableId"), VideoType.create(intent.getStringExtra("videoTYpe")), PlayContext.OFFLINE_MY_DOWNLOADS_CONTEXT);
        }
    }
    
    @Override
    public final void onBackPressed() {
        if (Log.isLoggable()) {
            Log.v("NetflixActivity", this.getClass().getSimpleName() + ": back button pressed");
        }
        final IMiniPlayerFrag mdxMiniPlayerFrag = this.getMdxMiniPlayerFrag();
        if (mdxMiniPlayerFrag == null || !mdxMiniPlayerFrag.handleBackPressed()) {
            if (this.slidingPanel != null && mdxMiniPlayerFrag != null && this.slidingPanel.isPaneVisible() && this.slidingPanel.isExpanded() && mdxMiniPlayerFrag.isVisible()) {
                if (this.isDialogFragmentVisible()) {
                    if (Log.isLoggable()) {
                        Log.v("NetflixActivity", "DialogFragment is visible, closing...");
                    }
                    this.removeDialogFrag();
                    return;
                }
                if (Log.isLoggable()) {
                    Log.v("NetflixActivity", "MDX mini player sliding panel was expanded, collapsing...");
                }
                this.slidingPanel.collapsePane();
            }
            else if (!this.handleBackPressed()) {
                UIViewLogUtils.reportUIViewCommand((Context)this, UIViewLogging$UIViewCommandName.backButton, this.getUiScreen(), this.getDataContext());
                super.onBackPressed();
            }
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
            this.getWindow().setBackgroundDrawableResource(2131689731);
        }
        this.shouldExpandMiniPlayer = (bundle != null && bundle.getBoolean("mini_player_expanded", false));
        boolean mConnectingToTarget = b;
        if (bundle != null) {
            mConnectingToTarget = b;
            if (bundle.getBoolean("mdx_connecting", false)) {
                mConnectingToTarget = true;
            }
        }
        this.mConnectingToTarget = mConnectingToTarget;
        if (Log.isLoggable()) {
            Log.v("NetflixActivity", "Should expand mini player: " + this.shouldExpandMiniPlayer);
            Log.v("NetflixActivity", "mConnectingToTarget: " + this.mConnectingToTarget);
        }
        this.registerFinishReceiverWithAutoUnregister("com.netflix.mediaclient.ui.login.ACTION_FINISH_ALL_ACTIVITIES");
        this.registerReceiverWithAutoUnregister(this.expandMdxMiniPlayerReceiver, "com.netflix.mediaclient.service.ACTION_EXPAND_MDX_MINI_PLAYER");
        this.registerReceiverWithAutoUnregister(this.closeMdxMiniPlayerReceiver, "com.netflix.mediaclient.service.ACTION_CLOSE_MINI_PLAYER");
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.netflix.mediaclient.ui.error.ACTION_DISPLAY_ERROR");
        intentFilter.addAction("com.netflix.mediaclient.ui.cs.ACTION_CALL_ENDED");
        this.registerReceiverLocallyWithAutoUnregister(this.localBroadcastReceiver, intentFilter);
        ((NetflixApplication)this.getApplication()).refreshLastKnownLocale();
        this.setupServicemanager();
        this.handler = new Handler();
        DebugOverlay.init(this.getApplication());
        if (AndroidUtils.getAndroidVersion() >= 23) {
            this.setAssistBlocked(this.findViewById(16908290), true);
        }
        this.lockScreenOrientation();
        if (this.mCallback != null) {
            this.mCallback.onActivityCreated((Activity)this, bundle);
        }
        this.overridePendingTransitionAnimation(this.getSlidingRightInTransition(), 2130968586);
    }
    
    protected void onCreateOptionsMenu(final Menu menu, final Menu menu2) {
        if (menu2 != null) {
            new DebugMenuItems("NetflixActivity", this).addItems(menu2);
        }
        if (this.showHelpInMenu()) {
            (this.mHelpMenuItem = menu.add((CharSequence)this.getString(2131296668))).setShowAsAction(1);
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
        if (Log.isLoggable()) {
            Log.v("NetflixActivity", "onCreateOptionsMenu");
        }
        this.onCreateOptionsMenu(menu, null);
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    protected void onDestroy() {
        ((NetflixApplication)this.getApplication()).releaseCurrentActivity(this);
        if (Log.isLoggable()) {
            Log.v("NetflixActivity", "Destroying activity: " + this.getClass().getSimpleName() + ", hash: " + this.hashCode());
        }
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
        this.mPendingServiceManagerRunnable.clear();
        super.onDestroy();
        if (this.mCallback != null) {
            this.mCallback.onActivityDestroyed((Activity)this);
        }
        Advisor.destroyAll();
    }
    
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
    
    @Override
    protected void onNewIntent(final Intent intent) {
        if (Log.isLoggable()) {
            Log.d("NetflixActivity", "Received new intent:", intent);
        }
        if (this.shouldSetIntentOnNewIntent()) {
            this.setIntent(intent);
        }
        super.onNewIntent(intent);
    }
    
    @Override
    public void onOfflineDownloadPinAndAgeVerified(final boolean b, final PlayVerifierVault playVerifierVault) {
        throw new IllegalStateException(String.format("onPlayVerified vault: %s", playVerifierVault));
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
        if (this.mCallback != null) {
            this.mCallback.onActivityPaused((Activity)this);
        }
        this.removeDownloadButtonListener();
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
    public void onRequestPermissionsResult(final int n, final String[] array, final int[] array2) {
        switch (n) {
            case 232: {
                if (array2.length > 0 && array2[0] == 0) {
                    PerformanceProfiler.getInstance().dumpToDisk(this);
                    return;
                }
                break;
            }
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
        if (this.isComingFromBackground()) {
            this.onFromBackground();
        }
        this.setInstanceStateSaved(false);
        netflixApplication.setCurrentActivity(this);
        this.isVisible = true;
        this.handler.post(this.printLoadingStatusRunnable);
        netflixApplication.stopActivityTransitionTimer();
        this.addFab();
        this.displayNoNetworkOverlay();
        if (this.mCallback != null) {
            this.mCallback.onActivityResumed((Activity)this);
        }
        this.setupDownloadButtonListener();
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
        bundle.putBoolean("mdx_connecting", this.isConnectingToTarget());
        if (this.mCallback != null) {
            this.mCallback.onActivitySaveInstanceState((Activity)this, bundle);
        }
    }
    
    protected void onSlidingPanelCollapsed(final View view) {
    }
    
    protected void onSlidingPanelExpanded(final View view) {
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        UserActionLogUtils.reportNavigationActionStarted((Context)this, (UserActionLogging$CommandName)null, this.getUiScreen());
        this.mdxFrag = (IMiniPlayerFrag)this.getFragmentManager().findFragmentByTag("mini_player");
        this.initSlidingPanel();
        if (this.mCallback != null) {
            this.mCallback.onActivityStarted((Activity)this);
        }
    }
    
    @Override
    protected void onStop() {
        if (this.shouldReportNavigationActionEndedOnStop()) {
            UserActionLogUtils.reportNavigationActionEnded((Context)this, this.getUiScreen(), IClientLogging$CompletionReason.success, (UIError)null);
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
                        if (Log.isLoggable()) {
                            Log.d("NetflixActivity", "Report end of error dialog ended");
                        }
                        this.reportUiModelessViewSessionEnded(IClientLogging$ModalView.errorDialog, (String)o);
                        this.mErrorDialogId = null;
                    }
                    super.onStop();
                    if (this.mCallback != null) {
                        this.mCallback.onActivityStopped((Activity)this);
                    }
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
        }
        else {
            this.startActivity(SignupActivity.createShowIntent((Context)this));
        }
        this.overridePendingTransitionAnimation(2130968587, this.getSlidingRightOutTransition());
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
    
    public void removeNoNetworkOverlay() {
        if (this.mNoNetworkOverlay != null) {
            ViewUtils.setVisibleOrInvisible((View)this.mNoNetworkOverlay, false);
        }
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
    
    public void requestDownloadButtonRefresh(final String s) {
        if (this.activityPageOfflineAgentListener != null) {
            this.activityPageOfflineAgentListener.refreshDownloadButton(s, this);
        }
    }
    
    protected boolean requiresDownloadButtonListener() {
        return false;
    }
    
    public void runInUiThread(final Runnable runnable) {
        if (runnable == null || AndroidUtils.isActivityFinishedOrDestroyed((Context)this)) {
            return;
        }
        this.runOnUiThread(runnable);
    }
    
    public void runWhenManagerIsReady(final NetflixActivity$ServiceManagerRunnable netflixActivity$ServiceManagerRunnable) {
        if (this.getServiceManager().isReady()) {
            netflixActivity$ServiceManagerRunnable.run(this.getServiceManager());
            return;
        }
        this.mPendingServiceManagerRunnable.push(netflixActivity$ServiceManagerRunnable);
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
        this.addDebugOverlay();
    }
    
    @Override
    public void setContentView(final View contentView) {
        super.setContentView(contentView);
        if (this.shouldAttachToolbar()) {
            this.initToolbar();
        }
        this.addDebugOverlay();
    }
    
    public void setDismissingDialogConfiguration(final NetflixActivity$DismissingDialogConfig mDismissingDialogConfiguration) {
        this.mDismissingDialogConfiguration = mDismissingDialogConfiguration;
    }
    
    public void setLifeCycleListener(final Application$ActivityLifecycleCallbacks mCallback) {
        this.mCallback = mCallback;
    }
    
    @Override
    public void setLoadingStatusCallback(final LoadingStatus$LoadingStatusCallback mLoadingStatusCallback) {
        if (!this.isLoadingData() && mLoadingStatusCallback != null) {
            mLoadingStatusCallback.onDataLoaded(CommonStatus.OK);
            return;
        }
        this.mLoadingStatusCallback = mLoadingStatusCallback;
    }
    
    public void setSlidingEnabled(final boolean slidingEnabled) {
        if (this.slidingPanel != null) {
            this.slidingPanel.setSlidingEnabled(slidingEnabled);
        }
    }
    
    protected void setupServicemanager() {
        this.serviceManager = new ServiceManager((Context)this, new NetflixActivity$DefaultManagerStatusListener(this, this.createManagerStatusListener(), this.isComingFromBackground()));
    }
    
    public final boolean shouldAddMdxToMenu() {
        if (!this.showMdxInMenu()) {
            Log.d("NetflixActivity", "Activity does not required MDX.");
        }
        else {
            final ServiceManager serviceManager = this.serviceManager;
            if (serviceManager == null || !serviceManager.isReady()) {
                Log.w("NetflixActivity", "Service manager is null or service manager is not ready.");
                return false;
            }
            if (!serviceManager.isUserLoggedIn()) {
                Log.d("NetflixActivity", "User is not logged in, not adding MDX icon");
                return false;
            }
            final UmaAlert userMessageAlert = serviceManager.getUserMessageAlert();
            if (userMessageAlert == null || !userMessageAlert.blocking()) {
                return MdxUtils.isAnyMdxTargetAvailable(this.getServiceManager().getMdx());
            }
        }
        return false;
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
    
    protected boolean shouldSetIntentOnNewIntent() {
        return true;
    }
    
    protected boolean shouldShowKidsBackground() {
        return this.canApplyBrowseExperience() && BrowseExperience.showKidsExperience();
    }
    
    protected boolean shouldStartLaunchActivityIfVisibleOnManagerUnavailable() {
        return true;
    }
    
    public boolean showAboutInMenu() {
        return this.canApplyBrowseExperience() && !BrowseExperience.showKidsExperience();
    }
    
    public boolean showAccountInMenu() {
        return this.canApplyBrowseExperience() && !BrowseExperience.showKidsExperience();
    }
    
    protected boolean showCallInProgressFloatingActionButton() {
        return true;
    }
    
    public boolean showContactUsInSlidingMenu() {
        return this.canApplyBrowseExperience() && !BrowseExperience.showKidsExperience();
    }
    
    public void showDebugToast(String string) {
        if (NetflixApplication.isDebugToastEnabled()) {
            string = "DEBUG: " + string;
            if (Log.isLoggable()) {
                Log.v("NetflixActivity", "Showing toast: " + string);
            }
            Toast.makeText((Context)this, (CharSequence)string, 1).show();
        }
    }
    
    public boolean showDialog(final DialogFragment dialogFragment) {
        if (dialogFragment == null || AndroidUtils.isActivityFinishedOrDestroyed((Context)this)) {
            return false;
        }
        final DialogFragment dialogFragment2 = this.getDialogFragment();
        if (dialogFragment2 != null && dialogFragment2.isVisible()) {
            final String string = "SPY-10201, Dialog fragment already visible (" + this.getDialogFragment() + ") while trying to display " + dialogFragment + ". There should only be one visible at time.";
            Log.w("NetflixActivity", string);
            ErrorLoggingManager.logHandledException(new IllegalArgumentException(string));
            return true;
        }
        synchronized (this.instanceStateSaved) {
            if (this.instanceStateSaved.get()) {
                Log.d("NetflixActivity", "Instance state has been saved - skipping showing dialog");
                return false;
            }
            try {
                final FragmentTransaction beginTransaction = this.getFragmentManager().beginTransaction();
                final DialogFragment dialogFragment3 = this.getDialogFragment();
                if (dialogFragment3 != null) {
                    if (dialogFragment3 instanceof DialogFragment) {
                        Log.v("NetflixActivity", "Dismissing previous dialog");
                        dialogFragment3.dismiss();
                    }
                    Log.v("NetflixActivity", "Removing previous dialog");
                    beginTransaction.remove((Fragment)dialogFragment3);
                }
                beginTransaction.addToBackStack((String)null);
                Log.v("NetflixActivity", "Showing dialog");
                ViewUtils.safeShowDialogFragment(dialogFragment, this.getFragmentManager(), beginTransaction, "frag_dialog");
                return true;
            }
            catch (Throwable t) {
                final String string2 = "Failed to show dialog, " + t;
                Log.e("NetflixActivity", string2);
                ErrorLoggingManager.logHandledException(string2);
                return false;
            }
        }
    }
    
    public void showFetchErrorsToast() {
        if (NetflixApplication.isDebugToastEnabled()) {
            final StringBuilder append = new StringBuilder().append("DEBUG: ");
            String s;
            if (NetflixService.areFetchErrorsEnabled()) {
                s = "Fetch errors ENABLED";
            }
            else {
                s = "Fetch errors DISABLED";
            }
            Toast.makeText((Context)this, (CharSequence)append.append(s).toString(), 1).show();
        }
    }
    
    protected boolean showHelpInMenu() {
        return false;
    }
    
    protected void showMDXPostPlayOnResume() {
        if (ServiceManagerUtils.isMdxAgentAvailable(this.serviceManager)) {
            final IMdxSharedState sharedState = this.getSharedState();
            while (true) {
                Label_0173: {
                    if (sharedState == null || TextUtils.isEmpty((CharSequence)sharedState.getPostplayState())) {
                        break Label_0173;
                    }
                    final WebApiUtils$VideoIds videoIdsPostplay = ((MdxAgent)this.serviceManager.getMdx()).getVideoIdsPostplay();
                    if (videoIdsPostplay == null || videoIdsPostplay.episodeId <= 0) {
                        break Label_0173;
                    }
                    if (BrowseExperience.shouldShowMemento((Context)this)) {
                        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.MINI_PLAYER_POST_PLAY_TITLE_NEXT");
                        intent.putExtra("id", String.valueOf(videoIdsPostplay.episodeId));
                        LocalBroadcastManager.getInstance((Context)this).sendBroadcast(intent);
                        LocalBroadcastManager.getInstance((Context)this).sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.RDP_CLOSE"));
                        MiniPlayerControlsFrag.sendShowAndDisableIntent((Context)this);
                    }
                    else {
                        final WebApiUtils$VideoIds videoIds = this.getServiceManager().getMdx().getVideoIds();
                        if (videoIds != null && videoIds.episodeId > 0) {
                            MDXControllerActivity.showMDXController(this, String.valueOf(videoIds.episodeId), true, PlayContext.DFLT_MDX_CONTEXT);
                        }
                    }
                    final int n = 1;
                    if (n == 0) {
                        MDXControllerActivity.finishMDXController((Context)this);
                        return;
                    }
                    return;
                }
                final int n = 0;
                continue;
            }
        }
    }
    
    protected boolean showMdxInMenu() {
        return true;
    }
    
    public boolean showMenu(final PopupMenu popupMenu) {
        if (popupMenu == null || AndroidUtils.isActivityFinishedOrDestroyed((Context)this)) {
            return false;
        }
        popupMenu.show();
        this.mShownPopupMenus.push(popupMenu);
        popupMenu.setOnDismissListener((PopupMenu$OnDismissListener)new NetflixActivity$11(this));
        return true;
    }
    
    protected void showMiniPlayer() {
        if (this.mdxFrag != null && this.slidingPanel != null) {
            final SlidingUpPanelLayout slidingPanel = this.slidingPanel;
            final Resources resources = this.getResources();
            int n;
            if (!BrowseExperience.shouldShowMemento((Context)this)) {
                n = 2131427369;
            }
            else {
                n = 2131427816;
            }
            slidingPanel.setPanelHeight(resources.getDimensionPixelSize(n));
            this.slidingPanel.showPane();
            final View viewById = this.findViewById(2131755523);
            if (viewById != null) {
                viewById.setVisibility(0);
            }
            final IMiniPlayerFrag mdxMiniPlayerFrag = this.getMdxMiniPlayerFrag();
            if (!mdxMiniPlayerFrag.isShowing()) {
                final FragmentManager fragmentManager = this.getFragmentManager();
                if (fragmentManager != null) {
                    fragmentManager.beginTransaction().show((Fragment)this.getMdxMiniPlayerFrag()).commit();
                    fragmentManager.executePendingTransactions();
                }
            }
            if (mdxMiniPlayerFrag instanceof MiniPlayerControlsFrag) {
                ((MiniPlayerControlsFrag)mdxMiniPlayerFrag).hideRDP();
                ((MiniPlayerControlsFrag)mdxMiniPlayerFrag).hideRelatedGroup();
            }
        }
    }
    
    protected boolean showNoNetworkOverlayIfNeeded() {
        return false;
    }
    
    public boolean showOfflineInMenu() {
        return this.canApplyBrowseExperience();
    }
    
    public boolean showSettingsInMenu() {
        return this.canApplyBrowseExperience() && !BrowseExperience.showKidsExperience();
    }
    
    public boolean showSignOutInMenu() {
        return ConnectivityUtils.isConnected((Context)this) && this.canApplyBrowseExperience() && !BrowseExperience.showKidsExperience();
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
            this.displayDialog(MdxUtils.createMdxTargetSelectionDialog(this, (MdxUtils$MdxTargetSelectionDialogInterface)this.mdxFrag));
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
