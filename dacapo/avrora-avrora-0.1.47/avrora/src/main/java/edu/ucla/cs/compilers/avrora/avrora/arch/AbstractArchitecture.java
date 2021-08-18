/**
 * Copyright (c) 2005, Regents of the University of California
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 * Neither the name of the University of California, Los Angeles nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package edu.ucla.cs.compilers.avrora.avrora.arch;

/**
 * The <code>AbstractArchitecture</code> interface represents an instruction set
 * architecture generated by jIntGen. This interface allows tools to be written
 * that are independent of a particular instruction set architecture. This
 * interface has methods to get information about instructions, disassemblers,
 * and assemblers for this architecture.
 *
 * @author jIntGen
 */
public interface AbstractArchitecture
{

    /**
     * The <code>getDisassembler()</code> method returns an instance of the
     * appropriate disassembler for the architecture. The disassembler can be
     * used to decode binary instructions into <code>AbstractInstr</code>
     * instances of the appropriate type.
     * 
     * @return an instance of the <code>AbstractDisassembler</code> interface
     *         appropriate for this architecture
     */
    public AbstractDisassembler getDisassembler();


    /**
     * The <code>getAssembler()</code> method returns an instance of the
     * appropriate assembler for the architecture. The assembler can be used to
     * translate an instruction instance into its binary encoding.
     * 
     * @return an instance of the <code>AbstractAssembler</code> interface
     *         appropriate for this architecture
     */
    public AbstractAssembler getAssembler();


    /**
     * The <code>getParser()</code> method returns an instance of the
     * appropriate source-assembly parser for this architecture. The parser can
     * be used to convert text assembly (i.e. a source file) into instances of
     * the appropriate instruction class.
     * 
     * @return an instance of the <code>AbstractParser</code> interface
     *         appropriate for this architecture
     */
    public AbstractParser getParser();


    /**
     * The <code>newInstrArray()</code> method returns a new array for holding
     * references to instruction instances. Because of Java's covariant array
     * types, implementations of this method can allocate an array of their
     * appropriate architecture-specific instruction class implementation.
     * Stores into the array are automatically checked by the Java VM to ensure
     * that only instruction objects of the appropriate class are stored into
     * the array.
     * 
     * @param len
     *            the length of the array to allocation
     * @return a reference to a new array that can be used to store instructions
     */
    public AbstractInstr[] newInstrArray(int len);
}
