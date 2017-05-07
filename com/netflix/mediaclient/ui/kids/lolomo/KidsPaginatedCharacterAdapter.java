// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import com.netflix.mediaclient.ui.lomo.VideoViewGroup;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import android.view.View;
import com.netflix.mediaclient.servicemgr.model.BasicLoMo;
import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.List;
import com.netflix.mediaclient.android.widget.ObjectRecycler;
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
    protected View getView(final ObjectRecycler.ViewRecycler viewRecycler, final List<Video> list, final int n, final int n2, final BasicLoMo basicLoMo) {
        KidsCharacterViewGroup kidsCharacterViewGroup;
        if ((kidsCharacterViewGroup = ((ObjectRecycler<KidsCharacterViewGroup>)viewRecycler).pop(KidsCharacterViewGroup.class)) == null) {
            kidsCharacterViewGroup = new KidsCharacterViewGroup((Context)this.getActivity(), true);
            kidsCharacterViewGroup.init(n);
        }
        ((VideoViewGroup<Video, V>)kidsCharacterViewGroup).updateDataThenViews(list, n, n2, this.getListViewPos(), basicLoMo);
        return (View)kidsCharacterViewGroup;
    }
}
