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
import com.netflix.mediaclient.service.user.volley.FriendForRecommendation;
import java.util.HashSet;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import android.widget.EditText;
import android.view.View;

class RecommendToFriendsFrag$9$1 implements Runnable
{
    final /* synthetic */ RecommendToFriendsFrag$9 this$1;
    
    RecommendToFriendsFrag$9$1(final RecommendToFriendsFrag$9 this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void run() {
        final View access$2600 = this.this$1.this$0.mTotalSelectedLayout;
        int visibility;
        if (this.this$1.val$bHideViews) {
            visibility = 4;
        }
        else {
            visibility = 0;
        }
        access$2600.setVisibility(visibility);
        final EditText access$2601 = this.this$1.this$0.mSearchEditText;
        float translationX;
        if (this.this$1.val$bHideViews) {
            translationX = -this.this$1.val$width;
        }
        else {
            translationX = 0.0f;
        }
        access$2601.setTranslationX(translationX);
    }
}
