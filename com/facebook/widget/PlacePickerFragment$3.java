// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import com.facebook.FacebookException;
import com.facebook.internal.Logger;
import com.facebook.LoggingBehavior;

class PlacePickerFragment$3 implements Runnable
{
    final /* synthetic */ PlacePickerFragment this$0;
    
    PlacePickerFragment$3(final PlacePickerFragment this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        try {
            this.this$0.loadData(true);
            if (false) {
                final PickerFragment$OnErrorListener onErrorListener = this.this$0.getOnErrorListener();
                if (onErrorListener == null) {
                    Logger.log(LoggingBehavior.REQUESTS, "PlacePickerFragment", "Error loading data : %s", null);
                    return;
                }
                onErrorListener.onError(this.this$0, null);
            }
        }
        catch (FacebookException ex) {
            if (ex == null) {
                return;
            }
            final PickerFragment$OnErrorListener onErrorListener2 = this.this$0.getOnErrorListener();
            if (onErrorListener2 != null) {
                onErrorListener2.onError(this.this$0, ex);
                return;
            }
            Logger.log(LoggingBehavior.REQUESTS, "PlacePickerFragment", "Error loading data : %s", ex);
        }
        catch (Exception ex3) {
            final FacebookException ex2 = new FacebookException(ex3);
            if (ex2 == null) {
                return;
            }
            final PickerFragment$OnErrorListener onErrorListener3 = this.this$0.getOnErrorListener();
            if (onErrorListener3 != null) {
                onErrorListener3.onError(this.this$0, ex2);
                return;
            }
            Logger.log(LoggingBehavior.REQUESTS, "PlacePickerFragment", "Error loading data : %s", ex2);
        }
        finally {
            while (true) {
                if (!false) {
                    break Label_0188;
                }
                final PickerFragment$OnErrorListener onErrorListener4 = this.this$0.getOnErrorListener();
                if (onErrorListener4 != null) {
                    onErrorListener4.onError(this.this$0, null);
                    break Label_0188;
                }
                Logger.log(LoggingBehavior.REQUESTS, "PlacePickerFragment", "Error loading data : %s", null);
                break Label_0188;
                continue;
            }
        }
    }
}
