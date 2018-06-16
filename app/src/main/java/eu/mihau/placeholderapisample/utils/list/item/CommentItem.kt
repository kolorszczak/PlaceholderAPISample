package eu.mihau.placeholderapisample.utils.list.item

import android.support.v7.widget.RecyclerView
import android.view.View
import eu.mihau.placeholderapisample.R
import eu.mihau.placeholderapisample.databinding.CommentItemBinding
import eu.mihau.placeholderapisample.model.Comment

class CommentItem(val comment: Comment) : KAbstractItem<CommentItem, CommentItem.ViewHolder>(R.layout.comment_item, ::ViewHolder) {

    override fun bindView(holder: ViewHolder, payloads: List<Any>) {
        super.bindView(holder, payloads)
        holder.binding.comment = comment
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: CommentItemBinding = CommentItemBinding.bind(itemView)
    }

}