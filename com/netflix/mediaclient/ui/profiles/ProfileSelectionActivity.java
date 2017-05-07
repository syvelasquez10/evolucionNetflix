// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.profiles;

import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.view.MenuItem;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.Menu;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.os.Bundle;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.ui.RelaunchActivity;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.view.ViewGroup$MarginLayoutParams;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import android.view.ViewPropertyAnimator;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.util.StringUtils;
import java.util.Iterator;
import android.widget.ListAdapter;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.servicemgr.UIViewLogging;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import android.content.Intent;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.app.Status;
import android.app.Activity;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.servicemgr.UserActionLogging;
import android.content.Context;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.Log;
import android.widget.AdapterView;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.model.user.UserProfile;
import java.util.List;
import android.widget.AdapterView$OnItemClickListener;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.StaticGridView;
import com.netflix.mediaclient.android.widget.ErrorWrapper;
import android.view.View;
import android.util.SparseIntArray;
import android.util.SparseArray;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class ProfileSelectionActivity extends NetflixActivity
{
    public static final int ALPHA_ANIMATION_DURATION_MS = 400;
    private static final float ALPHA_CONTENT_FADE = 0.2f;
    public static final String EXTRA_APP_WAS_RESTARTED = "app_was_restarted";
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
    private ProfileAvatarAdapter adapter;
    private int columnWidth;
    private View content;
    private final ErrorWrapper.Callback errorCallback;
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
    private boolean shouldShowKidsLoadingScreen;
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
        this.errorCallback = new ErrorWrapper.Callback() {
            @Override
            public void onRetryRequested() {
            }
        };
        this.onAvatarClickListener = (AdapterView$OnItemClickListener)new AdapterView$OnItemClickListener() {
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                if (ProfileSelectionActivity.this.profiles == null || n > ProfileSelectionActivity.this.profiles.size()) {
                    Log.d("ProfileSelectionActivity", "Invalid profiles set");
                    return;
                }
                if (n == ProfileSelectionActivity.this.profiles.size()) {
                    LogUtils.reportAddProfileActionStarted((Context)ProfileSelectionActivity.this, null, ProfileSelectionActivity.this.getUiScreen());
                    ProfileSelectionActivity.this.startActivity(ProfileDetailsActivity.getStartIntent((Context)ProfileSelectionActivity.this, null));
                    return;
                }
                if (!ProfileSelectionActivity.this.isProfileEditMode) {
                    final UserProfile userProfile = ProfileSelectionActivity.this.profiles.get(n);
                    LogUtils.reportSelectProfileActionStarted((Context)ProfileSelectionActivity.this, null, ProfileSelectionActivity.this.getUiScreen(), userProfile.getProfileId(), UserActionLogging.RememberProfile.userChoseToRemember);
                    ProfileSelectionActivity.this.startChangeProfile(userProfile);
                    ProfileSelectionActivity.this.invalidateOptionsMenu();
                    return;
                }
                if (ProfileSelectionActivity.this.profiles.get(n).getProfileId() == null) {
                    ProfileSelectionActivity.this.handleUserAgentErrors(ProfileSelectionActivity.this, CommonStatus.INTERNAL_ERROR);
                    return;
                }
                LogUtils.reportEditProfileActionStarted((Context)ProfileSelectionActivity.this, null, ProfileSelectionActivity.this.getUiScreen());
                ProfileSelectionActivity.this.startActivity(ProfileDetailsActivity.getStartIntent((Context)ProfileSelectionActivity.this, ProfileSelectionActivity.this.profiles.get(n).getProfileId()));
            }
        };
    }
    
    private void adjustGridViewMargins() {
        final int screenWidthInPixels = DeviceUtils.getScreenWidthInPixels((Context)this);
        final int n = this.columnWidth * this.numCols;
        final int n2 = (screenWidthInPixels - n) / 2;
        if (Log.isLoggable("ProfileSelectionActivity", 2)) {
            Log.v("ProfileSelectionActivity", String.format("screen: %d, grid: %d, padding: %d", screenWidthInPixels, n, n2));
        }
        this.gridView.setPadding(n2, 0, 0, 0);
        this.showPromoViewIfNeeded(n2);
    }
    
    public static Intent createStartIntent(final Context context) {
        return new Intent(context, (Class)ProfileSelectionActivity.class).addFlags(131072);
    }
    
    public static Intent createSwitchFromDeepLinking(final Activity activity, final IClientLogging.ModalView modalView) {
        if (modalView == null) {
            return createStartIntent((Context)activity);
        }
        return createStartIntent((Context)activity).putExtra("extra_destination", modalView.name());
    }
    
    public static Intent createSwitchFromKidsIntent(final Activity activity, final UIViewLogging.UIViewCommandName uiViewCommandName) {
        return createStartIntent((Context)activity).putExtra("extra_stop_kids_bool", true).putExtra("extra_kids_door_name", uiViewCommandName.toString());
    }
    
    public static Intent createSwitchToKidsIntent(final Activity activity, final UIViewLogging.UIViewCommandName uiViewCommandName) {
        return createStartIntent((Context)activity).putExtra("extra_start_kids_bool", true).putExtra("extra_kids_door_name", uiViewCommandName.toString());
    }
    
    private void handleManagerReady(final boolean b) {
        final List<? extends UserProfile> allProfiles = this.manager.getAllProfiles();
        if (allProfiles == null) {
            Log.w("ProfileSelectionActivity", "No profiles found for user!");
            this.manager.getClientLogging().getErrorLogging().logHandledException(new IllegalStateException("No profiles found for user!"));
        }
        else {
            this.shouldShowKidsLoadingScreen = KidsUtils.isUserInKopExperience(this.manager.getConfiguration());
            Log.v("ProfileSelectionActivity", "handleManagerReady(), isLoading: " + this.isLoading);
            final Iterator<? extends UserProfile> iterator = allProfiles.iterator();
            while (iterator.hasNext()) {
                Log.d("ProfileSelectionActivity", "profile: " + iterator.next());
            }
            this.profiles = allProfiles;
            this.adapter = new ProfileAvatarAdapter();
            this.gridView.setAdapter((ListAdapter)this.adapter);
            this.setupGridViewColumns();
            this.showContentView();
            if (this.isLoading) {
                if (Log.isLoggable("ProfileSelectionActivity", 3)) {
                    Log.d("ProfileSelectionActivity", "We're in loading state - showing loading view");
                }
                this.showLoadingViews(false, this.shouldShowKidsLoadingScreen && this.isShowingKidsLoadingScreen);
            }
            final boolean b2 = this.manager.getConfiguration() != null && this.manager.getConfiguration().getKidsOnPhoneConfiguration() != null && this.manager.getConfiguration().getKidsOnPhoneConfiguration().isKidsOnPhoneEnabled();
            Log.v("ProfileSelectionActivity", String.format("shouldAutoSelectProfile: %s, shouldStartKids: %s, isInKoPEnabled: %s", b, this.shouldStartKids, b2));
            if (b && this.shouldStartKids && b2) {
                int n = 0;
                UserProfile userProfile = null;
                for (final UserProfile userProfile2 : this.manager.getAllProfiles()) {
                    if (userProfile2.isKidsProfile()) {
                        userProfile = userProfile2;
                        ++n;
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
    
    private void logKidsEntryExit(final Intent intent) {
        final boolean booleanExtra = this.getIntent().getBooleanExtra("extra_start_kids_bool", false);
        final boolean booleanExtra2 = this.getIntent().getBooleanExtra("extra_stop_kids_bool", false);
        final String stringExtra = this.getIntent().getStringExtra("extra_kids_door_name");
        if ((Boolean.valueOf(booleanExtra) || Boolean.valueOf(booleanExtra2)) && StringUtils.isNotEmpty(stringExtra)) {
            LogUtils.reportUIViewCommandStarted((Context)this, UIViewLogging.UIViewCommandName.valueOf(stringExtra), IClientLogging.ModalView.homeScreen, null);
            LogUtils.reportUIViewCommandEnded((Context)this);
        }
    }
    
    private void refreshEditProfileModeViews() {
        if (this.isProfileEditMode) {
            this.getNetflixActionBar().setTitle(this.getResources().getString(2131493346));
        }
        final NetflixActionBar netflixActionBar = this.getNetflixActionBar();
        NetflixActionBar.LogoType logoType;
        if (this.isProfileEditMode) {
            logoType = NetflixActionBar.LogoType.GONE;
        }
        else {
            logoType = NetflixActionBar.LogoType.FULL_SIZE;
        }
        netflixActionBar.setLogoType(logoType);
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
                final View viewById = child.findViewById(2131165299);
                int visibility;
                if (this.isProfileEditMode) {
                    visibility = 0;
                }
                else {
                    visibility = 8;
                }
                viewById.setVisibility(visibility);
                final ViewPropertyAnimator animate2 = child.findViewById(2131165298).animate();
                float n2;
                if (this.isProfileEditMode) {
                    n2 = 0.2f;
                }
                else {
                    n2 = 1.0f;
                }
                animate2.alpha(n2).setDuration(400L).start();
            }
        }
        this.invalidateOptionsMenu();
    }
    
    private void reportError(final Status status, final String s) {
        LogUtils.reportNavigationActionEnded((Context)this, this.getUiScreen(), IClientLogging.CompletionReason.failed, LogUtils.createUIError(status, s, ActionOnUIError.displayedError));
        LogUtils.reportNavigationActionStarted((Context)this, null, this.getUiScreen());
    }
    
    private void setupGridViewColumns() {
        final int value = ((SparseIntArray)ProfileSelectionActivity.maxNumGridColumns.get(DeviceUtils.getBasicScreenOrientation((Context)this))).get(DeviceUtils.getScreenSizeCategory((Context)this));
        int count = this.adapter.getCount();
        if (count > 3) {
            count -= 2;
        }
        this.numCols = Math.min(count, value);
        if (Log.isLoggable("ProfileSelectionActivity", 2)) {
            Log.v("ProfileSelectionActivity", String.format("max cols: %d, limited cols: %d, num cols: %d", value, count, this.numCols));
        }
        this.gridView.setNumColumns(this.numCols);
        this.adjustGridViewMargins();
    }
    
    private void showContentView() {
        Log.v("ProfileSelectionActivity", "Showing content view...");
        this.leWrapper.hide(false);
        this.content.setEnabled(true);
        if (this.content.getVisibility() == 0) {
            if (this.content.getAlpha() < 1.0f) {
                this.content.animate().alpha(1.0f).setDuration(150L).start();
            }
        }
        else {
            AnimationUtils.showView(this.content, false);
        }
        if (this.kidsLoadingScreen.getVisibility() == 0) {
            this.setRequestedOrientation(-1);
            AnimationUtils.hideView(this.kidsLoadingScreen, true);
            this.getNetflixActionBar().setLogoType(NetflixActionBar.LogoType.FULL_SIZE);
        }
    }
    
    private void showLoadingViews(final boolean b, final boolean b2) {
        Log.v("ProfileSelectionActivity", "Showing loading view...");
        this.leWrapper.showLoadingView(false);
        this.content.setEnabled(false);
        if (b) {
            this.content.animate().alpha(0.2f).setDuration(400L).start();
        }
        else {
            this.content.setAlpha(0.2f);
        }
        if (b2) {
            this.setRequestedOrientation(1);
            AnimationUtils.showView(this.kidsLoadingScreen, false);
            this.getNetflixActionBar().setLogoType(NetflixActionBar.LogoType.GONE);
        }
    }
    
    private void showPromoViewIfNeeded(final int n) {
        final View viewById = this.findViewById(2131165613);
        if (PreferenceUtils.getBooleanPref((Context)this, "user_profile_was_selected", false) || this.profiles == null || this.profiles.size() > 1) {
            viewById.setVisibility(8);
            return;
        }
        viewById.setVisibility(0);
        final int n2 = (int)((this.columnWidth - this.getResources().getDimension(2131361943)) / 2.0f);
        ((ViewGroup$MarginLayoutParams)viewById.getLayoutParams()).setMargins(n + n2, 0, n + n2, (int)this.getResources().getDimension(2131361862));
    }
    
    private void updateAppWasRestartedFlag(final Intent intent) {
        if (intent != null) {
            this.mAppWasRestarted = intent.getBooleanExtra("app_was_restarted", false);
            if (Log.isLoggable("ProfileSelectionActivity", 4)) {
                Log.i("ProfileSelectionActivity", "Got new value for 'app was restorted' flag: " + this.mAppWasRestarted);
            }
        }
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new ManagerStatusListener() {
            @Override
            public void onManagerReady(final ServiceManager serviceManager, final Status status) {
                ProfileSelectionActivity.this.manager = serviceManager;
                ProfileSelectionActivity.this.handleManagerReady(true);
            }
            
            @Override
            public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
                ProfileSelectionActivity.this.manager = null;
            }
        };
    }
    
    @Override
    public IClientLogging.ModalView getUiScreen() {
        return IClientLogging.ModalView.profilesGate;
    }
    
    public boolean handleBackPressed() {
        boolean isProfileEditMode = false;
        if (this.isProfileEditMode) {
            if (!this.isProfileEditMode) {
                isProfileEditMode = true;
            }
            this.isProfileEditMode = isProfileEditMode;
            this.refreshEditProfileModeViews();
            return true;
        }
        return false;
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
            LogUtils.reportSelectProfileActionEnded((Context)this, IClientLogging.CompletionReason.success, IClientLogging.ModalView.homeScreen, statusCodeByValue);
            PreferenceUtils.putBooleanPref((Context)this, "user_profile_was_selected", true);
            return;
        }
        LogUtils.reportSelectProfileActionEnded((Context)this, IClientLogging.CompletionReason.failed, this.getUiScreen(), statusCodeByValue);
        final NetflixStatus netflixStatus = new NetflixStatus(statusCodeByValue);
        netflixStatus.setMessage(message);
        netflixStatus.setDisplayMessage(true);
        this.reportError(netflixStatus, message);
        this.handleUserAgentErrors(this, netflixStatus);
    }
    
    @Override
    protected void handleProfilesListUpdated() {
        this.profiles = this.manager.getAllProfiles();
        if (Log.isLoggable("ProfileSelectionActivity", 3)) {
            Log.d("ProfileSelectionActivity", "handleProfilesListUpdated: " + this.profiles.size());
        }
        this.adapter.notifyDataSetChanged();
        this.gridView.setAdapter((ListAdapter)this.adapter);
    }
    
    @Override
    protected boolean hasUpAction() {
        return false;
    }
    
    public boolean isLoadingData() {
        return this.isLoading || this.profiles == null || this.profiles.size() <= 0;
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.columnWidth = this.getResources().getDimensionPixelSize(2131361931);
        this.setContentView(2130903165);
        this.leWrapper = new LoadingAndErrorWrapper(this.findViewById(2131165609), this.errorCallback);
        this.content = this.findViewById(2131165610);
        this.topTextHeader = (TextView)this.findViewById(2131165611);
        (this.gridView = (StaticGridView)this.findViewById(2131165612)).setOnItemClickListener(this.onAvatarClickListener);
        this.gridView.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new ViewTreeObserver$OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                ProfileSelectionActivity.this.adjustGridViewMargins();
            }
        });
        this.kidsLoadingScreen = this.findViewById(2131165418);
        this.mDestination = this.getIntent().getStringExtra("extra_destination");
        if (bundle == null) {
            this.logKidsEntryExit(this.getIntent());
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
        final MenuItem add = menu.add(0, 2131165246, 0, (CharSequence)this.getString(2131493345));
        add.setShowAsAction(1);
        add.setIcon(2130837674);
        add.setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new MenuItem$OnMenuItemClickListener() {
            public boolean onMenuItemClick(final MenuItem menuItem) {
                ProfileSelectionActivity.this.isProfileEditMode = !ProfileSelectionActivity.this.isProfileEditMode;
                ProfileSelectionActivity.this.refreshEditProfileModeViews();
                return true;
            }
        });
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
    protected boolean showSettingsInMenu() {
        return !this.isProfileEditMode;
    }
    
    @Override
    protected boolean showSignOutInMenu() {
        return !this.isProfileEditMode;
    }
    
    protected void startChangeProfile(final UserProfile userProfile) {
        if (this.manager == null || !this.manager.isReady()) {
            Log.e("ProfileSelectionActivity", "Manager is not ready");
            LogUtils.reportSelectProfileActionEnded((Context)this, IClientLogging.CompletionReason.failed, this.getUiScreen(), StatusCode.INTERNAL_ERROR);
            return;
        }
        if (Log.isLoggable("ProfileSelectionActivity", 3)) {
            Log.d("ProfileSelectionActivity", "Selecting profile: " + userProfile.getFirstName() + ", id: " + userProfile.getProfileId());
        }
        final UserProfile currentProfile = this.manager.getCurrentProfile();
        if (!this.mAppWasRestarted && this.mDestination == null && currentProfile != null && StringUtils.safeEquals(currentProfile.getProfileId(), userProfile.getProfileId())) {
            Log.d("ProfileSelectionActivity", "Selected profile is the same as the current one - skipping profile change...");
            LogUtils.reportSelectProfileActionEnded((Context)this, IClientLogging.CompletionReason.failed, IClientLogging.ModalView.homeScreen, StatusCode.INTERNAL_ERROR);
            this.finish();
            return;
        }
        this.showLoadingViews(this.isLoading = true, this.shouldShowKidsLoadingScreen && userProfile.isKidsProfile());
        this.manager.selectProfile(userProfile.getProfileId());
        this.mAppWasRestarted = false;
    }
    
    private static class Holder
    {
        private final AdvancedImageView img;
        private final TextView title;
        private final View topEditImg;
        
        public Holder(final AdvancedImageView img, final TextView title, final View topEditImg) {
            this.img = img;
            this.title = title;
            this.topEditImg = topEditImg;
            img.setPressedStateHandlerEnabled(false);
        }
    }
    
    private class ProfileAvatarAdapter extends BaseAdapter
    {
        public int getCount() {
            int size;
            final int n = size = ProfileSelectionActivity.this.profiles.size();
            if (n < 5) {
                size = n + 1;
            }
            return size;
        }
        
        public UserProfile getItem(final int n) {
            if (n < ProfileSelectionActivity.this.profiles.size()) {
                return ProfileSelectionActivity.this.profiles.get(n);
            }
            return null;
        }
        
        public long getItemId(final int n) {
            return n;
        }
        
        public View getView(int visibility, final View view, final ViewGroup viewGroup) {
            View inflate = view;
            if (view == null) {
                inflate = ProfileSelectionActivity.this.getLayoutInflater().inflate(2130903166, viewGroup, false);
                inflate.setTag((Object)new Holder((AdvancedImageView)inflate.findViewById(2131165298), (TextView)inflate.findViewById(2131165614), inflate.findViewById(2131165299)));
            }
            final Holder holder = (Holder)inflate.getTag();
            final UserProfile item = this.getItem(visibility);
            if (visibility == ProfileSelectionActivity.this.profiles.size()) {
                holder.img.setImageResource(2130837828);
                holder.title.setText(2131493337);
                holder.topEditImg.setVisibility(8);
                return inflate;
            }
            holder.title.setText((CharSequence)item.getProfileName());
            NetflixActivity.getImageLoader((Context)ProfileSelectionActivity.this).showImg(holder.img, item.getAvatarUrl(), IClientLogging.AssetType.profileAvatar, item.getFirstName(), true, true);
            final View access$1000 = holder.topEditImg;
            if (ProfileSelectionActivity.this.isProfileEditMode) {
                visibility = 0;
            }
            else {
                visibility = 8;
            }
            access$1000.setVisibility(visibility);
            final AdvancedImageView access$1001 = holder.img;
            float alpha;
            if (ProfileSelectionActivity.this.isProfileEditMode) {
                alpha = 0.2f;
            }
            else {
                alpha = 1.0f;
            }
            access$1001.setAlpha(alpha);
            return inflate;
        }
        
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
            ProfileSelectionActivity.this.setupGridViewColumns();
        }
    }
}
