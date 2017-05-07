// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus;

import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.q;
import com.google.android.gms.plus.internal.g;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.content.Context;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View$OnClickListener;

public class PlusOneButton$DefaultOnPlusOneClickListener implements View$OnClickListener, PlusOneButton$OnPlusOneClickListener
{
    private final PlusOneButton$OnPlusOneClickListener ale;
    final /* synthetic */ PlusOneButton alf;
    
    public PlusOneButton$DefaultOnPlusOneClickListener(final PlusOneButton alf, final PlusOneButton$OnPlusOneClickListener ale) {
        this.alf = alf;
        this.ale = ale;
    }
    
    public void onClick(final View view) {
        final Intent intent = (Intent)this.alf.ala.getTag();
        if (this.ale != null) {
            this.ale.onPlusOneClick(intent);
            return;
        }
        this.onPlusOneClick(intent);
    }
    
    public void onPlusOneClick(final Intent intent) {
        final Context context = this.alf.getContext();
        if (context instanceof Activity && intent != null) {
            ((Activity)context).startActivityForResult(intent, this.alf.alc);
        }
    }
}
