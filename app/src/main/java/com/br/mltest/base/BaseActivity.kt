package com.br.mltest.base

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

open class BaseActivity : AppCompatActivity() {

    protected fun setupToolbar(toolbar: Toolbar, titleIdRes: Int, titleString: String? = null, showBackButton: Boolean = false) {
        if (titleString.isNullOrEmpty())
            toolbar.title = getString(titleIdRes)
        else
            toolbar.title = titleString

        setSupportActionBar(toolbar)
        if(showBackButton) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
    }
}