package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(viewModel: ProfileViewModel = viewModel()) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        if (state.isPreview) "Profile Preview" else "Edit Profile",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            if (state.isPreview) {
                ProfilePreview(state = state, onBack = { viewModel.backToEdit() })
            } else {
                ProfileForm(state = state, viewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileForm(state: ProfileUiState, viewModel: ProfileViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            "Basic Information",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(12.dp))

        CustomTextField(
            value = state.name,
            onValueChange = { viewModel.onNameChange(it) },
            label = "Full Name",
            icon = Icons.Default.Person
        )
        CustomTextField(
            value = state.email,
            onValueChange = { viewModel.onEmailChange(it) },
            label = "Email Address",
            icon = Icons.Default.Email
        )
        CustomTextField(
            value = state.contactNumber,
            onValueChange = { viewModel.onContactChange(it) },
            label = "Contact Number",
            icon = Icons.Default.Phone
        )
        CustomTextField(
            value = state.address,
            onValueChange = { viewModel.onAddressChange(it) },
            label = "Address",
            icon = Icons.Default.LocationOn
        )
        CustomTextField(
            value = state.username,
            onValueChange = { viewModel.onUsernameChange(it) },
            label = "Username",
            icon = Icons.Default.AccountCircle
        )

        Spacer(Modifier.height(24.dp))

        Text(
            "Skills & Expertise",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(12.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = state.newSkill,
                onValueChange = { viewModel.onNewSkillChange(it) },
                label = { Text("Add a skill") },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )
            Spacer(Modifier.width(8.dp))
            Button(
                onClick = { viewModel.addSkill() },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.height(56.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }

        Spacer(Modifier.height(8.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            state.skills.forEach { skill ->
                SkillChip(
                    label = skill,
                    onRemove = { viewModel.removeSkill(skill) }
                )
            }
        }

        Spacer(Modifier.height(32.dp))

        Button(
            onClick = { viewModel.showPreview() },
            enabled = state.name.isNotBlank() && state.email.isNotBlank(),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(Icons.Default.Visibility, contentDescription = null)
            Spacer(Modifier.width(8.dp))
            Text("Preview Profile", fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(12.dp))

        OutlinedButton(
            onClick = { viewModel.clearAll() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error)
        ) {
            Icon(Icons.Default.DeleteSweep, contentDescription = null)
            Spacer(Modifier.width(8.dp))
            Text("Clear All")
        }
        
        Spacer(Modifier.height(24.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SkillChip(label: String, onRemove: () -> Unit) {
    InputChip(
        selected = true,
        onClick = { },
        label = { Text(label) },
        trailingIcon = {
            IconButton(
                onClick = onRemove,
                modifier = Modifier.size(18.dp)
            ) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = "Remove",
                    modifier = Modifier.size(14.dp),
                    tint = MaterialTheme.colorScheme.error
                )
            }
        },
        modifier = Modifier.padding(vertical = 4.dp),
        shape = CircleShape
    )
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = { Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        singleLine = true
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfilePreview(state: ProfileUiState, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier.size(60.dp),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        
        Spacer(Modifier.height(16.dp))
        
        Text(
            state.name.ifBlank { "No Name" },
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            "@${state.username.ifBlank { "username" }}",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                InfoItem(Icons.Default.Email, "Email", state.email)
                InfoItem(Icons.Default.Phone, "Contact", state.contactNumber)
                InfoItem(Icons.Default.LocationOn, "Address", state.address)
                
                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = MaterialTheme.colorScheme.outlineVariant)
                
                Text(
                    "Skills",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(Modifier.height(8.dp))
                
                if (state.skills.isEmpty()) {
                    Text("No skills added.", style = MaterialTheme.typography.bodyMedium)
                } else {
                    FlowRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        state.skills.forEach { skill ->
                            SuggestionChip(
                                onClick = { },
                                label = { Text(skill) },
                                shape = CircleShape
                            )
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(32.dp))

        Button(
            onClick = onBack,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(Icons.Default.Edit, contentDescription = null)
            Spacer(Modifier.width(8.dp))
            Text("Back to Edit")
        }
        
        Spacer(Modifier.height(40.dp))
    }
}

@Composable
fun InfoItem(icon: ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icon,
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.width(12.dp))
        Column {
            Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.secondary)
            Text(value.ifBlank { "Not set" }, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
