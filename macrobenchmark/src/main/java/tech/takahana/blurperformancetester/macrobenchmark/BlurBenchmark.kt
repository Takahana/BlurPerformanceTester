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
class BlurBenchmark {
  @get:Rule
  val benchmarkRule = MacrobenchmarkRule()

  private val iterations = 10

  @Ignore
  @Test
  fun startup() = benchmarkRule.measureRepeated(
    packageName = "tech.takahana.blurperformancetester",
    metrics = listOf(FrameTimingMetric()),
    iterations = iterations,
    startupMode = StartupMode.COLD
  ) {
    pressHome()
    startActivityAndWait()
  }

  @Test
  fun composeModifierBlur() = benchmarkRule.measureRepeated(
    packageName = "tech.takahana.blurperformancetester",
    metrics = listOf(FrameTimingMetric()),
    iterations = iterations,
    startupMode = StartupMode.COLD,
    setupBlock = {
      pressHome()
      startActivityAndWait()

      val composeButton = device.findObject(By.text("Compose"))
      composeButton.click()

      val modifierButton = device.findObject(By.text("Modifier"))
      modifierButton.click()
    }
  ) {
    val runButton = device.findObject(By.text("Run"))
    runButton.click()

    device.wait(Until.findObject(By.text("Complete")), 30.seconds.inWholeMilliseconds)
  }

  @Test
  fun composeGlideBlur() = benchmarkRule.measureRepeated(
    packageName = "tech.takahana.blurperformancetester",
    metrics = listOf(FrameTimingMetric()),
    iterations = iterations,
    startupMode = StartupMode.COLD,
    setupBlock = {
      pressHome()
      startActivityAndWait()

      val composeButton = device.findObject(By.text("Compose"))
      composeButton.click()

      val glideButton = device.findObject(By.text("Glide"))
      glideButton.click()
    }
  ) {
    val runButton = device.findObject(By.text("Run"))
    runButton.click()

    device.wait(Until.findObject(By.text("Complete")), 30.seconds.inWholeMilliseconds)
  }

  @Test
  fun androidViewGlideBlur() = benchmarkRule.measureRepeated(
    packageName = "tech.takahana.blurperformancetester",
    metrics = listOf(FrameTimingMetric()),
    iterations = iterations,
    startupMode = StartupMode.COLD,
    setupBlock = {
      pressHome()
      startActivityAndWait()

      val composeButton = device.findObject(By.text("AndroidView"))
      composeButton.click()

      val glideButton = device.findObject(By.text("Glide"))
      glideButton.click()
    }
  ) {
    val runButton = device.findObject(By.text("Run"))
    runButton.click()

    device.wait(Until.findObject(By.text("Complete")), 30.seconds.inWholeMilliseconds)
  }

  @Test
  fun androidViewBlurryBlur() = benchmarkRule.measureRepeated(
    packageName = "tech.takahana.blurperformancetester",
    metrics = listOf(FrameTimingMetric()),
    iterations = iterations,
    startupMode = StartupMode.COLD,
    setupBlock = {
      pressHome()
      startActivityAndWait()

      val composeButton = device.findObject(By.text("AndroidView"))
      composeButton.click()

      val glideButton = device.findObject(By.text("Blurry"))
      glideButton.click()
    }
  ) {
    val runButton = device.findObject(By.text("Run"))
    runButton.click()

    device.wait(Until.findObject(By.text("Complete")), 30.seconds.inWholeMilliseconds)
  }

  @Test
  fun androidViewRealtimeBlurViewBlur() = benchmarkRule.measureRepeated(
    packageName = "tech.takahana.blurperformancetester",
    metrics = listOf(FrameTimingMetric()),
    iterations = iterations,
    startupMode = StartupMode.COLD,
    setupBlock = {
      pressHome()
      startActivityAndWait()

      val composeButton = device.findObject(By.text("AndroidView"))
      composeButton.click()

      val glideButton = device.findObject(By.text("RealtimeBlurView"))
      glideButton.click()
    }
  ) {
    val runButton = device.findObject(By.text("Run"))
    runButton.click()

    device.wait(Until.findObject(By.text("Complete")), 30.seconds.inWholeMilliseconds)
  }

  @Test
  fun androidViewBlurKitBlur() = benchmarkRule.measureRepeated(
    packageName = "tech.takahana.blurperformancetester",
    metrics = listOf(FrameTimingMetric()),
    iterations = iterations,
    startupMode = StartupMode.COLD,
    setupBlock = {
      pressHome()
      startActivityAndWait()

      val composeButton = device.findObject(By.text("AndroidView"))
      composeButton.click()

      val glideButton = device.findObject(By.text("BlurKit"))
      glideButton.click()
    }
  ) {
    val runButton = device.findObject(By.text("Run"))
    runButton.click()

    device.wait(Until.findObject(By.text("Complete")), 30.seconds.inWholeMilliseconds)
  }

  @Test
  fun androidViewBlurViewBlur() = benchmarkRule.measureRepeated(
    packageName = "tech.takahana.blurperformancetester",
    metrics = listOf(FrameTimingMetric()),
    iterations = iterations,
    startupMode = StartupMode.COLD,
    setupBlock = {
      pressHome()
      startActivityAndWait()

      val composeButton = device.findObject(By.text("AndroidView"))
      composeButton.click()

      val glideButton = device.findObject(By.text("BlurView"))
      glideButton.click()
    }
  ) {
    val runButton = device.findObject(By.text("Run"))
    runButton.click()

    device.wait(Until.findObject(By.text("Complete")), 30.seconds.inWholeMilliseconds)
  }

  @Test
  fun androidViewRenderScriptIntrinsicsReplacementToolKitBlur() = benchmarkRule.measureRepeated(
    packageName = "tech.takahana.blurperformancetester",
    metrics = listOf(FrameTimingMetric()),
    iterations = iterations,
    startupMode = StartupMode.COLD,
    setupBlock = {
      pressHome()
      startActivityAndWait()

      val composeButton = device.findObject(By.text("AndroidView"))
      composeButton.click()

      val glideButton = device.findObject(By.text("RenderScriptIntrinsicsReplacementToolKit"))
      glideButton.click()
    }
  ) {
    val runButton = device.findObject(By.text("Run"))
    runButton.click()

    device.wait(Until.findObject(By.text("Complete")), 30.seconds.inWholeMilliseconds)
  }

  @Test
  fun androidViewRenderEffectBlur() = benchmarkRule.measureRepeated(
    packageName = "tech.takahana.blurperformancetester",
    metrics = listOf(FrameTimingMetric()),
    iterations = iterations,
    startupMode = StartupMode.COLD,
    setupBlock = {
      pressHome()
      startActivityAndWait()

      val composeButton = device.findObject(By.text("AndroidView"))
      composeButton.click()

      val glideButton = device.findObject(By.text("RenderEffect"))
      glideButton.click()
    }
  ) {
    val runButton = device.findObject(By.text("Run"))
    runButton.click()

    device.wait(Until.findObject(By.text("Complete")), 30.seconds.inWholeMilliseconds)
  }
}