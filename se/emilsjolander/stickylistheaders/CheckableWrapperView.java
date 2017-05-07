// 
// Decompiled by Procyon v0.5.30
// 

package se.emilsjolander.stickylistheaders;

import android.content.Context;
import android.widget.Checkable;

class CheckableWrapperView extends WrapperView implements Checkable
{
    public CheckableWrapperView(final Context context) {
        super(context);
    }
    
    public boolean isChecked() {
        return ((Checkable)this.mItem).isChecked();
    }
    
    public void setChecked(final boolean checked) {
        ((Checkable)this.mItem).setChecked(checked);
    }
    
    public void toggle() {
        this.setChecked(!this.isChecked());
    }
}
