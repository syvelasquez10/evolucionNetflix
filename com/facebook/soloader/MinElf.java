// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.soloader;

import java.nio.ByteOrder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.io.FileInputStream;
import java.io.File;

public final class MinElf
{
    public static String[] extract_DT_NEEDED(File file) {
        file = (File)new FileInputStream(file);
        try {
            return extract_DT_NEEDED(((FileInputStream)file).getChannel());
        }
        finally {
            ((FileInputStream)file).close();
        }
    }
    
    public static String[] extract_DT_NEEDED(final FileChannel fileChannel) {
        final ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        if (getu32(fileChannel, allocate, 0L) != 1179403647L) {
            throw new MinElf$ElfError("file is not ELF");
        }
        boolean b;
        if (getu8(fileChannel, allocate, 4L) == 1) {
            b = true;
        }
        else {
            b = false;
        }
        if (getu8(fileChannel, allocate, 5L) == 2) {
            allocate.order(ByteOrder.BIG_ENDIAN);
        }
        long n;
        if (b) {
            n = getu32(fileChannel, allocate, 28L);
        }
        else {
            n = get64(fileChannel, allocate, 32L);
        }
        long n2;
        if (b) {
            n2 = getu16(fileChannel, allocate, 44L);
        }
        else {
            n2 = getu16(fileChannel, allocate, 56L);
        }
        int n3;
        if (b) {
            n3 = getu16(fileChannel, allocate, 42L);
        }
        else {
            n3 = getu16(fileChannel, allocate, 54L);
        }
        long n4 = n2;
        if (n2 == 65535L) {
            long n5;
            if (b) {
                n5 = getu32(fileChannel, allocate, 32L);
            }
            else {
                n5 = get64(fileChannel, allocate, 40L);
            }
            if (b) {
                n4 = getu32(fileChannel, allocate, n5 + 28L);
            }
            else {
                n4 = getu32(fileChannel, allocate, n5 + 44L);
            }
        }
        final long n6 = 0L;
        long n7 = 0L;
        long n8 = n;
        while (true) {
            Label_0343: {
                long n9;
                while (true) {
                    n9 = n6;
                    if (n7 >= n4) {
                        break;
                    }
                    long n10;
                    if (b) {
                        n10 = getu32(fileChannel, allocate, 0L + n8);
                    }
                    else {
                        n10 = getu32(fileChannel, allocate, 0L + n8);
                    }
                    if (n10 == 2L) {
                        if (b) {
                            n9 = getu32(fileChannel, allocate, 4L + n8);
                            break;
                        }
                        break Label_0343;
                    }
                    else {
                        n8 += n3;
                        ++n7;
                    }
                }
                if (n9 == 0L) {
                    throw new MinElf$ElfError("ELF file does not contain dynamic linking information");
                }
                int n11 = 0;
                long n12 = 0L;
                long n13 = n9;
                while (true) {
                    long n14;
                    if (b) {
                        n14 = getu32(fileChannel, allocate, 0L + n13);
                    }
                    else {
                        n14 = get64(fileChannel, allocate, 0L + n13);
                    }
                    if (n14 == 1L) {
                        if (n11 == Integer.MAX_VALUE) {
                            throw new MinElf$ElfError("malformed DT_NEEDED section");
                        }
                        ++n11;
                    }
                    else if (n14 == 5L) {
                        if (b) {
                            n12 = getu32(fileChannel, allocate, 4L + n13);
                        }
                        else {
                            n12 = get64(fileChannel, allocate, 8L + n13);
                        }
                    }
                    long n15;
                    if (b) {
                        n15 = 8L;
                    }
                    else {
                        n15 = 16L;
                    }
                    if (n14 == 0L) {
                        if (n12 == 0L) {
                            throw new MinElf$ElfError("Dynamic section string-table not found");
                        }
                        while (true) {
                            long n27;
                            for (int n16 = 0; n16 < n4; ++n16, n += n27) {
                                long n17;
                                if (b) {
                                    n17 = getu32(fileChannel, allocate, 0L + n);
                                }
                                else {
                                    n17 = getu32(fileChannel, allocate, 0L + n);
                                }
                                if (n17 == 1L) {
                                    long n18;
                                    if (b) {
                                        n18 = getu32(fileChannel, allocate, 8L + n);
                                    }
                                    else {
                                        n18 = get64(fileChannel, allocate, 16L + n);
                                    }
                                    long n19;
                                    if (b) {
                                        n19 = getu32(fileChannel, allocate, 20L + n);
                                    }
                                    else {
                                        n19 = get64(fileChannel, allocate, 40L + n);
                                    }
                                    if (n18 <= n12 && n12 < n19 + n18) {
                                        long n20;
                                        if (b) {
                                            n20 = getu32(fileChannel, allocate, 4L + n);
                                        }
                                        else {
                                            n20 = get64(fileChannel, allocate, 8L + n);
                                        }
                                        final long n21 = n20 + (n12 - n18);
                                        if (n21 == 0L) {
                                            throw new MinElf$ElfError("did not find file offset of DT_STRTAB table");
                                        }
                                        final String[] array = new String[n11];
                                        int n22 = 0;
                                        while (true) {
                                            long n23;
                                            if (b) {
                                                n23 = getu32(fileChannel, allocate, 0L + n9);
                                            }
                                            else {
                                                n23 = get64(fileChannel, allocate, 0L + n9);
                                            }
                                            int n24 = n22;
                                            if (n23 == 1L) {
                                                long n25;
                                                if (b) {
                                                    n25 = getu32(fileChannel, allocate, 4L + n9);
                                                }
                                                else {
                                                    n25 = get64(fileChannel, allocate, 8L + n9);
                                                }
                                                array[n22] = getSz(fileChannel, allocate, n25 + n21);
                                                if (n22 == Integer.MAX_VALUE) {
                                                    throw new MinElf$ElfError("malformed DT_NEEDED section");
                                                }
                                                n24 = n22 + 1;
                                            }
                                            long n26;
                                            if (b) {
                                                n26 = 8L;
                                            }
                                            else {
                                                n26 = 16L;
                                            }
                                            if (n23 == 0L) {
                                                if (n24 != array.length) {
                                                    throw new MinElf$ElfError("malformed DT_NEEDED section");
                                                }
                                                return array;
                                            }
                                            else {
                                                n9 += n26;
                                                n22 = n24;
                                            }
                                        }
                                    }
                                }
                                n27 = n3;
                            }
                            final long n21 = 0L;
                            continue;
                        }
                    }
                    else {
                        n13 += n15;
                    }
                }
            }
            long n9 = get64(fileChannel, allocate, 8L + n8);
            continue;
        }
    }
    
    private static long get64(final FileChannel fileChannel, final ByteBuffer byteBuffer, final long n) {
        read(fileChannel, byteBuffer, 8, n);
        return byteBuffer.getLong();
    }
    
    private static String getSz(final FileChannel fileChannel, final ByteBuffer byteBuffer, long n) {
        final StringBuilder sb = new StringBuilder();
        while (true) {
            final short getu8 = getu8(fileChannel, byteBuffer, n);
            if (getu8 == 0) {
                break;
            }
            sb.append((char)getu8);
            ++n;
        }
        return sb.toString();
    }
    
    private static int getu16(final FileChannel fileChannel, final ByteBuffer byteBuffer, final long n) {
        read(fileChannel, byteBuffer, 2, n);
        return byteBuffer.getShort() & 0xFFFF;
    }
    
    private static long getu32(final FileChannel fileChannel, final ByteBuffer byteBuffer, final long n) {
        read(fileChannel, byteBuffer, 4, n);
        return byteBuffer.getInt() & 0xFFFFFFFFL;
    }
    
    private static short getu8(final FileChannel fileChannel, final ByteBuffer byteBuffer, final long n) {
        read(fileChannel, byteBuffer, 1, n);
        return (short)(byteBuffer.get() & 0xFF);
    }
    
    private static void read(final FileChannel fileChannel, final ByteBuffer byteBuffer, int read, long n) {
        byteBuffer.position(0);
        byteBuffer.limit(read);
        while (byteBuffer.remaining() > 0) {
            read = fileChannel.read(byteBuffer, n);
            if (read == -1) {
                break;
            }
            n += read;
        }
        if (byteBuffer.remaining() > 0) {
            throw new MinElf$ElfError("ELF file truncated");
        }
        byteBuffer.position(0);
    }
}
