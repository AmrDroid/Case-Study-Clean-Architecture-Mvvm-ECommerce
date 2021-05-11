package com.amrmustafa.adidas.activities
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.amrmustafa.adidas.utils.NetworkUtil

internal open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    //Observe Network Change
    protected fun onNetworkChange(block: (Boolean) -> Unit) {
        NetworkUtil.getNetworkStatus(this)
            .observe(this, Observer { isConnected ->
                block(isConnected)
            })
    }
}