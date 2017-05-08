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
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.android.widget.NetflixActionBar$LogoType;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.android.app.CommonStatus;
import android.app.Fragment;
import android.os.Parcelable;
import android.support.v4.widget.DrawerLayout$DrawerListener;
import com.netflix.mediaclient.service.logging.perf.InteractiveTracker$InteractiveListener;
import android.widget.Toast;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import android.os.Handler;
import com.netflix.mediaclient.util.IrisUtils$NotificationsListStatus;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import android.content.BroadcastReceiver;
import android.support.v4.widget.DrawerLayout;
import android.content.Intent;
import java.util.LinkedList;
import com.netflix.mediaclient.service.logging.perf.InteractiveTracker$TTRTracker;
import com.netflix.mediaclient.ui.push_notify.SocialOptInDialogFrag$OptInResponseHandler;
import com.netflix.mediaclient.ui.offline.TutorialHelper$Tutorialable;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecyclerProvider;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;
import java.util.Map;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.Context;
import com.netflix.mediaclient.ui.survey.SurveyActivity;
import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.model.leafs.OnRampEligibility$Action;
import android.app.Activity;
import com.netflix.mediaclient.ui.signup.OnRampActivity;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;

class HomeActivity$6 implements ManagerStatusListener
{
    final /* synthetic */ HomeActivity this$0;
    
    HomeActivity$6(final HomeActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        Log.v("HomeActivity", "ServiceManager ready");
        this.this$0.manager = serviceManager;
        this.this$0.mDialogManager = new DialogManager(this.this$0);
        if (OnRampActivity.shouldShowOnRamp(serviceManager, this.this$0)) {
            serviceManager.doOnRampEligibilityAction(OnRampEligibility$Action.FETCH, new HomeActivity$6$1(this));
        }
        else {
            this.this$0.hasCheckedOnRampEligibility = true;
            this.this$0.showDialogsIfApplicable();
        }
        this.this$0.setupTTRTracking();
        this.this$0.showProfileToast();
        this.this$0.leaveExperienceBreadcrumb();
        this.this$0.reportUiViewChanged(this.this$0.getCurrentViewType());
        this.this$0.getPrimaryFrag().onManagerReady(serviceManager, status);
        this.this$0.slidingMenuAdapter.onManagerReady(serviceManager, status);
        this.this$0.setLoadingStatusCallback(new HomeActivity$6$2(this));
        if (SurveyActivity.shouldShowSurvey((Context)this.this$0, serviceManager)) {
            SurveyActivity.makeSurveyRequestAndShow(this.this$0);
        }
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
        PerformanceProfiler.getInstance().endSession(Sessions.TTI, PerformanceProfiler.createFailedMap());
        PerformanceProfiler.getInstance().endSession(Sessions.LOLOMO_LOAD, null);
        Log.w("HomeActivity", "ServiceManager unavailable");
        this.this$0.manager = null;
        this.this$0.getPrimaryFrag().onManagerUnavailable(serviceManager, status);
        this.this$0.slidingMenuAdapter.onManagerUnavailable(serviceManager, status);
        Log.d("HomeActivity", "LOLOMO failed, report UI startup session ended in case this was on UI startup");
    }
}
