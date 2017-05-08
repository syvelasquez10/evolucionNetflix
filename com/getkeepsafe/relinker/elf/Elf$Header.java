// 
// Decompiled by Procyon v0.5.30
// 

package com.getkeepsafe.relinker.elf;

public abstract class Elf$Header
{
    public boolean bigEndian;
    public int phentsize;
    public int phnum;
    public long phoff;
    public int shentsize;
    public int shnum;
    public long shoff;
    public int shstrndx;
    public int type;
    
    public abstract Elf$DynamicStructure getDynamicStructure(final long p0, final int p1);
    
    public abstract Elf$ProgramHeader getProgramHeader(final long p0);
    
    public abstract Elf$SectionHeader getSectionHeader(final int p0);
}
