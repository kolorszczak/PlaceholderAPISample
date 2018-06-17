package eu.mihau.placeholderapisample.view

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.view.View
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.IAdapter
import com.mikepenz.fastadapter.adapters.ModelAdapter
import eu.mihau.placeholderapisample.R
import eu.mihau.placeholderapisample.databinding.MainActivityBinding
import eu.mihau.placeholderapisample.model.Post
import eu.mihau.placeholderapisample.utils.list.item.PostItem
import eu.mihau.placeholderapisample.viewmodel.MainViewModel
import eu.mihau.placeholderapisample.viewmodel.MainViewModelEvent
import javax.inject.Inject


class MainActivity : BaseActivity() {

    private val TAG = MainActivity::class.java.simpleName

    @Inject
    lateinit var viewModel: MainViewModel

    private val modelAdapter = ModelAdapter<Post, PostItem>({ PostItem(it) })
    private val adapter: FastAdapter<PostItem> = FastAdapter.with(listOf(modelAdapter))

    private val binding by lazy { DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.main_activity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.adapter = adapter
        adapter.withOnClickListener(::listClick)
        viewModel.mainViewModelSubject.subscribe(::handleListEvent)
        viewModel.getPosts()
    }

    private fun listClick(v: View?, adapter: IAdapter<PostItem>, item: PostItem, position: Int): Boolean {
        when (binding.recyclerView.findViewHolderForAdapterPosition(position)) {
            is PostItem.ViewHolder -> {
                val p1 = Pair.create<View, String>((binding.recyclerView.findViewHolderForAdapterPosition(position) as PostItem.ViewHolder).title, "title")
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity, p1)
                startActivity(Intent(this, DescriptionActivity::class.java).putExtra("comment", item.post), options.toBundle())
            }
        }
        return true
    }

    private fun handleListEvent(event: MainViewModelEvent) {
        when (event.type) {
            MainViewModelEvent.Type.DATA_LOADED -> event.postList.let { modelAdapter.add(it) }
            MainViewModelEvent.Type.ERROR -> Snackbar.make(binding.container, event.error.message.toString(), Snackbar.LENGTH_SHORT).show()
        }
    }
}