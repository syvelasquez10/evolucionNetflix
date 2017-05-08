// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.experience;

import android.widget.TextView;
import android.view.View;
import android.support.v7.widget.RecyclerView$ViewHolder;

public class ThemeActivity$ThemeFragment$ThemeViewHolder extends RecyclerView$ViewHolder
{
    View background;
    View lightDark;
    View root;
    TextView text;
    TextView textSecondary;
    
    ThemeActivity$ThemeFragment$ThemeViewHolder(final View root) {
        super(root);
        this.root = root;
        this.background = root.findViewById(2131821028);
        this.lightDark = root.findViewById(2131821517);
        this.text = (TextView)root.findViewById(2131821518);
        this.textSecondary = (TextView)root.findViewById(2131821519);
    }
}
