// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.internal;

class NavigationMenuPresenter$NavigationMenuSeparatorItem implements NavigationMenuPresenter$NavigationMenuItem
{
    private final int mPaddingBottom;
    private final int mPaddingTop;
    
    public NavigationMenuPresenter$NavigationMenuSeparatorItem(final int mPaddingTop, final int mPaddingBottom) {
        this.mPaddingTop = mPaddingTop;
        this.mPaddingBottom = mPaddingBottom;
    }
    
    public int getPaddingBottom() {
        return this.mPaddingBottom;
    }
    
    public int getPaddingTop() {
        return this.mPaddingTop;
    }
}
