package id.maskology.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import id.maskology.R
import id.maskology.databinding.ItemLoadingBinding
import id.maskology.utils.NetworkCheck

class LoadingStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<LoadingStateAdapter.LoadingStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadingStateViewHolder {
        val binding = ItemLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadingStateViewHolder(binding, retry)
    }

    override fun onBindViewHolder(holder: LoadingStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    class LoadingStateViewHolder(private val binding: ItemLoadingBinding, retry: () -> Unit):
    RecyclerView.ViewHolder(binding.root){
        init {
            binding.btnRetry.setOnClickListener { retry.invoke() }
        }

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                val isConnect = NetworkCheck.connectionCheck(binding.root.context)
                if (isConnect) {
                    binding.tvErrorMessage.text = loadState.error.localizedMessage
                } else {
                    binding.tvErrorMessage.text = binding.root.context.resources.getString(R.string.no_connection_message)
                }

            }

            binding.apply {
                progresbar.isVisible = loadState is LoadState.Loading
                btnRetry.isVisible = loadState is LoadState.Error
                tvErrorMessage.isVisible = loadState is LoadState.Error
            }
        }
    }
}