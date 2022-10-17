package com.karayel.composeworkaround

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.karayel.composeworkaround.core.BaseActivity
import com.karayel.composeworkaround.core.BooNavigation
import com.karayel.composeworkaround.core.FooNavigation
import com.karayel.composeworkaround.screen.Boo
import com.karayel.composeworkaround.screen.Foo

class MainActivity : BaseActivity() {

    @Composable
    override fun OnBecomeVisibleScreen() {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = BooNavigation.route
        ) {
            composable(
                route = BooNavigation.route
            ) {
                Boo(navController = navController)
            }

            composable(
                route = "foo?userId={userId}",
                arguments = listOf(navArgument("userId") {
                    type = NavType.StringType
                    defaultValue = ""
                })
            ) { backStackEntry ->
                Foo(
                    userId = backStackEntry.arguments?.getString("userId").orEmpty(),
                    navController = navController
                )
            }
        }
    }
}
