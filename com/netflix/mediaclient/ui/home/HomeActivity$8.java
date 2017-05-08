// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.ui.offline.TutorialHelper;
import com.netflix.android.tooltips.Tooltip;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import android.view.View;
import java.io.Serializable;
import com.netflix.mediaclient.android.activity.NetflixActivity$ServiceManagerRunnable;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.view.MenuItem;
import com.netflix.mediaclient.service.webclient.model.leafs.UmaAlert;
import com.netflix.mediaclient.ui.search.SearchMenu;
import com.netflix.mediaclient.ui.mdx.MdxMenu;
import android.view.Menu;
import com.netflix.mediaclient.util.Coppola2Utils;
import java.util.Collection;
import android.os.SystemClock;
import com.netflix.mediaclient.service.logging.perf.Events;
import android.os.Bundle;
import android.view.KeyEvent;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;
import com.netflix.mediaclient.ui.lolomo.GalleryGenresLoMoFrag;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList$GenreType;
import java.util.Map;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.ui.kubrick.lolomo.BarkerHomeActionBar;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.android.widget.NetflixActionBar$LogoType;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.android.app.CommonStatus;
import android.app.Fragment;
import android.os.Parcelable;
import android.app.Activity;
import android.support.v4.widget.DrawerLayout$DrawerListener;
import com.netflix.mediaclient.service.logging.perf.InteractiveTracker$InteractiveListener;
import android.widget.Toast;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import android.os.Handler;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import android.support.v4.widget.DrawerLayout;
import java.util.LinkedList;
import com.netflix.mediaclient.service.logging.perf.InteractiveTracker$TTRTracker;
import com.netflix.mediaclient.ui.push_notify.SocialOptInDialogFrag$OptInResponseHandler;
import com.netflix.mediaclient.ui.offline.TutorialHelper$Tutorialable;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecyclerProvider;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;
import com.netflix.mediaclient.util.IrisUtils$NotificationsListStatus;
import com.netflix.mediaclient.util.IrisUtils;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class HomeActivity$8 extends BroadcastReceiver
{
    final /* synthetic */ HomeActivity this$0;
    
    HomeActivity$8(final HomeActivity this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (Log.isLoggable()) {
            Log.v("HomeActivity", "notificationsListUpdateReceiver, onReceive");
        }
        final IrisUtils$NotificationsListStatus handleNotificationsUpdateReceiver = IrisUtils.handleNotificationsUpdateReceiver(intent, "HomeActivity");
        if (handleNotificationsUpdateReceiver != this.this$0.notificationsListStatus) {
            this.this$0.notificationsListStatus = handleNotificationsUpdateReceiver;
            if (Log.isLoggable()) {
                Log.v("HomeActivity", "Updating menu indicator with new status: " + handleNotificationsUpdateReceiver);
            }
            this.this$0.getNetflixActionBar().setSandwichIcon(this.this$0.notificationsListStatus == IrisUtils$NotificationsListStatus.HAS_UNREAD_MESSAGES);
        }
    }
}
