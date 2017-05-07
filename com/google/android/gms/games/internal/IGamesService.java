// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.drive.Contents;
import android.os.Bundle;
import android.os.IBinder;
import android.net.Uri;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;
import com.google.android.gms.games.internal.request.GameRequestCluster;
import com.google.android.gms.games.internal.multiplayer.ZInvitationCluster;
import com.google.android.gms.games.achievement.AchievementEntity;
import android.content.Intent;
import android.os.IInterface;

public interface IGamesService extends IInterface
{
    void N(final boolean p0);
    
    void O(final boolean p0);
    
    void P(final boolean p0);
    
    int a(final IGamesCallbacks p0, final byte[] p1, final String p2, final String p3);
    
    Intent a(final int p0, final int p1, final boolean p2);
    
    Intent a(final int p0, final byte[] p1, final int p2, final String p3);
    
    Intent a(final AchievementEntity p0);
    
    Intent a(final ZInvitationCluster p0, final String p1, final String p2);
    
    Intent a(final GameRequestCluster p0, final String p1);
    
    Intent a(final RoomEntity p0, final int p1);
    
    Intent a(final String p0, final boolean p1, final boolean p2, final int p3);
    
    Intent a(final ParticipantEntity[] p0, final String p1, final String p2, final Uri p3, final Uri p4);
    
    void a(final long p0, final String p1);
    
    void a(final IBinder p0, final Bundle p1);
    
    void a(final Contents p0);
    
    void a(final IGamesCallbacks p0);
    
    void a(final IGamesCallbacks p0, final int p1);
    
    void a(final IGamesCallbacks p0, final int p1, final int p2, final int p3);
    
    void a(final IGamesCallbacks p0, final int p1, final int p2, final boolean p3, final boolean p4);
    
    void a(final IGamesCallbacks p0, final int p1, final int p2, final String[] p3, final Bundle p4);
    
    void a(final IGamesCallbacks p0, final int p1, final boolean p2, final boolean p3);
    
    void a(final IGamesCallbacks p0, final int p1, final int[] p2);
    
    void a(final IGamesCallbacks p0, final long p1);
    
    void a(final IGamesCallbacks p0, final long p1, final String p2);
    
    void a(final IGamesCallbacks p0, final Bundle p1, final int p2, final int p3);
    
    void a(final IGamesCallbacks p0, final IBinder p1, final int p2, final String[] p3, final Bundle p4, final boolean p5, final long p6);
    
    void a(final IGamesCallbacks p0, final IBinder p1, final String p2, final boolean p3, final long p4);
    
    void a(final IGamesCallbacks p0, final String p1);
    
    void a(final IGamesCallbacks p0, final String p1, final int p2);
    
    void a(final IGamesCallbacks p0, final String p1, final int p2, final int p3, final int p4, final boolean p5);
    
    void a(final IGamesCallbacks p0, final String p1, final int p2, final IBinder p3, final Bundle p4);
    
    void a(final IGamesCallbacks p0, final String p1, final int p2, final boolean p3);
    
    void a(final IGamesCallbacks p0, final String p1, final int p2, final boolean p3, final boolean p4);
    
    void a(final IGamesCallbacks p0, final String p1, final int p2, final boolean p3, final boolean p4, final boolean p5, final boolean p6);
    
    void a(final IGamesCallbacks p0, final String p1, final int p2, final int[] p3);
    
    void a(final IGamesCallbacks p0, final String p1, final long p2);
    
    void a(final IGamesCallbacks p0, final String p1, final long p2, final String p3);
    
    void a(final IGamesCallbacks p0, final String p1, final IBinder p2, final Bundle p3);
    
    void a(final IGamesCallbacks p0, final String p1, final SnapshotMetadataChange p2, final Contents p3);
    
    void a(final IGamesCallbacks p0, final String p1, final String p2);
    
    void a(final IGamesCallbacks p0, final String p1, final String p2, final int p3, final int p4);
    
    void a(final IGamesCallbacks p0, final String p1, final String p2, final int p3, final int p4, final int p5);
    
    void a(final IGamesCallbacks p0, final String p1, final String p2, final int p3, final int p4, final int p5, final boolean p6);
    
    void a(final IGamesCallbacks p0, final String p1, final String p2, final int p3, final boolean p4, final boolean p5);
    
    void a(final IGamesCallbacks p0, final String p1, final String p2, final SnapshotMetadataChange p3, final Contents p4);
    
    void a(final IGamesCallbacks p0, final String p1, final String p2, final boolean p3);
    
    void a(final IGamesCallbacks p0, final String p1, final String p2, final int[] p3, final int p4, final boolean p5);
    
    void a(final IGamesCallbacks p0, final String p1, final String p2, final String[] p3);
    
    void a(final IGamesCallbacks p0, final String p1, final String p2, final String[] p3, final boolean p4);
    
    void a(final IGamesCallbacks p0, final String p1, final boolean p2);
    
    void a(final IGamesCallbacks p0, final String p1, final byte[] p2, final String p3, final ParticipantResult[] p4);
    
    void a(final IGamesCallbacks p0, final String p1, final byte[] p2, final ParticipantResult[] p3);
    
    void a(final IGamesCallbacks p0, final String p1, final int[] p2);
    
    void a(final IGamesCallbacks p0, final String p1, final String[] p2, final int p3, final byte[] p4, final int p5);
    
    void a(final IGamesCallbacks p0, final boolean p1);
    
    void a(final IGamesCallbacks p0, final boolean p1, final Bundle p2);
    
    void a(final IGamesCallbacks p0, final boolean p1, final String[] p2);
    
    void a(final IGamesCallbacks p0, final int[] p1);
    
    void a(final IGamesCallbacks p0, final int[] p1, final int p2, final boolean p3);
    
    void a(final IGamesCallbacks p0, final String[] p1);
    
    void a(final IGamesCallbacks p0, final String[] p1, final boolean p2);
    
    void a(final String p0, final IBinder p1, final Bundle p2);
    
    int b(final byte[] p0, final String p1, final String[] p2);
    
    Intent b(final int p0, final int p1, final boolean p2);
    
    Intent b(final int[] p0);
    
    void b(final long p0, final String p1);
    
    void b(final IGamesCallbacks p0);
    
    void b(final IGamesCallbacks p0, final int p1, final boolean p2, final boolean p3);
    
    void b(final IGamesCallbacks p0, final long p1);
    
    void b(final IGamesCallbacks p0, final long p1, final String p2);
    
    void b(final IGamesCallbacks p0, final String p1);
    
    void b(final IGamesCallbacks p0, final String p1, final int p2);
    
    void b(final IGamesCallbacks p0, final String p1, final int p2, final int p3, final int p4, final boolean p5);
    
    void b(final IGamesCallbacks p0, final String p1, final int p2, final IBinder p3, final Bundle p4);
    
    void b(final IGamesCallbacks p0, final String p1, final int p2, final boolean p3);
    
    void b(final IGamesCallbacks p0, final String p1, final int p2, final boolean p3, final boolean p4);
    
    void b(final IGamesCallbacks p0, final String p1, final IBinder p2, final Bundle p3);
    
    void b(final IGamesCallbacks p0, final String p1, final String p2);
    
    void b(final IGamesCallbacks p0, final String p1, final String p2, final int p3, final int p4, final int p5, final boolean p6);
    
    void b(final IGamesCallbacks p0, final String p1, final String p2, final int p3, final boolean p4, final boolean p5);
    
    void b(final IGamesCallbacks p0, final String p1, final String p2, final boolean p3);
    
    void b(final IGamesCallbacks p0, final String p1, final boolean p2);
    
    void b(final IGamesCallbacks p0, final boolean p1);
    
    void b(final IGamesCallbacks p0, final String[] p1);
    
    void b(final String p0, final String p1, final int p2);
    
    String bB(final String p0);
    
    String bC(final String p0);
    
    void bD(final String p0);
    
    int bE(final String p0);
    
    Uri bF(final String p0);
    
    void bG(final String p0);
    
    ParcelFileDescriptor bH(final String p0);
    
    Intent bu(final String p0);
    
    Intent bz(final String p0);
    
    void c(final long p0, final String p1);
    
    void c(final IGamesCallbacks p0);
    
    void c(final IGamesCallbacks p0, final int p1, final boolean p2, final boolean p3);
    
    void c(final IGamesCallbacks p0, final long p1);
    
    void c(final IGamesCallbacks p0, final long p1, final String p2);
    
    void c(final IGamesCallbacks p0, final String p1);
    
    void c(final IGamesCallbacks p0, final String p1, final int p2);
    
    void c(final IGamesCallbacks p0, final String p1, final int p2, final boolean p3, final boolean p4);
    
    void c(final IGamesCallbacks p0, final String p1, final String p2);
    
    void c(final IGamesCallbacks p0, final String p1, final String p2, final boolean p3);
    
    void c(final IGamesCallbacks p0, final String p1, final boolean p2);
    
    void c(final IGamesCallbacks p0, final boolean p1);
    
    void c(final IGamesCallbacks p0, final String[] p1);
    
    void c(final String p0, final String p1, final int p2);
    
    void d(final long p0, final String p1);
    
    void d(final IGamesCallbacks p0);
    
    void d(final IGamesCallbacks p0, final int p1, final boolean p2, final boolean p3);
    
    void d(final IGamesCallbacks p0, final long p1);
    
    void d(final IGamesCallbacks p0, final long p1, final String p2);
    
    void d(final IGamesCallbacks p0, final String p1);
    
    void d(final IGamesCallbacks p0, final String p1, final int p2, final boolean p3, final boolean p4);
    
    void d(final IGamesCallbacks p0, final String p1, final String p2);
    
    void d(final IGamesCallbacks p0, final String p1, final boolean p2);
    
    void d(final IGamesCallbacks p0, final boolean p1);
    
    void dC(final int p0);
    
    void e(final IGamesCallbacks p0);
    
    void e(final IGamesCallbacks p0, final int p1, final boolean p2, final boolean p3);
    
    void e(final IGamesCallbacks p0, final String p1);
    
    void e(final IGamesCallbacks p0, final String p1, final int p2, final boolean p3, final boolean p4);
    
    void e(final IGamesCallbacks p0, final String p1, final String p2);
    
    void e(final IGamesCallbacks p0, final String p1, final boolean p2);
    
    void e(final IGamesCallbacks p0, final boolean p1);
    
    void f(final IGamesCallbacks p0);
    
    void f(final IGamesCallbacks p0, final String p1);
    
    void f(final IGamesCallbacks p0, final String p1, final int p2, final boolean p3, final boolean p4);
    
    void f(final IGamesCallbacks p0, final String p1, final String p2);
    
    void f(final IGamesCallbacks p0, final boolean p1);
    
    Bundle fD();
    
    void g(final IGamesCallbacks p0);
    
    void g(final IGamesCallbacks p0, final String p1);
    
    void g(final IGamesCallbacks p0, final boolean p1);
    
    ParcelFileDescriptor h(final Uri p0);
    
    RoomEntity h(final IGamesCallbacks p0, final String p1);
    
    void h(final IGamesCallbacks p0);
    
    void h(final IGamesCallbacks p0, final boolean p1);
    
    void i(final IGamesCallbacks p0);
    
    void i(final IGamesCallbacks p0, final String p1);
    
    void j(final IGamesCallbacks p0);
    
    void j(final IGamesCallbacks p0, final String p1);
    
    String jX();
    
    String jY();
    
    void k(final IGamesCallbacks p0, final String p1);
    
    Intent kA();
    
    void kB();
    
    boolean kC();
    
    Intent kb();
    
    Intent kc();
    
    Intent kd();
    
    Intent ke();
    
    Intent kj();
    
    Intent kk();
    
    int kl();
    
    String km();
    
    int kn();
    
    Intent ko();
    
    int kp();
    
    int kq();
    
    int kr();
    
    int ks();
    
    void ku();
    
    DataHolder kw();
    
    boolean kx();
    
    DataHolder ky();
    
    void kz();
    
    void l(final IGamesCallbacks p0, final String p1);
    
    void m(final IGamesCallbacks p0, final String p1);
    
    void n(final IGamesCallbacks p0, final String p1);
    
    void n(final String p0, final int p1);
    
    void o(final IGamesCallbacks p0, final String p1);
    
    void o(final String p0, final int p1);
    
    void p(final IGamesCallbacks p0, final String p1);
    
    void p(final String p0, final int p1);
    
    void q(final long p0);
    
    void q(final IGamesCallbacks p0, final String p1);
    
    void r(final long p0);
    
    void r(final IGamesCallbacks p0, final String p1);
    
    void r(final String p0, final int p1);
    
    void s(final long p0);
    
    void s(final IGamesCallbacks p0, final String p1);
    
    void s(final String p0, final int p1);
    
    void t(final long p0);
    
    void t(final IGamesCallbacks p0, final String p1);
    
    void u(final long p0);
    
    void u(final IGamesCallbacks p0, final String p1);
    
    void u(final String p0, final String p1);
    
    void v(final String p0, final String p1);
}
