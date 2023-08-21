package fastcampus.part1.chapter8

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fastcampus.part1.chapter8.databinding.ItemFrameBinding

/**
 * Adapter의 사전적 의미는 다른 전기나 기계 장치를 서로 연결해서 작동할 수 있도록 만들어 주는 결합 도구이며
 * 여기서 연결을 하기 위한 것은 데이터와 RecyclerView 사이를 연결해주기 위한 객체로 존재하는 것이다
 * */
class FrameAdapter(private val list : List<FrameItem>) : RecyclerView.Adapter<FrameViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FrameViewHolder {
        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = ItemFrameBinding.inflate(inflater, parent, false)
        return FrameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FrameViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}

data class FrameItem(
    val uri: Uri
)

/**
 * ItemFrameBinding을 사용하기 위해 private val 을 넣어준다
 * ItemFrameBinding = item_frame.xml 에 있는 ImageView
 * ImageView 는 화면을 꽉차게 보여주는 layout
 * ViewHolder란 화면에 표시될 데이터나 아이템들을 저장하는 역할
 * */
class FrameViewHolder(private val binding: ItemFrameBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: FrameItem) {
        binding.frameImageView.setImageURI(item.uri) //원하는 데이터를 받아서 뷰와 데이터를 연결 해주는 과정
    }
}