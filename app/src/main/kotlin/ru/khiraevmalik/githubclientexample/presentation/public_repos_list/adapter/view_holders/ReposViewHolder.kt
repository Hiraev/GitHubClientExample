package ru.khiraevmalik.githubclientexample.presentation.public_repos_list.adapter.view_holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.khiraevmalik.githubclientexample.R
import ru.khiraevmalik.githubclientexample.domain_model.GitHubRepository

class ReposViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val logo: ImageView = view.findViewById(R.id.item_github_repo_logo)
    private val repoName: TextView = view.findViewById(R.id.item_github_repo_full_name)
    private val author: TextView = view.findViewById(R.id.item_github_repo_author)

    fun bind(gitHubRepository: GitHubRepository) {
        repoName.text = gitHubRepository.fullName
        author.text = gitHubRepository.owner.login
        Glide.with(itemView)
                .load(gitHubRepository.owner.avatar_url)
                .circleCrop()
                .into(logo)
    }

}