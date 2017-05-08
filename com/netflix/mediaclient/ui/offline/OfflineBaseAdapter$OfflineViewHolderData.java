// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import android.graphics.drawable.Drawable;
import android.content.res.ColorStateList;
import android.support.v4.content.ContextCompat;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.view.View;
import android.view.View$OnLongClickListener;
import android.widget.TextView;
import android.view.View$OnClickListener;
import android.support.v7.widget.AppCompatCheckBox;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.support.v7.widget.RecyclerView$ViewHolder;

public class OfflineBaseAdapter$OfflineViewHolderData extends RecyclerView$ViewHolder
{
    public AdvancedImageView boxShot;
    public AppCompatCheckBox checkmark;
    private View$OnClickListener checkmarkClickListener;
    private View$OnClickListener clickListener;
    public DownloadButton downloadButton;
    public TextView downloadStatus;
    public TextView info;
    private View$OnLongClickListener longClickListener;
    public View playIcon;
    public TextView profileName;
    public ProgressBar progress;
    public ImageView showIndicator;
    final /* synthetic */ OfflineBaseAdapter this$0;
    public TextView title;
    
    public OfflineBaseAdapter$OfflineViewHolderData(final OfflineBaseAdapter this$0, final View view) {
        this.this$0 = this$0;
        super(view);
        this.clickListener = (View$OnClickListener)new OfflineBaseAdapter$OfflineViewHolderData$1(this);
        this.checkmarkClickListener = (View$OnClickListener)new OfflineBaseAdapter$OfflineViewHolderData$2(this);
        this.longClickListener = (View$OnLongClickListener)new OfflineBaseAdapter$OfflineViewHolderData$3(this);
        this.boxShot = (AdvancedImageView)view.findViewById(2131690059);
        this.title = (TextView)view.findViewById(2131689574);
        this.info = (TextView)view.findViewById(2131690063);
        this.profileName = (TextView)view.findViewById(2131690065);
        this.showIndicator = (ImageView)view.findViewById(2131690066);
        this.downloadStatus = (TextView)view.findViewById(2131690069);
        this.playIcon = view.findViewById(2131690060);
        this.downloadButton = (DownloadButton)view.findViewById(2131690067);
        this.progress = (ProgressBar)view.findViewById(2131690061);
        this.checkmark = (AppCompatCheckBox)view.findViewById(2131690068);
        if (BrowseExperience.showKidsExperience()) {
            this.checkmark.setSupportButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(), 2131624056)));
        }
        view.setOnClickListener(this.clickListener);
        view.setOnLongClickListener(this.longClickListener);
        this.checkmark.setOnClickListener(this.checkmarkClickListener);
        this.boxShot.setForeground(null);
    }
}
