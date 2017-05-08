// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.ums;

import android.view.View;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.text.style.ClickableSpan;

final class EogLandingPage$1 extends ClickableSpan
{
    final /* synthetic */ NetflixActivity val$context;
    
    EogLandingPage$1(final NetflixActivity val$context) {
        this.val$context = val$context;
    }
    
    public void onClick(final View view) {
        new EogLandingPage$1$1(this).run();
    }
}
