// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;

class EpisodesFrag$4 implements SeasonsSpinnerAdapter$IViewCreator
{
    final /* synthetic */ EpisodesFrag this$0;
    
    EpisodesFrag$4(final EpisodesFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public View createItemView() {
        return ((LayoutInflater)this.this$0.getActivity().getSystemService("layout_inflater")).inflate(2130903210, (ViewGroup)null, false);
    }
}
