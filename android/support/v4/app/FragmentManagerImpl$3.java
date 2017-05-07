// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

class FragmentManagerImpl$3 implements Runnable
{
    final /* synthetic */ FragmentManagerImpl this$0;
    final /* synthetic */ int val$flags;
    final /* synthetic */ String val$name;
    
    FragmentManagerImpl$3(final FragmentManagerImpl this$0, final String val$name, final int val$flags) {
        this.this$0 = this$0;
        this.val$name = val$name;
        this.val$flags = val$flags;
    }
    
    @Override
    public void run() {
        this.this$0.popBackStackState(this.this$0.mActivity.mHandler, this.val$name, -1, this.val$flags);
    }
}
