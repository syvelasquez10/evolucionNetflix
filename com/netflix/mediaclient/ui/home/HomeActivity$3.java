// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import android.view.View;
import com.netflix.mediaclient.ui.search.SearchMenu;
import com.netflix.mediaclient.ui.mdx.MdxMenu;
import android.view.Menu;
import android.support.v4.widget.DrawerLayout$DrawerListener;
import android.app.Activity;
import com.netflix.mediaclient.ui.kids.lolomo.KidsSlidingMenuAdapter;
import java.util.Collection;
import android.os.SystemClock;
import android.os.Bundle;
import android.content.res.Configuration;
import android.view.KeyEvent;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.android.widget.NetflixActionBar$LogoType;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import android.app.Fragment;
import android.os.Parcelable;
import com.netflix.mediaclient.util.SocialNotificationsUtils;
import android.widget.Toast;
import java.io.Serializable;
import com.netflix.mediaclient.ui.kubrick.lomo.KubrickHomeActivity;
import com.netflix.mediaclient.ui.kids.lolomo.KidsHomeActivity;
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.view.MenuItem;
import android.content.DialogInterface$OnClickListener;
import com.netflix.mediaclient.servicemgr.model.genre.GenreList;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import java.util.LinkedList;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecyclerProvider;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class HomeActivity$3 extends BroadcastReceiver
{
    final /* synthetic */ HomeActivity this$0;
    
    HomeActivity$3(final HomeActivity this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (intent == null) {
            Log.w("HomeActivity", "Received null intent");
        }
        else {
            final String action = intent.getAction();
            Log.i("HomeActivity", "homeUpdatedReceiver invoked and received Intent with Action " + action);
            if ("com.netflix.mediaclient.intent.action.HOME_LOLOMO_UPDATED".equals(action) && this.this$0.drawerLayout != null) {
                this.this$0.drawerLayout.closeDrawers();
            }
        }
    }
}
