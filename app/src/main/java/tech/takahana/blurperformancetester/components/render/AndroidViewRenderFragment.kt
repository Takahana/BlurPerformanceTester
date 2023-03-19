package tech.takahana.blurperformancetester.components.render

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import tech.takahana.blurperformancetester.R
import tech.takahana.blurperformancetester.components.setting.SettingScreenUiState
import tech.takahana.blurperformancetester.components.setting.SettingViewModel
import tech.takahana.blurperformancetester.databinding.FragmentAndroidViewRenderBinding
import tech.takahana.blurperformancetester.domain.domainobject.AndroidViewImageLoader
import tech.takahana.blurperformancetester.domain.domainobject.ImageSize
import tech.takahana.blurperformancetester.domain.domainobject.RemoteImage

class AndroidViewRenderFragment : Fragment(R.layout.fragment_android_view_render) {

  private var _binding: FragmentAndroidViewRenderBinding? = null
  private val binding: FragmentAndroidViewRenderBinding get() = _binding!!

  private val settingViewModel: SettingViewModel by activityViewModels()

  private val renderState = MutableStateFlow(RenderState.Initialized)
  private val imageSize = MutableStateFlow(Pair(0, 0))

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    _binding = FragmentAndroidViewRenderBinding.bind(view)

    showScreenSize()

    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
        settingViewModel.uiState.onEach { uiState ->
          when (uiState) {
            SettingScreenUiState.Initialized,
            is SettingScreenUiState.Display.Compose -> Unit
            is SettingScreenUiState.Display.AndroidView -> {
              loadImage(uiState)
            }
          }
        }.launchIn(this)

        renderState.onEach { state ->
          showRenderState(state)
        }.launchIn(this)

        imageSize.onEach { (width, height) ->
          showImageSize(width, height)
        }.launchIn(this)
      }
    }
  }

  private fun showScreenSize() {
    val displayMetrics = requireContext().resources.displayMetrics
    val screenWidth = displayMetrics.widthPixels
    val screenHeight = displayMetrics.heightPixels

    binding.screenWidthText.text = "screenWidth: $screenWidth"
    binding.screenHeightText.text = "screenHeight: $screenHeight"
  }

  private fun showImageSize(width: Int, height: Int) {
    binding.widthText.text = width.toString()
    binding.heightText.text = height.toString()
  }

  private fun showRenderState(renderState: RenderState) {
    val text = when (renderState) {
      RenderState.Initialized -> null
      RenderState.Processing -> getString(R.string.processing)
      RenderState.Completed -> getString(R.string.complete)
    }
    binding.renderStateText.text = text
  }

  private fun loadImage(uiState: SettingScreenUiState.Display.AndroidView) {
    when (uiState.selectedImageLoader) {
      AndroidViewImageLoader.Glide -> loadImageWithGlide()
    }
  }

  private fun loadImageWithGlide() {
    val image = RemoteImage.W8256_H5504
    val density = requireContext().resources.displayMetrics.density
    val radius = (16 * density).toInt()

    renderState.value = RenderState.Processing
    Glide.with(this)
      .load(image)
      .transform(BlurTransformation(radius))
      .listener(object : RequestListener<Drawable> {
        override fun onLoadFailed(
          e: GlideException?,
          model: Any?,
          target: Target<Drawable>?,
          isFirstResource: Boolean
        ): Boolean {
          return false
        }

        override fun onResourceReady(
          resource: Drawable?,
          model: Any?,
          target: Target<Drawable>?,
          dataSource: DataSource?,
          isFirstResource: Boolean
        ): Boolean {
          val bitmapDrawable = resource as? BitmapDrawable
          if (bitmapDrawable != null) {
            imageSize.value = Pair(bitmapDrawable.bitmap.width, bitmapDrawable.bitmap.height)
            renderState.value = RenderState.Completed
          }
          return false
        }
      })
      .diskCacheStrategy(DiskCacheStrategy.ALL)
      .into(binding.imageView)
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  companion object {
    fun newInstance(): AndroidViewRenderFragment = AndroidViewRenderFragment()
  }
}