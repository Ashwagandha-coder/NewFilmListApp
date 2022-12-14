package com.example.newfilmlistapp.ui.movie_detail

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newfilmlistapp.BASE_URL_FOR_PICTURE
import com.example.newfilmlistapp.R
import com.example.newfilmlistapp.view_model.movie_detail.MovieDetailViewModel
import com.example.newfilmlistapp.databinding.FragmentMovieDetailBinding
import com.example.newfilmlistapp.local.db.AppDatabase
import com.example.newfilmlistapp.model.*
import com.example.newfilmlistapp.repository.Impl.ImplRepositoryAPI
import com.example.newfilmlistapp.repository.Impl.ImplRepositoryRoom
import com.example.newfilmlistapp.view_model.movie_detail.MovieDetailViewModelFactory
import kotlinx.coroutines.launch


class MovieDetailFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding
    private val viewModel: MovieDetailViewModel by viewModels {
        MovieDetailViewModelFactory(
            ImplRepositoryAPI(), ImplRepositoryRoom(
                AppDatabase.getDatabase(requireContext().applicationContext)
            )
        )
    }

    private val args: MovieDetailFragmentArgs by navArgs()

    private lateinit var movieDetailWrapperRoom: MovieDetailWrapperRoom

    private var isMovieDB: Boolean = false

    private var flag = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)

        onBackScreen()
        workWithViewModel()
        requestWrapper()
        setListenerFAB()

        return binding.root
    }

    private fun requestWrapper() {
        lifecycleScope.launch {
            val id: Int = args.movieID
            viewModel.requestMovieDetail(id)
            isMovieDB = viewModel.flag
            checkFAB(isMovieDB)
        }

    }

    private fun onBackScreen() {

        binding.topNavBar.returnImageView.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun checkFAB(flag: Boolean) {

        if (flag)
            binding.buttonFavorite.setImageResource(R.drawable.ic_favorite_white_24dp)
        else
            binding.buttonFavorite.setImageResource(R.drawable.ic_favorite_border_white_24dp)

        this.flag = flag
    }


    private fun setListenerFAB() {

        binding.buttonFavorite.setOnClickListener {

            if (flag) {
                binding.buttonFavorite.setImageResource(R.drawable.ic_favorite_border_white_24dp)
                viewModel.onDelete(movieDetailWrapperRoom, requireContext().applicationContext)
                movieDetailWrapperRoom.isFavorite = false
            }
            else {
                binding.buttonFavorite.setImageResource(R.drawable.ic_favorite_white_24dp)
                viewModel.onLoad(movieDetailWrapperRoom, requireContext().applicationContext)
                movieDetailWrapperRoom.isFavorite = true
            }
        }

    }

    private fun workWithViewModel() {

        viewModel.movieDetailWrapper.observe(viewLifecycleOwner) {

            movieDetailWrapperRoom = parse(it)

            val backdrop_path = it.backdropPath

            val poster_path = args.posterPath

            val vote_average = args.voteAverage

            val runtime = it.runtime

            if (it.releaseDate.isNotEmpty()) {
                var date = it.releaseDate
                val dateYear = date.removeRange(4, date.length)
                var dateMonth = date.removeRange(0, 5)
                dateMonth = dateMonth.removeRange(2, dateMonth.length)
                val dateDay = date.removeRange(0, 8)
                date =
                    "$dateDay/$dateMonth/$dateYear" + " (${it.productionCountries[0].iso3166_1})"

                binding.tvYear.apply {
                    text = date
                }

                val htmlText = "<font color=#ffffff>${it.title}</font> <font color=#cce0e3>(${
                    it.releaseDate.removeRange(
                        4,
                        it.releaseDate.length
                    )
                })</font>"

                binding.tvMovieName.apply {
                    text = Html.fromHtml(htmlText)
                }
            } else {
                binding.tvMovieName.apply {
                    text = it.title
                }
            }


            var genres: String? = ""
            for (element in it.genres)
                genres += "${element.name}, "
            binding.movieGenres.apply {
                text = genres?.substring(0, genres.length - 2)
            }

            val runtimeStr = "${runtime!!.toInt() / 60}h ${runtime.toInt()!! % 60}m"
            if (runtime.toInt() >= 60) {
                binding.movieRuntime.apply {
                    text = runtimeStr
                }
            } else {
                binding.movieRuntime.apply {
                    text = runtime.toString()
                }
            }

            if (it.tagline!!.isEmpty()) binding.movieTagline.visibility = View.GONE
            else {
                binding.movieTagline.apply {
                    text = it.tagline
                }
            }

            if (it.overview!!.isEmpty()) binding.movieHeadline.visibility = View.GONE
            else {
                binding.movieHeadline.apply {
                    text = context.getString(R.string.overview)
                }
            }


            binding.progressBar.apply {
                progress = (vote_average * 10).toInt()
            }

            val progressStr: String = binding.progressBar.progress.toString() + "%"

            binding.progressText.apply {
                text = progressStr
            }

            binding.movieStatus.setImageResource(R.drawable.ic_realise)

            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500${backdrop_path}")
                .apply(RequestOptions().centerCrop())
                .into(binding.imgMovie)

            Glide.with(this)
                .load(BASE_URL_FOR_PICTURE + poster_path)
                .apply(RequestOptions().centerCrop())
                .into(binding.poster)

            binding.tvDescription.text = it.overview
            // binding.tvRatingValue.text = it.voteAverage.toString()
            Log.d(
                MovieDetailFragment::class.java.name,
                "realease Date " + it.releaseDate.substring(0, 3)
            )


//            var isMovieDB = viewModel.testSearchDB(it.id)
//            Log.d(MovieDetailFragment::class.java.name,"isMovieDB - ${isMovieDB}")
//            checkFAB(isMovieDB)


        }

    }


    private fun parse(movieDetailWrapper: MovieDetailWrapper): MovieDetailWrapperRoom {


        return MovieDetailWrapperRoom(
            adult = movieDetailWrapper.adult,
            backdropPath = movieDetailWrapper.backdropPath,
            //belongsToCollection = movieDetailWrapper.belongsToCollection,
            budget = movieDetailWrapper.budget,
            //genres = movieDetailWrapper.genres,
            homepage = movieDetailWrapper.homepage,
            id = movieDetailWrapper.id,
            imdbID = movieDetailWrapper.imdbID,
            originalLanguage = movieDetailWrapper.originalLanguage,
            originalTitle = movieDetailWrapper.originalTitle,
            overview = movieDetailWrapper.overview,
            popularity = movieDetailWrapper.popularity,
            posterPath = movieDetailWrapper.posterPath,
            //productionCompanies = movieDetailWrapper.productionCompanies,
            //productionCountries = movieDetailWrapper.productionCountries,
            releaseDate = movieDetailWrapper.releaseDate,
            revenue = movieDetailWrapper.revenue,
            runtime = movieDetailWrapper.runtime,
            //spokenLanguages = movieDetailWrapper.spokenLanguages,
            status = movieDetailWrapper.status,
            tagline = movieDetailWrapper.tagline,
            title = movieDetailWrapper.title,
            video = movieDetailWrapper.video,
            voteAverage = movieDetailWrapper.voteAverage,
            voteCount = movieDetailWrapper.voteCount
        )

    }


}