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
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
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
import com.netflix.mediaclient.service.logging.client.model.UIError;
import android.content.Context;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import android.os.Handler;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.UserActionLogging$Profile;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.widget.EditText;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.widget.CheckBox;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import android.view.View;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.content.DialogInterface$OnClickListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.service.webclient.model.leafs.AvatarInfo;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;

class ProfileDetailsActivity$1 implements ManagerStatusListener
{
    final /* synthetic */ ProfileDetailsActivity this$0;
    
    ProfileDetailsActivity$1(final ProfileDetailsActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        Log.d("ProfileDetailsActivity", "Manager is here!");
        this.this$0.mServiceManager = serviceManager;
        this.this$0.updateInputProfile();
        if (!this.this$0.mDataWasInitialized) {
            if (this.this$0.mNewProfileCreation) {
                this.this$0.mServiceManager.fetchAvailableAvatarsList(new ProfileDetailsActivity$AvatarsFetchedCallback(this.this$0, null));
            }
            else {
                this.this$0.mDefaultAvatar = new AvatarInfo(this.this$0.mInputProfile.getProfileName(), this.this$0.mInputProfile.getAvatarUrl());
                this.this$0.mCurrentAvatar = this.this$0.mDefaultAvatar;
            }
        }
        this.this$0.updateUI();
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
        Log.e("ProfileDetailsActivity", "Manager isn't available!");
        this.this$0.mServiceManager = null;
        this.this$0.updateUI();
    }
}
