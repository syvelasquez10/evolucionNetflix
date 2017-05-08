// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.wordparty.moments;

import com.netflix.mediaclient.Log;
import android.util.AttributeSet;
import android.content.Context;

public class WPWordWallyCardImageView extends WPCardImageView
{
    public static final String TAG = "WPWordWallyCardImageView";
    
    public WPWordWallyCardImageView(final Context context) {
        super(context, null);
    }
    
    public WPWordWallyCardImageView(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    @Override
    protected void init(final Context context) {
        Log.d("WPWordWallyCardImageView", "Initing card");
    }
    
    @Override
    public String toString() {
        return "WPWordWallyCardImageView{} " + super.toString();
    }
}
