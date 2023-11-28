package com.example.keepnotes_pps

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.keepnotes_pps.ui.theme.Pastel
import com.example.keepnotes_pps.ui.theme.Purple40
import com.example.keepnotes_pps.ui.theme.Purple80
import java.util.Collections.rotate

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun Portada(){
    val lazyListState = rememberLazyListState()
    var scrolled = 0f
    var previousOffset = 0
    var selected by remember{ mutableStateOf(-1) }
    val getOpinions =  listOf<String>("Servicio algo flojo, aún así lo recomiendo","Céntrica y acogedora. Volveremos seguro", "Céntrica y acogedora. Volveremos seguro", "La comida estaba deliciosa y bastante bien de precio, mucha variedad de platos.\n", "El personal muy amable, nos permitieron ver todo el establecimiento.\n", "Muy bueno",
        "Excelente. Destacable la extensa carta de cafés", "Buen ambiente y buen servicio. Lo recomiendo.", "En días festivos demasiado tiempo de espera. Los camareros/as no dan abasto. No lo recomiendo. No volveré", "Repetiremos. Gran selección de tartas y cafés.", "Todo lo que he probado en la cafetería está riquísimo, dulce o salado.\n",
        "La vajilla muy bonita todo de diseño que en el entorno del bar queda ideal.\n", "Puntos negativos: el servicio es muy lento y los precios son un poco elevados.")


    Scaffold(topBar = { MyTopAppBar()},
        floatingActionButton = { MyFAB() }) {


        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(top = it.calculateTopPadding())
        ) {

            LazyColumn( state = lazyListState) {
                item {
                    Image(painter = painterResource(id = R.drawable.keepnotes), contentDescription ="keep",
                    modifier = Modifier
                        .graphicsLayer {
                            scrolled += lazyListState.firstVisibleItemScrollOffset -
                                    previousOffset
                            translationY = scrolled * 0.5f
                            previousOffset = lazyListState.firstVisibleItemScrollOffset
                        }
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop)
                }
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
                        colors = CardDefaults.cardColors(Pastel),
                    ) {
                        Column() {
                            Text(
                                text = getOpinions[index],
                                fontSize = 16.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier
                                    .padding(start = 8.dp, top = 8.dp, bottom = 8.dp))
                        }

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

Box(modifier = Modifier.fillMaxWidth()) {

    Image(painter = painterResource(id = R.drawable.keepnotes),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        contentScale = ContentScale.Crop
    )

    TopAppBar(

        title = { Text(text = "KeepNotes", color = Color.White) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.Transparent),
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    tint = Color.White,
                    contentDescription = "Back"
                )
            }
        },
        actions = {

            Icon(
                imageVector = Icons.Outlined.Notifications,
                tint = Color.White,
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
                    tint = Color.White,
                    contentDescription = "Back"
                )
            }
        }
    )
}
}

@Composable
fun MyFAB(){
    val value by rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            )
        )
    )

    val colors = listOf(
        Color(0xFF405DE6),
        Color(0xFFC13584),
        Color(0xFFFD1D1D),
        Color(0xFFFFDC80)
    )
    var gradientBrush by remember {
        mutableStateOf(
            Brush.horizontalGradient(
                colors = colors,
                startX = -10.0f,
                endX = 400.0f,
                tileMode = TileMode.Repeated
            )
        )
    }
    Box(modifier = Modifier
        .drawBehind {
            rotate(value) {
                drawCircle(
                    gradientBrush, style = Stroke(width = 12.dp.value)
                )
            }
        }
        .size(57.dp)
    ){
        FloatingActionButton(onClick = {}, containerColor = Purple40, shape = CircleShape) {
            Icon(imageVector = Icons.Filled.Create, contentDescription = " ", tint = Color.White )
        }
    }
}



