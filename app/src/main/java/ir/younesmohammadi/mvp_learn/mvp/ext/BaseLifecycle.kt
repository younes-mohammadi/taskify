package ir.younesmohammadi.mvp_learn.mvp.ext

interface BaseLifecycle {

    fun onCreate()

    fun onStart(){}

    fun onResume(){}

    fun onStop(){}

    fun onDestroy(){}

}