package com.example.youtubereplacement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LibraryFragment : Fragment() {
    
    private lateinit var recyclerView: RecyclerView
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_library, container, false)
        
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        
        // Fetch user's library items (playlists, history, etc.)
        fetchLibraryItems()
        
        return view
    }
    
    private fun fetchLibraryItems() {
        // This would normally fetch user's library items
        val videoList = mutableListOf<VideoItem>()
        
        // Add some sample library videos
        for (i in 1..20) {
            videoList.add(
                VideoItem(
                    id = "lib_$i",
                    title = "Library Video $i",
                    channelName = "Library Channel $i",
                    viewCount = "${(i * 2000)} views",
                    duration = "${i % 12 + 1}:${String.format("%02d", i % 60)}",
                    thumbnailUrl = "https://example.com/lib_thumbnail_$i.jpg",
                    uploadDate = "${i % 5 + 1} days ago"
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