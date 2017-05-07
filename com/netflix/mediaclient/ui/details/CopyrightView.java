// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.app.AlertDialog$Builder;
import android.view.WindowManager$LayoutParams;
import android.app.AlertDialog;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.view.ViewGroup;
import android.widget.TextView;

class CopyrightView
{
    private TextView copyrightTextView;
    private ViewGroup copyrightViewGroup;
    private VideoDetails details;
    private int expandedYOffset;
    
    public CopyrightView(final VideoDetails details, final Context context, final ViewGroup copyrightViewGroup) {
        this.copyrightViewGroup = copyrightViewGroup;
        this.details = details;
        if (copyrightViewGroup != null) {
            this.copyrightTextView = (TextView)copyrightViewGroup.findViewById(2131427846);
            if (this.copyrightTextView != null) {
                this.copyrightTextView.setText((CharSequence)context.getResources().getString(2131493393, new Object[] { details.getCopyright() }));
                this.copyrightTextView.setVisibility(0);
                this.addExpandedCopyright(context);
            }
        }
    }
    
    private void addExpandedCopyright(final Context context) {
        this.copyrightTextView.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new CopyrightView$1(this, context));
    }
    
    public static CopyrightView create(final VideoDetails videoDetails, final Context context) {
        final ViewGroup viewGroup = (ViewGroup)((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2130903208, (ViewGroup)null);
        if (viewGroup != null) {
            return new CopyrightView(videoDetails, context, viewGroup);
        }
        return null;
    }
    
    private void setExpandedText(final VideoDetails videoDetails, final Context context, final View view) {
        final TextView textView = (TextView)view.findViewById(2131427847);
        if (textView != null) {
            textView.setText((CharSequence)context.getResources().getString(2131493393, new Object[] { videoDetails.getCopyright() }));
        }
    }
    
    private void setScreenLocation(final AlertDialog alertDialog) {
        final int[] array = new int[2];
        this.copyrightViewGroup.getLocationOnScreen(array);
        final WindowManager$LayoutParams attributes = alertDialog.getWindow().getAttributes();
        attributes.gravity = 51;
        attributes.x = array[0];
        attributes.y = array[1] - this.expandedYOffset;
    }
    
    private void showExpandedCopyrightPopup(final VideoDetails videoDetails, final Context context) {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(context);
        final View inflate = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2130903209, (ViewGroup)null);
        if (inflate == null) {
            return;
        }
        this.setExpandedText(videoDetails, context, inflate);
        alertDialog$Builder.setView(inflate);
        final AlertDialog create = alertDialog$Builder.create();
        this.setScreenLocation(create);
        create.show();
    }
    
    public View getView() {
        return (View)this.copyrightViewGroup;
    }
}
