package com.amrmustafa.adidas.utils
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amrmustafa.adidas.R

internal fun String.enforceHttps(): String =
    if (!this.contains("https"))
        this.replace("http", "https")
    else this



internal fun Context.loadColor(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(this, colorRes)
}

internal inline fun <reified T : Activity> Context.startActivity(block: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    block(intent)
    startActivity(intent)
}



internal fun Activity.showSnackbar(view: View, message: String, isError: Boolean = false) {
    val sb = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
    if (isError)
        sb.setBackgroundTint(loadColor(R.color.colorError))
            .setTextColor(loadColor(R.color.colorOnError))
            .show()
    else
        sb.setBackgroundTint(loadColor(R.color.colorSecondary))
            .setTextColor(loadColor(R.color.colorOnSecondary))
            .show()

}



internal fun View.show() {
    this.visibility = View.VISIBLE
}

internal fun View.hide() {
    this.visibility = View.INVISIBLE
}

internal fun View.remove() {
    this.visibility = View.GONE
}




internal fun RecyclerView.recyclerViewLineDecoration(context: Context) {
    val linearLayoutManager = LinearLayoutManager(context)
    val itemDecoration = DividerItemDecoration(context, linearLayoutManager.orientation).apply {
        setDrawable(context.getDrawable(R.drawable.line_divider)!!)
    }
    layoutManager = linearLayoutManager
    addItemDecoration(itemDecoration)
}
