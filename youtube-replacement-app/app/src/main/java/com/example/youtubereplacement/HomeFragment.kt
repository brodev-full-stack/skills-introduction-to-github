package com.example.youtubereplacement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {
    
    private lateinit var recyclerView: RecyclerView
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        
        // Initialize with trending videos
        fetchTrendingVideos()
        
        return view
    }
    
    private fun fetchTrendingVideos() {
        // This would normally call the YouTube API to get trending videos
        // For now, we'll simulate with placeholder data
        val videoList = mutableListOf<VideoItem>()
        
        // Add some sample videos
        for (i in 1..20) {
            videoList.add(
                VideoItem(
                    id = "video_$i",
                    title = "Sample Video $i",
                    channelName = "Channel $i",
                    viewCount = "${(i * 1000)} views",
                    duration = "${i % 10 + 1}:${String.format("%02d", i % 60)}",
                    thumbnailUrl = "https://example.com/thumbnail_$i.jpg",
                    uploadDate = "${i % 7 + 1} days ago"
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