// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.view;

import android.net.Uri;
import com.facebook.drawee.interfaces.SimpleDraweeControllerBuilder;
import com.facebook.common.internal.Supplier;

public class SimpleDraweeView extends GenericDraweeView
{
    private static Supplier<? extends SimpleDraweeControllerBuilder> sDraweeControllerBuilderSupplier;
    private SimpleDraweeControllerBuilder mSimpleDraweeControllerBuilder;
    
    public static void initialize(final Supplier<? extends SimpleDraweeControllerBuilder> sDraweeControllerBuilderSupplier) {
        SimpleDraweeView.sDraweeControllerBuilderSupplier = sDraweeControllerBuilderSupplier;
    }
    
    protected SimpleDraweeControllerBuilder getControllerBuilder() {
        return this.mSimpleDraweeControllerBuilder;
    }
    
    @Override
    public void setImageURI(final Uri uri) {
        this.setImageURI(uri, null);
    }
    
    public void setImageURI(final Uri uri, final Object callerContext) {
        this.setController(this.mSimpleDraweeControllerBuilder.setCallerContext(callerContext).setUri(uri).setOldController(this.getController()).build());
    }
    
    public void setImageURI(final String s) {
        this.setImageURI(s, null);
    }
    
    public void setImageURI(final String s, final Object o) {
        Uri parse;
        if (s != null) {
            parse = Uri.parse(s);
        }
        else {
            parse = null;
        }
        this.setImageURI(parse, o);
    }
}
