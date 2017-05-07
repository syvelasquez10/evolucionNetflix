// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.ui.RelaunchActivity;
import com.netflix.mediaclient.ui.LaunchActivity;
import com.netflix.mediaclient.util.WebApiUtils$VideoIds;
import com.netflix.mediaclient.ui.player.MDXControllerActivity;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.service.mdx.MdxAgent;
import android.text.TextUtils;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.content.IntentFilter;
import com.netflix.mediaclient.ui.home.HomeActivity;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import android.view.View;
import com.netflix.mediaclient.ui.pin.PinDialogVault;
import android.view.MenuItem;
import com.netflix.mediaclient.util.DeviceUtils;
import java.util.Iterator;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.ui.settings.SettingsActivity;
import com.netflix.mediaclient.ui.common.DebugMenuItems;
import com.netflix.mediaclient.util.ViewUtils;
import android.os.Bundle;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.ui.ServiceErrorsHandler;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import android.app.Activity;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import com.netflix.mediaclient.ui.login.LogoutActivity;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.IMdxSharedState;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.ui.details.EpisodeRowView$EpisodeRowListener;
import android.app.DialogFragment;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import java.io.Serializable;
import com.netflix.mediaclient.android.widget.AlertDialogFactory;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;
import com.netflix.mediaclient.android.widget.UpdateDialog;
import com.netflix.mediaclient.android.widget.UpdateDialog$Builder;
import android.app.AlertDialog;
import android.app.AlertDialog$Builder;
import android.view.MotionEvent;
import com.netflix.mediaclient.util.MdxUtils;
import android.view.KeyEvent;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.ui.kids.NetflixKidsActionBar;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import android.content.Intent;
import android.content.Context;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import com.netflix.mediaclient.ui.mdx.MdxReceiver;
import com.netflix.mediaclient.Log;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.Menu;
import android.net.Uri;
import com.netflix.mediaclient.StatusCode;
import java.util.HashSet;
import android.app.Dialog;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.sothree.slidinguppanel.SlidingUpPanelLayout$PanelSlideListener;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag;
import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicBoolean;
import android.os.Handler;
import android.content.BroadcastReceiver;
import java.util.Set;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.ui.pin.PinVerifier$PinVerificationCallback;
import com.netflix.mediaclient.ui.mdx.ShowMessageDialogFrag$MessageResponseProvider;
import com.netflix.mediaclient.ui.details.EpisodeRowView$EpisodeRowListenerProvider;
import com.netflix.mediaclient.android.app.LoadingStatus;
import android.support.v7.app.ActionBarActivity;

@SuppressLint({ "DefaultLocale" })
public abstract class NetflixActivity extends ActionBarActivity implements LoadingStatus, EpisodeRowView$EpisodeRowListenerProvider, ShowMessageDialogFrag$MessageResponseProvider, PinVerifier$PinVerificationCallback
{
    private static final long ACTION_BAR_VISIBILITY_CHECK_DELAY_MS = 1000L;
    public static final String ACTION_DISPLAY_ERROR = "com.netflix.mediaclient.ui.error.ACTION_DISPLAY_ERROR";
    private static final String ACTION_FINISH_ALL_ACTIVITIES = "com.netflix.mediaclient.ui.login.ACTION_FINISH_ALL_ACTIVITIES";
    public static final long EXPAND_MINI_PLAYER_DELAY_MS = 400L;
    public static final String EXTRA_PARAM_MESSAGE_ID = "message_id";
    public static final String EXTRA_PARAM_STATUS = "status";
    public static final String EXTRA_PARAM_URL = "url";
    private static final String EXTRA_SHOULD_EXPAND_MINI_PLAYER = "mini_player_expanded";
    public static final String FRAG_DIALOG_TAG = "frag_dialog";
    private static final String INSTANCE_STATE_SAVED_TAG = "NetflixActivity_instanceState";
    public static final String INTENT_CATEGORY_UI = "LocalIntentNflxUi";
    private static final boolean PRINT_LOADING_STATUS = false;
    private static final String TAG = "NetflixActivity";
    private int actionBarHeight;
    private final Set<BroadcastReceiver> autoUnregisterLocalReceivers;
    private final Set<BroadcastReceiver> autoUnregisterReceivers;
    private final BroadcastReceiver autokillReceiver;
    private final BroadcastReceiver displayErrorReceiver;
    private final BroadcastReceiver expandMdxMiniPlayerReceiver;
    protected Handler handler;
    protected AtomicBoolean instanceStateSaved;
    private boolean isDestroyed;
    private boolean isVisible;
    protected AtomicLong mDialogCount;
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
        this.instanceStateSaved = new AtomicBoolean(false);
        this.visibleDialogLock = new Object();
        this.mDialogCount = new AtomicLong(1L);
        this.mIsTablet = false;
        this.autokillReceiver = new NetflixActivity$4(this);
        this.expandMdxMiniPlayerReceiver = new NetflixActivity$5(this);
        this.printLoadingStatusRunnable = new NetflixActivity$6(this);
        this.userAgentUpdateReceiver = new NetflixActivity$8(this);
        this.panelSlideListener = new NetflixActivity$9(this);
        this.updateActionBarVisibilityRunnable = new NetflixActivity$10(this);
        this.displayErrorReceiver = new NetflixActivity$13(this);
    }
    
    private void addAboutMenu(final Menu menu) {
        menu.add(2131492995).setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new NetflixActivity$3(this));
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
    
    public static void finishAllActivities(final Context context) {
        context.sendBroadcast(new Intent("com.netflix.mediaclient.ui.login.ACTION_FINISH_ALL_ACTIVITIES"));
    }
    
    public static ImageLoader getImageLoader(final Context context) {
        return ((NetflixActivity)context).serviceManager.getImageLoader();
    }
    
    private String getMessage(final int n, final StatusCode statusCode) {
        String string;
        if (n == Integer.MAX_VALUE) {
            string = this.getString(2131493356);
        }
        else {
            String s;
            if (statusCode != null) {
                s = this.getString(n, new Object[] { statusCode.getValue() });
            }
            else {
                s = this.getString(n);
            }
            string = s;
            if (s == null) {
                return this.getString(2131493356);
            }
        }
        return string;
    }
    
    private Uri getUri(final String s) {
        if (s == null) {
            return null;
        }
        return Uri.parse(s);
    }
    
    private void onFromBackground() {
        this.showMDXPostPlayOnResume();
    }
    
    private void postActionBarUpdate() {
        this.handler.removeCallbacks(this.updateActionBarVisibilityRunnable);
        this.handler.postDelayed(this.updateActionBarVisibilityRunnable, 1000L);
    }
    
    private void setInstanceStateSaved(final boolean b) {
        if (Log.isLoggable("NetflixActivity", 2)) {
            Log.v("NetflixActivity_instanceState", this.getClass().getSimpleName() + " instanceStateSaved: " + b);
        }
        synchronized (this.instanceStateSaved) {
            this.instanceStateSaved.set(b);
        }
    }
    
    protected NetflixActionBar createActionBar() {
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
        if (this.visibleDialog != null) {
            this.visibleDialog.dismiss();
        }
        final Dialog visibleDialog;
        visibleDialog.show();
        this.visibleDialog = visibleDialog;
    }
    // monitorexit(o)
    
    protected void displayErrorDialog(final String s, final int n) {
        this.displayUserAgentDialog(String.format("%s ( %d )", s, n), new NetflixActivity$12(this), true);
    }
    
    protected void displayUserAgentDialog(final String s, Runnable visibleDialogLock, final boolean b) {
        final UpdateDialog$Builder dialog = AlertDialogFactory.createDialog((Context)this, this.handler, new AlertDialogFactory$AlertDialogDescriptor(null, s, this.getString(17039370), visibleDialogLock));
        if (this.destroyed()) {
            return;
        }
        visibleDialogLock = (Runnable)this.visibleDialogLock;
        // monitorenter(visibleDialogLock)
        Label_0098: {
            if (!b) {
                break Label_0098;
            }
            while (true) {
                try {
                    if (Log.isLoggable("NetflixActivity", 3)) {
                        Log.d("NetflixActivity", "displayUserAgentDialog " + s + " isCritical");
                    }
                    this.displayDialog(dialog);
                    return;
                    while (true) {
                        this.displayDialog(dialog);
                        return;
                        Log.d("NetflixActivity", "displayUserAgentDialog " + s);
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
            if (Log.isLoggable("NetflixActivity", 2)) {
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
                if (Log.isLoggable("NetflixActivity", 2)) {
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
    
    @Override
    public EpisodeRowView$EpisodeRowListener getEpisodeRowListener() {
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
    
    @SuppressLint({ "DefaultLocale" })
    protected String handleUserAgentErrors(final Activity activity, final Status status) {
        String message = status.getMessage();
        if (message == null) {
            message = "";
        }
        switch (NetflixActivity$14.$SwitchMap$com$netflix$mediaclient$StatusCode[status.getStatusCode().ordinal()]) {
            default: {
                final String string = this.getString(2131493234);
                this.displayErrorDialog(this.getString(2131493234), status.getStatusCode().getValue());
                return string;
            }
            case 1: {
                String format = message;
                if (message.isEmpty()) {
                    format = String.format("%s ( %d )", this.getString(2131493231), status.getStatusCode().getValue());
                }
                this.displayUserAgentDialog(format, null, false);
                return format;
            }
            case 2:
            case 3: {
                final String format2 = String.format("%s ( %d )", this.getString(2131493238), status.getStatusCode().getValue());
                this.displayUserAgentDialog(format2, new NetflixActivity$11(this), true);
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
                final String format3 = String.format("%s ( %d )", this.getString(2131493231), status.getStatusCode().getValue());
                this.displayUserAgentDialog(format3, null, false);
                return format3;
            }
            case 13:
            case 14:
            case 15: {
                final String string2 = this.getString(2131493233);
                this.displayErrorDialog(string2, status.getStatusCode().getValue());
                return string2;
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
    
    protected void initToolbar() {
        this.netflixActionBar = this.createActionBar();
    }
    
    boolean isComingFromBackground(final boolean b) {
        final boolean wasInBackground = this.getNetflixApplication().wasInBackground(b);
        if (Log.isLoggable("NetflixActivity", 3)) {
            Log.d("NetflixActivity", "isComingFromBackground, wasinBackground: " + wasInBackground);
        }
        return wasInBackground;
    }
    
    public boolean isDialogFragmentVisible() {
        return this.getDialogFragment() != null;
    }
    
    public boolean isForKids() {
        return false;
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
        this.handler.postDelayed((Runnable)new NetflixActivity$7(this), 250L);
    }
    
    @Override
    public final void onBackPressed() {
        if (Log.isLoggable("NetflixActivity", 2)) {
            Log.v("NetflixActivity", this.getClass().getSimpleName() + ": back button pressed");
        }
        if (this.slidingPanel != null && this.mdxFrag != null && this.slidingPanel.isExpanded() && this.mdxFrag.isVisible()) {
            Log.v("NetflixActivity", "MDX mini player sliding panel was expanded, collapsing...");
            this.slidingPanel.collapsePane();
        }
        else if (!this.handleBackPressed()) {
            UIViewLogUtils.reportUIViewCommand((Context)this, UIViewLogging$UIViewCommandName.backButton, this.getUiScreen(), this.getDataContext());
            super.onBackPressed();
        }
    }
    
    @Override
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
            this.getWindow().setBackgroundDrawableResource(2130837703);
        }
        if (bundle == null || !bundle.getBoolean("mini_player_expanded", false)) {
            shouldExpandMiniPlayer = false;
        }
        this.shouldExpandMiniPlayer = shouldExpandMiniPlayer;
        if (Log.isLoggable("NetflixActivity", 2)) {
            Log.v("NetflixActivity", "Should expand mini player: " + this.shouldExpandMiniPlayer);
        }
        this.registerFinishReceiverWithAutoUnregister("com.netflix.mediaclient.ui.login.ACTION_FINISH_ALL_ACTIVITIES");
        this.registerReceiverWithAutoUnregister(this.expandMdxMiniPlayerReceiver, "com.netflix.mediaclient.service.ACTION_EXPAND_MDX_MINI_PLAYER");
        this.registerReceiverLocallyWithAutoUnregister(this.displayErrorReceiver, "com.netflix.mediaclient.ui.error.ACTION_DISPLAY_ERROR");
        this.actionBarHeight = (int)ViewUtils.getDefaultActionBarHeight((Context)this);
        this.setupServicemanager();
        this.handler = new Handler();
    }
    
    protected void onCreateOptionsMenu(final Menu menu, final Menu menu2) {
        if (menu2 != null) {
            new DebugMenuItems("NetflixActivity", this).addItems(menu2);
        }
        if (this.showSettingsInMenu()) {
            menu.add(2131493116).setIcon(2130837691).setIntent(SettingsActivity.createStartIntent(this));
        }
        if (this.showAboutInMenu()) {
            this.addAboutMenu(menu);
        }
        if (this.showSignOutInMenu()) {
            menu.add(2131493157).setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new NetflixActivity$2(this));
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
    
    protected void onLoaded(final Status status) {
        if (this.mLoadingStatusCallback != null) {
            this.mLoadingStatusCallback.onDataLoaded(status);
        }
    }
    
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        if (Log.isLoggable("NetflixActivity", 2)) {
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
    public void onPinVerified(final PinDialogVault pinDialogVault) {
        throw new IllegalStateException(String.format("onPinVerified vault: %s", pinDialogVault));
    }
    
    protected void onPostCreate(final Bundle bundle) {
        super.onPostCreate(bundle);
        if (Log.isLoggable("NetflixActivity", 2)) {
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
        if (Log.isLoggable("NetflixActivity", 3)) {
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
        this.mdxFrag = (MdxMiniPlayerFrag)this.getFragmentManager().findFragmentById(2131165478);
        this.slidingPanel = (SlidingUpPanelLayout)this.findViewById(2131165387);
        if (this.slidingPanel != null) {
            this.slidingPanel.setDragView(this.mdxFrag.getSlidingPanelDragView());
            this.slidingPanel.setPanelHeight(this.getResources().getDimensionPixelSize(2131361871));
            this.slidingPanel.setShadowDrawable(this.getResources().getDrawable(2130837854));
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
        UIViewLogUtils.reportUIViewCommand((Context)this, UIViewLogging$UIViewCommandName.upButton, this.getUiScreen(), this.getDataContext());
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
    
    public void reportUiModelessViewSessionEnded(final IClientLogging$ModalView clientLogging$ModalView, final String s) {
        final ApplicationPerformanceMetricsLogging apmSafely = this.getApmSafely();
        if (apmSafely != null) {
            final boolean portrait = DeviceUtils.isPortrait((Context)this);
            if (Log.isLoggable("NetflixActivity", 3)) {
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
            if (Log.isLoggable("NetflixActivity", 3)) {
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
            if (Log.isLoggable("NetflixActivity", 3)) {
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
        return this.isForKids();
    }
    
    protected boolean showAboutInMenu() {
        return !this.isForKids();
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
                Log.e("NetflixActivity", "We failed to display dialog", t);
                continue;
            }
            break;
        }
    }
    
    public void showFetchErrorsToast() {
    }
    
    protected void showMDXPostPlayOnResume() {
        while (true) {
            Label_0078: {
                if (!ServiceManagerUtils.isMdxAgentAvailable(this.serviceManager)) {
                    break Label_0078;
                }
                final IMdxSharedState sharedState = this.getSharedState();
                if (sharedState == null || TextUtils.isEmpty((CharSequence)sharedState.getPostplayState())) {
                    break Label_0078;
                }
                final WebApiUtils$VideoIds videoIdsPostplay = ((MdxAgent)this.serviceManager.getMdx()).getVideoIdsPostplay();
                if (videoIdsPostplay == null || videoIdsPostplay.episodeId <= 0) {
                    break Label_0078;
                }
                MDXControllerActivity.showMDXController(this, videoIdsPostplay.episodeId, PlayContext.DFLT_MDX_CONTEXT);
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
}
