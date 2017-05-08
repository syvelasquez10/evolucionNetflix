// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import android.view.ViewTreeObserver$OnPreDrawListener;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
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

public class OfflineTutorialDialogFrag extends NetflixDialogFrag
{
    private static final float DIALOG_SCREEN_WIDTH_RATIO = 0.95f;
    
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
        final FrameLayout$LayoutParams layoutParams = new FrameLayout$LayoutParams((int)(DeviceUtils.getScreenWidthInPixels((Context)this.getActivity()) * 0.95f), -2);
        layoutParams.gravity = 17;
        view.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
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
        this.getDialog().getWindow().setBackgroundDrawable((Drawable)new ColorDrawable(this.getActivity().getResources().getColor(2131624166)));
        this.setStyle(0, 16973840);
        final View viewById = this.getDialog().findViewById(this.getActivity().getResources().getIdentifier("android:id/titleDivider", (String)null, (String)null));
        if (viewById != null) {
            viewById.setBackgroundColor(this.getResources().getColor(2131624173));
        }
        return new OfflineTutorialContentBinder((Context)this.getActivity()).bind(viewGroup, this);
    }
    
    public void onViewCreated(final View view, final Bundle bundle) {
        super.onViewCreated(view, bundle);
        view.getViewTreeObserver().addOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)new OfflineTutorialDialogFrag$1(this, view));
    }
    
    public void showAvailableToDownload() {
        if (this.getActivity() != null) {
            OfflineUiHelper.showAvailableDownloadsGenreList(this.getNetflixActivity());
        }
    }
}
