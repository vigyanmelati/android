package id.maskology.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import id.maskology.R
import id.maskology.data.model.Category
import id.maskology.data.model.Product
import id.maskology.databinding.ItemCategoryBinding
import id.maskology.databinding.ItemSmallProductBinding
import id.maskology.utils.CurrencyFormatter

class ListCategoryAdapter :
    PagingDataAdapter<Category, ListCategoryAdapter.ListViewHolder>(DIFF_CALLBACK)
{
    override fun onBindViewHolder(holder: ListCategoryAdapter.ListViewHolder, position: Int) {
        val category = getItem(position)
        if (category != null){
            holder.bind(category)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListCategoryAdapter.ListViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    inner class ListViewHolder(
        private val binding: ItemCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(category: Category){
            binding.apply {
                tvCategory.text = category.name
            }
        }

    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Category> =
            object : DiffUtil.ItemCallback<Category>() {
                override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
                    return oldItem == newItem
                }
                override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
                    return oldItem.id == newItem.id
                }

            }
    }

}