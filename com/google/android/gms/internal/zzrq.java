// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.nio.BufferOverflowException;
import java.nio.ReadOnlyBufferException;
import java.nio.ByteBuffer;

public final class zzrq
{
    private final ByteBuffer zzbbZ;
    
    private zzrq(final ByteBuffer zzbbZ) {
        this.zzbbZ = zzbbZ;
    }
    
    private zzrq(final byte[] array, final int n, final int n2) {
        this(ByteBuffer.wrap(array, n, n2));
    }
    
    public static zzrq zzA(final byte[] array) {
        return zzb(array, 0, array.length);
    }
    
    public static int zzB(final int n, final int n2) {
        return zzlv(n) + zzls(n2);
    }
    
    public static int zzY(final long n) {
        return zzab(n);
    }
    
    private static int zza(final CharSequence charSequence, int i) {
        final int length = charSequence.length();
        char c = '\0';
        while (i < length) {
            final char char1 = charSequence.charAt(i);
            int n;
            if (char1 < '\u0800') {
                c += (char)('\u007f' - char1 >>> 31);
                n = i;
            }
            else {
                final char c2 = (char)(c + '\u0002');
                n = i;
                c = c2;
                if ('\ud800' <= char1) {
                    n = i;
                    c = c2;
                    if (char1 <= '\udfff') {
                        if (Character.codePointAt(charSequence, i) < 65536) {
                            throw new IllegalArgumentException("Unpaired surrogate at index " + i);
                        }
                        n = i + 1;
                        c = c2;
                    }
                }
            }
            i = n + 1;
        }
        return c;
    }
    
    private static int zza(final CharSequence charSequence, final byte[] array, int n, int i) {
        final int length = charSequence.length();
        final int n2 = 0;
        int n3;
        char char1;
        for (n3 = n + i, i = n2; i < length && i + n < n3; ++i) {
            char1 = charSequence.charAt(i);
            if (char1 >= '\u0080') {
                break;
            }
            array[n + i] = (byte)char1;
        }
        if (i == length) {
            return n + length;
        }
        n += i;
        while (i < length) {
            final char char2 = charSequence.charAt(i);
            Label_0123: {
                if (char2 < '\u0080' && n < n3) {
                    final int n4 = n + 1;
                    array[n] = (byte)char2;
                    n = n4;
                }
                else if (char2 < '\u0800' && n <= n3 - 2) {
                    final int n5 = n + 1;
                    array[n] = (byte)(char2 >>> 6 | '\u03c0');
                    n = n5 + 1;
                    array[n5] = (byte)((char2 & '?') | '\u0080');
                }
                else if ((char2 < '\ud800' || '\udfff' < char2) && n <= n3 - 3) {
                    final int n6 = n + 1;
                    array[n] = (byte)(char2 >>> 12 | '\u01e0');
                    final int n7 = n6 + 1;
                    array[n6] = (byte)((char2 >>> 6 & '?') | '\u0080');
                    n = n7 + 1;
                    array[n7] = (byte)((char2 & '?') | '\u0080');
                }
                else {
                    if (n <= n3 - 4) {
                        int n8 = i;
                        if (i + 1 != charSequence.length()) {
                            ++i;
                            final char char3 = charSequence.charAt(i);
                            if (Character.isSurrogatePair(char2, char3)) {
                                final int codePoint = Character.toCodePoint(char2, char3);
                                final int n9 = n + 1;
                                array[n] = (byte)(codePoint >>> 18 | 0xF0);
                                n = n9 + 1;
                                array[n9] = (byte)((codePoint >>> 12 & 0x3F) | 0x80);
                                final int n10 = n + 1;
                                array[n] = (byte)((codePoint >>> 6 & 0x3F) | 0x80);
                                n = n10 + 1;
                                array[n10] = (byte)((codePoint & 0x3F) | 0x80);
                                break Label_0123;
                            }
                            n8 = i;
                        }
                        throw new IllegalArgumentException("Unpaired surrogate at index " + (n8 - 1));
                    }
                    throw new ArrayIndexOutOfBoundsException("Failed writing " + char2 + " at index " + n);
                }
            }
            ++i;
        }
        return n;
    }
    
    private static void zza(final CharSequence charSequence, final ByteBuffer byteBuffer) {
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        }
        if (byteBuffer.hasArray()) {
            try {
                byteBuffer.position(zza(charSequence, byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining()) - byteBuffer.arrayOffset());
                return;
            }
            catch (ArrayIndexOutOfBoundsException ex2) {
                final BufferOverflowException ex = new BufferOverflowException();
                ex.initCause(ex2);
                throw ex;
            }
        }
        zzb(charSequence, byteBuffer);
    }
    
    public static int zzab(final long n) {
        if ((0xFFFFFFFFFFFFFF80L & n) == 0x0L) {
            return 1;
        }
        if ((0xFFFFFFFFFFFFC000L & n) == 0x0L) {
            return 2;
        }
        if ((0xFFFFFFFFFFE00000L & n) == 0x0L) {
            return 3;
        }
        if ((0xFFFFFFFFF0000000L & n) == 0x0L) {
            return 4;
        }
        if ((0xFFFFFFF800000000L & n) == 0x0L) {
            return 5;
        }
        if ((0xFFFFFC0000000000L & n) == 0x0L) {
            return 6;
        }
        if ((0xFFFE000000000000L & n) == 0x0L) {
            return 7;
        }
        if ((0xFF00000000000000L & n) == 0x0L) {
            return 8;
        }
        if ((Long.MIN_VALUE & n) == 0x0L) {
            return 9;
        }
        return 10;
    }
    
    public static int zzax(final boolean b) {
        return 1;
    }
    
    public static int zzb(final int n, final zzrx zzrx) {
        return zzlv(n) * 2 + zzd(zzrx);
    }
    
    public static zzrq zzb(final byte[] array, final int n, final int n2) {
        return new zzrq(array, n, n2);
    }
    
    private static void zzb(final CharSequence charSequence, final ByteBuffer byteBuffer) {
        for (int length = charSequence.length(), i = 0; i < length; ++i) {
            final char char1 = charSequence.charAt(i);
            if (char1 < '\u0080') {
                byteBuffer.put((byte)char1);
            }
            else if (char1 < '\u0800') {
                byteBuffer.put((byte)(char1 >>> 6 | '\u03c0'));
                byteBuffer.put((byte)((char1 & '?') | '\u0080'));
            }
            else {
                if (char1 >= '\ud800' && '\udfff' >= char1) {
                    int n = i;
                    if (i + 1 != charSequence.length()) {
                        ++i;
                        final char char2 = charSequence.charAt(i);
                        if (Character.isSurrogatePair(char1, char2)) {
                            final int codePoint = Character.toCodePoint(char1, char2);
                            byteBuffer.put((byte)(codePoint >>> 18 | 0xF0));
                            byteBuffer.put((byte)((codePoint >>> 12 & 0x3F) | 0x80));
                            byteBuffer.put((byte)((codePoint >>> 6 & 0x3F) | 0x80));
                            byteBuffer.put((byte)((codePoint & 0x3F) | 0x80));
                            continue;
                        }
                        n = i;
                    }
                    throw new IllegalArgumentException("Unpaired surrogate at index " + (n - 1));
                }
                byteBuffer.put((byte)(char1 >>> 12 | '\u01e0'));
                byteBuffer.put((byte)((char1 >>> 6 & '?') | '\u0080'));
                byteBuffer.put((byte)((char1 & '?') | '\u0080'));
            }
        }
    }
    
    public static int zzc(final int n, final float n2) {
        return zzlv(n) + zzj(n2);
    }
    
    public static int zzc(final int n, final zzrx zzrx) {
        return zzlv(n) + zze(zzrx);
    }
    
    public static int zzc(final int n, final boolean b) {
        return zzlv(n) + zzax(b);
    }
    
    private static int zzc(final CharSequence charSequence) {
        int length;
        int n;
        for (length = charSequence.length(), n = '\0'; n < length && charSequence.charAt(n) < '\u0080'; ++n) {}
        int n2 = n;
        int n3 = length;
        int n4;
        while (true) {
            n4 = n3;
            if (n2 >= length) {
                break;
            }
            final char char1 = charSequence.charAt(n2);
            if (char1 >= '\u0800') {
                n4 = n3 + zza(charSequence, n2);
                break;
            }
            ++n2;
            n3 += '\u007f' - char1 >>> 31;
        }
        if (n4 < length) {
            throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (n4 + 4294967296L));
        }
        return n4;
    }
    
    public static int zzd(final int n, final long n2) {
        return zzlv(n) + zzY(n2);
    }
    
    public static int zzd(final zzrx zzrx) {
        return zzrx.zzDx();
    }
    
    public static int zze(final zzrx zzrx) {
        final int zzDx = zzrx.zzDx();
        return zzDx + zzlx(zzDx);
    }
    
    public static int zzfy(final String s) {
        final int zzc = zzc(s);
        return zzc + zzlx(zzc);
    }
    
    public static int zzj(final float n) {
        return 4;
    }
    
    public static int zzl(final int n, final String s) {
        return zzlv(n) + zzfy(s);
    }
    
    public static int zzls(final int n) {
        if (n >= 0) {
            return zzlx(n);
        }
        return 10;
    }
    
    public static int zzlv(final int n) {
        return zzlx(zzsa.zzE(n, 0));
    }
    
    public static int zzlx(final int n) {
        if ((n & 0xFFFFFF80) == 0x0) {
            return 1;
        }
        if ((n & 0xFFFFC000) == 0x0) {
            return 2;
        }
        if ((0xFFE00000 & n) == 0x0) {
            return 3;
        }
        if ((0xF0000000 & n) == 0x0) {
            return 4;
        }
        return 5;
    }
    
    public void zzD(final int n, final int n2) {
        this.zzlw(zzsa.zzE(n, n2));
    }
    
    public void zzD(final byte[] array) {
        this.zzc(array, 0, array.length);
    }
    
    public void zzW(final long n) {
        this.zzaa(n);
    }
    
    public void zza(final int n, final zzrx zzrx) {
        this.zzD(n, 2);
        this.zzc(zzrx);
    }
    
    public void zzaa(long n) {
        while ((0xFFFFFFFFFFFFFF80L & n) != 0x0L) {
            this.zzlu(((int)n & 0x7F) | 0x80);
            n >>>= 7;
        }
        this.zzlu((int)n);
    }
    
    public void zzaw(final boolean b) {
        int n;
        if (b) {
            n = 1;
        }
        else {
            n = 0;
        }
        this.zzlu(n);
    }
    
    public void zzb(final byte b) {
        if (!this.zzbbZ.hasRemaining()) {
            throw new zzrq$zza(this.zzbbZ.position(), this.zzbbZ.limit());
        }
        this.zzbbZ.put(b);
    }
    
    public void zzb(final int n, final float n2) {
        this.zzD(n, 5);
        this.zzi(n2);
    }
    
    public void zzb(final int n, final long n2) {
        this.zzD(n, 0);
        this.zzW(n2);
    }
    
    public void zzb(final int n, final String s) {
        this.zzD(n, 2);
        this.zzfx(s);
    }
    
    public void zzb(final int n, final boolean b) {
        this.zzD(n, 0);
        this.zzaw(b);
    }
    
    public void zzb(final zzrx zzrx) {
        zzrx.zza(this);
    }
    
    public void zzc(final zzrx zzrx) {
        this.zzlw(zzrx.zzDw());
        zzrx.zza(this);
    }
    
    public void zzc(final byte[] array, final int n, final int n2) {
        if (this.zzbbZ.remaining() >= n2) {
            this.zzbbZ.put(array, n, n2);
            return;
        }
        throw new zzrq$zza(this.zzbbZ.position(), this.zzbbZ.limit());
    }
    
    public void zzfx(final String s) {
        try {
            final int zzlx = zzlx(s.length());
            if (zzlx == zzlx(s.length() * 3)) {
                final int position = this.zzbbZ.position();
                this.zzbbZ.position(position + zzlx);
                zza(s, this.zzbbZ);
                final int position2 = this.zzbbZ.position();
                this.zzbbZ.position(position);
                this.zzlw(position2 - position - zzlx);
                this.zzbbZ.position(position2);
                return;
            }
            this.zzlw(zzc(s));
            zza(s, this.zzbbZ);
        }
        catch (BufferOverflowException ex) {
            throw new zzrq$zza(this.zzbbZ.position(), this.zzbbZ.limit());
        }
    }
    
    public void zzi(final float n) {
        this.zzly(Float.floatToIntBits(n));
    }
    
    public void zzlq(final int n) {
        if (n >= 0) {
            this.zzlw(n);
            return;
        }
        this.zzaa(n);
    }
    
    public void zzlu(final int n) {
        this.zzb((byte)n);
    }
    
    public void zzlw(int n) {
        while ((n & 0xFFFFFF80) != 0x0) {
            this.zzlu((n & 0x7F) | 0x80);
            n >>>= 7;
        }
        this.zzlu(n);
    }
    
    public void zzly(final int n) {
        this.zzlu(n & 0xFF);
        this.zzlu(n >> 8 & 0xFF);
        this.zzlu(n >> 16 & 0xFF);
        this.zzlu(n >> 24 & 0xFF);
    }
    
    public void zzz(final int n, final int n2) {
        this.zzD(n, 0);
        this.zzlq(n2);
    }
}
