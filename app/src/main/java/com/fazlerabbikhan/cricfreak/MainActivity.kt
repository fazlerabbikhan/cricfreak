package com.fazlerabbikhan.cricfreak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.fazlerabbikhan.cricfreak.databinding.ActivityMainBinding
import com.fazlerabbikhan.cricfreak.global.Constant
import com.fazlerabbikhan.cricfreak.viewmodel.CricViewModel
import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val viewModel: CricViewModel by viewModels()

    fun checkInternetConnection(context: Context) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo

        if (networkInfo != null && networkInfo.isConnected) {
            // Internet connection is available
            Toast.makeText(context, "Internet connection available", Toast.LENGTH_SHORT).show()
        } else {
            // Internet connection is not available
            Toast.makeText(context, "Internet connection not available", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkInternetConnection(this)

//        set global context view
        Constant.contextView = binding.navHostFragment
//        set global context
        Constant.context = applicationContext

//        roomdb
        viewModel.catchLeagues().observe(this){
            viewModel.addLeagueList(it)
        }
        viewModel.catchTeams().observe(this){
            viewModel.addTeamList(it)
        }

//        navigation
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment)
        )
        navController = navHostFragment.findNavController()
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomNavigation.setupWithNavController(navController)

//        val periodicWorkRequest =
//            PeriodicWorkRequest.Builder(NewsApiCallWorker::class.java, 5, TimeUnit.HOURS).build()
//        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
//            "periodicNewsAPICall",
//            ExistingPeriodicWorkPolicy.KEEP,
//            periodicWorkRequest
//        )
//        one time call
//        val work = OneTimeWorkRequestBuilder<NewsApiCallWorker>()
//            .build()
//        WorkManager.getInstance(this).enqueue(work)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}