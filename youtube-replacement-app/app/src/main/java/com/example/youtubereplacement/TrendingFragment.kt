package com.example.youtubereplacement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TrendingFragment : Fragment() {
    
    private lateinit var recyclerView: RecyclerView
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_trending, container, false)
        
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        
        // Fetch trending videos
        fetchTrendingVideos()
        
        return view
    }
    
    private fun fetchTrendingVideos() {
        // This would normally call the YouTube API to get trending videos
        val videoList = mutableListOf<VideoItem>()
        
        // Add some sample trending videos
        for (i in 1..20) {
            videoList.add(
                VideoItem(
                    id = "trending_$i",
                    title = "Trending Video $i",
                    channelName = "Trending Channel $i",
                    viewCount = "${(i * 5000)} views",
                    duration = "${i % 10 + 2}:${String.format("%02d", i % 60)}",
                    thumbnailUrl = "https://example.com/trending_thumbnail_$i.jpg",
                    uploadDate = "Today"
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