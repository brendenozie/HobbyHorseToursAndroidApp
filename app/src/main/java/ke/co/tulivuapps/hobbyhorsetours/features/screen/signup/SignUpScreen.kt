package ke.co.tulivuapps.hobbyhorsetours.features.screen.signup

import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.launch
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ke.co.tulivuapps.hobbyhorsetours.R
import ke.co.tulivuapps.hobbyhorsetours.features.lottie.LottieWorkingLoadingView
import ke.co.tulivuapps.hobbyhorsetours.utils.LoginWithGoogle
import kotlinx.coroutines.launch

@SuppressLint("UnusedCrossfadeTargetStateParameter")
@Composable
fun SignUpOnboarding(
    signupViewModel: OnSignUpViewModel = hiltViewModel(),
    navController: NavController
) {

    val loggedIn by signupViewModel.selectedLogin.collectAsState()

    LaunchedEffect(key1 = loggedIn) {
        if (loggedIn) {
            navController.popBackStack()
        }
    }

    Crossfade(targetState = loggedIn, label = "LoginCrossFadeAnimation") {
        SignUpScreen(navigateToBack = {navController.popBackStack()},
            onLoginSuccess={navController.popBackStack()},
            onSignUpViewModel = signupViewModel)
    }
}

@Composable
fun SignUpScreen(navigateToBack: () -> Unit,
                 onLoginSuccess: () -> Unit,
                 onSignUpViewModel: OnSignUpViewModel) {

    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val viewState = onSignUpViewModel.uiState.collectAsState().value
//    val tokl = onSignUpViewModel.toke.collectAsState().value
//    val rUser = onSignUpViewModel.resultUser.collectAsState().value

//    var loading = onSignUpViewModel.loading.collectAsState().value
    val user = onSignUpViewModel.userDetails.collectAsState().value

    val googleLoginLauncher = rememberLauncherForActivityResult(
        contract = LoginWithGoogle(),
        onResult = {
            if (it != null) {
                onSignUpViewModel.signupWithGoogle(it)
            }
        })

    LaunchedEffect(viewState.data) {
        launch {
            if (viewState.data != null) {
                onSignUpViewModel.signUpWithCredentials(
                    viewState.data.name,
                    viewState.data.email,
                    "123456789",
                    "none"
                )
            }
        }
    }

    LaunchedEffect(onSignUpViewModel.uiEvent) {
        launch {
            onSignUpViewModel.uiEvent.collect {
                when (it) {
                    is SignUpViewEvent.SnackBarError -> {
                        scaffoldState.snackbarHostState.showSnackbar(it.message.orEmpty())
                    }

                    else -> {}
                }
            }
        }
    }


    Scaffold { paddingValues ->

        //TextFields
        var name by remember { mutableStateOf(TextFieldValue("")) }
        var email by remember { mutableStateOf(TextFieldValue("")) }
        var password by remember { mutableStateOf(TextFieldValue("")) }
        var confirmPassword by remember { mutableStateOf(TextFieldValue("")) }
        var hasError by remember { mutableStateOf(false) }
        var passwordVisualTransformation by remember {
            mutableStateOf<VisualTransformation>(
                PasswordVisualTransformation()
            )
        }
        var confirmpasswordVisualTransformation by remember {
            mutableStateOf<VisualTransformation>(
                PasswordVisualTransformation()
            )
        }
        val passwordInteractionState = remember { MutableInteractionSource() }
        val emailInteractionState = remember { MutableInteractionSource() }

        val nameInteractionState = remember { MutableInteractionSource() }
        val confirmpasswordInteractionState = remember { MutableInteractionSource() }

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {

            item { Spacer(modifier = Modifier.height(20.dp)) }
            item { LottieWorkingLoadingView() }
            item {
                Text(
                    text = "Welcome",
                    fontWeight = FontWeight.ExtraBold,
//                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            item {
                Text(
                    text = "Let's have your account set up!",
//                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }

            item {
                OutlinedTextField(
                    value = name,
                    leadingIcon = {
                        Icon(
//                            faIcon = FaIcons.Envelope,
                            painter = painterResource(R.drawable.ic_person),
                            contentDescription = "username",
                            tint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
                            modifier = Modifier.size(12.dp)
                        )
                    },
                    maxLines = 1,
                    isError = hasError,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(),
                    label = { Text(text = "Username") },
                    placeholder = { Text(text = "username",color = Color.LightGray) },
                    onValueChange = {
                        name = it
                    },
                    interactionSource = nameInteractionState,
                )
            }
            item {
                OutlinedTextField(
                    value = email,
                    leadingIcon = {
                        Icon(
//                            faIcon = FaIcons.Envelope,
                            painter = painterResource(R.drawable.envelope),
                            contentDescription = "envolpe",
                            tint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
                            modifier = Modifier.size(12.dp)
                        )
                    },
                    maxLines = 1,
                    isError = hasError,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(),
                    label = { Text(text = "Email address") },
                    placeholder = { Text(text = "abc@gmail.com",color = Color.LightGray) },
                    onValueChange = {
                        email = it
                    },
                    interactionSource = emailInteractionState,
                )
            }

            item {
                OutlinedTextField(
                    value = password,
                    leadingIcon = {
                        Icon(
//                            faIcon = FaIcons.Key,
                            painter = painterResource(R.drawable.key),
                            contentDescription = "key",
                            tint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
                            modifier = Modifier.size(12.dp)
                        )
                    },
                    trailingIcon = {
                        Icon(
//                            Icon = FaIcons.EyeSlash,
                            painter = painterResource(R.drawable.eyelashes),
                            contentDescription = "eyelash",
                            tint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
                            modifier = Modifier
                                .size(12.dp)
                                .clickable(onClick = {
                                    passwordVisualTransformation =
                                        if (passwordVisualTransformation != VisualTransformation.None) {
                                            VisualTransformation.None
                                        } else {
                                            PasswordVisualTransformation()
                                        }
                                })
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(),
                    maxLines = 1,
                    isError = hasError,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    label = { Text(text = "Password") },
                    placeholder = { Text(text = "12334444",color = Color.LightGray) },
                    onValueChange = {
                        password = it
                    },
                    interactionSource = passwordInteractionState,
                    visualTransformation = passwordVisualTransformation,
                )
            }

            item {
                OutlinedTextField(
                    value = confirmPassword,
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.key),
                            contentDescription = "key",
                            tint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
                            modifier = Modifier.size(12.dp)
                        )
                    },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.eyelashes),
                            contentDescription = "eyelash",
                            tint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
                            modifier = Modifier
                                .size(12.dp)
                                .clickable(onClick = {
                                    confirmpasswordVisualTransformation =
                                        if (confirmpasswordVisualTransformation != VisualTransformation.None) {
                                            VisualTransformation.None
                                        } else {
                                            PasswordVisualTransformation()
                                        }
                                })
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(),
                    maxLines = 1,
                    isError = hasError,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    label = { Text(text = "Re-enter Password") },
                    placeholder = { Text(text = "12334444",color = Color.LightGray) },
                    onValueChange = {
                        confirmPassword = it
                    },
                    interactionSource = confirmpasswordInteractionState,
                    visualTransformation = passwordVisualTransformation,
                )
            }

            item {
                var loading by remember { mutableStateOf(false) }
                Button(
                    onClick = {
                        if (invalidInput(email.text, password.text, name.text , confirmPassword.text)) {
                            hasError = true
                            loading = false
                        } else {
                            loading = true
                            hasError = false
                        }

                        onSignUpViewModel.signUpWithCredentials(username = name.text, emailAddress = email.text, password = password.text,img = email.text,)
                        onLoginSuccess.invoke()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .height(50.dp)
                        .clip(CircleShape)
                ) {
                    if (viewState.isLoading) {
                        HorizontalDottedProgressBar()
                    } else {
                        Text(text = "Register")
                    }
                }
            }
            item {
                Box(modifier = Modifier.padding(vertical = 16.dp)) {
                    Spacer(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .height(1.dp)
                            .fillMaxWidth()
                            .background(Color.LightGray)
                    )
                    Text(
                        text = "Or use",
                        color = Color.Gray,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .background(MaterialTheme.colors.background)
                            .padding(horizontal = 16.dp)
                    )
                }
            }

//            item {
//                OutlinedButton(
//                    onClick = { }, modifier = Modifier
//                        .fillMaxWidth()
//                        .height(50.dp)
//                ) {
//                    Icon(
//                        painter = painterResource(R.drawable.facebook),
//                        contentDescription = "facebook",
////                        tint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
//                    )
//                    Text(
//                        text = "Register with Facebook",
//                        fontSize = 14.sp,
//                        textAlign = TextAlign.Center,
//                        modifier = Modifier.fillMaxWidth()
//                    )
//                }
//            }

            item { Spacer(modifier = Modifier.height(8.dp)) }

            item {
                OutlinedButton(
                    onClick = { googleLoginLauncher.launch() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.google),
                        contentDescription = "google",
                    )
                    Text(
                        text = "Register with Gmail",
                        style = MaterialTheme.typography.h3.copy(fontSize = 14.sp),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            item {
                val primaryColor = MaterialTheme.colors.primary
                val annotatedString = remember {
                    AnnotatedString.Builder("Have an account? Login")
                        .apply {
                            addStyle(style = SpanStyle(color = primaryColor), 23, 31)
                        }
                }
                Text(
                    text = annotatedString.toAnnotatedString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .clickable(onClick = { navigateToBack() }),
                    textAlign = TextAlign.Center
                )
            }

            item { Spacer(modifier = Modifier.height(100.dp)) }
        }
    }
}



fun invalidInput(username: String,email: String, password: String, confirmPassword: String) =
    email.isBlank() || password.isBlank() || username.isBlank() || confirmPassword.isBlank()
