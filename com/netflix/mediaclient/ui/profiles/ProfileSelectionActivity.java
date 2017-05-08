// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.profiles;

import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.android.app.CommonStatus;
import android.view.MenuItem;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.Menu;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.os.Bundle;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.ui.launch.RelaunchActivity;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.view.ViewGroup$MarginLayoutParams;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.android.widget.NetflixActionBar$LogoType;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.util.StringUtils;
import java.util.Iterator;
import android.widget.ListAdapter;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.view.ViewPropertyAnimator;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.app.Activity;
import android.content.Intent;
import com.netflix.mediaclient.util.l10n.LocalizationUtils;
import java.util.Locale;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.app.Status;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import java.util.List;
import android.widget.AdapterView$OnItemClickListener;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.StaticGridView;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.view.View;
import android.util.SparseIntArray;
import android.util.SparseArray;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class ProfileSelectionActivity extends NetflixActivity
{
    public static final int ALPHA_ANIMATION_DURATION_MS = 400;
    private static final float ALPHA_CONTENT_FADE = 0.2f;
    private static final String EXTRA_APP_WAS_RESTARTED = "app_was_restarted";
    private static final String EXTRA_DESTINATION = "extra_destination";
    private static final String EXTRA_KIDS_DOOR_NAME = "extra_kids_door_name";
    private static final String EXTRA_START_KIDS_BOOL = "extra_start_kids_bool";
    private static final String EXTRA_STOP_KIDS_BOOL = "extra_stop_kids_bool";
    private static final boolean HANG_ON_KIDS_LOADING_SCREEN = false;
    private static final String KEY_IS_LOADING = "is_loading";
    private static final String KEY_IS_PROFILE_EDIT_MODE = "is_profile_edit_mode";
    private static final String KEY_IS_SHOWING_KIDS_LOADING_SCREEN = "is_showing_kids_loading_screen";
    private static final String TAG = "ProfileSelectionActivity";
    private static final SparseArray<SparseIntArray> maxNumGridColumns;
    private ProfileSelectionActivity$ProfileAvatarAdapter adapter;
    private boolean canShowKidsLoadingScreen;
    private int columnWidth;
    private View content;
    private final ErrorWrapper$Callback errorCallback;
    private StaticGridView gridView;
    private boolean isLoading;
    private boolean isProfileEditMode;
    private boolean isShowingKidsLoadingScreen;
    private View kidsLoadingScreen;
    private LoadingAndErrorWrapper leWrapper;
    private boolean mAppWasRestarted;
    private String mDestination;
    private ServiceManager manager;
    private int numCols;
    private final AdapterView$OnItemClickListener onAvatarClickListener;
    private List<? extends UserProfile> profiles;
    private boolean shouldStartKids;
    private TextView topTextHeader;
    
    static {
        maxNumGridColumns = new SparseArray(2);
        final SparseIntArray sparseIntArray = new SparseIntArray();
        sparseIntArray.put(1, 1);
        sparseIntArray.put(2, 2);
        sparseIntArray.put(3, 2);
        sparseIntArray.put(4, 2);
        ProfileSelectionActivity.maxNumGridColumns.put(1, (Object)sparseIntArray);
        final SparseIntArray sparseIntArray2 = new SparseIntArray();
        sparseIntArray2.put(1, 2);
        sparseIntArray2.put(2, 3);
        sparseIntArray2.put(3, 3);
        sparseIntArray2.put(4, 3);
        ProfileSelectionActivity.maxNumGridColumns.put(2, (Object)sparseIntArray2);
    }
    
    public ProfileSelectionActivity() {
        this.errorCallback = new ProfileSelectionActivity$4(this);
        this.onAvatarClickListener = (AdapterView$OnItemClickListener)new ProfileSelectionActivity$5(this);
    }
    
    private void adjustGridViewMargins() {
        final int screenWidthInPixels = DeviceUtils.getScreenWidthInPixels((Context)this);
        final int n = this.columnWidth * this.numCols;
        final int n2 = (screenWidthInPixels - n) / 2;
        if (Log.isLoggable()) {
            Log.v("ProfileSelectionActivity", String.format("screen: %d, grid: %d, padding: %d", screenWidthInPixels, n, n2));
        }
        if (LocalizationUtils.isLocaleLTR(Locale.getDefault())) {
            this.gridView.setPadding(n2, 0, 0, 0);
        }
        else {
            this.gridView.setPadding(0, 0, n2, 0);
        }
        this.showPromoViewIfNeeded(n2);
    }
    
    public static Intent createStartIntent(final Context context) {
        return new Intent(context, (Class)ProfileSelectionActivity.class).addFlags(131072);
    }
    
    public static Intent createStartIntentForAppRestart(final Context context) {
        final Intent startIntent = createStartIntent(context);
        startIntent.putExtra("app_was_restarted", true);
        return startIntent;
    }
    
    public static Intent createStartIntentSingleTop(final Context context) {
        final Intent startIntentForAppRestart = createStartIntentForAppRestart(context);
        startIntentForAppRestart.addFlags(268435456);
        return startIntentForAppRestart;
    }
    
    public static Intent createSwitchFromDeepLinking(final Activity activity, final IClientLogging$ModalView clientLogging$ModalView) {
        if (clientLogging$ModalView == null) {
            return createStartIntent((Context)activity);
        }
        return createStartIntent((Context)activity).putExtra("extra_destination", clientLogging$ModalView.name());
    }
    
    public static Intent createSwitchFromKidsIntent(final Activity activity, final UIViewLogging$UIViewCommandName uiViewLogging$UIViewCommandName) {
        return createStartIntent((Context)activity).putExtra("extra_stop_kids_bool", true).putExtra("extra_kids_door_name", uiViewLogging$UIViewCommandName.toString());
    }
    
    public static Intent createSwitchToKidsIntent(final Activity activity, final UIViewLogging$UIViewCommandName uiViewLogging$UIViewCommandName) {
        return createStartIntent((Context)activity).putExtra("extra_start_kids_bool", true).putExtra("extra_kids_door_name", uiViewLogging$UIViewCommandName.toString());
    }
    
    private void findViewAndAnimate(final View view, final int n) {
        final ViewPropertyAnimator animate = view.findViewById(n).animate();
        float n2;
        if (this.isProfileEditMode) {
            n2 = 0.2f;
        }
        else {
            n2 = 1.0f;
        }
        animate.alpha(n2).setDuration(400L).start();
    }
    
    private void handleManagerReady(final boolean b) {
        final List<? extends UserProfile> allProfiles = this.manager.getAllProfiles();
        if (allProfiles == null) {
            Log.w("ProfileSelectionActivity", "No profiles found for user!");
            this.manager.getClientLogging().getErrorLogging().logHandledException(new IllegalStateException("No profiles found for user!"));
        }
        else {
            this.canShowKidsLoadingScreen = BrowseExperience.showKidsExperience();
            Log.v("ProfileSelectionActivity", "handleManagerReady(), isLoading: " + this.isLoading);
            final Iterator<? extends UserProfile> iterator = allProfiles.iterator();
            while (iterator.hasNext()) {
                Log.d("ProfileSelectionActivity", "profile: " + iterator.next());
            }
            this.profiles = allProfiles;
            this.adapter = new ProfileSelectionActivity$ProfileAvatarAdapter(this, null);
            this.gridView.setAdapter((ListAdapter)this.adapter);
            this.setupGridViewColumns();
            this.showContentView();
            if (this.isLoading) {
                if (Log.isLoggable()) {
                    Log.d("ProfileSelectionActivity", "We're in loading state - showing loading view");
                }
                this.showLoadingViews(false, this.canShowKidsLoadingScreen && this.isShowingKidsLoadingScreen);
            }
            Log.v("ProfileSelectionActivity", String.format("shouldAutoSelectProfile: %s, shouldStartKids: %s", b, this.shouldStartKids));
            if (b && this.shouldStartKids) {
                final Iterator<? extends UserProfile> iterator2 = this.manager.getAllProfiles().iterator();
                int n = 0;
                UserProfile userProfile = null;
                while (iterator2.hasNext()) {
                    final UserProfile userProfile2 = (UserProfile)iterator2.next();
                    if (userProfile2.isKidsProfile()) {
                        ++n;
                        userProfile = userProfile2;
                    }
                }
                if (n == 1) {
                    Log.v("ProfileSelectionActivity", "Found one Kids profile - switching to: " + userProfile.getProfileName());
                    this.startChangeProfile(userProfile);
                    return;
                }
                Log.v("ProfileSelectionActivity", "Num Kids profiles found: " + n);
            }
        }
    }
    
    private void logKidsEntryExit() {
        final boolean booleanExtra = this.getIntent().getBooleanExtra("extra_start_kids_bool", false);
        final boolean booleanExtra2 = this.getIntent().getBooleanExtra("extra_stop_kids_bool", false);
        final String stringExtra = this.getIntent().getStringExtra("extra_kids_door_name");
        if ((Boolean.valueOf(booleanExtra) || Boolean.valueOf(booleanExtra2)) && StringUtils.isNotEmpty(stringExtra)) {
            UIViewLogUtils.reportUIViewCommand((Context)this, UIViewLogging$UIViewCommandName.valueOf(stringExtra), IClientLogging$ModalView.homeScreen, null);
        }
    }
    
    private void refreshEditProfileModeViews() {
        final NetflixActionBar netflixActionBar = this.getNetflixActionBar();
        NetflixActionBar$LogoType logoType;
        if (this.isProfileEditMode) {
            logoType = NetflixActionBar$LogoType.GONE;
        }
        else {
            logoType = NetflixActionBar$LogoType.FULL_SIZE;
        }
        netflixActionBar.setLogoType(logoType);
        if (this.isProfileEditMode) {
            this.getNetflixActionBar().setTitle(this.getResources().getString(2131165718));
        }
        this.getNetflixActionBar().setDisplayHomeAsUpEnabled(this.isProfileEditMode);
        final ViewPropertyAnimator animate = this.topTextHeader.animate();
        float n;
        if (this.isProfileEditMode) {
            n = 0.0f;
        }
        else {
            n = 1.0f;
        }
        animate.alpha(n).setDuration(400L).start();
        for (int i = 0; i < this.gridView.getChildCount(); ++i) {
            final View child = this.gridView.getChildAt(i);
            if (child == null) {
                Log.e("ProfileSelectionActivity", "Something wierd happened: null grid child view!");
            }
            else if (i < this.profiles.size()) {
                final View viewById = child.findViewById(2131624611);
                int visibility;
                if (this.isProfileEditMode) {
                    visibility = 0;
                }
                else {
                    visibility = 8;
                }
                viewById.setVisibility(visibility);
                this.findViewAndAnimate(child, 2131624067);
            }
        }
        this.invalidateOptionsMenu();
    }
    
    private void reportError(final Status status, final String s) {
        UserActionLogUtils.reportNavigationActionEnded((Context)this, this.getUiScreen(), IClientLogging$CompletionReason.failed, ConsolidatedLoggingUtils.createUIError(status, s, ActionOnUIError.displayedError));
        UserActionLogUtils.reportNavigationActionStarted((Context)this, null, this.getUiScreen());
    }
    
    private void setupGridViewColumns() {
        final int value = ((SparseIntArray)ProfileSelectionActivity.maxNumGridColumns.get(DeviceUtils.getBasicScreenOrientation((Context)this))).get(DeviceUtils.getScreenSizeCategory((Context)this));
        int count;
        final int n = count = this.adapter.getCount();
        if (n > 3) {
            count = n - 2;
        }
        this.numCols = Math.min(count, value);
        if (Log.isLoggable()) {
            Log.v("ProfileSelectionActivity", String.format("max cols: %d, limited cols: %d, num cols: %d", value, count, this.numCols));
        }
        this.gridView.setNumColumns(this.numCols);
        this.adjustGridViewMargins();
    }
    
    private void showContentView() {
        Log.v("ProfileSelectionActivity", "Showing content view...");
        this.leWrapper.hide(false);
        this.content.setEnabled(true);
        this.gridView.setEnabled(true);
        if (this.content.getVisibility() == 0) {
            if (this.content.getAlpha() < 1.0f) {
                this.content.animate().alpha(1.0f).setDuration(150L).start();
            }
        }
        else {
            AnimationUtils.showView(this.content, false);
        }
        if (this.kidsLoadingScreen.getVisibility() == 0) {
            AnimationUtils.hideView(this.kidsLoadingScreen, true);
            this.getNetflixActionBar().setLogoType(NetflixActionBar$LogoType.FULL_SIZE);
        }
    }
    
    private void showLoadingViews(final boolean b, final boolean b2) {
        Log.v("ProfileSelectionActivity", "Showing loading view...");
        this.leWrapper.showLoadingView(false);
        this.content.setEnabled(false);
        this.gridView.setEnabled(false);
        if (b) {
            this.content.animate().alpha(0.2f).setDuration(400L).start();
        }
        else {
            this.content.setAlpha(0.2f);
        }
        if (b2) {
            AnimationUtils.showView(this.kidsLoadingScreen, false);
            this.getNetflixActionBar().setLogoType(NetflixActionBar$LogoType.GONE);
        }
    }
    
    private void showPromoViewIfNeeded(final int n) {
        final View viewById = this.findViewById(2131624610);
        if (PreferenceUtils.getBooleanPref((Context)this, "user_profile_was_selected", false) || this.profiles == null || this.profiles.size() > 1) {
            viewById.setVisibility(8);
            return;
        }
        viewById.setVisibility(0);
        final int n2 = (int)((this.columnWidth - this.getResources().getDimension(2131296323)) / 2.0f);
        ((ViewGroup$MarginLayoutParams)viewById.getLayoutParams()).setMargins(n + n2, 0, n2 + n, (int)this.getResources().getDimension(2131296528));
    }
    
    private void updateAppWasRestartedFlag(final Intent intent) {
        if (intent != null) {
            this.mAppWasRestarted = intent.getBooleanExtra("app_was_restarted", false);
            if (Log.isLoggable()) {
                Log.i("ProfileSelectionActivity", "Got new value for 'app was restorted' flag: " + this.mAppWasRestarted);
            }
        }
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new ProfileSelectionActivity$2(this);
    }
    
    @Override
    public IClientLogging$ModalView getUiScreen() {
        return IClientLogging$ModalView.profilesGate;
    }
    
    public boolean handleBackPressed() {
        boolean b = false;
        if (this.isProfileEditMode) {
            this.isProfileEditMode = false;
            this.refreshEditProfileModeViews();
            b = true;
        }
        return b;
    }
    
    @Override
    protected void handleNetworkErrorDialog() {
        if (this.profiles != null && this.profiles.size() > 1) {
            Log.d("ProfileSelectionActivity", "relaunch onhandleProfileSelectionResult failed");
            NetflixActivity.finishAllActivities((Context)this);
            this.startActivity(RelaunchActivity.createStartIntent(this, "handleNetwotkErrorDialog()"));
            return;
        }
        Log.d("ProfileSelectionActivity", "finish onhandleProfileSelectionResult failed");
        NetflixActivity.finishAllActivities((Context)this);
    }
    
    @Override
    protected void handleProfileActivated() {
        final long nanoTime = System.nanoTime();
        Log.d("ProfileSelectionActivity", "Restarting app, time: " + nanoTime);
        NetflixActivity.finishAllActivities((Context)this);
        this.startActivity(RelaunchActivity.createStartIntent(this, "handleProfileActivated() " + nanoTime).addFlags(67108864));
    }
    
    @Override
    protected void handleProfileReadyToSelect() {
        Log.v("ProfileSelectionActivity", "handleProfileReadyToSelect()");
        this.handleManagerReady(this.isLoading = false);
    }
    
    @Override
    protected void handleProfileSelectionResult(final int n, final String message) {
        final StatusCode statusCodeByValue = StatusCode.getStatusCodeByValue(n);
        if (statusCodeByValue == StatusCode.OK) {
            Log.v("ProfileSelectionActivity", "Profile selection was successful.  Parent class should finish this activity in a moment...");
            UserActionLogUtils.reportSelectProfileActionEnded((Context)this, IClientLogging$CompletionReason.success, IClientLogging$ModalView.homeScreen, null);
            PreferenceUtils.putBooleanPref((Context)this, "user_profile_was_selected", true);
            return;
        }
        final NetflixStatus netflixStatus = new NetflixStatus(statusCodeByValue);
        final UIError uiError = ConsolidatedLoggingUtils.createUIError(netflixStatus, message, ActionOnUIError.displayedError);
        netflixStatus.setMessage(message);
        netflixStatus.setDisplayMessage(true);
        UserActionLogUtils.reportSelectProfileActionEnded((Context)this, IClientLogging$CompletionReason.failed, this.getUiScreen(), uiError);
        this.reportError(netflixStatus, message);
        this.handleUserAgentErrors(netflixStatus, false);
    }
    
    @Override
    protected void handleProfilesListUpdated() {
        this.profiles = this.manager.getAllProfiles();
        if (Log.isLoggable()) {
            Log.d("ProfileSelectionActivity", "handleProfilesListUpdated: " + this.profiles.size());
        }
        this.adapter.notifyDataSetChanged();
        this.gridView.setAdapter((ListAdapter)this.adapter);
    }
    
    @Override
    protected boolean hasUpAction() {
        return false;
    }
    
    @Override
    public boolean isLoadingData() {
        return this.isLoading || this.profiles == null || this.profiles.size() <= 0;
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.columnWidth = this.getResources().getDimensionPixelSize(2131296319);
        this.setContentView(2130903222);
        this.leWrapper = new LoadingAndErrorWrapper(this.findViewById(2131624606), this.errorCallback);
        this.content = this.findViewById(2131624607);
        this.topTextHeader = (TextView)this.findViewById(2131624608);
        (this.gridView = (StaticGridView)this.findViewById(2131624609)).setOnItemClickListener(this.onAvatarClickListener);
        this.gridView.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new ProfileSelectionActivity$1(this));
        this.kidsLoadingScreen = this.findViewById(2131624258);
        this.mDestination = this.getIntent().getStringExtra("extra_destination");
        if (bundle == null) {
            this.logKidsEntryExit();
            this.shouldStartKids = this.getIntent().getBooleanExtra("extra_start_kids_bool", false);
        }
        else {
            this.isLoading = bundle.getBoolean("is_loading", false);
            this.isProfileEditMode = bundle.getBoolean("is_profile_edit_mode", false);
            this.isShowingKidsLoadingScreen = bundle.getBoolean("is_showing_kids_loading_screen", false);
            Log.v("ProfileSelectionActivity", "Recovered state, isLoading: " + this.isLoading + ", isShowingKidsLoadingScreen: " + this.isShowingKidsLoadingScreen);
            this.refreshEditProfileModeViews();
        }
        this.updateAppWasRestartedFlag(this.getIntent());
    }
    
    @Override
    protected void onCreateOptionsMenu(final Menu menu, final Menu menu2) {
        if (this.isLoading || this.isProfileEditMode) {
            return;
        }
        super.onCreateOptionsMenu(menu, menu2);
        final MenuItem add = menu.add(0, 2131623945, 0, (CharSequence)this.getString(2131165717));
        add.setShowAsAction(1);
        add.setIcon(2130837732);
        add.setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new ProfileSelectionActivity$3(this));
    }
    
    @Override
    protected void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Log.v("ProfileSelectionActivity", "Saving loading state: " + this.isLoading);
        bundle.putBoolean("is_loading", this.isLoading);
        bundle.putBoolean("is_showing_kids_loading_screen", this.kidsLoadingScreen.getVisibility() == 0);
        bundle.putBoolean("is_profile_edit_mode", this.isProfileEditMode);
    }
    
    @Override
    public void performUpAction() {
        if (this.isProfileEditMode) {
            this.isProfileEditMode = false;
            this.refreshEditProfileModeViews();
        }
    }
    
    @Override
    public boolean showSettingsInMenu() {
        return !this.isProfileEditMode;
    }
    
    @Override
    public boolean showSignOutInMenu() {
        return !this.isProfileEditMode;
    }
    
    protected void startChangeProfile(final UserProfile userProfile) {
        if (this.manager == null || !this.manager.isReady()) {
            Log.e("ProfileSelectionActivity", "Manager is not ready");
            UserActionLogUtils.reportSelectProfileActionEnded((Context)this, IClientLogging$CompletionReason.failed, this.getUiScreen(), ConsolidatedLoggingUtils.createUIError(CommonStatus.INTERNAL_ERROR, "", ActionOnUIError.handledSilently));
            return;
        }
        if (Log.isLoggable()) {
            Log.d("ProfileSelectionActivity", "Selecting profile: " + userProfile.getFirstName() + ", id: " + userProfile.getProfileGuid());
        }
        final UserProfile currentProfile = this.manager.getCurrentProfile();
        if (!KidsUtils.isKidsProfile(currentProfile)) {
            this.getSupportActionBar().hide();
        }
        if (!this.mAppWasRestarted && this.mDestination == null && currentProfile != null && StringUtils.safeEquals(currentProfile.getProfileGuid(), userProfile.getProfileGuid())) {
            Log.d("ProfileSelectionActivity", "Selected profile is the same as the current one - skipping profile change...");
            UserActionLogUtils.reportSelectProfileActionEnded((Context)this, IClientLogging$CompletionReason.failed, IClientLogging$ModalView.homeScreen, ConsolidatedLoggingUtils.createUIError(CommonStatus.INTERNAL_ERROR, "", ActionOnUIError.handledSilently));
            this.finish();
            return;
        }
        this.showLoadingViews(this.isLoading = true, this.canShowKidsLoadingScreen && userProfile.isKidsProfile());
        this.manager.selectProfile(userProfile.getProfileGuid());
        this.mAppWasRestarted = false;
    }
}
