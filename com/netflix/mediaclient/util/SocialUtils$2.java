// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.app.Dialog;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import com.netflix.mediaclient.util.log.SocialLoggingUtils;
import android.text.Html;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import android.app.DialogFragment;
import java.util.ArrayList;
import com.netflix.mediaclient.service.webclient.model.leafs.social.FriendForRecommendation;
import java.util.Set;
import com.netflix.mediaclient.ui.details.RecommendToFriendsFrag;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import android.content.Context;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.Log;
import android.view.View;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View$OnClickListener;

final class SocialUtils$2 implements View$OnClickListener
{
    final /* synthetic */ NetflixActivity val$activity;
    final /* synthetic */ ServiceManager val$manager;
    final /* synthetic */ String val$videoId;
    
    SocialUtils$2(final ServiceManager val$manager, final NetflixActivity val$activity, final String val$videoId) {
        this.val$manager = val$manager;
        this.val$activity = val$activity;
        this.val$videoId = val$videoId;
    }
    
    public void onClick(final View view) {
        if (this.val$manager == null || !this.val$manager.isReady() || this.val$manager.getCurrentProfile() == null || this.val$activity == null) {
            if (Log.isLoggable("SocialUtils", 6)) {
                Log.e("SocialUtils", "Got problems trying to handle click on RecommendButton. Activity: " + this.val$activity + "; manager: " + this.val$manager);
            }
            return;
        }
        UserActionLogUtils.reportRecommendSheetActionStarted((Context)this.val$activity, null, IClientLogging$ModalView.movieDetails);
        if (this.val$manager.getCurrentProfile().isSocialConnected()) {
            this.val$activity.showDialog(RecommendToFriendsFrag.newInstance(this.val$videoId, null, null, null, null));
        }
        else {
            final Dialog displayDialog = this.val$activity.displayDialog(new AlertDialog$Builder((Context)this.val$activity).setPositiveButton(2131492988, (DialogInterface$OnClickListener)new SocialUtils$2$1(this)).setMessage((CharSequence)Html.fromHtml(this.val$activity.getString(2131493345))).setNegativeButton(2131493108, (DialogInterface$OnClickListener)null));
            SocialLoggingUtils.reportSocialConnectImpressionEvent((Context)this.val$activity, IClientLogging$ModalView.movieDetails);
            ((TextView)displayDialog.findViewById(16908299)).setMovementMethod(LinkMovementMethod.getInstance());
        }
        UserActionLogUtils.reportRecommendSheetActionEnded((Context)this.val$activity, IClientLogging$CompletionReason.success, null);
    }
}
