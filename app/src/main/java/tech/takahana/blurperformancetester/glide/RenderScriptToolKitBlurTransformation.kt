package tech.takahana.blurperformancetester.glide

import android.graphics.Bitmap
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.google.android.renderscript.Toolkit
import java.security.MessageDigest


class RenderScriptToolKitBlurTransformation(
    private val radius: Int,
) : BitmapTransformation() {

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update(TAG.toByteArray(CHARSET))
    }

    override fun transform(
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap {
        return Toolkit.blur(toTransform, radius)
    }

    companion object {
        const val TAG = "RenderScriptToolKitBlurTransformation"
    }
}