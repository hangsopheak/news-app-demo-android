package com.rupp.news_app_demo_android.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rupp.news_app_demo_android.data.OnboardingSectionData
import com.rupp.news_app_demo_android.model.OnboardingSection

@Composable
fun OnboardingSection(onboardingSection: OnboardingSection){
    Column(modifier = Modifier.fillMaxWidth()){
        Spacer(modifier = Modifier.size(90.dp))
        Image(painter = painterResource(id = onboardingSection.imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 0.dp),
            alignment = Alignment.Center
        )

        Spacer(modifier = Modifier.size(80.dp))

        Text(
            text = onboardingSection.title,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(60.dp))

        Text(
            text = onboardingSection.description,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(15.dp, 0.dp)
        )

        Spacer(modifier = Modifier.size(50.dp))


    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingSectionPreview1(){
    OnboardingSection(OnboardingSectionData.getMainOnboardingSections()[0])
}

@Preview(showBackground = true)
@Composable
fun OnboardingSectionPreview2(){
    OnboardingSection(OnboardingSectionData.getMainOnboardingSections()[1])
}

@Preview(showBackground = true)
@Composable
fun OnboardingSectionPreview3(){
    OnboardingSection(OnboardingSectionData.getMainOnboardingSections()[2])
}
