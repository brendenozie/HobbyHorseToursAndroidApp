package ke.co.tulivuapps.hobbyhorsetours.composebase.data

import ke.co.tulivuapps.hobbyhorsetours.composebase.R
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.HomeScreenItems
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.Item
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.Tweet

object DemoDataProvider {

    val itemList = listOf(
        Item(
            1,
            "Fresh Vegges and Greens",
            "Very awesome list item has very awesome subtitle. This is bit long",
            R.drawable.coffee
        ),
        Item(
            2,
            "Best blue berries",
            "Very awesome list item has very awesome subtitle. This is bit long",
            R.drawable.coffee
        ),
        Item(
            3,
            "Cherries La Bloom",
            "Very awesome list item has very awesome subtitle. This is bit long",
            R.drawable.coffee
        ),
        Item(
            4,
            "Fruits everyday",
            "Very awesome list item has very awesome subtitle. This is bit long",
            R.drawable.coffee
        ),
        Item(
            5,
            "Sweet and sour",
            "Very awesome list item has very awesome subtitle. This is bit long",
            R.drawable.coffee
        ),
        Item(
            6,
            "Pancakes for everyone",
            "Very awesome list item has very awesome subtitle. This is bit long",
            R.drawable.coffee
        ),
        Item(
            7,
            "Cupcakes and sparkle",
            "Very awesome list item has very awesome subtitle. This is bit long",
            R.drawable.coffee
        ),
        Item(
            8,
            "Best Burgers shop",
            "Very awesome list item has very awesome subtitle. This is bit long",
            R.drawable.coffee
        ),
        Item(
            9,
            "Coffee of India",
            "Very awesome list item has very awesome subtitle. This is bit long",
            R.drawable.coffee
        ),
        Item(
            10,
            "Pizza boy town",
            "Very awesome list item has very awesome subtitle. This is bit long",
            R.drawable.coffee
        ),
        Item(
            11,
            "Burgers and Chips",
            "Very awesome list item has very awesome subtitle. This is bit long",
            R.drawable.coffee
        ),
        Item(
            12,
            "Breads and butter",
            "Very awesome list item has very awesome subtitle. This is bit long",
            R.drawable.coffee
        ),
        Item(
            13,
            "Cupcake factory",
            "Very awesome list item has very awesome subtitle. This is bit long",
            R.drawable.coffee
        ),
        Item(
            14,
            "Breakfast paradise",
            "Very awesome list item has very awesome subtitle. This is bit long",
            R.drawable.coffee
        ),
        Item(
            15,
            "Cake and Bake",
            "Very awesome list item has very awesome subtitle. This is bit long",
            R.drawable.coffee
        ),
        Item(
            16,
            "Brunch and Stakes",
            "Very awesome list item has very awesome subtitle. This is bit long",
            R.drawable.coffee
        )
    )

    val item = Item(
        1,
        "Awesome List Item",
        "Very awesome list item has very awesome subtitle. This is bit long",
        R.drawable.coffee
    )

    val tweet = Tweet(
        1,
        "Jetpack compose is the next thing for andorid. Declarative UI is the way to go for all screens.",
        "The Verge",
        "@verge",
        "12m",
        R.drawable.coffee,
        100,
        12,
        15,
        "Twitter for web"
    )

    val tweetList = listOf(
        tweet,
        tweet.copy(
            id = 2,
            author = "Google",
            handle = "@google",
            authorImageId = R.drawable.coffee,
            tweetImageId = R.drawable.coffee,
            time = "11m"
        ),
        tweet.copy(
            id = 3,
            author = "Amazon",
            handle = "@Amazon",
            authorImageId = R.drawable.coffee,
            time = "1h"
        ),
        tweet.copy(
            id = 4,
            author = "Facebook",
            handle = "@Facebook",
            authorImageId = R.drawable.coffee,
            time = "1h"
        ),
        tweet.copy(
            id = 5,
            author = "Instagram",
            handle = "@Instagram",
            authorImageId = R.drawable.coffee,
            tweetImageId = R.drawable.coffee,
            time = "11m"
        ),
        tweet.copy(
            id = 6,
            author = "Twitter",
            handle = "@Twitter",
            authorImageId = R.drawable.coffee,
            tweetImageId = R.drawable.coffee,
            time = "11m"
        ),
        tweet.copy(
            id = 7,
            author = "Netflix",
            handle = "@Netflix",
            authorImageId = R.drawable.coffee,
            tweetImageId = R.drawable.coffee,
            time = "11m"
        ),
        tweet.copy(
            id = 8,
            author = "Tesla",
            handle = "@Tesla",
            authorImageId = R.drawable.coffee,
            time = "1h"
        ),
        tweet.copy(
            id = 9,
            author = "Microsoft",
            handle = "@Microsoft",
            authorImageId = R.drawable.coffee,
            time = "1h"
        ),
        tweet.copy(
            id = 3,
            author = "Tencent",
            handle = "@Tencent",
            authorImageId = R.drawable.coffee,
            time = "1h"
        ),
        tweet.copy(
            id = 4,
            author = "Snapchat",
            handle = "@Snapchat",
            authorImageId = R.drawable.coffee,
            time = "1h"
        ),
        tweet.copy(
            id = 5,
            author = "Snapchat",
            handle = "@Snapchat",
            authorImageId = R.drawable.coffee,
            tweetImageId = R.drawable.coffee,
            time = "11m"
        ),
        tweet.copy(
            id = 6,
            author = "Tiktok",
            handle = "@Tiktok",
            authorImageId = R.drawable.coffee,
            tweetImageId = R.drawable.coffee,
            time = "11m"
        ),
        tweet.copy(
            id = 7,
            author = "Samsung",
            handle = "@Samsung",
            authorImageId = R.drawable.coffee,
            tweetImageId = R.drawable.coffee,
            time = "11m"
        ),
        tweet.copy(
            id = 8,
            author = "Youtube",
            handle = "@Youtube",
            authorImageId = R.drawable.coffee,
            time = "1h"
        ),
        tweet.copy(
            id = 9,
            author = "Gmail",
            handle = "@Gmail",
            authorImageId = R.drawable.coffee,
            time = "1h"
        ),
        tweet.copy(
            id = 3,
            author = "Android",
            handle = "@Android",
            authorImageId = R.drawable.coffee,
            time = "1h"
        ),
        tweet.copy(
            id = 4,
            author = "Whatsapp",
            handle = "@Whatsapp",
            authorImageId = R.drawable.coffee,
            time = "1h"
        ),
        tweet.copy(
            id = 5,
            author = "Telegram",
            handle = "@Telegram",
            authorImageId = R.drawable.coffee,
            tweetImageId = R.drawable.coffee,
            time = "11m"
        ),
        tweet.copy(
            id = 6,
            author = "Spotify",
            handle = "@Spotify",
            authorImageId = R.drawable.coffee,
            tweetImageId = R.drawable.coffee,
            time = "11m"
        ),
        tweet.copy(
            id = 7,
            author = "WeChat",
            handle = "@WeChat",
            authorImageId = R.drawable.coffee,
            tweetImageId = R.drawable.coffee,
            time = "11m"
        ),
        tweet.copy(
            id = 8,
            author = "Airbnb",
            handle = "@Airbnb",
            authorImageId = R.drawable.coffee,
            time = "1h"
        ),
        tweet.copy(
            id = 9,
            author = "LinkedIn",
            handle = "@LinkedIn",
            authorImageId = R.drawable.coffee,
            time = "1h"
        ),
        tweet.copy(
            id = 6,
            author = "Shazam",
            handle = "@Shazam",
            authorImageId = R.drawable.coffee,
            tweetImageId = R.drawable.coffee,
            time = "11m"
        ),
        tweet.copy(
            id = 7,
            author = "Chrome",
            handle = "@Chrome",
            authorImageId = R.drawable.coffee,
            tweetImageId = R.drawable.coffee,
            time = "11m"
        ),
        tweet.copy(
            id = 6,
            author = "Safari",
            handle = "@Safari",
            authorImageId = R.drawable.coffee,
            tweetImageId = R.drawable.coffee,
            time = "11m"
        ),
        tweet.copy(
            id = 7,
            author = "Disney",
            handle = "@Disney",
            authorImageId = R.drawable.coffee,
            tweetImageId = R.drawable.coffee,
            time = "11m"
        )


    )

    val homeScreenListItems = listOf(
        HomeScreenItems.ListView("Vertical"),
        HomeScreenItems.ListView("Horizontal"),
        HomeScreenItems.ListView("Grid"),
        HomeScreenItems.Modifiers,
        HomeScreenItems.Layouts,
        HomeScreenItems.ConstraintsLayout,
        HomeScreenItems.MotionLayout,
        HomeScreenItems.AdvanceLists,
        HomeScreenItems.CustomFling,
        HomeScreenItems.AndroidViews,
        HomeScreenItems.Carousel,
        HomeScreenItems.Dialogs,
        HomeScreenItems.TabLayout,
        HomeScreenItems.BottomSheets,
        HomeScreenItems.PullRefresh,
    )

    const val longText =
        "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae"

    val demoUiList = listOf(
        "Instagram",
        "Twitter",
        "Gmail",
        "Youtube",
        "Spotify",
        "CryptoApp+MVVM",
        "MoviesApp+MVVM",
        "DatingApp",
        "TikTok",
        "Paint",
        "Meditation"
    )

}