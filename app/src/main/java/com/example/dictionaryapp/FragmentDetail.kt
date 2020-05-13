package com.example.dictionaryapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri
import android.widget.*

class FragmentDetail : Fragment() {
        private var mp: MediaPlayer? = null
        private var mc: MediaController? = null


    override  fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val rootView= inflater.inflate(R.layout.fragment_fragment_detail, container, false)
            val bundle = this.arguments
            val image = rootView.findViewById<ImageView>(R.id.image)
            val description = rootView.findViewById<TextView>(R.id.description)
            val audio =rootView.findViewById<ImageButton>(R.id.imageButton4)
            val video=rootView.findViewById<VideoView>(R.id.videoView)
            val play=rootView.findViewById<Button>(R.id.btnPlayVideo)


            if (bundle != null) {
                val index = bundle.getInt("amount", android.R.attr.defaultValue)
                description.text=resources.getStringArray(R.array.def_array)[index]
                if (index==0){
                    play.visibility= View.VISIBLE
                    video.visibility=View.VISIBLE
                    audio.setOnClickListener { lireAudio(R.raw.combiner)}
                        play.setOnClickListener { lireVideo(R.raw.video)}
                }
                if(index==1){audio.setOnClickListener { lireAudio(R.raw.dontologie)}}
                if(index==2){audio.setOnClickListener { lireAudio(R.raw.flageoler)}}


                var images = resources.obtainTypedArray(R.array.images)
                var i= resources.getIntArray(R.array.images)[index]
                image.setImageResource(images.getResourceId(index,-1))


            }

            return rootView
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            //you can set the title for your toolbar here for different fragments different titles
            activity!!.title = "Détails"
        }
        fun lireAudio(resId: Int) {
            if (mp == null) {        //set up MediaPlayer
                mp = MediaPlayer.create(activity, resId)

                try {
                    mp!!.prepare()

                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
            if (!mp!!.isPlaying())
                mp!!.start()
            else
                mp!!.pause()
        }
    private fun lireVideo(resId: Int) {
        val vv = view!!.findViewById<VideoView>(R.id.videoView)
        if (mc == null) {

            mc = MediaController(activity)
            vv.setMediaController(mc)
            val video = Uri.parse("android.resource://" + activity?.packageName + "/"
                    + resId) //do not add any extension
            vv.setVideoURI(video)
            vv.start()
        } else {
            if (!vv.isPlaying) {
                vv.start()

            } else {
                vv.pause()
            }
        }
    }

    }



