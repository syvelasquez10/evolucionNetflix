// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.extractor.webm;

import com.google.android.exoplayer.extractor.ExtractorInput;

final class WebmExtractor$InnerEbmlReaderOutput implements EbmlReaderOutput
{
    final /* synthetic */ WebmExtractor this$0;
    
    private WebmExtractor$InnerEbmlReaderOutput(final WebmExtractor this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void binaryElement(final int n, final int n2, final ExtractorInput extractorInput) {
        this.this$0.binaryElement(n, n2, extractorInput);
    }
    
    @Override
    public void endMasterElement(final int n) {
        this.this$0.endMasterElement(n);
    }
    
    @Override
    public void floatElement(final int n, final double n2) {
        this.this$0.floatElement(n, n2);
    }
    
    @Override
    public int getElementType(final int n) {
        return this.this$0.getElementType(n);
    }
    
    @Override
    public void integerElement(final int n, final long n2) {
        this.this$0.integerElement(n, n2);
    }
    
    @Override
    public boolean isLevel1Element(final int n) {
        return this.this$0.isLevel1Element(n);
    }
    
    @Override
    public void startMasterElement(final int n, final long n2, final long n3) {
        this.this$0.startMasterElement(n, n2, n3);
    }
    
    @Override
    public void stringElement(final int n, final String s) {
        this.this$0.stringElement(n, s);
    }
}
