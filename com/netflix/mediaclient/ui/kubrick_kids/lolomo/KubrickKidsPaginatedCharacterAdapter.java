// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.lolomo;

import com.netflix.mediaclient.ui.lomo.VideoViewGroup;
import com.netflix.mediaclient.android.widget.ObjectRecycler;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.BasicLoMo;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.List;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.util.MathUtils;
import com.netflix.mediaclient.ui.lomo.LoMoViewPager;
import com.netflix.mediaclient.ui.lomo.LoMoUtils$LoMoWidthType;
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import android.content.Context;
import com.netflix.mediaclient.ui.lomo.PaginatedLoMoAdapter;

public class KubrickKidsPaginatedCharacterAdapter extends PaginatedLoMoAdapter
{
    public KubrickKidsPaginatedCharacterAdapter(final Context context) {
        super(context);
    }
    
    @Override
    protected int computeNumItemsPerPage() {
        return KubrickUtils.computeNumCharactersPerPage(this.activity);
    }
    
    @Override
    public int getRowHeightInPx() {
        return this.getActivity().getResources().getDimensionPixelSize(2131296479) + MathUtils.divideIntsWithRounding(LoMoViewPager.computeViewPagerWidth(this.activity, true, LoMoUtils$LoMoWidthType.KUBRICK_KIDS_CHARACTER_ROW), this.numItemsPerPage);
    }
    
    @Override
    protected View getView(final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final List<Video> list, final int n, final int n2, final BasicLoMo basicLoMo) {
        KubrickKidsCharacterViewGroup kubrickKidsCharacterViewGroup;
        if ((kubrickKidsCharacterViewGroup = ((ObjectRecycler<KubrickKidsCharacterViewGroup>)objectRecycler$ViewRecycler).pop(KubrickKidsCharacterViewGroup.class)) == null) {
            kubrickKidsCharacterViewGroup = new KubrickKidsCharacterViewGroup((Context)this.getActivity());
            ViewUtils.setPaddingTop((View)kubrickKidsCharacterViewGroup, this.getActivity().getResources().getDimensionPixelSize(2131296479));
            kubrickKidsCharacterViewGroup.init(n);
        }
        ((VideoViewGroup<Video, V>)kubrickKidsCharacterViewGroup).updateDataThenViews(list, n, n2, this.getListViewPos(), basicLoMo);
        return (View)kubrickKidsCharacterViewGroup;
    }
}
