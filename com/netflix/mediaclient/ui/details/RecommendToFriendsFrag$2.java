// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.service.logging.client.model.Error;
import android.widget.ListAdapter;
import android.text.TextWatcher;
import android.widget.AbsListView$OnScrollListener;
import android.view.ViewGroup;
import com.netflix.mediaclient.util.SocialNotificationsUtils;
import android.os.Parcelable;
import android.os.Bundle;
import android.view.animation.Animation$AnimationListener;
import android.view.animation.Interpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.model.user.UserProfile;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import android.app.Activity;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.widget.Button;
import android.view.animation.Animation;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.EditText;
import java.util.HashSet;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import android.app.Dialog;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import com.netflix.mediaclient.util.log.SocialLoggingUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.text.Html;
import android.content.DialogInterface$OnClickListener;
import android.content.Context;
import android.app.AlertDialog$Builder;
import android.app.DialogFragment;
import java.util.ArrayList;
import com.netflix.mediaclient.service.webclient.model.leafs.social.FriendForRecommendation;
import java.util.Set;
import com.netflix.mediaclient.Log;
import android.view.View;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View$OnClickListener;

final class RecommendToFriendsFrag$2 implements View$OnClickListener
{
    final /* synthetic */ NetflixActivity val$activity;
    final /* synthetic */ ServiceManager val$manager;
    final /* synthetic */ String val$videoId;
    
    RecommendToFriendsFrag$2(final ServiceManager val$manager, final NetflixActivity val$activity, final String val$videoId) {
        this.val$manager = val$manager;
        this.val$activity = val$activity;
        this.val$videoId = val$videoId;
    }
    
    public void onClick(final View view) {
        if (this.val$manager == null || !this.val$manager.isReady() || this.val$manager.getCurrentProfile() == null || this.val$activity == null) {
            if (Log.isLoggable(RecommendToFriendsFrag.TAG, 6)) {
                Log.e(RecommendToFriendsFrag.TAG, "Got problems trying to handle click on RecommendButton. Activity: " + this.val$activity + "; manager: " + this.val$manager);
            }
            return;
        }
        if (this.val$manager.getCurrentProfile().isSocialConnected()) {
            this.val$activity.showDialog(RecommendToFriendsFrag.newInstance(this.val$videoId, null, null, null, null));
            return;
        }
        final Dialog displayDialog = this.val$activity.displayDialog(new AlertDialog$Builder((Context)this.val$activity).setPositiveButton(2131492988, (DialogInterface$OnClickListener)new RecommendToFriendsFrag$2$1(this)).setMessage((CharSequence)Html.fromHtml(this.val$activity.getString(2131493345))).setNegativeButton(2131493108, (DialogInterface$OnClickListener)null));
        SocialLoggingUtils.reportSocialConnectImpressionEvent((Context)this.val$activity, IClientLogging$ModalView.movieDetails);
        ((TextView)displayDialog.findViewById(16908299)).setMovementMethod(LinkMovementMethod.getInstance());
    }
}
