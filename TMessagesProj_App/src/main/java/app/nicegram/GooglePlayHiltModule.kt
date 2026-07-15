package app.nicegram

import android.content.Context
import android.content.SharedPreferences
import com.appvillis.core_network.ApiService
import com.appvillis.core_domain.repository.user.UserRepository
import com.appvillis.core_domain.usecase.openrouter.UpdateOpenRouterBalanceUseCase
import com.appvillis.core_domain.usecase.user.RefreshUserInfoUseCase
import com.appvillis.core_markets.MarketFeatureFlagsProvider
import com.appvillis.core_analytics.AnalyticsManager
import com.appvillis.feature_nicegram_billing.data.BillingManagerImpl
import com.appvillis.core_domain.BillingManager
import com.appvillis.feature_nicegram_billing.domain.PurchaseSync
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GooglePlayHiltModule {

    @Provides
    @Singleton
    fun provideBillingManger(
        @ApplicationContext context: Context,
        apiService: ApiService,
        userRepository: UserRepository,
        refreshUserInfoUseCase: RefreshUserInfoUseCase,
        purchaseSync: PurchaseSync,
        analyticsManager: AnalyticsManager,
        updateOpenRouterBalanceUseCase: UpdateOpenRouterBalanceUseCase
    ): BillingManager =
        BillingManagerImpl(
            context = context,
            appCoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO),
            apiService = apiService,
            userRepository = userRepository,
            refreshUserInfoUseCase = refreshUserInfoUseCase,
            purchaseSync = purchaseSync,
            analyticsManager = analyticsManager,
            updateOpenRouterBalanceUseCase = updateOpenRouterBalanceUseCase
        )

    @Provides
    @Singleton
    fun provideMarketFeatureFlagsProvider(): MarketFeatureFlagsProvider = object : MarketFeatureFlagsProvider {
        override val canShowAds = true
    }
}
