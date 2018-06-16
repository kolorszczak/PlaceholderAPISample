package eu.mihau.placeholderapisample.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import eu.mihau.placeholderapisample.R
import eu.mihau.placeholderapisample.databinding.MainActivityBinding
import eu.mihau.placeholderapisample.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity : BaseActivity() {

    private val TAG = MainActivity::class.java.simpleName

    @Inject
    lateinit var viewModel: MainViewModel

    private val binding by lazy { DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.main_activity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
    }
}