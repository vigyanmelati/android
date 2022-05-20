package id.maskology.ui.main.fragment

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import id.maskology.R
import id.maskology.databinding.FragmentHomeBinding
import id.maskology.data.model.Banner
import id.maskology.ui.main.adapter.BannerSlideAdapter

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var bannerSlideAdapter: BannerSlideAdapter
    private lateinit var sliderHandler: Handler
    private lateinit var sliderRun: Runnable
    private lateinit var reSliderRun: Runnable


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        setSliderBanner()
    }

    private fun setSliderBanner() {
        val listBanner = listOf(
            Banner(R.drawable.dummy_1),
            Banner(R.drawable.dummy_2),
            Banner(R.drawable.dummy_3)
        )

        binding.collapsedbar.headerHome.vpBannerSlider.apply {
            bannerSlideAdapter = BannerSlideAdapter(listBanner)
            sliderHandler = Handler()
            sliderRun = Runnable {
                currentItem += 1
                Log.d("JAYAK", "$currentItem")
            }
            reSliderRun = Runnable {
                currentItem = 0

            }
            adapter = bannerSlideAdapter
            setIndicatorBanner()
            setActiveBannerIndicator(0)
            registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    setActiveBannerIndicator(position)
                    sliderHandler.removeCallbacks(sliderRun)
                    sliderHandler.postDelayed(sliderRun, 5000)
                    if (position == listBanner.size - 1){
                        sliderHandler.postDelayed(reSliderRun, 5000)
                    }
                }
            })
        }

    }

    private fun setIndicatorBanner() {
        val indicator = arrayOfNulls<ImageView>(bannerSlideAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicator.indices){
            indicator[i] = ImageView(requireActivity().applicationContext)
            indicator[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireActivity().applicationContext,
                        R.drawable.ic_indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            binding.collapsedbar.headerHome.indicatorSlideLayout.addView(indicator[i])
        }
    }

    private fun setActiveBannerIndicator(index: Int) {
        val childCount = binding.collapsedbar.headerHome.indicatorSlideLayout.childCount
        for (i in 0 until childCount) {
            val imageView = binding.collapsedbar.headerHome.indicatorSlideLayout[i] as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireActivity().applicationContext,
                        R.drawable.ic_indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireActivity().applicationContext,
                        R.drawable.ic_indicator_inactive
                    )
                )
            }
        }
    }

    override fun onPause() {
        super.onPause()
        sliderHandler.removeCallbacks(sliderRun)
    }

    override fun onResume() {
        super.onResume()
        sliderHandler.postDelayed(sliderRun, 3000)
    }


}