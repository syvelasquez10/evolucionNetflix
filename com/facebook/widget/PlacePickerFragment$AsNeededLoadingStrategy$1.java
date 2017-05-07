// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

class PlacePickerFragment$AsNeededLoadingStrategy$1 implements GraphObjectAdapter$DataNeededListener
{
    final /* synthetic */ PlacePickerFragment$AsNeededLoadingStrategy this$1;
    
    PlacePickerFragment$AsNeededLoadingStrategy$1(final PlacePickerFragment$AsNeededLoadingStrategy this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onDataNeeded() {
        if (!this.this$1.loader.isLoading()) {
            this.this$1.loader.followNextLink();
        }
    }
}
