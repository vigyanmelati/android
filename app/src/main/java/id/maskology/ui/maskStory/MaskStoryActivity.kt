package id.maskology.ui.maskStory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import id.maskology.R
import id.maskology.data.model.Category
import id.maskology.data.model.Product
import id.maskology.databinding.ActivityDetailProductBinding
import id.maskology.databinding.ActivityMaskStoryBinding
import id.maskology.ui.LoadingStateAdapter
import id.maskology.ui.ViewModelFactory
import id.maskology.ui.main.adapter.ListNewProductAdapter
import id.maskology.ui.maskStory.adapter.ListRelatedProductAdapter
import id.maskology.ui.maskStory.viewmodel.MaskStoryViewModel
import id.maskology.utils.LocalDateConverter

class MaskStoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMaskStoryBinding
    private lateinit var category: Category
    private lateinit var maskStoryViewModel: MaskStoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMaskStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViewModel()
        getDataCategory()
        setView()
        setToolbar()
        setListRelatedProduct()
    }

    private fun setListRelatedProduct() {
        val listAdapter = ListRelatedProductAdapter()
        binding.descriptionStoryMaskLayout.rvRelatedProduct.apply {
            layoutManager = LinearLayoutManager(this@MaskStoryActivity, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = listAdapter.withLoadStateFooter(
                footer = LoadingStateAdapter {
                    listAdapter.retry()
                }
            )
        }

        binding.descriptionStoryMaskLayout.rvRelatedProduct.adapter = listAdapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                listAdapter.retry()
            }
        )

        maskStoryViewModel.listProduct.observe(this@MaskStoryActivity){ listRelatedProduct ->
            listAdapter.submitData(lifecycle, listRelatedProduct)
        }
    }

    private fun setViewModel() {
        val factory = ViewModelFactory.getInstance(this@MaskStoryActivity.application)
        maskStoryViewModel = ViewModelProvider(this@MaskStoryActivity, factory)[MaskStoryViewModel::class.java]
    }

    private fun setToolbar() {
        setSupportActionBar(binding.collapsebar.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.collapsebar.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun setView() {
        Glide.with(applicationContext)
            .load(category.imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.drawable.ic_baseline_image)
            .error(R.drawable.ic_baseline_broken_image)
            .into(binding.collapsebar.headerStoryMask.imgMask)
        binding.collapsebar.collapsebar.title = category.name
        binding.descriptionStoryMaskLayout.tvWriterName.text = category.author
        binding.descriptionStoryMaskLayout.tvDescription.text = category.detail
        binding.descriptionStoryMaskLayout.tvYear.text = LocalDateConverter.convertLocalDate(category.updatedAt)
    }

    private fun getDataCategory() {
        category = intent.getParcelableExtra<Category>("category") as Category
//        category = intent.getStringExtra("category") as Category
    }
}