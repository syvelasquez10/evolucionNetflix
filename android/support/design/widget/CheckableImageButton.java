// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.support.v4.view.AccessibilityDelegateCompat;
import android.view.View;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R$attr;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.Checkable;
import android.support.v7.widget.AppCompatImageButton;

public class CheckableImageButton extends AppCompatImageButton implements Checkable
{
    private static final int[] DRAWABLE_STATE_CHECKED;
    private boolean mChecked;
    
    static {
        DRAWABLE_STATE_CHECKED = new int[] { 16842912 };
    }
    
    public CheckableImageButton(final Context context) {
        this(context, null);
    }
    
    public CheckableImageButton(final Context context, final AttributeSet set) {
        this(context, set, R$attr.imageButtonStyle);
    }
    
    public CheckableImageButton(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        ViewCompat.setAccessibilityDelegate((View)this, new CheckableImageButton$1(this));
    }
    
    public boolean isChecked() {
        return this.mChecked;
    }
    
    public int[] onCreateDrawableState(final int n) {
        if (this.mChecked) {
            return mergeDrawableStates(super.onCreateDrawableState(CheckableImageButton.DRAWABLE_STATE_CHECKED.length + n), CheckableImageButton.DRAWABLE_STATE_CHECKED);
        }
        return super.onCreateDrawableState(n);
    }
    
    public void setChecked(final boolean mChecked) {
        if (this.mChecked != mChecked) {
            this.mChecked = mChecked;
            this.refreshDrawableState();
            this.sendAccessibilityEvent(2048);
        }
    }
    
    public void toggle() {
        this.setChecked(!this.mChecked);
    }
}
