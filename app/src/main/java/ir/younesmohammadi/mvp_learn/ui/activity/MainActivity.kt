package ir.younesmohammadi.mvp_learn.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ir.younesmohammadi.mvp_learn.mvp.model.ModelMainActivity
import ir.younesmohammadi.mvp_learn.mvp.presenter.PresenterMainActivity
import ir.younesmohammadi.mvp_learn.mvp.view.ViewMainActivity

class MainActivity : AppCompatActivity() {

    private lateinit var presenter: PresenterMainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ViewMainActivity(this)
        setContentView(view.binding.root)

        presenter = PresenterMainActivity(view, ModelMainActivity(this))
        presenter.onCreate()
        presenter.initTabLayout(supportFragmentManager, lifecycle)

    }

}