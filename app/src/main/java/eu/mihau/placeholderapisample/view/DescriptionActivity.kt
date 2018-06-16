package eu.mihau.placeholderapisample.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ModelAdapter
import eu.mihau.placeholderapisample.R
import eu.mihau.placeholderapisample.databinding.DescriptionActivityBinding
import eu.mihau.placeholderapisample.model.Comment
import eu.mihau.placeholderapisample.model.Post
import eu.mihau.placeholderapisample.utils.list.item.CommentItem
import eu.mihau.placeholderapisample.viewmodel.DescriptionViewModel
import eu.mihau.placeholderapisample.viewmodel.DescriptionViewModelEvent
import javax.inject.Inject

class DescriptionActivity : BaseActivity() {

    private val TAG = DescriptionActivity::class.java.simpleName

    @Inject
    lateinit var descriptionViewModel: DescriptionViewModel

    private val post: Post by lazy { intent.extras.getParcelable<Post>("comment") }

    private val modelAdapter = ModelAdapter<Comment, CommentItem>({ CommentItem(it) })
    private val adapter: FastAdapter<CommentItem> = FastAdapter.with(listOf(modelAdapter))

    val binding by lazy { DataBindingUtil.setContentView<DescriptionActivityBinding>(this, R.layout.description_activity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        descriptionViewModel.post.set(post)
        binding.viewModel = descriptionViewModel
        binding.adapter = adapter
        descriptionViewModel.descriptionViewModelSubject.subscribe(::handleEvent)
        descriptionViewModel.getUser()
        descriptionViewModel.getComments()
    }

    fun handleEvent(event: DescriptionViewModelEvent) {
        when (event.type) {
            DescriptionViewModelEvent.Type.COMMENTS_LOADED -> {
                modelAdapter.add(descriptionViewModel.comments)
            }
            DescriptionViewModelEvent.Type.ERROR -> Snackbar.make(binding.container, event.error.message.toString(), Snackbar.LENGTH_SHORT).show()
        }
    }
}