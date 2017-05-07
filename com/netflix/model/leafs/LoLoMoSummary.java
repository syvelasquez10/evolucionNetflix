// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import com.netflix.mediaclient.servicemgr.model.LoMoType;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.model.LoLoMo;

public class LoLoMoSummary implements LoLoMo
{
    private String displayName;
    private String id;
    private int length;
    private String lolomosId;
    
    @Override
    public String getId() {
        return this.id;
    }
    
    @Override
    public String getLolomosId() {
        return this.lolomosId;
    }
    
    @Override
    public int getNumLoMos() {
        return this.length;
    }
    
    @Override
    public String getTitle() {
        return StringUtils.decodeHtmlEntities(this.displayName);
    }
    
    @Override
    public LoMoType getType() {
        return LoMoType.STANDARD;
    }
    
    public void setId(final String id) {
        this.id = id;
    }
}
