package com.example.hw2_4_viewpager2.fragment.board


import androidx.navigation.fragment.findNavController
import com.example.hw2_4_viewpager2.R
import com.example.hw2_4_viewpager2.databinding.FragmentOnBoardBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class OnBoardFragment : BaseFragment<FragmentOnBoardBinding>(FragmentOnBoardBinding::inflate), BoardAdapter.Result {

    private val adapter: BoardAdapter by lazy {
        BoardAdapter(this)
    }

    override fun setupUI() {
        binding().pager.adapter = adapter
    }

    override fun setupObserver() {
        TabLayoutMediator(
            binding().tabLayout, binding().pager
        ) { tab: TabLayout.Tab, position: Int ->
            tab.setIcon(
                R.drawable.tab_selector
            )
        }.attach()
    }

    override fun onClick() {
        findNavController().navigate(R.id.noteFragment)
    }
}
