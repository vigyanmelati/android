package id.maskology.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.maskology.data.model.Banner
import id.maskology.databinding.ItemBannerBinding

class BannerSlideAdapter(private val listBanner: List<Banner>) :  RecyclerView.Adapter<BannerSlideAdapter.BannerSlideViewHolder>(){

    inner class BannerSlideViewHolder(private val binding: ItemBannerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(banner: Banner){
            binding.apply {
                imgBanner.setImageResource(banner.image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerSlideViewHolder {
        val binding = ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BannerSlideViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BannerSlideViewHolder, position: Int) {
        holder.bind(listBanner[position])
    }

    override fun getItemCount(): Int = listBanner.size

}