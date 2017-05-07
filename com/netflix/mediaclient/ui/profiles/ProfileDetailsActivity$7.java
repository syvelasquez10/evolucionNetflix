// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.profiles;

import android.os.Parcelable;
import android.os.Bundle;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.StatusCode;
import android.content.DialogInterface;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.view.ViewPropertyAnimator;
import com.netflix.mediaclient.util.DeviceUtils;
import android.widget.TextView;
import android.text.TextWatcher;
import android.content.Intent;
import java.util.Iterator;
import android.widget.Toast;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.os.Handler;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.UserActionLogging$Profile;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.widget.EditText;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.widget.CheckBox;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.service.webclient.model.leafs.AvatarInfo;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.content.DialogInterface$OnClickListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import android.content.Context;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import android.view.View;
import android.view.View$OnClickListener;

class ProfileDetailsActivity$7 implements View$OnClickListener
{
    final /* synthetic */ ProfileDetailsActivity this$0;
    
    ProfileDetailsActivity$7(final ProfileDetailsActivity this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        String name = null;
        Log.d("ProfileDetailsActivity", "Save button was triggered");
        if (!this.this$0.findErrorsBeforeSubmit()) {
            this.this$0.removeFocusAndHideKeyboard();
            final String escapeJsonChars = StringUtils.escapeJsonChars(this.this$0.mName.getText().toString());
            if (this.this$0.mNewProfileCreation) {
                this.this$0.mServiceManager.addProfile(escapeJsonChars, this.this$0.mKidsCheckBox.isChecked(), this.this$0.mCurrentAvatar.getName(), this.this$0.mErrorHandlerCallback);
                this.this$0.mProfileChangeRequestWasSent = true;
            }
            else if (this.this$0.mInputProfile != null) {
                if (!this.this$0.mInputProfile.getFirstName().equals(this.this$0.mCurrentAvatar.getName())) {
                    name = this.this$0.mCurrentAvatar.getName();
                }
                this.this$0.mServiceManager.editProfile(this.this$0.mInputProfile.getProfileGuid(), escapeJsonChars, this.this$0.mKidsCheckBox.isChecked(), name, this.this$0.mErrorHandlerCallback);
                this.this$0.mProfileChangeRequestWasSent = true;
            }
            else {
                Log.e("ProfileDetailsActivity", "Weird use case: profile edit was started, but input profile is null");
                final UIError uiError = ConsolidatedLoggingUtils.createUIError(CommonStatus.INTERNAL_ERROR, "", ActionOnUIError.displayedError);
                if (this.this$0.mNewProfileCreation) {
                    UserActionLogUtils.reportAddProfileActionEnded((Context)this.this$0, IClientLogging$CompletionReason.failed, this.this$0.getUiScreen(), uiError, this.this$0.getProfileForLogging());
                }
                else {
                    UserActionLogUtils.reportEditProfileActionEnded((Context)this.this$0, IClientLogging$CompletionReason.failed, this.this$0.getUiScreen(), uiError, this.this$0.getProfileForLogging());
                }
                this.this$0.finish();
            }
            this.this$0.showLoading(true, true);
            return;
        }
        final UIError uiError2 = ConsolidatedLoggingUtils.createUIError(CommonStatus.INTERNAL_ERROR, "", ActionOnUIError.displayedError);
        if (this.this$0.mNewProfileCreation) {
            UserActionLogUtils.reportAddProfileActionEnded((Context)this.this$0, IClientLogging$CompletionReason.failed, this.this$0.getUiScreen(), uiError2, this.this$0.getProfileForLogging());
            UserActionLogUtils.reportAddProfileActionStarted((Context)this.this$0, null, this.this$0.getUiScreen());
            return;
        }
        UserActionLogUtils.reportEditProfileActionEnded((Context)this.this$0, IClientLogging$CompletionReason.failed, this.this$0.getUiScreen(), uiError2, this.this$0.getProfileForLogging());
        UserActionLogUtils.reportEditProfileActionStarted((Context)this.this$0, null, this.this$0.getUiScreen());
    }
}
