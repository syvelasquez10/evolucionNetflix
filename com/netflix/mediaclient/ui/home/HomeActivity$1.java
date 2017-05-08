// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import com.netflix.mediaclient.NetflixApplication;
import java.io.Serializable;
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
import com.netflix.mediaclient.ui.lolomo.KidsGenresLoMoFrag;
import java.util.Map;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.ui.kubrick.lolomo.BarkerHomeActionBar;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.android.widget.NetflixActionBar$LogoType;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.android.app.CommonStatus;
import android.app.Fragment;
import android.os.Parcelable;
import android.app.Activity;
import android.widget.Toast;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import android.support.v4.widget.DrawerLayout;
import android.content.Intent;
import java.util.LinkedList;
import com.netflix.mediaclient.ui.push_notify.SocialOptInDialogFrag$OptInResponseHandler;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecyclerProvider;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;
import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.uiview.model.CommandEndedEvent$InputValue;
import android.os.Handler;
import com.netflix.mediaclient.util.IrisUtils$NotificationsListStatus;
import android.content.Context;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.Log;
import android.view.View;
import android.support.v4.widget.DrawerLayout$DrawerListener;

class HomeActivity$1 implements DrawerLayout$DrawerListener
{
    final /* synthetic */ HomeActivity this$0;
    
    HomeActivity$1(final HomeActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onDrawerClosed(final View view) {
        Log.i("HomeActivity", "onDrawerClosed");
        if (this.this$0.slidingMenuAdapter instanceof StandardSlidingMenu) {
            this.this$0.cancelMarkingNotificationsAsRead();
            ((StandardSlidingMenu)this.this$0.slidingMenuAdapter).reportNotificationsImpression(false);
            UIViewLogUtils.reportLeftMenuImpressionEnded((Context)this.this$0, true);
            UIViewLogUtils.reportLeftMenuUIViewCommandEnded((Context)this.this$0);
        }
    }
    
    @Override
    public void onDrawerOpened(View view) {
        Log.i("HomeActivity", "onDrawerOpened");
        if (this.this$0.notificationsListStatus == IrisUtils$NotificationsListStatus.HAS_UNREAD_MESSAGES) {
            Log.i("HomeActivity", "Drawer was opened - scheduling a timer to mark all visible notifications as read");
            this.this$0.readRunnable = new HomeActivity$1$1(this);
            this.this$0.readRunnableHandler = new Handler();
            this.this$0.readRunnableHandler.postDelayed(this.this$0.readRunnable, 3000L);
        }
        if (!(this.this$0.slidingMenuAdapter instanceof StandardSlidingMenu)) {
            return;
        }
        final HomeActivity this$0 = this.this$0;
        Label_0204: {
            if (!this.this$0.bWasHamburgerClicked) {
                break Label_0204;
            }
            view = (View)CommandEndedEvent$InputValue.touch;
            while (true) {
                UIViewLogUtils.reportLeftMenuUIViewCommandStarted((Context)this$0, (CommandEndedEvent$InputValue)view);
                this.this$0.bWasHamburgerClicked = false;
                view = (View)new JSONObject();
                while (true) {
                    try {
                        ((JSONObject)view).put("notifications", this.this$0.notificationsListStatus != IrisUtils$NotificationsListStatus.NO_MESSAGES);
                        ((JSONObject)view).put("unreadNotifications", this.this$0.notificationsListStatus == IrisUtils$NotificationsListStatus.HAS_UNREAD_MESSAGES);
                        UIViewLogUtils.reportLeftMenuImpressionStarted((Context)this.this$0, (JSONObject)view);
                        ((StandardSlidingMenu)this.this$0.slidingMenuAdapter).reportNotificationsImpression(true);
                        return;
                        view = (View)CommandEndedEvent$InputValue.swipe;
                    }
                    catch (JSONException ex) {
                        Log.w("HomeActivity", "setupViews() Got exception", (Throwable)ex);
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    @Override
    public void onDrawerSlide(final View view, final float n) {
    }
    
    @Override
    public void onDrawerStateChanged(final int n) {
    }
}
