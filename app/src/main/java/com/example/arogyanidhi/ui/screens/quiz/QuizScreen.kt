package com.example.arogyanidhi.ui.screens.quiz

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.arogyanidhi.ui.ArogyaViewModel
import com.example.arogyanidhi.data.model.UserProfile

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    viewModel: ArogyaViewModel,
    onQuizComplete: () -> Unit
) {
    var step by remember { mutableStateOf(0) }
    
    // Form state
    var income by remember { mutableStateOf("") }
    var isBpl by remember { mutableStateOf(false) }
    var occupation by remember { mutableStateOf("Other") }
    var state by remember { mutableStateOf("") }
    var familySize by remember { mutableStateOf("1") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Eligibility Quiz") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            when (step) {
                0 -> {
                    Text("Step 1: Financial Information", style = MaterialTheme.typography.titleLarge)
                    Spacer(Modifier.height(16.dp))
                    OutlinedTextField(
                        value = income,
                        onValueChange = { income = it },
                        label = { Text("Annual Family Income (₹)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(16.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(checked = isBpl, onCheckedChange = { isBpl = it })
                        Text("Do you have a BPL Ration Card?")
                    }
                }
                1 -> {
                    Text("Step 2: Occupation & Location", style = MaterialTheme.typography.titleLarge)
                    Spacer(Modifier.height(16.dp))
                    // Simple text fields instead of complex dropdowns for now to ensure compile
                    OutlinedTextField(
                        value = occupation,
                        onValueChange = { occupation = it },
                        label = { Text("Occupation (e.g. Farmer, Laborer)") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(16.dp))
                    OutlinedTextField(
                        value = state,
                        onValueChange = { state = it },
                        label = { Text("State") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                2 -> {
                    Text("Step 3: Family Details", style = MaterialTheme.typography.titleLarge)
                    Spacer(Modifier.height(16.dp))
                    OutlinedTextField(
                        value = familySize,
                        onValueChange = { familySize = it },
                        label = { Text("Family Size") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(Modifier.weight(1f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (step > 0) {
                    OutlinedButton(onClick = { step-- }) {
                        Text("Back")
                    }
                } else {
                    Spacer(modifier = Modifier.width(8.dp))
                }

                Button(onClick = {
                    if (step < 2) {
                        step++
                    } else {
                        val profile = UserProfile(
                            income = income.toDoubleOrNull() ?: 0.0,
                            isBpl = isBpl,
                            occupation = occupation,
                            state = state,
                            familySize = familySize.toIntOrNull() ?: 1
                        )
                        viewModel.updateUserProfile(profile)
                        onQuizComplete()
                    }
                }) {
                    Text(if (step < 2) "Next" else "Finish")
                }
            }
        }
    }
}
