// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.upstream;

import com.google.android.exoplayer.util.Util;
import java.util.Arrays;
import com.google.android.exoplayer.util.Assertions;

public final class DefaultAllocator implements Allocator
{
    private int allocatedCount;
    private Allocation[] availableAllocations;
    private int availableCount;
    private final int individualAllocationSize;
    private final byte[] initialAllocationBlock;
    
    public DefaultAllocator(final int n) {
        this(n, 0);
    }
    
    public DefaultAllocator(final int individualAllocationSize, final int availableCount) {
        final boolean b = true;
        int i = 0;
        Assertions.checkArgument(individualAllocationSize > 0);
        Assertions.checkArgument(availableCount >= 0 && b);
        this.individualAllocationSize = individualAllocationSize;
        this.availableCount = availableCount;
        this.availableAllocations = new Allocation[availableCount + 100];
        if (availableCount > 0) {
            this.initialAllocationBlock = new byte[availableCount * individualAllocationSize];
            while (i < availableCount) {
                this.availableAllocations[i] = new Allocation(this.initialAllocationBlock, i * individualAllocationSize);
                ++i;
            }
        }
        else {
            this.initialAllocationBlock = null;
        }
    }
    
    @Override
    public Allocation allocate() {
        synchronized (this) {
            ++this.allocatedCount;
            Allocation allocation;
            if (this.availableCount > 0) {
                final Allocation[] availableAllocations = this.availableAllocations;
                final int availableCount = this.availableCount - 1;
                this.availableCount = availableCount;
                allocation = availableAllocations[availableCount];
                this.availableAllocations[this.availableCount] = null;
            }
            else {
                allocation = new Allocation(new byte[this.individualAllocationSize], 0);
            }
            return allocation;
        }
    }
    
    @Override
    public int getIndividualAllocationLength() {
        return this.individualAllocationSize;
    }
    
    @Override
    public int getTotalBytesAllocated() {
        synchronized (this) {
            return this.allocatedCount * this.individualAllocationSize;
        }
    }
    
    @Override
    public void release(final Allocation allocation) {
        while (true) {
            while (true) {
                synchronized (this) {
                    if (allocation.data != this.initialAllocationBlock) {
                        if (allocation.data.length != this.individualAllocationSize) {
                            final boolean b = false;
                            Assertions.checkArgument(b);
                            --this.allocatedCount;
                            if (this.availableCount == this.availableAllocations.length) {
                                this.availableAllocations = Arrays.copyOf(this.availableAllocations, this.availableAllocations.length * 2);
                            }
                            this.availableAllocations[this.availableCount++] = allocation;
                            this.notifyAll();
                            return;
                        }
                    }
                }
                final boolean b = true;
                continue;
            }
        }
    }
    
    @Override
    public void release(final Allocation[] p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: monitorenter   
        //     2: aload_0        
        //     3: getfield        com/google/android/exoplayer/upstream/DefaultAllocator.availableCount:I
        //     6: aload_1        
        //     7: arraylength    
        //     8: iadd           
        //     9: aload_0        
        //    10: getfield        com/google/android/exoplayer/upstream/DefaultAllocator.availableAllocations:[Lcom/google/android/exoplayer/upstream/Allocation;
        //    13: arraylength    
        //    14: if_icmplt       48
        //    17: aload_0        
        //    18: aload_0        
        //    19: getfield        com/google/android/exoplayer/upstream/DefaultAllocator.availableAllocations:[Lcom/google/android/exoplayer/upstream/Allocation;
        //    22: aload_0        
        //    23: getfield        com/google/android/exoplayer/upstream/DefaultAllocator.availableAllocations:[Lcom/google/android/exoplayer/upstream/Allocation;
        //    26: arraylength    
        //    27: iconst_2       
        //    28: imul           
        //    29: aload_0        
        //    30: getfield        com/google/android/exoplayer/upstream/DefaultAllocator.availableCount:I
        //    33: aload_1        
        //    34: arraylength    
        //    35: iadd           
        //    36: invokestatic    java/lang/Math.max:(II)I
        //    39: invokestatic    java/util/Arrays.copyOf:([Ljava/lang/Object;I)[Ljava/lang/Object;
        //    42: checkcast       [Lcom/google/android/exoplayer/upstream/Allocation;
        //    45: putfield        com/google/android/exoplayer/upstream/DefaultAllocator.availableAllocations:[Lcom/google/android/exoplayer/upstream/Allocation;
        //    48: aload_1        
        //    49: arraylength    
        //    50: istore_3       
        //    51: iconst_0       
        //    52: istore_2       
        //    53: iload_2        
        //    54: iload_3        
        //    55: if_icmpge       130
        //    58: aload_1        
        //    59: iload_2        
        //    60: aaload         
        //    61: astore          6
        //    63: aload           6
        //    65: getfield        com/google/android/exoplayer/upstream/Allocation.data:[B
        //    68: aload_0        
        //    69: getfield        com/google/android/exoplayer/upstream/DefaultAllocator.initialAllocationBlock:[B
        //    72: if_acmpeq       153
        //    75: aload           6
        //    77: getfield        com/google/android/exoplayer/upstream/Allocation.data:[B
        //    80: arraylength    
        //    81: aload_0        
        //    82: getfield        com/google/android/exoplayer/upstream/DefaultAllocator.individualAllocationSize:I
        //    85: if_icmpne       159
        //    88: goto            153
        //    91: iload           5
        //    93: invokestatic    com/google/android/exoplayer/util/Assertions.checkArgument:(Z)V
        //    96: aload_0        
        //    97: getfield        com/google/android/exoplayer/upstream/DefaultAllocator.availableAllocations:[Lcom/google/android/exoplayer/upstream/Allocation;
        //   100: astore          7
        //   102: aload_0        
        //   103: getfield        com/google/android/exoplayer/upstream/DefaultAllocator.availableCount:I
        //   106: istore          4
        //   108: aload_0        
        //   109: iload           4
        //   111: iconst_1       
        //   112: iadd           
        //   113: putfield        com/google/android/exoplayer/upstream/DefaultAllocator.availableCount:I
        //   116: aload           7
        //   118: iload           4
        //   120: aload           6
        //   122: aastore        
        //   123: iload_2        
        //   124: iconst_1       
        //   125: iadd           
        //   126: istore_2       
        //   127: goto            53
        //   130: aload_0        
        //   131: aload_0        
        //   132: getfield        com/google/android/exoplayer/upstream/DefaultAllocator.allocatedCount:I
        //   135: aload_1        
        //   136: arraylength    
        //   137: isub           
        //   138: putfield        com/google/android/exoplayer/upstream/DefaultAllocator.allocatedCount:I
        //   141: aload_0        
        //   142: invokevirtual   java/lang/Object.notifyAll:()V
        //   145: aload_0        
        //   146: monitorexit    
        //   147: return         
        //   148: astore_1       
        //   149: aload_0        
        //   150: monitorexit    
        //   151: aload_1        
        //   152: athrow         
        //   153: iconst_1       
        //   154: istore          5
        //   156: goto            91
        //   159: iconst_0       
        //   160: istore          5
        //   162: goto            91
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  2      48     148    153    Any
        //  48     51     148    153    Any
        //  63     88     148    153    Any
        //  91     116    148    153    Any
        //  130    145    148    153    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.assembler.ir.StackMappingVisitor.push(StackMappingVisitor.java:290)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.execute(StackMappingVisitor.java:833)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:398)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2030)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public void trim(int i) {
        while (true) {
            final int n = 0;
            while (true) {
                final int max;
                Label_0183: {
                    synchronized (this) {
                        max = Math.max(0, Util.ceilDivide(i, this.individualAllocationSize) - this.allocatedCount);
                        i = this.availableCount;
                        if (max < i) {
                            if (this.initialAllocationBlock == null) {
                                break Label_0183;
                            }
                            final int n2 = this.availableCount - 1;
                            i = n;
                            int n3 = n2;
                            while (i <= n3) {
                                final Allocation allocation = this.availableAllocations[i];
                                if (allocation.data == this.initialAllocationBlock) {
                                    ++i;
                                }
                                else {
                                    final Allocation allocation2 = this.availableAllocations[n3];
                                    if (allocation2.data != this.initialAllocationBlock) {
                                        --n3;
                                    }
                                    else {
                                        this.availableAllocations[i] = allocation2;
                                        this.availableAllocations[n3] = allocation;
                                        --n3;
                                        ++i;
                                    }
                                }
                            }
                            i = Math.max(max, i);
                            if (i < this.availableCount) {
                                Arrays.fill(this.availableAllocations, i, this.availableCount, null);
                                this.availableCount = i;
                            }
                        }
                        return;
                    }
                }
                i = max;
                continue;
            }
        }
    }
}
