// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.widget;

import android.database.CursorIndexOutOfBoundsException;
import java.util.Iterator;
import android.util.Log;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.View;
import java.util.Arrays;
import java.util.ArrayList;
import android.view.LayoutInflater;
import com.google.android.gms.common.data.DataBuffer;
import java.util.List;
import android.content.Context;
import android.widget.BaseAdapter;

public class DataBufferAdapter<T> extends BaseAdapter
{
    private final Context mContext;
    private final int sm;
    private int sn;
    private final int so;
    private final List<DataBuffer<T>> sp;
    private final LayoutInflater sq;
    private boolean sr;
    
    public DataBufferAdapter(final Context context, final int n) {
        this(context, n, 0, (List)new ArrayList());
    }
    
    public DataBufferAdapter(final Context context, final int n, final int n2) {
        this(context, n, n2, (List)new ArrayList());
    }
    
    public DataBufferAdapter(final Context mContext, final int n, final int so, final List<DataBuffer<T>> sp) {
        this.sr = true;
        this.mContext = mContext;
        this.sn = n;
        this.sm = n;
        this.so = so;
        this.sp = sp;
        this.sq = (LayoutInflater)mContext.getSystemService("layout_inflater");
    }
    
    public DataBufferAdapter(final Context context, final int n, final int n2, final DataBuffer<T>... array) {
        this(context, n, n2, Arrays.asList(array));
    }
    
    public DataBufferAdapter(final Context context, final int n, final List<DataBuffer<T>> list) {
        this(context, n, 0, list);
    }
    
    public DataBufferAdapter(final Context context, final int n, final DataBuffer<T>... array) {
        this(context, n, 0, Arrays.asList(array));
    }
    
    private View a(final int n, View inflate, final ViewGroup viewGroup, final int n2) {
        TextView textView = null;
        CharSequence item = null;
        Label_0054: {
            if (inflate != null) {
                break Label_0054;
            }
            inflate = this.sq.inflate(n2, viewGroup, false);
            try {
                while (true) {
                    if (this.so == 0) {
                        textView = (TextView)inflate;
                    }
                    else {
                        textView = (TextView)inflate.findViewById(this.so);
                    }
                    item = this.getItem(n);
                    if (item instanceof CharSequence) {
                        textView.setText((CharSequence)item);
                        return inflate;
                    }
                    break Label_0054;
                    continue;
                }
            }
            catch (ClassCastException ex) {
                Log.e("DataBufferAdapter", "You must supply a resource ID for a TextView");
                throw new IllegalStateException("DataBufferAdapter requires the resource ID to be a TextView", ex);
            }
        }
        textView.setText((CharSequence)item.toString());
        return inflate;
    }
    
    public void append(final DataBuffer<T> dataBuffer) {
        this.sp.add(dataBuffer);
        if (this.sr) {
            this.notifyDataSetChanged();
        }
    }
    
    public void clear() {
        final Iterator<DataBuffer<T>> iterator = this.sp.iterator();
        while (iterator.hasNext()) {
            iterator.next().close();
        }
        this.sp.clear();
        if (this.sr) {
            this.notifyDataSetChanged();
        }
    }
    
    public Context getContext() {
        return this.mContext;
    }
    
    public int getCount() {
        final Iterator<DataBuffer<T>> iterator = this.sp.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            n += iterator.next().getCount();
        }
        return n;
    }
    
    public View getDropDownView(final int n, final View view, final ViewGroup viewGroup) {
        return this.a(n, view, viewGroup, this.sn);
    }
    
    public T getItem(final int n) throws CursorIndexOutOfBoundsException {
        final Iterator<DataBuffer<T>> iterator = this.sp.iterator();
        int n2 = n;
        while (iterator.hasNext()) {
            final DataBuffer<T> dataBuffer = iterator.next();
            final int count = dataBuffer.getCount();
            if (count > n2) {
                try {
                    return dataBuffer.get(n2);
                }
                catch (CursorIndexOutOfBoundsException ex) {
                    throw new CursorIndexOutOfBoundsException(n, this.getCount());
                }
                break;
            }
            n2 -= count;
        }
        throw new CursorIndexOutOfBoundsException(n, this.getCount());
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        return this.a(n, view, viewGroup, this.sm);
    }
    
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        this.sr = true;
    }
    
    public void setDropDownViewResource(final int sn) {
        this.sn = sn;
    }
    
    public void setNotifyOnChange(final boolean sr) {
        this.sr = sr;
    }
}
