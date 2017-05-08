// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.experience;

import android.view.ViewGroup;
import android.view.View;
import android.view.View$OnClickListener;
import android.support.v7.widget.RecyclerView$ViewHolder;
import com.netflix.mediaclient.ui.kids.KidsUtils$Theme;
import android.support.v7.widget.RecyclerView$Adapter;

class ThemeActivity$ThemeFragment$1 extends RecyclerView$Adapter<ThemeActivity$ThemeFragment$ThemeViewHolder>
{
    final /* synthetic */ ThemeActivity$ThemeFragment this$0;
    
    ThemeActivity$ThemeFragment$1(final ThemeActivity$ThemeFragment this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public int getItemCount() {
        return KidsUtils$Theme.values().length;
    }
    
    @Override
    public void onBindViewHolder(final ThemeActivity$ThemeFragment$ThemeViewHolder themeActivity$ThemeFragment$ThemeViewHolder, int backgroundColor) {
        final KidsUtils$Theme kidsUtils$Theme = KidsUtils$Theme.values()[backgroundColor];
        themeActivity$ThemeFragment$ThemeViewHolder.background.setBackgroundColor(kidsUtils$Theme.getBackgroundColor());
        final View lightDark = themeActivity$ThemeFragment$ThemeViewHolder.lightDark;
        if (kidsUtils$Theme.isLight()) {
            backgroundColor = -1;
        }
        else {
            backgroundColor = -16777216;
        }
        lightDark.setBackgroundColor(backgroundColor);
        themeActivity$ThemeFragment$ThemeViewHolder.text.setTextColor(kidsUtils$Theme.getTextColor());
        themeActivity$ThemeFragment$ThemeViewHolder.textSecondary.setTextColor(kidsUtils$Theme.getSecondaryTextColor());
        themeActivity$ThemeFragment$ThemeViewHolder.root.setOnClickListener((View$OnClickListener)new ThemeActivity$ThemeFragment$1$1(this, kidsUtils$Theme));
        themeActivity$ThemeFragment$ThemeViewHolder.text.setText((CharSequence)kidsUtils$Theme.toString());
    }
    
    @Override
    public ThemeActivity$ThemeFragment$ThemeViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int n) {
        return new ThemeActivity$ThemeFragment$ThemeViewHolder(this.this$0.inflater.inflate(2130903314, viewGroup, false));
    }
}
