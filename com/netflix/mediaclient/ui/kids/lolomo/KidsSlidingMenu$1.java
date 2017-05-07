// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import android.view.ViewStub;
import android.widget.TextView;
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.home.OldSlidingMenu$Holder;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import com.netflix.mediaclient.util.ViewUtils;
import android.support.v4.widget.DrawerLayout;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.home.OldSlidingMenu;
import android.app.Activity;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import android.view.View;
import android.view.View$OnClickListener;

class KidsSlidingMenu$1 implements View$OnClickListener
{
    final /* synthetic */ KidsSlidingMenu this$0;
    
    KidsSlidingMenu$1(final KidsSlidingMenu this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        this.this$0.activity.startActivity(KidsUtils.createExitKidsIntent(this.this$0.activity, UIViewLogging$UIViewCommandName.slidingMenuKidsExit));
    }
}
