// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.print;

class PrintHelper$PrintHelperKitkatImpl$2 implements PrintHelperKitkat$OnPrintFinishCallback
{
    final /* synthetic */ PrintHelper$PrintHelperKitkatImpl this$0;
    final /* synthetic */ PrintHelper$OnPrintFinishCallback val$callback;
    
    PrintHelper$PrintHelperKitkatImpl$2(final PrintHelper$PrintHelperKitkatImpl this$0, final PrintHelper$OnPrintFinishCallback val$callback) {
        this.this$0 = this$0;
        this.val$callback = val$callback;
    }
    
    @Override
    public void onFinish() {
        this.val$callback.onFinish();
    }
}
