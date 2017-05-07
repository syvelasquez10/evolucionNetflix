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
import android.content.Intent;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.app.Status;
import android.app.Activity;
import java.util.List;
import android.widget.AdapterView$OnItemClickListener;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.StaticGridView;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.util.SparseIntArray;
import android.util.SparseArray;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.content.Context;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.view.ViewGroup;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import android.widget.BaseAdapter;

class ProfileSelectionActivity$ProfileAvatarAdapter extends BaseAdapter
{
    final /* synthetic */ ProfileSelectionActivity this$0;
    
    private ProfileSelectionActivity$ProfileAvatarAdapter(final ProfileSelectionActivity this$0) {
        this.this$0 = this$0;
    }
    
    public int getCount() {
        int size;
        final int n = size = this.this$0.profiles.size();
        if (n < 5) {
            size = n + 1;
        }
        return size;
    }
    
    public UserProfile getItem(final int n) {
        if (n < this.this$0.profiles.size()) {
            return this.this$0.profiles.get(n);
        }
        return null;
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(int visibility, final View view, final ViewGroup viewGroup) {
        View inflate = view;
        if (view == null) {
            inflate = this.this$0.getLayoutInflater().inflate(2130903176, viewGroup, false);
            inflate.setTag((Object)new ProfileSelectionActivity$Holder((AdvancedImageView)inflate.findViewById(2131427451), (TextView)inflate.findViewById(2131427782), inflate.findViewById(2131427781)));
        }
        final ProfileSelectionActivity$Holder profileSelectionActivity$Holder = (ProfileSelectionActivity$Holder)inflate.getTag();
        final UserProfile item = this.getItem(visibility);
        if (visibility == this.this$0.profiles.size()) {
            profileSelectionActivity$Holder.img.setImageResource(2130837879);
            profileSelectionActivity$Holder.title.setText(2131493300);
            profileSelectionActivity$Holder.topEditImg.setVisibility(8);
            return inflate;
        }
        profileSelectionActivity$Holder.title.setText((CharSequence)item.getProfileName());
        NetflixActivity.getImageLoader((Context)this.this$0).showImg(profileSelectionActivity$Holder.img, item.getAvatarUrl(), IClientLogging$AssetType.profileAvatar, item.getFirstName(), ImageLoader$StaticImgConfig.DARK, true);
        final View access$1000 = profileSelectionActivity$Holder.topEditImg;
        if (this.this$0.isProfileEditMode) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        access$1000.setVisibility(visibility);
        final AdvancedImageView access$1001 = profileSelectionActivity$Holder.img;
        float alpha;
        if (this.this$0.isProfileEditMode) {
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
        this.this$0.setupGridViewColumns();
    }
}
