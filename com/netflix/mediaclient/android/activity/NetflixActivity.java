// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.ui.search.SearchUtils;
import com.netflix.mediaclient.ui.RelaunchActivity;
import com.netflix.mediaclient.ui.LaunchActivity;
import com.netflix.mediaclient.util.WebApiUtils;
import com.netflix.mediaclient.ui.player.MDXControllerActivity;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.service.mdx.MdxAgent;
import android.text.TextUtils;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.content.IntentFilter;
import com.netflix.mediaclient.ui.home.HomeActivity;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.UserActionLogging;
import com.netflix.mediaclient.ui.pin.PinDialogVault;
import java.util.Iterator;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.ui.settings.SettingsActivity;
import com.netflix.mediaclient.ui.common.DebugMenuItems;
import android.os.Bundle;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.servicemgr.UIViewLogging;
import com.netflix.mediaclient.ui.ServiceErrorsHandler;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.ui.login.LogoutActivity;
import com.netflix.mediaclient.servicemgr.IMdxSharedState;
import com.netflix.mediaclient.NetflixApplication;
import android.app.DialogFragment;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import java.io.Serializable;
import com.netflix.mediaclient.android.widget.AlertDialogFactory;
import com.netflix.mediaclient.android.widget.UpdateDialog;
import android.app.AlertDialog;
import android.app.AlertDialog$Builder;
import android.view.MotionEvent;
import android.view.KeyEvent;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.ui.kids.NetflixKidsActionBar;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import android.content.res.TypedArray;
import android.util.TypedValue;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import com.netflix.mediaclient.ui.mdx.MdxReceiver;
import com.netflix.mediaclient.ui.settings.AboutActivity;
import android.view.MenuItem;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.Menu;
import android.view.View;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import java.util.HashSet;
import android.app.Dialog;
import android.app.ActionBar;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicBoolean;
import android.os.Handler;
import android.content.BroadcastReceiver;
import java.util.Set;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.ui.pin.PinVerifier;
import com.netflix.mediaclient.ui.mdx.ShowMessageDialogFrag;
import com.netflix.mediaclient.android.app.LoadingStatus;
import com.netflix.mediaclient.ui.details.EpisodeRowView;
import android.app.Activity;

@SuppressLint({ "DefaultLocale" })
public abstract class NetflixActivity extends Activity implements EpisodeRowListenerProvider, LoadingStatus, MessageResponseProvider, PinVerificationCallback
{
    private static final long ACTION_BAR_VISIBILITY_CHECK_DELAY_MS = 1000L;
    private static final String ACTION_FINISH_ALL_ACTIVITIES = "com.netflix.mediaclient.ui.login.ACTION_FINISH_ALL_ACTIVITIES";
    public static final long EXPAND_MINI_PLAYER_DELAY_MS = 400L;
    private static final String EXTRA_SHOULD_EXPAND_MINI_PLAYER = "mini_player_expanded";
    private static final String FRAG_DIALOG_TAG = "frag_dialog";
    private static final String INSTANCE_STATE_SAVED_TAG = "NetflixActivity_instanceState";
    public static final String INTENT_CATEGORY_UI = "LocalIntentNflxUi";
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
    protected AtomicLong mDialogCount;
    protected boolean mIsTablet;
    protected LoadingStatusCallback mLoadingStatusCallback;
    private MdxMiniPlayerFrag mdxFrag;
    private NetflixActionBar netflixActionBar;
    private final SlidingUpPanelLayout.PanelSlideListener panelSlideListener;
    private final Runnable printLoadingStatusRunnable;
    private ServiceManager serviceManager;
    private boolean shouldExpandMiniPlayer;
    private SlidingUpPanelLayout slidingPanel;
    private ActionBar systemActionBar;
    private final Runnable updateActionBarVisibilityRunnable;
    private final BroadcastReceiver userAgentUpdateReceiver;
    protected Dialog visibleDialog;
    protected Object visibleDialogLock;
    
    public NetflixActivity() {
        this.autoUnregisterReceivers = new HashSet<BroadcastReceiver>();
        this.autoUnregisterLocalReceivers = new HashSet<BroadcastReceiver>();
        this.instanceStateSaved = new AtomicBoolean(false);
        this.visibleDialogLock = new Object();
        this.mDialogCount = new AtomicLong(1L);
        this.mIsTablet = false;
        this.autokillReceiver = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                Log.v("NetflixActivity", "Finishing activity " + NetflixActivity.this.getClass().getSimpleName() + " from intent: " + intent.getAction());
                NetflixActivity.this.finish();
            }
        };
        this.expandMdxMiniPlayerReceiver = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                if (NetflixActivity.this.destroyed()) {
                    return;
                }
                if (intent == null || !"com.netflix.mediaclient.service.ACTION_EXPAND_MDX_MINI_PLAYER".equals(intent.getAction())) {
                    Log.v("NetflixActivity", "Invalid intent: ");
                    AndroidUtils.logIntent("NetflixActivity", intent);
                    return;
                }
                NetflixActivity.this.expandMiniPlayerIfVisible();
            }
        };
        this.printLoadingStatusRunnable = new Runnable() {
            @Override
            public void run() {
            }
        };
        this.userAgentUpdateReceiver = new BroadcastReceiver() {
            private void logWithClassName(final String s) {
                if (Log.isLoggable("NetflixActivity", 3)) {
                    Log.d("NetflixActivity", NetflixActivity.this.getClass().getSimpleName() + ": " + s);
                }
            }
            
            public void onReceive(final Context context, final Intent intent) {
                if (intent == null) {
                    this.logWithClassName("Null intent");
                    return;
                }
                final String action = intent.getAction();
                if ("com.netflix.mediaclient.intent.action.NOTIFY_USER_PROFILE_ACTIVE".equals(action)) {
                    this.logWithClassName("User profile activated - restarting app");
                    PinVerifier.getInstance().clearState();
                    NetflixActivity.this.handleProfileActivated();
                    return;
                }
                if ("com.netflix.mediaclient.intent.action.NOTIFY_USER_ACCOUNT_DEACTIVE".equals(action)) {
                    this.logWithClassName("Account deactivated - restarting app");
                    NetflixActivity.this.handleAccountDeactivated();
                    return;
                }
                if ("com.netflix.mediaclient.intent.action.NOTIFY_USER_PROFILE_READY_TO_SELECT".equals(action)) {
                    this.logWithClassName("Ready to select profile - calling children");
                    NetflixActivity.this.handleProfileReadyToSelect();
                    return;
                }
                if ("com.netflix.mediaclient.intent.action.NOTIFY_USER_PROFILE_SELECTION_RESULT".equals(action)) {
                    final int intExtra = intent.getIntExtra("com.netflix.mediaclient.intent.action.EXTRA_USER_PROFILE_SELECTION_RESULT_INT", StatusCode.OK.getValue());
                    final String stringExtra = intent.getStringExtra("com.netflix.mediaclient.intent.action.EXTRA_USER_PROFILE_SELECTION_RESULT_STRING");
                    this.logWithClassName("Profile selection status: " + intExtra);
                    NetflixActivity.this.handleProfileSelectionResult(intExtra, stringExtra);
                    return;
                }
                if ("com.netflix.mediaclient.intent.action.NOTIFY_PROFILES_LIST_UPDATED".equals(action)) {
                    this.logWithClassName("Profiles list updated!");
                    NetflixActivity.this.handleProfilesListUpdated();
                    return;
                }
                this.logWithClassName("No action taken for intent: " + action);
            }
        };
        this.panelSlideListener = new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelAnchored(final View view) {
                Log.v("NetflixActivity", "onPanelAnchored");
            }
            
            @Override
            public void onPanelCollapsed(final View view) {
                Log.v("NetflixActivity", "onPanelCollapsed");
                NetflixActivity.this.onSlidingPanelCollapsed(view);
                if (NetflixActivity.this.mdxFrag != null) {
                    NetflixActivity.this.mdxFrag.onPanelCollapsed();
                }
            }
            
            @Override
            public void onPanelExpanded(final View view) {
                Log.v("NetflixActivity", "onPanelExpanded");
                NetflixActivity.this.onSlidingPanelExpanded(view);
                if (NetflixActivity.this.mdxFrag != null) {
                    NetflixActivity.this.mdxFrag.onPanelExpanded();
                }
            }
            
            @Override
            public void onPanelSlide(final View view, final float n) {
                if (Log.isLoggable("NetflixActivity", 2)) {
                    Log.v("NetflixActivity", "onPanelSlide, offset: " + n);
                }
                if (NetflixActivity.this.mdxFrag != null) {
                    NetflixActivity.this.mdxFrag.onPanelSlide(n);
                }
                if (NetflixActivity.this.systemActionBar != null) {
                    if (n < 0.3f) {
                        if (NetflixActivity.this.systemActionBar.isShowing()) {
                            NetflixActivity.this.systemActionBar.hide();
                        }
                    }
                    else if (!NetflixActivity.this.systemActionBar.isShowing()) {
                        NetflixActivity.this.systemActionBar.show();
                    }
                }
            }
        };
        this.updateActionBarVisibilityRunnable = new Runnable() {
            @Override
            public void run() {
                if (NetflixActivity.this.isVisible && !NetflixActivity.this.destroyed() && NetflixActivity.this.mdxFrag != null && NetflixActivity.this.slidingPanel != null && NetflixActivity.this.systemActionBar != null) {
                    if (Log.isLoggable("NetflixActivity", 2)) {
                        Log.v("NetflixActivity", "Checking to see if action bar visibility is valid.  Frag showing: " + NetflixActivity.this.mdxFrag.isShowing() + ", panel expanded: " + NetflixActivity.this.slidingPanel.isExpanded() + ", system action bar showing: " + NetflixActivity.this.systemActionBar.isShowing());
                    }
                    if (NetflixActivity.this.mdxFrag.isShowing() && NetflixActivity.this.slidingPanel.isExpanded()) {
                        if (NetflixActivity.this.systemActionBar.isShowing()) {
                            Log.v("NetflixActivity", "Hiding action bar since it should not be shown");
                            NetflixActivity.this.systemActionBar.hide();
                        }
                    }
                    else if (!NetflixActivity.this.systemActionBar.isShowing()) {
                        Log.v("NetflixActivity", "Showing action bar since it should not be hidden");
                        NetflixActivity.this.systemActionBar.show();
                    }
                }
            }
        };
    }
    
    private void addAboutTool(final Menu menu) {
        menu.add(2131493008).setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new MenuItem$OnMenuItemClickListener() {
            public boolean onMenuItemClick(final MenuItem menuItem) {
                NetflixActivity.this.startActivity(AboutActivity.createStartIntent(NetflixActivity.this));
                return true;
            }
        });
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
    
    private int computeActionBarHeight() {
        final TypedValue typedValue = new TypedValue();
        this.getTheme().resolveAttribute(16843470, typedValue, true);
        final TypedArray obtainStyledAttributes = this.obtainStyledAttributes(typedValue.resourceId, new int[] { 16843093 });
        final int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, -1);
        obtainStyledAttributes.recycle();
        if (Log.isLoggable("NetflixActivity", 2)) {
            Log.v("NetflixActivity", "computed action bar height as: " + dimensionPixelSize);
        }
        return dimensionPixelSize;
    }
    
    public static void finishAllActivities(final Context context) {
        context.sendBroadcast(new Intent("com.netflix.mediaclient.ui.login.ACTION_FINISH_ALL_ACTIVITIES"));
    }
    
    public static ImageLoader getImageLoader(final Context context) {
        return ((NetflixActivity)context).serviceManager.getImageLoader();
    }
    
    private void onFromBackground() {
        this.showMDXPostPlayOnResume();
    }
    
    private void postActionBarUpdate() {
        this.handler.removeCallbacks(this.updateActionBarVisibilityRunnable);
        this.handler.postDelayed(this.updateActionBarVisibilityRunnable, 1000L);
    }
    
    private void setInstanceStateSaved(final boolean b) {
        Log.v("NetflixActivity_instanceState", this.getClass().getSimpleName() + " instanceStateSaved: " + b);
        synchronized (this.instanceStateSaved) {
            this.instanceStateSaved.set(b);
        }
    }
    
    protected NetflixActionBar createActionBar(final ActionBar actionBar) {
        if (this.isForKids()) {
            return new NetflixKidsActionBar(this, this.hasUpAction());
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
        return (this.mdxFrag != null && this.mdxFrag.dispatchKeyEvent(keyEvent)) || super.dispatchKeyEvent(keyEvent);
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
    
    public Dialog displayDialog(final UpdateDialog.Builder builder) {
        if (builder == null || this.destroyed()) {
            return null;
        }
        synchronized (this.visibleDialogLock) {
            final UpdateDialog create = builder.create();
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
        if (this.visibleDialog != null) {
            this.visibleDialog.dismiss();
        }
        final Dialog visibleDialog;
        visibleDialog.show();
        this.visibleDialog = visibleDialog;
    }
    // monitorexit(o)
    
    protected void displayErrorDialog(final String s, final int n) {
        this.displayUserAgentDialog(String.format("%s ( %d )", s, n), new Runnable() {
            @Override
            public void run() {
                NetflixActivity.this.finish();
            }
        }, true);
    }
    
    protected void displayUserAgentDialog(final String s, Runnable visibleDialogLock, final boolean b) {
        final UpdateDialog.Builder dialog = AlertDialogFactory.createDialog((Context)this, this.handler, new AlertDialogFactory.AlertDialogDescriptor(null, s, this.getString(17039370), visibleDialogLock));
        if (this.destroyed()) {
            return;
        }
        visibleDialogLock = (Runnable)this.visibleDialogLock;
        // monitorenter(visibleDialogLock)
        Label_0098: {
            if (!b) {
                break Label_0098;
            }
        Block_10_Outer:
            while (true) {
                try {
                    if (Log.isLoggable("NetflixActivity", 3)) {
                        Log.d("NetflixActivity", "displayUserAgentDialog " + s + " isCritical");
                    }
                    this.displayDialog(dialog);
                    return;
                    while (true) {
                        while (true) {
                            this.displayDialog(dialog);
                            return;
                            Log.d("NetflixActivity", "displayUserAgentDialog " + s);
                            continue Block_10_Outer;
                        }
                        continue;
                    }
                }
                // iftrue(Label_0165:, this.getVisibleDialog() == null || this.getVisibleDialog().isShowing())
                // iftrue(Label_0150:, !Log.isLoggable("NetflixActivity", 3))
                finally {
                }
                // monitorexit(visibleDialogLock)
                final String s2;
                Label_0165: {
                    if (this.getVisibleDialog() == null) {
                        if (Log.isLoggable("NetflixActivity", 3)) {
                            Log.d("NetflixActivity", "displayUserAgentDialog, no dialog  " + s2);
                        }
                        this.displayDialog(dialog);
                        return;
                    }
                }
                if (Log.isLoggable("NetflixActivity", 3)) {
                    Log.e("NetflixActivity", "displayUserAgentDialog, Dialog visible, skipping  " + s2);
                }
            }
        }
    }
    
    protected void expandMiniPlayerIfVisible() {
        if (this.isVisible) {
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
            if (this.mdxFrag != null && this.mdxFrag.isShowing() && this.slidingPanel != null) {
                Log.v("NetflixActivity", "Expanding mini player");
                String s;
                if (this.slidingPanel.expandPane()) {
                    s = "Panel is expanding";
                }
                else {
                    s = "Panel is NOT expanding";
                }
                Log.v("NetflixActivity", s);
            }
        }
    }
    
    public void finish() {
        if (Log.isLoggable("NetflixActivity", 3)) {
            Log.d("NetflixActivity", this.getClass().getSimpleName() + ": finish has been called");
        }
        super.finish();
    }
    
    public int getActionBarHeight() {
        return this.actionBarHeight;
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
    
    public EpisodeRowListener getEpisodeRowListener() {
        return this.mdxFrag;
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
    
    public abstract IClientLogging.ModalView getUiScreen();
    
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
    
    @SuppressLint({ "DefaultLocale" })
    protected void handleUserAgentErrors(final Activity activity, final Status status) {
        String message = status.getMessage();
        if (message == null) {
            message = "";
        }
        switch (status.getStatusCode()) {
            default: {
                this.displayErrorDialog(this.getString(2131493278), status.getStatusCode().getValue());
            }
            case NRD_LOGIN_ACTIONID_3: {
                this.displayUserAgentDialog(message, null, false);
            }
            case NRD_LOGIN_ACTIONID_4:
            case NRD_LOGIN_ACTIONID_8: {
                this.displayUserAgentDialog(String.format("%s ( %d )", this.getString(2131493283), status.getStatusCode().getValue()), new Runnable() {
                    @Override
                    public void run() {
                        Log.d("NetflixActivity", "Restarting app, time: " + System.nanoTime());
                        NetflixActivity.this.finish();
                        NetflixActivity.this.startActivity(LogoutActivity.create((Context)NetflixActivity.this));
                    }
                }, true);
            }
            case NRD_LOGIN_ACTIONID_9: {
                ServiceErrorsHandler.handleManagerResponse(this, CommonStatus.OBSOLETE_APP_VERSION);
            }
            case NRD_LOGIN_ACTIONID_1:
            case NRD_LOGIN_ACTIONID_2:
            case NRD_LOGIN_ACTIONID_5:
            case NRD_LOGIN_ACTIONID_6:
            case NRD_LOGIN_ACTIONID_7:
            case NRD_LOGIN_ACTIONID_10:
            case NRD_LOGIN_ACTIONID_11:
            case NRD_LOGIN_ACTIONID_12: {
                this.displayUserAgentDialog(String.format("%s ( %d )", this.getString(2131493274), status.getStatusCode().getValue()), null, false);
            }
            case HTTP_SSL_DATE_TIME_ERROR:
            case HTTP_SSL_ERROR:
            case HTTP_SSL_NO_VALID_CERT: {
                this.displayErrorDialog(this.getString(2131493277), status.getStatusCode().getValue());
            }
        }
    }
    
    protected boolean hasUpAction() {
        return true;
    }
    
    public void hideMdxMiniPlayer() {
        if (this.mdxFrag != null) {
            this.mdxFrag.hide();
        }
    }
    
    boolean isComingFromBackground(final boolean b) {
        final boolean wasInBackground = ((NetflixApplication)this.getApplication()).wasInBackground(b);
        Log.d("NetflixActivity", "isComingFromBackground, wasinBackground: " + wasInBackground);
        return wasInBackground;
    }
    
    public boolean isDialogFragmentVisible() {
        return this.getDialogFragment() != null;
    }
    
    public boolean isForKids() {
        return false;
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
        this.handler.postDelayed((Runnable)new Runnable() {
            @Override
            public void run() {
                NetflixActivity.this.collapseSlidingPanel();
            }
        }, 250L);
    }
    
    public final void onBackPressed() {
        if (Log.isLoggable("NetflixActivity", 2)) {
            Log.v("NetflixActivity", this.getClass().getSimpleName() + ": back button pressed");
        }
        if (this.slidingPanel != null && this.mdxFrag != null && this.slidingPanel.isExpanded() && this.mdxFrag.isVisible()) {
            Log.v("NetflixActivity", "MDX mini player sliding panel was expanded, collapsing...");
            this.slidingPanel.collapsePane();
        }
        else if (!this.handleBackPressed()) {
            LogUtils.reportUIViewCommand((Context)this, UIViewLogging.UIViewCommandName.backButton, this.getUiScreen(), this.getDataContext());
            super.onBackPressed();
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        boolean shouldExpandMiniPlayer = true;
        super.onCreate(bundle);
        this.setInstanceStateSaved(false);
        if (Log.isLoggable("NetflixActivity", 2)) {
            Log.v("NetflixActivity", "Creating activity: " + this.getClass().getSimpleName() + ", hash: " + this.hashCode());
        }
        if (this.isForKids()) {
            this.setRequestedOrientation(1);
        }
        if (this.shouldShowKidsBackground()) {
            this.getWindow().setBackgroundDrawableResource(2130837705);
        }
        if (bundle == null || !bundle.getBoolean("mini_player_expanded", false)) {
            shouldExpandMiniPlayer = false;
        }
        this.shouldExpandMiniPlayer = shouldExpandMiniPlayer;
        Log.v("NetflixActivity", "Should expand mini player: " + this.shouldExpandMiniPlayer);
        this.registerFinishReceiverWithAutoUnregister("com.netflix.mediaclient.ui.login.ACTION_FINISH_ALL_ACTIVITIES");
        this.registerReceiverWithAutoUnregister(this.expandMdxMiniPlayerReceiver, "com.netflix.mediaclient.service.ACTION_EXPAND_MDX_MINI_PLAYER");
        this.setupServicemanager();
        this.actionBarHeight = this.computeActionBarHeight();
        this.systemActionBar = super.getActionBar();
        if (this.systemActionBar != null) {
            this.netflixActionBar = this.createActionBar(this.systemActionBar);
        }
        this.handler = new Handler();
    }
    
    protected void onCreateOptionsMenu(final Menu menu, final Menu menu2) {
        if (menu2 != null) {
            new DebugMenuItems("NetflixActivity", this).addItems(menu2);
        }
        if (this.showSettingsInMenu()) {
            menu.add(2131493142).setIcon(2130837694).setIntent(SettingsActivity.createStartIntent(this));
        }
        if (this.showAboutInMenu()) {
            this.addAboutTool(menu);
        }
        if (this.showSignOutInMenu()) {
            menu.add(2131493190).setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new MenuItem$OnMenuItemClickListener() {
                public boolean onMenuItemClick(final MenuItem menuItem) {
                    LogoutActivity.showLogoutDialog(NetflixActivity.this);
                    return true;
                }
            });
        }
    }
    
    public final boolean onCreateOptionsMenu(final Menu menu) {
        Log.v("NetflixActivity", "onCreateOptionsMenu");
        this.onCreateOptionsMenu(menu, null);
        return super.onCreateOptionsMenu(menu);
    }
    
    protected void onDestroy() {
        ((NetflixApplication)this.getApplication()).releaseCurrentActivity(this);
        if (Log.isLoggable("NetflixActivity", 2)) {
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
    
    protected void onLoaded(final Status status) {
        if (this.mLoadingStatusCallback != null) {
            this.mLoadingStatusCallback.onDataLoaded(status);
        }
    }
    
    public boolean onMenuItemSelected(final int n, final MenuItem menuItem) {
        Log.v("NetflixActivity", "onMenuItemSelected: " + menuItem.getItemId());
        return (menuItem != null && this.netflixActionBar != null && this.netflixActionBar.handleHomeButtonSelected(menuItem)) || super.onMenuItemSelected(n, menuItem);
    }
    
    protected void onPause() {
        super.onPause();
        final NetflixApplication netflixApplication = (NetflixApplication)this.getApplication();
        NetflixApplication.activityPaused();
        netflixApplication.releaseCurrentActivity(this);
        this.isVisible = false;
        this.handler.removeCallbacks(this.printLoadingStatusRunnable);
        netflixApplication.startActivityTransitionTimer();
    }
    
    public void onPinVerified(final PinDialogVault pinDialogVault) {
        throw new IllegalStateException(String.format("onPinVerified vault: %s", pinDialogVault));
    }
    
    protected void onPostCreate(final Bundle bundle) {
        super.onPostCreate(bundle);
        Log.v("NetflixActivity", this.getClass().getSimpleName() + ": onPostCreate");
    }
    
    protected void onPostResume() {
        super.onPostResume();
        if (this.mdxFrag != null) {
            this.mdxFrag.onResumeFragments();
        }
    }
    
    public void onResponse(final String s) {
        if (Log.isLoggable("NetflixActivity", 3)) {
            Log.d("NetflixActivity", "onResponse: User selected: " + s);
        }
        if (this.mdxFrag != null) {
            this.mdxFrag.sendDialogResponse(s);
        }
    }
    
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
    }
    
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
    
    protected void onStart() {
        super.onStart();
        LogUtils.reportNavigationActionStarted((Context)this, null, this.getUiScreen());
        this.mdxFrag = (MdxMiniPlayerFrag)this.getFragmentManager().findFragmentById(2131165477);
        this.slidingPanel = (SlidingUpPanelLayout)this.findViewById(2131165387);
        if (this.slidingPanel != null) {
            this.slidingPanel.setDragView(this.mdxFrag.getSlidingPanelDragView());
            this.slidingPanel.setPanelHeight(this.getResources().getDimensionPixelSize(2131361870));
            this.slidingPanel.setShadowDrawable(this.getResources().getDrawable(2130837848));
            this.slidingPanel.setPanelSlideListener(this.panelSlideListener);
            if (this.shouldApplyPaddingToSlidingPanel()) {
                final View child = this.slidingPanel.getChildAt(0);
                child.setPadding(child.getPaddingLeft(), this.actionBarHeight, child.getPaddingRight(), child.getPaddingBottom());
            }
        }
    }
    
    protected void onStop() {
        if (this.shouldReportNavigationActionEndedOnStop()) {
            LogUtils.reportNavigationActionEnded((Context)this, this.getUiScreen(), IClientLogging.CompletionReason.success, null);
        }
        synchronized (this.visibleDialogLock) {
            Label_0047: {
                if (this.visibleDialog == null) {
                    break Label_0047;
                }
                try {
                    this.visibleDialog.dismiss();
                    this.visibleDialog = null;
                    // monitorexit(this.visibleDialogLock)
                    super.onStop();
                }
                catch (Throwable t) {
                    Log.e("NetflixActivity", "Failed to dismiss dialog!", t);
                }
            }
        }
    }
    
    public void performUpAction() {
        LogUtils.reportUIViewCommand((Context)this, UIViewLogging.UIViewCommandName.upButton, this.getUiScreen(), this.getDataContext());
        this.startActivity(HomeActivity.createShowIntent(this));
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
    
    public void reportUiModelessViewSessionEnded(final IClientLogging.ModalView modalView, final String s) {
        final ApplicationPerformanceMetricsLogging apmSafely = this.getApmSafely();
        if (apmSafely != null) {
            final boolean portrait = DeviceUtils.isPortrait((Context)this);
            if (Log.isLoggable("NetflixActivity", 3)) {
                Log.d("NetflixActivity", "Report UI modeless view session ended for  " + modalView + " in portrait: " + portrait + ". Dialog id: " + s);
            }
            apmSafely.endUiModelessViewSession(s);
        }
    }
    
    public String reportUiModelessViewSessionStart(final IClientLogging.ModalView modalView) {
        final ApplicationPerformanceMetricsLogging apmSafely = this.getApmSafely();
        if (apmSafely != null && modalView != null) {
            final boolean portrait = DeviceUtils.isPortrait((Context)this);
            final String value = String.valueOf(this.mDialogCount.getAndIncrement());
            if (Log.isLoggable("NetflixActivity", 3)) {
                Log.d("NetflixActivity", "Report UI modeless view session started for  " + modalView + " in portrait: " + portrait + ". Dialog id: " + value);
            }
            apmSafely.startUiModelessViewSession(portrait, modalView, value);
            return value;
        }
        return null;
    }
    
    protected void reportUiViewChanged(final IClientLogging.ModalView modalView) {
        final ApplicationPerformanceMetricsLogging apmSafely = this.getApmSafely();
        if (apmSafely != null && modalView != null) {
            final boolean portrait = DeviceUtils.isPortrait((Context)this);
            if (Log.isLoggable("NetflixActivity", 3)) {
                Log.d("NetflixActivity", "Report UI view change for  " + modalView + " in portrait: " + portrait);
            }
            apmSafely.uiViewChanged(portrait, modalView);
        }
    }
    
    public void runInUiThread(final Runnable runnable) {
        if (runnable == null || this.destroyed()) {
            return;
        }
        this.runOnUiThread(runnable);
    }
    
    public void setLoadingStatusCallback(final LoadingStatusCallback mLoadingStatusCallback) {
        if (!this.isLoadingData() && mLoadingStatusCallback != null) {
            mLoadingStatusCallback.onDataLoaded(CommonStatus.OK);
            return;
        }
        this.mLoadingStatusCallback = mLoadingStatusCallback;
    }
    
    protected void setupServicemanager() {
        this.serviceManager = new ServiceManager(this, new DefaultManagerStatusListener(this.createManagerStatusListener(), this.isComingFromBackground(true)));
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
    
    protected boolean shouldFinishOnManagerError() {
        return true;
    }
    
    protected boolean shouldReportNavigationActionEndedOnStop() {
        return true;
    }
    
    protected boolean shouldShowKidsBackground() {
        return this.isForKids();
    }
    
    protected boolean showAboutInMenu() {
        return !this.isForKids();
    }
    
    public void showDialog(final DialogFragment dialogFragment) {
        if (dialogFragment == null || this.isDestroyed || this.instanceStateSaved.get()) {
            return;
        }
        synchronized (this.instanceStateSaved) {
            if (this.instanceStateSaved.get()) {
                return;
            }
        }
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
    
    public void showFetchErrorsToast() {
    }
    
    protected void showMDXPostPlayOnResume() {
        boolean b2;
        final boolean b = b2 = false;
        if (ServiceManagerUtils.isMdxAgentAvailable(this.serviceManager)) {
            final IMdxSharedState sharedState = this.getSharedState();
            b2 = b;
            if (sharedState != null) {
                b2 = b;
                if (!TextUtils.isEmpty((CharSequence)sharedState.getPostplayState())) {
                    final WebApiUtils.VideoIds videoIdsPostplay = ((MdxAgent)this.serviceManager.getMdx()).getVideoIdsPostplay();
                    b2 = b;
                    if (videoIdsPostplay != null) {
                        b2 = b;
                        if (videoIdsPostplay.episodeId > 0) {
                            MDXControllerActivity.showMDXController(this, videoIdsPostplay.episodeId, PlayContext.DFLT_MDX_CONTEXT);
                            b2 = true;
                        }
                    }
                }
            }
        }
        if (!b2) {
            MDXControllerActivity.finishMDXController((Context)this);
        }
    }
    
    protected boolean showMdxInMenu() {
        return true;
    }
    
    protected boolean showSettingsInMenu() {
        return !this.isForKids();
    }
    
    protected boolean showSignOutInMenu() {
        return !this.isForKids();
    }
    
    protected void startLaunchActivityIfVisible() {
        if (this.isVisible && !(this instanceof LaunchActivity)) {
            Log.i("NetflixActivity", "Activity is visible, starting launch activity");
            this.startActivity(RelaunchActivity.createStartIntent(this, "startLaunchActivityIfVisible()").addFlags(131072));
            return;
        }
        Log.v("NetflixActivity", "Activity is not visible, skipping launch of new activity");
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
    
    private class DefaultManagerStatusListener implements ManagerStatusListener
    {
        private boolean isFrombackground;
        private final ManagerStatusListener listener;
        
        public DefaultManagerStatusListener(final ManagerStatusListener listener, final boolean isFrombackground) {
            this.isFrombackground = false;
            this.listener = listener;
            this.isFrombackground = isFrombackground;
        }
        
        @Override
        public void onManagerReady(final ServiceManager serviceManager, final Status status) {
            if (Log.isLoggable("NetflixActivity", 3)) {
                Log.d("NetflixActivity", "onManagerReady, status: " + status.getStatusCode());
            }
            NetflixActivity.this.mIsTablet = serviceManager.isTablet();
            if (status.isError()) {
                NetflixActivity.this.startLaunchActivityIfVisible();
            }
            ((NetflixApplication)NetflixActivity.this.getApplication()).refreshLocale(serviceManager.getCurrentAppLocale());
            if (NetflixActivity.this.netflixActionBar != null) {
                NetflixActivity.this.netflixActionBar.onManagerReady();
            }
            if (NetflixActivity.this.mdxFrag != null) {
                NetflixActivity.this.mdxFrag.onManagerReady(serviceManager, status);
                if (NetflixActivity.this.shouldExpandMiniPlayer) {
                    NetflixActivity.this.shouldExpandMiniPlayer = false;
                    NetflixActivity.this.handler.postDelayed((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            NetflixActivity.this.expandMiniPlayerIfVisible();
                        }
                    }, 400L);
                }
            }
            final DialogFragment dialogFragment = NetflixActivity.this.getDialogFragment();
            if (dialogFragment instanceof ManagerStatusListener) {
                ((ManagerStatusListener)dialogFragment).onManagerReady(serviceManager, status);
            }
            NetflixActivity.this.addMdxReceiver();
            NetflixActivity.this.addUserAgentUpdateReceiver();
            if (NetflixActivity.this.showMdxInMenu()) {
                NetflixActivity.this.invalidateOptionsMenu();
            }
            if (this.listener != null) {
                this.listener.onManagerReady(serviceManager, status);
            }
            if (!(NetflixActivity.this instanceof LaunchActivity)) {
                serviceManager.getClientLogging().getApplicationPerformanceMetricsLogging().endUiStartupSession(true, null, serviceManager.getConfiguration().getCurrentPlayerType());
            }
            serviceManager.getClientLogging().setDataContext(NetflixActivity.this.getDataContext());
            NetflixActivity.this.reportUiViewChanged(NetflixActivity.this.getUiScreen());
            if (this.isFrombackground) {
                NetflixActivity.this.showMDXPostPlayOnResume();
            }
            SearchUtils.setTestCell(serviceManager.getConfiguration().getSearchTest());
        }
        
        @Override
        public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
            if (Log.isLoggable("NetflixActivity", 3)) {
                Log.d("NetflixActivity", "onManagerUnavailable, status: " + status.getStatusCode());
            }
            if (NetflixActivity.this.mdxFrag != null) {
                NetflixActivity.this.mdxFrag.onManagerUnavailable(serviceManager, status);
            }
            final DialogFragment dialogFragment = NetflixActivity.this.getDialogFragment();
            if (dialogFragment instanceof ManagerStatusListener) {
                ((ManagerStatusListener)dialogFragment).onManagerUnavailable(serviceManager, status);
            }
            if (this.listener != null) {
                this.listener.onManagerUnavailable(serviceManager, status);
            }
            NetflixActivity.this.startLaunchActivityIfVisible();
            if (NetflixActivity.this.shouldFinishOnManagerError()) {
                if (Log.isLoggable("NetflixActivity", 3)) {
                    Log.d("NetflixActivity", NetflixActivity.this.getClass().getSimpleName() + ": Finishing activity because manager error occured...");
                }
                NetflixActivity.this.finish();
            }
        }
    }
}
