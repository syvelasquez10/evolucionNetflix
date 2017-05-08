// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.barker_kids.lolomo;

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
import android.content.Context;
import com.netflix.mediaclient.ui.lomo.PaginatedLoMoAdapter;

public class BarkerKidsPaginatedCharacterAdapter extends PaginatedLoMoAdapter
{
    public BarkerKidsPaginatedCharacterAdapter(final Context context) {
        super(context);
    }
    
    protected int computeNumItemsPerPage() {
        return MathUtils.divideIntsWithRounding(LoMoViewPager.computeViewPagerWidth(this.activity, true, LoMoUtils$LoMoWidthType.KUBRICK_KIDS_CHARACTER_ROW), this.getActivity().getResources().getDimensionPixelSize(2131427496) + this.getActivity().getResources().getDimensionPixelSize(2131427495));
    }
    
    public int getRowHeightInPx() {
        return this.getActivity().getResources().getDimensionPixelSize(2131427785) + this.getActivity().getResources().getDimensionPixelSize(2131427784) + this.getActivity().getResources().getDimensionPixelSize(2131427496);
    }
    
    protected View getView(final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final List<Video> list, final int n, final int n2, final BasicLoMo basicLoMo) {
        BarkerKidsCharacterViewGroup barkerKidsCharacterViewGroup;
        if ((barkerKidsCharacterViewGroup = ((ObjectRecycler<BarkerKidsCharacterViewGroup>)objectRecycler$ViewRecycler).pop(BarkerKidsCharacterViewGroup.class)) == null) {
            barkerKidsCharacterViewGroup = new BarkerKidsCharacterViewGroup((Context)this.getActivity());
            ViewUtils.setPaddingTop((View)barkerKidsCharacterViewGroup, this.getActivity().getResources().getDimensionPixelSize(2131427785));
            ViewUtils.setPaddingBottom((View)barkerKidsCharacterViewGroup, this.getActivity().getResources().getDimensionPixelSize(2131427784));
            barkerKidsCharacterViewGroup.init(n);
        }
        barkerKidsCharacterViewGroup.updateDataThenViews((List)list, n, n2, this.getListViewPos(), (Trackable)basicLoMo);
        return (View)barkerKidsCharacterViewGroup;
    }
}
