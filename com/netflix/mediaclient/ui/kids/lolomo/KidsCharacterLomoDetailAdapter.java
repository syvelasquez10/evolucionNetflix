// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import com.netflix.mediaclient.servicemgr.Video;
import java.util.List;
import com.netflix.mediaclient.util.MathUtils;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.content.Context;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup;
import com.netflix.mediaclient.servicemgr.BasicLoMo;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;

public class KidsCharacterLomoDetailAdapter extends KidsLomoDetailAdapter
{
    public KidsCharacterLomoDetailAdapter(final LoLoMoFrag loLoMoFrag, final BasicLoMo basicLoMo) {
        super(loLoMoFrag, basicLoMo);
    }
    
    @Override
    protected VideoViewGroup<?, ?> createVideoViewGroup() {
        final KidsCharacterViewGroup kidsCharacterViewGroup = new KidsCharacterViewGroup((Context)this.activity, false);
        kidsCharacterViewGroup.init(2);
        kidsCharacterViewGroup.setPadding(this.activity.getResources().getDimensionPixelSize(2131361918), 0, 0, 0);
        kidsCharacterViewGroup.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, KidsUtils.computeSkidmarkCharacterViewSize(this.activity)));
        return kidsCharacterViewGroup;
    }
    
    @Override
    public int getCount() {
        return MathUtils.ceiling(super.getCount(), 2);
    }
    
    @Override
    public List<Video> getItem(final int n) {
        return this.videoData.subList(n * 2, n * 2 + 2);
    }
    
    @Override
    protected int getNumItemsPerPage() {
        return 2;
    }
    
    @Override
    protected boolean shouldReportPresentationTracking() {
        return false;
    }
}
