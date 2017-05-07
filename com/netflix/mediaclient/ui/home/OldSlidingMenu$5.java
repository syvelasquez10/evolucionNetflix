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
import android.view.View$OnClickListener;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.ListView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import android.annotation.SuppressLint;
import android.support.v4.widget.DrawerLayout;
import android.os.Build$VERSION;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView$OnItemClickListener;

class OldSlidingMenu$5 implements AdapterView$OnItemClickListener
{
    final /* synthetic */ OldSlidingMenu this$0;
    
    OldSlidingMenu$5(final OldSlidingMenu this$0) {
        this.this$0 = this$0;
    }
    
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, long n2) {
        HomeActivity.showGenreList(this.this$0.activity, this.this$0.adapter.getItem(n - this.this$0.list.getHeaderViewsCount()));
        final DrawerLayout access$000 = this.this$0.drawerLayout;
        final OldSlidingMenu$5$1 oldSlidingMenu$5$1 = new OldSlidingMenu$5$1(this);
        if (Build$VERSION.SDK_INT >= 21) {
            n2 = 300L;
        }
        else {
            n2 = 0L;
        }
        access$000.postDelayed((Runnable)oldSlidingMenu$5$1, n2);
    }
}
