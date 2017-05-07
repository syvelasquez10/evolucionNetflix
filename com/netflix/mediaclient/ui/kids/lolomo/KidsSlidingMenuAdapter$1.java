// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import com.netflix.mediaclient.servicemgr.model.genre.GenreList;
import android.view.ViewStub;
import android.widget.TextView;
import com.netflix.mediaclient.ui.kubrick_kids.KubrickKidsUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.home.SlidingMenuAdapter$Holder;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import com.netflix.mediaclient.util.ViewUtils;
import android.support.v4.widget.DrawerLayout;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.home.SlidingMenuAdapter;
import android.app.Activity;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import android.view.View;
import android.view.View$OnClickListener;

class KidsSlidingMenuAdapter$1 implements View$OnClickListener
{
    final /* synthetic */ KidsSlidingMenuAdapter this$0;
    
    KidsSlidingMenuAdapter$1(final KidsSlidingMenuAdapter this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        this.this$0.activity.startActivity(KidsUtils.createExitKidsIntent(this.this$0.activity, UIViewLogging$UIViewCommandName.slidingMenuKidsExit));
    }
}
