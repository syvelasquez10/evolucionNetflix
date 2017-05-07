// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import android.os.Bundle;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;
import android.net.Uri;
import android.net.Uri$Builder;
import android.app.PendingIntent;
import android.os.Build;
import android.content.res.Configuration;
import android.content.pm.PackageInfo;
import com.google.android.gms.internal.fg;
import android.content.res.Resources;
import com.google.android.gms.internal.dz;
import android.content.Intent;
import com.google.android.gms.R;
import android.util.Log;
import android.content.DialogInterface$OnClickListener;
import com.google.android.gms.internal.du;
import android.content.Context;
import android.app.AlertDialog$Builder;
import android.app.Dialog;
import android.content.DialogInterface$OnCancelListener;
import android.app.Activity;
import java.io.UnsupportedEncodingException;

public final class GooglePlayServicesUtil
{
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 4242000;
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
    static final byte[][] mD;
    static final byte[][] mE;
    static final byte[][] mF;
    static final byte[][] mG;
    private static final byte[][] mH;
    private static final byte[][] mI;
    static final byte[][] mJ;
    public static boolean mK;
    public static boolean mL;
    static boolean mM;
    private static int mN;
    private static final Object mO;
    
    static {
        mD = new byte[][] { K("0\u0082\u0004C0\u0082\u0003+ \u0003\u0002\u0001\u0002\u0002\t\u0000\u00c2\u00e0\u0087FdJ0\u008d0\r\u0006\t*\u0086H\u0086\u00f7\r\u0001\u0001\u0004\u0005\u00000t1\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\u0013\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\u0013\rMountain View1\u00140\u0012\u0006\u0003U\u0004\n\u0013\u000bGoogle Inc.1\u00100\u000e\u0006\u0003U\u0004\u000b\u0013\u0007Android1\u00100\u000e\u0006\u0003U\u0004\u0003\u0013\u0007Android0\u001e\u0017\r080821231334Z\u0017\r360107231334Z0t1\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\u0013\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\u0013\rMountain View1\u00140\u0012\u0006\u0003U\u0004\n\u0013\u000bGoogle Inc.1\u00100\u000e\u0006\u0003U\u0004\u000b\u0013\u0007Android1\u00100\u000e\u0006\u0003U\u0004\u0003\u0013\u0007Android0\u0082\u0001 0\r\u0006\t*\u0086H\u0086\u00f7\r\u0001\u0001\u0001\u0005\u0000\u0003\u0082\u0001\r\u00000\u0082\u0001\b\u0002\u0082\u0001\u0001\u0000«V.\u0000\u00d8;¢\b®\n\u0096o\u0012N)\u00da\u0011\u00f2«V\u00d0\u008fX\u00e2\u00cc©\u0013\u0003\u00e9·T\u00d3r\u00f6@§\u001b\u001d\u00cb\u0013\tgbNFV§wj\u0092\u0019=²\u00e5¿·$©\u001ew\u0018\u008b\u000ejG¤;3\u00d9`\u009bw\u00181E\u00cc\u00df{.Xft\u00c9\u00e1V[\u001fLjYU¿\u00f2Q¦=«\u00f9\u00c5\\'\"\"R\u00e8u\u00e4\u00f8\u0015Jd_\u0089qh\u00c0±¿\u00c6\u0012\u00ea¿xWi»4ªy\u0084\u00dc~.¢vL®\u0083\u0007\u00d8\u00c1qT\u00d7\u00ee_d¥\u001aD¦\u0002\u00c2I\u0005AW\u00dc\u0002\u00cd_\\\u000eU\u00fb\u00ef\u0085\u0019\u00fb\u00e3'\u00f0±Q\u0016\u0092\u00c5 o\u0019\u00d1\u0083\u0085\u00f5\u00c4\u00db\u00c2\u00d6¹?h\u00cc)y\u00c7\u000e\u0018«\u0093\u0086k;\u00d5\u00db\u0089\u0099U*\u000e;L\u0099\u00dfX\u00fb\u0091\u008b\u00ed\u00c1\u0082º5\u00e0\u0003\u00c1´±\r\u00d2D¨\u00ee$\u00ff\u00fd38r«R!\u0098^\u00da°\u00fc\r\u000b\u0014[j¡\u0092\u0085\u008ey\u0002\u0001\u0003£\u0081\u00d90\u0081\u00d60\u001d\u0006\u0003U\u001d\u000e\u0004\u0016\u0004\u0014\u00c7}\u008c\u00c2!\u0017V%\u009a\u007f\u00d3\u0082\u00dfk\u00e3\u0098\u00e4\u00d7\u0086¥0\u0081¦\u0006\u0003U\u001d#\u0004\u0081\u009e0\u0081\u009b\u0080\u0014\u00c7}\u008c\u00c2!\u0017V%\u009a\u007f\u00d3\u0082\u00dfk\u00e3\u0098\u00e4\u00d7\u0086¥¡x¤v0t1\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\u0013\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\u0013\rMountain View1\u00140\u0012\u0006\u0003U\u0004\n\u0013\u000bGoogle Inc.1\u00100\u000e\u0006\u0003U\u0004\u000b\u0013\u0007Android1\u00100\u000e\u0006\u0003U\u0004\u0003\u0013\u0007Android\u0082\t\u0000\u00c2\u00e0\u0087FdJ0\u008d0\f\u0006\u0003U\u001d\u0013\u0004\u00050\u0003\u0001\u0001\u00ff0\r\u0006\t*\u0086H\u0086\u00f7\r\u0001\u0001\u0004\u0005\u0000\u0003\u0082\u0001\u0001\u0000m\u00d2R\u00ce\u00ef\u00850,6\nª\u00ce\u0093\u009b\u00cf\u00f2\u00cc©\u0004»]z\u0016a\u00f8®F²\u0099B\u0004\u00d0\u00ffJh\u00c7\u00ed\u001aS\u001e\u00c4YZb<\u00e6\u0007c±g)zz\u00e3W\u0012\u00c4\u0007\u00f2\b\u00f0\u00cb\u0010\u0094)\u0012M{\u0010b\u0019\u00c0\u0084\u00ca>³\u00f9\u00ad_¸q\u00ef\u0092&\u009a\u008b\u00e2\u008b\u00f1mD\u00c8\u00d9 \u008el²\u00f0\u0005»?\u00e2\u00cb\u0096D~\u0086\u008es\u0010v\u00adE³?`\t\u00ea\u0019\u00c1a\u00e6&Aª\u0099'\u001d\u00fdR(\u00c5\u00c5\u0087\u0087]\u00db\u007fE'X\u00d6a\u00f6\u00cc\f\u00cc·5.BL\u00c46\\R52\u00f72Q7Y<J\u00e3A\u00f4\u00dbA\u00ed\u00da\r\u000b\u0010q§\u00c4@\u00f0\u00fe\u009e \u001c¶'\u00cagCi\u00d0\u0084½/\u00d9\u0011\u00ff\u0006\u00cd¿,\u00fa\u0010\u00dc\u000f\u0089:\u00e3Wb\u0091\u0090H\u00c7\u00ef\u00c6LqD\u0017\u0083B\u00f7\u0005\u0081\u00c9\u00deW:\u00f5[9\r\u00d7\u00fd¹A\u00861\u0089]_u\u009f0\u0011&\u0087\u00ffb\u0014\u0010\u00c0i0\u008a"), K("0\u0082\u0004¨0\u0082\u0003\u0090 \u0003\u0002\u0001\u0002\u0002\t\u0000\u00d5\u0085¸l}\u00d3N\u00f50\r\u0006\t*\u0086H\u0086\u00f7\r\u0001\u0001\u0004\u0005\u00000\u0081\u00941\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\u0013\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\u0013\rMountain View1\u00100\u000e\u0006\u0003U\u0004\n\u0013\u0007Android1\u00100\u000e\u0006\u0003U\u0004\u000b\u0013\u0007Android1\u00100\u000e\u0006\u0003U\u0004\u0003\u0013\u0007Android1\"0 \u0006\t*\u0086H\u0086\u00f7\r\u0001\t\u0001\u0016\u0013android@android.com0\u001e\u0017\r080415233656Z\u0017\r350901233656Z0\u0081\u00941\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\u0013\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\u0013\rMountain View1\u00100\u000e\u0006\u0003U\u0004\n\u0013\u0007Android1\u00100\u000e\u0006\u0003U\u0004\u000b\u0013\u0007Android1\u00100\u000e\u0006\u0003U\u0004\u0003\u0013\u0007Android1\"0 \u0006\t*\u0086H\u0086\u00f7\r\u0001\t\u0001\u0016\u0013android@android.com0\u0082\u0001 0\r\u0006\t*\u0086H\u0086\u00f7\r\u0001\u0001\u0001\u0005\u0000\u0003\u0082\u0001\r\u00000\u0082\u0001\b\u0002\u0082\u0001\u0001\u0000\u00d6\u00ce.\b\n¿\u00e21M\u00d1\u008d³\u00cf\u00d3\u0018\\´=3\u00fa\ft\u00e1½¶\u00d1\u00db\u0089\u0013\u00f6,\\9\u00dfV\u00f8F\u0081=e¾\u00c0\u00f3\u00caBk\u0007\u00c5¨\u00edZ9\u0090\u00c1g\u00e7k\u00c9\u0099¹'\u0089K\u008f\u000b\"\u0000\u0019\u0094©)\u0015\u00e5r\u00c5m*0\u001b£o\u00c5\u00fc\u0011:\u00d6\u00cb\u009et5¡m#«}\u00fa\u00ee\u00e1e\u00e4\u00df\u001f\n\u008d½§\n\u0086\u009dQlN\u009d\u0005\u0011\u0096\u00ca|\fU\u007f\u0017[\u00c3u\u00f9H\u00c5j®\u0086\b\u009b¤O\u008a¦¤\u00dd\u009a}¿,\n5\"\u0082\u00ad\u0006¸\u00cc\u0018^±Uy\u00ee\u00f8m\b\u000b\u001da\u0089\u00c0\u00f9¯\u0098±\u00c2\u00eb\u00d1\u0007\u00eaE«\u00dbh£\u00c7\u0083\u008a^T\u0088\u00c7lS\u00d4\u000b\u0012\u001d\u00e7»\u00d3\u000eb\f\u0018\u008a\u00e1ªa\u00db¼\u0087\u00dd<d_/U\u00f3\u00d4\u00c3u\u00ec@p©?qQ\u00d86p\u00c1j\u0097\u001a¾^\u00f2\u00d1\u0018\u0090\u00e1¸®\u00f3)\u008c\u00f0f¿\u009el\u00e1D¬\u009a\u00e8m\u001c\u001b\u000f\u0002\u0001\u0003£\u0081\u00fc0\u0081\u00f90\u001d\u0006\u0003U\u001d\u000e\u0004\u0016\u0004\u0014\u008d\u001c\u00c5¾\u0095LC<a\u0086:\u0015°L¼\u0003\u00f2O\u00e0²0\u0081\u00c9\u0006\u0003U\u001d#\u0004\u0081\u00c10\u0081¾\u0080\u0014\u008d\u001c\u00c5¾\u0095LC<a\u0086:\u0015°L¼\u0003\u00f2O\u00e0²¡\u0081\u009a¤\u0081\u00970\u0081\u00941\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\u0013\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\u0013\rMountain View1\u00100\u000e\u0006\u0003U\u0004\n\u0013\u0007Android1\u00100\u000e\u0006\u0003U\u0004\u000b\u0013\u0007Android1\u00100\u000e\u0006\u0003U\u0004\u0003\u0013\u0007Android1\"0 \u0006\t*\u0086H\u0086\u00f7\r\u0001\t\u0001\u0016\u0013android@android.com\u0082\t\u0000\u00d5\u0085¸l}\u00d3N\u00f50\f\u0006\u0003U\u001d\u0013\u0004\u00050\u0003\u0001\u0001\u00ff0\r\u0006\t*\u0086H\u0086\u00f7\r\u0001\u0001\u0004\u0005\u0000\u0003\u0082\u0001\u0001\u0000\u0019\u00d3\f\u00f1\u0005\u00fbx\u0092?L\r}\u00d2##=@\u0096z\u00cf\u00ce\u0000\b\u001d[\u00d7\u00c6\u00e9\u00d6\u00ed k\u000e\u0011 \u0095\u0006Al¢D\u0093\u0099\u0013\u00d2kJ \u00e0\u00f5$\u00ca\u00d2»\\nL¡\u0001j\u0015\u0091n¡\u00ec]\u00c9Z^:\u0001\u00006\u00f4\u0092H\u00d5\u0010\u009b¿.\u001ea\u0081\u0086g:;\u00e5m¯\u000bw±\u00c2)\u00e3\u00c2U\u00e3\u00e8L\u0090]#\u0087\u00efº\t\u00cb\u00f1; +NZ\"\u00c92cHJ#\u00d2\u00fc)\u00fa\u009f\u00199u\u00973¯\u00d8ª\u0016\u000fB\u0096\u00c2\u00d0\u0016>\u0081\u0082\u0085\u009cfC\u00e9\u00c1\u0096/ \u00c1\u008333[\u00c0\u0090\u00ff\u009ak\"\u00de\u00d1\u00adDB)¥9©N\u00ef\u00ad«\u00d0e\u00ce\u00d2K>Q\u00e5\u00dd{fx{\u00ef\u0012\u00fe\u0097\u00fb¤\u0084\u00c4#\u00fbO\u00f8\u00ccIL\u0002\u00f0\u00f5\u0005\u0016\u0012\u00ffe)9>\u008eF\u00ea\u00c5»!\u00f2w\u00c1Qª_*¦'\u00d1\u00e8\u009d§\n¶\u00035i\u00de;\u0098\u0097¿\u00ff|©\u00da>\u0012C\u00f6\u000b") };
        mE = new byte[][] { K("0\u0082\u0002R0\u0082\u0001»\u0002\u0004I4\u0098~0\r\u0006\t*\u0086H\u0086\u00f7\r\u0001\u0001\u0004\u0005\u00000p1\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u000b0\t\u0006\u0003U\u0004\b\u0013\u0002CA1\u00160\u0014\u0006\u0003U\u0004\u0007\u0013\rMountain View1\u00140\u0012\u0006\u0003U\u0004\n\u0013\u000bGoogle, Inc1\u00140\u0012\u0006\u0003U\u0004\u000b\u0013\u000bGoogle, Inc1\u00100\u000e\u0006\u0003U\u0004\u0003\u0013\u0007Unknown0\u001e\u0017\r081202020758Z\u0017\r360419020758Z0p1\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u000b0\t\u0006\u0003U\u0004\b\u0013\u0002CA1\u00160\u0014\u0006\u0003U\u0004\u0007\u0013\rMountain View1\u00140\u0012\u0006\u0003U\u0004\n\u0013\u000bGoogle, Inc1\u00140\u0012\u0006\u0003U\u0004\u000b\u0013\u000bGoogle, Inc1\u00100\u000e\u0006\u0003U\u0004\u0003\u0013\u0007Unknown0\u0081\u009f0\r\u0006\t*\u0086H\u0086\u00f7\r\u0001\u0001\u0001\u0005\u0000\u0003\u0081\u008d\u00000\u0081\u0089\u0002\u0081\u0081\u0000\u009fH\u0003\u0019\u0090\u00f9±G&8N\u0004S\u00d1\u008f\u008c\u000b¿\u008d\u00c7{%\u0004¤± |LlDº¼\u0000\u00ad\u00c6a\u000f¦¶«-¨\u000e3\u00f2\u00ee\u00f1k&£\u00f6¸[\u009a\u00fa\u00ca\u0090\u009f\u00fb¾³\u00f4\u00c9O~\u0081\"§\u0098\u00e0\u00eb§\\\u00ed=\u00d2)\u00fase\u00f4\u0015\u0016AZ©\u00c1a}\u00d5\u0083\u00ce\u0019º\u00e8 »\u00d8\u0085\u00fc\u0017©´½&@\u0080Q!ª\u00db\u0093w\u00de´\u0000\u00138\u0014\u0018\u0088.\u00c5\"\u0082\u00fcX\r\u0002\u0003\u0001\u0000\u00010\r\u0006\t*\u0086H\u0086\u00f7\r\u0001\u0001\u0004\u0005\u0000\u0003\u0081\u0081\u0000@\u0086f\u009e\u00d61\u00daC\u0084\u00dd\u00d0a\u00d2&\u00e0s¹\u008c\u00c4¹\u009d\u00f8µ\u00e4¾\u009e<¾\u0097P\u001e\u0083\u00df\u001co©Y\u00c0\u00ce`\\O\u00d2¬m\u001c\u0084\u00ce\u00de Glº±\u009b\u00e8\u00f2 :\u00ffw\u0017\u00ade-\u008f\u00cc\u0089\u0007\b\u00d1!m¨DWY&I\u00e0\u00e9\u00d3\u00c4»L\u00f5\u008d¡\u009d±\u00d4\u00fcA¼¹XOd\u00e6_A\r\u0005)\u00fd[h\u0083\u008c\u0014\u001d\n\u009b\u00d1\u00db\u0011\u0091\u00cb*\r\u00f7\u0090\u00ea\f±-³¤"), K("0\u0082\u0004¨0\u0082\u0003\u0090 \u0003\u0002\u0001\u0002\u0002\t\u0000\u0084~O\u00f2\u00d6µ\u00de\u008e0\r\u0006\t*\u0086H\u0086\u00f7\r\u0001\u0001\u0005\u0005\u00000\u0081\u00941\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\u0013\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\u0013\rMountain View1\u00100\u000e\u0006\u0003U\u0004\n\u0013\u0007Android1\u00100\u000e\u0006\u0003U\u0004\u000b\u0013\u0007Android1\u00100\u000e\u0006\u0003U\u0004\u0003\u0013\u0007Android1\"0 \u0006\t*\u0086H\u0086\u00f7\r\u0001\t\u0001\u0016\u0013android@android.com0\u001e\u0017\r100120010135Z\u0017\r370607010135Z0\u0081\u00941\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\u0013\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\u0013\rMountain View1\u00100\u000e\u0006\u0003U\u0004\n\u0013\u0007Android1\u00100\u000e\u0006\u0003U\u0004\u000b\u0013\u0007Android1\u00100\u000e\u0006\u0003U\u0004\u0003\u0013\u0007Android1\"0 \u0006\t*\u0086H\u0086\u00f7\r\u0001\t\u0001\u0016\u0013android@android.com0\u0082\u0001 0\r\u0006\t*\u0086H\u0086\u00f7\r\u0001\u0001\u0001\u0005\u0000\u0003\u0082\u0001\r\u00000\u0082\u0001\b\u0002\u0082\u0001\u0001\u0000\u00d8(q|6\u00d1\u0017\u000f\u00d4M\n{\u000f\u0007\u0011&\u00e8[¿\u00df3°4`\u0000Z\u0094\u00cc\u00fbe¥\u00db ²C\u00df`±\u0091¿\u009d\u0006\u00df\u001d\u008a\\\n3\u00e2\u00d1c\u00f5\u0013\u00df\u001d\"SA\u00ea<3y\"\u00e8\\\u0002\u00ec4\u00ce\u00d9L¸\u0007#¦#\u00ffK¯\u00fb´\u00e5\u00ef\u00e6w;>¢¾¸¼²\u0002g\u00cf\u00e7\u0085Q\u001f\u0083.\u00f9\u0087«u\u0094\u00fe\u001e)\u00cf¼M\b:\u001f\u0012R\u0000ws\u0096\u00f2\u0016[i{\u0000£ \u00c1:\u00cc0\u008a\u0093\u00f2!c\u00c1n\u009c=J²\u0014\u009f6LE\u00c0C\u00142p9\u00f1\u00da\t`\u0093\u00f1³\u00fc\u0018¶V\u0010\u0095\u00c6\"_\u00c7\u0010+\u0098|o\u0013¤]$\u00e3\u00e0\u00c5N\u0085\u009dg\u00e3[g\b'\u0013\u00d2\u00d6\u00f0W\u00dd4W\u00d1\u009f\u00c4\u00fe\u008d\u00dd\u00ec\u008c:O?\u0097#\u0005\u0019§\n(64¬5\u0081£J½¡}\u0084Z\n\t\u0085\u00fb\u00f8\u0006\u000b\u0003j'x`\u0081c\u00fa\f7¹\u00e7\u00f2¡\u000ev¼w\u0002\u0001\u0003£\u0081\u00fc0\u0081\u00f90\u001d\u0006\u0003U\u001d\u000e\u0004\u0016\u0004\u0014µ\u00c7\u00f9\u0012ox\r:\u00fb\u00caess?\u00f5\"k\u009b\u001770\u0081\u00c9\u0006\u0003U\u001d#\u0004\u0081\u00c10\u0081¾\u0080\u0014µ\u00c7\u00f9\u0012ox\r:\u00fb\u00caess?\u00f5\"k\u009b\u00177¡\u0081\u009a¤\u0081\u00970\u0081\u00941\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\u0013\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\u0013\rMountain View1\u00100\u000e\u0006\u0003U\u0004\n\u0013\u0007Android1\u00100\u000e\u0006\u0003U\u0004\u000b\u0013\u0007Android1\u00100\u000e\u0006\u0003U\u0004\u0003\u0013\u0007Android1\"0 \u0006\t*\u0086H\u0086\u00f7\r\u0001\t\u0001\u0016\u0013android@android.com\u0082\t\u0000\u0084~O\u00f2\u00d6µ\u00de\u008e0\f\u0006\u0003U\u001d\u0013\u0004\u00050\u0003\u0001\u0001\u00ff0\r\u0006\t*\u0086H\u0086\u00f7\r\u0001\u0001\u0005\u0005\u0000\u0003\u0082\u0001\u0001\u0000L>§e}&\u00e6»\u00d7\u0011\f\u008f\u0019\u00df\u001f\u008d¡\t}3\u0086\u000fi\u00de¿\u00ca\u00dbF£~\u0087\u00e5³\u000f»4{\u001cuU¼»<\u0099T\u0014\u0080F\u0096_\u009cy*\u0002\u00d0\u00db\u00e5¦Ga³yG«k\u00ff°º\u00c6¢\u00c1 \u00cd\u00f8b\u00f8w©g\r\u00fdo\u0006.@n\u00ce\u0018\u0006\f`I\u008d\u00fc6\u009f'\u0011q\u0098\u00e5o\u00cb¡R\u00e6\u0005\u008d\u00ce\u0094\u00ceY\u001f\u00c4\u00f4©\u0098+3º\u00d8\u0019mwoU·\u00d0\u001a\u00cf1\u00dd\u00d7\f\u00ec·\u0089xv\u0006e\u0010\u00f9I¥RJ11³\u00cbeA\u00cf\u008b5B\u000e¼\u00c4R%Y\u0096?Bfi\u0005rfbO³\u0098\u00cf\u00dbR\u0017\u0088\u001d\u0011\u001cn\u0003F\u0016\u00f8Q!\u0018\u00d0¢¦\u009d\u0013\u00d7\u0092\u00f0\u00cd\u0011\u00db\u00d5\u008e#\u0083ZT¥J\u00c2Q\u00e7\u00d2,Dj?\u00ee\u0014\u0012\u0010\u00e9DGK@c\u0007»&\u0084+Ok\u00d3U\u0082\u001cs\u0096Q\u00ff¢`[\u0005\u00e2$\u0095\u00d7\u0015\u00d8z\u0091\u00f6") };
        mF = new byte[][] { K("0\u0082\u0002§0\u0082\u0002e \u0003\u0002\u0001\u0002\u0002\u0004P\u0005|B0\u000b\u0006\u0007*\u0086H\u00ce8\u0004\u0003\u0005\u0000071\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00100\u000e\u0006\u0003U\u0004\n\u0013\u0007Android1\u00160\u0014\u0006\u0003U\u0004\u0003\u0013\rAndroid Debug0\u001e\u0017\r120717145250Z\u0017\r220715145250Z071\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00100\u000e\u0006\u0003U\u0004\n\u0013\u0007Android1\u00160\u0014\u0006\u0003U\u0004\u0003\u0013\rAndroid Debug0\u0082\u0001·0\u0082\u0001,\u0006\u0007*\u0086H\u00ce8\u0004\u00010\u0082\u0001\u001f\u0002\u0081\u0081\u0000\u00fd\u007fS\u0081\u001du\u0012)R\u00dfJ\u009c.\u00ec\u00e4\u00e7\u00f6\u0011·R<\u00efD\u0000\u00c3\u001e?\u0080¶Q&iE]@\"Q\u00fbY=\u008dX\u00fa¿\u00c5\u00f5º0\u00f6\u00cb\u009bUl\u00d7\u0081;\u0080\u001d4o\u00f2f`·k\u0099P¥¤\u009f\u009f\u00e8\u0004{\u0010\"\u00c2O»©\u00d7\u00fe·\u00c6\u001b\u00f8;W\u00e7\u00c6¨¦\u0015\u000f\u0004\u00fb\u0083\u00f6\u00d3\u00c5\u001e\u00c3\u00025T\u0013Z\u0016\u00912\u00f6u\u00f3®+a\u00d7*\u00ef\u00f2\"\u0003\u0019\u009d\u00d1H\u0001\u00c7\u0002\u0015\u0000\u0097`P\u008f\u0015#\u000b\u00cc²\u0092¹\u0082¢\u00eb\u0084\u000b\u00f0X\u001c\u00f5\u0002\u0081\u0081\u0000\u00f7\u00e1 \u0085\u00d6\u009b=\u00de\u00cb¼«\\6¸W¹y\u0094¯»\u00fa:\u00ea\u0082\u00f9WL\u000b=\u0007\u0082gQYW\u008eº\u00d4YO\u00e6q\u0007\u0010\u0081\u0080´I\u0016q#\u00e8L(\u0016\u0013·\u00cf\t2\u008c\u00c8¦\u00e1<\u0016z\u008bT|\u008d(\u00e0£®\u001e+³¦u\u0091n£\u007f\u000b\u00fa!5b\u00f1\u00fbbz\u0001$;\u00cc¤\u00f1¾¨Q\u0090\u0089¨\u0083\u00df\u00e1Z\u00e5\u009f\u0006\u0092\u008bf^\u0080{U%d\u0001L;\u00fe\u00cfI*\u0003\u0081\u0084\u0000\u0002\u0081\u0080j\u00d1\u001b\u00d7\u00d5f\u00d2z\u00f49\u00c0.Ah¬\u00fdE´¾\u0085¼\u0099\u008c{\u009b\u008e\u001cwTi?\u008c\rB\u008a¤\u00fc\u00e1\u0010\u0084\u00818BO¦\u008c\u00d10RN\u00ef\u00f6\u00f178c\u0082/¦7)\u008b\u00feMF ¸fe\u00ee\u00f0A\u00179\u0001\u0003[\u001c\u0080j£\u0018\u0018\r0:¨\u00cc\u009eY#\u00e0jo«\u00fauh<E;²\u0007w|\u00f2\u00fd\u00e7\u00cf±\u009b\u001408\u0014ª\u001d\u00f7´=[\"+W\u0006´\u008b\u00940\u000b\u0006\u0007*\u0086H\u00ce8\u0004\u0003\u0005\u0000\u0003/\u00000,\u0002\u0014\t\u00d2\u00d1°G\u0002)µ¾\u00d2\u0090&a\u00d1\u0012\u00f2p\u00c5\u00e6\u001d\u0002\u0014gP\u0002\u0006§\u0080Pºx®\u00c7\u0017O\u0016\u0004\u007f\u0084\u00ea¢\u00f7") };
        mG = new byte[][] { K("0\u0082\u0004L0\u0082\u00034 \u0003\u0002\u0001\u0002\u0002\t\u0000¨\u00cd\u0017\u00c9=¥\u00d9\u00900\r\u0006\t*\u0086H\u0086\u00f7\r\u0001\u0001\u0005\u0005\u00000w1\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\u0013\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\u0013\rMountain View1\u00140\u0012\u0006\u0003U\u0004\n\u0013\u000bGoogle Inc.1\u00100\u000e\u0006\u0003U\u0004\u000b\u0013\u0007Android1\u00130\u0011\u0006\u0003U\u0004\u0003\u0013\nGoogle NFC0\u001e\u0017\r110324010653Z\u0017\r380809010653Z0w1\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\u0013\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\u0013\rMountain View1\u00140\u0012\u0006\u0003U\u0004\n\u0013\u000bGoogle Inc.1\u00100\u000e\u0006\u0003U\u0004\u000b\u0013\u0007Android1\u00130\u0011\u0006\u0003U\u0004\u0003\u0013\nGoogle NFC0\u0082\u0001 0\r\u0006\t*\u0086H\u0086\u00f7\r\u0001\u0001\u0001\u0005\u0000\u0003\u0082\u0001\r\u00000\u0082\u0001\b\u0002\u0082\u0001\u0001\u0000\u00c3\u000f\u0088\u00ad\u00d9´\u0092\tj,XjZ\u009a\u00805k\u00fa\u0002iX\u00f8\u00ff\f]\u00fa\u00f5\u009fI&\u008a\u00d8p\u00de\u00e8!¥>\u001f[\u0017\u000f\u00c9bE£\u00c9\u0082§\u00cbE'\u0005;\u00e3^4\u00f3\u0096\u00d2K\"\u0091\u00ec\fR\u008dn&\u0092te\u00e0hu\u00eab\u001f\u007f\u00f9\u008c@\u00e34[ I\u0007\u00cc\u0093Tt:\u00cdª\u00ceeV_Hºt\u00cdA!\u00cd\u00c8v\u00df5\"º\u00db\t\\ \u00d94\u00c5j>\\9>\u00e5\u00f0\u00e0/\u008f\u00e0b\u001f\u0091\u008d\u001f5¨$\u0089%,o¦¶3\u0092§hk>Ha-\u0006©\u00cfoI¿\u00f1\u001d]\u0096(\u009c\u009d\u00fe\u0014¬WbC\u0096\u0097\u00dd)\u00ea\u00fd¹\u0081\r\u00e3&5\u0013©\u0005¬\u008e\u008e¯ \u0090~Fu\nZ·¿\u009aw&/G°?Z<nm{Q4?i\u00c7\u00f7%\u00f7\u000b\u00cc\u001bJ\u00d5\u0092%\u000bpZ\u0086\u00e6\u00e8>\u00e2®7\u00feW\u0001¼½²o\u00ee\u00fd\u00ff\u00f6\u000fj[\u00dfµ¶G\u0093\u0002\u0001\u0003£\u0081\u00dc0\u0081\u00d90\u001d\u0006\u0003U\u001d\u000e\u0004\u0016\u0004\u0014\u001c\u00ce\u00ce\u000e\u00eaM\u00c1\u0012\u001f\u00c7Q_\r\n\fr\u00e0\u008c\u00c9m0\u0081©\u0006\u0003U\u001d#\u0004\u0081¡0\u0081\u009e\u0080\u0014\u001c\u00ce\u00ce\u000e\u00eaM\u00c1\u0012\u001f\u00c7Q_\r\n\fr\u00e0\u008c\u00c9m¡{¤y0w1\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\u0013\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\u0013\rMountain View1\u00140\u0012\u0006\u0003U\u0004\n\u0013\u000bGoogle Inc.1\u00100\u000e\u0006\u0003U\u0004\u000b\u0013\u0007Android1\u00130\u0011\u0006\u0003U\u0004\u0003\u0013\nGoogle NFC\u0082\t\u0000¨\u00cd\u0017\u00c9=¥\u00d9\u00900\f\u0006\u0003U\u001d\u0013\u0004\u00050\u0003\u0001\u0001\u00ff0\r\u0006\t*\u0086H\u0086\u00f7\r\u0001\u0001\u0005\u0005\u0000\u0003\u0082\u0001\u0001\u0000¤p\u00c7(\u00e1\u00d3\u001b\u0006\u00d9¯j\u00e7hµe\u0004lW\u0080k\u0098CrI1\u00d7]L¡\f2\u0015 \u00d3<\u00cf\u00ed*¦Tb#L\u009e\u00f9¶\u00f9\u0010\u00ccgk\u0099\u00cb\u007f\u0098\u0095\u00d6\u00c0gcWO»x3\u0012u\u00dc\\\u00f3\u008fº©\u0018\u00d7\u0093\u008c\u0005\u001f\u00fb¢\u00ad\u00e8\u00f3\u0003\u00cd\u00e8\u00d9\u00e6\u008a\u0004\u008d\u001f\u00db\u009e|\u009f*I²\"\u00c6\u008f\u00ffB+\u00f1Ui¸^\u00ee\u00ed°J£\bs\u00db\u00e6K\u009c\u009et\u00f8\u00f2\u00c2\u00f6\u00c4\u0001$ª¨\u00d1x\r\u0018Q+T\n\u00dd(³\u00e9X\u0019q¤\u0017\r\u00d8h\u00cf_1\u00e4G\u0012²\u00c2;µ\u00107\u00d7\u00ef\u009f\u0087¦\u00e5½³^,\u00ebk°\"cl\u0017¥j\u0096¼zP%\u008c\u000b\u00d2\u00ed{1UZ\u0018E.\u00172\u001a\rR\u0083\u008c\u0082\u00f6?t-t\u00ffyXj\\»\u007f¯q\u0098¨K\u00cftC\u0010\u00e9\u00e9'Y\u007f\u0000¢=\u00d0\u0006`\u0080\f\"8\u00d9\u000b/³r\u00df\u00dbºu½\u0085."), K("0\u0082\u0004L0\u0082\u00034 \u0003\u0002\u0001\u0002\u0002\t\u0000\u00dev\u0095\u0004\u001dvP\u00c00\r\u0006\t*\u0086H\u0086\u00f7\r\u0001\u0001\u0005\u0005\u00000w1\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\u0013\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\u0013\rMountain View1\u00140\u0012\u0006\u0003U\u0004\n\u0013\u000bGoogle Inc.1\u00100\u000e\u0006\u0003U\u0004\u000b\u0013\u0007Android1\u00130\u0011\u0006\u0003U\u0004\u0003\u0013\nGoogle NFC0\u001e\u0017\r110324010324Z\u0017\r380809010324Z0w1\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\u0013\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\u0013\rMountain View1\u00140\u0012\u0006\u0003U\u0004\n\u0013\u000bGoogle Inc.1\u00100\u000e\u0006\u0003U\u0004\u000b\u0013\u0007Android1\u00130\u0011\u0006\u0003U\u0004\u0003\u0013\nGoogle NFC0\u0082\u0001 0\r\u0006\t*\u0086H\u0086\u00f7\r\u0001\u0001\u0001\u0005\u0000\u0003\u0082\u0001\r\u00000\u0082\u0001\b\u0002\u0082\u0001\u0001\u0000\u00e6\u00ff=\u00ef\u00e9*¡\rq\u00eb\u000f¦@\u008b\u00c06·\u00e2C\u00ee\u00edh¦¤v=\u00c7¥*1u|\u00da\u00c6\u001f\u00e5\u0010»s\u00c7\u0016\u00e4\u0000\u0001\u0004&[4\u007f\u00ce\u00ce\u00f4\u00c4+\u00f1\u00e17\u009d\u00d0¨v\u00f0(\"\u007f»\u00c1\u00f9½\u00d5\u00d7\u0013²\u00f6©5£y\u00d2\u00cb©\u00c9o\u0092\u00d2\u00d0x|\u0011\u00f1\u00eb\u0019T\u0080\b¦ r³K\u0091\u0083l\u00fa\n\u00e1'g\u0080\u00e9\u0000u0\u0016i\u0086¡\u001c\u009c\u00efF\u00ce\u00f7\u00c7\u0004\u0080m\u00de\u00941\u00fb`(M\u0012\n°\u00e7\u00de\u001dc?\u0007h}F\u008cQ\u0013\u009a\u00ff\u00fd\u00c6¼\u009a |©\u0004¸¾\u001d ª{N\u0097uoC`d\u0088¾\\®<h\u00e8»yB\u00cd\u00f5\u0016\u0007\u00c90¢\u00fc\u00dae[u\u00d0u\u009cº\u0089\u00ad\u0006\u00e79½\u000b¢\u009b\u001f@B\u0096\u00c2\u00c0¨Z\u0084\u007fZ°\u00d0g\u00c6\u00c3\u00ec\u009cI! B¬c§\u00e5;Tle´`\u0080´\u00e3\u00e6\u0080\u00e2>\u001fw\u00cf\u00e7\u00f6\u00detK\u001ae\u0002\u0001\u0003£\u0081\u00dc0\u0081\u00d90\u001d\u0006\u0003U\u001d\u000e\u0004\u0016\u0004\u0014¢\u00e8\u0090d°]\b\u0086\\4\u00db\u0093\n\u009d\u0084\u0000P\u0011z\u00ec0\u0081©\u0006\u0003U\u001d#\u0004\u0081¡0\u0081\u009e\u0080\u0014¢\u00e8\u0090d°]\b\u0086\\4\u00db\u0093\n\u009d\u0084\u0000P\u0011z\u00ec¡{¤y0w1\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\u0013\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\u0013\rMountain View1\u00140\u0012\u0006\u0003U\u0004\n\u0013\u000bGoogle Inc.1\u00100\u000e\u0006\u0003U\u0004\u000b\u0013\u0007Android1\u00130\u0011\u0006\u0003U\u0004\u0003\u0013\nGoogle NFC\u0082\t\u0000\u00dev\u0095\u0004\u001dvP\u00c00\f\u0006\u0003U\u001d\u0013\u0004\u00050\u0003\u0001\u0001\u00ff0\r\u0006\t*\u0086H\u0086\u00f7\r\u0001\u0001\u0005\u0005\u0000\u0003\u0082\u0001\u0001\u00007q\u0087\f\u00e8|<R\u00ea\u0084\u0089\u00920\u00c6\u00e9b\u00d9KM_\u0012\u0093\u00c2]\u0088&\u0015A\u00fd\u0090µU]\u0012\u0085\u00ce\u00f3¸1,?]\u00f6\u0091¨ª\u00e0L¹\u0081³\u0005\u00e4'\u00fd\u001d-\u009e\u0019\u0087\u00e1\u00d2\u0090x\u00f1<\u0084R\u0099\u000f\u0018!\u0098\u0002c\u00d8\u00d4½6Q\u0093H\u00d8\u00d8º&\u00d8¹\u009f¿\t\u00f5\u00fd>»\u000e£\u00c2\u00f0\u00c97o\u001e\u001f\u00cav\u00f3¦¤\u0005B\u009d\b\u001bu*z\u0090·V\u00e9«D\u00daA«\u00c8\u00e1\u00e8\u00f8\u008a\u00c2u\u008d§C\u00fbs\u00e6Pq\u009aW\u0084\f\u00cbkz\u00dd!¹\u009f\u00c6\u0081\u00e4V\u00e1\u0087,\"=\\\u0007J\u00dfU\u00f6«\u00da&\u008c-\u008bd\u00ea\n\u0088E\u00ee\u00cd\u0096\u008f\u0092´\u0093\u0012~u\u00c7S\u00c3\u00ff0\u00cb\u00c6xµ\u001c\u009fR\u0096\u0014r\u00f1}¢\n\r\u00c6'J¢F44\u00c1©¶\u0014\u00dfi}\u008f\u00f5\u00ca\u0081\u0001\u00e7¢\\}³\u00fb\u0005]eV\u009c\u0004°\u001d8\u009c«ºW³¡p>\u00c2\u00e7J\u0088\u00d34") };
        mH = a(new byte[][][] { GooglePlayServicesUtil.mD, GooglePlayServicesUtil.mE, GooglePlayServicesUtil.mF, GooglePlayServicesUtil.mG });
        mI = new byte[][] { GooglePlayServicesUtil.mD[0], GooglePlayServicesUtil.mE[0], GooglePlayServicesUtil.mG[0] };
        mJ = new byte[][] { K("0\u0082\u0002_0\u0082\u0001\u00c8 \u0003\u0002\u0001\u0002\u0002\u0004K\u0019±\u009d0\r\u0006\t*\u0086H\u0086\u00f7\r\u0001\u0001\u0005\u0005\u00000t1\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\u0013\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\u0013\rMountain View1\u00150\u0013\u0006\u0003U\u0004\n\u0013\fGoogle, Inc.1\u00100\u000e\u0006\u0003U\u0004\u000b\u0013\u0007Unknown1\u000f0\r\u0006\u0003U\u0004\u0003\u0013\u0006Bazaar0\u001e\u0017\r091205010429Z\u0017\r370422010429Z0t1\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\u0013\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\u0013\rMountain View1\u00150\u0013\u0006\u0003U\u0004\n\u0013\fGoogle, Inc.1\u00100\u000e\u0006\u0003U\u0004\u000b\u0013\u0007Unknown1\u000f0\r\u0006\u0003U\u0004\u0003\u0013\u0006Bazaar0\u0081\u009f0\r\u0006\t*\u0086H\u0086\u00f7\r\u0001\u0001\u0001\u0005\u0000\u0003\u0081\u008d\u00000\u0081\u0089\u0002\u0081\u0081\u0000©\b\u0088\u00de\u0096\u00e354w\t\u00ddK%\u001ez)¨G7k.\\º[[\u00d5\u0004>\u0083\u0088\u0001\u0002\u0098\u0019\u0014\u0094\u0013\u00fa3ª\u00e5D;\u0003SJ\u00ceJ\u00adoP\u0097\u0012I\u00d9\u008ev£\u009a~L\u00cc\u00e1\u00d7\u001b§¾>ugMµ\u00f1\u0007Z\u0098sp\u0001FH§\u00cep<-\u00c7\u00884\u0089\u0005\u0092\u0012¯\u009cl[(«\u00d5O\u0083d\u0011\u00c81¢\u009fP\u000f(\u0002\u00d1l\u00e6\u00d1\u0085o\u0086pª\u00fc¢eA\u0083{9\u0002\u0003\u0001\u0000\u00010\r\u0006\t*\u0086H\u0086\u00f7\r\u0001\u0001\u0005\u0005\u0000\u0003\u0081\u0081\u0000I\u0084\u00c6\u00f3AG\u0001#b:'O\u00e9\u00e17=u1\u00cc\r\u00fc\u00e9§j\u00e6\u007f\u00fbp[@L½\u001b\u00c1\u0016\u008c«\u0018»\u0011\u00c3x\u0095¿´\u00f3l\u00c1L\u00ec\u001d,\u00c5Qj\u000e\u00ce\u00d4\u0007Nµh\u0082\u0089Pd\u0000¯\u00f8\u00dc\u00c8\u00efT\u0004\u0012\u0002\u00fd\u00ef\u00f1\u00fd\u0082\u00e0\u00f3#\u0010r\u00fd\u00cc\u00deJ6\u008b\u00e0\u00c6\u00c3\u00f9¸³ª\rh<:¿\u00da\u009a·»\u00882\u00e9¾^6\u0019º\u0092\u00dd:\u00cc\u0003j\u00adµ¦\u0019¯P") };
        GooglePlayServicesUtil.mK = false;
        GooglePlayServicesUtil.mL = false;
        GooglePlayServicesUtil.mM = false;
        GooglePlayServicesUtil.mN = -1;
        mO = new Object();
    }
    
    private static byte[] K(final String s) {
        try {
            return s.getBytes("ISO-8859-1");
        }
        catch (UnsupportedEncodingException ex) {
            throw new AssertionError((Object)ex);
        }
    }
    
    public static Dialog a(final int n, final Activity activity, final int n2, final DialogInterface$OnCancelListener onCancelListener, final int n3) {
        final AlertDialog$Builder setMessage = new AlertDialog$Builder((Context)activity).setMessage((CharSequence)b((Context)activity, n, n3));
        if (onCancelListener != null) {
            setMessage.setOnCancelListener(onCancelListener);
        }
        final du du = new du(activity, a((Context)activity, n, n3), n2);
        final String b = b((Context)activity, n);
        if (b != null) {
            setMessage.setPositiveButton((CharSequence)b, (DialogInterface$OnClickListener)du);
        }
        switch (n) {
            default: {
                Log.e("GooglePlayServicesUtil", "Unexpected error code " + n);
                return (Dialog)setMessage.create();
            }
            case 0: {
                return null;
            }
            case 4:
            case 6: {
                return (Dialog)setMessage.create();
            }
            case 1: {
                return (Dialog)setMessage.setTitle(R.string.common_google_play_services_install_title).create();
            }
            case 3: {
                return (Dialog)setMessage.setTitle(R.string.common_google_play_services_enable_title).create();
            }
            case 2: {
                return (Dialog)setMessage.setTitle(R.string.common_google_play_services_update_title).create();
            }
            case 9: {
                Log.e("GooglePlayServicesUtil", "Google Play services is invalid. Cannot recover.");
                return (Dialog)setMessage.setTitle(R.string.common_google_play_services_unsupported_title).create();
            }
            case 7: {
                Log.e("GooglePlayServicesUtil", "Network error occurred. Please retry request later.");
                return (Dialog)setMessage.setTitle(R.string.common_google_play_services_network_error_title).create();
            }
            case 8: {
                Log.e("GooglePlayServicesUtil", "Internal error occurred. Please see logs for detailed information");
                return (Dialog)setMessage.create();
            }
            case 10: {
                Log.e("GooglePlayServicesUtil", "Developer error occurred. Please see logs for detailed information");
                return (Dialog)setMessage.create();
            }
            case 5: {
                Log.e("GooglePlayServicesUtil", "An invalid account was specified when connecting. Please provide a valid account.");
                return (Dialog)setMessage.setTitle(R.string.common_google_play_services_invalid_account_title).create();
            }
            case 11: {
                Log.e("GooglePlayServicesUtil", "The application is not licensed to the user.");
                return (Dialog)setMessage.create();
            }
            case 12: {
                Log.e("GooglePlayServicesUtil", "The date of the device is not valid.");
                return (Dialog)setMessage.setTitle(R.string.common_google_play_services_unsupported_title).create();
            }
        }
    }
    
    public static Intent a(final Context context, final int n, final int n2) {
        switch (n) {
            default: {
                return null;
            }
            case 1:
            case 2: {
                if (!y(n2)) {
                    return dz.S("com.google.android.gms");
                }
                if (o(context)) {
                    return dz.T("com.google.android.gms");
                }
                return dz.S("com.google.android.apps.bazaar");
            }
            case 3: {
                return dz.Q("com.google.android.gms");
            }
            case 12: {
                return dz.bX();
            }
        }
    }
    
    public static boolean a(final Resources resources) {
        if (resources != null) {
            boolean b;
            if ((resources.getConfiguration().screenLayout & 0xF) > 3) {
                b = true;
            }
            else {
                b = false;
            }
            if ((fg.cD() && b) || b(resources)) {
                return true;
            }
        }
        return false;
    }
    
    private static byte[] a(final PackageInfo p0, final byte[]... p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc             "X509"
        //     2: invokestatic    java/security/cert/CertificateFactory.getInstance:(Ljava/lang/String;)Ljava/security/cert/CertificateFactory;
        //     5: astore_3       
        //     6: aload_0        
        //     7: getfield        android/content/pm/PackageInfo.signatures:[Landroid/content/pm/Signature;
        //    10: arraylength    
        //    11: iconst_1       
        //    12: if_icmpeq       38
        //    15: ldc             "GooglePlayServicesUtil"
        //    17: ldc_w           "Package has more than one signature."
        //    20: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //    23: pop            
        //    24: aconst_null    
        //    25: areturn        
        //    26: astore_0       
        //    27: ldc             "GooglePlayServicesUtil"
        //    29: ldc_w           "Could not get certificate instance."
        //    32: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //    35: pop            
        //    36: aconst_null    
        //    37: areturn        
        //    38: new             Ljava/io/ByteArrayInputStream;
        //    41: dup            
        //    42: aload_0        
        //    43: getfield        android/content/pm/PackageInfo.signatures:[Landroid/content/pm/Signature;
        //    46: iconst_0       
        //    47: aaload         
        //    48: invokevirtual   android/content/pm/Signature.toByteArray:()[B
        //    51: invokespecial   java/io/ByteArrayInputStream.<init>:([B)V
        //    54: astore          4
        //    56: aload_3        
        //    57: aload           4
        //    59: invokevirtual   java/security/cert/CertificateFactory.generateCertificate:(Ljava/io/InputStream;)Ljava/security/cert/Certificate;
        //    62: checkcast       Ljava/security/cert/X509Certificate;
        //    65: astore_3       
        //    66: aload_3        
        //    67: invokevirtual   java/security/cert/X509Certificate.checkValidity:()V
        //    70: aload_0        
        //    71: getfield        android/content/pm/PackageInfo.signatures:[Landroid/content/pm/Signature;
        //    74: iconst_0       
        //    75: aaload         
        //    76: invokevirtual   android/content/pm/Signature.toByteArray:()[B
        //    79: astore_0       
        //    80: iconst_0       
        //    81: istore_2       
        //    82: iload_2        
        //    83: aload_1        
        //    84: arraylength    
        //    85: if_icmpge       145
        //    88: aload_1        
        //    89: iload_2        
        //    90: aaload         
        //    91: astore_3       
        //    92: aload_3        
        //    93: aload_0        
        //    94: invokestatic    java/util/Arrays.equals:([B[B)Z
        //    97: ifeq            138
        //   100: aload_3        
        //   101: areturn        
        //   102: astore_0       
        //   103: ldc             "GooglePlayServicesUtil"
        //   105: ldc_w           "Could not generate certificate."
        //   108: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   111: pop            
        //   112: aconst_null    
        //   113: areturn        
        //   114: astore_0       
        //   115: ldc             "GooglePlayServicesUtil"
        //   117: ldc_w           "Certificate has expired."
        //   120: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   123: pop            
        //   124: aconst_null    
        //   125: areturn        
        //   126: astore_0       
        //   127: ldc             "GooglePlayServicesUtil"
        //   129: ldc_w           "Certificate is not yet valid."
        //   132: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   135: pop            
        //   136: aconst_null    
        //   137: areturn        
        //   138: iload_2        
        //   139: iconst_1       
        //   140: iadd           
        //   141: istore_2       
        //   142: goto            82
        //   145: ldc             "GooglePlayServicesUtil"
        //   147: iconst_2       
        //   148: invokestatic    android/util/Log.isLoggable:(Ljava/lang/String;I)Z
        //   151: ifeq            184
        //   154: ldc             "GooglePlayServicesUtil"
        //   156: new             Ljava/lang/StringBuilder;
        //   159: dup            
        //   160: invokespecial   java/lang/StringBuilder.<init>:()V
        //   163: ldc_w           "Signature not valid.  Found: \n"
        //   166: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   169: aload_0        
        //   170: iconst_0       
        //   171: invokestatic    android/util/Base64.encodeToString:([BI)Ljava/lang/String;
        //   174: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   177: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   180: invokestatic    android/util/Log.v:(Ljava/lang/String;Ljava/lang/String;)I
        //   183: pop            
        //   184: aconst_null    
        //   185: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                
        //  -----  -----  -----  -----  ----------------------------------------------------
        //  0      6      26     38     Ljava/security/cert/CertificateException;
        //  56     66     102    114    Ljava/security/cert/CertificateException;
        //  66     70     114    126    Ljava/security/cert/CertificateExpiredException;
        //  66     70     126    138    Ljava/security/cert/CertificateNotYetValidException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0082:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static byte[][] a(final byte[][]... array) {
        final int length = array.length;
        int i = 0;
        int n = 0;
        while (i < length) {
            n += array[i].length;
            ++i;
        }
        final byte[][] array2 = new byte[n][];
        final int length2 = array.length;
        int j = 0;
        int n2 = 0;
        while (j < length2) {
            final byte[][] array3 = array[j];
            for (int k = 0; k < array3.length; ++k, ++n2) {
                array2[n2] = array3[k];
            }
            ++j;
        }
        return array2;
    }
    
    public static String b(final Context context, final int n) {
        final Resources resources = context.getResources();
        switch (n) {
            default: {
                return resources.getString(17039370);
            }
            case 1: {
                return resources.getString(R.string.common_google_play_services_install_button);
            }
            case 3: {
                return resources.getString(R.string.common_google_play_services_enable_button);
            }
            case 2: {
                return resources.getString(R.string.common_google_play_services_update_button);
            }
        }
    }
    
    public static String b(final Context context, final int n, final int n2) {
        final Resources resources = context.getResources();
        String s = null;
        switch (n) {
            default: {
                s = resources.getString(R.string.common_google_play_services_unknown_issue);
                break;
            }
            case 1: {
                String s2;
                if (a(context.getResources())) {
                    s2 = resources.getString(R.string.common_google_play_services_install_text_tablet);
                }
                else {
                    s2 = resources.getString(R.string.common_google_play_services_install_text_phone);
                }
                s = s2;
                if (y(n2)) {
                    return s2 + " (via Bazaar)";
                }
                break;
            }
            case 3: {
                return resources.getString(R.string.common_google_play_services_enable_text);
            }
            case 2: {
                s = resources.getString(R.string.common_google_play_services_update_text);
                if (y(n2)) {
                    return s + " (via Bazaar)";
                }
                break;
            }
            case 9: {
                return resources.getString(R.string.common_google_play_services_unsupported_text);
            }
            case 7: {
                return resources.getString(R.string.common_google_play_services_network_error_text);
            }
            case 5: {
                return resources.getString(R.string.common_google_play_services_invalid_account_text);
            }
            case 12: {
                return resources.getString(R.string.common_google_play_services_unsupported_date_text);
            }
        }
        return s;
    }
    
    private static boolean b(final Resources resources) {
        final boolean b = false;
        final Configuration configuration = resources.getConfiguration();
        boolean b2 = b;
        if (fg.cF()) {
            b2 = b;
            if ((configuration.screenLayout & 0xF) <= 3) {
                b2 = b;
                if (configuration.smallestScreenWidthDp >= 600) {
                    b2 = true;
                }
            }
        }
        return b2;
    }
    
    public static boolean bi() {
        if (GooglePlayServicesUtil.mK) {
            return GooglePlayServicesUtil.mL;
        }
        return "user".equals(Build.TYPE);
    }
    
    public static Dialog getErrorDialog(final int n, final Activity activity, final int n2) {
        return a(n, activity, n2, null, -1);
    }
    
    public static Dialog getErrorDialog(final int n, final Activity activity, final int n2, final DialogInterface$OnCancelListener dialogInterface$OnCancelListener) {
        return a(n, activity, n2, dialogInterface$OnCancelListener, -1);
    }
    
    public static PendingIntent getErrorPendingIntent(final int n, final Context context, final int n2) {
        final Intent a = a(context, n, -1);
        if (a == null) {
            return null;
        }
        return PendingIntent.getActivity(context, n2, a, 268435456);
    }
    
    public static String getErrorString(final int n) {
        switch (n) {
            default: {
                return "UNKNOWN_ERROR_CODE";
            }
            case 0: {
                return "SUCCESS";
            }
            case 1: {
                return "SERVICE_MISSING";
            }
            case 2: {
                return "SERVICE_VERSION_UPDATE_REQUIRED";
            }
            case 3: {
                return "SERVICE_DISABLED";
            }
            case 4: {
                return "SIGN_IN_REQUIRED";
            }
            case 5: {
                return "INVALID_ACCOUNT";
            }
            case 6: {
                return "RESOLUTION_REQUIRED";
            }
            case 7: {
                return "NETWORK_ERROR";
            }
            case 8: {
                return "INTERNAL_ERROR";
            }
            case 9: {
                return "SERVICE_INVALID";
            }
            case 10: {
                return "DEVELOPER_ERROR";
            }
            case 11: {
                return "LICENSE_CHECK_FAILED";
            }
            case 12: {
                return "DATE_INVALID";
            }
        }
    }
    
    public static String getOpenSourceSoftwareLicenseInfo(final Context context) {
        Object o = new Uri$Builder().scheme("android.resource").authority("com.google.android.gms").appendPath("raw").appendPath("oss_notice").build();
        try {
            final InputStream openInputStream = context.getContentResolver().openInputStream((Uri)o);
            try {
                return (String)(o = new Scanner(openInputStream).useDelimiter("\\A").next());
            }
            catch (NoSuchElementException ex) {}
            finally {
                if (openInputStream != null) {
                    openInputStream.close();
                }
            }
        }
        catch (Exception ex2) {
            o = null;
        }
        return (String)o;
    }
    
    public static Context getRemoteContext(Context packageContext) {
        try {
            packageContext = packageContext.createPackageContext("com.google.android.gms", 3);
            return packageContext;
        }
        catch (PackageManager$NameNotFoundException ex) {
            return null;
        }
    }
    
    public static Resources getRemoteResource(final Context context) {
        try {
            return context.getPackageManager().getResourcesForApplication("com.google.android.gms");
        }
        catch (PackageManager$NameNotFoundException ex) {
            return null;
        }
    }
    
    public static int isGooglePlayServicesAvailable(final Context context) {
        final PackageManager packageManager = context.getPackageManager();
        while (true) {
            try {
                context.getResources().getString(R.string.common_google_play_services_unknown_issue);
                if (System.currentTimeMillis() < 1227312000288L) {
                    return 12;
                }
            }
            catch (Throwable t) {
                Log.e("GooglePlayServicesUtil", "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included.");
                continue;
            }
            break;
        }
        n(context);
        byte[] a;
        try {
            a = a(packageManager.getPackageInfo("com.android.vending", 64), GooglePlayServicesUtil.mD);
            if (a == null) {
                Log.w("GooglePlayServicesUtil", "Google Play Store signature invalid.");
                return 9;
            }
        }
        catch (PackageManager$NameNotFoundException ex2) {
            Log.w("GooglePlayServicesUtil", "Google Play Store is missing.");
            return 9;
        }
        PackageInfo packageInfo;
        try {
            packageInfo = packageManager.getPackageInfo("com.google.android.gms", 64);
            if (a(packageInfo, new byte[][] { a }) == null) {
                Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
                return 9;
            }
        }
        catch (PackageManager$NameNotFoundException ex3) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return 1;
        }
        if (packageInfo.versionCode < 4242000) {
            Log.w("GooglePlayServicesUtil", "Google Play services out of date.  Requires 4242000 but found " + packageInfo.versionCode);
            return 2;
        }
        try {
            if (!packageManager.getApplicationInfo("com.google.android.gms", 0).enabled) {
                return 3;
            }
        }
        catch (PackageManager$NameNotFoundException ex) {
            Log.wtf("GooglePlayServicesUtil", "Google Play services missing when getting application info.");
            ex.printStackTrace();
            return 1;
        }
        return 0;
    }
    
    public static boolean isUserRecoverableError(final int n) {
        switch (n) {
            default: {
                return false;
            }
            case 1:
            case 2:
            case 3:
            case 9:
            case 12: {
                return true;
            }
        }
    }
    
    public static void m(final Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        final int googlePlayServicesAvailable = isGooglePlayServicesAvailable(context);
        if (googlePlayServicesAvailable == 0) {
            return;
        }
        final Intent a = a(context, googlePlayServicesAvailable, -1);
        Log.e("GooglePlayServicesUtil", "GooglePlayServices not available due to error " + googlePlayServicesAvailable);
        if (a == null) {
            throw new GooglePlayServicesNotAvailableException(googlePlayServicesAvailable);
        }
        throw new GooglePlayServicesRepairableException(googlePlayServicesAvailable, "Google Play Services not available", a);
    }
    
    private static void n(final Context context) {
        final ApplicationInfo applicationInfo = null;
        int int1;
        while (true) {
            try {
                final ApplicationInfo applicationInfo2 = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                final Bundle metaData = applicationInfo2.metaData;
                if (metaData == null) {
                    throw new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
                }
                int1 = metaData.getInt("com.google.android.gms.version");
                if (int1 == 4242000) {
                    return;
                }
            }
            catch (PackageManager$NameNotFoundException ex) {
                Log.wtf("GooglePlayServicesUtil", "This should never happen.", (Throwable)ex);
                final ApplicationInfo applicationInfo2 = applicationInfo;
                continue;
            }
            break;
        }
        throw new IllegalStateException("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected 4242000 but found " + int1 + ".  You must have the" + " following declaration within the <application> element: " + "    <meta-data android:name=\"" + "com.google.android.gms.version" + "\" android:value=\"@integer/google_play_services_version\" />");
    }
    
    private static boolean o(final Context context) {
        boolean mm = false;
        if (GooglePlayServicesUtil.mK) {
            mm = GooglePlayServicesUtil.mM;
        }
        else {
            try {
                if (a(context.getPackageManager().getPackageInfo("com.google.android.apps.bazaar", 64), GooglePlayServicesUtil.mJ) != null) {
                    return true;
                }
            }
            catch (PackageManager$NameNotFoundException ex) {
                return false;
            }
        }
        return mm;
    }
    
    static boolean y(final int n) {
        boolean b = true;
        switch (z(n)) {
            default: {
                b = false;
                return b;
            }
            case 1: {
                return b;
            }
            case 0: {
                b = b;
                if (bi()) {
                    return false;
                }
                return b;
            }
            case 2: {
                return false;
            }
        }
    }
    
    private static int z(final int n) {
        int n2 = n;
        if (n == -1) {
            n2 = 2;
        }
        return n2;
    }
}
