package com.example.composedemo.home.domain.useCases

import com.example.composedemo.home.data.dto.toCharacter
import com.example.composedemo.home.domain.HomeRepository
import com.example.composedemo.home.domain.model.Character
import com.example.composedemo.utils.Resource
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.UnknownHostException

/**
 * Created by  Ajeet Singh on Date: 09/08/25.
 */
class GetUniversitiesUseCase(private val homeRepository: HomeRepository) {

    operator fun invoke(): Flow<Resource<List<Character>>> = flow {
        try {
            emit(Resource.Loading)
            val characters = homeRepository.loadCharacters().charactersDto.map { it.toCharacter() }
            delay(2000)
            emit(Resource.Success(characters))
        } catch (e: RedirectResponseException) {
            //3xx codes
            emit(Resource.Error(e.response.status.description, e.response.status.value))
        }catch (e: ClientRequestException) {
            //4xx codes
            emit(Resource.Error(e.response.status.description, e.response.status.value))
        } catch (e: ServerResponseException) {
            //5xx codes
            emit(Resource.Error(e.response.status.description, e.response.status.value))
        }catch (e: UnknownHostException) {
            emit(Resource.Error("No Internet, Try again when internet back.", 0))
        }catch (e: Exception) {
            emit(Resource.Error("Exception : ${e.message}", 0))
        }
    }

}