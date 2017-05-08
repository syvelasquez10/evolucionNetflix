// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.internal;

import android.support.design.R$layout;
import android.view.View$OnClickListener;
import android.view.ViewGroup;
import android.view.LayoutInflater;

class NavigationMenuPresenter$NormalViewHolder extends NavigationMenuPresenter$ViewHolder
{
    public NavigationMenuPresenter$NormalViewHolder(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final View$OnClickListener onClickListener) {
        super(layoutInflater.inflate(R$layout.design_navigation_item, viewGroup, false));
        this.itemView.setOnClickListener(onClickListener);
    }
}
