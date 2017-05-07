// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.app.Activity;
import com.netflix.mediaclient.ui.kids.KidsUtils$OnSwitchToKidsClickListener;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.widget.ListAdapter;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import java.util.List;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.widget.AdapterView$OnItemClickListener;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.ListView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import android.annotation.SuppressLint;

class OldSlidingMenu$5$1 implements Runnable
{
    final /* synthetic */ OldSlidingMenu$5 this$1;
    
    OldSlidingMenu$5$1(final OldSlidingMenu$5 this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void run() {
        this.this$1.this$0.drawerLayout.closeDrawers();
    }
}
