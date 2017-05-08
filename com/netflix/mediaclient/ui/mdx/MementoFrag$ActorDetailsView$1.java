// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.view.ViewGroup;
import android.view.LayoutInflater;
import java.util.Iterator;
import android.view.ViewGroup$LayoutParams;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.kubrick.BarkerUtils;
import android.widget.ImageView;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.os.Handler;
import android.os.Bundle;
import com.netflix.mediaclient.ui.details.RoleDetailsFrag;
import android.support.design.widget.TabLayout$OnTabSelectedListener;
import android.widget.TextView;
import com.viewpagerindicator.android.osp.ViewPager$PageTransformer;
import android.support.v4.view.PagerAdapter;
import com.viewpagerindicator.android.osp.ViewPager$OnPageChangeListener;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import java.util.ArrayList;
import android.support.design.widget.TabLayout;
import com.netflix.model.branches.FalkorActorStill;
import com.netflix.model.branches.MementoVideoSwatch;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.android.osp.ViewPager;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import java.util.List;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import android.content.Context;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import android.view.View;
import com.netflix.model.branches.FalkorPerson;
import android.view.View$OnClickListener;

class MementoFrag$ActorDetailsView$1 implements View$OnClickListener
{
    final /* synthetic */ MementoFrag$ActorDetailsView this$1;
    final /* synthetic */ FalkorPerson val$actor;
    
    MementoFrag$ActorDetailsView$1(final MementoFrag$ActorDetailsView this$1, final FalkorPerson val$actor) {
        this.this$1 = this$1;
        this.val$actor = val$actor;
    }
    
    public void onClick(final View view) {
        this.this$1.this$0.showRDP(this.val$actor.detail.getId());
        if (this.this$1.this$0.isActivityValid()) {
            UIViewLogUtils.reportUIViewCommand((Context)this.this$1.this$0.getActivity(), UIViewLogging$UIViewCommandName.mementoGoToRDP, IClientLogging$ModalView.memento, this.this$1.this$0.getNetflixActivity().getDataContext());
        }
    }
}
