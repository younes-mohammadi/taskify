package ir.younesmohammadi.mvp_learn.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.younesmohammadi.mvp_learn.mvp.model.ModelTaskFragment
import ir.younesmohammadi.mvp_learn.mvp.presenter.PresenterTaskFragment
import ir.younesmohammadi.mvp_learn.mvp.view.ViewTaskFragment

class TaskFalseFragment : Fragment() {

    private lateinit var viewMain: ViewTaskFragment

    private lateinit var presenter: PresenterTaskFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewMain = ViewTaskFragment(requireContext(), false)
        return viewMain.binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = PresenterTaskFragment(viewMain, ModelTaskFragment(this))
        presenter.onCreate()

    }

}