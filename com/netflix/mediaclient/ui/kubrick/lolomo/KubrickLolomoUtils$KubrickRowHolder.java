// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.lolomo;

import com.netflix.mediaclient.ui.lolomo.BaseLoLoMoAdapter$LoMoRowContent;
import android.view.View;
import android.widget.TextView;
import com.netflix.mediaclient.ui.lolomo.BaseLoLoMoAdapter$RowHolder;

class KubrickLolomoUtils$KubrickRowHolder extends BaseLoLoMoAdapter$RowHolder
{
    final TextView kubrickHeroTitle;
    final View topSpacer;
    
    protected KubrickLolomoUtils$KubrickRowHolder(final View view, final TextView textView, final BaseLoLoMoAdapter$LoMoRowContent baseLoLoMoAdapter$LoMoRowContent, final View view2, final TextView kubrickHeroTitle, final View topSpacer) {
        super(view, textView, baseLoLoMoAdapter$LoMoRowContent, view2);
        this.kubrickHeroTitle = kubrickHeroTitle;
        this.topSpacer = topSpacer;
    }
}
