package ir.younesmohammadi.mvp_learn.mvp.view

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import com.google.android.material.tabs.TabLayoutMediator
import ir.younesmohammadi.mvp_learn.adapter.tabLayout.TabLayoutAdapter
import ir.younesmohammadi.mvp_learn.androidWrapper.ColorImportance
import ir.younesmohammadi.mvp_learn.androidWrapper.DialogUtils
import ir.younesmohammadi.mvp_learn.androidWrapper.PersianDate
import ir.younesmohammadi.mvp_learn.androidWrapper.SnackBarUtils
import ir.younesmohammadi.mvp_learn.databinding.ActivityMainBinding
import ir.younesmohammadi.mvp_learn.databinding.CustomDialogBinding
import ir.younesmohammadi.mvp_learn.databinding.CustomDialogDevBinding
import ir.younesmohammadi.mvp_learn.db.model.TaskEntity
import ir.younesmohammadi.mvp_learn.mvp.ext.OnBinData

class ViewMainActivity(
    contextInstance: Context
) : FrameLayout(contextInstance) {

    val binding = ActivityMainBinding.inflate(LayoutInflater.from(context))

    fun showDialogAddTask(onBinData: OnBinData) {

        binding.fabAdd.setOnClickListener {

            val view = CustomDialogBinding.inflate(LayoutInflater.from(context))

            DialogUtils.show(context, view.root, false)

            view.btnCancel.setOnClickListener { DialogUtils.dismiss() }

            view.btnSave.setOnClickListener {
                val task = view.edtTask.text.toString().trim()
                val viewError = view.layoutTask

                // Prioritization Task
                val selectedPriority = when (view.radioGroup.checkedRadioButtonId) {
                    view.radioLow.id -> ColorImportance.Low
                    view.radioMedium.id -> ColorImportance.Normal
                    view.radioHigh.id -> ColorImportance.High
                    else -> ColorImportance.Low
                }


                // save data
                if (task.isNotBlank() && selectedPriority.isNotEmpty()) {
                    onBinData.saveData(
                        TaskEntity(
                            id = 0,
                            title = task,
                            state = false,
                            date = PersianDate.getDate(),
                            importance = selectedPriority
                        )
                    )
                    SnackBarUtils.show(binding.root, "وظیفه با موفقیت ذخیره شد.")
                    DialogUtils.dismiss()
                } else {
                    viewError.error = "لطفا وظیفه را وارد کنید."
                }

                 view.edtTask.addTextChangedListener {
                     viewError.error = null
                 }

            }

        }

    }

    fun setTabLayout(fragmentManager: FragmentManager, lifecycle: Lifecycle) {

        val tabTitle = arrayOf("وظایف انجام نشده", "وظایف انجام شده")

        binding.viewPager.adapter = TabLayoutAdapter(
            fragmentManager = fragmentManager,
            lifecycle = lifecycle
        )

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()

    }

    fun showDialogDev() {

        binding.imgDev.setOnClickListener {
            val view = CustomDialogDevBinding.inflate(LayoutInflater.from(context))

            DialogUtils.show(context, view.root, true)

            view.btnId.setOnClickListener {
                copyToClipboard()
                DialogUtils.dismiss()
            }
        }

    }

    // copy to clipboard
    private fun copyToClipboard() {
        val clipboard = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("آیدی تلگرام", "@younesmohammadi_info")
        clipboard.setPrimaryClip(clip)

        // نمایش پیغام موفقیت
        SnackBarUtils.show(binding.root, "آیدی تلگرام در کلیپ بورد کپی شد")
    }


}