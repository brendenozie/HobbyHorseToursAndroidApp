package ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.extension

import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.*
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.CharacterDto
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.DestinationDto
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.HotelDto
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.LocationDto

/**
 * Created by brendenozie on 27.03.2023
 */

fun ResultHotel.toHotelDto() = HotelDto(
    localId,
    id,
    title,
    description,
    star,
    lat,
    location,
    long,
    price,
    offer,
    offerPrice,
    userEmail,
    cityId,
    createdAt,
    travelStyleId,
    img
)

fun HotelFavoriteEntity.toHotelDto() = HotelDto(
    localId = localId ?: 0,
    id = id.orEmpty(),
    title = title.orEmpty(),
    description = description.orEmpty(),
    star = star.orEmpty(),
    lat = lat.orEmpty(),
    location = location.orEmpty(),
    lang = lang.orEmpty(),
    price = price.orEmpty(),
    offer = offer.orEmpty(),
    offerPrice = offerPrice.orEmpty(),
    userEmail = userEmail.orEmpty(),
    cityId = cityId.orEmpty(),
    createdAt = createdAt.orEmpty(),
    travelStyleId = travelStyleId.orEmpty(),
    img = img.orEmpty()
)

fun List<ResultHotel>.toHotelDtoList() = map { it.toHotelDto() }

fun List<HotelFavoriteEntity>.toHotelFavoriteDtoList() = map { it.toHotelDto() }
fun Result.toDestinationDto() = DestinationDto(
    localId,
    id,
    title,
    description,
    star,
    lat,
    location,
    long,
    price,
    offer,
    offerPrice,
    userEmail,
    cityId,
    createdAt,
    img
)

fun DestinationFavoriteEntity.toDestinationDto() = DestinationDto(
    localId = localId ?: 0,
    id = id.orEmpty(),
    title = title.orEmpty(),
    description = description.orEmpty(),
    star = star.orEmpty(),
    lat = lat.orEmpty(),
    location = location.orEmpty(),
    lang = lang.orEmpty(),
    price = price.orEmpty(),
    offer = offer.orEmpty(),
    offerPrice = offerPrice.orEmpty(),
    userEmail = userEmail.orEmpty(),
    cityId = cityId.orEmpty(),
    createdAt = createdAt.orEmpty(),
    img = img.orEmpty()
)

fun List<Result>.toDestinationDtoList() = map { it.toDestinationDto() }

fun List<DestinationFavoriteEntity>.toDestinationFavoriteDtoList() = map { it.toDestinationDto() }

fun ResultCharacter.toCharacterDto() = CharacterDto(
    created,
    episode,
    gender,
    id,
    image,
    location.toLocationDto(),
    name,
    origin.toLocationDto(),
    species,
    status,
    type,
    url
)

fun List<ResultCharacter>.toCharacterDtoList() = map { it.toCharacterDto() }

fun FavoriteEntity.toCharacterDto() = CharacterDto(
    created,
    episode,
    gender,
    id,
    image,
    location?.toLocationDto(),
    name,
    origin?.toLocationDto(),
    species,
    status,
    type,
    url
)

fun LocationEntity.toLocationDto() = LocationDto(
    locationId = url.getIdFromUrl(),
    name = name,
    url = url
)

fun LocationResponse.toLocationDto() = LocationDto(
    locationId = url?.getIdFromUrl() ?: 0,
    name = name.toString(),
    url = url.toString()
)

fun OriginResponse.toLocationDto() = LocationDto(
    locationId = url?.getIdFromUrl() ?: 0,
    name = name.toString(),
    url = url.toString()
)

fun String.getIdFromUrl(): Int = substring(lastIndexOf("/") + 1).toIntOrNull() ?: 0

fun List<FavoriteEntity>.toFavoriteDtoList() = map { it.toCharacterDto() }

fun LocationDto.toLocationDto() = LocationEntity(
    locationId = url.getIdFromUrl() ?: 0,
    name = name,
    url = url
)

fun CharacterDto.toFavoriteEntity() = FavoriteEntity(
    id = id ?: 0,
    name = name.orEmpty(),
    image = image.orEmpty(),
    created = created.orEmpty(),
    origin = origin?.toLocationDto(),
    location = location?.toLocationDto(),
    status = status ?: Status.Unknown,
    species = species.orEmpty(),
    gender = gender.orEmpty(),
    type = type.orEmpty(),
    url = url.orEmpty(),
    episode = episode.orEmpty()
)

fun DestinationDto.toDestinationFavoriteEntity() = DestinationFavoriteEntity(
    localId = localId ?: 0,
    id = id.orEmpty(),
    title = title.orEmpty(),
    description = description.orEmpty(),
    star = star.orEmpty(),
    lat = lat.orEmpty(),
    location = location.orEmpty(),
    lang = lang.orEmpty(),
    price = price.orEmpty(),
    offer = offer.orEmpty(),
    offerPrice = offerPrice.orEmpty(),
    userEmail = userEmail.orEmpty(),
    cityId = cityId.orEmpty(),
    createdAt = createdAt.orEmpty(),
    img = img.orEmpty()
)

fun HotelDto.toHotelFavoriteEntity() = HotelFavoriteEntity(
    localId = localId ?: 0,
    id = id.orEmpty(),
    title = title.orEmpty(),
    description = description.orEmpty(),
    star = star.orEmpty(),
    lat = lat.orEmpty(),
    location = location.orEmpty(),
    lang = lang.orEmpty(),
    price = price.orEmpty(),
    offer = offer.orEmpty(),
    offerPrice = offerPrice.orEmpty(),
    userEmail = userEmail.orEmpty(),
    cityId = cityId.orEmpty(),
    createdAt = createdAt.orEmpty(),
    travelStyleId = travelStyleId.orEmpty(),
    img = img.orEmpty()
)