// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.details;

import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;
import com.netflix.mediaclient.ui.details.SeasonsSpinnerAdapter$IViewCreator;

class BarkerKidsShowDetailsFrag$3 implements SeasonsSpinnerAdapter$IViewCreator
{
    final /* synthetic */ BarkerKidsShowDetailsFrag this$0;
    
    BarkerKidsShowDetailsFrag$3(final BarkerKidsShowDetailsFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public View createItemView() {
        return ((LayoutInflater)this.this$0.getActivity().getSystemService("layout_inflater")).inflate(2130903163, (ViewGroup)null, false);
    }
}
