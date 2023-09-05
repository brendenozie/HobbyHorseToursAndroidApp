package ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.utils

/**
 * Created by brendenozie on 10.03.2023
 */
object Constants {
    const val BASE_URL = "https://HobbyHorseToursapi.com/api/"
    const val BASE_TEST_URL = "http://192.168.228.118:3000/api/"
    const val CHARACTER_LIST = "character"
    const val GET_CHARACTER = "character/{id}"
    const val CHARACTER_FILTER = "character/"
    const val EPISODE_LIST = "episode"
    const val GET_EPISODE = "episode/{id}"

    const val HOTEL_LIST = "get-hotels"
    const val GET_HOTEL = "get-hotels/{id}"
    const val HOTEL_FILTER = "get-hotels/"

    const val DESTINATION_LIST = "get-destinations"
    const val GET_DESTINATION = "get-destinations/{id}"
    const val DESTINATION_FILTER = "get-destinations/"

    //Query Parameter
    const val PARAM_PAGE = "page"
    const val PARAM_ID = "id"
    const val PARAM_NAME = "name"
    const val PARAM_STATUS = "status"
    const val PARAM_GENDER = "gender"

    const val DATABASE_NAME = "hobbyhorsedb"

    const val TABLE_NAME = "favorite"
    const val COLUMN_ID = "id"
    const val COLUMN_NAME = "name"
    const val COLUMN_IMAGE_URL = "image_url"
    const val COLUMN_CREATED = "created"
    const val PREF_ORIGIN = "origin_"
    const val PREF_LOCATION = "location_"
    const val COLUMN_STATUS = "status"
    const val COLUMN_SPECIES = "species"
    const val COLUMN_GENDER = "gender"
    const val COLUMN_TYPE = "type"
    const val COLUMN_URL = "url"
    const val COLUMN_EPISODE = "episode"

    const val HOTEL_TABLE_NAME = "hotel"

    const val COLUMN_TRAVEL_STYLE_ID = "travelStyleId"

    const val DESTINATION_TABLE_NAME = "destination"

    const val COLUMN_LOCAL_ID = "localId"
    const val COLUMN_TITLE = "title"
    const val COLUMN_DESCRIPTION = "description"
    const val COLUMN_STAR = "star"
    const val COLUMN_IMG = "img"
    const val COLUMN_LAT = "lat"
    const val COLUMN_LOCATION = "location"
    const val COLUMN_LANG = "lang"
    const val COLUMN_PRICE = "price"
    const val COLUMN_OFFER = "offer"
    const val COLUMN_OFFER_PRICE = "offerPrice"
    const val COLUMN_USER_EMAIL = "userEmail"
    const val COLUMN_CITY_ID = "cityId"
    const val COLUMN_CREATED_AT = "createdAt"


    const val CONTENT_LENGTH = 250_000L
}