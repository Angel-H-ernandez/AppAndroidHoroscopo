package com.example.horoscoApp.ui.luck

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import com.example.horoscoApp.ui.core.listeners.OnSwipeTouchListener
import com.example.horoscoApp.ui.provider.RandomCardProvider
import com.example.intermedioappkottlin.R
import com.example.intermedioappkottlin.databinding.FragmentLuckBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.random.Random

@AndroidEntryPoint
class LuckFragment : Fragment() {
    private var _binding : FragmentLuckBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var randomCardProvider : RandomCardProvider

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        preparePrediction()
        initListeners()
    }

    private fun preparePrediction() {
        val Currentluck= randomCardProvider.getLucky()
        Currentluck?.let {
            val currentPredicition = getString(it.text)
            binding.tvLucky.text = currentPredicition
            binding.ivLuckyCard.setImageResource(it.image)
            binding.tvShare.setOnClickListener{
                shareResult(currentPredicition)
            }
        }
    }

    private fun shareResult(predicition: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, predicition)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }


    @SuppressLint("ClickableViewAccessibility")
    private fun initListeners() {
        binding.ivRoulette.setOnTouchListener(object : OnSwipeTouchListener(requireContext()){

            override fun onSwipeRight() {
                spinRoulette()
            }

            override fun onSwipeLeft() {
                spinRoulette()
            }
        })
    }

    private fun spinRoulette() {
        val random = kotlin.random.Random
        val degrees = random.nextInt(144) + 360
        val animator = ObjectAnimator.ofFloat(binding.ivRoulette, View.ROTATION, 0f, degrees.toFloat())
       animator.duration = 2000
        animator.interpolator = DecelerateInterpolator()
        animator.doOnEnd { slideCard() }
        animator.start()
    }

    private fun slideCard(){
    //metodo para abrir y cerrar la tarjeta
        val slideUpAnimator = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up)
        slideUpAnimator.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {
                binding.ivRerverse.isVisible = true
            }

            override fun onAnimationEnd(p0: Animation?) {
                growAnimation()
            }

            override fun onAnimationRepeat(p0: Animation?) {
                //No se utiliza
            }
        })
        binding.ivRerverse.startAnimation(slideUpAnimator)


    }

    private fun growAnimation() {
        val slideUpAnimator = AnimationUtils.loadAnimation(requireContext(), R.anim.grow)
        slideUpAnimator.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                binding.ivRerverse.isVisible = false
                showPremonicion()
            }

            override fun onAnimationRepeat(p0: Animation?) {
                //No se utiliza
            }
        })
        binding.ivRerverse.startAnimation(slideUpAnimator)
    }

    private fun showPremonicion() {
        val  disapearAnimation = AlphaAnimation(1.0f, 0.0f)
        disapearAnimation.duration = 200
        val  appearAnimation = AlphaAnimation(0.0f, 1.0f)
        appearAnimation.duration = 1000
        disapearAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {
                //No se utiliza
            }

            override fun onAnimationEnd(p0: Animation?) {
                binding.preview.isVisible = false
                binding.prediction.isVisible = true
            }

            override fun onAnimationRepeat(p0: Animation?) {
                //No se utiliza
            }
        })

        binding.preview.startAnimation(disapearAnimation)
        binding.prediction.startAnimation(appearAnimation)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLuckBinding.inflate(layoutInflater,container, false)

        return binding.root

    }

}