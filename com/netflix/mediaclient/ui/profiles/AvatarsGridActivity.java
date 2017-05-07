// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.profiles;

import java.util.ArrayList;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.FrameLayout$LayoutParams;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.android.widget.NetflixActionBar$LogoType;
import android.os.Parcelable;
import android.content.Intent;
import android.content.Context;
import com.netflix.mediaclient.android.app.Status;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.widget.GridView;
import com.netflix.mediaclient.service.webclient.model.leafs.AvatarInfo;
import java.util.List;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class AvatarsGridActivity extends NetflixActivity
{
    private static final String BUNDLE_EXTRA_ICONS_LIST = "extra_icons_list";
    private static final String EXTRA_CURRENT_AVATAR = "extra_current_avatar";
    private static final String EXTRA_DEFAULT_AVATAR = "extra_default_avatar";
    private static final String TAG = "AvatarsGridActivity";
    private final ErrorWrapper$Callback errorCallback;
    private AvatarsGridActivity$ProfileAvatarAdapter mAdapter;
    private List<AvatarInfo> mAvatars;
    private AvatarInfo mCurrentAvatar;
    private AvatarInfo mDefaultAvatar;
    private GridView mGridView;
    private LoadingAndErrorWrapper mLoadingWrapper;
    private int mSelectedIconPos;
    private ServiceManager mServiceManager;
    
    public AvatarsGridActivity() {
        this.errorCallback = new AvatarsGridActivity$2(this);
    }
    
    public static Intent getStartIntent(final Context context, final AvatarInfo avatarInfo, final AvatarInfo avatarInfo2) {
        final Intent intent = new Intent(context, (Class)AvatarsGridActivity.class);
        if (avatarInfo != null && avatarInfo2 != null) {
            intent.putExtra("extra_default_avatar", (Parcelable)avatarInfo);
            intent.putExtra("extra_current_avatar", (Parcelable)avatarInfo2);
            return intent;
        }
        throw new RuntimeException("You must specify default and current avatars to launch this activity");
    }
    
    private void initUI() {
        this.setContentView(2130903067);
        this.mGridView = (GridView)this.findViewById(2131165298);
        this.mLoadingWrapper = new LoadingAndErrorWrapper(this.findViewById(2131165297), this.errorCallback);
        this.mAdapter = new AvatarsGridActivity$ProfileAvatarAdapter(this, null);
        final NetflixActionBar netflixActionBar = this.getNetflixActionBar();
        if (netflixActionBar != null) {
            netflixActionBar.setTitle(this.getResources().getString(2131493313));
            netflixActionBar.setLogoType(NetflixActionBar$LogoType.GONE);
        }
    }
    
    private void refreshCurrentIconSelection() {
        int mSelectedIconPos;
        if (this.mAvatars.contains(this.mCurrentAvatar)) {
            mSelectedIconPos = this.mAvatars.indexOf(this.mCurrentAvatar) + 1;
        }
        else {
            mSelectedIconPos = 0;
        }
        this.mSelectedIconPos = mSelectedIconPos;
    }
    
    private void showLoading(final boolean b, final boolean b2) {
        if (b) {
            this.mLoadingWrapper.showLoadingView(true);
            return;
        }
        this.mLoadingWrapper.hide(true);
    }
    
    private void updateTopGridViewMargin() {
        int topMargin = (int)this.getResources().getDimension(2131361954);
        if (DeviceUtils.isNotTabletByContext((Context)this)) {
            topMargin += ViewUtils.getDefaultActionBarHeight((Context)this);
        }
        ((FrameLayout$LayoutParams)this.mGridView.getLayoutParams()).topMargin = topMargin;
    }
    
    private void updateUI() {
        boolean b;
        if (this.mServiceManager != null) {
            b = true;
        }
        else {
            b = false;
        }
        if (!b || this.mAvatars == null) {
            this.showLoading(true, false);
            return;
        }
        this.showLoading(false, true);
        this.updateTopGridViewMargin();
        this.mGridView.setAdapter((ListAdapter)this.mAdapter);
        this.mGridView.setOnItemClickListener((AdapterView$OnItemClickListener)new AvatarsGridActivity$3(this));
        this.mGridView.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new AvatarsGridActivity$4(this));
        this.mAdapter.notifyDataSetChanged();
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new AvatarsGridActivity$1(this);
    }
    
    @Override
    public IClientLogging$ModalView getUiScreen() {
        return IClientLogging$ModalView.profilesGate;
    }
    
    @Override
    protected void handleProfilesListUpdated() {
        if (this.mServiceManager != null) {
            this.mServiceManager.fetchAvailableAvatarsList(new AvatarsGridActivity$AvatarsFetchedCallback(this, null));
        }
    }
    
    @Override
    public boolean isLoadingData() {
        return false;
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        final Intent intent = this.getIntent();
        if (intent != null && intent.hasExtra("extra_default_avatar") && intent.hasExtra("extra_current_avatar")) {
            this.mDefaultAvatar = (AvatarInfo)intent.getParcelableExtra("extra_default_avatar");
            this.mCurrentAvatar = (AvatarInfo)intent.getParcelableExtra("extra_current_avatar");
            this.mSelectedIconPos = 0;
        }
        if (bundle != null && bundle.containsKey("extra_icons_list")) {
            this.mAvatars = (List<AvatarInfo>)bundle.getParcelableArrayList("extra_icons_list");
        }
        this.initUI();
        this.updateUI();
    }
    
    @Override
    protected void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.mAvatars != null) {
            bundle.putParcelableArrayList("extra_icons_list", (ArrayList)this.mAvatars);
        }
    }
    
    @Override
    public void performUpAction() {
        this.setResult(0);
        this.finish();
    }
    
    @Override
    protected boolean shouldAttachToolbar() {
        return DeviceUtils.isNotTabletByContext((Context)this);
    }
    
    @Override
    protected boolean showAboutInMenu() {
        return false;
    }
    
    @Override
    protected boolean showSettingsInMenu() {
        return false;
    }
    
    @Override
    protected boolean showSignOutInMenu() {
        return false;
    }
}
