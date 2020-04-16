package dev.skyit.utilities.others

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import dev.skyit.utilities.R
import dev.skyit.utilities.base.BaseDialog

class LoadingDialog : BaseDialog(R.layout.progress_dialog){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog?.window?.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }
}