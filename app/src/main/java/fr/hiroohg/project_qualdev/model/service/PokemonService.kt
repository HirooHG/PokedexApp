package fr.hiroohg.project_qualdev.model.service

import fr.hiroohg.project_qualdev.model.model.Pokemon
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.Retrofit
import retrofit2.http.GET
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

private const val BASE_URL = "https://raw.githubusercontent.com/Josstoh/res508-qualite-dev-android/main/rest/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
   .addConverterFactory(MoshiConverterFactory.create(moshi))
   .baseUrl(BASE_URL)
   .build()

interface PokemonService {
  @GET("pokemons.json") suspend fun getPokemons(): List<Pokemon>
}

object PokemonApi {
  val retrofitService: PokemonService by lazy { retrofit.create(PokemonService::class.java) }
}
