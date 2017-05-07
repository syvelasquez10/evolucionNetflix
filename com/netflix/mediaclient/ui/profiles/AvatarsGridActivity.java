// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.profiles;

import android.content.res.Resources;
import android.widget.ImageView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;
import java.util.ArrayList;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.ListAdapter;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
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
import com.netflix.mediaclient.android.widget.ErrorWrapper;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class AvatarsGridActivity extends NetflixActivity
{
    private static final String BUNDLE_EXTRA_ICONS_LIST = "extra_icons_list";
    private static final String EXTRA_CURRENT_AVATAR = "extra_current_avatar";
    private static final String EXTRA_DEFAULT_AVATAR = "extra_default_avatar";
    private static final String TAG = "AvatarsGridActivity";
    private final ErrorWrapper.Callback errorCallback;
    private ProfileAvatarAdapter mAdapter;
    private List<AvatarInfo> mAvatars;
    private AvatarInfo mCurrentAvatar;
    private AvatarInfo mDefaultAvatar;
    private GridView mGridView;
    private LoadingAndErrorWrapper mLoadingWrapper;
    private int mSelectedIconPos;
    private ServiceManager mServiceManager;
    
    public AvatarsGridActivity() {
        this.errorCallback = new ErrorWrapper.Callback() {
            @Override
            public void onRetryRequested() {
            }
        };
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
        this.setContentView(2130903066);
        this.mGridView = (GridView)this.findViewById(2131165297);
        this.mLoadingWrapper = new LoadingAndErrorWrapper(this.findViewById(2131165296), this.errorCallback);
        this.mAdapter = new ProfileAvatarAdapter();
        final NetflixActionBar netflixActionBar = this.getNetflixActionBar();
        if (netflixActionBar != null) {
            netflixActionBar.setTitle(this.getResources().getString(2131493350));
            netflixActionBar.setLogoType(NetflixActionBar.LogoType.GONE);
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
        this.mGridView.setAdapter((ListAdapter)this.mAdapter);
        this.mGridView.setOnItemClickListener((AdapterView$OnItemClickListener)new AdapterView$OnItemClickListener() {
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                final Intent intent = new Intent();
                AvatarInfo access$500;
                if (n == 0) {
                    access$500 = AvatarsGridActivity.this.mDefaultAvatar;
                }
                else {
                    access$500 = AvatarsGridActivity.this.mAvatars.get(n - 1);
                }
                intent.putExtra("avatar_name", (Parcelable)access$500);
                AvatarsGridActivity.this.setResult(-1, intent);
                AvatarsGridActivity.this.finish();
            }
        });
        this.mGridView.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new ViewTreeObserver$OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                AvatarsGridActivity.this.mAdapter.setupGridViewColumns();
            }
        });
        this.mAdapter.notifyDataSetChanged();
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new ManagerStatusListener() {
            @Override
            public void onManagerReady(final ServiceManager serviceManager, final Status status) {
                Log.d("AvatarsGridActivity", "Manager is here!");
                AvatarsGridActivity.this.mServiceManager = serviceManager;
                if (AvatarsGridActivity.this.mAvatars == null) {
                    AvatarsGridActivity.this.mServiceManager.fetchAvailableAvatarsList(new AvatarsFetchedCallback());
                    return;
                }
                AvatarsGridActivity.this.refreshCurrentIconSelection();
                AvatarsGridActivity.this.updateUI();
            }
            
            @Override
            public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
                Log.d("AvatarsGridActivity", "Manager isn't available!");
                AvatarsGridActivity.this.mServiceManager = null;
                AvatarsGridActivity.this.updateUI();
            }
        };
    }
    
    @Override
    public IClientLogging.ModalView getUiScreen() {
        return IClientLogging.ModalView.profilesGate;
    }
    
    @Override
    protected void handleProfilesListUpdated() {
        if (this.mServiceManager != null) {
            this.mServiceManager.fetchAvailableAvatarsList(new AvatarsFetchedCallback());
        }
    }
    
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
    
    private class AvatarsFetchedCallback extends SimpleManagerCallback
    {
        @Override
        public void onAvailableAvatarsListFetched(final List<AvatarInfo> list, final Status status) {
            if (Log.isLoggable("AvatarsGridActivity", 4)) {
                Log.i("AvatarsGridActivity", "onAvailableAvatarsListFetched: " + list);
            }
            if (status.isSucces() && list != null) {
                AvatarsGridActivity.this.mAvatars = list;
                if (AvatarsGridActivity.this.mAvatars.contains(AvatarsGridActivity.this.mDefaultAvatar)) {
                    AvatarsGridActivity.this.mAvatars.remove(AvatarsGridActivity.this.mDefaultAvatar);
                }
                AvatarsGridActivity.this.refreshCurrentIconSelection();
                AvatarsGridActivity.this.updateUI();
                return;
            }
            AvatarsGridActivity.this.handleUserAgentErrors(AvatarsGridActivity.this, status);
        }
    }
    
    private class ProfileAvatarAdapter extends BaseAdapter
    {
        private void adjustGridViewPaddings(final int n, int n2, final float n3, final float n4) {
            n2 *= (int)n3;
            final int n5 = (int)((n - n2 + n4) / 2.0f);
            if (Log.isLoggable("AvatarsGridActivity", 2)) {
                Log.v("AvatarsGridActivity", String.format("gridWidthWithoutPadding: %d, gridWidth: %d, padding: %d, avatarWidth: %d", n2, n, n5, (int)n3));
            }
            AvatarsGridActivity.this.mGridView.setPadding(n5, 0, (int)(n5 - n4), 0);
        }
        
        public int getCount() {
            if (AvatarsGridActivity.this.mAvatars == null) {
                return 1;
            }
            return AvatarsGridActivity.this.mAvatars.size() + 1;
        }
        
        public String getItem(final int n) {
            if (AvatarsGridActivity.this.mAvatars == null || n == 0) {
                return AvatarsGridActivity.this.mDefaultAvatar.getUrl();
            }
            return AvatarsGridActivity.this.mAvatars.get(n - 1).getUrl();
        }
        
        public long getItemId(final int n) {
            return n;
        }
        
        public String getItemName(final int n) {
            if (AvatarsGridActivity.this.mAvatars == null || n == 0) {
                return AvatarsGridActivity.this.mDefaultAvatar.getName();
            }
            return AvatarsGridActivity.this.mAvatars.get(n - 1).getName();
        }
        
        public View getView(final int n, final View view, final ViewGroup viewGroup) {
            View inflate = view;
            if (view == null) {
                inflate = AvatarsGridActivity.this.getLayoutInflater().inflate(2130903067, viewGroup, false);
                inflate.setTag((Object)new ViewHolder((AdvancedImageView)inflate.findViewById(2131165299), (ImageView)inflate.findViewById(2131165300)));
            }
            final ViewHolder viewHolder = (ViewHolder)inflate.getTag();
            final String item = this.getItem(n);
            if (AvatarsGridActivity.this.mSelectedIconPos == n) {
                viewHolder.topEditImg.setVisibility(0);
                viewHolder.topEditImg.setBackgroundResource(2130837825);
            }
            else {
                viewHolder.topEditImg.setVisibility(8);
            }
            NetflixActivity.getImageLoader((Context)AvatarsGridActivity.this).showImg(viewHolder.img, item, IClientLogging.AssetType.profileAvatar, this.getItemName(n), true, false);
            return inflate;
        }
        
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
            this.setupGridViewColumns();
        }
        
        public void setupGridViewColumns() {
            final int width = AvatarsGridActivity.this.mGridView.getWidth();
            if (width > 0) {
                final Resources resources = AvatarsGridActivity.this.getResources();
                final float dimension = resources.getDimension(2131361947);
                final float n = resources.getDimension(2131361946) + dimension;
                final int numColumns = (int)(width / n);
                AvatarsGridActivity.this.mGridView.setNumColumns(numColumns);
                this.adjustGridViewPaddings(width, numColumns, n, dimension);
            }
        }
    }
    
    private static class ViewHolder
    {
        private final AdvancedImageView img;
        private final ImageView topEditImg;
        
        public ViewHolder(final AdvancedImageView img, final ImageView topEditImg) {
            this.img = img;
            this.topEditImg = topEditImg;
            img.setPressedStateHandlerEnabled(false);
        }
    }
}
