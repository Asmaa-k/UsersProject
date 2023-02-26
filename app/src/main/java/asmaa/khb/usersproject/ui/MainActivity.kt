package asmaa.khb.usersproject.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import asmaa.khb.usersproject.databinding.ActivityMainBinding
import asmaa.khb.usersproject.ui.viewmodel.UserViewModel
import com.google.android.material.snackbar.Snackbar
import com.haizo.generaladapter.listadapter.BlenderListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: UserViewModel by viewModels()
    private val userAdapter: BlenderListAdapter by lazy { BlenderListAdapter(context = this) }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        setupObservable()
        viewModel.getUsersList()
    }

    private fun setupRecyclerView() {
        if (::binding.isInitialized) binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter
        }
    }

    private fun setupObservable() {
        observeUserList()
        observeLoadingList()
        observeErrorList()
    }

    private fun observeUserList() = lifecycleScope.launch {
        viewModel.userList.collect {
            userAdapter.submitList(it)
        }
    }

    private fun observeLoadingList() = lifecycleScope.launch {
        viewModel.loading.collect {
            binding.progressBar.isVisible = it
        }
    }

    private fun observeErrorList() = lifecycleScope.launch {
        viewModel.error.collect {
            Snackbar.make(binding.container, it?.message.toString(), Snackbar.LENGTH_LONG).show()
        }
    }
}