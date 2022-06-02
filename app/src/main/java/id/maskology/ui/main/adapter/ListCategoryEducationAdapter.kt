package id.maskology.ui.main.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import id.maskology.R
import id.maskology.data.model.Category
import id.maskology.data.model.Product
import id.maskology.databinding.ItemCategoryBinding
import id.maskology.databinding.ItemMaskStoryBinding
import id.maskology.databinding.ItemSmallProductBinding
import id.maskology.ui.detailProduct.DetailProductActivity
import id.maskology.ui.maskStory.MaskStoryActivity
import id.maskology.utils.CurrencyFormatter

class ListCategoryEducationAdapter :
    PagingDataAdapter<Category, ListCategoryEducationAdapter.ListViewHolder>(DIFF_CALLBACK)
{
    override fun onBindViewHolder(holder: ListCategoryEducationAdapter.ListViewHolder, position: Int) {
        val category = getItem(position)
        if (category != null){
            holder.bind(category)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListCategoryEducationAdapter.ListViewHolder {
        val binding = ItemMaskStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    inner class ListViewHolder(
        private val binding: ItemMaskStoryBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(category: Category){
            binding.apply {
                Glide.with(itemView)
                    .load(category.imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.drawable.ic_baseline_image)
                    .error(R.drawable.ic_baseline_broken_image)
                    .into(imgMask)
                tvMaskName.text = category.name
                tvMaskStory.text = category.detail

                root.setOnClickListener {
                    val intent = Intent(itemView.context, MaskStoryActivity::class.java)
                    intent.putExtra("category", category)

                    val optionsCompat: ActivityOptionsCompat =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                            itemView.context as Activity,
                            Pair(imgMask, "img_mask")
                        )
                    itemView.context.startActivity(intent, optionsCompat.toBundle())
                }
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