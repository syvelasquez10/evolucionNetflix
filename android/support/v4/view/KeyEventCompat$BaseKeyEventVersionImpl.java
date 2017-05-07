// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.KeyEvent;

class KeyEventCompat$BaseKeyEventVersionImpl implements KeyEventCompat$KeyEventVersionImpl
{
    private static int metaStateFilterDirectionalModifiers(final int n, int n2, final int n3, int n4, final int n5) {
        final int n6 = 1;
        int n7;
        if ((n2 & n3) != 0x0) {
            n7 = 1;
        }
        else {
            n7 = 0;
        }
        n4 |= n5;
        if ((n2 & n4) != 0x0) {
            n2 = n6;
        }
        else {
            n2 = 0;
        }
        if (n7 != 0) {
            if (n2 != 0) {
                throw new IllegalArgumentException("bad arguments");
            }
            n4 = (n & ~n4);
        }
        else {
            n4 = n;
            if (n2 != 0) {
                return n & ~n3;
            }
        }
        return n4;
    }
    
    @Override
    public boolean metaStateHasModifiers(final int n, final int n2) {
        return metaStateFilterDirectionalModifiers(metaStateFilterDirectionalModifiers(this.normalizeMetaState(n) & 0xF7, n2, 1, 64, 128), n2, 2, 16, 32) == n2;
    }
    
    @Override
    public boolean metaStateHasNoModifiers(final int n) {
        return (this.normalizeMetaState(n) & 0xF7) == 0x0;
    }
    
    public int normalizeMetaState(int n) {
        if ((n & 0xC0) != 0x0) {
            n |= 0x1;
        }
        int n2 = n;
        if ((n & 0x30) != 0x0) {
            n2 = (n | 0x2);
        }
        return n2 & 0xF7;
    }
    
    @Override
    public void startTracking(final KeyEvent keyEvent) {
    }
}
