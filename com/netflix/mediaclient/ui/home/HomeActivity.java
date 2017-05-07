// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import android.view.View;
import java.io.Serializable;
import android.view.MenuItem;
import com.netflix.mediaclient.ui.search.SearchMenu;
import com.netflix.mediaclient.ui.mdx.MdxMenu;
import android.view.Menu;
import android.support.v4.widget.DrawerLayout$DrawerListener;
import android.app.Activity;
import com.netflix.mediaclient.ui.kids.lolomo.KidsSlidingMenuAdapter;
import java.util.Collection;
import android.os.SystemClock;
import android.os.Bundle;
import android.content.res.Configuration;
import android.view.KeyEvent;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.android.widget.NetflixActionBar$LogoType;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import android.app.Fragment;
import android.os.Parcelable;
import com.netflix.mediaclient.util.SocialUtils;
import android.widget.Toast;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Context;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.util.SocialUtils$NotificationsListStatus;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.content.DialogInterface$OnClickListener;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.content.Intent;
import java.util.LinkedList;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecyclerProvider;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;

public class HomeActivity extends FragmentHostActivity implements ObjectRecycler$ViewRecyclerProvider, SocialOptInDialogFrag$OptInResponseHandler
{
    private static final long ACTIVITY_RESUME_TIMEOUT_MS = 28800000L;
    private static final String EXTRA_BACK_STACK_INTENTS = "extra_back_stack_intents";
    private static final String EXTRA_GENRE_ID = "genre_id";
    private static final String EXTRA_GENRE_PARCEL = "genre_parcel";
    public static final String HOME_LOLOMO_UPDATED = "com.netflix.mediaclient.intent.action.HOME_LOLOMO_UPDATED";
    public static final String REFRESH_HOME_LOLOMO = "com.netflix.mediaclient.intent.action.REFRESH_HOME_LOLOMO";
    static final int REQUEST_RESOLVE_ERROR = 1001;
    private static final String TAG = "HomeActivity";
    private final LinkedList<Intent> backStackIntents;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggler;
    private GenreList genre;
    private String genreId;
    private final BroadcastReceiver homeUpdatedReceiver;
    protected final DialogInterface$OnClickListener invalidCountryDialogListener;
    private DialogManager mDialogManager;
    private long mStartedTimeMs;
    private ServiceManager manager;
    private final ManagerStatusListener managerStatusListener;
    private SocialUtils$NotificationsListStatus notificationsListStatus;
    private long pauseTimeMs;
    private final BroadcastReceiver refreshHomeReceiver;
    private SlidingMenuAdapter slidingMenuAdapter;
    private final BroadcastReceiver socialNotificationsListUpdateReceiver;
    private ObjectRecycler$ViewRecycler viewRecycler;
    
    public HomeActivity() {
        this.backStackIntents = new LinkedList<Intent>();
        this.notificationsListStatus = SocialUtils$NotificationsListStatus.NO_MESSAGES;
        this.managerStatusListener = new HomeActivity$1(this);
        this.refreshHomeReceiver = new HomeActivity$2(this);
        this.homeUpdatedReceiver = new HomeActivity$3(this);
        this.socialNotificationsListUpdateReceiver = new HomeActivity$4(this);
        this.invalidCountryDialogListener = (DialogInterface$OnClickListener)new HomeActivity$5(this);
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
    
    private IClientLogging$ModalView getCurrentViewType() {
        if (StringUtils.isEmpty(this.genreId)) {
            return IClientLogging$ModalView.homeScreen;
        }
        if ("lolomo".equals(this.genreId)) {
            return IClientLogging$ModalView.homeScreen;
        }
        return IClientLogging$ModalView.browseTitles;
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
    
    private void lockSlidingDrawer() {
        this.drawerLayout.setDrawerLockMode(1);
    }
    
    private void onResumeAfterTimeout() {
        Toast.makeText((Context)this, 2131493211, 1).show();
        this.clearAllStateAndRefresh();
    }
    
    private void refreshSocialNotificationsStateIfNeeded() {
        final boolean b = this.notificationsListStatus != SocialUtils$NotificationsListStatus.NO_MESSAGES && true;
        final boolean notificationsFeatureSupported = SocialUtils.isNotificationsFeatureSupported(this);
        if (b != notificationsFeatureSupported) {
            this.invalidateOptionsMenu();
            if (notificationsFeatureSupported && this.manager != null && this.manager.getBrowse() != null) {
                this.manager.getBrowse().refreshSocialNotifications(true);
            }
        }
    }
    
    private void registerReceivers() {
        this.registerReceiverWithAutoUnregister(this.refreshHomeReceiver, "com.netflix.mediaclient.intent.action.REFRESH_HOME_LOLOMO");
        this.registerReceiverWithAutoUnregister(this.homeUpdatedReceiver, "com.netflix.mediaclient.intent.action.HOME_LOLOMO_UPDATED");
        this.registerReceiverLocallyWithAutoUnregister(this.socialNotificationsListUpdateReceiver, "com.netflix.mediaclient.intent.action.BA_NOTIFICATION_LIST_UPDATED");
    }
    
    public static void showGenreList(final NetflixActivity netflixActivity, final GenreList list) {
        netflixActivity.startActivity(new Intent((Context)netflixActivity, (Class)getHomeActivityClass()).addFlags(67108864).putExtra("genre_id", list.getId()).putExtra("genre_parcel", (Parcelable)list));
    }
    
    private void showNewFrag() {
        this.updateActionBar();
        this.updateSlidingDrawer();
        this.setPrimaryFrag(this.createPrimaryFrag());
        this.getFragmentManager().beginTransaction().replace(2131165375, (Fragment)this.getPrimaryFrag(), "primary").setTransition(4099).commit();
        this.getFragmentManager().executePendingTransactions();
        this.getPrimaryFrag().onManagerReady(this.manager, CommonStatus.OK);
    }
    
    @SuppressLint({ "RtlHardcoded" })
    private void toggleDrawer() {
        if (this.drawerLayout.isDrawerOpen(3)) {
            UIViewLogUtils.reportUIViewCommand((Context)this, UIViewLogging$UIViewCommandName.slidingMenuClosed, this.getUiScreen(), this.getDataContext());
            this.drawerLayout.closeDrawers();
            return;
        }
        UIViewLogUtils.reportUIViewCommand((Context)this, UIViewLogging$UIViewCommandName.slidingMenuOpened, this.getUiScreen(), this.getDataContext());
        this.drawerLayout.openDrawer(3);
    }
    
    private void unlockSlidingDrawerIfPossible() {
        if (BrowseExperience.isKubrickKids()) {
            this.lockSlidingDrawer();
            return;
        }
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
    protected ManagerStatusListener createManagerStatusListener() {
        return this.managerStatusListener;
    }
    
    @Override
    protected NetflixFrag createPrimaryFrag() {
        return LoLoMoFrag.create(this.genreId, this.genre);
    }
    
    @Override
    public boolean dispatchKeyEvent(final KeyEvent keyEvent) {
        return this.getPrimaryFrag().dispatchKeyEvent(keyEvent) || super.dispatchKeyEvent(keyEvent);
    }
    
    @Override
    protected int getContentLayoutId() {
        return 2130903097;
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
        if (this.drawerLayout != null && this.drawerLayout.isDrawerOpen(3)) {
            Log.v("HomeActivity", "Sliding drawer was open, closing...");
            this.drawerLayout.closeDrawer(3);
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
    
    public boolean isKidsGenre() {
        return this.genre != null && this.genre.isKidsGenre();
    }
    
    @Override
    public void onAccept() {
        if (this.mDialogManager != null) {
            this.mDialogManager.onAccept();
        }
    }
    
    @Override
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.drawerToggler.onConfigurationChanged(configuration);
    }
    
    public void onCreate(final Bundle bundle) {
        this.mStartedTimeMs = SystemClock.elapsedRealtime();
        if (bundle != null) {
            this.backStackIntents.addAll((Collection<? extends Intent>)bundle.getSerializable("extra_back_stack_intents"));
        }
        this.registerReceivers();
        this.handleNewIntent(this.getIntent());
        this.viewRecycler = new ObjectRecycler$ViewRecycler();
        super.onCreate(bundle);
        this.showFetchErrorsToast();
        this.drawerLayout = (DrawerLayout)this.findViewById(2131165377);
        this.unlockSlidingDrawerIfPossible();
        SlidingMenuAdapter slidingMenuAdapter;
        if (BrowseExperience.isKubrickKids()) {
            slidingMenuAdapter = new KidsSlidingMenuAdapter(this, this.drawerLayout);
        }
        else {
            slidingMenuAdapter = new SlidingMenuAdapter(this, this.drawerLayout);
        }
        this.slidingMenuAdapter = slidingMenuAdapter;
        this.drawerToggler = new ActionBarDrawerToggle(this, this.drawerLayout, 2131493175, 2131493175);
        this.drawerLayout.setDrawerListener(this.drawerToggler);
        this.drawerLayout.setFocusable(false);
        this.updateActionBar();
        this.updateSlidingDrawer();
        this.pauseTimeMs = SystemClock.elapsedRealtime();
    }
    
    @Override
    protected void onCreateOptionsMenu(final Menu menu, final Menu menu2) {
        if (this.getMdxMiniPlayerFrag() != null) {
            MdxMenu.addSelectPlayTarget(this, menu);
        }
        else {
            Log.e("HomeActivity", "onCreateOptionsMenu got null MdxMiniPlayerFrag");
        }
        SearchMenu.addSearchNavigation(this, menu);
        SocialUtils.addNotificationsIconIfNeeded(this, this.notificationsListStatus, menu);
        super.onCreateOptionsMenu(menu, menu2);
    }
    
    @Override
    public void onDecline() {
        if (this.mDialogManager != null) {
            this.mDialogManager.onDecline();
        }
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
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        return this.drawerToggler.onOptionsItemSelected(menuItem) || SocialUtils.tryHandleMenuItemClick(menuItem, (Context)this) || super.onOptionsItemSelected(menuItem);
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        if (Log.isLoggable()) {
            Log.d("HomeActivity", "onResumedAfterTimeout() will fire if activity not resumed within: 28800 seconds");
        }
        this.pauseTimeMs = SystemClock.elapsedRealtime();
    }
    
    @Override
    protected void onPostCreate(final Bundle bundle) {
        super.onPostCreate(bundle);
        this.drawerToggler.syncState();
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
    }
}
