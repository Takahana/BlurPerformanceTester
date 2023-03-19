package tech.takahana.blurperformancetester

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import io.alterac.blurkit.BlurKit
import tech.takahana.blurperformancetester.domain.domainobject.ImageSizeBytes

class App : Application(), ImageLoaderFactory {

  override fun onCreate() {
    super.onCreate()
    BlurKit.init(this)
  }

  override fun newImageLoader(): ImageLoader {
    return ImageLoader.Builder(this)
      .memoryCache {
        MemoryCache.Builder(this)
          .maxSizeBytes(ImageSizeBytes.`50MB`)
          .build()
      }
      .diskCache(
        DiskCache.Builder()
          .directory(cacheDir.resolve("image_cache"))
          .maxSizeBytes(ImageSizeBytes.`50MB`.toLong())
          .build()
      )
      .build()
  }
}