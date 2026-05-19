package com.example.arogyanidhi.ui.screens.result

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.arogyanidhi.ui.ArogyaViewModel
import com.example.arogyanidhi.data.model.Scheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(
    viewModel: ArogyaViewModel,
    onSchemeSelected: (String) -> Unit,
    onNavigateToHospitals: () -> Unit
) {
    val schemes by viewModel.eligibleSchemes.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Eligible Schemes") })
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onNavigateToHospitals
            ) {
                Text("Find Hospitals")
            }
        }
    ) { padding ->
        if (schemes.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = androidx.compose.ui.Alignment.Center) {
                Text("No schemes found based on your profile.")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(schemes) { scheme ->
                    SchemeCard(scheme = scheme, onClick = { onSchemeSelected(scheme.id) })
                }
            }
        }
    }
}

@Composable
fun SchemeCard(scheme: Scheme, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = scheme.name, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = scheme.description, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onClick) {
                Text("View Details")
            }
        }
    }
}
