// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs.originals;

import com.google.gson.JsonElement;
import com.fasterxml.jackson.core.JsonParser;

public class BillboardDateBadge extends AbstractBillboardAsset
{
    public BillboardDateBadge(final JsonParser jsonParser) {
        super(jsonParser);
    }
    
    public BillboardDateBadge(final JsonElement jsonElement) {
        super(jsonElement);
    }
    
    @Override
    public String getTag() {
        return "DateBadge";
    }
}
