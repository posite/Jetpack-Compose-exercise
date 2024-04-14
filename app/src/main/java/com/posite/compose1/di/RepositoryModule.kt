package com.posite.compose1.di

import com.posite.compose1.data.datasource.test.TestDataSource
import com.posite.compose1.data.repository.test.TestRepositoryImpl
import com.posite.compose1.domain.repository.test.TestRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideTestRepository(userInfoDataSource: TestDataSource): TestRepository =
        TestRepositoryImpl(userInfoDataSource)
}