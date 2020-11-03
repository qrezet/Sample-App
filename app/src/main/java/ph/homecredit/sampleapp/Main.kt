package ph.homecredit.sampleapp

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material.icons.sharp.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.*
import ph.homecredit.sampleapp.Screen.*
import ph.homecredit.sampleapp.ui.SampleAppTheme


@Composable
fun Main() {
    SampleAppTheme {
        val navController = rememberNavController()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text("Sample App")
                    },
                )
            },
            bottomBar = {
                val currentBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = currentBackStackEntry?.arguments?.get(KEY_ROUTE)
                if (currentRoute != ScreenC.route) {
                    BottomNavigation {
                        BottomNavigationItem(
                            icon = { Icon(Icons.Default.Home) },
                            selected = true,
                            onClick = {}
                        )
                        BottomNavigationItem(
                            icon = { Icon(Icons.Default.PieChart) },
                            selected = false,
                            onClick = {}
                        )
                        BottomNavigationItem(
                                icon = { Icon(Icons.Default.Person) },
                                selected = false,
                                onClick = {}
                        )
                    }
                }
            }
        ) {
            val modifier = Modifier.padding(it)
            NavHost(
                navController = navController,
                startDestination = ScreenA.route,
            ) {
                composable(ScreenA.route) {
                    MyScreen(modifier, "A", "", "to B") {
                        navController.navigate(ScreenB.route)
                    }
                }
                composable(ScreenB.route) {
                    MyScreen(modifier, "B",  "", "to C") {
                        navController.navigate(ScreenC.route)
                    }
                }
                composable(ScreenC.route) {
                    MyScreen(modifier, "C",  "","to A") {
                        navController.navigate(ScreenA.route)
                    }
                }
            }
        }
    }
}

sealed class Screen(
    val route: String
) {

    object ScreenA : Screen("a")
    object ScreenB : Screen("b")
    object ScreenC : Screen("c")

}

@Composable
fun MyScreen(
    modifier: Modifier = Modifier,
    name: String,
    value: String,
    buttonName: String,
    onNext: () -> Unit,
) {
    Column(
        modifier.fillMaxSize().padding(24.dp),
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = "I am in screen $name!",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h3
        )
        Spacer(Modifier.height(16.dp))
        Text(
            modifier = Modifier.weight(1f),
            text = value,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h1
        )
        Spacer(Modifier.height(16.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onNext
        ) {
            Text(text = buttonName)
        }
    }
}