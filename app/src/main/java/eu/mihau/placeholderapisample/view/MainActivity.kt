package eu.mihau.placeholderapisample.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import eu.mihau.placeholderapisample.R
import eu.mihau.placeholderapisample.databinding.MainActivityBinding
import eu.mihau.placeholderapisample.viewmodel.MainViewModel

class MainActivity : BaseActivity() {

    private val TAG = MainActivity::class.java.simpleName

    private val binding by lazy { DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.main_activity) }
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        viewModel = MainViewModel()
        binding.viewModel = viewModel
    }
}