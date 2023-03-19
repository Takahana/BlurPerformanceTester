package tech.takahana.blurperformancetester.components.fragment

import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

class FakeViewModelStoreOwner : ViewModelStoreOwner {

  override fun getViewModelStore(): ViewModelStore {
    return ViewModelStore()
  }
}