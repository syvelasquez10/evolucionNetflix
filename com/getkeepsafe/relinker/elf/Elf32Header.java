// 
// Decompiled by Procyon v0.5.30
// 

package com.getkeepsafe.relinker.elf;

import java.nio.ByteOrder;
import java.nio.ByteBuffer;

public class Elf32Header extends Elf$Header
{
    private final ElfParser parser;
    
    public Elf32Header(final boolean bigEndian, final ElfParser parser) {
        this.bigEndian = bigEndian;
        this.parser = parser;
        final ByteBuffer allocate = ByteBuffer.allocate(4);
        ByteOrder byteOrder;
        if (bigEndian) {
            byteOrder = ByteOrder.BIG_ENDIAN;
        }
        else {
            byteOrder = ByteOrder.LITTLE_ENDIAN;
        }
        allocate.order(byteOrder);
        this.type = parser.readHalf(allocate, 16L);
        this.phoff = parser.readWord(allocate, 28L);
        this.shoff = parser.readWord(allocate, 32L);
        this.phentsize = parser.readHalf(allocate, 42L);
        this.phnum = parser.readHalf(allocate, 44L);
        this.shentsize = parser.readHalf(allocate, 46L);
        this.shnum = parser.readHalf(allocate, 48L);
        this.shstrndx = parser.readHalf(allocate, 50L);
    }
    
    @Override
    public Elf$DynamicStructure getDynamicStructure(final long n, final int n2) {
        return new Dynamic32Structure(this.parser, this, n, n2);
    }
    
    @Override
    public Elf$ProgramHeader getProgramHeader(final long n) {
        return new Program32Header(this.parser, this, n);
    }
    
    @Override
    public Elf$SectionHeader getSectionHeader(final int n) {
        return new Section32Header(this.parser, this, n);
    }
}
