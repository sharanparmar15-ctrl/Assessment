package com.example.assessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.assessment.ui.feature.Success.SuccessScreen
import com.example.assessment.ui.feature.register.RegisterScreen
import com.example.assessment.ui.navigation.NavRoutes
import com.example.assessment.ui.theme.AssessmentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AssessmentTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                    TopAppBar(title = {
                        Text("Customers App")
                    })
                }) { paddingValues ->
                    val navController = rememberNavController()
                    AppNavHost(navController = navController, paddingValues)
                }
            }
        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost (
        navController = navController,
        startDestination = NavRoutes.Register.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(NavRoutes.Register.route) {
            RegisterScreen(navController)
        }
        composable(NavRoutes.Success.route, arguments = listOf(
            navArgument("name")    { type = NavType.StringType },
            navArgument("email")   { type = NavType.StringType },
            navArgument("phoneNo") { type = NavType.StringType },
            navArgument("city")    { type = NavType.StringType },
        )) {
            SuccessScreen()
        }
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AssessmentTheme {
        Greeting("Android")
    }
}