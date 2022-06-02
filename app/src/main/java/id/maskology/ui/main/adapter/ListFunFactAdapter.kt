package id.maskology.ui.main.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import id.maskology.R
import id.maskology.data.model.Category
import id.maskology.data.model.FunFact
import id.maskology.data.model.Product
import id.maskology.databinding.ItemCategoryBinding
import id.maskology.databinding.ItemMaskStoryBinding
import id.maskology.databinding.ItemNewsBinding
import id.maskology.databinding.ItemSmallProductBinding
import id.maskology.ui.detailProduct.DetailProductActivity
import id.maskology.ui.maskStory.MaskStoryActivity
import id.maskology.utils.CurrencyFormatter

class ListFunFactAdapter(private val listFunFact: List<FunFact>) :
    ListAdapter<FunFact, ListFunFactAdapter.ListViewHolder>(DIFF_CALLBACK)
{
    override fun onBindViewHolder(holder: ListFunFactAdapter.ListViewHolder, position: Int) {
        holder.bind(listFunFact[position])
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListFunFactAdapter.ListViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    inner class ListViewHolder(
        private val binding: ItemNewsBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(funFact: FunFact){
            binding.apply {
                imgNews.setImageResource(funFact.image)
                tvNewsTitle.text = funFact.title
                tvNewsContent.text = funFact.desc

                root.setOnClickListener {
                    Toast.makeText(itemView.context, "This feature is still under development", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun getItemCount(): Int = listFunFact.size

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<FunFact> =
            object : DiffUtil.ItemCallback<FunFact>() {
                override fun areItemsTheSame(oldItem: FunFact, newItem: FunFact): Boolean {
                    return oldItem == newItem
                }
                override fun areContentsTheSame(oldItem: FunFact, newItem: FunFact): Boolean {
                    return oldItem.title == newItem.title
                }

            }
    }

}