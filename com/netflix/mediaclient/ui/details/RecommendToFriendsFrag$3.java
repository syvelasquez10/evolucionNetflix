// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.ListAdapter;
import android.view.View$OnClickListener;
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
import com.netflix.mediaclient.service.webclient.model.leafs.social.FriendForRecommendation;
import java.util.HashSet;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import android.view.View;
import android.content.Context;
import com.netflix.mediaclient.util.log.SocialLoggingUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.text.Editable;
import android.text.TextWatcher;

class RecommendToFriendsFrag$3 implements TextWatcher
{
    final /* synthetic */ RecommendToFriendsFrag this$0;
    
    RecommendToFriendsFrag$3(final RecommendToFriendsFrag this$0) {
        this.this$0 = this$0;
    }
    
    public void afterTextChanged(final Editable editable) {
    }
    
    public void beforeTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
    }
    
    public void onTextChanged(final CharSequence charSequence, int visibility, final int n, final int n2) {
        if (charSequence.length() < 1) {
            this.this$0.mSearchTerm = null;
        }
        else {
            this.this$0.mSearchTerm = charSequence.toString();
        }
        final View access$400 = this.this$0.mSearchClearButton;
        if (this.this$0.mSearchTerm != null) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        access$400.setVisibility(visibility);
        SocialLoggingUtils.reportRecommendPanelSearchedEvent((Context)this.this$0.getActivity(), IClientLogging$ModalView.movieDetails, this.this$0.getGUID(), this.this$0.getTrackId(this.this$0.getActivity()));
        this.this$0.fetchFriends();
    }
}
