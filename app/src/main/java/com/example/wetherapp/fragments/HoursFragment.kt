package com.example.wetherapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wetherapp.MainViewModel
import com.example.wetherapp.R
import com.example.wetherapp.adapters.WeatherAdapter
import com.example.wetherapp.adapters.WeatherModel
import com.example.wetherapp.databinding.FragmentHoursBinding
import org.json.JSONArray
import org.json.JSONObject

import java.util.Calendar

class HoursFragment : Fragment() {
    //private lateinit var hours: String
    private lateinit var binding: FragmentHoursBinding
    private lateinit var adapter: WeatherAdapter
    private val model: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHoursBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
        model.livaDataCurrent.observe(viewLifecycleOwner){
            adapter.submitList(getHoursList(it))
        }
    }
    //private lateinit var layoutManager: LinearLayoutManager
    //layoutManager.scrollToPosition(10)\
    //layoutManager.scrollToPositionWithOffset(10, 0)
    private fun initRcView() = with(binding){
        rcView.layoutManager = LinearLayoutManager(activity)
        adapter = WeatherAdapter(null)
        rcView.adapter = adapter
        //(rcView.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(10,0)
    }


    private val sharedViewModel: MainFragment.SharedViewModel by activityViewModels()
    private fun getHoursList(wItem: WeatherModel): List<WeatherModel>{
        val hoursArray = JSONArray(wItem.hours)
        val list = ArrayList<WeatherModel>()

        //val calendar = Calendar.getInstance()
        //val hour = calendar.get(Calendar.HOUR_OF_DAY)

        val hours = sharedViewModel.hours.value.toString().toInt()


        for(i in hours until hoursArray.length()){
            val item = WeatherModel(
                wItem.city,
                (hoursArray[i] as JSONObject).getString("time").substring(11),
                (hoursArray[i] as JSONObject).getJSONObject("condition").getString("text"),
                (hoursArray[i] as JSONObject).getString("temp_c"),
                "",
                "",
                (hoursArray[i] as JSONObject).getJSONObject("condition").getString("icon"),
                ""
            )
            list.add(item)
        }
        return list
    }

    companion object {
        @JvmStatic
        fun newInstance() = HoursFragment()
    }
}