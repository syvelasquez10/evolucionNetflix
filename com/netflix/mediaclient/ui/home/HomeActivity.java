// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.ui.offline.TutorialHelper;
import com.netflix.android.tooltips.Tooltip;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import android.view.View;
import java.io.Serializable;
import com.netflix.mediaclient.android.activity.NetflixActivity$ServiceManagerRunnable;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.service.webclient.model.leafs.UmaAlert;
import com.netflix.mediaclient.ui.mdx.ICastPlayerFrag;
import com.netflix.mediaclient.ui.search.SearchMenu;
import com.netflix.mediaclient.ui.mdx.CastMenu;
import android.view.Menu;
import com.netflix.mediaclient.util.Coppola2Utils;
import java.util.Collection;
import android.os.SystemClock;
import com.netflix.mediaclient.service.logging.perf.Events;
import android.os.Bundle;
import android.view.KeyEvent;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;
import com.netflix.mediaclient.ui.lolomo.GalleryGenresLoMoFrag;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList$GenreType;
import java.util.Map;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.android.widget.NetflixActionBar$LogoType;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.android.app.CommonStatus;
import android.app.Fragment;
import android.os.Parcelable;
import android.app.Activity;
import android.support.v4.widget.DrawerLayout$DrawerListener;
import com.netflix.mediaclient.service.logging.perf.InteractiveTracker$InteractiveListener;
import android.widget.Toast;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Context;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import android.os.Handler;
import com.netflix.mediaclient.util.IrisUtils$NotificationsListStatus;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import android.content.BroadcastReceiver;
import android.support.v4.widget.DrawerLayout;
import android.content.Intent;
import java.util.LinkedList;
import com.netflix.mediaclient.service.logging.perf.InteractiveTracker$TTRTracker;
import com.netflix.mediaclient.ui.push_notify.SocialOptInDialogFrag$OptInResponseHandler;
import com.netflix.mediaclient.ui.offline.TutorialHelper$Tutorialable;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecyclerProvider;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;

public class HomeActivity extends FragmentHostActivity implements ObjectRecycler$ViewRecyclerProvider, TutorialHelper$Tutorialable, SocialOptInDialogFrag$OptInResponseHandler
{
    private static final long ACTIVITY_RESUME_TIMEOUT_MS = 28800000L;
    private static final long DELAY_BEFORE_NOTIFICATIONS_READ = 3000L;
    private static final String EXTRA_BACK_STACK_INTENTS = "extra_back_stack_intents";
    private static final String EXTRA_GENRE_ID = "genre_id";
    private static final String EXTRA_GENRE_PARCEL = "genre_parcel";
    private static final String EXTRA_NOTIFICATION_LIST_STATUS = "extra_notification_list_status";
    public static final String REFRESH_HOME_LOLOMO = "com.netflix.mediaclient.intent.action.REFRESH_HOME_LOLOMO";
    static final int REQUEST_RESOLVE_ERROR = 1001;
    private static final String TAG = "HomeActivity";
    private InteractiveTracker$TTRTracker TTRTracker;
    private boolean bWasHamburgerClicked;
    private final LinkedList<Intent> backStackIntents;
    private DrawerLayout drawerLayout;
    private final BroadcastReceiver expandMdxMiniPlayerReceiver;
    private GenreList genre;
    private String genreId;
    private boolean hasCheckedOnRampEligibility;
    private boolean isFirstLaunch;
    private DialogManager mDialogManager;
    private long mStartedTimeMs;
    private final BroadcastReceiver mUserMessageUpdatedReceiver;
    private ServiceManager manager;
    private final ManagerStatusListener managerStatusListener;
    private IrisUtils$NotificationsListStatus notificationsListStatus;
    private final BroadcastReceiver notificationsListUpdateReceiver;
    private long pauseTimeMs;
    private Runnable readRunnable;
    private Handler readRunnableHandler;
    private final BroadcastReceiver refreshHomeReceiver;
    private SlidingMenuAdapter slidingMenuAdapter;
    private ObjectRecycler$ViewRecycler viewRecycler;
    
    public HomeActivity() {
        this.backStackIntents = new LinkedList<Intent>();
        this.notificationsListStatus = IrisUtils$NotificationsListStatus.NO_MESSAGES;
        this.mUserMessageUpdatedReceiver = new HomeActivity$1(this);
        this.expandMdxMiniPlayerReceiver = new HomeActivity$2(this);
        this.managerStatusListener = new HomeActivity$6(this);
        this.refreshHomeReceiver = new HomeActivity$7(this);
        this.notificationsListUpdateReceiver = new HomeActivity$8(this);
    }
    
    private void cancelMarkingNotificationsAsRead() {
        if (this.notificationsListStatus == IrisUtils$NotificationsListStatus.HAS_UNREAD_MESSAGES && this.readRunnableHandler != null && this.readRunnable != null) {
            this.readRunnableHandler.removeCallbacks(this.readRunnable);
        }
    }
    
    private void clearAllStateAndRefresh() {
        this.getServiceManager().getBrowse().flushCaches();
        this.getPrimaryFrag().refresh();
        this.slidingMenuAdapter.refresh();
        this.refreshSocialNotificationsStateIfNeeded();
    }
    
    public static Intent createShowIntent(final NetflixActivity netflixActivity) {
        return new Intent((Context)netflixActivity, (Class)getHomeActivityClass()).addFlags(67108864);
    }
    
    public static Intent createStartIntent(final NetflixActivity netflixActivity) {
        return createShowIntent(netflixActivity).putExtra("genre_id", "lolomo");
    }
    
    private static Class<?> getHomeActivityClass() {
        return HomeActivity.class;
    }
    
    private boolean handleNewIntent(final Intent intent) {
        final boolean b = true;
        if (StringUtils.isEmpty(this.genreId) && this.backStackIntents.size() == 0 && !intent.hasExtra("genre_id")) {
            intent.putExtra("genre_id", "lolomo");
        }
        if (intent.getBooleanExtra("expandCastPlayer", false)) {
            this.notifyCastPlayerShown(true);
        }
        final String stringExtra = intent.getStringExtra("genre_id");
        if (StringUtils.isEmpty(stringExtra)) {
            Log.d("HomeActivity", "No new genre ID to show");
            return false;
        }
        if (Log.isLoggable()) {
            Log.v("HomeActivity", "Curr genre: " + this.genreId + ", new genre: " + stringExtra);
        }
        boolean b2;
        if (stringExtra.equals(this.genreId)) {
            Log.i("HomeActivity", "Asked to show genre that we're already showing - skipping: " + this.genreId);
            b2 = false;
        }
        else {
            b2 = b;
            if ("lolomo".equals(this.genreId)) {
                Log.v("HomeActivity", "Adding genre to back stack: " + this.genreId);
                this.backStackIntents.add(this.getIntent());
                b2 = b;
            }
        }
        if (this.slidingMenuAdapter instanceof StandardSlidingMenu) {
            ((StandardSlidingMenu)this.slidingMenuAdapter).closeDrawers();
        }
        if ("lolomo".equals(stringExtra)) {
            this.backStackIntents.clear();
        }
        this.genreId = stringExtra;
        this.genre = (GenreList)intent.getParcelableExtra("genre_parcel");
        this.setIntent(intent);
        this.reportUiViewChanged(this.getCurrentViewType());
        return b2;
    }
    
    private void leaveExperienceBreadcrumb() {
        if (this.isFirstLaunch) {
            final String string = "experience=" + String.valueOf(BrowseExperience.get());
            this.manager.getClientLogging().getBreadcrumbLogging().leaveBreadcrumb(string);
            if (Log.isLoggable()) {
                Log.v("HomeActivity", "Left breadcrumb: " + string);
            }
        }
    }
    
    private void lockSlidingDrawer() {
        this.drawerLayout.setDrawerLockMode(1);
    }
    
    private void onResumeAfterTimeout() {
        Toast.makeText((Context)this, 2131296773, 1).show();
        this.clearAllStateAndRefresh();
    }
    
    private void refreshSocialNotificationsStateIfNeeded() {
        if (this.manager != null && this.manager.getBrowse() != null) {
            this.manager.getBrowse().refreshIrisNotifications(true);
        }
    }
    
    private void registerReceivers() {
        this.registerReceiverWithAutoUnregister(this.refreshHomeReceiver, "com.netflix.mediaclient.intent.action.REFRESH_HOME_LOLOMO");
        this.registerReceiverWithAutoUnregister(this.expandMdxMiniPlayerReceiver, "com.netflix.mediaclient.service.ACTION_EXPAND_HOME_CAST_PLAYER");
        if (this.slidingMenuAdapter.canLoadNotifications()) {
            this.registerReceiverLocallyWithAutoUnregister(this.notificationsListUpdateReceiver, "com.netflix.mediaclient.intent.action.BA_IRIS_NOTIFICATION_LIST_UPDATED");
        }
    }
    
    private void setupTTRTracking() {
        Log.i("HomeActivity", "setupTTRTracking");
        if (this.TTRTracker == null) {
            this.TTRTracker = new InteractiveTracker$TTRTracker(new HomeActivity$3(this));
            NetflixActivity.getImageLoader((Context)this).setTTRTracker(this.TTRTracker);
        }
    }
    
    private void setupViews() {
        (this.drawerLayout = (DrawerLayout)this.findViewById(2131821032)).setDrawerListener(new HomeActivity$4(this));
        this.unlockSlidingDrawerIfPossible();
        this.slidingMenuAdapter = BrowseExperience.get().createSlidingMenuAdapter(this, this.drawerLayout);
        if (Log.isLoggable()) {
            Log.v("HomeActivity", "Created sliding menu adapter of type: " + this.slidingMenuAdapter.getClass());
        }
        this.drawerLayout.setFocusable(false);
        this.drawerLayout.setScrimColor(this.getResources().getColor(2131755121));
        this.updateActionBar();
        this.updateSlidingDrawer();
    }
    
    private void showDataSaverNotif() {
        DataSaverNotifier.showNotificationIfNecessary(this);
    }
    
    private void showDialogsIfApplicable() {
        if (this.getGenre() == null && this.hasCheckedOnRampEligibility && !this.mDialogManager.displayDialogsIfNeeded()) {
            this.showDataSaverNotif();
        }
    }
    
    public static void showGenreList(final NetflixActivity netflixActivity, final GenreList list) {
        netflixActivity.startActivity(new Intent((Context)netflixActivity, (Class)getHomeActivityClass()).addFlags(67108864).putExtra("genre_id", list.getId()).putExtra("genre_parcel", (Parcelable)list));
    }
    
    private void showNewFrag() {
        this.updateActionBar();
        this.updateSlidingDrawer();
        this.setPrimaryFrag(this.createPrimaryFrag());
        this.getFragmentManager().beginTransaction().replace(2131820920, (Fragment)this.getPrimaryFrag(), "primary").setTransition(4099).commit();
        this.getFragmentManager().executePendingTransactions();
        this.getPrimaryFrag().onManagerReady(this.manager, (Status)CommonStatus.OK);
    }
    
    @SuppressLint({ "RtlHardcoded" })
    private void toggleDrawer() {
        if (this.drawerLayout.isDrawerOpen(8388611)) {
            UIViewLogUtils.reportUIViewCommand((Context)this, UIViewLogging$UIViewCommandName.slidingMenuClosed, this.getUiScreen(), this.getDataContext());
            this.drawerLayout.closeDrawers();
            return;
        }
        UIViewLogUtils.reportUIViewCommand((Context)this, UIViewLogging$UIViewCommandName.slidingMenuOpened, this.getUiScreen(), this.getDataContext());
        this.drawerLayout.openDrawer(8388611);
        this.bWasHamburgerClicked = true;
    }
    
    private void unlockSlidingDrawerIfPossible() {
        this.drawerLayout.setDrawerLockMode(0);
    }
    
    private void updateActionBar() {
        String title;
        if (this.genre == null) {
            title = null;
        }
        else {
            title = this.genre.getTitle();
        }
        Log.v("HomeActivity", "Updating action bar, genre: " + title);
        final NetflixActionBar netflixActionBar = this.getNetflixActionBar();
        if (netflixActionBar != null) {
            netflixActionBar.setTitle(title);
            NetflixActionBar$LogoType logoType;
            if (StringUtils.isEmpty(title)) {
                logoType = NetflixActionBar$LogoType.FULL_SIZE;
            }
            else {
                logoType = NetflixActionBar$LogoType.GONE;
            }
            netflixActionBar.setLogoType(logoType);
            netflixActionBar.setSandwichIcon(this.notificationsListStatus == IrisUtils$NotificationsListStatus.HAS_UNREAD_MESSAGES);
        }
    }
    
    private void updateSlidingDrawer() {
        this.slidingMenuAdapter.setSelectedGenre(this.genre);
    }
    
    protected boolean canApplyBrowseExperience() {
        return true;
    }
    
    public boolean canShowSnackBar() {
        return true;
    }
    
    protected ManagerStatusListener createManagerStatusListener() {
        return this.managerStatusListener;
    }
    
    @Override
    protected NetflixFrag createPrimaryFrag() {
        PerformanceProfiler.getInstance().startSession(Sessions.LOLOMO_LOAD, null);
        if (!"lolomo".equals(this.genreId) && ((BrowseExperience.useKidsGenresLoMo() && !"1647397".equalsIgnoreCase(this.genreId)) || this.genre.getGenreType() == GenreList$GenreType.GALLERY)) {
            return (NetflixFrag)GalleryGenresLoMoFrag.create(this.genreId, this.genre);
        }
        return (NetflixFrag)LoLoMoFrag.create(this.genreId, this.genre);
    }
    
    public boolean dispatchKeyEvent(final KeyEvent keyEvent) {
        return this.getPrimaryFrag().dispatchKeyEvent(keyEvent) || super.dispatchKeyEvent(keyEvent);
    }
    
    public int getActionBarParentViewId() {
        return 2131821022;
    }
    
    @Override
    protected int getContentLayoutId() {
        return 2130903156;
    }
    
    public IClientLogging$ModalView getCurrentViewType() {
        if (StringUtils.isEmpty(this.genreId)) {
            return IClientLogging$ModalView.homeScreen;
        }
        if ("lolomo".equals(this.genreId)) {
            return IClientLogging$ModalView.homeScreen;
        }
        return IClientLogging$ModalView.browseTitles;
    }
    
    public GenreList getGenre() {
        return this.genre;
    }
    
    public String getGenreId() {
        return this.genreId;
    }
    
    public LoLoMoFrag getPrimaryFrag() {
        return (LoLoMoFrag)super.getPrimaryFrag();
    }
    
    public IClientLogging$ModalView getUiScreen() {
        return IClientLogging$ModalView.homeScreen;
    }
    
    @Override
    public ObjectRecycler$ViewRecycler getViewRecycler() {
        return this.viewRecycler;
    }
    
    @SuppressLint({ "RtlHardcoded" })
    protected boolean handleBackPressed() {
        if (this.drawerLayout != null && this.drawerLayout.isDrawerOpen(8388611)) {
            Log.v("HomeActivity", "Sliding drawer was open, closing...");
            this.drawerLayout.closeDrawer(8388611);
            return true;
        }
        Log.v("HomeActivity", "Back pressed, backStack size: " + this.backStackIntents.size());
        if (this.backStackIntents.size() > 0) {
            this.onNewIntent(this.backStackIntents.removeLast());
            return true;
        }
        Log.v("HomeActivity", "No more items in back stack, finishing...");
        return false;
    }
    
    public void onAccept() {
        if (this.mDialogManager != null) {
            this.mDialogManager.onAccept();
        }
        this.showDataSaverNotif();
    }
    
    public void onCreate(final Bundle bundle) {
        PerformanceProfiler.getInstance().logEvent(Events.HOME_ACTIVITY_CREATED, null);
        this.isFirstLaunch = (bundle == null);
        this.mStartedTimeMs = SystemClock.elapsedRealtime();
        if (bundle != null) {
            this.backStackIntents.addAll((Collection<? extends Intent>)bundle.getSerializable("extra_back_stack_intents"));
            this.notificationsListStatus = (IrisUtils$NotificationsListStatus)bundle.getSerializable("extra_notification_list_status");
        }
        this.handleNewIntent(this.getIntent());
        this.viewRecycler = new ObjectRecycler$ViewRecycler();
        super.onCreate(bundle);
        this.setupViews();
        this.registerReceivers();
        this.pauseTimeMs = SystemClock.elapsedRealtime();
        Coppola2Utils.forceToPortraitIfNeeded((Activity)this);
    }
    
    protected void onCreateOptionsMenu(final Menu menu, final Menu menu2) {
        final boolean b = true;
        final ICastPlayerFrag castPlayerFrag = this.getCastPlayerFrag();
        final boolean b2 = BrowseExperience.showKidsExperience() || BrowseExperience.isLightTheme();
        if (castPlayerFrag != null) {
            CastMenu.addSelectPlayTarget((NetflixActivity)this, menu, b2);
        }
        else {
            Log.e("HomeActivity", "onCreateOptionsMenu got null CastPlayerHelper");
        }
        UmaAlert userMessageAlert;
        if (this.getServiceManager() == null || !this.getServiceManager().isReady()) {
            userMessageAlert = null;
        }
        else {
            userMessageAlert = this.getServiceManager().getUserMessageAlert();
        }
        int n;
        if (userMessageAlert != null && userMessageAlert.blocking()) {
            n = 1;
        }
        else {
            n = 0;
        }
        SearchMenu.addSearchNavigation((NetflixActivity)this, menu, b2).setVisible(n == 0 && b);
        super.onCreateOptionsMenu(menu, menu2);
    }
    
    public void onDecline() {
        if (this.mDialogManager != null) {
            this.mDialogManager.onDecline();
        }
        this.showDataSaverNotif();
    }
    
    protected void onDestroy() {
        if (this.manager != null && this.manager.isReady()) {
            NetflixActivity.getImageLoader((Context)this).setTTRTracker((InteractiveTracker$TTRTracker)null);
        }
        super.onDestroy();
    }
    
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        Log.d("HomeActivity", "onNewIntent: ", intent);
        if (this.handleNewIntent(intent)) {
            this.showNewFrag();
        }
    }
    
    protected void onPause() {
        super.onPause();
        if (Log.isLoggable()) {
            Log.d("HomeActivity", "onResumedAfterTimeout() will fire if activity not resumed within: 28800 seconds");
        }
        this.pauseTimeMs = SystemClock.elapsedRealtime();
        this.cancelMarkingNotificationsAsRead();
        this.slidingMenuAdapter.onActivityPause(this);
        LocalBroadcastManager.getInstance((Context)this).unregisterReceiver(this.mUserMessageUpdatedReceiver);
    }
    
    protected void onResume() {
        super.onResume();
        this.slidingMenuAdapter.onActivityResume(this);
        final long n = SystemClock.elapsedRealtime() - this.pauseTimeMs;
        if (n > 28800000L) {
            Log.d("HomeActivity", "Activity resume timeout reached");
            this.onResumeAfterTimeout();
        }
        else if (Log.isLoggable()) {
            Log.d("HomeActivity", "Activity resume timeout NOT reached, elapsed ms: " + n);
        }
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("RefreshUserMessageRequest.ACTION_UMA_MESSAGE_UPDATED");
        intentFilter.addAction("RefreshUserMessageRequest.ACTION_UMA_MESSAGE_CONSUMED");
        LocalBroadcastManager.getInstance((Context)this).registerReceiver(this.mUserMessageUpdatedReceiver, intentFilter);
        this.invalidateOptionsMenu();
        this.runWhenManagerIsReady(new HomeActivity$5(this));
    }
    
    protected void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable("extra_back_stack_intents", (Serializable)this.backStackIntents);
        bundle.putSerializable("extra_notification_list_status", (Serializable)this.notificationsListStatus);
    }
    
    protected void onSlidingPanelCollapsed(final View view) {
        this.unlockSlidingDrawerIfPossible();
    }
    
    protected void onSlidingPanelExpanded(final View view) {
        this.lockSlidingDrawer();
    }
    
    public void performUpAction() {
        this.toggleDrawer();
    }
    
    protected boolean requiresDownloadButtonListener() {
        return true;
    }
    
    public Tooltip setupTutorial(final UserProfile userProfile) {
        return TutorialHelper.buildMyDownloadTutorial(this.getNetflixActionBar().getHomeView(), (Activity)this, userProfile);
    }
    
    public boolean shouldApplyPaddingToSlidingPanel() {
        return false;
    }
    
    protected boolean shouldSetIntentOnNewIntent() {
        return false;
    }
    
    protected boolean showNoNetworkOverlayIfNeeded() {
        return true;
    }
    
    protected void showProfileToast() {
        if (NetflixApplication.isDebugToastEnabled() && this.getServiceManager().getCurrentProfile() != null) {
            Toast.makeText((Context)this, (CharSequence)String.format("DEBUG: Profile %s", this.getServiceManager().getCurrentProfile().getProfileName()), 1).show();
        }
    }
}
