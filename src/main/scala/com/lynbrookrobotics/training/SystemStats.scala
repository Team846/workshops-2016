package com.lynbrookrobotics.training

import java.lang.management.ManagementFactory

import com.sun.management.OperatingSystemMXBean

object SystemStats {
  private val osBean = ManagementFactory.getOperatingSystemMXBean.asInstanceOf[OperatingSystemMXBean]

  private var lastRequest = 0L
  private var lastLoad = 0D

  def currentCPUUsage = {
    if (System.currentTimeMillis() - lastRequest > 100) {
      lastRequest = System.currentTimeMillis()
      lastLoad = osBean.getSystemCpuLoad
    }

    lastLoad
  }
}
