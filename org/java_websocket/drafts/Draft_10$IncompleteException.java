// 
// Decompiled by Procyon v0.5.30
// 

package org.java_websocket.drafts;

class Draft_10$IncompleteException extends Throwable
{
    private int preferedsize;
    final /* synthetic */ Draft_10 this$0;
    
    public Draft_10$IncompleteException(final Draft_10 this$0, final int preferedsize) {
        this.this$0 = this$0;
        this.preferedsize = preferedsize;
    }
    
    public int getPreferedSize() {
        return this.preferedsize;
    }
}
