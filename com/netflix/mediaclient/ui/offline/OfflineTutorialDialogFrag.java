// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import android.view.ViewTreeObserver$OnPreDrawListener;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.DialogInterface;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;

public class OfflineTutorialDialogFrag extends NetflixDialogFrag
{
    private static final float DIALOG_SCREEN_WIDTH_RATIO = 0.95f;
    
    private void adjustModalWidthIfApplicable() {
        if (this.getDialog().getWindow() != null) {
            this.getDialog().getWindow().setLayout((int)(DeviceUtils.getScreenWidthInPixels((Context)this.getActivity()) * 0.95f), -2);
        }
    }
    
    public void dismiss() {
        super.dismiss();
        this.markAsSeen();
    }
    
    public void markAsSeen() {
        if (this.getNetflixActivity() != null) {
            this.getNetflixActivity().getTutorialHelper().setFullscreenTutorialDisplayed(true);
        }
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        this.markAsSeen();
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        this.getDialog().getWindow().setBackgroundDrawable((Drawable)new ColorDrawable(0));
        final View viewById = this.getDialog().findViewById(this.getActivity().getResources().getIdentifier("android:id/titleDivider", (String)null, (String)null));
        if (viewById != null) {
            viewById.setBackgroundColor(this.getResources().getColor(2131624173));
        }
        final View bind = new OfflineTutorialContentBinder((Context)this.getActivity()).bind(viewGroup, this);
        bind.getViewTreeObserver().addOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)new OfflineTutorialDialogFrag$1(this, bind));
        return bind;
    }
    
    public void showAvailableToDownload() {
        if (this.getActivity() != null) {
            OfflineUiHelper.showAvailableDownloadsGenreList(this.getNetflixActivity());
        }
    }
}
