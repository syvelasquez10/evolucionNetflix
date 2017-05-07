// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import android.view.View;
import com.netflix.mediaclient.servicemgr.model.BasicLoMo;
import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.List;
import com.netflix.mediaclient.android.widget.ObjectRecycler;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.content.Context;
import com.netflix.mediaclient.ui.lomo.PaginatedLoMoAdapter;

public class KidsPaginatedLoMoAdapter extends PaginatedLoMoAdapter
{
    public KidsPaginatedLoMoAdapter(final Context context) {
        super(context);
    }
    
    @Override
    protected int computeNumItemsPerPage(final int n, final int n2) {
        return 1;
    }
    
    @Override
    public int getRowHeightInPx() {
        return KidsUtils.computeRowHeight(this.activity, true);
    }
    
    @Override
    protected View getView(final ObjectRecycler.ViewRecycler viewRecycler, final List<Video> list, final int n, final int n2, final BasicLoMo basicLoMo) {
        KidsLoMoViewGroup kidsLoMoViewGroup;
        if ((kidsLoMoViewGroup = ((ObjectRecycler<KidsLoMoViewGroup>)viewRecycler).pop(KidsLoMoViewGroup.class)) == null) {
            kidsLoMoViewGroup = new KidsLoMoViewGroup((Context)this.getActivity(), true);
            kidsLoMoViewGroup.init(n);
        }
        kidsLoMoViewGroup.updateDataThenViews(list, n, n2, this.getListViewPos(), basicLoMo);
        return (View)kidsLoMoViewGroup;
    }
}
