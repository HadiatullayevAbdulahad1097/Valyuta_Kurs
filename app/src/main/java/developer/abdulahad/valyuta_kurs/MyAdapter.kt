package developer.abdulahad.valyuta_kurs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import developer.abdulahad.valyuta_kurs.databinding.ItemRvBinding

class MyAdapter(
    private val list: List<User>,
    private val myClick: MyClick
) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemRvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(user: User, position: Int) {
            binding.foiz.text = user.Diff
            binding.text.text = user.CcyNm_UZ
            binding.card.setOnClickListener { myClick.onClick(user, position) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(hol: ViewHolder, pos: Int) = hol.onBind(list[pos], pos)
    override fun getItemCount(): Int = list.size
    interface MyClick {
        fun onClick(user: User, i: Int)
    }
}