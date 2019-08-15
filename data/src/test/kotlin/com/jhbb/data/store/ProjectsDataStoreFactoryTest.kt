package com.jhbb.data.store

import com.nhaarman.mockito_kotlin.mock
import org.junit.Assert.*
import org.junit.Test

class ProjectsDataStoreFactoryTest {

    private val cacheStore = mock<ProjectsCacheDataStore>()
    private val remoteStore = mock<ProjectsRemoteDataStore>()
    private val factory = ProjectsDataStoreFactory(cacheStore, remoteStore)

    @Test
    fun getDataStoreReturnsRemoteStoreWhenCacheIsExpired() {
        assertEquals(remoteStore, factory.getDataStore(projectsCached = true, cacheExpired = true))
    }

    @Test
    fun getDataStoreReturnsRemoteStoreWhenProjectsNotCached() {
        assertEquals(remoteStore, factory.getDataStore(projectsCached = false, cacheExpired = false))
    }

    @Test
    fun getDataStoreReturnsCacheStore() {
        assertEquals(cacheStore, factory.getDataStore(projectsCached = true, cacheExpired = false))
    }

    @Test
    fun getCacheDataStoreReturnsCacheStore() {
        assertEquals(cacheStore, factory.getCacheDataStore())
    }
}