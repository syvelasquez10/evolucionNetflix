// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.profiles;

import android.os.Parcelable;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.StatusCode;
import android.content.DialogInterface;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.view.ViewPropertyAnimator;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.util.StringUtils;
import android.widget.TextView;
import android.text.TextWatcher;
import android.view.View$OnClickListener;
import android.content.Intent;
import java.util.Iterator;
import android.widget.Toast;
import android.os.Handler;
import com.netflix.mediaclient.servicemgr.UserActionLogging$Profile;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.widget.EditText;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.widget.CheckBox;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.service.webclient.model.leafs.AvatarInfo;
import android.view.View;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.content.DialogInterface$OnClickListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import android.app.Activity;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import android.content.Context;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

class ProfileDetailsActivity$3 extends SimpleManagerCallback
{
    final /* synthetic */ ProfileDetailsActivity this$0;
    
    ProfileDetailsActivity$3(final ProfileDetailsActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onProfileListUpdateStatus(final Status status) {
        if (status.isSucces()) {
            Log.v("ProfileDetailsActivity", "Operation successful!");
            if (this.this$0.mProfileDeletionWasTriggered) {
                UserActionLogUtils.reportDeleteProfileActionEnded((Context)this.this$0, IClientLogging$CompletionReason.success, IClientLogging$ModalView.profilesGate, null);
                return;
            }
            if (this.this$0.mNewProfileCreation) {
                UserActionLogUtils.reportAddProfileActionEnded((Context)this.this$0, IClientLogging$CompletionReason.success, IClientLogging$ModalView.profilesGate, null, this.this$0.getProfileForLogging());
                return;
            }
            UserActionLogUtils.reportEditProfileActionEnded((Context)this.this$0, IClientLogging$CompletionReason.success, IClientLogging$ModalView.profilesGate, null, this.this$0.getProfileForLogging());
        }
        else {
            final String access$1100 = this.this$0.handleUserAgentErrors(this.this$0, status);
            if (this.this$0.mProfileDeletionWasTriggered) {
                UserActionLogUtils.reportDeleteProfileActionEnded((Context)this.this$0, IClientLogging$CompletionReason.failed, IClientLogging$ModalView.profilesGate, ConsolidatedLoggingUtils.createUIError(status, access$1100, ActionOnUIError.displayedError));
                return;
            }
            if (this.this$0.mNewProfileCreation) {
                UserActionLogUtils.reportAddProfileActionEnded((Context)this.this$0, IClientLogging$CompletionReason.failed, IClientLogging$ModalView.profilesGate, ConsolidatedLoggingUtils.createUIError(status, access$1100, ActionOnUIError.displayedError), this.this$0.getProfileForLogging());
                return;
            }
            UserActionLogUtils.reportEditProfileActionEnded((Context)this.this$0, IClientLogging$CompletionReason.failed, IClientLogging$ModalView.profilesGate, ConsolidatedLoggingUtils.createUIError(status, access$1100, ActionOnUIError.displayedError), this.this$0.getProfileForLogging());
        }
    }
}
