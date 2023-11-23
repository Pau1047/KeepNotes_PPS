package com.example.keepnotes_pps

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.keepnotes_pps.ui.theme.Purple40
import com.example.keepnotes_pps.ui.theme.Purple80

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun Portada(){
    val lazyListState = rememberLazyListState()
    var scrolled = 0f
    var previousOffset = 0
    var selected by remember{ mutableStateOf(-1) }
    val listState = rememberLazyStaggeredGridState()
    val getOpinions =  listOf<String>("Servicio algo flojo, aún así lo recomiendo","Céntrica y acogedora. Volveremos seguro", "Céntrica y acogedora. Volveremos seguro", "La comida estaba deliciosa y bastante bien de precio, mucha variedad de platos.\n", "El personal muy amable, nos permitieron ver todo el establecimiento.\n", "Muy bueno",
        "Excelente. Destacable la extensa carta de cafés", "Buen ambiente y buen servicio. Lo recomiendo.", "En días festivos demasiado tiempo de espera. Los camareros/as no dan abasto. No lo recomiendo. No volveré", "Repetiremos. Gran selección de tartas y cafés.", "Todo lo que he probado en la cafetería está riquísimo, dulce o salado.\n",
        "La vajilla muy bonita todo de diseño que en el entorno del bar queda ideal.\n", "Puntos negativos: el servicio es muy lento y los precios son un poco elevados.")


    Scaffold(topBar = { MyTopAppBar()},
        floatingActionButton = { MyFAB() }) {


        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
        ) {


            LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(1), state = listState) {

                Image(imageVector = R.drawable.keepnotes, contentDescription = "",modifier = Modifier.graphicsLayer {
                    scrolled += lazyListState.firstVisibleItemScrollOffset -
                            previousOffset
                    translationY = scrolled * 0.5f
                    previousOffset = lazyListState.firstVisibleItemScrollOffset
                } )
                items(getOpinions.size) { index ->
                    Card(
                        modifier = Modifier
                            .padding(6.dp)
                            .graphicsLayer {
                                this.scaleX = if (index == selected) 1.05f else 1f
                                this.scaleY = if (index == selected) 1.05f else 1f
                            }
                            .clickable {
                                if (selected == index) {
                                    selected = -1
                                } else {
                                    selected = index
                                }
                            }
                        ,
                        elevation = CardDefaults.cardElevation(6.dp),
                        colors = CardDefaults.cardColors(Purple80),
                    ) {
                        Text(
                            text = getOpinions[index],
                            fontSize = 16.sp,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 8.dp))
                    }
                }
            }


        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar() {
    val value by rememberInfiniteTransition().animateFloat(
        initialValue = 25f, targetValue = -25f, animationSpec = infiniteRepeatable(
            animation = tween(
            durationMillis = 600,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    TopAppBar(
        title = { Text(text = "KeepNotes") },

        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Back"
                )
            }
        },
        actions = {

            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "",
            modifier = Modifier
                .graphicsLayer(
                    transformOrigin = TransformOrigin(
                        pivotFractionX = 0.5f,
                        pivotFractionY = 0.0f,
                    ),
                    rotationZ = value
                )
            )
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Back"
                )
            }


        }
    )
}

@Composable
fun MyFAB(){
    FloatingActionButton(onClick = {}, containerColor = Purple40) {
        Icon(imageVector = Icons.Filled.Create, contentDescription = " ", tint = Color.White )
    }
}



