package com.achmadrizkin.achmadrizki_suitmedia.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.achmadrizkin.achmadrizki_suitmedia.R
import com.achmadrizkin.achmadrizki_suitmedia.adapter.RecyclerViewUserAdapter
import com.achmadrizkin.achmadrizki_suitmedia.data.model.UserResponse
import com.achmadrizkin.achmadrizki_suitmedia.utils.DataState
import com.achmadrizkin.achmadrizki_suitmedia.view_model.ThirdScreenViewModel
import com.achmadrizkin.achmadrizki_suitmedia.view_model.UserStateEvent
import dagger.hilt.android.AndroidEntryPoint
import java.io.UnsupportedEncodingException

@AndroidEntryPoint
class ThirdScreenActivity : AppCompatActivity() {
    private lateinit var ivBack: ImageView
    private lateinit var pbLoading: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewUserAdapter: RecyclerViewUserAdapter
    private lateinit var thirdScreenViewModel: ThirdScreenViewModel

    private var page: Int = 1
    private var isFirstTimeCall = true
    private var isDataStillAvaible: Boolean = true
    private val listUserResponse: MutableList<UserResponse> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_screen)

        init()

        ivBack.setOnClickListener {
            finish()
        }

        swipeRefreshLayout.setOnRefreshListener {
            getAllDataFromAPI()
            swipeRefreshLayout.isRefreshing = false;
        }
    }

    private fun init() {
        ivBack = findViewById(R.id.ivBack)
        pbLoading = findViewById(R.id.pbLoading)
        recyclerView = findViewById(R.id.recyclerView)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        thirdScreenViewModel = ViewModelProvider(this)[ThirdScreenViewModel::class.java]

        initRecyclerViewUserApiCall()

        getAllDataFromAPI()
        subscribeAllUserObserversFromAPI()
    }

    private fun getAllDataFromAPI() {
        //
        thirdScreenViewModel.setStateEventGetAllUserFromApiCall(UserStateEvent.GetAllUserEvents, page)
    }

    private fun subscribeAllUserObserversFromAPI() {
        thirdScreenViewModel.getAllUserFromAPI.observe(this, Observer { it ->

            when (it) {
                is DataState.Success<List<UserResponse>> -> {
                    displayProgressBar(false)
                    appendBlogTitle(it.data)
                }

                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(it.exception.message.toString())
                }

                is DataState.Loading -> {
                    displayProgressBar(true)
                }

                else -> {

                }
            }
        })
    }

    private fun setAdapterClickListener() {
        recyclerViewUserAdapter.setItemClickListener(object :
            RecyclerViewUserAdapter.ItemClickListener {
            override fun onClick(view: View?, listUserResponse2: List<UserResponse>, position: Int) {
                val i = Intent()
                i.putExtra("getName", listUserResponse2[position].first_name)
                setResult(RESULT_OK, i)
                finish()
            }
        })
    }

    private fun setupScrollableRecyclerView(horizontalLayoutManager: LinearLayoutManager) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (isFirstTimeCall && isDataStillAvaible) {
                        isFirstTimeCall = false

                        // Do your stuff here...
                        val visibleItemCount: Int = horizontalLayoutManager.childCount
                        val pastVisibleItem: Int = horizontalLayoutManager.findFirstVisibleItemPosition()
                        val total: Int = recyclerViewUserAdapter.itemCount

                        if (visibleItemCount + pastVisibleItem >= total) {
                            try {
                                getAllDataFromAPI()
                            } catch (e: UnsupportedEncodingException) {
                                e.printStackTrace()
                            }
                        }
                    }
                }

                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    isFirstTimeCall = true
                }
            }
        })
    }

    private fun initRecyclerViewUserApiCall() {
        recyclerViewUserAdapter = RecyclerViewUserAdapter()
        setAdapterClickListener()

        //
        val horizontalLayoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = horizontalLayoutManager
        recyclerView.adapter = recyclerViewUserAdapter

        setupScrollableRecyclerView(horizontalLayoutManager)
    }

    private fun displayError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        pbLoading.visibility = if (isDisplayed) View.VISIBLE else View.GONE
        recyclerView.visibility = if (isDisplayed) View.GONE else View.VISIBLE
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun appendBlogTitle(listUser: List<UserResponse>) {
        if (listUser.isNotEmpty() && listUser != null) {
            page++

            recyclerViewUserAdapter.setListDataItems(thirdScreenViewModel.addDataToList(listUserResponse, listUser))
            recyclerViewUserAdapter.notifyDataSetChanged()
        } else {
            isDataStillAvaible = false
            Toast.makeText(this, "This is last data", Toast.LENGTH_LONG).show()
        }
    }
}