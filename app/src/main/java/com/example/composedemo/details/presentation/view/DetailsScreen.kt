package com.example.composedemo.details.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.composedemo.core.DetailsScreen
import com.example.composedemo.home.domain.model.Character


/**
 * Created by  Ajeet Singh on Date: 11/08/25.
 */
@Composable
fun DetailsScreenCore(character: Character) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
        , horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top
    ) {
        DetailsScreen(character)
    }
}

@Composable
fun DetailsScreen(character: Character) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
        Spacer(modifier = Modifier.height(20.dp))
        AsyncImage(
            character.image, null,
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, shape = CircleShape)
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            character.name, fontSize = 25.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Card(
            colors = CardDefaults.cardColors(Color.White), elevation = CardDefaults.cardElevation(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(15.dp, 20.dp)
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 20.dp)) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        "Status", fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    )
                    Text(
                        character.status, fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .wrapContentWidth()
                    )
                }
                HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray, modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        "Species", fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    )
                    Text(
                        character.species, fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .wrapContentWidth()
                    )
                }
                HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray, modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        "Gender", fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    )
                    Text(
                        character.gender, fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .wrapContentWidth()
                    )
                }
                HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray, modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        "Origin", fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    )
                    Text(
                        character.origin, fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .wrapContentWidth()
                    )
                }
                HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray, modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        "Location", fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    )
                    Text(
                        character.location, fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .wrapContentWidth()
                    )
                }
            }
        }
    }

}