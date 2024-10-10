package ir.younesmohammadi.mvp_learn.adapter.tabLayout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import ir.younesmohammadi.mvp_learn.ui.fragment.TaskFalseFragment
import ir.younesmohammadi.mvp_learn.ui.fragment.TaskTrueFragment

class TabLayoutAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
): FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> TaskFalseFragment()
            else -> TaskTrueFragment()
        }
    }
}