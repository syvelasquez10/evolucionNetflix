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
import com.netflix.model.branches.FalkorPerson;
import java.util.List;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import android.content.Context;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import android.view.View;
import android.widget.TextView;
import android.view.View$OnClickListener;

class MementoFrag$RelatedTitleView$1 implements View$OnClickListener
{
    final /* synthetic */ MementoFrag$RelatedTitleView this$1;
    final /* synthetic */ TextView val$moreInfo;
    
    MementoFrag$RelatedTitleView$1(final MementoFrag$RelatedTitleView this$1, final TextView val$moreInfo) {
        this.this$1 = this$1;
        this.val$moreInfo = val$moreInfo;
    }
    
    public void onClick(final View view) {
        final MiniPlayerControlsFrag miniPlayerControlsFrag = (MiniPlayerControlsFrag)this.this$1.this$0.getFragmentManager().findFragmentByTag("mini_player");
        if (miniPlayerControlsFrag != null) {
            if (this.this$1.this$0.leWrapper != null) {
                this.this$1.this$0.leWrapper.showLoadingView(true);
            }
            miniPlayerControlsFrag.fetchRelatedCollection((String)this.val$moreInfo.getTag(2131689496), (String)this.val$moreInfo.getTag(2131689497));
            if (this.this$1.this$0.isActivityValid()) {
                UIViewLogUtils.reportUIViewCommand((Context)this.this$1.this$0.getActivity(), UIViewLogging$UIViewCommandName.mementoRelatedMore, IClientLogging$ModalView.memento, this.this$1.this$0.getNetflixActivity().getDataContext());
            }
        }
    }
}
