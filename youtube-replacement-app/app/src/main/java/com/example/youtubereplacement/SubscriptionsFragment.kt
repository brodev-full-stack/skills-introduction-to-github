package com.example.youtubereplacement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SubscriptionsFragment : Fragment() {
    
    private lateinit var recyclerView: RecyclerView
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_subscriptions, container, false)
        
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        
        // Fetch subscribed channels' videos
        fetchSubscriptionVideos()
        
        return view
    }
    
    private fun fetchSubscriptionVideos() {
        // This would normally call the YouTube API to get videos from subscribed channels
        val videoList = mutableListOf<VideoItem>()
        
        // Add some sample subscription videos
        for (i in 1..20) {
            videoList.add(
                VideoItem(
                    id = "sub_$i",
                    title = "New Video from Channel $i",
                    channelName = "Subscribed Channel $i",
                    viewCount = "${(i * 3000)} views",
                    duration = "${i % 8 + 1}:${String.format("%02d", i % 60)}",
                    thumbnailUrl = "https://example.com/sub_thumbnail_$i.jpg",
                    uploadDate = "${i % 3 + 1} hours ago"
                )
            )
        }
        
        val adapter = VideoAdapter(videoList) { videoItem ->
            // Handle video click - open video player
            val bundle = Bundle().apply {
                putString("video_id", videoItem.id)
                putString("video_title", videoItem.title)
            }
            val playerFragment = VideoPlayerFragment()
            playerFragment.arguments = bundle
            
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, playerFragment)
                .addToBackStack(null)
                .commit()
        }
        
        recyclerView.adapter = adapter
    }
}