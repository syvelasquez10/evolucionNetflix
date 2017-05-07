// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.profiles;

import android.view.MenuItem;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.Menu;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.os.Bundle;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.ui.RelaunchActivity;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.view.ViewGroup$MarginLayoutParams;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.android.widget.NetflixActionBar$LogoType;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.util.StringUtils;
import java.util.Iterator;
import android.widget.ListAdapter;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.view.ViewPropertyAnimator;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.content.Intent;
import com.netflix.mediaclient.util.DeviceUtils;
import android.widget.TextView;
import java.util.List;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.StaticGridView;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.util.SparseIntArray;
import android.util.SparseArray;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.app.Status;
import android.app.Activity;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.servicemgr.UserActionLogging$RememberProfile;
import com.netflix.mediaclient.servicemgr.model.user.UserProfile;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import android.content.Context;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView$OnItemClickListener;

class ProfileSelectionActivity$5 implements AdapterView$OnItemClickListener
{
    final /* synthetic */ ProfileSelectionActivity this$0;
    
    ProfileSelectionActivity$5(final ProfileSelectionActivity this$0) {
        this.this$0 = this$0;
    }
    
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        if (this.this$0.profiles == null || n > this.this$0.profiles.size()) {
            Log.d("ProfileSelectionActivity", "Invalid profiles set");
            return;
        }
        if (n == this.this$0.profiles.size()) {
            UserActionLogUtils.reportAddProfileActionStarted((Context)this.this$0, null, this.this$0.getUiScreen());
            this.this$0.startActivity(ProfileDetailsActivity.getStartIntent((Context)this.this$0, null));
            return;
        }
        if (!this.this$0.isProfileEditMode) {
            final UserProfile userProfile = this.this$0.profiles.get(n);
            UserActionLogUtils.reportSelectProfileActionStarted((Context)this.this$0, null, this.this$0.getUiScreen(), userProfile.getProfileGuid(), UserActionLogging$RememberProfile.userChoseToRemember);
            this.this$0.startChangeProfile(userProfile);
            this.this$0.invalidateOptionsMenu();
            return;
        }
        if (this.this$0.profiles.get(n).getProfileGuid() == null) {
            this.this$0.handleUserAgentErrors(this.this$0, CommonStatus.INTERNAL_ERROR);
            return;
        }
        UserActionLogUtils.reportEditProfileActionStarted((Context)this.this$0, null, this.this$0.getUiScreen());
        this.this$0.startActivity(ProfileDetailsActivity.getStartIntent((Context)this.this$0, this.this$0.profiles.get(n).getProfileGuid()));
    }
}
