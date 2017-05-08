// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

class FloatingActionButtonGingerbread$ResetElevationAnimation extends FloatingActionButtonGingerbread$ShadowAnimatorImpl
{
    final /* synthetic */ FloatingActionButtonGingerbread this$0;
    
    FloatingActionButtonGingerbread$ResetElevationAnimation(final FloatingActionButtonGingerbread this$0) {
        this.this$0 = this$0;
        super(this$0, null);
    }
    
    @Override
    protected float getTargetShadowSize() {
        return this.this$0.mElevation;
    }
}
