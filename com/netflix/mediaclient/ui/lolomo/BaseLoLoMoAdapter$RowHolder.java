// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import com.netflix.mediaclient.Log;
import android.widget.TextView;
import android.content.res.ColorStateList;
import android.view.View;

public class BaseLoLoMoAdapter$RowHolder
{
    public final View contentGroup;
    public final ColorStateList defaultTitleColors;
    public final BaseLoLoMoAdapter$LoMoRowContent rowContent;
    public final View shelf;
    public final TextView title;
    
    protected BaseLoLoMoAdapter$RowHolder(final View contentGroup, final TextView title, final BaseLoLoMoAdapter$LoMoRowContent rowContent, final View shelf) {
        this.contentGroup = contentGroup;
        this.title = title;
        this.rowContent = rowContent;
        this.shelf = shelf;
        this.defaultTitleColors = title.getTextColors();
    }
    
    public void onViewMovedToScrapHeap() {
        if (this.rowContent == null) {
            Log.d("BaseLoLoMoAdapter", "rowContent is null - can't notify of move to scrap heap");
            return;
        }
        this.rowContent.onViewMovedToScrapHeap();
    }
}
