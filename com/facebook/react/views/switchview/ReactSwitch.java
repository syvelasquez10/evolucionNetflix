// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.switchview;

import android.content.Context;
import android.support.v7.widget.SwitchCompat;

class ReactSwitch extends SwitchCompat
{
    private boolean mAllowChange;
    
    public ReactSwitch(final Context context) {
        super(context);
        this.mAllowChange = true;
    }
    
    @Override
    public void setChecked(final boolean checked) {
        if (this.mAllowChange) {
            this.mAllowChange = false;
            super.setChecked(checked);
        }
    }
    
    void setOn(final boolean checked) {
        if (this.isChecked() != checked) {
            super.setChecked(checked);
        }
        this.mAllowChange = true;
    }
}
