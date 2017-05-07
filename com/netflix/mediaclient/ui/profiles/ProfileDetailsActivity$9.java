// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.profiles;

import android.os.Parcelable;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
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
import android.content.Intent;
import java.util.Iterator;
import android.widget.Toast;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import android.os.Handler;
import com.netflix.mediaclient.android.app.Status;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.UserActionLogging$Profile;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.widget.EditText;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.widget.CheckBox;
import com.netflix.mediaclient.servicemgr.model.user.UserProfile;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.service.webclient.model.leafs.AvatarInfo;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.content.DialogInterface$OnClickListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.Context;
import com.netflix.mediaclient.Log;
import android.view.View;
import android.view.View$OnClickListener;

class ProfileDetailsActivity$9 implements View$OnClickListener
{
    final /* synthetic */ ProfileDetailsActivity this$0;
    
    ProfileDetailsActivity$9(final ProfileDetailsActivity this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        if (!this.this$0.isCurrentAvatarDataValid()) {
            Log.e("ProfileDetailsActivity", "Profile avatar was touched when current avatar data is not ready...ignoring...");
        }
        else {
            this.this$0.startActivityForResult(AvatarsGridActivity.getStartIntent((Context)this.this$0, this.this$0.mDefaultAvatar, this.this$0.mCurrentAvatar), 1);
            if (Log.isLoggable("ProfileDetailsActivity", 4)) {
                Log.i("ProfileDetailsActivity", "Started with defaultAvatar: " + this.this$0.mDefaultAvatar + "; currentAvatar: " + this.this$0.mCurrentAvatar);
            }
        }
    }
}
