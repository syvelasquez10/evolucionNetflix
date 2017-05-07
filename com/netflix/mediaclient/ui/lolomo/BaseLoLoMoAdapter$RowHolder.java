// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import android.widget.TextView;
import android.content.res.ColorStateList;
import android.view.View;

class BaseLoLoMoAdapter$RowHolder
{
    public final View contentGroup;
    public final ColorStateList defaultTitleColors;
    public final BaseLoLoMoAdapter$LoMoRowContent rowContent;
    public final View shelf;
    public final TextView title;
    
    BaseLoLoMoAdapter$RowHolder(final View contentGroup, final TextView title, final BaseLoLoMoAdapter$LoMoRowContent rowContent, final View shelf) {
        this.contentGroup = contentGroup;
        this.title = title;
        this.rowContent = rowContent;
        this.shelf = shelf;
        this.defaultTitleColors = title.getTextColors();
    }
    
    public void invalidateRequestId() {
        if (this.rowContent == null) {
            return;
        }
        this.rowContent.invalidateRequestId();
    }
}
