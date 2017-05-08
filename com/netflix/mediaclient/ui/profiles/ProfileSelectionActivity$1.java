// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.profiles;

import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.android.app.CommonStatus;
import android.view.MenuItem;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.Menu;
import android.os.Bundle;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.ui.launch.RelaunchActivity;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.view.ViewGroup$MarginLayoutParams;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
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
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.view.ViewPropertyAnimator;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.app.Activity;
import android.content.Intent;
import com.netflix.mediaclient.util.l10n.LocalizationUtils;
import java.util.Locale;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.app.Status;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import java.util.List;
import android.widget.AdapterView$OnItemClickListener;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.StaticGridView;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.view.View;
import android.util.SparseIntArray;
import android.util.SparseArray;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

class ProfileSelectionActivity$1 implements ViewTreeObserver$OnGlobalLayoutListener
{
    final /* synthetic */ ProfileSelectionActivity this$0;
    
    ProfileSelectionActivity$1(final ProfileSelectionActivity this$0) {
        this.this$0 = this$0;
    }
    
    public void onGlobalLayout() {
        this.this$0.adjustGridViewMargins();
    }
}
