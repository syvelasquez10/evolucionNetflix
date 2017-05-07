// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

final class az$3 extends dj
{
    final /* synthetic */ az a;
    final /* synthetic */ az b;
    
    az$3(final az b, final az a) {
        this.b = b;
        this.a = a;
    }
    
    @Override
    public final void a() {
        if (this.a.f.b()) {
            return;
        }
        final cv cv = new cv(this.a);
        cv.a.put("metadata", this.a.x.a());
        new dk(cv, new dd(new dc(this.b.u.b(), "/android_v2/update_user_metadata").a()), new de(this.a.x)).run();
    }
}
