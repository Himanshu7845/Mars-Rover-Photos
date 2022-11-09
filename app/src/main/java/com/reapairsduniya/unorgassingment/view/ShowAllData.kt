package com.reapairsduniya.unorgassingment.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView.OnScrollListener
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymvvm.constants.API_KEY
import com.google.gson.Gson
import com.reapairsduniya.admin.MyViewModel.MyViewModel
import com.reapairsduniya.admin.ResultWrapper.ResultWrappers
import com.reapairsduniya.unorgassingment.R
import com.reapairsduniya.unorgassingment.adapter.MyAdapter
import com.reapairsduniya.unorgassingment.databinding.ActivityShowAllDataBinding
import com.reapairsduniya.unorgassingment.loader.Loader
import com.reapairsduniya.unorgassingment.model.alldatamodel.AllDataModel
import com.reapairsduniya.unorgassingment.model.alldatamodel.MyCamera
import com.reapairsduniya.unorgassingment.model.alldatamodel.MyRover
import com.reapairsduniya.unorgassingment.util.showToast
import kotlinx.coroutines.launch


class ShowAllData : AppCompatActivity(),MyAdapter.I1 {
    lateinit var binding: ActivityShowAllDataBinding
    lateinit var  myAdapter: MyAdapter
    lateinit var  MyAd: MyAdapter
    var isScrolling:Boolean=false
    var currentItem=0
    var scrollOutItem=0
    var totalItem=0
    val list=ArrayList<AllDataModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_all_data)

        binding.shimmer.startShimmer()
        hitRoverDataApi(CommonView.solValue!!)
    }

    private fun hitRoverDataApi(solValue: String) {
        val myViewModel = MyViewModel()
        lifecycleScope.launch {
            myViewModel.getRoverData(solValue, API_KEY, Home.roverName)
            myViewModel.liveRoverData.observe(this@ShowAllData, Observer {
                when (it) {
                    is ResultWrappers.Error -> {
                        binding.empty.visibility=View.GONE
                        binding.shimmer.stopShimmer()
                        binding.shimmer.visibility=View.GONE
                        binding.recyclerView.visibility=View.VISIBLE
                      //  Loader.hideLoading()
                        showToast("Error")
                    }
                    is ResultWrappers.Loading -> {
                        binding.shimmer.startShimmer()
                        binding.shimmer.stopShimmer()
                        binding.empty.visibility=View.GONE
                      //  Loader.showLoading(this@ShowAllData)

                    }
                    is ResultWrappers.Success -> {
                        binding.shimmer.stopShimmer()
                        binding.shimmer.visibility=View.GONE
                        binding.recyclerView.visibility=View.VISIBLE
                        list.clear()
                        if (it.data!!.photos.isEmpty()) {
                            binding.empty.visibility=View.VISIBLE
                            showToast("No Data Found")
                            Log.d("RoverDatas", "hitRoverDataApi: ${it.data.toString()}")
                         //   Loader.hideLoading()
                        } else {
                            for(i in  it.data.photos){
                                list.add(AllDataModel(i.id.toString(),i.sol.toString(),
                                MyCamera(i.camera.full_name,
                                i.camera.id,i.camera.name,i.camera.rover_id),
                                    i.img_src,i.earth_date,
                                    MyRover(i.rover.id,i.rover.landing_date,i.rover.launch_date,i.rover.name,
                                    i.rover.status)
                                ))
                            }
                            val layoutManager = GridLayoutManager(this@ShowAllData, 2)
                            binding.recyclerView.layoutManager = layoutManager
                            myAdapter= MyAdapter(this@ShowAllData,this@ShowAllData)
                            myAdapter.submitList(list)
                            binding.recyclerView.adapter=myAdapter


                            binding.apply {
                                recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
                                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                                        if(newState==OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                                            isScrolling=true
                                        }

                                    }

                                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                                        currentItem= layoutManager.childCount
                                        totalItem= layoutManager.itemCount
                                        scrollOutItem= layoutManager.findFirstVisibleItemPosition()

                                        if(isScrolling && (currentItem+scrollOutItem==totalItem)){
                                            isScrolling=false
                                          Loader.showLoading(applicationContext)
                                        }
                                     //   Loader.hideLoading()

                                    }
                                })
                            }


                            Log.d("checkList", "hitRoverDataApi:  ${list.size} $list")

                            binding.empty.visibility=View.GONE
                            Log.d("RoverDatas", "hitRoverDataApi: ${it.data.toString()}")
                            Loader.hideLoading()

                        }


                    }
                }
            })
        }
    }

    override fun getAllData(list: AllDataModel, position: Int) {
        Log.d("listOfClickedData", "getAllData: ${list.toString()}")
        val data = Gson().toJson(list)
        val intent=Intent(applicationContext,RoverInfoActivity::class.java)
        intent.putExtra("data",data)
        startActivity(intent)
    }
}