// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.profiles;

import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.os.Bundle;
import com.netflix.mediaclient.ui.RelaunchActivity;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.util.StringUtils;
import java.util.Iterator;
import android.widget.ListAdapter;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.servicemgr.UIViewLogging;
import android.content.Intent;
import android.app.Activity;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.Log;
import android.widget.AdapterView;
import com.netflix.mediaclient.servicemgr.UserProfile;
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
    private static final float ALPHA_CONTENT_FADE = 0.2f;
    private static final String EXTRA_KIDS_DOOR_NAME = "extra_kids_door_name";
    private static final String EXTRA_START_KIDS_BOOL = "extra_start_kids_bool";
    private static final String EXTRA_STOP_KIDS_BOOL = "extra_stop_kids_bool";
    private static final boolean HANG_ON_KIDS_LOADING_SCREEN = false;
    private static final String KEY_IS_LOADING = "is_loading";
    private static final String KEY_IS_SHOWING_KIDS_LOADING_SCREEN = "is_showing_kids_loading_screen";
    private static final String TAG = "ProfileSelectionActivity";
    private static final SparseArray<SparseIntArray> maxNumGridColumns;
    private ProfileAvatarAdapter adapter;
    private int columnWidth;
    private View content;
    private final ErrorWrapper.Callback errorCallback;
    private StaticGridView gridView;
    private boolean isLoading;
    private boolean isShowingKidsLoadingScreen;
    private View kidsLoadingScreen;
    private LoadingAndErrorWrapper leWrapper;
    private ServiceManager manager;
    private int numCols;
    private final AdapterView$OnItemClickListener onAvatarClickListener;
    private List<? extends UserProfile> profiles;
    private boolean shouldShowKidsLoadingScreen;
    private boolean shouldStartKids;
    
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
                ProfileSelectionActivity.this.startChangeProfile(ProfileSelectionActivity.this.profiles.get(n));
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
    }
    
    public static Intent createStartIntent(final Activity activity) {
        return new Intent((Context)activity, (Class)ProfileSelectionActivity.class).addFlags(131072);
    }
    
    public static Intent createSwitchFromKidsIntent(final Activity activity, final UIViewLogging.UIViewCommandName uiViewCommandName) {
        return createStartIntent(activity).putExtra("extra_stop_kids_bool", true).putExtra("extra_kids_door_name", uiViewCommandName.toString());
    }
    
    public static Intent createSwitchToKidsIntent(final Activity activity, final UIViewLogging.UIViewCommandName uiViewCommandName) {
        return createStartIntent(activity).putExtra("extra_start_kids_bool", true).putExtra("extra_kids_door_name", uiViewCommandName.toString());
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
            if (!this.isLoading && allProfiles.size() == 1) {
                if (Log.isLoggable("ProfileSelectionActivity", 3)) {
                    Log.d("ProfileSelectionActivity", "Only one user profile, showing loading view");
                }
                final UserProfile userProfile = (UserProfile)allProfiles.get(0);
                this.showLoadingViews(this.isLoading = true, this.shouldShowKidsLoadingScreen && userProfile.isKidsProfile());
                if (b) {
                    if (Log.isLoggable("ProfileSelectionActivity", 3)) {
                        Log.d("ProfileSelectionActivity", "Auto-selecting profile: " + userProfile.getFirstName() + ", id: " + userProfile.getProfileId());
                    }
                    this.manager.selectProfile(userProfile.getProfileId());
                }
            }
            else {
                if (Log.isLoggable("ProfileSelectionActivity", 3)) {
                    Log.d("ProfileSelectionActivity", "More than one profile");
                }
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
            }
            Log.v("ProfileSelectionActivity", String.format("shouldAutoSelectProfile: %s, shouldStartKids: %s, isInKoPEnabled: %s", b, this.shouldStartKids, this.manager.getConfiguration().getKidsOnPhoneConfiguration().isKidsOnPhoneEnabled()));
            if (b && this.shouldStartKids && this.manager.getConfiguration().getKidsOnPhoneConfiguration().isKidsOnPhoneEnabled()) {
                int n = 0;
                UserProfile userProfile2 = null;
                for (final UserProfile userProfile3 : this.manager.getAllProfiles()) {
                    if (userProfile3.isKidsProfile()) {
                        userProfile2 = userProfile3;
                        ++n;
                    }
                }
                if (n == 1) {
                    Log.v("ProfileSelectionActivity", "Found one Kids profile - switching to: " + userProfile2.getProfileName());
                    this.startChangeProfile(userProfile2);
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
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new ManagerStatusListener() {
            @Override
            public void onManagerReady(final ServiceManager serviceManager, final int n) {
                ProfileSelectionActivity.this.manager = serviceManager;
                ProfileSelectionActivity.this.handleManagerReady(true);
            }
            
            @Override
            public void onManagerUnavailable(final ServiceManager serviceManager, final int n) {
                ProfileSelectionActivity.this.manager = null;
            }
        };
    }
    
    @Override
    public IClientLogging.ModalView getUiScreen() {
        return IClientLogging.ModalView.profilesGate;
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
    protected void handleProfileSelectionResult(final int n, final String s) {
        if (n == 0) {
            Log.v("ProfileSelectionActivity", "Profile selection was successful.  Parent class should finish this activity in a moment...");
            return;
        }
        this.handleUserAgentErrors(this, n, s);
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
        this.columnWidth = this.getResources().getDimensionPixelSize(2131361866);
        this.setContentView(2130903155);
        this.leWrapper = new LoadingAndErrorWrapper(this.findViewById(2131165574), this.errorCallback);
        this.content = this.findViewById(2131165575);
        (this.gridView = (StaticGridView)this.findViewById(2131165577)).setOnItemClickListener(this.onAvatarClickListener);
        this.gridView.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new ViewTreeObserver$OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                ProfileSelectionActivity.this.adjustGridViewMargins();
            }
        });
        this.kidsLoadingScreen = this.findViewById(2131165395);
        if (bundle == null) {
            this.logKidsEntryExit(this.getIntent());
            this.shouldStartKids = this.getIntent().getBooleanExtra("extra_start_kids_bool", false);
            return;
        }
        this.isLoading = bundle.getBoolean("is_loading", false);
        this.isShowingKidsLoadingScreen = bundle.getBoolean("is_showing_kids_loading_screen", false);
        Log.v("ProfileSelectionActivity", "Recovered state, isLoading: " + this.isLoading + ", isShowingKidsLoadingScreen: " + this.isShowingKidsLoadingScreen);
    }
    
    @Override
    protected void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Log.v("ProfileSelectionActivity", "Saving loading state: " + this.isLoading);
        bundle.putBoolean("is_loading", this.isLoading);
        bundle.putBoolean("is_showing_kids_loading_screen", this.kidsLoadingScreen.getVisibility() == 0);
    }
    
    @Override
    public void performUpAction() {
    }
    
    protected void startChangeProfile(final UserProfile userProfile) {
        if (this.manager == null || !this.manager.isReady()) {
            Log.e("ProfileSelectionActivity", "Manager is not ready");
            return;
        }
        if (Log.isLoggable("ProfileSelectionActivity", 3)) {
            Log.d("ProfileSelectionActivity", "Selecting profile: " + userProfile.getFirstName() + ", id: " + userProfile.getProfileId());
        }
        final UserProfile currentProfile = this.manager.getCurrentProfile();
        if (currentProfile != null && StringUtils.safeEquals(currentProfile.getProfileId(), userProfile.getProfileId())) {
            Log.d("ProfileSelectionActivity", "Selected profile is the same as the current one - skipping profile change...");
            this.finish();
            return;
        }
        this.showLoadingViews(this.isLoading = true, this.shouldShowKidsLoadingScreen && userProfile.isKidsProfile());
        this.manager.selectProfile(userProfile.getProfileId());
    }
    
    private static class Holder
    {
        private final AdvancedImageView img;
        private final TextView title;
        
        public Holder(final AdvancedImageView img, final TextView title) {
            this.img = img;
            this.title = title;
            img.setPressedStateHandlerEnabled(false);
        }
    }
    
    private class ProfileAvatarAdapter extends BaseAdapter
    {
        public int getCount() {
            return ProfileSelectionActivity.this.profiles.size();
        }
        
        public UserProfile getItem(final int n) {
            return ProfileSelectionActivity.this.profiles.get(n);
        }
        
        public long getItemId(final int n) {
            return n;
        }
        
        public View getView(final int n, final View view, final ViewGroup viewGroup) {
            View inflate = view;
            if (view == null) {
                inflate = ProfileSelectionActivity.this.getLayoutInflater().inflate(2130903156, (ViewGroup)null, false);
                inflate.setTag((Object)new Holder((AdvancedImageView)inflate.findViewById(2131165579), (TextView)inflate.findViewById(2131165580)));
            }
            final Holder holder = (Holder)inflate.getTag();
            final UserProfile item = this.getItem(n);
            holder.title.setText((CharSequence)item.getProfileName());
            NetflixActivity.getImageLoader((Context)ProfileSelectionActivity.this).showImg(holder.img, item.getAvatarUrl(), IClientLogging.AssetType.profileAvatar, item.getFirstName(), true, true);
            return inflate;
        }
        
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
            ProfileSelectionActivity.this.setupGridViewColumns();
        }
    }
}
