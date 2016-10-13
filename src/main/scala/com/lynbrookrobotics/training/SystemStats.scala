package com.lynbrookrobotics.training

import java.lang.management.ManagementFactory

import com.sun.management.OperatingSystemMXBean

object SystemStats {
  private val osBean = ManagementFactory.getOperatingSystemMXBean.asInstanceOf[OperatingSystemMXBean]

  def currentCPUUsage = osBean.getSystemCpuLoad
}
