package tech.takahana.blurperformancetester.macrobenchmark

import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.time.Duration.Companion.seconds

/**
 * This is an example startup benchmark.
 *
 * It navigates to the device's home screen, and launches the default activity.
 *
 * Before running this benchmark:
 * 1) switch your app's active build variant in the Studio (affects Studio runs only)
 * 2) add `<profileable android:shell="true" />` to your app's manifest, within the `<application>` tag
 *
 * Run this benchmark from Studio to see startup measurements, and captured system traces
 * for investigating your app's performance.
 */
@RunWith(AndroidJUnit4::class)
class ExampleStartupBenchmark {
  @get:Rule
  val benchmarkRule = MacrobenchmarkRule()

  @Ignore
  @Test
  fun startup() = benchmarkRule.measureRepeated(
    packageName = "tech.takahana.blurperformancetester",
    metrics = listOf(FrameTimingMetric()),
    iterations = 5,
    startupMode = StartupMode.COLD
  ) {
    pressHome()
    startActivityAndWait()
  }

  @Test
  fun blur() = benchmarkRule.measureRepeated(
    packageName = "tech.takahana.blurperformancetester",
    metrics = listOf(FrameTimingMetric()),
    iterations = 5,
    startupMode = StartupMode.COLD,
    setupBlock = {
      pressHome()
      startActivityAndWait()

      val runButton = device.findObject(By.text("Run"))
      runButton.click()
    }
  ) {
    device.wait(Until.findObject(By.text("Complete")), 30.seconds.inWholeMilliseconds)
  }
}