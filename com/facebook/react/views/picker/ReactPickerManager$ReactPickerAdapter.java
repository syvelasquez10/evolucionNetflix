// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.picker;

import android.widget.TextView;
import android.view.ViewGroup;
import android.view.View;
import com.facebook.infer.annotation.Assertions;
import android.content.Context;
import android.view.LayoutInflater;
import com.facebook.react.bridge.ReadableMap;
import android.widget.ArrayAdapter;

class ReactPickerManager$ReactPickerAdapter extends ArrayAdapter<ReadableMap>
{
    private final LayoutInflater mInflater;
    private Integer mPrimaryTextColor;
    
    public ReactPickerManager$ReactPickerAdapter(final Context context, final ReadableMap[] array) {
        super(context, 0, (Object[])array);
        this.mInflater = Assertions.assertNotNull(context.getSystemService("layout_inflater"));
    }
    
    private View getView(int n, View inflate, final ViewGroup viewGroup, final boolean b) {
        final ReadableMap readableMap = (ReadableMap)this.getItem(n);
        if (inflate == null) {
            if (b) {
                n = 17367049;
            }
            else {
                n = 17367048;
            }
            inflate = this.mInflater.inflate(n, viewGroup, false);
        }
        final TextView textView = (TextView)inflate;
        textView.setText((CharSequence)readableMap.getString("label"));
        if (!b && this.mPrimaryTextColor != null) {
            textView.setTextColor((int)this.mPrimaryTextColor);
        }
        else if (readableMap.hasKey("color") && !readableMap.isNull("color")) {
            textView.setTextColor(readableMap.getInt("color"));
            return inflate;
        }
        return inflate;
    }
    
    public View getDropDownView(final int n, final View view, final ViewGroup viewGroup) {
        return this.getView(n, view, viewGroup, true);
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        return this.getView(n, view, viewGroup, false);
    }
    
    public void setPrimaryTextColor(final Integer mPrimaryTextColor) {
        this.mPrimaryTextColor = mPrimaryTextColor;
        this.notifyDataSetChanged();
    }
}
