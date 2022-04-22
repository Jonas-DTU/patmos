package patmos

import Chisel._
import chisel3.Mem

class AsyncMemBlockIO(size : Int, width : Int) extends Bundle {
  val rdAddr = UInt(INPUT, log2Up(size))
  val rdData = UInt(OUTPUT, width)
  val wrAddr = UInt(INPUT, log2Up(size))
  val wrEna  = UInt(INPUT, 1)
  val wrData = UInt(INPUT, width)
}

class AsyncMemBlock(size : Int, width : Int) extends Module{
  val io = new AsyncMemBlockIO(size, width)

  val mem = Mem(size, UInt(width = width))

  // write
  when(io.wrEna === UInt(1)) {
      mem.write(io.wrAddr, io.wrData)
  }

  io.rdData := mem.read(io.rdAddr)
}
