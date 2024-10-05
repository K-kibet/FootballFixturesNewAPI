package com.codesui.footballfixtures.Requests

import com.google.gson.JsonObject
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.QueryMap

interface ApiService {
    @GET("fixtures")
    suspend fun getFixtures(
        @HeaderMap headers: Map<String, String>,
        @QueryMap params: Map<String, String>
    ): JsonObject

    @GET("fixtures/lineups")
    suspend fun getLineups(
        @HeaderMap headers: Map<String, String>,
        @QueryMap params: Map<String, String>
    ): JsonObject

    @GET("leagues")
    suspend fun getLeagues(
        @HeaderMap headers: Map<String, String>
    ): JsonObject

    @GET("standings")
    suspend fun getStandings(
        @HeaderMap headers: Map<String, String>,
        @QueryMap params: Map<String, String>
    ): JsonObject


    @GET("players/topscorers")
    suspend fun getTopScorers(
        @HeaderMap headers: Map<String, String>,
        @QueryMap params: Map<String, String>
    ): JsonObject

    @GET("players")
    suspend fun getPlayer(
        @HeaderMap headers: Map<String, String>,
        @QueryMap params: Map<String, String>
    ): JsonObject
}