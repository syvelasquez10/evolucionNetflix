// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.app.AlertDialog$Builder;
import android.view.WindowManager$LayoutParams;
import android.app.AlertDialog;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.view.View;
import android.view.LayoutInflater;
import android.view.View$OnLayoutChangeListener;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.view.ViewGroup;
import android.widget.TextView;

public class CopyrightView
{
    private TextView copyrightTextView;
    private ViewGroup copyrightViewGroup;
    private VideoDetails details;
    private int expandedYOffset;
    private boolean isCentered;
    
    public CopyrightView(final VideoDetails details, final Context context, final ViewGroup copyrightViewGroup) {
        this.copyrightViewGroup = copyrightViewGroup;
        this.details = details;
        this.init();
    }
    
    private void addExpandedCopyright(final Context context) {
        this.copyrightTextView.addOnLayoutChangeListener((View$OnLayoutChangeListener)new CopyrightView$1(this, context));
    }
    
    public static CopyrightView create(final VideoDetails videoDetails, final Context context) {
        final ViewGroup viewGroup = (ViewGroup)((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2130903288, (ViewGroup)null);
        if (viewGroup != null) {
            return new CopyrightView(videoDetails, context, viewGroup);
        }
        return null;
    }
    
    private void init() {
        if (this.details != null && this.copyrightViewGroup != null) {
            this.copyrightTextView = (TextView)this.copyrightViewGroup.findViewById(2131690314);
            if (this.copyrightTextView != null && this.details != null) {
                this.copyrightTextView.setText((CharSequence)this.details.getCopyright());
                this.copyrightTextView.setVisibility(0);
                this.setLayoutAsCentered();
                this.addExpandedCopyright(this.copyrightTextView.getContext());
            }
        }
    }
    
    private void setExpandedText(final VideoDetails videoDetails, final Context context, final View view) {
        final TextView textView = (TextView)view.findViewById(2131690315);
        if (textView != null) {
            textView.setText((CharSequence)videoDetails.getCopyright());
        }
    }
    
    private void setLayoutAsCentered() {
        if (this.isCentered) {
            final RelativeLayout$LayoutParams layoutParams = (RelativeLayout$LayoutParams)this.copyrightTextView.getLayoutParams();
            layoutParams.addRule(13, -1);
            this.copyrightTextView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        }
    }
    
    private void setScreenLocation(final AlertDialog alertDialog) {
        final int[] array = new int[2];
        this.copyrightViewGroup.getLocationOnScreen(array);
        final WindowManager$LayoutParams attributes = alertDialog.getWindow().getAttributes();
        int n;
        if (this.isCentered) {
            n = 17;
        }
        else {
            n = 8388611;
        }
        attributes.gravity = (n | 0x30);
        attributes.x = array[0];
        attributes.y = array[1] - this.expandedYOffset;
    }
    
    private void showExpandedCopyrightPopup(final VideoDetails videoDetails, final Context context) {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(context);
        final View inflate = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2130903289, (ViewGroup)null);
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
    
    public void setGravityAsCenter() {
        this.isCentered = true;
    }
    
    public void update(final VideoDetails details) {
        this.details = details;
        this.init();
    }
}
