// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import android.os.Bundle;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.common.data.DataHolder;
import android.os.IInterface;

public interface IGamesCallbacks extends IInterface
{
    void A(final DataHolder p0);
    
    void B(final DataHolder p0);
    
    void C(final DataHolder p0);
    
    void D(final DataHolder p0);
    
    void E(final DataHolder p0);
    
    void F(final DataHolder p0);
    
    void G(final DataHolder p0);
    
    void H(final DataHolder p0);
    
    void I(final DataHolder p0);
    
    void J(final DataHolder p0);
    
    void K(final DataHolder p0);
    
    void L(final DataHolder p0);
    
    void M(final DataHolder p0);
    
    void N(final DataHolder p0);
    
    void O(final DataHolder p0);
    
    void P(final DataHolder p0);
    
    void Q(final DataHolder p0);
    
    void a(final int p0, final String p1, final boolean p2);
    
    void a(final DataHolder p0, final DataHolder p1);
    
    void a(final DataHolder p0, final Contents p1);
    
    void a(final DataHolder p0, final String p1, final Contents p2, final Contents p3, final Contents p4);
    
    void a(final DataHolder p0, final String[] p1);
    
    void b(final int p0, final int p1, final String p2);
    
    void b(final int p0, final Bundle p1);
    
    void b(final DataHolder p0, final String[] p1);
    
    void c(final int p0, final Bundle p1);
    
    void c(final DataHolder p0);
    
    void c(final DataHolder p0, final String[] p1);
    
    void d(final int p0, final Bundle p1);
    
    void d(final DataHolder p0);
    
    void d(final DataHolder p0, final String[] p1);
    
    void dx(final int p0);
    
    void dy(final int p0);
    
    void dz(final int p0);
    
    void e(final int p0, final Bundle p1);
    
    void e(final DataHolder p0);
    
    void e(final DataHolder p0, final String[] p1);
    
    void f(final int p0, final Bundle p1);
    
    void f(final int p0, final String p1);
    
    void f(final DataHolder p0);
    
    void f(final DataHolder p0, final String[] p1);
    
    void fq();
    
    void g(final int p0, final String p1);
    
    void g(final DataHolder p0);
    
    void h(final int p0, final String p1);
    
    void h(final DataHolder p0);
    
    void i(final int p0, final String p1);
    
    void i(final DataHolder p0);
    
    void j(final DataHolder p0);
    
    void k(final DataHolder p0);
    
    void l(final DataHolder p0);
    
    void m(final DataHolder p0);
    
    void n(final DataHolder p0);
    
    void o(final DataHolder p0);
    
    void onInvitationRemoved(final String p0);
    
    void onLeftRoom(final int p0, final String p1);
    
    void onP2PConnected(final String p0);
    
    void onP2PDisconnected(final String p0);
    
    void onRealTimeMessageReceived(final RealTimeMessage p0);
    
    void onRequestRemoved(final String p0);
    
    void onTurnBasedMatchRemoved(final String p0);
    
    void p(final DataHolder p0);
    
    void q(final DataHolder p0);
    
    void r(final DataHolder p0);
    
    void s(final DataHolder p0);
    
    void t(final DataHolder p0);
    
    void u(final DataHolder p0);
    
    void v(final DataHolder p0);
    
    void w(final DataHolder p0);
    
    void x(final DataHolder p0);
    
    void y(final DataHolder p0);
    
    void z(final DataHolder p0);
}
