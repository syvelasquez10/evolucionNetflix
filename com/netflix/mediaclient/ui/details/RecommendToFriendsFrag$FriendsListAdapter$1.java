// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.widget.ImageView;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.ListAdapter;
import android.text.TextWatcher;
import android.widget.AbsListView$OnScrollListener;
import android.view.ViewGroup;
import com.netflix.mediaclient.util.SocialUtils;
import android.os.Parcelable;
import android.os.Bundle;
import java.util.Set;
import android.view.animation.Animation$AnimationListener;
import android.view.animation.Interpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import android.content.Context;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.text.Html;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import android.app.Activity;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.widget.TextView;
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
import android.view.View;
import com.netflix.mediaclient.service.user.volley.FriendForRecommendation;
import android.view.View$OnClickListener;

class RecommendToFriendsFrag$FriendsListAdapter$1 implements View$OnClickListener
{
    final /* synthetic */ RecommendToFriendsFrag$FriendsListAdapter this$1;
    final /* synthetic */ RecommendToFriendsFrag$FriendsListAdapter$Holder val$holder;
    final /* synthetic */ FriendForRecommendation val$profile;
    
    RecommendToFriendsFrag$FriendsListAdapter$1(final RecommendToFriendsFrag$FriendsListAdapter this$1, final FriendForRecommendation val$profile, final RecommendToFriendsFrag$FriendsListAdapter$Holder val$holder) {
        this.this$1 = this$1;
        this.val$profile = val$profile;
        this.val$holder = val$holder;
    }
    
    public void onClick(final View view) {
        if (this.this$1.this$0.mCheckedFriends.contains(this.val$profile)) {
            this.val$holder.checkMark.setImageResource(2130837876);
            this.this$1.this$0.mCheckedFriends.remove(this.val$profile);
        }
        else {
            this.val$holder.checkMark.setImageResource(2130837877);
            this.this$1.this$0.mCheckedFriends.add(this.val$profile);
        }
        this.this$1.this$0.updateTotalSelectedStatus();
        if (this.this$1.this$0.mSearchTerm != null) {
            this.this$1.this$0.resetSearch();
        }
    }
}
