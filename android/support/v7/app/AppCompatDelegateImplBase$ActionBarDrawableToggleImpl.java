// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

class AppCompatDelegateImplBase$ActionBarDrawableToggleImpl implements ActionBarDrawerToggle$Delegate
{
    final /* synthetic */ AppCompatDelegateImplBase this$0;
    
    AppCompatDelegateImplBase$ActionBarDrawableToggleImpl(final AppCompatDelegateImplBase this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void setActionBarDescription(final int homeActionContentDescription) {
        final ActionBar supportActionBar = this.this$0.getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeActionContentDescription(homeActionContentDescription);
        }
    }
}
