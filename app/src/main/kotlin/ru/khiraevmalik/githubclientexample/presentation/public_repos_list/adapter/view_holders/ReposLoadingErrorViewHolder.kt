package ru.khiraevmalik.githubclientexample.presentation.public_repos_list.adapter.view_holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_github_repos_error_loading_more.view.item_loading_more_error_retry_button
import ru.khiraevmalik.githubclientexample.utils.rippleClick

class ReposLoadingErrorViewHolder(view: View, onRetryButtonClickListener: () -> Unit) : RecyclerView.ViewHolder(view) {
    init {
        view.item_loading_more_error_retry_button.rippleClick {
            onRetryButtonClickListener.invoke()
        }
    }
}
