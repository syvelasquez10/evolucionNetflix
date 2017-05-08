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
        this.background = root.findViewById(2131821018);
        this.lightDark = root.findViewById(2131821501);
        this.text = (TextView)root.findViewById(2131821502);
        this.textSecondary = (TextView)root.findViewById(2131821503);
    }
}
