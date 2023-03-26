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
                this@mapEntityToDomain.sprites.backDefault?.let {
                    this.add(it)
                }
                this@mapEntityToDomain.sprites.backFemale?.let {
                    this.add(it)
                }
                this@mapEntityToDomain.sprites.backShiny?.let {
                    this.add(it)
                }
                this@mapEntityToDomain.sprites.backShinyFemale?.let {
                    this.add(it)
                }
                this@mapEntityToDomain.sprites.frontDefault?.let {
                    this.add(it)
                }
                this@mapEntityToDomain.sprites.frontFemale?.let {
                    this.add(it)
                }
                this@mapEntityToDomain.sprites.frontShiny?.let {
                    this.add(it)
                }
                this@mapEntityToDomain.sprites.frontShinyFemale?.let {
                    this.add(it)
                }
            },
            mutableListOf<Stat>().apply {
                this@mapEntityToDomain.stats.forEach {
                    this.add(Stat(it.stat?.name.orEmpty(), it.baseStat.toString()))
                }
            }
        )
}