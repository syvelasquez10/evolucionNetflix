// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.support.v7.app.AlertDialog$Builder;
import android.view.WindowManager$LayoutParams;
import android.support.v7.app.AlertDialog;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.view.View;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.view.LayoutInflater;
import android.view.View$OnLayoutChangeListener;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;

public class CopyrightView
{
    private final Context context;
    private TextView copyrightTextView;
    private ViewGroup copyrightViewGroup;
    private VideoDetails details;
    private int expandedYOffset;
    private boolean isCentered;
    
    public CopyrightView(final VideoDetails details, final Context context, final ViewGroup copyrightViewGroup) {
        this.copyrightViewGroup = copyrightViewGroup;
        this.details = details;
        this.context = context;
        this.init();
    }
    
    private void addExpandedCopyright() {
        this.copyrightTextView.addOnLayoutChangeListener((View$OnLayoutChangeListener)new CopyrightView$1(this));
    }
    
    public static CopyrightView create(final VideoDetails videoDetails, final Context context) {
        final ViewGroup viewGroup = (ViewGroup)((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2130903333, (ViewGroup)null);
        if (viewGroup != null) {
            return new CopyrightView(videoDetails, context, viewGroup);
        }
        return null;
    }
    
    private void init() {
        if (this.details == null || this.copyrightViewGroup == null) {
            return;
        }
        this.copyrightTextView = (TextView)this.copyrightViewGroup.findViewById(2131821548);
        if (this.copyrightTextView != null && this.details != null) {
            this.copyrightTextView.setText((CharSequence)this.details.getCopyright());
            this.copyrightTextView.setVisibility(0);
            this.setLayoutAsCentered();
            this.addExpandedCopyright();
        }
        KidsUtils.manageSectionTextColor(this.copyrightTextView, VideoDetailsViewGroup$Section.INFO);
    }
    
    private void setExpandedText(final VideoDetails videoDetails, final View view) {
        final TextView textView = (TextView)view.findViewById(2131821549);
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
    
    private void showExpandedCopyrightPopup(final VideoDetails videoDetails) {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(this.context);
        final View inflate = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130903334, (ViewGroup)null);
        if (inflate == null) {
            return;
        }
        this.setExpandedText(videoDetails, inflate);
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
