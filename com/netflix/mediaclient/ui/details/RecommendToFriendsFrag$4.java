// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.ListAdapter;
import android.text.TextWatcher;
import android.widget.AbsListView$OnScrollListener;
import android.view.ViewGroup;
import com.netflix.mediaclient.util.SocialUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import android.os.Parcelable;
import android.os.Bundle;
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
import android.app.Activity;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.Button;
import android.view.animation.Animation;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.EditText;
import java.util.HashSet;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import java.util.Iterator;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import java.util.ArrayList;
import java.util.Set;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;
import com.netflix.mediaclient.util.log.SocialLoggingUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.user.volley.FriendForRecommendation;
import com.netflix.mediaclient.servicemgr.SocialLogging$FriendPosition;
import android.view.View;
import android.view.View$OnClickListener;

class RecommendToFriendsFrag$4 implements View$OnClickListener
{
    final /* synthetic */ RecommendToFriendsFrag this$0;
    
    RecommendToFriendsFrag$4(final RecommendToFriendsFrag this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        final SocialLogging$FriendPosition[] array = new SocialLogging$FriendPosition[this.this$0.mCheckedFriends.size()];
        final Iterator<FriendForRecommendation> iterator = this.this$0.mCheckedFriends.iterator();
        int n = 0;
        boolean b = false;
        while (iterator.hasNext()) {
            final FriendForRecommendation friendForRecommendation = iterator.next();
            if (!friendForRecommendation.isNetlflixConnected()) {
                b = true;
            }
            array[n] = new SocialLogging$FriendPosition(friendForRecommendation.getFriendProfile().getId(), n, false);
            ++n;
        }
        final String string = this.this$0.getArguments().getString("video_id");
        final String string2 = this.this$0.mEditMessage.getText().toString();
        if (StringUtils.isNotEmpty(string2)) {
            SocialLoggingUtils.reportRecommendMessageAddedEvent((Context)this.this$0.getActivity(), IClientLogging$ModalView.movieDetails, this.this$0.getGUID(), this.this$0.getTrackId(this.this$0.getActivity()));
        }
        SocialLoggingUtils.reportRecommendFriendSelectedEvent((Context)this.this$0.getActivity(), IClientLogging$ModalView.movieDetails, this.this$0.getGUID(), array, this.this$0.getTrackId(this.this$0.getActivity()));
        if (!b || PreferenceUtils.getBooleanPref((Context)this.this$0.getActivity(), SendAsFacebookMessageDialog.getFacebookMsgOptInKey(this.this$0.mServiceManager), false)) {
            this.this$0.mServiceManager.sendRecommendationsToFriends(string, this.this$0.mCheckedFriends, string2, this.this$0.getGUID());
        }
        else {
            final SendAsFacebookMessageDialog instance = SendAsFacebookMessageDialog.newInstance(this.this$0.mCheckedFriends, string, string2, this.this$0.getGUID(), this.this$0.mFriends);
            instance.show(this.this$0.getFragmentManager(), "frag_dialog");
            instance.onManagerReady(this.this$0.mServiceManager, CommonStatus.OK);
        }
        this.this$0.dismiss();
    }
}
