// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import com.netflix.mediaclient.NetflixApplication;
import android.view.View;
import java.io.Serializable;
import com.netflix.mediaclient.ui.search.SearchMenu;
import com.netflix.mediaclient.ui.mdx.MdxMenu;
import android.view.Menu;
import com.netflix.mediaclient.util.Coppola2Utils;
import java.util.Collection;
import android.os.SystemClock;
import com.netflix.mediaclient.service.logging.perf.Events;
import android.os.Bundle;
import android.view.KeyEvent;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;
import com.netflix.mediaclient.ui.lolomo.KidsGenresLoMoFrag;
import java.util.Map;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.ui.kubrick.lolomo.BarkerHomeActionBar;
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
import android.widget.Toast;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Context;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import android.os.Handler;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.util.IrisUtils$NotificationsListStatus;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import android.support.v4.widget.DrawerLayout;
import android.content.Intent;
import java.util.LinkedList;
import com.netflix.mediaclient.ui.push_notify.SocialOptInDialogFrag$OptInResponseHandler;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecyclerProvider;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;

public class HomeActivity extends FragmentHostActivity implements ObjectRecycler$ViewRecyclerProvider, SocialOptInDialogFrag$OptInResponseHandler
{
    private static final long ACTIVITY_RESUME_TIMEOUT_MS = 28800000L;
    private static final long DELAY_BEFORE_NOTIFICATIONS_READ = 3000L;
    private static final String EXTRA_BACK_STACK_INTENTS = "extra_back_stack_intents";
    private static final String EXTRA_GENRE_ID = "genre_id";
    private static final String EXTRA_GENRE_PARCEL = "genre_parcel";
    public static final String REFRESH_HOME_LOLOMO = "com.netflix.mediaclient.intent.action.REFRESH_HOME_LOLOMO";
    static final int REQUEST_RESOLVE_ERROR = 1001;
    private static final String TAG = "HomeActivity";
    private boolean bWasHamburgerClicked;
    private final LinkedList<Intent> backStackIntents;
    private DrawerLayout drawerLayout;
    private GenreList genre;
    private String genreId;
    private boolean isFirstLaunch;
    private DialogManager mDialogManager;
    private long mStartedTimeMs;
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
        this.managerStatusListener = new HomeActivity$2(this);
        this.refreshHomeReceiver = new HomeActivity$3(this);
        this.notificationsListUpdateReceiver = new HomeActivity$4(this);
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
        Toast.makeText((Context)this, 2131231162, 1).show();
        this.clearAllStateAndRefresh();
    }
    
    private void refreshSocialNotificationsStateIfNeeded() {
        if (this.manager != null && this.manager.getBrowse() != null) {
            this.manager.getBrowse().refreshIrisNotifications(true);
        }
    }
    
    private void registerReceivers() {
        this.registerReceiverWithAutoUnregister(this.refreshHomeReceiver, "com.netflix.mediaclient.intent.action.REFRESH_HOME_LOLOMO");
        if (this.slidingMenuAdapter.canLoadNotifications()) {
            this.registerReceiverLocallyWithAutoUnregister(this.notificationsListUpdateReceiver, "com.netflix.mediaclient.intent.action.BA_IRIS_NOTIFICATION_LIST_UPDATED");
        }
    }
    
    private void setupViews() {
        (this.drawerLayout = (DrawerLayout)this.findViewById(2131689839)).setDrawerListener(new HomeActivity$1(this));
        this.unlockSlidingDrawerIfPossible();
        this.slidingMenuAdapter = BrowseExperience.get().createSlidingMenuAdapter(this, this.drawerLayout);
        if (Log.isLoggable()) {
            Log.v("HomeActivity", "Created sliding menu adapter of type: " + this.slidingMenuAdapter.getClass());
        }
        this.drawerLayout.setFocusable(false);
        this.drawerLayout.setScrimColor(this.getResources().getColor(2131624028));
        this.updateActionBar();
        this.updateSlidingDrawer();
    }
    
    private void showDataSaverNotif() {
        DataSaverNotifier.showNotificationIfNecessary(this);
    }
    
    public static void showGenreList(final NetflixActivity netflixActivity, final GenreList list) {
        netflixActivity.startActivity(new Intent((Context)netflixActivity, (Class)getHomeActivityClass()).addFlags(67108864).putExtra("genre_id", list.getId()).putExtra("genre_parcel", (Parcelable)list));
    }
    
    private void showNewFrag() {
        this.updateActionBar();
        this.updateSlidingDrawer();
        this.setPrimaryFrag(this.createPrimaryFrag());
        this.getFragmentManager().beginTransaction().replace(2131689744, (Fragment)this.getPrimaryFrag(), "primary").setTransition(4099).commit();
        this.getFragmentManager().executePendingTransactions();
        this.getPrimaryFrag().onManagerReady(this.manager, CommonStatus.OK);
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
            netflixActionBar.setSandwichIcon(false);
        }
    }
    
    private void updateSlidingDrawer() {
        this.slidingMenuAdapter.setSelectedGenre(this.genre);
    }
    
    @Override
    protected boolean canApplyBrowseExperience() {
        return true;
    }
    
    @Override
    protected NetflixActionBar createActionBar() {
        if (BrowseExperience.isKubrick() || BrowseExperience.isDisplayPageRefresh()) {
            return new BarkerHomeActionBar(this, this.hasUpAction());
        }
        return super.createActionBar();
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return this.managerStatusListener;
    }
    
    @Override
    protected NetflixFrag createPrimaryFrag() {
        PerformanceProfiler.getInstance().startSession(Sessions.LOLOMO_LOAD, null);
        if (!"lolomo".equals(this.genreId) && BrowseExperience.useKidsGenresLoMo()) {
            return KidsGenresLoMoFrag.create(this.genreId, this.genre);
        }
        return LoLoMoFrag.create(this.genreId, this.genre);
    }
    
    @Override
    public boolean dispatchKeyEvent(final KeyEvent keyEvent) {
        return this.getPrimaryFrag().dispatchKeyEvent(keyEvent) || super.dispatchKeyEvent(keyEvent);
    }
    
    @Override
    public int getActionBarParentViewId() {
        return 2131689834;
    }
    
    @Override
    protected int getContentLayoutId() {
        return 2130903132;
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
    
    @Override
    public LoLoMoFrag getPrimaryFrag() {
        return (LoLoMoFrag)super.getPrimaryFrag();
    }
    
    @Override
    public IClientLogging$ModalView getUiScreen() {
        return IClientLogging$ModalView.homeScreen;
    }
    
    @Override
    public ObjectRecycler$ViewRecycler getViewRecycler() {
        return this.viewRecycler;
    }
    
    @SuppressLint({ "RtlHardcoded" })
    @Override
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
    
    @Override
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
        }
        this.handleNewIntent(this.getIntent());
        this.viewRecycler = new ObjectRecycler$ViewRecycler();
        super.onCreate(bundle);
        this.setupViews();
        this.registerReceivers();
        this.showFetchErrorsToast();
        this.pauseTimeMs = SystemClock.elapsedRealtime();
        Coppola2Utils.forceToPortraitIfNeeded(this);
    }
    
    @Override
    protected void onCreateOptionsMenu(final Menu menu, final Menu menu2) {
        if (this.getMdxMiniPlayerFrag() != null) {
            MdxMenu.addSelectPlayTarget(this, menu, BrowseExperience.showKidsExperience());
        }
        else {
            Log.e("HomeActivity", "onCreateOptionsMenu got null MdxMiniPlayerFrag");
        }
        SearchMenu.addSearchNavigation(this, menu, BrowseExperience.showKidsExperience());
        super.onCreateOptionsMenu(menu, menu2);
    }
    
    @Override
    public void onDecline() {
        if (this.mDialogManager != null) {
            this.mDialogManager.onDecline();
        }
        this.showDataSaverNotif();
    }
    
    @Override
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        Log.d("HomeActivity", "onNewIntent: ", intent);
        if (this.handleNewIntent(intent)) {
            this.showNewFrag();
        }
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        if (Log.isLoggable()) {
            Log.d("HomeActivity", "onResumedAfterTimeout() will fire if activity not resumed within: 28800 seconds");
        }
        this.pauseTimeMs = SystemClock.elapsedRealtime();
        this.cancelMarkingNotificationsAsRead();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        this.slidingMenuAdapter.onActivityResume();
        final long n = SystemClock.elapsedRealtime() - this.pauseTimeMs;
        if (n > 28800000L) {
            Log.d("HomeActivity", "Activity resume timeout reached");
            this.onResumeAfterTimeout();
        }
        else if (Log.isLoggable()) {
            Log.d("HomeActivity", "Activity resume timeout NOT reached, elapsed ms: " + n);
        }
    }
    
    @Override
    protected void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable("extra_back_stack_intents", (Serializable)this.backStackIntents);
    }
    
    @Override
    protected void onSlidingPanelCollapsed(final View view) {
        this.unlockSlidingDrawerIfPossible();
    }
    
    @Override
    protected void onSlidingPanelExpanded(final View view) {
        this.lockSlidingDrawer();
    }
    
    @Override
    public void performUpAction() {
        this.toggleDrawer();
    }
    
    @Override
    public boolean shouldApplyPaddingToSlidingPanel() {
        return false;
    }
    
    protected void showProfileToast() {
        if (NetflixApplication.isDebugToastEnabled() && this.getServiceManager().getCurrentProfile() != null) {
            Toast.makeText((Context)this, (CharSequence)String.format("DEBUG: Profile %s", this.getServiceManager().getCurrentProfile().getProfileName()), 1).show();
        }
    }
}
