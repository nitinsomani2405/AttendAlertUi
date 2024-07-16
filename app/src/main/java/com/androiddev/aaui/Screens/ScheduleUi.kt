package com.androiddev.aaui.Screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.androiddev.aaui.R
import java.time.DateTimeException
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScheduleUi(modifier: Modifier){
    Box(modifier = modifier.fillMaxSize()) {

        BackGround(modifier = Modifier.fillMaxSize())
        Column(modifier=Modifier) {
            Headerbar(modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(16.dp))

            DateAndDay(modifier = Modifier.fillMaxWidth())

            LabSchedule(modifier = Modifier.fillMaxWidth(), onClick = {"Clicked"})

            AttendanceButtons(modifier = Modifier,onAttendedClick = { "Attended" },
                onMissedClick = { "Missed" },
                onCancelledClick = { "Cancelled" })

            Spacer(modifier = Modifier.height(25.dp))

            ClassesSchedule(modifier = Modifier.fillMaxWidth(), onClick = {"Clicked"})


        }
    }
}
@Composable
fun BackGround(modifier: Modifier){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ){
        Image(
            painter = painterResource(id =R.drawable.gradient_bg),
            contentDescription =null,
            modifier= Modifier
                .fillMaxSize()
                .alpha(0.1f),
            contentScale = ContentScale.FillBounds
        )
    }
}
@Composable
fun Headerbar(modifier: Modifier) {
    val headerColor = Color(0xFF7A3853)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(headerColor)
            .padding(top = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "ATTEND ALERT",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.weight(1f)
            )
            Icon(
                Icons.Filled.Menu,
                contentDescription = "Menu Icon",
                tint = Color.Black,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateAndDay(modifier: Modifier){
    val currentDate=LocalDate.now()
    val dateFormatter= DateTimeFormatter.ofPattern("EEE,MMM d,yyyy")
    val dayOfWeek=currentDate.format(DateTimeFormatter.ofPattern("EEEE"))

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "${currentDate.format(dateFormatter)} - $dayOfWeek",
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun LabSchedule(modifier: Modifier=Modifier,onClick:()->Unit){
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "LABS",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Spacer(modifier = Modifier.height(20.dp))
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick()
                }
                .background(color = Color.DarkGray),
            shape = RoundedCornerShape(30.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2000.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Today's Lab",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

    }
}
@Composable
fun AttendanceButtons(
    modifier: Modifier=Modifier,
    onAttendedClick: () -> Unit,
    onMissedClick: () -> Unit,
    onCancelledClick: () -> Unit
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            modifier = Modifier.padding(start = 16.dp),
            onClick = onAttendedClick,
            colors = ButtonDefaults.run {
                buttonColors(
                        containerColor = Color.Green,
                        contentColor = Color.Black
                    )
            },
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Attended")
        }
        Button(
            onClick = onMissedClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Missed")
        }
        Button(
            modifier = Modifier.padding(end = 16.dp),
            onClick = onCancelledClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Cancelled")
        }
    }
}

@Composable
fun ClassesSchedule(modifier: Modifier,onClick: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Lectures",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Spacer(modifier = Modifier.height(20.dp))
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick()
                }
                .background(color = Color.DarkGray),
            shape = RoundedCornerShape(30.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2000.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "lecture-1",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
    AttendanceButtons(modifier = Modifier,onAttendedClick = { "Attended" },
        onMissedClick = { "Missed" },
        onCancelledClick = { "Cancelled" })

    Spacer(modifier = Modifier.height(5.dp))


    Column(modifier = Modifier.padding(16.dp)) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick()
                }
                .background(color = Color.DarkGray),
            shape = RoundedCornerShape(30.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2000.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "lecture-2",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }

    AttendanceButtons(modifier = Modifier,onAttendedClick = { "Attended" },
        onMissedClick = { "Missed" },
        onCancelledClick = { "Cancelled" })

}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
fun ScheduleUiPreview() {
    ScheduleUi(modifier = Modifier)
}

