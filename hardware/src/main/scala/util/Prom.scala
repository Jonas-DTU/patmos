package util

import chisel3._
import patmos._
import patmos.Constants._

class PRom(amount : Int, addrWidth : Int) extends Module{
    val io = IO(new PRomIO(addrWidth))
      
    val promEven = Module(new AsyncMemBlock(amount / 2, INSTR_WIDTH))
    promEven.io.wrEna := io.write.enEven
    promEven.io.wrAddr := io.write.addrEven
    promEven.io.wrData := io.write.dataEven

    val promOdd = Module(new AsyncMemBlock(amount / 2, INSTR_WIDTH))
    promOdd.io.wrEna := io.write.enOdd
    promOdd.io.wrAddr := io.write.addrOdd
    promOdd.io.wrData := io.write.dataOdd

    promEven.io.rdAddr := io.addressEven
    promOdd.io.rdAddr := io.addressOdd
    io.instructionEven := promEven.io.rdData
    io.instructionOdd := promOdd.io.rdData

}