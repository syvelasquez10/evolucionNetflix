// 
// Decompiled by Procyon v0.5.30
// 

package com.tonicartos.widget.stickygridheaders;

import android.widget.TextView;
import android.view.ViewGroup;
import android.view.View;
import java.util.Arrays;
import android.content.Context;
import java.util.List;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

public class StickyGridHeadersSimpleArrayAdapter<T> extends BaseAdapter implements StickyGridHeadersSimpleAdapter
{
    protected static final String TAG;
    private int mHeaderResId;
    private LayoutInflater mInflater;
    private int mItemResId;
    private List<T> mItems;
    
    static {
        TAG = StickyGridHeadersSimpleArrayAdapter.class.getSimpleName();
    }
    
    public StickyGridHeadersSimpleArrayAdapter(final Context context, final List<T> list, final int n, final int n2) {
        this.init(context, list, n, n2);
    }
    
    public StickyGridHeadersSimpleArrayAdapter(final Context context, final T[] array, final int n, final int n2) {
        this.init(context, Arrays.asList(array), n, n2);
    }
    
    private void init(final Context context, final List<T> mItems, final int mHeaderResId, final int mItemResId) {
        this.mItems = mItems;
        this.mHeaderResId = mHeaderResId;
        this.mItemResId = mItemResId;
        this.mInflater = LayoutInflater.from(context);
    }
    
    public boolean areAllItemsEnabled() {
        return false;
    }
    
    public int getCount() {
        return this.mItems.size();
    }
    
    public long getHeaderId(final int n) {
        final CharSequence item = this.getItem(n);
        CharSequence string;
        if (item instanceof CharSequence) {
            string = item;
        }
        else {
            string = item.toString();
        }
        return string.subSequence(0, 1).charAt(0);
    }
    
    public View getHeaderView(final int n, View inflate, final ViewGroup viewGroup) {
        StickyGridHeadersSimpleArrayAdapter$HeaderViewHolder tag;
        if (inflate == null) {
            inflate = this.mInflater.inflate(this.mHeaderResId, viewGroup, false);
            tag = new StickyGridHeadersSimpleArrayAdapter$HeaderViewHolder(this);
            tag.textView = (TextView)inflate.findViewById(16908308);
            inflate.setTag((Object)tag);
        }
        else {
            tag = (StickyGridHeadersSimpleArrayAdapter$HeaderViewHolder)inflate.getTag();
        }
        final CharSequence item = this.getItem(n);
        CharSequence string;
        if (item instanceof CharSequence) {
            string = item;
        }
        else {
            string = item.toString();
        }
        tag.textView.setText(string.subSequence(0, 1));
        return inflate;
    }
    
    public T getItem(final int n) {
        return this.mItems.get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        StickyGridHeadersSimpleArrayAdapter$ViewHolder tag;
        if (inflate == null) {
            inflate = this.mInflater.inflate(this.mItemResId, viewGroup, false);
            tag = new StickyGridHeadersSimpleArrayAdapter$ViewHolder(this);
            tag.textView = (TextView)inflate.findViewById(16908308);
            inflate.setTag((Object)tag);
        }
        else {
            tag = (StickyGridHeadersSimpleArrayAdapter$ViewHolder)inflate.getTag();
        }
        final CharSequence item = this.getItem(n);
        if (item instanceof CharSequence) {
            tag.textView.setText((CharSequence)item);
            return inflate;
        }
        tag.textView.setText((CharSequence)item.toString());
        return inflate;
    }
}
