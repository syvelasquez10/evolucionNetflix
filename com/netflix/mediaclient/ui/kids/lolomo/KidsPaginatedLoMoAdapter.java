// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import com.netflix.mediaclient.ui.lomo.VideoViewGroup;
import android.widget.LinearLayout;
import com.netflix.mediaclient.servicemgr.Trackable;
import com.netflix.mediaclient.servicemgr.LoMoType;
import android.view.View;
import com.netflix.mediaclient.servicemgr.BasicLoMo;
import com.netflix.mediaclient.servicemgr.Video;
import java.util.List;
import com.netflix.mediaclient.android.widget.ViewRecycler;
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
    protected View getView(final ViewRecycler viewRecycler, final List<Video> list, final int n, final int n2, final BasicLoMo basicLoMo) {
        LinearLayout linearLayout;
        if (basicLoMo.getType() == LoMoType.CHARACTERS) {
            if ((linearLayout = (KidsCharacterViewGroup)viewRecycler.pop(KidsCharacterViewGroup.class)) == null) {
                linearLayout = new KidsCharacterViewGroup((Context)this.getActivity(), true);
                ((VideoViewGroup)linearLayout).init(n);
            }
        }
        else if ((linearLayout = (KidsLoMoViewGroup)viewRecycler.pop(KidsLoMoViewGroup.class)) == null) {
            linearLayout = new KidsLoMoViewGroup((Context)this.getActivity(), true);
            ((VideoViewGroup)linearLayout).init(n);
        }
        ((VideoViewGroup<Video, V>)linearLayout).updateDataThenViews(list, n, n2, this.getListViewPos(), basicLoMo);
        return (View)linearLayout;
    }
}
