// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.ListAdapter;
import android.view.View$OnClickListener;
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
import com.netflix.mediaclient.servicemgr.model.user.UserProfile;
import android.content.Context;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.text.Html;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.Log;
import android.app.Activity;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.Button;
import android.view.animation.Animation;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.ListView;
import java.util.ArrayList;
import android.widget.EditText;
import java.util.HashSet;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import java.util.Iterator;
import java.util.Collection;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.webclient.model.leafs.social.FriendForRecommendation;
import java.util.List;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

class RecommendToFriendsFrag$8 extends SimpleManagerCallback
{
    final /* synthetic */ RecommendToFriendsFrag this$0;
    
    RecommendToFriendsFrag$8(final RecommendToFriendsFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onFriendsForRecommendationsListFetched(final List<FriendForRecommendation> list, final Status status) {
        if (!this.this$0.checkForError(status)) {
            this.this$0.mLoadMoreAvailable = (list != null && list.size() == 20);
            if (list != null) {
                if (this.this$0.mSearchTerm == null && this.this$0.mCheckedFriends != null) {
                    for (final FriendForRecommendation friendForRecommendation : list) {
                        if (!this.this$0.mFriends.contains(friendForRecommendation)) {
                            this.this$0.mFriends.add(friendForRecommendation);
                        }
                    }
                }
                else {
                    this.this$0.mFriends.addAll(list);
                }
            }
            if (this.this$0.mAdapter != null) {
                this.this$0.mAdapter.notifyDataSetChanged();
            }
        }
    }
}
