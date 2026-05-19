package com.example.arogyanidhi.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.arogyanidhi.ui.screens.onboarding.OnboardingScreen
import com.example.arogyanidhi.ui.screens.quiz.QuizScreen
import com.example.arogyanidhi.ui.screens.result.ResultScreen
import com.example.arogyanidhi.ui.screens.scheme.SchemeDetailsScreen
import com.example.arogyanidhi.ui.screens.hospital.HospitalScreen
import com.example.arogyanidhi.ui.screens.ai.AiAssistantScreen

@Composable
fun AppNavigation(viewModel: com.example.arogyanidhi.ui.ArogyaViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "onboarding") {
        composable("onboarding") {
            OnboardingScreen(
                onNavigateToQuiz = { navController.navigate("quiz") },
                onNavigateToAi = { navController.navigate("ai_assistant") }
            )
        }
        composable("quiz") {
            QuizScreen(
                viewModel = viewModel,
                onQuizComplete = { 
                    navController.navigate("results")
                }
            )
        }
        composable("results") {
            ResultScreen(
                viewModel = viewModel,
                onSchemeSelected = { schemeId ->
                    navController.navigate("scheme_details/$schemeId")
                },
                onNavigateToHospitals = {
                    navController.navigate("hospitals")
                }
            )
        }
        composable(
            route = "scheme_details/{schemeId}",
            arguments = listOf(navArgument("schemeId") { type = NavType.StringType }),
            deepLinks = listOf(navDeepLink { uriPattern = "arogyanidhi://app/scheme/{schemeId}" })
        ) { backStackEntry ->
            val schemeId = backStackEntry.arguments?.getString("schemeId") ?: ""
            SchemeDetailsScreen(
                schemeId = schemeId,
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onFindHospitals = { navController.navigate("hospitals") }
            )
        }
        composable("hospitals") {
            HospitalScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable("ai_assistant") {
            AiAssistantScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
