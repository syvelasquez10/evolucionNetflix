// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import java.util.Arrays;

public abstract class zze
{
    public static final zze zzacG;
    public static final zze zzacH;
    public static final zze zzacI;
    public static final zze zzacJ;
    public static final zze zzacK;
    public static final zze zzacL;
    public static final zze zzacM;
    public static final zze zzacN;
    public static final zze zzacO;
    public static final zze zzacP;
    public static final zze zzacQ;
    public static final zze zzacR;
    public static final zze zzacS;
    public static final zze zzacT;
    public static final zze zzacU;
    
    static {
        zzacG = zza("\t\n\u000b\f\r \u0085\u1680\u2028\u2029\u205f\u3000 \u180e\u202f").zza(zza('\u2000', '\u200a'));
        zzacH = zza("\t\n\u000b\f\r \u0085\u1680\u2028\u2029\u205f\u3000").zza(zza('\u2000', '\u2006')).zza(zza('\u2008', '\u200a'));
        zzacI = zza('\0', '\u007f');
        zze zzacJ2 = zza('0', '9');
        final char[] charArray = "\u0660\u06f0\u07c0\u0966\u09e6\u0a66\u0ae6\u0b66\u0be6\u0c66\u0ce6\u0d66\u0e50\u0ed0\u0f20\u1040\u1090\u17e0\u1810\u1946\u19d0\u1b50\u1bb0\u1c40\u1c50\ua620\ua8d0\ua900\uaa50\uff10".toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char c = charArray[i];
            zzacJ2 = zzacJ2.zza(zza(c, (char)(c + '\t')));
        }
        zzacJ = zzacJ2;
        zzacK = zza('\t', '\r').zza(zza('\u001c', ' ')).zza(zzc('\u1680')).zza(zzc('\u180e')).zza(zza('\u2000', '\u2006')).zza(zza('\u2008', '\u200b')).zza(zza('\u2028', '\u2029')).zza(zzc('\u205f')).zza(zzc('\u3000'));
        zzacL = new zze$1();
        zzacM = new zze$5();
        zzacN = new zze$6();
        zzacO = new zze$7();
        zzacP = new zze$8();
        zzacQ = zza('\0', '\u001f').zza(zza('\u007f', '\u009f'));
        zzacR = zza('\0', ' ').zza(zza('\u007f', ' ')).zza(zzc('\u00ad')).zza(zza('\u0600', '\u0603')).zza(zza("\u06dd\u070f\u1680\u17b4\u17b5\u180e")).zza(zza('\u2000', '\u200f')).zza(zza('\u2028', '\u202f')).zza(zza('\u205f', '\u2064')).zza(zza('\u206a', '\u206f')).zza(zzc('\u3000')).zza(zza('\ud800', '\uf8ff')).zza(zza("\ufeff\ufff9\ufffa\ufffb"));
        zzacS = zza('\0', '\u04f9').zza(zzc('\u05be')).zza(zza('\u05d0', '\u05ea')).zza(zzc('\u05f3')).zza(zzc('\u05f4')).zza(zza('\u0600', '\u06ff')).zza(zza('\u0750', '\u077f')).zza(zza('\u0e00', '\u0e7f')).zza(zza('\u1e00', '\u20af')).zza(zza('\u2100', '\u213a')).zza(zza('\ufb50', '\ufdff')).zza(zza('\ufe70', '\ufeff')).zza(zza('\uff61', '\uffdc'));
        zzacT = new zze$9();
        zzacU = new zze$10();
    }
    
    public static zze zza(final char c, final char c2) {
        zzx.zzZ(c2 >= c);
        return new zze$4(c, c2);
    }
    
    public static zze zza(final CharSequence charSequence) {
        switch (charSequence.length()) {
            default: {
                final char[] charArray = charSequence.toString().toCharArray();
                Arrays.sort(charArray);
                return new zze$3(charArray);
            }
            case 0: {
                return zze.zzacU;
            }
            case 1: {
                return zzc(charSequence.charAt(0));
            }
            case 2: {
                return new zze$2(charSequence.charAt(0), charSequence.charAt(1));
            }
        }
    }
    
    public static zze zzc(final char c) {
        return new zze$11(c);
    }
    
    public zze zza(final zze zze) {
        return new zze$zza(Arrays.asList(this, zzx.zzv(zze)));
    }
    
    public boolean zzb(final CharSequence charSequence) {
        for (int i = charSequence.length() - 1; i >= 0; --i) {
            if (!this.zzd(charSequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    public abstract boolean zzd(final char p0);
}
