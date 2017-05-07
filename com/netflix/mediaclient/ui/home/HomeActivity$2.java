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
import android.os.SystemClock;
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
import com.netflix.mediaclient.util.IrisUtils$NotificationsListStatus;
import android.content.DialogInterface$OnClickListener;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import android.support.v4.widget.DrawerLayout;
import android.content.Intent;
import java.util.LinkedList;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecyclerProvider;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;
import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;

class HomeActivity$2 implements ManagerStatusListener
{
    final /* synthetic */ HomeActivity this$0;
    
    HomeActivity$2(final HomeActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        Log.v("HomeActivity", "ServiceManager ready");
        this.this$0.manager = serviceManager;
        this.this$0.showProfileToast();
        this.this$0.leaveExperienceBreadcrumb();
        this.this$0.reportUiViewChanged(this.this$0.getCurrentViewType());
        this.this$0.getPrimaryFrag().onManagerReady(serviceManager, status);
        this.this$0.slidingMenuAdapter.onManagerReady(serviceManager, status);
        if (serviceManager != null && serviceManager.getBrowse() != null) {
            serviceManager.getBrowse().refreshIrisNotifications(false);
        }
        this.this$0.setLoadingStatusCallback(new HomeActivity$2$1(this));
        this.this$0.mDialogManager = new DialogManager(this.this$0);
        this.this$0.mDialogManager.displayDialogsIfNeeded();
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
        Log.w("HomeActivity", "ServiceManager unavailable");
        this.this$0.manager = null;
        this.this$0.getPrimaryFrag().onManagerUnavailable(serviceManager, status);
        this.this$0.slidingMenuAdapter.onManagerUnavailable(serviceManager, status);
        Log.d("HomeActivity", "LOLOMO failed, report UI startup session ended in case this was on UI startup");
    }
}
