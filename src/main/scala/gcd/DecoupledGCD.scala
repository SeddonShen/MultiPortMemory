// See README.md for license details.

package gcd

import chisel3._
import chisel3.util.experimental.loadMemoryFromFile

class MultiWritePort extends Bundle {
  val wrEnable = Input(Bool())
  val wrAddr = Input(UInt(10.W))
  val wrData = Input(UInt(8.W))
}
class DecoupledGcd() extends Module {
  val io = IO(new Bundle {
      val rdAddr = Input(UInt(10.W))
      val rdData = Output(UInt(8.W))
      val wrVec = Vec(4, new MultiWritePort())
  })
  
  val mem = SyncReadMem(1024, UInt(8.W))
  loadMemoryFromFile(mem, "/Users/seddonshen/Coding/MultiPortMemory/data.hex")
  io.rdData := mem.read(io.rdAddr)
  
  for( i <- 0 to 3){
    when(io.wrVec(i).wrEnable) {
        mem.write(io.wrVec(i).wrAddr, io.wrVec(i).wrData)
    }
  }  

}
