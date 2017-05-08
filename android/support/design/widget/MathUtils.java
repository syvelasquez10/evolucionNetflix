// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

class MathUtils
{
    static int constrain(final int n, final int n2, final int n3) {
        if (n < n2) {
            return n2;
        }
        if (n > n3) {
            return n3;
        }
        return n;
    }
}
