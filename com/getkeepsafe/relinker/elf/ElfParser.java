// 
// Decompiled by Procyon v0.5.30
// 

package com.getkeepsafe.relinker.elf;

import java.io.EOFException;
import java.util.Iterator;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.nio.ByteOrder;
import java.nio.ByteBuffer;
import java.io.FileInputStream;
import java.io.File;
import java.nio.channels.FileChannel;
import java.io.Closeable;

public class ElfParser implements Elf, Closeable
{
    private final int MAGIC;
    private final FileChannel channel;
    
    public ElfParser(final File file) {
        this.MAGIC = 1179403647;
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("File is null or does not exist");
        }
        this.channel = new FileInputStream(file).getChannel();
    }
    
    private long offsetFromVma(final Elf$Header elf$Header, final long n, final long n2) {
        for (long n3 = 0L; n3 < n; ++n3) {
            final Elf$ProgramHeader programHeader = elf$Header.getProgramHeader(n3);
            if (programHeader.type == 1L && programHeader.vaddr <= n2 && n2 <= programHeader.vaddr + programHeader.memsz) {
                return n2 - programHeader.vaddr + programHeader.offset;
            }
        }
        throw new IllegalStateException("Could not map vma to file offset!");
    }
    
    @Override
    public void close() {
        this.channel.close();
    }
    
    public Elf$Header parseHeader() {
        this.channel.position(0L);
        final ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        if (this.readWord(allocate, 0L) != 1179403647L) {
            throw new IllegalArgumentException("Invalid ELF Magic!");
        }
        final short byte1 = this.readByte(allocate, 4L);
        final boolean b = this.readByte(allocate, 5L) == 2;
        if (byte1 == 1) {
            return new Elf32Header(b, this);
        }
        if (byte1 == 2) {
            return new Elf64Header(b, this);
        }
        throw new IllegalStateException("Invalid class type!");
    }
    
    public List<String> parseNeededDependencies() {
        this.channel.position(0L);
        final ArrayList<String> list = new ArrayList<String>();
        final Elf$Header header = this.parseHeader();
        final ByteBuffer allocate = ByteBuffer.allocate(8);
        ByteOrder byteOrder;
        if (header.bigEndian) {
            byteOrder = ByteOrder.BIG_ENDIAN;
        }
        else {
            byteOrder = ByteOrder.LITTLE_ENDIAN;
        }
        allocate.order(byteOrder);
        long info;
        if ((info = header.phnum) == 65535L) {
            info = header.getSectionHeader(0).info;
        }
        long n = 0L;
        while (true) {
            while (n < info) {
                final Elf$ProgramHeader programHeader = header.getProgramHeader(n);
                if (programHeader.type == 2L) {
                    final long offset = programHeader.offset;
                    if (offset == 0L) {
                        return (List<String>)Collections.unmodifiableList((List<?>)list);
                    }
                    int n2 = 0;
                    final ArrayList<Long> list2 = new ArrayList<Long>();
                    long n3 = 0L;
                    Elf$DynamicStructure dynamicStructure;
                    long val;
                    do {
                        dynamicStructure = header.getDynamicStructure(offset, n2);
                        if (dynamicStructure.tag == 1L) {
                            list2.add(dynamicStructure.val);
                            val = n3;
                        }
                        else {
                            val = n3;
                            if (dynamicStructure.tag == 5L) {
                                val = dynamicStructure.val;
                            }
                        }
                        ++n2;
                        n3 = val;
                    } while (dynamicStructure.tag != 0L);
                    if (val == 0L) {
                        throw new IllegalStateException("String table offset not found!");
                    }
                    final long offsetFromVma = this.offsetFromVma(header, info, val);
                    final Iterator<Object> iterator = list2.iterator();
                    while (iterator.hasNext()) {
                        list.add(this.readString(allocate, iterator.next() + offsetFromVma));
                    }
                    return list;
                }
                else {
                    ++n;
                }
            }
            final long offset = 0L;
            continue;
        }
    }
    
    protected void read(final ByteBuffer byteBuffer, final long n, final int n2) {
        byteBuffer.position(0);
        byteBuffer.limit(n2);
        int read;
        for (long n3 = 0L; n3 < n2; n3 += read) {
            read = this.channel.read(byteBuffer, n + n3);
            if (read == -1) {
                throw new EOFException();
            }
        }
        byteBuffer.position(0);
    }
    
    protected short readByte(final ByteBuffer byteBuffer, final long n) {
        this.read(byteBuffer, n, 1);
        return (short)(byteBuffer.get() & 0xFF);
    }
    
    protected int readHalf(final ByteBuffer byteBuffer, final long n) {
        this.read(byteBuffer, n, 2);
        return byteBuffer.getShort() & 0xFFFF;
    }
    
    protected long readLong(final ByteBuffer byteBuffer, final long n) {
        this.read(byteBuffer, n, 8);
        return byteBuffer.getLong();
    }
    
    protected String readString(final ByteBuffer byteBuffer, long n) {
        final StringBuilder sb = new StringBuilder();
        while (true) {
            final short byte1 = this.readByte(byteBuffer, n);
            if (byte1 == 0) {
                break;
            }
            sb.append((char)byte1);
            ++n;
        }
        return sb.toString();
    }
    
    protected long readWord(final ByteBuffer byteBuffer, final long n) {
        this.read(byteBuffer, n, 4);
        return byteBuffer.getInt() & 0xFFFFFFFFL;
    }
}
