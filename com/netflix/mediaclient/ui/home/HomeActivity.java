// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import android.view.View;
import com.netflix.mediaclient.servicemgr.IBrowseManager;
import android.view.MenuItem$OnMenuItemClickListener;
import com.netflix.mediaclient.ui.search.SearchMenu;
import com.netflix.mediaclient.ui.mdx.MdxMenu;
import android.view.Menu;
import android.app.Activity;
import com.netflix.mediaclient.ui.kids.lolomo.KidsSlidingMenuAdapter;
import java.util.Collection;
import android.os.Bundle;
import android.content.res.Configuration;
import android.view.KeyEvent;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.servicemgr.UIViewLogging;
import com.netflix.mediaclient.android.app.CommonStatus;
import android.app.Fragment;
import android.os.Parcelable;
import android.widget.Toast;
import com.netflix.mediaclient.service.webclient.model.leafs.ListOfGenreSummary;
import com.netflix.mediaclient.util.StringUtils;
import java.io.Serializable;
import com.netflix.mediaclient.ui.kids.lolomo.KidsHomeActivity;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import android.content.Context;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import android.os.SystemClock;
import com.netflix.mediaclient.android.app.LoadingStatus;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.view.MenuItem;
import com.netflix.mediaclient.servicemgr.model.genre.GenreList;
import com.netflix.mediaclient.android.osp.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.content.Intent;
import java.util.LinkedList;
import com.netflix.mediaclient.android.widget.ViewRecycler;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;

public class HomeActivity extends FragmentHostActivity implements OptInResponseHandler, ViewRecyclerProvider
{
    private static final long ACTIVITY_RESUME_TIMEOUT_MS = 28800000L;
    private static final String EXTRA_BACK_STACK_INTENTS = "extra_back_stack_intents";
    private static final String EXTRA_GENRE_ID = "genre_id";
    private static final String EXTRA_GENRE_PARCEL = "genre_parcel";
    public static final String REFRESH_HOME_LOLOMO = "com.netflix.mediaclient.intent.action.REFRESH_HOME_LOLOMO";
    static final int REQUEST_RESOLVE_ERROR = 1001;
    private static final String TAG = "HomeActivity";
    private final LinkedList<Intent> backStackIntents;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggler;
    private GenreList genre;
    private String genreId;
    private MenuItem kidsEntryItem;
    private DialogManager mDialogManager;
    private long mStartedTimeMs;
    private ServiceManager manager;
    private final ManagerStatusListener managerStatusListener;
    private long pauseTimeMs;
    private final BroadcastReceiver refreshHomeReceiver;
    private SlidingMenuAdapter slidingMenuAdapter;
    private ViewRecycler viewRecycler;
    
    public HomeActivity() {
        this.backStackIntents = new LinkedList<Intent>();
        this.managerStatusListener = new ManagerStatusListener() {
            @Override
            public void onManagerReady(final ServiceManager serviceManager, final Status status) {
                Log.v("HomeActivity", "ServiceManager ready");
                HomeActivity.this.manager = serviceManager;
                HomeActivity.this.showProfileToast();
                HomeActivity.this.reportUiViewChanged(HomeActivity.this.getCurrentViewType());
                HomeActivity.this.getPrimaryFrag().onManagerReady(serviceManager, status);
                HomeActivity.this.slidingMenuAdapter.onManagerReady(serviceManager, status);
                KidsUtils.updateKidsMenuItem(HomeActivity.this, HomeActivity.this.kidsEntryItem);
                HomeActivity.this.setLoadingStatusCallback(new LoadingStatusCallback() {
                    @Override
                    public void onDataLoaded(final Status status) {
                        Log.d("HomeActivity", "LOLOMO is loaded, report UI browse startup session ended in case this was on UI startup");
                        HomeActivity.this.getServiceManager().getClientLogging().getApplicationPerformanceMetricsLogging().endUiBrowseStartupSession(SystemClock.elapsedRealtime() - HomeActivity.this.mStartedTimeMs, status.isSucces(), null);
                        HomeActivity.this.setLoadingStatusCallback(null);
                    }
                });
                HomeActivity.this.mDialogManager = new DialogManager(HomeActivity.this);
                HomeActivity.this.mDialogManager.displayDialogsIfNeeded();
            }
            
            @Override
            public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
                Log.w("HomeActivity", "ServiceManager unavailable");
                KidsUtils.updateKidsMenuItem(HomeActivity.this, HomeActivity.this.kidsEntryItem);
                HomeActivity.this.manager = null;
                HomeActivity.this.getPrimaryFrag().onManagerUnavailable(serviceManager, status);
                HomeActivity.this.slidingMenuAdapter.onManagerUnavailable(serviceManager, status);
                Log.d("HomeActivity", "LOLOMO failed, report UI startup session ended in case this was on UI startup");
            }
        };
        this.refreshHomeReceiver = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                if (intent == null) {
                    Log.w("HomeActivity", "Received null intent");
                }
                else {
                    final String action = intent.getAction();
                    Log.i("HomeActivity", "RefreshHomeReceiver inovoked and received Intent with Action " + action);
                    if ("com.netflix.mediaclient.intent.action.REFRESH_HOME_LOLOMO".equals(action)) {
                        HomeActivity.this.clearAllStateAndRefresh();
                    }
                }
            }
        };
    }
    
    private void clearAllStateAndRefresh() {
        this.getServiceManager().getBrowse().flushCaches();
        this.getPrimaryFrag().refresh();
        this.slidingMenuAdapter.refresh();
    }
    
    public static Intent createShowIntent(final NetflixActivity netflixActivity) {
        final boolean shouldShowKidsExperience = KidsUtils.shouldShowKidsExperience(netflixActivity);
        if (Log.isLoggable("HomeActivity", 2)) {
            Log.v("HomeActivity", "Creating home activity, showing kids experience: " + shouldShowKidsExperience);
        }
        Serializable s;
        if (shouldShowKidsExperience) {
            s = KidsHomeActivity.class;
        }
        else {
            s = HomeActivity.class;
        }
        return new Intent((Context)netflixActivity, (Class)s).addFlags(67108864);
    }
    
    public static Intent createStartIntent(final NetflixActivity netflixActivity) {
        return createShowIntent(netflixActivity).putExtra("genre_id", "lolomo");
    }
    
    private IClientLogging.ModalView getCurrentViewType() {
        if (StringUtils.isEmpty(this.genreId)) {
            return IClientLogging.ModalView.homeScreen;
        }
        if ("lolomo".equals(this.genreId)) {
            return IClientLogging.ModalView.homeScreen;
        }
        return IClientLogging.ModalView.browseTitles;
    }
    
    private boolean handleNewIntent(final Intent intent) {
        final boolean b = true;
        final String stringExtra = intent.getStringExtra("genre_id");
        if (StringUtils.isEmpty(stringExtra)) {
            Log.d("HomeActivity", "No new genre ID to show");
            return false;
        }
        if (Log.isLoggable("HomeActivity", 2)) {
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
        this.genre = (ListOfGenreSummary)intent.getParcelableExtra("genre_parcel");
        this.setIntent(intent);
        this.reportUiViewChanged(this.getCurrentViewType());
        return b2;
    }
    
    private void lockSlidingDrawer() {
        this.drawerLayout.setDrawerLockMode(1);
    }
    
    private void onResumeAfterTimeout() {
        Toast.makeText((Context)this, 2131493230, 1).show();
        this.clearAllStateAndRefresh();
    }
    
    private void registerRefreshHomeReceiver() {
        this.registerReceiverWithAutoUnregister(this.refreshHomeReceiver, "com.netflix.mediaclient.intent.action.REFRESH_HOME_LOLOMO");
    }
    
    public static void showGenreList(final NetflixActivity netflixActivity, final GenreList list) {
        final boolean shouldShowKidsExperience = KidsUtils.shouldShowKidsExperience(netflixActivity);
        if (Log.isLoggable("HomeActivity", 2)) {
            Log.v("HomeActivity", "Showing genres list, kids experience: " + shouldShowKidsExperience);
        }
        Serializable s;
        if (shouldShowKidsExperience) {
            s = KidsHomeActivity.class;
        }
        else {
            s = HomeActivity.class;
        }
        netflixActivity.startActivity(new Intent((Context)netflixActivity, (Class)s).addFlags(67108864).putExtra("genre_id", list.getId()).putExtra("genre_parcel", (Parcelable)list));
    }
    
    private void showNewFrag() {
        this.updateActionBar();
        this.updateSlidingDrawer();
        this.setPrimaryFrag(this.createPrimaryFrag());
        this.getFragmentManager().beginTransaction().replace(2131165387, (Fragment)this.getPrimaryFrag(), "primary").setTransition(4099).commit();
        this.getFragmentManager().executePendingTransactions();
        this.getPrimaryFrag().onManagerReady(this.manager, CommonStatus.OK);
    }
    
    private void toggleDrawer() {
        if (this.drawerLayout.isDrawerOpen(3)) {
            LogUtils.reportUIViewCommand((Context)this, UIViewLogging.UIViewCommandName.slidingMenuClosed, this.getUiScreen(), this.getDataContext());
            this.drawerLayout.closeDrawers();
            return;
        }
        LogUtils.reportUIViewCommand((Context)this, UIViewLogging.UIViewCommandName.slidingMenuOpened, this.getUiScreen(), this.getDataContext());
        this.drawerLayout.openDrawer(3);
    }
    
    private void unlockSlidingDrawerIfPossible() {
        if (this.isForKids()) {
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
            NetflixActionBar.LogoType logoType;
            if (StringUtils.isEmpty(title)) {
                logoType = NetflixActionBar.LogoType.FULL_SIZE;
            }
            else {
                logoType = NetflixActionBar.LogoType.GONE;
            }
            netflixActionBar.setLogoType(logoType);
        }
    }
    
    private void updateSlidingDrawer() {
        this.slidingMenuAdapter.setSelectedGenre(this.genre);
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
        return 2130903095;
    }
    
    @Override
    public LoLoMoFrag getPrimaryFrag() {
        return (LoLoMoFrag)super.getPrimaryFrag();
    }
    
    @Override
    public IClientLogging.ModalView getUiScreen() {
        return IClientLogging.ModalView.homeScreen;
    }
    
    @Override
    public ViewRecycler getViewRecycler() {
        return this.viewRecycler;
    }
    
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
    
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.drawerToggler.onConfigurationChanged(configuration);
    }
    
    public void onCreate(final Bundle bundle) {
        this.mStartedTimeMs = SystemClock.elapsedRealtime();
        if (bundle != null) {
            this.backStackIntents.addAll((Collection<? extends Intent>)bundle.getSerializable("extra_back_stack_intents"));
        }
        this.registerRefreshHomeReceiver();
        this.handleNewIntent(this.getIntent());
        this.viewRecycler = new ViewRecycler();
        super.onCreate(bundle);
        this.showFetchErrorsToast();
        this.drawerLayout = (DrawerLayout)this.findViewById(2131165389);
        this.unlockSlidingDrawerIfPossible();
        SlidingMenuAdapter slidingMenuAdapter;
        if (this.isForKids()) {
            slidingMenuAdapter = new KidsSlidingMenuAdapter(this, this.drawerLayout);
        }
        else {
            slidingMenuAdapter = new SlidingMenuAdapter(this, this.drawerLayout);
        }
        this.slidingMenuAdapter = slidingMenuAdapter;
        this.drawerToggler = new ActionBarDrawerToggle(this, this.drawerLayout, 2130837694, 2131493191, 2131493191);
        this.drawerLayout.setDrawerListener((DrawerLayout.DrawerListener)this.drawerToggler);
        this.drawerLayout.setFocusable(false);
        this.updateActionBar();
        this.updateSlidingDrawer();
        this.pauseTimeMs = SystemClock.elapsedRealtime();
    }
    
    @Override
    protected void onCreateOptionsMenu(final Menu menu, final Menu menu2) {
        MdxMenu.addSelectPlayTarget(this.getMdxMiniPlayerFrag(), menu);
        SearchMenu.addSearchNavigation(this, menu);
        this.kidsEntryItem = KidsUtils.createKidsMenuItem(this, menu);
        if (menu2 != null) {
            menu2.add((CharSequence)"Dump LoLoMo Data").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new MenuItem$OnMenuItemClickListener() {
                public boolean onMenuItemClick(final MenuItem menuItem) {
                    final IBrowseManager browse = HomeActivity.this.manager.getBrowse();
                    final String access$000 = HomeActivity.this.genreId;
                    String title;
                    if (HomeActivity.this.genre == null) {
                        title = "lolomo";
                    }
                    else {
                        title = HomeActivity.this.genre.getTitle();
                    }
                    browse.dumpHomeLoLoMosAndVideos(access$000, title);
                    return false;
                }
            });
        }
        super.onCreateOptionsMenu(menu, menu2);
    }
    
    @Override
    public void onDecline() {
        if (this.mDialogManager != null) {
            this.mDialogManager.onDecline();
        }
    }
    
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        if (this.handleNewIntent(intent)) {
            this.showNewFrag();
        }
    }
    
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        return this.drawerToggler.onOptionsItemSelected(menuItem) || super.onOptionsItemSelected(menuItem);
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        if (Log.isLoggable("HomeActivity", 3)) {
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
        if (SystemClock.elapsedRealtime() - this.pauseTimeMs > 28800000L) {
            Log.d("HomeActivity", "Activity resume timeout reached");
            this.onResumeAfterTimeout();
            return;
        }
        Log.d("HomeActivity", "Activity resume timeout NOT reached");
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
