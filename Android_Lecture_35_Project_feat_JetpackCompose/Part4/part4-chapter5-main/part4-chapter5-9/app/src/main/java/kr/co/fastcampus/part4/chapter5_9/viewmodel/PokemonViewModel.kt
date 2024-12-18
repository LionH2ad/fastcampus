package kr.co.fastcampus.part4.chapter5_9.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kr.co.fastcampus.part4.chapter5_9.PokeAPI
import kr.co.fastcampus.part4.chapter5_9.Response
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kr.co.fastcampus.part4.chapter5_9.PokemonResponse
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val pokeAPI: PokeAPI
) : ViewModel() {

    val pokemonList: Flow<PagingData<Response.Result>> = getPokemons().cachedIn(viewModelScope)
    var pokemonResult by mutableStateOf(
        PokemonResponse(
            PokemonResponse.Species(""),
            PokemonResponse.Sprites("")
        )
    )

    // Pager를 사용하고 있고 config 와 를 pagingSourceFactory사용하고 있다
    private fun getPokemons(): Flow<PagingData<Response.Result>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = true,
            prefetchDistance = 5
        ),
        pagingSourceFactory = {
            object : PagingSource<Int, Response.Result>() {
                override fun getRefreshKey(state: PagingState<Int, Response.Result>): Int? {
                    return state.anchorPosition
                }

                override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Response.Result> {
                    try {
                        val pokemons = if (params.key != null) {
                            pokeAPI.getPokemons(params.key as Int, params.loadSize)
                        } else {
                            pokeAPI.getPokemons()
                        }
                        // 단계 2: `offset=20&limit=20` 형태의 주소에서
                        // `prevKey`와 `nextKey`를 만들어 전달하자.
                        return LoadResult.Page(
                            data = pokemons.results, // 현재 페이지 데이터
                            prevKey = pokemons.previous?.substringAfter("offset=")
                                ?.substringBefore("&")?.toInt(), // 이전 페이지로 가기 위한 데이터
                            nextKey = pokemons.next?.substringAfter("offset=")
                                ?.substringBefore("&")?.toInt() // 다음 페이지로 가기 위한 데이터
                        )
                    } catch (e: Exception) {
                        Log.e("EEE", "error: $e")
                        e.printStackTrace()
                        return LoadResult.Error(e)
                    }
                }
            }
        }
    ).flow // flow 형태로 변경

    fun getPokemon(pokemonId: Int) {
        viewModelScope.launch {
            pokemonResult = pokeAPI.getPokemon(pokemonId)
        }
    }
}