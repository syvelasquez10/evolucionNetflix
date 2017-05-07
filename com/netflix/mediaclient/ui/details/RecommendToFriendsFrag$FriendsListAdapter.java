// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.client.model.Error;
import android.widget.ListAdapter;
import android.text.TextWatcher;
import android.widget.AbsListView$OnScrollListener;
import com.netflix.mediaclient.util.SocialUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import android.os.Parcelable;
import android.os.Bundle;
import java.util.Set;
import android.view.animation.Animation$AnimationListener;
import android.view.animation.Interpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.text.Html;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import android.app.Activity;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.Button;
import android.view.animation.Animation;
import android.view.LayoutInflater;
import android.widget.ListView;
import java.util.ArrayList;
import android.widget.EditText;
import java.util.HashSet;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.content.Context;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.ImageView;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.view.ViewGroup;
import android.view.View;
import com.netflix.mediaclient.service.user.volley.FriendForRecommendation;
import android.widget.BaseAdapter;

class RecommendToFriendsFrag$FriendsListAdapter extends BaseAdapter
{
    final /* synthetic */ RecommendToFriendsFrag this$0;
    
    private RecommendToFriendsFrag$FriendsListAdapter(final RecommendToFriendsFrag this$0) {
        this.this$0 = this$0;
    }
    
    public int getCount() {
        if (this.this$0.mErrorOccurred) {
            return 0;
        }
        if (this.this$0.mFriends == null || this.this$0.mFriends.size() == 0) {
            return 1;
        }
        if (this.this$0.mLoadMoreAvailable) {
            return this.this$0.mFriends.size() + 1;
        }
        return this.this$0.mFriends.size();
    }
    
    public FriendForRecommendation getItem(final int n) {
        if (this.this$0.mFriends == null || n > this.this$0.mFriends.size() - 1) {
            return null;
        }
        return this.this$0.mFriends.get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(int imageResource, final View view, final ViewGroup viewGroup) {
        View inflate = view;
        if (view == null) {
            inflate = this.this$0.mLayoutInflater.inflate(2130903180, viewGroup, false);
            inflate.setTag((Object)new RecommendToFriendsFrag$FriendsListAdapter$Holder(this, (AdvancedImageView)inflate.findViewById(2131427793), (TextView)inflate.findViewById(2131427794), (ImageView)inflate.findViewById(2131427797), inflate.findViewById(2131427796), (TextView)inflate.findViewById(2131427795)));
        }
        final RecommendToFriendsFrag$FriendsListAdapter$Holder recommendToFriendsFrag$FriendsListAdapter$Holder = (RecommendToFriendsFrag$FriendsListAdapter$Holder)inflate.getTag();
        final FriendForRecommendation item = this.getItem(imageResource);
        recommendToFriendsFrag$FriendsListAdapter$Holder.img.setVisibility(8);
        recommendToFriendsFrag$FriendsListAdapter$Holder.checkMark.setVisibility(8);
        recommendToFriendsFrag$FriendsListAdapter$Holder.fbIcon.setVisibility(8);
        recommendToFriendsFrag$FriendsListAdapter$Holder.friendWatchedStatus.setVisibility(8);
        if (!this.this$0.mLoadMoreAvailable && (this.this$0.mFriends == null || this.this$0.mFriends.size() == 0)) {
            recommendToFriendsFrag$FriendsListAdapter$Holder.name.setText(2131493346);
            recommendToFriendsFrag$FriendsListAdapter$Holder.name.setSingleLine(false);
            recommendToFriendsFrag$FriendsListAdapter$Holder.name.setGravity(17);
        }
        else {
            if (this.this$0.mLoadMoreAvailable && imageResource == this.getCount() - 1) {
                recommendToFriendsFrag$FriendsListAdapter$Holder.name.setText(2131493345);
                recommendToFriendsFrag$FriendsListAdapter$Holder.name.setSingleLine(false);
                recommendToFriendsFrag$FriendsListAdapter$Holder.name.setGravity(17);
                this.this$0.loadMoreFriends();
                return inflate;
            }
            if (this.this$0.getActivity() != null && NetflixActivity.getImageLoader((Context)this.this$0.getActivity()) != null) {
                recommendToFriendsFrag$FriendsListAdapter$Holder.name.setGravity(19);
                recommendToFriendsFrag$FriendsListAdapter$Holder.name.setSingleLine(true);
                recommendToFriendsFrag$FriendsListAdapter$Holder.name.setText((CharSequence)item.getFriendProfile().getFullName());
                recommendToFriendsFrag$FriendsListAdapter$Holder.img.setVisibility(0);
                NetflixActivity.getImageLoader((Context)this.this$0.getActivity()).showImg(recommendToFriendsFrag$FriendsListAdapter$Holder.img, item.getFriendProfile().getImageUrl(), IClientLogging$AssetType.profileAvatar, item.getFriendProfile().getFullName(), ImageLoader$StaticImgConfig.DARK, true);
                recommendToFriendsFrag$FriendsListAdapter$Holder.checkMark.setVisibility(0);
                if (item.wasWatched()) {
                    recommendToFriendsFrag$FriendsListAdapter$Holder.friendWatchedStatus.setVisibility(0);
                }
                else if (!item.isNetlflixConnected()) {
                    recommendToFriendsFrag$FriendsListAdapter$Holder.fbIcon.setVisibility(0);
                }
                final ImageView access$1600 = recommendToFriendsFrag$FriendsListAdapter$Holder.checkMark;
                if (this.this$0.mCheckedFriends.contains(item)) {
                    imageResource = 2130837883;
                }
                else {
                    imageResource = 2130837882;
                }
                access$1600.setImageResource(imageResource);
                inflate.setOnClickListener((View$OnClickListener)new RecommendToFriendsFrag$FriendsListAdapter$1(this, item, recommendToFriendsFrag$FriendsListAdapter$Holder));
                return inflate;
            }
        }
        return inflate;
    }
}
