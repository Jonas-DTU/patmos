package util

import chisel3._
import patmos._
import patmos.Constants._

class PRom(amount : Int, addrWidth : Int) extends Module{
    val io = IO(new PRomIO(addrWidth))
      
    val promEven = MemBlock(amount / 2, INSTR_WIDTH)
    promEven.io.wrEna := io.write.enEven
    promEven.io.wrAddr := io.write.addrEven
    promEven.io.wrData := io.write.dataEven

    val promOdd = MemBlock(amount / 2, INSTR_WIDTH)
    promOdd.io.wrEna := io.write.enOdd
    promOdd.io.wrAddr := io.write.addrOdd
    promOdd.io.wrData := io.write.dataOdd

    io.addressEven := promEven.io.rdAddr
    io.addressOdd := promOdd.io.rdAddr
    io.instructionEven := promEven.io.rdData
    io.instructionOdd := promOdd.io.rdData

}