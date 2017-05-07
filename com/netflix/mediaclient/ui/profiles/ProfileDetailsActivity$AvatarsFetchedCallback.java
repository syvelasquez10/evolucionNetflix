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
import com.netflix.mediaclient.servicemgr.model.user.UserProfile;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.view.View;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.content.DialogInterface$OnClickListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import android.content.Context;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import android.app.Activity;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.webclient.model.leafs.AvatarInfo;
import java.util.List;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

class ProfileDetailsActivity$AvatarsFetchedCallback extends SimpleManagerCallback
{
    final /* synthetic */ ProfileDetailsActivity this$0;
    
    private ProfileDetailsActivity$AvatarsFetchedCallback(final ProfileDetailsActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onAvailableAvatarsListFetched(final List<AvatarInfo> list, final Status status) {
        if (Log.isLoggable("ProfileDetailsActivity", 4)) {
            Log.i("ProfileDetailsActivity", "onAvailableAvatarsListFetched: " + list);
        }
        if (status.isSucces() && list != null) {
            if (!this.this$0.mDataWasInitialized || !list.contains(this.this$0.mCurrentAvatar)) {
                this.this$0.mDefaultAvatar = list.get(list.size() - 1);
                this.this$0.mCurrentAvatar = this.this$0.mDefaultAvatar;
            }
            this.this$0.updateUI();
            return;
        }
        final UIError uiError = ConsolidatedLoggingUtils.createUIError(status, this.this$0.handleUserAgentErrors(this.this$0, status), ActionOnUIError.displayedError);
        if (this.this$0.mProfileDeletionWasTriggered) {
            UserActionLogUtils.reportDeleteProfileActionEnded((Context)this.this$0, IClientLogging$CompletionReason.failed, IClientLogging$ModalView.profilesGate, uiError);
            return;
        }
        if (this.this$0.mNewProfileCreation) {
            UserActionLogUtils.reportAddProfileActionEnded((Context)this.this$0, IClientLogging$CompletionReason.failed, IClientLogging$ModalView.profilesGate, uiError, this.this$0.getProfileForLogging());
            return;
        }
        UserActionLogUtils.reportEditProfileActionEnded((Context)this.this$0, IClientLogging$CompletionReason.failed, IClientLogging$ModalView.profilesGate, uiError, this.this$0.getProfileForLogging());
    }
}
