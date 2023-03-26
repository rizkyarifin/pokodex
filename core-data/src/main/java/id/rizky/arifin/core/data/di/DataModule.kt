package id.rizky.arifin.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.rizky.arifin.core.data.repository.DetailRepository
import id.rizky.arifin.core.data.repository.DetailRepositoryImpl
import id.rizky.arifin.core.data.repository.HomeRepository
import id.rizky.arifin.core.data.repository.HomeRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {
    @Binds
    fun bindsHomeRepository(
        homeRepositoryImpl: HomeRepositoryImpl
    ): HomeRepository

    @Binds
    fun bindsDetailRepository(
        detailRepository: DetailRepositoryImpl
    ): DetailRepository
}