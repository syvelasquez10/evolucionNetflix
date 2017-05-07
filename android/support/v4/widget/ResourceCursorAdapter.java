// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.view.View;
import android.view.ViewGroup;
import android.database.Cursor;
import android.content.Context;
import android.view.LayoutInflater;

public abstract class ResourceCursorAdapter extends CursorAdapter
{
    private int mDropDownLayout;
    private LayoutInflater mInflater;
    private int mLayout;
    
    public ResourceCursorAdapter(final Context context, final int n, final Cursor cursor, final boolean b) {
        super(context, cursor, b);
        this.mDropDownLayout = n;
        this.mLayout = n;
        this.mInflater = (LayoutInflater)context.getSystemService("layout_inflater");
    }
    
    @Override
    public View newDropDownView(final Context context, final Cursor cursor, final ViewGroup viewGroup) {
        return this.mInflater.inflate(this.mDropDownLayout, viewGroup, false);
    }
    
    @Override
    public View newView(final Context context, final Cursor cursor, final ViewGroup viewGroup) {
        return this.mInflater.inflate(this.mLayout, viewGroup, false);
    }
}
