package util

import chisel3._

import patmos._
import patmos.Constants._

class PromIO(addrWidth : Int) extends BlackBoxRomIO(addrWidth){
    val enEven = Input(Bool())
    val enOdd = Input(Bool())
    val writeDataEven = Input(UInt(INSTR_WIDTH.W))
    val writeDataOdd = Input(UInt(INSTR_WIDTH.W))
    val writeAddrEven = Input(UInt(INSTR_WIDTH.W))
    val writeAddrOdd = Input(UInt(INSTR_WIDTH.W))
}

class Prom(amount:Int, addrWidth : Int) extends Module{
    val io = IO(new PromIO(addrWidth))

    val promEven = MemBlock(amount / 2, INSTR_WIDTH)
    promEven.io.wrEna := io.enEven
    promEven.io.wrAddr := io.writeAddrEven
    promEven.io.wrData := io.writeDataEven
    promEven.io.rdAddr := io.addressEven
    io.instructionEven := promEven.io.rdData

    val promOdd = MemBlock(amount / 2, INSTR_WIDTH)
    promOdd.io.wrEna := io.enOdd
    promOdd.io.wrAddr := io.writeAddrOdd
    promOdd.io.wrData := io.writeDataOdd
    promOdd.io.rdAddr := io.addressOdd
    io.instructionOdd := promOdd.io.rdData
}