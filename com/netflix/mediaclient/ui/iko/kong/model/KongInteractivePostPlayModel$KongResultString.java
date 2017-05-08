// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.model;

public class KongInteractivePostPlayModel$KongResultString
{
    public String battleAgain;
    public String battleOptInHeader;
    public String resultHeader;
    final /* synthetic */ KongInteractivePostPlayModel this$0;
    
    public KongInteractivePostPlayModel$KongResultString(final KongInteractivePostPlayModel this$0) {
        this.this$0 = this$0;
    }
    
    public String getBattleAgain() {
        return this.battleAgain;
    }
    
    public String getBattleOptInHeader() {
        return this.battleOptInHeader;
    }
    
    public String getResultHeader() {
        return this.resultHeader;
    }
    
    @Override
    public String toString() {
        return "KongResultString{resultHeader='" + this.resultHeader + '\'' + ", battleOptInHeader='" + this.battleOptInHeader + '\'' + ", battleAgain='" + this.battleAgain + '\'' + '}';
    }
}
