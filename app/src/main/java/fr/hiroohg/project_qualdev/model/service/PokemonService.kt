package fr.hiroohg.project_qualdev.model.service

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import fr.hiroohg.project_qualdev.model.entities.PokeApiEntity
import fr.hiroohg.project_qualdev.model.entities.Pokemon
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://raw.githubusercontent.com/Josstoh/res508-qualite-dev-android/main/rest/"

// global object Moshi package for Json converter
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

// Retrofit package like http or Dio
// specified for joss json file
private val retrofit =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()


// Service non instantiable
// Injected in the static object
interface PokemonService {
  @GET("pokemons.json")
  suspend fun getPokemons(): List<Pokemon>
}

// static object injecting a pokemon service
object PokemonsApi {
  val service: PokemonService by lazy { retrofit.create(PokemonService::class.java) }
}