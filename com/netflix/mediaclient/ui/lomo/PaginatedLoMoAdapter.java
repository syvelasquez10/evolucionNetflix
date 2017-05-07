// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.android.widget.ObjectRecycler;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import android.view.View;
import com.netflix.mediaclient.servicemgr.model.BasicLoMo;
import java.util.List;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.model.Video;

public class PaginatedLoMoAdapter extends BasePaginatedAdapter<Video>
{
    public PaginatedLoMoAdapter(final Context context) {
        super(context);
    }
    
    @Override
    protected int computeNumItemsPerPage() {
        return LomoConfig.computeStandardNumVideosPerPage(this.activity, false);
    }
    
    @Override
    protected int computeNumVideosToFetchPerBatch(final int n) {
        return LomoConfig.computeNumVideosToFetchPerBatch(this.activity, LoMoType.STANDARD);
    }
    
    @Override
    protected View getView(final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final List<Video> list, final int n, final int n2, final BasicLoMo basicLoMo) {
        LoMoViewGroup loMoViewGroup;
        if ((loMoViewGroup = ((ObjectRecycler<LoMoViewGroup>)objectRecycler$ViewRecycler).pop(LoMoViewGroup.class)) == null) {
            loMoViewGroup = new LoMoViewGroup((Context)this.getActivity());
            loMoViewGroup.init(n);
        }
        ((VideoViewGroup<Video, V>)loMoViewGroup).updateDataThenViews(list, n, n2, this.getListViewPos(), basicLoMo);
        return (View)loMoViewGroup;
    }
}
