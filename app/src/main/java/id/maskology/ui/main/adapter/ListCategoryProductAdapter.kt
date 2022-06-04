package id.maskology.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.maskology.data.model.Category
import id.maskology.data.model.CategoryProduct
import id.maskology.data.model.FunFact
import id.maskology.databinding.ItemCategoryBinding

class ListCategoryProductAdapter(private val listCategoryProduct: List<CategoryProduct>) :
    ListAdapter<CategoryProduct, ListCategoryProductAdapter.ListViewHolder>(DIFF_CALLBACK)
{
    override fun onBindViewHolder(holder: ListCategoryProductAdapter.ListViewHolder, position: Int) {
        holder.bind(listCategoryProduct[position])
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListCategoryProductAdapter.ListViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    inner class ListViewHolder(
        private val binding: ItemCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(categoryProduct: CategoryProduct){
            binding.apply {
                tvCategory.text = categoryProduct.name
            }
        }

    }

    override fun getItemCount(): Int = listCategoryProduct.size

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<CategoryProduct> =
            object : DiffUtil.ItemCallback<CategoryProduct>() {
                override fun areItemsTheSame(oldItem: CategoryProduct, newItem: CategoryProduct): Boolean {
                    return oldItem == newItem
                }
                override fun areContentsTheSame(oldItem: CategoryProduct, newItem: CategoryProduct): Boolean {
                    return oldItem.id == newItem.id
                }

            }
    }

}