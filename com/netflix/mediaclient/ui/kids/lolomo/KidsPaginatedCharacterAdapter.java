// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import com.netflix.mediaclient.ui.lomo.VideoViewGroup;
import com.netflix.mediaclient.servicemgr.Trackable;
import android.view.View;
import com.netflix.mediaclient.servicemgr.BasicLoMo;
import com.netflix.mediaclient.servicemgr.Video;
import java.util.List;
import com.netflix.mediaclient.android.widget.ViewRecycler;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.content.Context;
import com.netflix.mediaclient.ui.lomo.PaginatedLoMoAdapter;

public class KidsPaginatedCharacterAdapter extends PaginatedLoMoAdapter
{
    public KidsPaginatedCharacterAdapter(final Context context) {
        super(context);
    }
    
    @Override
    protected int computeNumItemsPerPage(final int n, final int n2) {
        return 2;
    }
    
    @Override
    public int getRowHeightInPx() {
        return KidsUtils.computeCharacterViewSize(this.activity, true);
    }
    
    @Override
    protected View getView(final ViewRecycler viewRecycler, final List<Video> list, final int n, final int n2, final BasicLoMo basicLoMo) {
        KidsCharacterViewGroup kidsCharacterViewGroup;
        if ((kidsCharacterViewGroup = (KidsCharacterViewGroup)viewRecycler.pop(KidsCharacterViewGroup.class)) == null) {
            kidsCharacterViewGroup = new KidsCharacterViewGroup((Context)this.getActivity(), true);
            kidsCharacterViewGroup.init(n);
        }
        ((VideoViewGroup<Video, V>)kidsCharacterViewGroup).updateDataThenViews(list, n, n2, this.getListViewPos(), basicLoMo);
        return (View)kidsCharacterViewGroup;
    }
}
