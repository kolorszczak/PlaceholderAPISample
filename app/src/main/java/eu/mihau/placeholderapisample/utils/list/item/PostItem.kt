package eu.mihau.placeholderapisample.utils.list.item

import android.support.v7.widget.RecyclerView
import android.view.View
import eu.mihau.placeholderapisample.R
import eu.mihau.placeholderapisample.databinding.PostItemBinding
import eu.mihau.placeholderapisample.model.Post

class PostItem(val post: Post) : KAbstractItem<PostItem, PostItem.ViewHolder>(R.layout.post_item, ::ViewHolder) {

    override fun bindView(holder: ViewHolder, payloads: List<Any>) {
        super.bindView(holder, payloads)
        holder.binding.post = post
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: PostItemBinding = PostItemBinding.bind(itemView)
        val title = binding.title
    }

}