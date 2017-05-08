// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

enum BillboardView$BillboardType
{
    NSRE_EPISODIC("nsreEpisodic"), 
    NSRE_SHOW("nsreShow");
    
    String name;
    
    private BillboardView$BillboardType(final String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
