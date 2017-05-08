// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

final class ax$4 extends di
{
    final /* synthetic */ ax a;
    final /* synthetic */ ax b;
    
    ax$4(final ax b, final ax a) {
        this.b = b;
        this.a = a;
    }
    
    @Override
    public final void a() {
        if (this.b.g.a()) {
            return;
        }
        final cu cu = new cu(this.a);
        cu.a.put("metadata", this.a.x.a());
        new dj(cu, new dc(new db(this.b.u.c.b, "/android_v2/update_user_metadata").a()), new dd(this.a.x)).run();
    }
}
