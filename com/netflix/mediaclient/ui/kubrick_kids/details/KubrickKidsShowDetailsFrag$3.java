// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.details;

import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;
import com.netflix.mediaclient.ui.details.SeasonsSpinnerAdapter$IViewCreator;

class KubrickKidsShowDetailsFrag$3 implements SeasonsSpinnerAdapter$IViewCreator
{
    final /* synthetic */ KubrickKidsShowDetailsFrag this$0;
    
    KubrickKidsShowDetailsFrag$3(final KubrickKidsShowDetailsFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public View createItemView() {
        return ((LayoutInflater)this.this$0.getActivity().getSystemService("layout_inflater")).inflate(2130903129, (ViewGroup)null, false);
    }
}
