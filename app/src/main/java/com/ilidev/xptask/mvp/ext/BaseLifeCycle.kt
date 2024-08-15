package com.ilidev.xptask.mvp.ext

interface BaseLifeCycle {
    fun onCreate()

    fun onDestroy(){}

    fun onStop(){}
}