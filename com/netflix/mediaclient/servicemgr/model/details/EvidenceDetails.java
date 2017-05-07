// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model.details;

import com.netflix.mediaclient.servicemgr.model.IconFontGlyph;

public interface EvidenceDetails extends VideoDetails
{
    IconFontGlyph getEvidenceGlyph();
    
    String getEvidenceText();
}
