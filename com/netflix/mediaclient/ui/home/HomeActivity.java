// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import java.io.Serializable;
import android.view.MenuItem;
import android.view.MenuItem$OnMenuItemClickListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.search.SearchMenu;
import com.netflix.mediaclient.ui.mdx.MdxMenu;
import android.view.Menu;
import java.util.Collection;
import android.os.Bundle;
import android.content.res.Configuration;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.ui.lolomo.BaseLoLoMoAdapter;
import android.view.KeyEvent;
import com.netflix.mediaclient.android.widget.AccessibilityRunnable;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.app.Fragment;
import com.netflix.mediaclient.servicemgr.GenreList;
import android.app.Activity;
import android.widget.Toast;
import com.netflix.mediaclient.util.StringUtils;
import android.view.View;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import android.content.Context;
import android.app.DialogFragment;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import android.os.SystemClock;
import com.netflix.mediaclient.android.app.LoadingStatus;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.widget.ViewRecycler;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.osp.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.content.Intent;
import java.util.LinkedList;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;

public class HomeActivity extends FragmentHostActivity implements OptInResponseHandler
{
    private static final long ACTIVITY_RESUME_TIMEOUT_MS = 28800000L;
    private static final String EXTRA_BACK_STACK_INTENTS = "extra_back_stack_intents";
    private static final String EXTRA_GENRE_ID = "genre_id";
    private static final String EXTRA_GENRE_TITLE = "genre_title";
    public static final String REFRESH_HOME_LOLOMO = "com.netflix.mediaclient.intent.action.REFRESH_HOME_LOLOMO";
    private static final String TAG = "HomeActivity";
    private final LinkedList<Intent> backStackIntents;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggler;
    private String genreId;
    private long mStartedTimeMs;
    private ServiceManager manager;
    private final ManagerStatusListener managerStatusListener;
    private long pauseTimeMs;
    private final BroadcastReceiver refreshHomeReceiver;
    private SlidingMenuAdapter slidingMenuAdapter;
    private String title;
    private ViewRecycler viewRecycler;
    
    public HomeActivity() {
        this.backStackIntents = new LinkedList<Intent>();
        this.managerStatusListener = new ManagerStatusListener() {
            @Override
            public void onManagerReady(final ServiceManager serviceManager, final int n) {
                Log.v("HomeActivity", "ServiceManager ready");
                HomeActivity.this.manager = serviceManager;
                HomeActivity.this.setLoadingStatusCallback(new LoadingStatusCallback() {
                    @Override
                    public void onDataLoaded(final int n) {
                        Log.d("HomeActivity", "LOLOMO is loaded, report UI browse startup session ended in case this was on UI startup");
                        HomeActivity.this.getServiceManager().getClientLogging().getApplicationPerformanceMetricsLogging().endUiBrowseStartupSession(SystemClock.elapsedRealtime() - HomeActivity.this.mStartedTimeMs, n >= 0, null);
                        HomeActivity.this.setLoadingStatusCallback(null);
                    }
                });
                HomeActivity.this.reportUiViewChanged(HomeActivity.this.getCurrentViewType());
                HomeActivity.this.getPrimaryFrag().onManagerReady(serviceManager, n);
                HomeActivity.this.slidingMenuAdapter.onManagerReady(serviceManager, n);
                if (HomeActivity.this.shouldDisplayOptInDialog()) {
                    Log.d("HomeActivity", "Displaying opt in dialog");
                    final SocialOptInDialogFrag instance = SocialOptInDialogFrag.newInstance();
                    instance.setCancelable(false);
                    HomeActivity.this.showDialog(instance);
                    return;
                }
                Log.d("HomeActivity", "No need to display opt in dialog");
            }
            
            @Override
            public void onManagerUnavailable(final ServiceManager serviceManager, final int n) {
                Log.w("HomeActivity", "ServiceManager unavailable");
                HomeActivity.this.manager = null;
                HomeActivity.this.getPrimaryFrag().onManagerUnavailable(serviceManager, n);
                HomeActivity.this.slidingMenuAdapter.onManagerUnavailable(serviceManager, n);
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
                        HomeActivity.this.clearHomeLoLoMoState();
                    }
                }
            }
        };
    }
    
    private void clearHomeLoLoMoState() {
        this.getServiceManager().flushCaches();
        this.getPrimaryFrag().refresh();
        this.slidingMenuAdapter.refresh();
    }
    
    private DrawerLayout.DrawerListener createDrawerListenerWrapper(final DrawerLayout.DrawerListener drawerListener) {
        return new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerClosed(final View view) {
                drawerListener.onDrawerClosed(view);
            }
            
            @Override
            public void onDrawerOpened(final View view) {
                drawerListener.onDrawerOpened(view);
            }
            
            @Override
            public void onDrawerSlide(final View view, final float n) {
                drawerListener.onDrawerSlide(view, n);
            }
            
            @Override
            public void onDrawerStateChanged(final int n) {
                drawerListener.onDrawerStateChanged(n);
            }
        };
    }
    
    public static Intent createStartIntent(final Context context) {
        return new Intent(context, (Class)HomeActivity.class).addFlags(67108864).putExtra("genre_id", "lolomo");
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
        if (Log.isLoggable("HomeActivity", 2)) {
            Log.v("HomeActivity", "Curr genre: " + this.genreId + ", new genre: " + stringExtra);
        }
        boolean b2 = b;
        if (StringUtils.isNotEmpty(this.genreId)) {
            if (this.genreId.equals(stringExtra)) {
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
        }
        if ("lolomo".equals(stringExtra)) {
            this.backStackIntents.clear();
        }
        this.genreId = stringExtra;
        this.title = intent.getStringExtra("genre_title");
        this.setIntent(intent);
        this.reportUiViewChanged(this.getCurrentViewType());
        return b2;
    }
    
    private void onResumeAfterTimeout() {
        Toast.makeText((Context)this, 2131296604, 1).show();
        this.clearHomeLoLoMoState();
    }
    
    private void registerRefreshHomeReceiver() {
        this.registerReceiverWithAutoUnregister(this.refreshHomeReceiver, "com.netflix.mediaclient.intent.action.REFRESH_HOME_LOLOMO");
    }
    
    private boolean shouldDisplayOptInDialog() {
        if (this.manager.getPushNotification().wasNotificationOptInDisplayed()) {
            Log.d("HomeActivity", "User was already prompted for opt in to social!");
            return false;
        }
        if (this.isDialogFragmentVisible()) {
            Log.w("HomeActivity", "Dialog fragment is already displayed. It should be only one visible at time! Do NOT display opt in to social!");
            return false;
        }
        Log.d("HomeActivity", "User was NOT prompted for opt in to social and none of dialogs is visible.");
        return true;
    }
    
    public static void showGenreList(final Activity activity, final GenreList list) {
        showGenreList(activity, list.getId(), list.getTitle());
    }
    
    public static void showGenreList(final Activity activity, final String s, final String s2) {
        activity.startActivity(new Intent((Context)activity, (Class)HomeActivity.class).addFlags(67108864).putExtra("genre_id", s).putExtra("genre_title", s2));
    }
    
    private void showNewGenresFrag() {
        this.updateActionBar();
        this.setPrimaryFrag(this.createPrimaryFrag());
        this.getFragmentManager().beginTransaction().replace(2131230883, (Fragment)this.getPrimaryFrag(), "primary").setTransition(4099).commit();
        this.getFragmentManager().executePendingTransactions();
        this.getPrimaryFrag().onManagerReady(this.manager, 0);
    }
    
    private void toggleDrawer() {
        if (this.drawerLayout.isDrawerOpen(3)) {
            this.drawerLayout.closeDrawers();
            return;
        }
        this.drawerLayout.openDrawer(3);
    }
    
    private void updateActionBar() {
        Log.v("HomeActivity", "Updating action bar, title: " + this.title);
        final NetflixActionBar netflixActionBar = this.getNetflixActionBar();
        if (netflixActionBar != null) {
            netflixActionBar.setTitle(this.title);
            int logo;
            if (StringUtils.isEmpty(this.title)) {
                logo = 2130837505;
            }
            else {
                logo = 2130837760;
            }
            netflixActionBar.setLogo(logo);
        }
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return this.managerStatusListener;
    }
    
    @Override
    protected NetflixFrag createPrimaryFrag() {
        return LoLoMoFrag.create(this.genreId);
    }
    
    @Override
    public AccessibilityRunnable createUpActionRunnable() {
        return new AccessibilityRunnable(new Runnable() {
            @Override
            public void run() {
                HomeActivity.this.toggleDrawer();
            }
        }, this.getString(2131296565));
    }
    
    @Override
    public boolean dispatchKeyEvent(final KeyEvent keyEvent) {
        return this.getPrimaryFrag().dispatchKeyEvent(keyEvent) || super.dispatchKeyEvent(keyEvent);
    }
    
    @Override
    protected int getContentLayoutId() {
        return 2130903089;
    }
    
    public BaseLoLoMoAdapter<?> getLoLoMoAdapter() {
        final LoLoMoFrag primaryFrag = this.getPrimaryFrag();
        if (primaryFrag == null) {
            return null;
        }
        return primaryFrag.getLoLoMoAdapter();
    }
    
    @Override
    public LoLoMoFrag getPrimaryFrag() {
        return (LoLoMoFrag)super.getPrimaryFrag();
    }
    
    @Override
    public IClientLogging.ModalView getUiScreen() {
        return IClientLogging.ModalView.homeScreen;
    }
    
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
    
    @Override
    public void onAccept() {
        if (this.destroyed()) {
            return;
        }
        Log.v("HomeActivity", "Sending PUSH_OPTIN...");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.PUSH_NOTIFICATION_OPTIN");
        intent.addCategory("com.netflix.mediaclient.intent.category.PUSH");
        LocalBroadcastManager.getInstance((Context)this).sendBroadcast(intent);
        Log.v("HomeActivity", "Sending PUSH_OPTIN done.");
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
        (this.drawerLayout = (DrawerLayout)this.findViewById(2131230885)).setDrawerLockMode(0);
        this.slidingMenuAdapter = new SlidingMenuAdapter(this, this.drawerLayout);
        this.drawerToggler = new ActionBarDrawerToggle(this, this.drawerLayout, 2130837690, 2131296565, 2131296565);
        this.drawerLayout.setDrawerListener(this.createDrawerListenerWrapper(this.drawerToggler));
        this.drawerLayout.setFocusable(false);
        this.updateActionBar();
        this.pauseTimeMs = SystemClock.elapsedRealtime();
    }
    
    @Override
    protected void onCreateOptionsMenu(final Menu menu, final Menu menu2) {
        MdxMenu.addSelectPlayTarget(this.getMdxMiniPlayerFrag(), menu);
        SearchMenu.addSearchNavigation(this, menu);
        if (menu2 != null) {
            menu2.add((CharSequence)"Dump LoLoMo Data").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new MenuItem$OnMenuItemClickListener() {
                public boolean onMenuItemClick(final MenuItem menuItem) {
                    HomeActivity.this.manager.dumpHomeLoLoMosAndVideos(HomeActivity.this.genreId, HomeActivity.this.title);
                    return false;
                }
            });
        }
        super.onCreateOptionsMenu(menu, menu2);
    }
    
    @Override
    public void onDecline() {
        if (this.destroyed()) {
            return;
        }
        Log.v("HomeActivity", "Sending PUSH_OPTOUT...");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.PUSH_NOTIFICATION_OPTOUT");
        intent.addCategory("com.netflix.mediaclient.intent.category.PUSH");
        LocalBroadcastManager.getInstance((Context)this).sendBroadcast(intent);
        Log.v("HomeActivity", "Sending PUSH_OPTOUT done.");
    }
    
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        Log.v("HomeActivity", "onNewIntent: " + intent.toString() + ", " + intent.getExtras());
        if (this.handleNewIntent(intent)) {
            this.showNewGenresFrag();
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
        this.showProfileToast();
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
        this.drawerLayout.setDrawerLockMode(0);
    }
    
    @Override
    protected void onSlidingPanelExpanded(final View view) {
        this.drawerLayout.setDrawerLockMode(1);
    }
    
    protected void showProfileToast() {
    }
}
