// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import android.os.Parcelable;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.LoMo;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;

public class KidsLomoDetailFrag extends LoLoMoFrag
{
    private static final String EXTRA_LOMO_PARCELABLE = "lomo_parcelable";
    private LoMo lomo;
    
    public static KidsLomoDetailFrag create(final LoMo loMo) {
        final KidsLomoDetailFrag kidsLomoDetailFrag = new KidsLomoDetailFrag();
        final Bundle arguments = new Bundle();
        arguments.putParcelable("lomo_parcelable", (Parcelable)loMo);
        kidsLomoDetailFrag.setArguments(arguments);
        return kidsLomoDetailFrag;
    }
    
    @Override
    protected ILoLoMoAdapter createAdapter() {
        return new KidsLomoDetailAdapter(this, this.lomo);
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.lomo = (LoMo)this.getArguments().getParcelable("lomo_parcelable");
    }
}
