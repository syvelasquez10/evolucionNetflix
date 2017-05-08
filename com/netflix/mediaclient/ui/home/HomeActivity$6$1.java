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
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import android.os.Handler;
import com.netflix.mediaclient.util.IrisUtils$NotificationsListStatus;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import android.content.BroadcastReceiver;
import android.support.v4.widget.DrawerLayout;
import java.util.LinkedList;
import com.netflix.mediaclient.service.logging.perf.InteractiveTracker$TTRTracker;
import com.netflix.mediaclient.ui.push_notify.SocialOptInDialogFrag$OptInResponseHandler;
import com.netflix.mediaclient.ui.offline.TutorialHelper$Tutorialable;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecyclerProvider;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;
import android.content.Context;
import android.content.Intent;
import com.netflix.mediaclient.ui.signup.OnRampActivity;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.model.leafs.OnRampEligibility;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

class HomeActivity$6$1 extends SimpleManagerCallback
{
    final /* synthetic */ HomeActivity$6 this$1;
    
    HomeActivity$6$1(final HomeActivity$6 this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onOnRampEligibilityAction(final OnRampEligibility onRampEligibility, final Status status) {
        this.this$1.this$0.hasCheckedOnRampEligibility = true;
        if (status != null && status.isSucces() && onRampEligibility != null && onRampEligibility.isEligible()) {
            this.this$1.this$0.startActivity(new Intent((Context)this.this$1.this$0, (Class)OnRampActivity.class));
            return;
        }
        this.this$1.this$0.showDialogsIfApplicable();
    }
}
