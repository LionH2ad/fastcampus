package fastcampus.part1.chapter8

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fastcampus.part1.chapter8.databinding.ItemImageBinding
import fastcampus.part1.chapter8.databinding.ItemLoadMoreBinding

class ImageAdapter(private val itemClickListener: ItemClickListener) : ListAdapter<ImageItems, RecyclerView.ViewHolder>( // < item , ViewHolder Type>
    /**
     * DiffUtil은 ListAdapter에서 반드시 구현 해야 함
     * item 변화 확인 하여 notify, insert, delete 등을 알아서 실행
     * 1. 같은 데이터 인지 같은 값을 가지고 있는지 확인하는 것은 ==
     * 2. 같은 데이터를 참조하고 있는지 코틀린에서 참조값을 확인하려면 ===
     * */
    object : DiffUtil.ItemCallback<ImageItems>() {
        override fun areItemsTheSame(oldItem: ImageItems, newItem: ImageItems): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ImageItems, newItem: ImageItems): Boolean {
            return oldItem == newItem
        }
    }
){
    companion object {
        const val ITEM_IMAGE = 0
        const val ITEM_LOAD_MORE = 1
    }

    override fun getItemCount(): Int { // RecyclerView Footer(마직막에 들어가서)
        val originSize = currentList.size // 현재 가지고 있는 사이즈
        return if(originSize == 0) 0 else originSize.inc()
    }

    override fun getItemViewType(position: Int): Int { // 0일때는 고려하지 않음
        return if(itemCount.dec() == position) ITEM_LOAD_MORE else ITEM_IMAGE // itemCount는 getItemCount()에서 반환한 값
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return when(viewType) {
            ITEM_IMAGE -> {
                val binding = ItemImageBinding.inflate(inflater, parent, false)
                ImageViewHolder(binding)
            }
            else -> {
                val binding = ItemLoadMoreBinding.inflate(inflater, parent, false)
                LoadMoreViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        /*when(val item = currentList[position]){
            is ImageItems.Image -> TODO()
            ImageItems.LoadMore -> TODO()
        }*/ //sealed class 구현으로 인해 자동으로 추가가 가능한 부분
        when(holder){
            is ImageViewHolder -> {
                holder.bind(currentList[position] as ImageItems.Image)
            }
            is LoadMoreViewHolder -> {
                holder.bind(itemClickListener)
            }
        }
    }

    interface ItemClickListener {
        fun onLoadMoreClick()
    }
}


sealed class ImageItems { // Image, LoadMore 2개를 묶는 클래스
    data class Image(
        val uri: Uri, // uri 를 통해서 사진 그리기가 가능
    ) : ImageItems()
    object LoadMore : ImageItems() // 사진을 하나만 가지고 오려고 할 때 싱글톤 사용
}

class ImageViewHolder(private val binding: ItemImageBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ImageItems.Image) {
        binding.previewImageView.setImageURI(item.uri)
    }
}

class LoadMoreViewHolder(binding: ItemLoadMoreBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(itemClickListener: ImageAdapter.ItemClickListener) {
        itemView.setOnClickListener { itemClickListener.onLoadMoreClick() }
    }
}
