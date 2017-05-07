// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.client.model.Error;
import android.widget.ListAdapter;
import android.text.TextWatcher;
import android.widget.AbsListView$OnScrollListener;
import android.view.ViewGroup;
import com.netflix.mediaclient.util.SocialNotificationsUtils;
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
import android.view.View$OnClickListener;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.app.Activity;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.Button;
import android.view.animation.Animation;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.ListView;
import java.util.ArrayList;
import android.widget.EditText;
import com.netflix.mediaclient.service.webclient.model.leafs.social.FriendForRecommendation;
import java.util.HashSet;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;

class RecommendToFriendsFrag$1 implements ErrorWrapper$Callback
{
    final /* synthetic */ RecommendToFriendsFrag this$0;
    
    RecommendToFriendsFrag$1(final RecommendToFriendsFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onRetryRequested() {
        this.this$0.fetchFriends();
    }
}
