// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import java.io.Serializable;
import com.netflix.mediaclient.ui.search.SearchMenu;
import com.netflix.mediaclient.ui.mdx.MdxMenu;
import android.view.Menu;
import java.util.Collection;
import android.os.SystemClock;
import android.os.Bundle;
import android.view.KeyEvent;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.ui.kubrick.lolomo.KubrickHomeActionBar;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.android.widget.NetflixActionBar$LogoType;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import android.app.Fragment;
import android.os.Parcelable;
import android.widget.Toast;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Context;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.content.DialogInterface$OnClickListener;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import android.support.v4.widget.DrawerLayout;
import android.content.Intent;
import java.util.LinkedList;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecyclerProvider;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;
import android.os.Handler;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.SocialUtils$NotificationsListStatus;
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
        this.this$0.cancelMarkingNotificationsAsRead();
    }
    
    @Override
    public void onDrawerOpened(final View view) {
        if (this.this$0.notificationsListStatus == SocialUtils$NotificationsListStatus.HAS_UNREAD_MESSAGES) {
            Log.i("HomeActivity", "Drawer was opened - scheduling a timer to mark all visible notifications as read");
            this.this$0.readRunnable = new HomeActivity$1$1(this);
            this.this$0.readRunnableHandler = new Handler();
            this.this$0.readRunnableHandler.postDelayed(this.this$0.readRunnable, 3000L);
        }
    }
    
    @Override
    public void onDrawerSlide(final View view, final float n) {
    }
    
    @Override
    public void onDrawerStateChanged(final int n) {
    }
}
