package ke.co.tulivuapps.hobbyhorsetours.composebase.features.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ke.co.tulivuapps.hobbyhorsetours.composebase.R


@Composable
fun SearchBox() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        TextField(modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .border(1.dp, color = if (isSystemInDarkTheme()) Color.LightGray else Color.White,
                shape = RoundedCornerShape(30.dp)),
            value = "",
            onValueChange = {
                it
            },
            shape = RoundedCornerShape(30.dp),
            placeholder = {
                Text(
                    text = "Search...",
                    color = Color.LightGray,
                    fontSize = 13.sp
                )
            },
            singleLine = true,
            trailingIcon = {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(shape = CircleShape)
                        .background(Color.Black), contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = R.drawable.filter),
                        contentDescription = "Filter Icon", tint = Color.White
                    )

                }
            }, colors = TextFieldDefaults.textFieldColors(
                backgroundColor = if (isSystemInDarkTheme()) Color.White else Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            leadingIcon = {
                Icon(
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "Search Icon",
                )
            }
        )


    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchBox() {
    SearchBox()
}