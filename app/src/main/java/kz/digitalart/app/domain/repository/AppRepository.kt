package kz.digitalart.app.domain.repository

import kz.digitalart.app.domain.model.Exhibit

interface AppRepository {
    suspend fun getExhibits(): List<Exhibit>
}