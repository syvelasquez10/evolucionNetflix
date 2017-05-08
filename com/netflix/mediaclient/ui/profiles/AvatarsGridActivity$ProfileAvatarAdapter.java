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
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.widget.GridView;
import java.util.List;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.content.res.Resources;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.content.Context;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.ImageView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.view.ViewGroup;
import android.view.View;
import com.netflix.mediaclient.service.webclient.model.leafs.AvatarInfo;
import com.netflix.mediaclient.Log;
import android.widget.BaseAdapter;

class AvatarsGridActivity$ProfileAvatarAdapter extends BaseAdapter
{
    final /* synthetic */ AvatarsGridActivity this$0;
    
    private AvatarsGridActivity$ProfileAvatarAdapter(final AvatarsGridActivity this$0) {
        this.this$0 = this$0;
    }
    
    private void adjustGridViewPaddings(final int n, int n2, final float n3, final float n4) {
        n2 *= (int)n3;
        final int n5 = (int)((n - n2 + n4) / 2.0f);
        if (Log.isLoggable()) {
            Log.v("AvatarsGridActivity", String.format("gridWidthWithoutPadding: %d, gridWidth: %d, padding: %d, avatarWidth: %d", n2, n, n5, (int)n3));
        }
        this.this$0.mGridView.setPadding(n5, 0, (int)(n5 - n4), 0);
    }
    
    public int getCount() {
        if (this.this$0.mAvatars == null) {
            return 1;
        }
        return this.this$0.mAvatars.size() + 1;
    }
    
    public String getItem(final int n) {
        if (this.this$0.mAvatars == null || n == 0) {
            return this.this$0.mDefaultAvatar.getUrl();
        }
        return this.this$0.mAvatars.get(n - 1).getUrl();
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public String getItemName(final int n) {
        if (this.this$0.mAvatars == null || n == 0) {
            return this.this$0.mDefaultAvatar.getName();
        }
        return this.this$0.mAvatars.get(n - 1).getName();
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        View inflate = view;
        if (view == null) {
            inflate = this.this$0.getLayoutInflater().inflate(2130903073, viewGroup, false);
            inflate.setTag((Object)new AvatarsGridActivity$ViewHolder((AdvancedImageView)inflate.findViewById(2131689622), (ImageView)inflate.findViewById(2131689623)));
        }
        final AvatarsGridActivity$ViewHolder avatarsGridActivity$ViewHolder = (AvatarsGridActivity$ViewHolder)inflate.getTag();
        final String item = this.getItem(n);
        if (this.this$0.mSelectedIconPos == n) {
            avatarsGridActivity$ViewHolder.topEditImg.setVisibility(0);
            avatarsGridActivity$ViewHolder.topEditImg.setBackgroundResource(2130837965);
        }
        else {
            avatarsGridActivity$ViewHolder.topEditImg.setVisibility(8);
        }
        NetflixActivity.getImageLoader((Context)this.this$0).showImg(avatarsGridActivity$ViewHolder.img, item, IClientLogging$AssetType.profileAvatar, this.getItemName(n), ImageLoader$StaticImgConfig.DARK, false);
        return inflate;
    }
    
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        this.setupGridViewColumns();
    }
    
    public void setupGridViewColumns() {
        final int width = this.this$0.mGridView.getWidth();
        if (width > 0) {
            final Resources resources = this.this$0.getResources();
            final float dimension = resources.getDimension(2131362319);
            final float n = resources.getDimension(2131362318) + dimension;
            final int numColumns = (int)(width / n);
            this.this$0.mGridView.setNumColumns(numColumns);
            this.adjustGridViewPaddings(width, numColumns, n, dimension);
        }
    }
}
