// 
// Decompiled by Procyon v0.5.30
// 

package com.getkeepsafe.relinker.elf;

import java.nio.ByteOrder;
import java.nio.ByteBuffer;

public class Elf64Header extends Elf$Header
{
    private final ElfParser parser;
    
    public Elf64Header(final boolean bigEndian, final ElfParser parser) {
        this.bigEndian = bigEndian;
        this.parser = parser;
        final ByteBuffer allocate = ByteBuffer.allocate(8);
        ByteOrder byteOrder;
        if (bigEndian) {
            byteOrder = ByteOrder.BIG_ENDIAN;
        }
        else {
            byteOrder = ByteOrder.LITTLE_ENDIAN;
        }
        allocate.order(byteOrder);
        this.type = parser.readHalf(allocate, 16L);
        this.phoff = parser.readLong(allocate, 32L);
        this.shoff = parser.readLong(allocate, 40L);
        this.phentsize = parser.readHalf(allocate, 54L);
        this.phnum = parser.readHalf(allocate, 56L);
        this.shentsize = parser.readHalf(allocate, 58L);
        this.shnum = parser.readHalf(allocate, 60L);
        this.shstrndx = parser.readHalf(allocate, 62L);
    }
    
    @Override
    public Elf$DynamicStructure getDynamicStructure(final long n, final int n2) {
        return new Dynamic64Structure(this.parser, this, n, n2);
    }
    
    @Override
    public Elf$ProgramHeader getProgramHeader(final long n) {
        return new Program64Header(this.parser, this, n);
    }
    
    @Override
    public Elf$SectionHeader getSectionHeader(final int n) {
        return new Section64Header(this.parser, this, n);
    }
}
