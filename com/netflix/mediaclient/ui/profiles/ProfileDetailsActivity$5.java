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
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.view.ViewPropertyAnimator;
import android.app.Activity;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.util.StringUtils;
import android.widget.TextView;
import android.text.TextWatcher;
import android.content.Intent;
import java.util.Iterator;
import android.widget.Toast;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
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
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.content.DialogInterface$OnClickListener;
import android.app.DialogFragment;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.android.widget.AlertDialogFactory;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import android.view.View;
import android.view.View$OnClickListener;

class ProfileDetailsActivity$5 implements View$OnClickListener
{
    final /* synthetic */ ProfileDetailsActivity this$0;
    
    ProfileDetailsActivity$5(final ProfileDetailsActivity this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        if (this.this$0.mInputProfile == null) {
            UserActionLogUtils.reportEditProfileActionEnded((Context)this.this$0, IClientLogging$CompletionReason.failed, this.this$0.getUiScreen(), ConsolidatedLoggingUtils.createUIError(CommonStatus.INTERNAL_ERROR, "", ActionOnUIError.handledSilently), this.this$0.getProfileForLogging());
            Log.e("ProfileDetailsActivity", "Weird use case: profile edit was started, but input profile is null");
            this.this$0.finish();
            return;
        }
        if (this.this$0.mServiceManager.getCurrentProfile() != null && this.this$0.mInputProfile.getProfileGuid().equals(this.this$0.mServiceManager.getCurrentProfile().getProfileGuid())) {
            this.this$0.displayDialog(AlertDialogFactory.createDialog((Context)this.this$0, this.this$0.handler, new AlertDialogFactory$AlertDialogDescriptor(null, this.this$0.getString(2131231253), this.this$0.getString(2131231126), null)));
            return;
        }
        UserActionLogUtils.reportEditProfileActionEnded((Context)this.this$0, IClientLogging$CompletionReason.canceled, this.this$0.getUiScreen(), null, this.this$0.getProfileForLogging());
        UserActionLogUtils.reportDeleteProfileActionStarted((Context)this.this$0, null, this.this$0.getUiScreen(), this.this$0.mInputProfile.getProfileGuid());
        this.this$0.removeFocusAndHideKeyboard();
        this.this$0.showDialog(DeleteProfileAlertDlg.createDeleteProfileDialog(this.this$0, this.this$0.mInputProfile.getAvatarUrl(), this.this$0.mInputProfile.getFirstName()));
    }
}
