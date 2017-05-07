// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.app.Activity;
import com.netflix.mediaclient.ui.Section;

public abstract class PlaycardSection extends Section
{
    protected MdxPlaycardActivity context;
    protected boolean tablet;
    protected int transpColor;
    
    public PlaycardSection(final MdxPlaycardActivity context) {
        super(context);
        this.context = context;
        this.tablet = context.isTablet();
    }
    
    @Override
    public void destroy() {
    }
}
