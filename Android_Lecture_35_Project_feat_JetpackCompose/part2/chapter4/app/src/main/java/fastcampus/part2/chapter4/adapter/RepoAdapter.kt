package fastcampus.part2.chapter4.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fastcampus.part2.chapter4.databinding.ItemRepoBinding
import fastcampus.part2.chapter4.model.Repo

class RepoAdapter(private val onClick: (Repo) -> Unit) :
    ListAdapter<Repo, RepoAdapter.ViewHolder>(diffUtil) {
    inner class ViewHolder(private val viewBinding: ItemRepoBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(item: Repo) { // Repo Item 을 해당 text 에 연결 시키는 과정
            viewBinding.repoNameTextView.text = item.name
            viewBinding.descriptionTextView.text = item.description
            viewBinding.starCountTextView.text = item.starCount.toString()
            viewBinding.forkCountTextView.text = "${item.forkCount}"

            viewBinding.root.setOnClickListener {
                onClick(item)
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoAdapter.ViewHolder {
        return ViewHolder(
            ItemRepoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: RepoAdapter.ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Repo>(){
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem == newItem
            }

        }
    }
}