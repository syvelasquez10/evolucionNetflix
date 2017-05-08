// 
// Decompiled by Procyon v0.5.30
// 

package com.getkeepsafe.relinker.elf;

import java.nio.ByteOrder;
import java.nio.ByteBuffer;

public class Dynamic32Structure extends Elf$DynamicStructure
{
    public Dynamic32Structure(final ElfParser elfParser, final Elf$Header elf$Header, long n, final int n2) {
        final ByteBuffer allocate = ByteBuffer.allocate(4);
        ByteOrder byteOrder;
        if (elf$Header.bigEndian) {
            byteOrder = ByteOrder.BIG_ENDIAN;
        }
        else {
            byteOrder = ByteOrder.LITTLE_ENDIAN;
        }
        allocate.order(byteOrder);
        n += n2 * 8;
        this.tag = elfParser.readWord(allocate, n);
        this.val = elfParser.readWord(allocate, n + 4L);
    }
}
