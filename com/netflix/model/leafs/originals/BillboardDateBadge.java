// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs.originals;

import com.google.gson.JsonElement;

public class BillboardDateBadge extends AbstractBillboardAsset
{
    public BillboardDateBadge(final JsonElement jsonElement) {
        super(jsonElement);
    }
    
    @Override
    public String getTag() {
        return "DateBadge";
    }
}
