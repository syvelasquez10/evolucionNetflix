// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.view.ViewTreeObserver$OnPreDrawListener;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.DialogInterface;
import android.widget.FrameLayout$LayoutParams;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.ViewGroup$LayoutParams;
import android.view.WindowManager$LayoutParams;
import android.view.View;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;

public abstract class BaseTutorialDialogFrag extends NetflixDialogFrag
{
    private void adjustModalWidthIfApplicable(final View view) {
        if (this.getDialog().getWindow() != null && this.getDialog().getWindow().getDecorView() != null) {
            this.getDialog().getWindow().getDecorView().setLayoutParams((ViewGroup$LayoutParams)new WindowManager$LayoutParams(-1, -1));
            final WindowManager$LayoutParams attributes = this.getDialog().getWindow().getAttributes();
            attributes.width = -1;
            attributes.height = -1;
            attributes.x = 0;
            attributes.y = 0;
            attributes.gravity = 17;
        }
        final FrameLayout$LayoutParams layoutParams = new FrameLayout$LayoutParams((int)(this.getResources().getFraction(2131623936, 1, 1) * DeviceUtils.getScreenWidthInPixels((Context)this.getActivity())), -2);
        layoutParams.gravity = 17;
        view.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
    }
    
    public void dismiss() {
        super.dismiss();
        this.markAsSeen();
    }
    
    public void markAsSeen() {
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        this.markAsSeen();
    }
    
    public abstract View onCreateView(final LayoutInflater p0, final ViewGroup p1, final Bundle p2);
    
    public void onViewCreated(final View view, final Bundle bundle) {
        super.onViewCreated(view, bundle);
        view.getViewTreeObserver().addOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)new BaseTutorialDialogFrag$1(this, view));
    }
}
