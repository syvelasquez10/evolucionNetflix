// 
// Decompiled by Procyon v0.5.30
// 

package com.getkeepsafe.relinker.elf;

import java.nio.ByteOrder;
import java.nio.ByteBuffer;

public class Section64Header extends Elf$SectionHeader
{
    public Section64Header(final ElfParser elfParser, final Elf$Header elf$Header, final int n) {
        final ByteBuffer allocate = ByteBuffer.allocate(8);
        ByteOrder byteOrder;
        if (elf$Header.bigEndian) {
            byteOrder = ByteOrder.BIG_ENDIAN;
        }
        else {
            byteOrder = ByteOrder.LITTLE_ENDIAN;
        }
        allocate.order(byteOrder);
        this.info = elfParser.readWord(allocate, elf$Header.shoff + elf$Header.shentsize * n + 44L);
    }
}
