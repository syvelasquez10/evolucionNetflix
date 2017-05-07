// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.os.Parcelable;
import android.content.Context;
import android.view.ViewGroup;

public interface y
{
    boolean collapseItemActionView(final i p0, final m p1);
    
    boolean expandItemActionView(final i p0, final m p1);
    
    boolean flagActionItems();
    
    int getId();
    
    aa getMenuView(final ViewGroup p0);
    
    void initForMenu(final Context p0, final i p1);
    
    void onCloseMenu(final i p0, final boolean p1);
    
    void onRestoreInstanceState(final Parcelable p0);
    
    Parcelable onSaveInstanceState();
    
    boolean onSubMenuSelected(final ae p0);
    
    void setCallback(final z p0);
    
    void updateMenuView(final boolean p0);
}
