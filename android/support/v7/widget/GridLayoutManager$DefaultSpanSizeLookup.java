// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

public final class GridLayoutManager$DefaultSpanSizeLookup extends GridLayoutManager$SpanSizeLookup
{
    @Override
    public int getSpanIndex(final int n, final int n2) {
        return n % n2;
    }
    
    @Override
    public int getSpanSize(final int n) {
        return 1;
    }
}
