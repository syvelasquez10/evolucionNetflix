// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.DialogInterface;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import android.view.View;
import android.view.ViewTreeObserver$OnPreDrawListener;

class OfflineTutorialDialogFrag$1 implements ViewTreeObserver$OnPreDrawListener
{
    final /* synthetic */ OfflineTutorialDialogFrag this$0;
    final /* synthetic */ View val$view;
    
    OfflineTutorialDialogFrag$1(final OfflineTutorialDialogFrag this$0, final View val$view) {
        this.this$0 = this$0;
        this.val$view = val$view;
    }
    
    public boolean onPreDraw() {
        this.val$view.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this);
        this.this$0.adjustModalWidthIfApplicable();
        return true;
    }
}
