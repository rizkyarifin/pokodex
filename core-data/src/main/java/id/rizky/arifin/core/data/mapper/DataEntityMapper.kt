package id.rizky.arifin.core.data.mapper

import id.rizky.arifin.core.model.PokemonDetail
import id.rizky.arifin.core.model.Stat
import id.rizky.arifin.core.network.model.mapper.PokemonDetailResponse

object DataEntityMapper {

    fun PokemonDetailResponse.mapEntityToDomain(): PokemonDetail =
        PokemonDetail(
            this.id,
            this.name,
            this.height,
            this.weight,
            mutableListOf<String>().apply {
                this@mapEntityToDomain.abilities.forEach {
                    this.add(it.ability.name)
                }
            }.toList(),
            mutableListOf<String>().apply {
                this@mapEntityToDomain.types.forEach {
                    this.add(it.type.name)
                }
            },
            mutableListOf<String>().apply {
                this.add(this@mapEntityToDomain.sprites.backDefault.orEmpty())
                this.add(this@mapEntityToDomain.sprites.backFemale.orEmpty())
                this.add(this@mapEntityToDomain.sprites.backShiny.orEmpty())
                this.add(this@mapEntityToDomain.sprites.backShinyFemale.orEmpty())
                this.add(this@mapEntityToDomain.sprites.frontDefault.orEmpty())
                this.add(this@mapEntityToDomain.sprites.frontFemale.orEmpty())
                this.add(this@mapEntityToDomain.sprites.frontShiny.orEmpty())
                this.add(this@mapEntityToDomain.sprites.frontShinyFemale.orEmpty())
            },
            mutableListOf<Stat>().apply {
                this@mapEntityToDomain.stats.forEach {
                    this.add(Stat(it.stat?.name.orEmpty(), it.stat?.name.orEmpty()))
                }
            }
        )
}