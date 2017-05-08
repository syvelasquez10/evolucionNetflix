// 
// Decompiled by Procyon v0.5.30
// 

package com.getkeepsafe.relinker.elf;

import java.nio.ByteOrder;
import java.nio.ByteBuffer;

public class Program64Header extends Elf$ProgramHeader
{
    public Program64Header(final ElfParser elfParser, final Elf$Header elf$Header, long n) {
        final ByteBuffer allocate = ByteBuffer.allocate(8);
        ByteOrder byteOrder;
        if (elf$Header.bigEndian) {
            byteOrder = ByteOrder.BIG_ENDIAN;
        }
        else {
            byteOrder = ByteOrder.LITTLE_ENDIAN;
        }
        allocate.order(byteOrder);
        n = elf$Header.phoff + elf$Header.phentsize * n;
        this.type = elfParser.readWord(allocate, n);
        this.offset = elfParser.readLong(allocate, 8L + n);
        this.vaddr = elfParser.readLong(allocate, 16L + n);
        this.memsz = elfParser.readLong(allocate, n + 40L);
    }
}
