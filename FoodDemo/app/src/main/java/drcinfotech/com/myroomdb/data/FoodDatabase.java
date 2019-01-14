package drcinfotech.com.myroomdb.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.VisibleForTesting;
import android.util.Log;

import drcinfotech.com.myroomdb.data.dao.FoodDAO;
import drcinfotech.com.myroomdb.data.model.Food;

/**
 * Created by harshadpk on 28/01/2018.
 */

@Database(entities = {Food.class},version = 1)
public abstract class FoodDatabase extends RoomDatabase
{

    public abstract FoodDAO foodDAO();

    private static FoodDatabase foodDatabase;


    public static synchronized FoodDatabase getInstance(Context context){
        if (foodDatabase == null)
        {
            foodDatabase = Room.databaseBuilder(context.getApplicationContext(),FoodDatabase.class,"fooddb")
                    .allowMainThreadQueries().build();
            foodDatabase.populateInitialData();
        }
        return foodDatabase;
    }

    @VisibleForTesting
    public static void switchToMemory(Context context)
    {
        foodDatabase =Room.inMemoryDatabaseBuilder(context.getApplicationContext(),FoodDatabase.class).build();
    }

    private void populateInitialData() {
        if (foodDAO().count() == 0) {
            beginTransaction();
            try {
                for (int i = 0; i < NAMES.length; i++)
                {
                    Food food = new Food(NAMES[i],IMAGES[i],DESCRIPTINOS[i]);
                    foodDAO().insert(food);
                }
                setTransactionSuccessful();
                Log.e("TAG","populateInitialData successfully.");
            } finally {
                endTransaction();
            }
        }
    }


    /*Testing Data*/

    public static final String[] NAMES = {"Spinach",
            "Cabbage",
            "Tomato",
            "Potato",
            "Broccoli",
            "Onion",
            "Cauliflower",
            "Pea",
            "Cucumber",
            "Lettuce",
            "Marrow-stem Kale",
            "Celery",
            "Kale",
            "Eggplant",
            "Garden asparagus",
            "Radish",
            "Brussels sprout",
            "Turnip",
            "Bell pepper",
            "Green bean",
            "Zucchini",
            "Garlic",
            "Artichoke",
            "Maize",
            "Okra",
            "Chard",
            "Chili pepper",
            "Leek",
            "Rutabaga",
            "Arugula",
            "Kohlrabi",
            "Watercress",
            "Endive",
            "Parsnip",
            "Herb"
            };

    public static final String[] IMAGES = {"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSuUlQnJVb8RNyuwNCT0gf0HU2P_7PSaLlNcnKLb2MGG9hfn2XKuw",
            "https://fthmb.tqn.com/RKsU3cQXVa8-Lui8L3I9_VRDbH0=/960x0/filters:no_upscale()/Cabbage-GettyImages-683732681-586599443df78ce2c32dd4c3.jpg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRZ72zFEMJbhR4Z-7sJV-Bn9DeRlaebxrt5jKP9-qBf0j0eWdHpoQ",
            "https://cdn.shopify.com/s/files/1/1017/2183/t/2/assets/live-preview-potato.png?11775197369035370987",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQrbIkT5Ssy6YYaf5C8s1KWEAT58RBbYO5mE05JnYXtpKLwWTqsZw",
            "http://cdn.shopify.com/s/files/1/1537/5553/products/00613_15abd93a-e239-45df-acdb-8485b40d546a_grande.jpg?v=1486440965",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTdcZD8MZkpggpFoWiOyhaIixePYo8q2lK8eALxri9pVukkG62FtA",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTYEoq02dyue9CwDJV0tef174LtmFUeH78Ifa37JoCjwYb7DTA7wA",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTdilJYo0FHY0cRLDcN7LZrUU9xk1v4xcJ2OVcIdInNc7UQSm5LpQ",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfm43a9mtKlphgYEZHhpwUAi4p7IXebofpeBxMt9D8wrMhJ7Mu",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSY0ZNDRUBZ6OWBi7KaXTppXGpOHRaKFj2_iKE3UK03fuMRemSm",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRSbCtacOCGeiBPxrKBNPFDL5NjM9ROcZdmgUCKytxDujvnb0Wgeg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR15QQA7MOlCKE8I67dzNhXXHvDanAhIZFojj08dWaWL9uxD09kiw",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTpMQD27IPHe-8JtfGLghvwWHCvm8LHWff-Ab9qzIE9zNE5tKXs",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQaSE0mlzgvBBld0upYpRm6zWBZMtSSahOdC3VSmdy177ikZ2La",
            "https://www.highmowingseeds.com/pub/media/catalog/product/cache/image/675x675/e9c3970ab036de70892d86c6d221abfe/2/8/2870.jpg",
            "http://images.media-allrecipes.com/userphotos/960x960/3863934.jpg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQQd_eoyWRnmH9jmuXz23ayv224KymTnXUmsGoa6jKvZsp4sstAYw",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_nVicTPwGDsSJdD7pCgYSRIJHmcYhHhPWNUdFlgHvdkgy9_6Y",
            "http://images.media-allrecipes.com/userphotos/250x250/2432913.jpg",
            "https://www.simplyrecipes.com/wp-content/uploads/2005/08/roasted-zucchini-garlic-vertical-a.jpg",
            "https://draxe.com/wp-content/uploads/2014/08/Raw-Garlic-Background.jpg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRCD9xS3mJCmSlVKqB70xJ8CoTpAap_OwfUDLQtswRxXyWWdXFw",
            "https://southernafrican.news/wp-content/uploads/2015/11/yellow-maize-1169337.jpg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_2kQ7GfOcgq8iQlV72HQfExqLFX4l09T7FHLnEGtAq2UMymMF",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQL9q3LH2N0YE2DdDLVcSso9LjfMI6OZz9VdPHjHDL1-JWc-gDf",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQlJDOCjyi7N-dKuBOyVy-RcYCMJJeexidzudmMq7mXiwycOCq8_Y0MMg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT8oTMGXDf-1aseudSDRVwpsC-TNsNJ-ZZ4uyne3kHMbSGxlFAgbA",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQGNqs3Zdr9xK6AtKA-HmFoTcxh_SWpcewFyax3Un4Gxiy6c75i0g",
            "http://media.mercola.com/assets/images/foodfacts/arugula-nutrition-facts.jpg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfHBQZ-sZVrZwSC-UUUuXHgCZpxFx9d5H-zG13V72bzhw4d4wSIw",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT1prtgi0GorMoGb3gViivvvmkTnaJtOBL3gdrHKKrsSpdDpHHvPQ",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKhcZbBKJzDGTEWfH1lWpdQArfWa8Qkk2yssRyYAKentuioXhnrmVgQ7A",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQV2OUF6c8Ssn0MlR3QzZjyZwM3O0yAkv0o62q8Ll4BnOdYdgUv",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRSxIg__rwQKXKG1WYZ_N5M1xloMyDq46R8ADpG49zCcKtZKkZgBQ"};

    public static final String[] DESCRIPTINOS   = {"Spinach is an edible flowering plant in the family Amaranthaceae native to central and western Asia. Its leaves are eaten as a vegetable. It is an annual plant growing as tall as 30 cm. Spinach may survive over winter in temperate regions",
            "Cabbage or headed cabbage is a leafy green or purple biennial plant, grown as an annual vegetable crop for its dense-leaved heads",
            "The tomato is the edible, often red, fruit of the plant Solanum lycopersicum, commonly known as a tomato plant. The plant belongs to the nightshade family, Solanaceae. The species originated in western South America",
            "The potato is a starchy, tuberous crop from the perennial nightshade Solanum tuberosum. The word \"potato\" may refer either to the plant itself or to the edible tuber. ",
            "Broccoli is an edible green plant in the cabbage family whose large flowering head is eaten as a vegetable.",
            "The onion, also known as the bulb onion or common onion, is a vegetable that is the most widely cultivated species of the genus Allium. Its close relatives include the garlic, shallot, leek, chive, and Chinese onion.",
            "Cauliflower is one of several vegetables in the species Brassica oleracea in the genus Brassica, which is in the family Brassicaceae. It is an annual plant that reproduces by seed",
            "The pea is most commonly the small spherical seed or the seed-pod of the pod fruit Pisum sativum. Each pod contains several peas. Pea pods are botanically fruit, since they contain seeds and developed from the ovary of a flower.",
            "Cucumber is a widely cultivated plant in the gourd family, Cucurbitaceae. It is a creeping vine that bears cucumiform fruits that are used as vegetables. There are three main varieties of cucumber: slicing, pickling, and seedless.",
            "Lettuce is an annual plant of the daisy family, Asteraceae. It is most often grown as a leaf vegetable, but sometimes for its stem and seeds.",
            "Collard greens describes certain loose-leafed cultivars of Brassica oleracea, the same species as many common vegetables, including cabbage and broccoli.",
            "Celery is a marshland plant in the family Apiaceae that has been cultivated as a vegetable since antiquity. Celery has a long fibrous stalk tapering into leaves.",
            "Kale or leaf cabbage are certain cultivars of cabbage grown for their edible leaves. A kale plant has green or purple leaves and the central leaves do not form a head. ",
            "Eggplant, or aubergine, is a species of nightshade, grown for its edible fruit. Eggplant is the common name in North America, Australia and New Zealand, but British English uses the French word aubergine.",
            "Asparagus, or garden asparagus, folk name sparrow grass, scientific name Asparagus officinalis, is a spring vegetable, a flowering perennial plant species in the genus Asparagus.",
            "The radish is an edible root vegetable of the Brassicaceae family that was domesticated in Europe in pre-Roman times. Radishes are grown and consumed throughout the world, being mostly eaten raw as a crunchy salad vegetable. ",
            "The Brussels sprout is a member of the Gemmifera Group of cabbages, grown for its edible buds. The leafy green vegetables are typically 2.5–4 cm in diameter and look like miniature cabbages.",
            "The turnip or white turnip is a root vegetable commonly grown in temperate climates worldwide for its white, bulbous taproot. The word turnip is a compound of tur- as in turned/rounded on a lathe and neep, derived from Latin napus. ",
            "The bell pepper is a cultivar group of the species Capsicum annuum. Cultivars of the plant produce fruits in different colors, including red, yellow, orange, green, chocolate/brown, vanilla/white, and purple.",
            "Green beans are the unripe, young fruit and protective pods of various cultivars of the common bean. Immature or young pods of the runner bean, yardlong bean, and hyacinth bean are used in a similar way.",
            "Zucchini or courgette is a summer squash which can reach nearly a metre in length, but is usually harvested immature at 15 to 25 cm. In Britain and Ireland a fully grown zucchini is referred to as a marrow.",
            "Garlic is a species in the onion genus, Allium. Its close relatives include the onion, shallot, leek, chive, and Chinese onion.",
            "The globe artichoke is a variety of a species of thistle cultivated as a food. The edible portion of the plant consists of the flower buds before the flowers come into bloom. ",
            "Maize, also known as corn, is a cereal grain first domesticated by indigenous peoples in southern Mexico about 10,000 years ago.",
            "Okra or okro, known in many English-speaking countries as ladies' fingers or ochro, is a flowering plant in the mallow family. It is valued for its edible green seed pods",
            "Chard or Swiss chard is a green leafy vegetable that can be used in Mediterranean cooking. In the cultivars of the Flavescens-Group, the leaf stalks are large and often prepared separately from the leaf blade.",
            "The chili pepper from Nahuatl chīlli is the fruit of plants from the genus Capsicum, members of the nightshade family, Solanaceae. They are widely used in many cuisines to add spiciness to dishes.",
            "The leek is a vegetable, a cultivar of Allium ampeloprasum, the broadleaf wild leek. The edible part of the plant is a bundle of leaf sheaths that is sometimes erroneously called a stem or stalk.",
            "The rutabaga, swede, or neep is a root vegetable that originated as a cross between the cabbage and the turnip. The roots are prepared for human consumption in a variety of ways, and the leaves can be eaten as a leaf vegetable.",
            "Arugula or rocket is an edible annual plant. It is also called \"rocket salad\", \"rucola\", \"rucoli\", \"rugula\", \"colewort\", and \"roquette\".",
            "Kohlrabi is a biennial vegetable, and is a low, stout cultivar of cabbage. Kohlrabi can be eaten raw as well as cooked. Edible preparations are made with both the stem and the leaves.",
            "Watercress is an aquatic plant species with the botanical name Nasturtium officinale. This should not be confused with the profoundly different and unrelated group of plants with the common name of nasturtium, within the genus Tropaeolum.",
            "Endive is a leaf vegetable belonging to the genus Cichorium, which includes several similar bitter leafed vegetables. Species include Cichorium endivia, Cichorium pumilum, and Cichorium intybus.",
            "The parsnip is a root vegetable closely related to the carrot and parsley. It is a biennial plant usually grown as an annual.",
            "In general use, herbs are plants with savory or aromatic properties that are used for flavoring food, in medicine, or as fragrances. Culinary use typically distinguishes herbs from spices.",
            ""};
}