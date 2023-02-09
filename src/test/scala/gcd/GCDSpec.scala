// See README.md for license details.

package gcd

import chisel3._
import chiseltest._
import org.scalatest.freespec.AnyFreeSpec
import chisel3.experimental.BundleLiterals._

/**
  * This is a trivial example of how to run this Specification
  * From within sbt use:
  * {{{
  * testOnly gcd.GcdDecoupledTester
  * }}}
  * From a terminal shell use:
  * {{{
  * sbt 'testOnly gcd.GcdDecoupledTester'
  * }}}
  */
class GCDSpec extends AnyFreeSpec with ChiselScalatestTester {

  "Gcd should ssd" in {
    test(new DecoupledGcd()) { dut =>
      dut.io.rdAddr.poke("b0000_0000_00".U)
      dut.clock.step(1)
      println(dut.io.rdData.peek())

      dut.io.rdAddr.poke("b0000_0000_01".U)
      dut.clock.step(1)
      println(dut.io.rdData.peek())

      dut.io.rdAddr.poke("b0000_0000_10".U)
      dut.clock.step(1)
      println(dut.io.rdData.peek())

      dut.io.rdAddr.poke("b0000_0000_11".U)
      dut.clock.step(1)
      println(dut.io.rdData.peek())


      dut.io.wrVec(0).wrAddr.poke("b0000_0000_00".U)
      dut.io.wrVec(0).wrEnable.poke(true.B)
      dut.io.wrVec(0).wrData.poke("hff".U)

      dut.io.wrVec(1).wrAddr.poke("b0000_0000_01".U)
      dut.io.wrVec(1).wrEnable.poke(true.B)
      dut.io.wrVec(1).wrData.poke("hfe".U)

      dut.io.wrVec(2).wrAddr.poke("b0000_0000_10".U)
      dut.io.wrVec(2).wrEnable.poke(true.B)
      dut.io.wrVec(2).wrData.poke("hfd".U)

      dut.io.wrVec(3).wrAddr.poke("b0000_0000_11".U)
      dut.io.wrVec(3).wrEnable.poke(true.B)
      dut.io.wrVec(3).wrData.poke("hfc".U)

      dut.clock.step(1)

      dut.io.rdAddr.poke("b0000_0000_00".U)
      dut.clock.step(1)
      println(dut.io.rdData.peek())
    
      dut.io.rdAddr.poke("b0000_0000_01".U)
      dut.clock.step(1)
      println(dut.io.rdData.peek())

      dut.io.rdAddr.poke("b0000_0000_10".U)
      dut.clock.step(1)
      println(dut.io.rdData.peek())

      dut.io.rdAddr.poke("b0000_0000_11".U)
      dut.clock.step(1)
      println(dut.io.rdData.peek())
    }
  }
}
