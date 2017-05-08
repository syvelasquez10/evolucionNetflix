// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs.originals;

import com.google.gson.JsonElement;
import com.fasterxml.jackson.core.JsonParser;

public class BillboardBackgroundPortrait extends AbstractBillboardAsset
{
    public BillboardBackgroundPortrait(final JsonParser jsonParser) {
        super(jsonParser);
    }
    
    public BillboardBackgroundPortrait(final JsonElement jsonElement) {
        super(jsonElement);
    }
    
    @Override
    public String getTag() {
        return "Background";
    }
}
