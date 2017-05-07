// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import android.view.View;
import java.io.Serializable;
import com.netflix.mediaclient.ui.search.SearchMenu;
import com.netflix.mediaclient.ui.mdx.MdxMenu;
import android.view.Menu;
import java.util.Collection;
import android.os.Bundle;
import android.view.KeyEvent;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;
import com.netflix.mediaclient.ui.lolomo.KidsGenresLoMoFrag;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.ui.kubrick.lolomo.KubrickHomeActionBar;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.android.widget.NetflixActionBar$LogoType;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.android.app.CommonStatus;
import android.app.Fragment;
import android.os.Parcelable;
import android.support.v4.widget.DrawerLayout$DrawerListener;
import android.widget.Toast;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Context;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import android.os.Handler;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.util.SocialUtils$NotificationsListStatus;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.content.DialogInterface$OnClickListener;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import android.support.v4.widget.DrawerLayout;
import android.content.Intent;
import java.util.LinkedList;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecyclerProvider;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;
import android.app.DialogFragment;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import android.os.SystemClock;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;

class HomeActivity$2$1 implements LoadingStatus$LoadingStatusCallback
{
    final /* synthetic */ HomeActivity$2 this$1;
    
    HomeActivity$2$1(final HomeActivity$2 this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onDataLoaded(final Status status) {
        this.this$1.this$0.setLoadingStatusCallback(null);
        Log.d("HomeActivity", "LOLOMO is loaded, report UI browse startup session ended in case this was on UI startup");
        this.this$1.this$0.getServiceManager().getClientLogging().getApplicationPerformanceMetricsLogging().endUiBrowseStartupSession(SystemClock.elapsedRealtime() - this.this$1.this$0.mStartedTimeMs, status.isSucces(), null);
        if (StatusCode.INVALID_COUNRTY.equals(status.getStatusCode())) {
            Log.d("HomeActivity", "User accessing Netflix in a not supported country. Show alert and kill self");
            this.this$1.this$0.showDialog(InvalidCountryAlertDialog.createInvalidCountryAlertDialog(this.this$1.this$0));
        }
    }
}