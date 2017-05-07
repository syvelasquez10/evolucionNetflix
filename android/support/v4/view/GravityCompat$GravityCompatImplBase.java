// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.Gravity;
import android.graphics.Rect;

class GravityCompat$GravityCompatImplBase implements GravityCompat$GravityCompatImpl
{
    @Override
    public void apply(final int n, final int n2, final int n3, final Rect rect, final Rect rect2, final int n4) {
        Gravity.apply(n, n2, n3, rect, rect2);
    }
    
    @Override
    public int getAbsoluteGravity(final int n, final int n2) {
        return 0xFF7FFFFF & n;
    }
}
