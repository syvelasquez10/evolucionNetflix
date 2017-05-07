// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.lolomo;

import com.netflix.mediaclient.ui.lolomo.BaseLoLoMoAdapter$LoMoRowContent;
import android.widget.TextView;
import android.view.View;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.lolomo.BaseLoLoMoAdapter$RowHolder;

class KubrickLolomoUtils$KubrickRowHolder extends BaseLoLoMoAdapter$RowHolder
{
    private final NetflixActivity activity;
    private final View topSpacer;
    
    protected KubrickLolomoUtils$KubrickRowHolder(final NetflixActivity activity, final View view, final TextView textView, final BaseLoLoMoAdapter$LoMoRowContent baseLoLoMoAdapter$LoMoRowContent, final View view2, final View topSpacer) {
        super(view, textView, baseLoLoMoAdapter$LoMoRowContent, view2);
        this.activity = activity;
        this.topSpacer = topSpacer;
    }
}
