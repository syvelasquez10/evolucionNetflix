// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import android.view.View;
import com.netflix.mediaclient.servicemgr.model.BasicLoMo;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import java.util.List;
import com.netflix.mediaclient.android.widget.ViewRecycler;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.content.Context;
import com.netflix.mediaclient.ui.lomo.PaginatedCwAdapter;

public class KidsPaginatedCwAdapter extends PaginatedCwAdapter
{
    public KidsPaginatedCwAdapter(final Context context) {
        super(context);
    }
    
    @Override
    protected int computeNumItemsPerPage(final int n, final int n2) {
        return 1;
    }
    
    @Override
    public int getCount() {
        return Math.min(super.getCount(), 3);
    }
    
    @Override
    public int getRowHeightInPx() {
        return KidsUtils.computeHorizontalRowHeight(this.activity, true);
    }
    
    @Override
    protected View getView(final ViewRecycler viewRecycler, final List<CWVideo> list, final int n, final int n2, final BasicLoMo basicLoMo) {
        KidsCwViewGroup kidsCwViewGroup;
        if ((kidsCwViewGroup = (KidsCwViewGroup)viewRecycler.pop(KidsCwViewGroup.class)) == null) {
            kidsCwViewGroup = new KidsCwViewGroup((Context)this.getActivity(), true);
            kidsCwViewGroup.init(n);
        }
        kidsCwViewGroup.updateDataThenViews(list, n, n2, this.getListViewPos(), basicLoMo);
        return (View)kidsCwViewGroup;
    }
}
