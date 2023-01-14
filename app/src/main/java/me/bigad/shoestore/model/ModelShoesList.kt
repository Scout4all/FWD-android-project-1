package me.bigad.shoestore.model

import androidx.lifecycle.MutableLiveData

class ModelShoesList {
    val shoesList = MutableLiveData<List<Shoe>>()


    init {
        val list = listOf<Shoe>(
            Shoe(
                "Nature Three Mahogany Leather",
                listOf(08.00, 09.00, 09.50, 10.00, 10.30),
                "Clarks",
                "This classic silhouette last is brought right up-to-date with the addition of state-of-the-art breathable Active Air underfoot cushioning. The last word in comfort is complemented by a rich leather upper and sheepskin lining. A rubber sole adds grip and durability."
            ),
            Shoe(
                "Court Lite Wally Dark Olive Suede",
                listOf(08.00, 09.00, 09.50, 10.00, 10.30),
                "Clarks",
                "Our instantly recognisable Wallabee silhouette gets a sports injection with an athletic-inspired sole for casual style. This super-lightweight profile is built on the original Wallabee last for a feel-good fit, boosted with innovative cushioning for 21st century comfort.\n" +
                        "Responsibly sourced premium dark olive suede upper\n" +
                        "Innovative MI-X technology combines a high rebound, lightweight part-recycled EVA midsole with a multi-density, contoured, breathable footbed for ultimate support\n" +
                        "Footbed is removable for customisable comfort\n" +
                        "Durable crepe-effect part-recycled rubber sole gives grip"
            ),
            Shoe(
                "Court Lite Wally Dark Olive Suede",
                listOf(08.00, 09.00, 09.50, 10.00, 10.30),
                "Clarks",
                "Our instantly recognisable Wallabee silhouette gets a sports injection with an athletic-inspired sole for casual style. This super-lightweight profile is built on the original Wallabee last for a feel-good fit, boosted with innovative cushioning for 21st century comfort.\n" +
                        "Responsibly sourced premium dark olive suede upper\n" +
                        "Innovative MI-X technology combines a high rebound, lightweight part-recycled EVA midsole with a multi-density, contoured, breathable footbed for ultimate support\n" +
                        "Footbed is removable for customisable comfort\n" +
                        "Durable crepe-effect part-recycled rubber sole gives grip"
            ),
            Shoe(
                "Court Lite Wally Dark Olive Suede",
                listOf(08.00, 09.00, 09.50, 10.00, 10.30),
                "Clarks",
                "Our instantly recognisable Wallabee silhouette gets a sports injection with an athletic-inspired sole for casual style. This super-lightweight profile is built on the original Wallabee last for a feel-good fit, boosted with innovative cushioning for 21st century comfort.\n" +
                        "Responsibly sourced premium dark olive suede upper\n" +
                        "Innovative MI-X technology combines a high rebound, lightweight part-recycled EVA midsole with a multi-density, contoured, breathable footbed for ultimate support\n" +
                        "Footbed is removable for customisable comfort\n" +
                        "Durable crepe-effect part-recycled rubber sole gives grip"
            ),
            Shoe(
                "Court Lite Wally Dark Olive Suede",
                listOf(08.00, 09.00, 09.50, 10.00, 10.30),
                "Clarks",
                "Our instantly recognisable Wallabee silhouette gets a sports injection with an athletic-inspired sole for casual style. This super-lightweight profile is built on the original Wallabee last for a feel-good fit, boosted with innovative cushioning for 21st century comfort.\n" +
                        "Responsibly sourced premium dark olive suede upper\n" +
                        "Innovative MI-X technology combines a high rebound, lightweight part-recycled EVA midsole with a multi-density, contoured, breathable footbed for ultimate support\n" +
                        "Footbed is removable for customisable comfort\n" +
                        "Durable crepe-effect part-recycled rubber sole gives grip"
            ),
            Shoe(
                "Court Lite Wally Dark Olive Suede",
                listOf(08.00, 09.00, 09.50, 10.00, 10.30),
                "Clarks",
                "Our instantly recognisable Wallabee silhouette gets a sports injection with an athletic-inspired sole for casual style. This super-lightweight profile is built on the original Wallabee last for a feel-good fit, boosted with innovative cushioning for 21st century comfort.\n" +
                        "Responsibly sourced premium dark olive suede upper\n" +
                        "Innovative MI-X technology combines a high rebound, lightweight part-recycled EVA midsole with a multi-density, contoured, breathable footbed for ultimate support\n" +
                        "Footbed is removable for customisable comfort\n" +
                        "Durable crepe-effect part-recycled rubber sole gives grip"
            ),
            Shoe(
                "Court Lite Wally Dark Olive Suede",
                listOf(08.00, 09.00, 09.50, 10.00, 10.30),
                "Clarks",
                "Our instantly recognisable Wallabee silhouette gets a sports injection with an athletic-inspired sole for casual style. This super-lightweight profile is built on the original Wallabee last for a feel-good fit, boosted with innovative cushioning for 21st century comfort.\n" +
                        "Responsibly sourced premium dark olive suede upper\n" +
                        "Innovative MI-X technology combines a high rebound, lightweight part-recycled EVA midsole with a multi-density, contoured, breathable footbed for ultimate support\n" +
                        "Footbed is removable for customisable comfort\n" +
                        "Durable crepe-effect part-recycled rubber sole gives grip"
            ),

            )
        shoesList.value = list
    }

    companion object {
        @Volatile
        var INSTANCE: ModelShoesList? = null
        fun getInstance(): ModelShoesList {
            return INSTANCE ?: synchronized(this) {
                val instance = ModelShoesList()
                INSTANCE = instance
                instance
            }
        }

    }

}