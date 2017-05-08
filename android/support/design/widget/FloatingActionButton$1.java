// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

class FloatingActionButton$1 implements FloatingActionButtonImpl$InternalVisibilityChangedListener
{
    final /* synthetic */ FloatingActionButton this$0;
    final /* synthetic */ FloatingActionButton$OnVisibilityChangedListener val$listener;
    
    FloatingActionButton$1(final FloatingActionButton this$0, final FloatingActionButton$OnVisibilityChangedListener val$listener) {
        this.this$0 = this$0;
        this.val$listener = val$listener;
    }
    
    @Override
    public void onHidden() {
        this.val$listener.onHidden(this.this$0);
    }
    
    @Override
    public void onShown() {
        this.val$listener.onShown(this.this$0);
    }
}
