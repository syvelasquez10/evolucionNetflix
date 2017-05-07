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
import com.netflix.mediaclient.ui.ServiceErrorsHandler;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.android.widget.AccessibilityRunnable;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.UpdateDialog;
import com.netflix.mediaclient.android.widget.AlertDialogFactory;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.ui.LaunchActivity;
import java.util.Iterator;
import android.widget.ListAdapter;
import android.content.Intent;
import android.app.Activity;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.util.StringUtils;
import android.widget.AdapterView;
import android.content.Context;
import com.netflix.mediaclient.ui.login.LogoutActivity;
import com.netflix.mediaclient.Log;
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
    private static final String KEY_IS_LOADING = "is_loading";
    private static final String TAG = "ProfileSelectionActivity";
    private static final SparseArray<SparseIntArray> maxNumGridColumns;
    private ProfileAvatarAdapter adapter;
    private int columnWidth;
    private View content;
    private final ErrorWrapper.Callback errorCallback;
    private StaticGridView gridView;
    private boolean isLoading;
    private LoadingAndErrorWrapper leWrapper;
    private final Runnable logoutUser;
    private ServiceManager manager;
    private int numCols;
    private final AdapterView$OnItemClickListener onAvatarClickListener;
    private List<? extends UserProfile> profiles;
    
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
        this.logoutUser = new Runnable() {
            @Override
            public void run() {
                Log.d("ProfileSelectionActivity", "Restarting app, time: " + System.nanoTime());
                ProfileSelectionActivity.this.finish();
                ProfileSelectionActivity.this.startActivity(LogoutActivity.create((Context)ProfileSelectionActivity.this));
            }
        };
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
                if (ProfileSelectionActivity.this.manager == null || !ProfileSelectionActivity.this.manager.isReady()) {
                    Log.d("ProfileSelectionActivity", "Manager is not ready");
                    return;
                }
                final UserProfile userProfile = ProfileSelectionActivity.this.profiles.get(n);
                if (Log.isLoggable("ProfileSelectionActivity", 3)) {
                    Log.d("ProfileSelectionActivity", "Selecting profile: " + userProfile.getFirstName() + ", id: " + userProfile.getProfileId());
                }
                final UserProfile currentProfile = ProfileSelectionActivity.this.manager.getCurrentProfile();
                if (currentProfile != null && StringUtils.safeEquals(currentProfile.getProfileId(), userProfile.getProfileId())) {
                    Log.d("ProfileSelectionActivity", "Selected profile is the same as the current one - skipping profile change...");
                    ProfileSelectionActivity.this.finish();
                    return;
                }
                ProfileSelectionActivity.this.isLoading = true;
                ProfileSelectionActivity.this.showLoadingView(true);
                ProfileSelectionActivity.this.manager.selectProfile(userProfile.getProfileId());
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
    
    private void handleManagerReady(final boolean b) {
        final List<? extends UserProfile> allProfiles = this.manager.getAllProfiles();
        if (allProfiles == null) {
            Log.w("ProfileSelectionActivity", "No profiles found for user!");
            this.manager.getClientLogging().getErrorLogging().logHandledException(new IllegalStateException("No profiles found for user!"));
        }
        else {
            Log.v("ProfileSelectionActivity", "handleManagerReady()");
            final Iterator<? extends UserProfile> iterator = allProfiles.iterator();
            while (iterator.hasNext()) {
                Log.d("ProfileSelectionActivity", "profile: " + iterator.next());
            }
            this.profiles = allProfiles;
            if (allProfiles.size() == 1) {
                final UserProfile userProfile = (UserProfile)allProfiles.get(0);
                if (Log.isLoggable("ProfileSelectionActivity", 3)) {
                    Log.d("ProfileSelectionActivity", "Auto-selecting profile: " + userProfile.getFirstName() + ", id: " + userProfile.getProfileId());
                }
                this.showLoadingView(this.isLoading = true);
                if (b) {
                    this.manager.selectProfile(userProfile.getProfileId());
                }
            }
            else {
                this.adapter = new ProfileAvatarAdapter();
                this.gridView.setAdapter((ListAdapter)this.adapter);
                this.setupGridViewColumns();
                this.showContentView();
                if (this.isLoading) {
                    this.showLoadingView(false);
                }
            }
        }
    }
    
    private void handleNetwotkErrorDialog() {
        if (this.profiles != null && this.profiles.size() > 1) {
            Log.d("ProfileSelectionActivity", "relaunch onhandleProfileSelectionResult failed");
            NetflixActivity.finishAllActivities((Context)this);
            this.startActivity(LaunchActivity.createStartIntent(this, "handleNetwotkErrorDialog()"));
            return;
        }
        Log.d("ProfileSelectionActivity", "finish onhandleProfileSelectionResult failed");
        NetflixActivity.finishAllActivities((Context)this);
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
            return;
        }
        AnimationUtils.showView(this.content, false);
    }
    
    private void showDialog(final String s, final Runnable runnable, final boolean b) {
        final UpdateDialog.Builder dialog = AlertDialogFactory.createDialog((Context)this, this.handler, new AlertDialogFactory.AlertDialogDescriptor(null, s, this.getString(17039370), runnable));
        if (!b) {
            this.displayDialog(dialog);
        }
        else if (!this.destroyed()) {
            dialog.show();
        }
    }
    
    private void showLoadingView(final boolean b) {
        Log.v("ProfileSelectionActivity", "Showing loading view...");
        this.leWrapper.showLoadingView(false);
        this.content.setEnabled(false);
        if (b) {
            this.content.animate().alpha(0.2f).setDuration(400L).start();
            return;
        }
        this.content.setAlpha(0.2f);
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
    public AccessibilityRunnable createUpActionRunnable() {
        return null;
    }
    
    @Override
    public IClientLogging.ModalView getUiScreen() {
        return IClientLogging.ModalView.profilesGate;
    }
    
    @Override
    protected void handleProfileActivated() {
        final long nanoTime = System.nanoTime();
        Log.d("ProfileSelectionActivity", "Restarting app, time: " + nanoTime);
        NetflixActivity.finishAllActivities((Context)this);
        this.startActivity(LaunchActivity.createStartIntent(this, "handleProfileActivated() " + nanoTime).addFlags(67108864));
    }
    
    @Override
    protected void handleProfileReadyToSelect() {
        Log.v("ProfileSelectionActivity", "handleProfileReadyToSelect()");
        this.handleManagerReady(this.isLoading = false);
    }
    
    @Override
    protected void handleProfileSelectionResult(final int n, String s) {
        if (n == 0) {
            Log.v("ProfileSelectionActivity", "Profile selection was successful.  Parent class should finish this activity in a moment...");
            this.isLoading = false;
            return;
        }
        if (s == null) {
            s = "";
        }
        switch (n) {
            default: {
                this.showDialog(String.format("%s ( %d )", this.getString(2131493207), n), null, false);
            }
            case -202: {
                this.showDialog(s, null, false);
            }
            case -207:
            case -203: {
                this.showDialog(String.format("%s ( %d )", this.getString(2131493211), n), this.logoutUser, true);
            }
            case -208: {
                ServiceErrorsHandler.handleManagerResponse(this, -5);
            }
            case -211:
            case -210:
            case -209:
            case -206:
            case -205:
            case -204:
            case -201:
            case -200: {
                this.showDialog(String.format("%s ( %d )", this.getString(2131493203), n), null, false);
            }
            case -3: {
                this.showDialog(this.getString(2131493207) + " (" + n + ")", new Runnable() {
                    @Override
                    public void run() {
                        ProfileSelectionActivity.this.handleNetwotkErrorDialog();
                    }
                }, true);
            }
        }
    }
    
    public boolean isLoadingData() {
        return this.isLoading || this.profiles == null || this.profiles.size() <= 0;
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.columnWidth = this.getResources().getDimensionPixelSize(2131361845);
        this.setContentView(2130903107);
        this.leWrapper = new LoadingAndErrorWrapper(this.findViewById(2131099897), this.errorCallback);
        this.content = this.findViewById(2131099898);
        (this.gridView = (StaticGridView)this.findViewById(2131099900)).setOnItemClickListener(this.onAvatarClickListener);
        this.gridView.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new ViewTreeObserver$OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                ProfileSelectionActivity.this.adjustGridViewMargins();
            }
        });
        if (bundle != null) {
            this.isLoading = bundle.getBoolean("is_loading", false);
            Log.v("ProfileSelectionActivity", "Recovered loading state: " + this.isLoading);
        }
    }
    
    @Override
    protected void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Log.v("ProfileSelectionActivity", "Saving loading state: " + this.isLoading);
        bundle.putBoolean("is_loading", this.isLoading);
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
                inflate = ProfileSelectionActivity.this.getLayoutInflater().inflate(2130903108, (ViewGroup)null, false);
                inflate.setTag((Object)new Holder((AdvancedImageView)inflate.findViewById(2131099902), (TextView)inflate.findViewById(2131099903)));
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
